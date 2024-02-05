package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;;

/** Une pièce est un élément structurel du monde qui contient des objets et des vivants. */
public class Piece extends ElementStructurel {
    private Objet[] objets;
    private Vivant[] vivants;

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
        return Objet.contientObjetArray(this.objets, nomObjet);
    }

    /** Vérifie si la pièce contient un objet.
     * @param objet L'objet.
     * @return Vrai si la pièce contient l'objet, faux sinon.
     */
    public Boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    public Boolean contientVivant(String nomVivant) {
        return Vivant.contientVivantArray(this.vivants, nomVivant);
    }

    public Boolean contientVivant(Vivant vivant) {
        return this.contientVivant(vivant.getNom());
    }

    /** Ajoute un objet dans la pièce.
     * @param objet L'objet à ajouter.
     */
    public void deposer(Objet objet) {
        Objet.ajouterObjetArray(this.objets, objet);
    }

    public void entree(Vivant vivant) {
        Vivant.ajouterVivantArray(this.vivants, vivant);
    }

    /** Récupère tous les objet de la pièce.
     * @return Les objets de la pièce.
     */
    public Objet[] getObjets() {
        return Objet.cloneArray(this.objets);
    }

    public Vivant[] getVivants() {
        return Vivant.cloneArray(this.vivants);
    }

    /** Récupère un objet de la pièce.
     * @param nomObjet Le nom de l'objet.
     * @return L'objet.
     */
    public Objet retirer(String nomObjet) {
        return Objet.retirerObjetArray(this.objets, nomObjet);
    }

    /** Récupère un objet de la pièce.
     * @param objet L'objet.
     * @return L'objet.
     */
    public Objet retirer(Objet objet) {
        return this.retirer(objet.getNom());
    }

    public Vivant sortirVivant(String nomVivant) {
        return Vivant.retirerVivantArray(this.vivants, nomVivant);
    }
}
