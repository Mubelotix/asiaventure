package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

public class Piece extends ElementStructurel {
    private Objet[] objets;

    public Piece(String nom, Monde monde) {
        super(nom, monde);
    }

    public Boolean contientObjet(String nomObjet) {
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet))
                return true;
        }
        return false;
    }

    public Boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    public void deposerObjet(Objet objet) {
        Objet[] newObjets = new Objet[this.objets.length + 1];
        System.arraycopy(this.objets, 0, newObjets, 0, this.objets.length);
        newObjets[this.objets.length] = objet;
        this.objets = newObjets;
    }

    public Objet[] getObjets() {
        return this.objets;
    }

    public void retirerObjet(String nomObjet) {
        // Locate the object in the array
        int index = -1;
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet)) {
                index = i;
                break;
            }
        }
        
        // Copy what's before and after the object
        Objet[] newObjets = new Objet[this.objets.length - 1];
        System.arraycopy(this.objets, 0, newObjets, 0, index);
        System.arraycopy(this.objets, index + 1, newObjets, index, this.objets.length - index - 1);

        this.objets = newObjets;
    }

    public void retirerObjet(Objet objet) {
        this.retirerObjet(objet.getNom());
    }
}
