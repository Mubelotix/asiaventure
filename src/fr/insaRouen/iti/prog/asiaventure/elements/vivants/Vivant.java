package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;

/**
 * Classe abstraite Vivant, héritant de la classe Entite.
 * Se trouve dans une pièce et dipose d'un équipement.
 */
public /*abstract*/ class Vivant extends Entite {
    private int pointsVie;
    private int pointsForce;
    private Piece piece;
    private final Map<String, Objet> objets = new HashMap<String, Objet>();

    /**
     * Crée un vivant avec son nom, un monde, des points de vie, des points de force, une pièce et des objets.
     * @param nom Le nom du vivant.
     * @param monde Le monde du vivant.
     * @param pointsVie Les points de vie du vivant.
     * @param pointsForce Les points de force du vivant.
     * @param piece La pièce où se trouve le vivant.
     * @param objets Les objets que possède le vivant.
     */
    public Vivant(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.pointsVie = pointsVie;
        this.pointsForce = pointsForce;
        this.piece = piece;
        if (objets != null) {
            for (Objet objet : objets) {
                this.objets.put(objet.getNom(), objet);
            }
        }
        piece.entrer(this);
    }

    /** 
     * Fait prendre un objet de sa pièce au vivant.
     * @param nomObjet Le nom de l'objet.
     */
    public void prendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        Objet objet = this.piece.retirer(nomObjet);
        this.objets.put(objet.getNom(), objet);
    }

    /**
     * Fait prendre un objet de sa pièce au vivant.
     * @param objet L'objet.
     */
    public void prendre(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        prendre(objet.getNom());
    }

    /**
     * Dépose un objet de son inventaire dans la pièce où il se trouve.
     * @param nomObjet Le nom de l'objet à déposer.
     */
    public void deposer(String nomObjet) throws ObjetNonPossedeParLeVivantException {
        try {
            Objet objet = this.objets.remove(nomObjet);
            this.piece.deposer(objet);
        } catch (NullPointerException e) {
            throw new ObjetNonPossedeParLeVivantException(String.format("L'objet %s n'est pas possédé par le vivant %s", nomObjet, this.getNom()));
        }
    }

    /**
     * Dépose un objet de son inventaire dans la pièce où il se trouve.
     * @param objet L'objet à déposer.
     */
    public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException {
        this.deposer(objet.getNom());
    }

    /**
     * Fait franchir une porte au vivant.
     * @param nomPorte Le nom de la porte à franchir.
     * @throws PorteFermeException Si la porte est fermée.
     * @throws PorteInexistanteDansLaPieceException Si la porte n'existe pas dans la pièce.
     */
    public void franchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
        Porte porte = this.piece.getPorte(nomPorte);
        if (!porte.getEtat().equals(Etat.OUVERT)) {
            throw new PorteFermeException();
        }
        Piece piece2 = porte.getPieceAutreCote(this.piece);
        this.entrer(piece2);
    }

    public void franchir(String nomPorte, String nomObjet) throws PorteFermeException, PorteInexistanteDansLaPieceException, ActivationException {
        Porte porte = this.piece.getPorte(nomPorte);
        Objet obj = this.getObjet(nomObjet);
        if (!porte.getEtat().equals(Etat.OUVERT)) {
            throw new PorteFermeException();
        }
        porte.activerAvec(obj);
        Piece piece2 = porte.getPieceAutreCote(this.piece);
        this.entrer(piece2);
    }

    /**
     * Fait franchir une porte au vivant.
     * @param porte La porte à franchir.
     * @throws PorteFermeException Si la porte est fermée.
     * @throws PorteInexistanteDansLaPieceException Si la porte n'existe pas dans la pièce.
     */
    public void franchir(Porte porte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
        this.franchir(porte.getNom());
    }

    /**
     * Récupère la pièce où se trouve le vivant.
     * @return La pièce où se trouve le vivant.
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Active un élément activable.
     * @param activable L'élément activable à activer.
     * @throws ActivationException Si l'activation est impossible.
     */
    public void activerActivable(Activable activable) throws ActivationException {
        activable.activer();
    }

    /**
     * Active un élément activable avec un objet.
     * @param activable L'élément activable à activer.
     * @param objet L'objet avec lequel activer l'élément activable.
     * @throws ActivationException Si l'activation est impossible.
     */
    public void activerActivableAvecObjet(Activable activable, Objet objet) throws ActivationException {
        activable.activableAvec(objet);
    }

    /**
     * Récupère tous les objets de l'inventaire du vivant.
     * @return Les objets de l'inventaire du vivant.
     */
    public Map<String, Objet> getObjets() {
        return Collections.unmodifiableMap(this.objets);
    }
    
    /**
     * Récupère un objet de l'inventaire du vivant.
     * @param nomObjet Le nom de l'objet.
     * @return L'objet.
     */
    public Objet getObjet(String nomObjet) {
        return this.objets.get(nomObjet);
    }

    /**
     * Indique si le vivant possède un objet.
     * @param obj L'objet.
     * @return Vrai si le vivant possède l'objet, faux sinon.
     */
    public boolean possede(Objet obj) {
        return this.objets.containsKey(obj.getNom());
    }

    /**
     * Fais quitter la pièce au vivant. Il peut alors se trouver dans les limbes.
     */
    public void sortir() {
        if (this.piece != null && this.piece.contientVivant(this.getNom())) {
            try {
                this.piece.sortir(this.getNom());
            } catch (VivantAbsentDeLaPieceException e) {
                // Already out, so we can ignore it safely
            }
        }
        this.piece = null;
    }

    /**
     * Fait entrer le vivant dans une pièce.
    */
    public void entrer(Piece piece) {
        if (this.piece == piece) {
            return;
        }
        if (this.piece != null) {
            try {
                this.piece.sortir(this.getNom());
            } catch (VivantAbsentDeLaPieceException e) {
                // Already out, so we can ignore it safely
            }
        }
        this.piece = piece;
        piece.entrer(this);
    }

    /**
     * Récupère les points de vie du vivant.
     * @return Les points de vie du vivant.
     */
    public int getPointsVie() {
        return this.pointsVie;
    }

    /**
     * Modifie les points de vie du vivant.
     * @param pointsVie Les nouveaux points de vie du vivant.
     */
    public void setPointsVie(int pointsVie) {
        this.pointsVie = pointsVie;
    }

    /**
     * Récupère les points de force du vivant.
     * @return Les points de force du vivant.
     */
    public int getPointsForce() {
        return this.pointsForce;
    }

    /**
     * Récupère si le vivant est mort.
     * @return Vrai si le vivant est mort, faux sinon.
     */
    public boolean estMort() {
        return this.pointsVie <= 0;
    }

    public String toString() {
        if (this.piece == null) {
            return String.format("Vivant(%s, null)", this.getNom());
        } else {
            return String.format("Vivant(%s, %s)", this.getNom(), this.piece.getNom());
        }
    }
}
