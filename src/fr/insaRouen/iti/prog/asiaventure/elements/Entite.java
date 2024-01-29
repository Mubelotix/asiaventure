package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.Monde;

public class Entite {
    private final Monde monde;
    public final String nom;

    public Entite(String nom, Monde monde) {
        this.monde = monde;
        this.nom = nom;
        monde.ajouter(this);
    }

    public Monde getMonde() {
        return this.monde;
    }

    public String getNom() {
        return this.nom;
    }

    public String toString() {
        return String.format("Entite(%s, %s)", this.getNom(), this.getMonde().getNom());
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

    public int hashCode() {
        return 13 * this.getNom().hashCode() + 17 * this.getMonde().hashCode();
    }
}
