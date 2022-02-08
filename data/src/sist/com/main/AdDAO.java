package sist.com.main;

import java.sql.*;

public class AdDAO {
    
    private Connection conn;
    private PreparedStatement ps;
    private DataBase db = new DataBase();
    
    public void adInsert(AdVO a) {

        try {
            conn = db.getConnection(conn);
            
            String sql = "INSERT INTO ad_1 VALUES (ad_id_seq_1.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, a.getC_id());
            ps.setString(2, a.getAd_title());
            ps.setString(3, a.getAd_content());
            ps.setString(4, a.getAd_we());
            ps.setString(5, a.getAd_education());
            ps.setString(6, a.getAd_qualification());
            ps.setString(7, a.getAd_language());
            ps.setString(8, a.getAd_major());
            ps.setString(9, a.getAd_wage());
            ps.setString(10, a.getAd_workhours());
            ps.setString(11, a.getAd_worktype());
            ps.setString(12, a.getAd_workplace());
            ps.setString(13, a.getAd_end());

            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.disConnection(conn, ps);
        }
    }
}
