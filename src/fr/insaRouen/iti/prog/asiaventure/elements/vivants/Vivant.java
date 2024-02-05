package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import fr.insaRouen.iti.prog.asiaventure.elements.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;

public abstract class Vivant extends Entite {
    int pointsVie;
    int pointsForce;
    Piece piece;
    Objet[] objets;

    public Vivant(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet[] objets) {
        super(nom, monde);
        this.pointsVie = pointsVie;
        this.pointsForce = pointsForce;
        // TODO add to piece
        this.piece = piece;
        this.objets = objets;
    }

    public void deposer(String nomObjet) {
        Objet objet = Objet.retirerObjetArray(this.objets, nomObjet);
        this.piece.deposer(objet);
    }

    public void deposer(Objet objet) {
        Objet.retirerObjetArray(this.objets, objet.getNom());
        this.piece.deposer(objet);
    }

    public Objet[] getObjets() {
        return Objet.cloneArray(this.objets);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getPointsVie() {
        return this.pointsVie;
    }

    public int getPointsForce() {
        return this.pointsForce;
    }

    public Boolean contientObjet(String nomObjet) {
        return Objet.contientObjetArray(this.objets, nomObjet);
    }

    public Boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    public void prendreObjet(String nomObjet) {
        Objet objet = this.piece.retirer(nomObjet);
        Objet.ajouterObjetArray(this.objets, objet);
    }

    public void prendreObjet(Objet objet) {
        prendreObjet(objet.getNom());
    }

    public static void ajouterVivantArray(Vivant[] vivants, Vivant vivant) {
        Vivant[] newArray = new Vivant[vivants.length + 1];
        System.arraycopy(vivants, 0, newArray, 0, vivants.length);
        newArray[vivants.length] = vivant;
        vivants = newArray;
    }

    public static Vivant retirerVivantArray(Vivant[] vivants, String nomVivant) {
        // Locate the object in the array
        int index = -1;
        for (int i = 0; i < vivants.length; i++) {
            if (vivants[i].getNom().equals(nomVivant)) {
                index = i;
                break;
            }
        }
        
        // Copy what's before and after the object
        Vivant[] newVivants = new Vivant[vivants.length - 1];
        System.arraycopy(vivants, 0, newVivants, 0, index);
        System.arraycopy(vivants, index + 1, newVivants, index, vivants.length - index - 1);

        vivants = newVivants;
        return vivants[index];
    }

    public static Boolean contientVivantArray(Vivant[] vivants, String nomVivant) {
        for (int i = 0; i < vivants.length; i++) {
            if (vivants[i].getNom().equals(nomVivant))
                return true;
        }
        return false;
    }

    public static Vivant[] cloneArray(Vivant[] vivants) {
        Vivant[] cloneVivants = new Vivant[vivants.length];
        System.arraycopy(vivants, 0, cloneVivants, 0, vivants.length);
        return cloneVivants;
    }
}
