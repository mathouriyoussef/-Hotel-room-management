package Controllers;
import Config.*;
import Models.*;
import java.sql.*;
import java.util.Vector;
import javafx.util.Pair;

public class ClientController {
     private final Connection conn;
     PreparedStatement preparedStmt;
     ResultSet rs;
     
     public ClientController(){
         this.conn = database.getConnection();
         this.preparedStmt = null;
         this.rs = null;
     }
     public int GenerateNewId(){
         try{
            preparedStmt = conn.prepareStatement("SELECT max(Id) FROM clients");
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                if(rs.getInt(1) >= 0)
                return rs.getInt(1)+1;
            }
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         return 1;
     }
     public int Add(Client c){
         try{
            preparedStmt = conn.prepareStatement("INSERT INTO clients(Id,Name,Email,Password) VALUES(?,?,?,?)");
            preparedStmt.setInt(1, c.getIdClient());
            preparedStmt.setString(2, c.getName());
            preparedStmt.setString(3, c.getEmail());
            preparedStmt.setString(4, c.getPassword());
            return preparedStmt.executeUpdate();
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         return 0;
     }
     
     public Vector<Client> getAll(){
         try{
            Vector<Client> listClients = new Vector<Client>();
            preparedStmt = conn.prepareStatement("SELECT Id,Name,Email,Password,groupId FROM clients");
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                listClients.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
            return listClients;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
     }
     public boolean isEmailExist(String Email) {
        try{
            preparedStmt = conn.prepareStatement("SELECT Email FROM clients");
            rs =  preparedStmt.executeQuery();
            while(rs.next())
            if(Email.equals(rs.getString(1))) return true;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
     
    public Client getByEmail(String Email, String Password){
        try{
            preparedStmt = conn.prepareStatement("SELECT * FROM clients WHERE Email=? AND Password=?");
            preparedStmt.setString(1, Email);
            preparedStmt.setString(2, Password);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                return new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public Client getById(int Id){
        try{
            preparedStmt = conn.prepareStatement("SELECT * FROM clients WHERE Id=?");
            preparedStmt.setInt(1, Id);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                return new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean isAdmin(int groupId){
        try{
            preparedStmt = conn.prepareStatement("SELECT isAdmin FROM groups WHERE Id=?");
            preparedStmt.setInt(1, groupId);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                return rs.getInt(1) == 1;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
