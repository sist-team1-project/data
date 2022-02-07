package sist.com.main;

public class CompanyVO {
    
    private int c_id;
    private String c_logo;
    private String c_name;
    private String c_address;
    private String c_industry;
    private String c_size;

    
    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_logo() {
        return c_logo;
    }

    public void setC_logo(String c_logo) {
        this.c_logo = c_logo;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public String getC_industry() {
        return c_industry;
    }

    public void setC_industry(String c_industry) {
        this.c_industry = c_industry;
    }

    public String getC_size() {
        return c_size;
    }

    public void setC_size(String c_size) {
        this.c_size = c_size;
    }

    @Override
    public String toString() {
        return "c_id:" + c_id + "|c_logo:" + c_logo + "|c_name:" + c_name + "|c_address:" + c_address + "|c_industry:"
                + c_industry + "|c_size:" + c_size;
    }
}
