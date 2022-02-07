package sist.com.main;

public class BookVO {
    
    private String book_title;
    private String book_img;
    private int book_sold;
    private String book_link;

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public int getBook_sold() {
        return book_sold;
    }

    public void setBook_sold(int book_sold) {
        this.book_sold = book_sold;
    }

    public String getBook_link() {
        return book_link;
    }

    public void setBook_link(String book_link) {
        this.book_link = book_link;
    }

    @Override
    public String toString() {
        return "book_title:" + book_title + "|book_img:" + book_img + "|book_sold:" + book_sold + "|book_link:"
                + book_link;
    }
}
