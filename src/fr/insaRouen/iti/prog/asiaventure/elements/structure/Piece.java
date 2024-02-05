package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

/** Une pièce est un élément structurel du monde qui contient des objets et des vivants. */
public class Piece extends ElementStructurel {
    private Objet[] objets;

    /** Crée une pièce avec son nom et un monde.
     * @param nom Le nom de la pièce.
     * @param monde Le monde de la pièce.
     */
    public Piece(String nom, Monde monde) {
        super(nom, monde);
    }

    /** Vérifie si la pièce contient un objet.
     * @param nomObjet Le nom de l'objet.
     * @return Vrai si la pièce contient l'objet, faux sinon.
     */
    public Boolean contientObjet(String nomObjet) {
        for (int i = 0; i < this.objets.length; i++) {
            if (this.objets[i].getNom().equals(nomObjet))
                return true;
        }
        return false;
    }

    /** Vérifie si la pièce contient un objet.
     * @param objet L'objet.
     * @return Vrai si la pièce contient l'objet, faux sinon.
     */
    public Boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    /** Ajoute un objet dans la pièce.
     * @param objet L'objet à ajouter.
     */
    public void deposerObjet(Objet objet) {
        Objet[] newObjets = new Objet[this.objets.length + 1];
        System.arraycopy(this.objets, 0, newObjets, 0, this.objets.length);
        newObjets[this.objets.length] = objet;
        this.objets = newObjets;
    }

    /** Récupère tous les objet de la pièce.
     * @return Les objets de la pièce.
     */
    public Objet[] getObjets() {
        return this.objets;
    }

    /** Récupère un objet de la pièce.
     * @param nomObjet Le nom de l'objet.
     * @return L'objet.
     */
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

    /** Récupère un objet de la pièce.
     * @param objet L'objet.
     */
    public void retirerObjet(Objet objet) {
        this.retirerObjet(objet.getNom());
    }
}
