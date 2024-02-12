package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

public abstract class Objet extends Entite {
    public Objet(String nom, Monde monde) {
        super(nom, monde);
    }

    public abstract boolean estDeplacable();

    // -- Static utility methods --

    public static Objet[] ajouterObjetArray(Objet[] objets, Objet objet) {
        if (locateObjetArray(objets, objet.getNom()) != -1) {
            return objets;
        }
        Objet[] newArray = new Objet[objets.length + 1];
        System.arraycopy(objets, 0, newArray, 0, objets.length);
        newArray[objets.length] = objet;
        return newArray;
    }

    public static int locateObjetArray(Objet[] objets, String nomObjet) {
        for (int i = 0; i < objets.length; i++) {
            if (objets[i].getNom().equals(nomObjet))
                return i;
        }
        return -1;
    }

    public static Objet[] retirerObjetArray(Objet[] objets, int index) {
        if (index == -1) {
            return objets;
        }

        // Copy what's before and after the object
        Objet[] newObjets = new Objet[objets.length - 1];
        System.arraycopy(objets, 0, newObjets, 0, index);
        System.arraycopy(objets, index + 1, newObjets, index, objets.length - index - 1);

        return newObjets;
    }

    public static boolean contientObjetArray(Objet[] objets, String nomObjet) {
        for (int i = 0; i < objets.length; i++) {
            if (objets[i].getNom().equals(nomObjet))
                return true;
        }
        return false;
    }

    public static Objet[] cloneArray(Objet[] objets) {
        Objet[] cloneObjets = new Objet[objets.length];
        System.arraycopy(objets, 0, cloneObjets, 0, objets.length);
        return cloneObjets;
    }
}
