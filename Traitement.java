package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.data.general.DefaultPieDataset;

public class Traitement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_reviews";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static Connection con = Connect.getConnection();

    public static void saveRatingToDatabase(String name, int Comfort, int Safety,int Entertainment,int Cleanliness, String commentaire) {
        try {
            

            String sql = "INSERT INTO avis (name, Comfort, Safety,Entertainment,Cleanliness, commentaire, date_avis) VALUES (?,?,?,?,?,?,?)";
            try  (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, Comfort);
                ps.setInt(3, Safety);
                ps.setInt(4, Entertainment);
                ps.setInt(5, Cleanliness);
                ps.setString(6, commentaire);
                ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
                ps.executeUpdate();
             
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            // If you need to perform any cleanup, you can do it here
        }
    }
     

}
