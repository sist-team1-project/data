package sist.com.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class BookManager {

    public static void main(String[] args) {
        BookManager b = new BookManager();
        b.bookData();
    }

    public void bookData() {
        try {
            BookDAO bdao = new BookDAO();
            BookVO b = new BookVO();

            for (int i = 1; i <= 10; i++) {
                
                Document doc = Jsoup.connect("http://www.yes24.com/24/category/bestseller?CategoryNumber=001001015&sumgb=08&PageNumber="+i+"&FetchSize=80").timeout(30000).get();
                Elements link = doc.select("div.goodsImgW a");
                
                for (int j = 0; j < link.size(); j++) {
                    String path = link.get(j).attr("href");
                    if (!path.startsWith("/Product/Goods/"))
                        continue;
                    
                    Document doc2 = Jsoup.connect("http://www.yes24.com/" + path).timeout(30000).get();
                    
                    /* 수험서 타이틀 */
                    b.setBook_title(doc2.select("div.gd_titArea").text());

                    /* 표지 */
                    b.setBook_img(doc2.select("div.gd_imgArea img").attr("src"));

                    /* 수험서 판매지수 */
                    b.setBook_sold(Integer.parseInt(doc2.select("span.gd_sellNum").text().replaceAll("[^\\d]", "")));

                    /* Yes24링크 */
                    b.setBook_link("http://www.yes24.com/"+path);

                    bdao.bookInsert(b); // 오라클에 값 저장
                    System.out.println("잘 넣고 있으니 걱정마세요 토닥토닥 -은영-");
                }
            }
            System.out.println("끝났어요 예이~ -은영-");
        } catch (Exception ex) {
            System.err.println("빼액!!!!!!!!!!!!!!!!!!!!!!! 오류발생");
            ex.printStackTrace();
        }
    }
}