package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import java.io.Serializable;
import fr.insaRouen.iti.prog.asiaventure.EntiteDejaDansUnAutreMondeException;;

public abstract class Entite implements Serializable {
    protected final Monde monde;
    public final String nom;

    public Entite(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = monde;
        this.nom = nom;
        try {
            monde.ajouter(this);
        } catch(EntiteDejaDansUnAutreMondeException e) {
            throw new Error("impossible");
        }
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
        return (autre != null)
            && (this.getClass() == autre.getClass())
            && this.getNom().equals(((Entite)autre).getNom())
            && this.getMonde().equals(((Entite)autre).getMonde()); /// == par defaut
    }

    public int hashCode() {
        return 13 * this.getNom().hashCode() + 17 * this.getMonde().hashCode();
    }
}
