/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Config.*;
import Models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javafx.util.Pair;

/**
 *
 * @author TyZoX
 */
public class ChambreController {
    private final Connection conn;
     PreparedStatement preparedStmt;
     ResultSet rs;
     
     public ChambreController(){
         this.conn = database.getConnection();
         this.preparedStmt = null;
         this.rs = null;
     }
     
     public Vector<Chambre> getAll(){
        try{
            Vector<Chambre> listChambers = new Vector<Chambre>();
            preparedStmt = conn.prepareStatement("SELECT Id,Type,Description,Tarif,capacite FROM Chambres");
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                listChambers.add(new Chambre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5)));
            }
            return listChambers;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
     public Chambre getById(int Id){
        try{
            preparedStmt = conn.prepareStatement("SELECT Id,Type,Description,Tarif,capacite FROM Chambres WHERE Id=?");
            preparedStmt.setInt(1, Id);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                return new Chambre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
