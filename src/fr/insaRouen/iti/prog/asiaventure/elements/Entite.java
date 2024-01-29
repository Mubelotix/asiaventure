package fr.insaRouen.iti.prog.asiaventure.elements;

public class Entite {
    private Monde monde;
    public String nom;

    public Entite(String nom, Monde monde) {
        this.monde = monde;
        this.nom = nom;
    }

    private Monde getMonde() {
        return this.monde;
    }

    private String getNom() {
        return this.nom;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entite(");
        sb.append(this.getNom());
        sb.append(", ");
        sb.append(this.getMonde());
        sb.append(")");
        return sb.toString();
    }
}
