package Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database {
        static Connection conn = null;
        
        public static Connection getConnection(){
            try{
                if(conn != null) return conn;

                Class.forName("com.mysql.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_hotel", "root", "");
            }
            catch (ClassNotFoundException | SQLException e){
                System.out.println("Echec de connexion");
            }
            return conn;
        }

        /*public static ResultSet getResult(String requete){
            try {
                return (conn.prepareStatement(requete)).executeQuery();
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return null;
        }*/
}
