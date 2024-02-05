package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.Monde;

public abstract class Objet extends Entite {
    public Objet(String nom, Monde monde) {
        super(nom, monde);
    }

    public abstract Boolean estDeplacable() ;
    

    public Objet clone() {
        return new Objet(this.getNom(), this.getMonde());
    }

    public static void ajouterObjetArray(Objet[] objets, Objet objet) {
        Objet[] newArray = new Objet[objets.length + 1];
        System.arraycopy(objets, 0, newArray, 0, objets.length);
        newArray[objets.length] = objet;
        objets = newArray;
    }

    public static Objet retirerObjetArray(Objet[] objets, String nomObjet) {
        // Locate the object in the array
        int index = -1;
        for (int i = 0; i < objets.length; i++) {
            if (objets[i].getNom().equals(nomObjet)) {
                index = i;
                break;
            }
        }
        
        // Copy what's before and after the object
        Objet[] newObjets = new Objet[objets.length - 1];
        System.arraycopy(objets, 0, newObjets, 0, index);
        System.arraycopy(objets, index + 1, newObjets, index, objets.length - index - 1);

        objets = newObjets;
        return objets[index];
    }

    public static Boolean contientObjetArray(Objet[] objets, String nomObjet) {
        for (int i = 0; i < objets.length; i++) {
            if (objets[i].getNom().equals(nomObjet))
                return true;
        }
        return false;
    }

    public static Objet[] cloneArray(Objet[] objets) {
        Objet[] cloneObjets = new Objet[objets.length];
        for (int i = 0; i < objets.length; i++) {
            cloneObjets[i] = objets[i].clone();
        }
        return cloneObjets;
    }
}
