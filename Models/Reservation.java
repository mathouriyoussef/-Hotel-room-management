package Models;

import java.util.*;

public class Reservation {
    private int IdRes;
    private Date dateArrive;
    private Date dateDepart;
    private int nbNuit;
    private int nbPersonne;
    private Chambre chambre;
    private Client client;
    private int status;

    public Reservation(int Id,Date dateArrive, Date dateDepart, int nmbNuit, int nmbPersonne, Chambre chambre, Client client, int status) {
        this.IdRes = Id;
        this.dateArrive = dateArrive;
        this.dateDepart = dateDepart;
        this.nbNuit = nmbNuit;
        this.nbPersonne = nmbPersonne;
        this.chambre = chambre;
        this.client = client;
        this.status = status;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getNbNuit() {
        return nbNuit;
    }

    public void setNbNuit(int nbNuit) {
        this.nbNuit = nbNuit;
    }

    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getIdRes() {
        return IdRes;
    }

    public void setIdRes(int IdRes) {
        this.IdRes = IdRes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
