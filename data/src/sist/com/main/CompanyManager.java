package sist.com.main;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class CompanyManager {
    
    static List<CompanyVO> list = new ArrayList<CompanyVO>();
    
    public static int duplicate(String c_name, String c_addr) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getC_name().equals(c_name)&&list.get(i).getC_address().equals(c_addr))
                return list.get(i).getC_id();
        }
        return -1;
    }
    
    // 주석 제거
    private static void removeComments(Node node) {
        for (int i = 0; i < node.childNodeSize();) {
            Node child = node.childNode(i);
            if (child.nodeName().equals("#comment"))
                child.remove();
            else {
                removeComments(child);
                i++;
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            CompanyDAO cdao = new CompanyDAO();
            AdDAO adao = new AdDAO();
            
            for (int i = 1; i <= 20; i++) {
    
                Document doc = Jsoup.connect(
                        "https://www.work.go.kr/empInfo/empInfoSrch/list/dtlEmpSrchList.do?careerTo=&keywordJobCd=&occupation=&templateInfo=&shsyWorkSecd=&rot2WorkYn=&payGbn=&resultCnt=50&keywordJobCont=&cert=&cloDateStdt=&moreCon=&minPay=&codeDepth2Info=11000&isChkLocCall=&sortFieldInfo=DATE&major=&resrDutyExcYn=&eodwYn=&sortField=DATE&staArea=&sortOrderBy=DESC&keyword=&termSearchGbn=all&carrEssYns=&benefitSrchAndOr=O&disableEmpHopeGbn=&webIsOut=&actServExcYn=&maxPay=&keywordStaAreaNm=&emailApplyYn=&listCookieInfo=DTL&pageCode=&codeDepth1Info=11000&keywordEtcYn=&publDutyExcYn=&keywordJobCdSeqNo=&exJobsCd=&templateDepthNmInfo=&computerPreferential=&regDateStdt=&employGbn=&empTpGbcd=&region=&infaYn=&resultCntInfo=50&siteClcd=WORK&cloDateEndt=&sortOrderByInfo=DESC&currntPageNo=1&indArea=&careerTypes=&searchOn=Y&tlmgYn=&subEmpHopeYn=&academicGbn=&templateDepthNoInfo=&foriegn=&mealOfferClcd=&station=&moerButtonYn=Y&holidayGbn=&enterPriseGbn=01%2C08%2C20%2C05%2C03&academicGbnoEdu=noEdu&cloTermSearchGbn=all&keywordWantedTitle=&stationNm=&benefitGbn=&keywordFlag=&essCertChk=&isEmptyHeader=&depth2SelCode=&_csrf=d1fbc74c-1d29-4a6d-b4db-5e6573845d67&keywordBusiNm=&preferentialGbn=&rot3WorkYn=&pfMatterPreferential=&regDateEndt=&staAreaLineInfo1=11000&staAreaLineInfo2=1&pageIndex="+i+"&termContractMmcnt=&careerFrom=&laborHrShortYn=#viewSPL").timeout(30000).get();

                Elements link = doc.select("div.cp-info-in > a");
                
                for(int j = 0; j < link.size(); j++) {
                    String path = link.get(j).attr("href");
                    Document doc2 = Jsoup.connect("https://www.work.go.kr" + path.substring(0, path.lastIndexOf("&"))).timeout(30000).get();
                    removeComments(doc2); // 코멘트 지우기
                    doc2.select("div.careers-table p.txt").remove(); // 근무시간에 소정근무시간 지우기
                    doc2.select("div.careers-table span").remove(); // 상세근무시간 span 지우기

                    /* --------------------------- 회사 ---------------------------- */
                    
                    Elements comp = doc2.select("div.info > ul > li > div"); // 회사정보 선택
                    
                    CompanyVO c = new CompanyVO();
                    AdVO a = new AdVO();

                    if(comp.size() == 0) continue;
                                        
                    // 중복 확인을 위하여 회사명과 주소를 먼저 저장
                    String c_name = comp.get(0).text();
                    String c_addr = doc2.select("div.data-table td").first().text();
                    
                    int id = duplicate(c_name, c_addr); // 이미 존재하는 회사인지 확인 후 존재하면 ID 저장, 존재하지 않으면 ID는 -1
                    if ( id == -1) { // 데이터에 회사가 아직 존재하지 않음

                        /** 회사 ID **/
                        id = cdao.getCompanyID(); // 시퀀스를 증가하며 회사 번호 저장
                        c.setC_id(id);
                        
                        /** 회사 로고 **/
                        c.setC_logo("https://work.go.kr"+doc2.select("div.logo-company img").attr("src"));
                        
                        /** 회사 이름 **/
                        c.setC_name(c_name);
                        
                        /** 회사 주소 **/
                        c.setC_address(c_addr);
                        
                        /** 업종 **/
                        c.setC_industry(comp.get(1).text());
                        
                        /** 규모 **/
                        c.setC_size(comp.get(2).text());
                        
                        list.add(c); // 후에 중복 확인을 위하여 리스트에 저장
                        
                        cdao.companyInsert(c); // 오라클에 값 저장
                    }
                    else { // 이미 존재하는 회사임
                        //System.out.println("중복됨");
                    }

                    Elements adtitle = doc2.select("div.careers-new p.tit");
                    Elements ad = doc2.select("div.careers-table td");
                    
                    /** 아이디 **/
                    a.setC_id(id);
                    
                    /** 타이틀 **/
                    a.setAd_title(adtitle.get(0).text());
                    
                    /** 내용 **/
                    a.setAd_content(ad.get(0).html().replace("&nbsp;"," ").replace("&amp;","&").replace("&lt;","<").replace("&gt;",">").replace("&035;","#").replaceAll(" +", " ")); // html 특수문자 변환
                    a.setAd_we(ad.get(1).text());
                    a.setAd_education(ad.get(2).text());
                    
                    /** 우대 자격증 (구분자: ,) **/
                    String qualification = ad.get(21).text().replace(" -", "").replace("(우대)","").replace("(필수)", ""); // 자격증에서 -, (우대), (필수) 제거
                    if(qualification.contains("기타")) { // 자격증에 기타가 포함되어있으면 기타 이전까지만 저장
                        qualification = qualification.substring(0,qualification.indexOf("기타"));
                    }   
                    a.setAd_qualification(qualification.replace(" ", "")); // 스페이스 제거
                    
                    /** 우대 언어 **/
                    a.setAd_language(ad.get(22).text());
                    
                    /** 우대 전공 (구분자: ,)**/
                    String major = ad.get(20).text().replace(") ", "),");
                    a.setAd_major(major);
                    
                    /** 임금 **/
                    a.setAd_wage(ad.get(10).html().replace("&nbsp;"," ").replace("&amp;","&").replace("&lt;","<").replace("&gt;",">").replace("&035;","#").replaceAll(" +", " ")); // html 특수문자 변환
                    
                    /** 근무 시간 **/
                    String workhours = ad.get(11).html().replace("&nbsp;"," ").replace("&amp;","&").replace("&lt;","<").replace("&gt;",">").replace("&035;","#").replaceAll(" +", " "); // html 특수문자 변환
                    a.setAd_workhours(workhours.substring(0,workhours.lastIndexOf("<br>")).trim()); // html 뒤에 <br> 붙는거 제거
                    
                    /** 근무 형태 **/
                    a.setAd_worktype(ad.get(12).text());
                    
                    /** 근무 예정지 **/
                    a.setAd_workplace(ad.get(6).text());
                    
                    /** 접수 마감일 (데이터: 날짜 / 채용시까지) **/
                    String end = ad.get(15).text();
                    a.setAd_end(end.substring(0,end.indexOf(" 마")).replace(".", "-"));
                    
                    adao.adInsert(a); // 오라클에 값 저장
                }
            }
            System.out.println("완료");
    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}