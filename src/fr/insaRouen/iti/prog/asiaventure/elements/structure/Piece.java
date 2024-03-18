package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;

/** Une pièce est un élément structurel du monde qui contient des objets et des vivants. */
public class Piece extends ElementStructurel {
    private final Map<String, Objet> objets = new HashMap<String, Objet>();
    private final Map<String, Vivant> vivants = new HashMap<String, Vivant>();
    private final Map<String, Porte> portes = new HashMap<String, Porte>();

    /** Crée une pièce avec son nom et un monde.
     * @param nom Le nom de la pièce.
     * @param monde Le monde de la pièce.
     */
    public Piece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    /** Vérifie si la pièce contient un objet.
     * @param nomObjet Le nom de l'objet.
     * @return Vrai si la pièce contient l'objet, faux sinon.
     */
    public boolean contientObjet(String nomObjet) {
        return this.objets.containsKey(nomObjet);
    }

    /** Vérifie si la pièce contient un objet.
     * @param objet L'objet.
     * @return Vrai si la pièce contient l'objet, faux sinon.
     */
    public boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    /**
     * Vérifie si la pièce contient une porte.
     * @param porte La porte.
     * @return Vrai si la pièce contient la porte, faux sinon.
     */
    public boolean aLaPorte(Porte porte) {
        return this.aLaPorte(porte.getNom());
    }

    /**
     * Vérifie si la pièce contient une porte.
     * @param nomPorte Le nom de la porte.
     * @return Vrai si la pièce contient la porte, faux sinon.
     */
    public boolean aLaPorte(String nomPorte) {
        return this.portes.containsKey(nomPorte);
    }

    /** Ajoute une porte à la pièce.
     * @param porte La porte à ajouter.
     */
    protected void addPorte(Porte porte) {
        this.portes.put(porte.getNom(), porte);
    }

    /** Récupère une porte de la pièce.
     * @param nomPorte Le nom de la porte.
     * @return La porte.
     * @throws PorteInexistanteDansLaPieceException Si la porte n'existe pas dans la pièce.
     */
    public Porte getPorte(String nomPorte) throws PorteInexistanteDansLaPieceException {
        if (!this.aLaPorte(nomPorte)) {
            throw new PorteInexistanteDansLaPieceException(String.format("La porte %s n'existe pas dans la piece %s", nomPorte, this.getNom()));
        }
        return this.portes.get(nomPorte);
    }

    /** Vérifie si la pièce contient un vivant.
     * @param nomVivant Le nom du vivant.
     * @return Vrai si la pièce contient le vivant, faux sinon.
     */
    public boolean contientVivant(String nomVivant) {
        return this.vivants.containsKey(nomVivant);
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
        this.objets.put(objet.getNom(), objet);
    }

    /** Ajoute un vivant dans la pièce.
     * @param vivant Le vivant à ajouter.
     */
    public void entrer(Vivant vivant) {
        this.vivants.put(vivant.getNom(), vivant);
        vivant.entrer(this);
    }

    /** Récupère tous les objet de la pièce.
     * @return Les objets de la pièce.
     */
    public Map<String, Objet> getObjets() {
        return Collections.unmodifiableMap(this.objets);
    }

    /** Récupère tous les vivants de la pièce.
     * @return Les vivants de la pièce.
     */
    public Map<String, Vivant> getVivants() {
        return Collections.unmodifiableMap(this.vivants);
    }

    /** Retire un objet de la pièce.
     * @param nomObjet Le nom de l'objet.
     * @return L'objet.
     */
    public Objet retirer(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        Objet objet = this.objets.get(nomObjet);
        if (objet == null) {
            throw new ObjetAbsentDeLaPieceException(String.format("L'objet %s est absent de la pièce %s", nomObjet, this.getNom()));
        }
        if (!objet.estDeplacable()) {
            throw new ObjetNonDeplacableException(String.format("L'objet %s n'est pas déplaçable", nomObjet));
        }
        this.objets.remove(nomObjet);
        return objet;
    }

    /** Retire un objet de la pièce.
     * @param objet L'objet.
     * @return L'objet.
     */
    public Objet retirer(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        return this.retirer(objet.getNom());
    }

    /** Retire un vivant de la pièce.
     * @param nomVivant Le nom du vivant.
     * @return Le vivant.
     */
    public Vivant sortir(String nomVivant) throws VivantAbsentDeLaPieceException {
        Vivant vivant = this.vivants.remove(nomVivant);
        if(vivant == null){
            throw new VivantAbsentDeLaPieceException(String.format("Le vivant %s est absent de la pièce %s", nomVivant, this.getNom()));
        }
        return vivant;
    }

    /** Retire un vivant de la pièce.
     * @param vivant Le vivant.
     * @return Le vivant.
     */
    public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException {
        return this.sortir(vivant.getNom());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Piece(nom: ");
        sb.append(this.getNom());
        sb.append(", objets: [");
        sb.append(String.format("%s", this.objets.keySet()));
        sb.append("], vivants: [");
        sb.append(this.vivants.keySet());
        sb.append("])");
        return sb.toString();
    }
}
