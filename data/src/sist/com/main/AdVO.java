package sist.com.main;

public class AdVO {

    int c_id;
    String ad_title;
    String ad_content;
    String ad_we;
    String ad_education;
    String ad_qualification;
    String ad_language;
    String ad_major;
    String ad_wage;
    String ad_workhours;
    String ad_worktype;
    String ad_workplace;
    String ad_end;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getAd_title() {
        return ad_title;
    }

    public void setAd_title(String ad_title) {
        this.ad_title = ad_title;
    }

    public String getAd_content() {
        return ad_content;
    }

    public void setAd_content(String ad_content) {
        this.ad_content = ad_content;
    }

    public String getAd_we() {
        return ad_we;
    }

    public void setAd_we(String ad_we) {
        this.ad_we = ad_we;
    }

    public String getAd_education() {
        return ad_education;
    }

    public void setAd_education(String ad_education) {
        this.ad_education = ad_education;
    }

    public String getAd_qualification() {
        return ad_qualification;
    }

    public void setAd_qualification(String ad_qualification) {
        this.ad_qualification = ad_qualification;
    }

    public String getAd_language() {
        return ad_language;
    }

    public void setAd_language(String ad_language) {
        this.ad_language = ad_language;
    }

    public String getAd_major() {
        return ad_major;
    }

    public void setAd_major(String ad_major) {
        this.ad_major = ad_major;
    }

    public String getAd_wage() {
        return ad_wage;
    }

    public void setAd_wage(String ad_wage) {
        this.ad_wage = ad_wage;
    }

    public String getAd_workhours() {
        return ad_workhours;
    }

    public void setAd_workhours(String ad_workhours) {
        this.ad_workhours = ad_workhours;
    }

    public String getAd_worktype() {
        return ad_worktype;
    }

    public void setAd_worktype(String ad_worktype) {
        this.ad_worktype = ad_worktype;
    }

    public String getAd_workplace() {
        return ad_workplace;
    }

    public void setAd_workplace(String ad_workplace) {
        this.ad_workplace = ad_workplace;
    }

    public String getAd_end() {
        return ad_end;
    }

    public void setAd_end(String ad_end) {
        this.ad_end = ad_end;
    }

    @Override
    public String toString() {
        return "c_id:" + c_id + "|ad_title:" + ad_title + "|ad_content:" + ad_content + "|ad_we:" + ad_we
                + "|ad_education:" + ad_education + "|ad_qualification:" + ad_qualification + "|ad_language:"
                + ad_language + "|ad_major:" + ad_major + "|ad_wage:" + ad_wage + "|ad_workhours:" + ad_workhours
                + "|ad_worktype:" + ad_worktype + "|ad_workplace:" + ad_workplace + "|ad_end:" + ad_end;
    }
}