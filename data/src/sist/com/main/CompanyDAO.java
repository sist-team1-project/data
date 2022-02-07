package sist.com.main;

import java.sql.*;

public class CompanyDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DataBase db = new DataBase();
    
    public int getCompanyID() {
        int nextID_from_seq = 0;
        try {
            conn = db.getConnection(conn);

            String sql = "SELECT c_id_seq.NEXTVAL FROM DUAL";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                nextID_from_seq = rs.getInt(1);

            rs.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.disConnection(conn, ps);
        }
        return nextID_from_seq;
    }
    
    public void companyInsert(CompanyVO c) {

        try {
            conn = db.getConnection(conn);

            String sql = "INSERT INTO company VALUES (?,?,?,?,?,?,0)";
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, c.getC_id());
            ps.setString(2, c.getC_logo());
            ps.setString(3, c.getC_name());
            ps.setString(4, c.getC_address());
            ps.setString(5, c.getC_industry());
            ps.setString(6, c.getC_size());
            
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.disConnection(conn, ps);
        }
    }
}