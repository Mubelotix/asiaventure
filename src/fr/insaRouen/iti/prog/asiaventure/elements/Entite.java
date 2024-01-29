package fr.insaRouen.iti.prog.asiaventure.elements;

public class Entite {
    private Monde monde;
    public final String nom;

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

    public boolean equals(Object autre) {
        if (autre == null) {
            return false;
        }
        if (!(autre instanceof Entite)) {
            return false;
        }
        Entite autreEntite = (Entite) autre;
        return this.getNom().equals(autreEntite.getNom()) && this.getMonde().equals(autreEntite.getMonde());
    }
}
