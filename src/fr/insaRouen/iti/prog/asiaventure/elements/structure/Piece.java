package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;

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
        this.objets = new Objet[0];
        this.vivants = new Vivant[0];
    }

    /** Vérifie si la pièce contient un objet.
     * @param nomObjet Le nom de l'objet.
     * @return Vrai si la pièce contient l'objet, faux sinon.
     */
    public boolean contientObjet(String nomObjet) {
        return Objet.contientObjetArray(this.objets, nomObjet);
    }

    /** Vérifie si la pièce contient un objet.
     * @param objet L'objet.
     * @return Vrai si la pièce contient l'objet, faux sinon.
     */
    public boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    /** Vérifie si la pièce contient un vivant.
     * @param nomVivant Le nom du vivant.
     * @return Vrai si la pièce contient le vivant, faux sinon.
     */
    public boolean contientVivant(String nomVivant) {
        return Vivant.contientVivantArray(this.vivants, nomVivant);
    }

    /** Vérifie si la pièce contient un vivant.
     * @param vivant Le vivant.
     * @return Vrai si la pièce contient le vivant, faux sinon.
     */
    public boolean contientVivant(Vivant vivant) {
        return this.contientVivant(vivant.getNom());
    }

    /** Ajoute un objet dans la pièce.
     * @param objet L'objet à ajouter.
     */
    public void deposer(Objet objet) {
        this.objets = Objet.ajouterObjetArray(this.objets, objet);
    }

    /** Ajoute un vivant dans la pièce.
     * @param vivant Le vivant à ajouter.
     */
    public void entrer(Vivant vivant) {
        this.vivants = Vivant.ajouterVivantArray(this.vivants, vivant);
        vivant.entrer(this);
    }

    /** Récupère tous les objet de la pièce.
     * @return Les objets de la pièce.
     */
    public Objet[] getObjets() {
        return Objet.cloneArray(this.objets);
    }

    /** Récupère tous les vivants de la pièce.
     * @return Les vivants de la pièce.
     */
    public Vivant[] getVivants() {
        return Vivant.cloneArray(this.vivants);
    }

    /** Retire un objet de la pièce.
     * @param nomObjet Le nom de l'objet.
     * @return L'objet.
     */
    public Objet retirer(String nomObjet) {
        int index = Objet.locateObjetArray(this.objets, nomObjet);
        Objet objet = this.objets[index];
        this.objets = Objet.retirerObjetArray(this.objets, index);
        return objet;
    }

    /** Retire un objet de la pièce.
     * @param objet L'objet.
     * @return L'objet.
     */
    public Objet retirer(Objet objet) {
        return this.retirer(objet.getNom());
    }

    /** Retire un vivant de la pièce.
     * @param nomVivant Le nom du vivant.
     * @return Le vivant.
     */
    public Vivant sortirVivant(String nomVivant) {
        int index = Vivant.locateVivantArray(this.vivants, nomVivant);
        Vivant vivant = this.vivants[index];
        this.vivants = Vivant.retirerVivantArray(this.vivants, index);
        vivant.sortir();
        return vivant;
    }

    /** Retire un vivant de la pièce.
     * @param vivant Le vivant.
     * @return Le vivant.
     */
    public Vivant sortirVivant(Vivant vivant) {
        return this.sortirVivant(vivant.getNom());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monde(nom: ");
        sb.append(this.getNom());
        sb.append(", objets: [");
        for (int i = 0; i < this.objets.length; i++) {
            sb.append(this.objets[i].getNom());
            sb.append(", ");
        }
        sb.append(", vivants: [");
        for (int i = 0; i < this.vivants.length; i++) {
            sb.append(this.vivants[i].getNom());
            sb.append(", ");
        }
        sb.append("])");
        return sb.toString();
    }
}
