/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.*;
import java.sql.*;


/**
 *
 * @author TyZoX
 */
public class StatusController {
     private final Connection conn;
     PreparedStatement preparedStmt;
     ResultSet rs;
     public StatusController(){
         this.conn = database.getConnection();
         this.preparedStmt = null;
         this.rs = null;
     }
     public String getById(int Id){
         try{
            preparedStmt = conn.prepareStatement("SELECT Description FROM status WHERE Id=?");
            preparedStmt.setInt(1, Id);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                return rs.getString(1);
            }
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         return null;
     }
}
