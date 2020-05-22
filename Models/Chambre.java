package Models;

public class Chambre {
    private int idChambre;
    private String typeChambre;
    private String description;
    private double Tarif;
    private int capacite;

    public Chambre(int idChambre, String typeChambre, String description, double Tarif, int capacite) {
        this.idChambre = idChambre;
        this.typeChambre = typeChambre;
        this.description = description;
        this.Tarif = Tarif;
        this.capacite = capacite;
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public String getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(String typeChambre) {
        this.typeChambre = typeChambre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTarif() {
        return Tarif;
    }

    public void setTarif(double Tarif) {
        this.Tarif = Tarif;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
    @Override
    public String toString() {
        return "Chambre{" +
                "idChambre=" + idChambre +
                ", typeChambre='" + typeChambre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
