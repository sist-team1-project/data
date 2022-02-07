package sist.com.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection conn;
    private PreparedStatement ps;
    private DataBase db = new DataBase();


    public void bookInsert(BookVO b) {
        try {
            conn = db.getConnection(conn);

            String sql = "INSERT INTO textbook VALUES (textbook_id_seq.NEXTVAL,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, b.getBook_title());
            ps.setString(2, b.getBook_img());
            ps.setInt(3, b.getBook_sold());
            ps.setString(4, b.getBook_link());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.disConnection(conn, ps);
        }
    }
}