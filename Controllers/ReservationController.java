/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.*;
import Models.*;
import java.sql.*;
import java.util.*;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author TyZoX
 */
public class ReservationController {
     private final Connection conn;
     PreparedStatement preparedStmt;
     ResultSet rs;
     private ChambreController cc = new ChambreController();
     private ClientController clc = new ClientController();
     
     public ReservationController(){
         this.conn = database.getConnection();
         this.preparedStmt = null;
         this.rs = null;
     }
     public int GenerateNewId(){
         try{
            preparedStmt = conn.prepareStatement("SELECT max(Id) FROM Reservations");
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
     public int Add(Reservation r){
         try{
            java.sql.Date dateArrive = new java.sql.Date(r.getDateArrive().getTime());
            java.sql.Date dateDepart = new java.sql.Date(r.getDateDepart().getTime());
            preparedStmt = conn.prepareStatement("INSERT INTO Reservations(Id,dateArrive,dateDepart,NbrPersonne,NbrNuit,chambreTypeId,clientId,status) VALUES(?,?,?,?,?,?,?,?)");
            preparedStmt.setInt(1, r.getIdRes());
            preparedStmt.setDate(2, dateArrive);
            preparedStmt.setDate(3, dateDepart);
            preparedStmt.setInt(4, r.getNbPersonne());
            preparedStmt.setInt(5, r.getNbNuit());
            preparedStmt.setInt(6, r.getChambre().getIdChambre());
            preparedStmt.setInt(7, r.getClient().getIdClient());
            preparedStmt.setInt(8, r.getStatus());
            return preparedStmt.executeUpdate();
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         return 0;
     }
     public int Remove(int resId){
         try{
            preparedStmt = conn.prepareStatement("DELETE FROM Reservations WHERE Id=?");
            preparedStmt.setInt(1, resId);
            return preparedStmt.executeUpdate();
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         return 0;
     }
    public int Accept(int resId){
         try{
            preparedStmt = conn.prepareStatement("UPDATE Reservations SET status=2 WHERE Id=?");
            preparedStmt.setInt(1, resId);
            return preparedStmt.executeUpdate();
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         return 0;
     }
     public void sendAccept(int idc) throws SQLException{
        preparedStmt = conn.prepareStatement("Select Email from clients,reservations  WHERE reservations.clientId=clients.Id AND reservations.id=?");
            preparedStmt.setInt(1, idc); 
            ResultSet rs=preparedStmt.executeQuery();
            if(rs.next()){
                String ToEmail=rs.getString(1);
            
       //String ToEmail ="";
        String fromEmail= "hotelh418@gmail.com";
        String FromEmailPassword ="algouc++1418";
        String subjects="hotel maroc";
        
        Properties properties =new Properties();
        properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
         properties.put("mail.smtp.host", "smtp.gmail.com");
          properties.put("mail.smtp.port", "587");
          Session session =Session.getDefaultInstance(properties,new javax.mail.Authenticator() {

          protected PasswordAuthentication getPasswordAuthentication(){
          return new PasswordAuthentication(fromEmail,FromEmailPassword);
          
          
          }
                  });
          try {
              
              MimeMessage message=new MimeMessage(session);
              message.setFrom(new InternetAddress(fromEmail));
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(ToEmail));
             message.setSubject(subjects);
             message.setText("Bienvenu dans hotel Maroc : C'est Reservation elle Accepter \n" +"Merci pour votre confiance.\n"+" Nous sommes à votre service ");
              Transport.send(message);
              System.out.println("yes");
        } catch (Exception ex) {
             System.out.println(""+ex);
        }
          
            }
     }
      public int Refuse(int resId){
         try{
            preparedStmt = conn.prepareStatement("UPDATE Reservations SET Status=3 WHERE Id=?");
            preparedStmt.setInt(1, resId);
            return preparedStmt.executeUpdate();
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         return 0;
     }
      public void sendRefuse(int idc) throws SQLException{
        preparedStmt = conn.prepareStatement("Select Email from clients,reservations  WHERE reservations.clientId=clients.Id AND reservations.id=?");
            preparedStmt.setInt(1, idc); 
            ResultSet rs=preparedStmt.executeQuery();
            if(rs.next()){
                String ToEmail=rs.getString(1);
            
       //String ToEmail ="";
        String fromEmail= "hotelh418@gmail.com";
        String FromEmailPassword ="algouc++1418";
        String subjects="hotel maroc";
        
        Properties properties =new Properties();
        properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
         properties.put("mail.smtp.host", "smtp.gmail.com");
          properties.put("mail.smtp.port", "587");
          Session session =Session.getDefaultInstance(properties,new javax.mail.Authenticator() {

          protected PasswordAuthentication getPasswordAuthentication(){
          return new PasswordAuthentication(fromEmail,FromEmailPassword);
          
          
          }
                  });
          try {
              
              MimeMessage message=new MimeMessage(session);
              message.setFrom(new InternetAddress(fromEmail));
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(ToEmail));
             message.setSubject(subjects);
             message.setText("Bienvenu dans hotel Maroc : C'est Reservation elle refusé  \n" +"Merci pour votre confiance.\n"+" Nous sommes à votre service ");
              Transport.send(message);
              System.out.println("yes");
        } catch (Exception ex) {
             System.out.println(""+ex);
        }
          
            }}
     public boolean getStatus(int resId, int status){
         try{
            preparedStmt = conn.prepareStatement("SELECT status FROM Reservations WHERE Id=?");
            preparedStmt.setInt(1, resId);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                return rs.getInt(1) == status;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
     }
        public Vector<Reservation> getAll(){
         try{
            Vector<Reservation> listReservation = new Vector<Reservation>();
            preparedStmt = conn.prepareStatement("SELECT Id,dateArrive,dateDepart,NbrPersonne,NbrNuit,chambreTypeId,clientId,status FROM Reservations");
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                listReservation.add(new Reservation(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),rs.getInt(5),cc.getById(rs.getInt(6)),clc.getById(rs.getInt(7)),rs.getInt(8)));
            }
            return listReservation;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
     }
   public Vector<Reservation> getByIdClient(int Id){
         try{
            Vector<Reservation> listReservation = new Vector<Reservation>();
            preparedStmt = conn.prepareStatement("SELECT Reservations.Id,dateArrive,dateDepart,NbrPersonne,NbrNuit,chambreTypeId,clientId,status FROM Reservations,Clients WHERE clientId=? and clientId=Clients.Id");
            preparedStmt.setInt(1, Id);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                listReservation.add(new Reservation(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),rs.getInt(5),cc.getById(rs.getInt(6)),clc.getById(rs.getInt(7)),rs.getInt(8)));
            }
            return listReservation;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
     }
}
