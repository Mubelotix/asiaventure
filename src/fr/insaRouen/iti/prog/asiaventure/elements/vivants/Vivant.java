package fr.insaRouen.iti.prog.asiaventure.elements.vivants;


import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;

/**
 * Classe abstraite Vivant, héritant de la classe Entite.
 * Se trouve dans une pièce et dipose d'un équipement.
 */
public /*abstract*/ class Vivant extends Entite {
    int pointsVie;
    int pointsForce;
    Piece piece;
    Objet[] objets;

    /**
     * Crée un vivant avec son nom, un monde, des points de vie, des points de force, une pièce et des objets.
     * @param nom Le nom du vivant.
     * @param monde Le monde du vivant.
     * @param pointsVie Les points de vie du vivant.
     * @param pointsForce Les points de force du vivant.
     * @param piece La pièce où se trouve le vivant.
     * @param objets Les objets que possède le vivant.
     */
    public Vivant(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets) {
        super(nom, monde);
        this.pointsVie = pointsVie;
        this.pointsForce = pointsForce;
        this.piece = piece;
        if (objets == null) {
            this.objets = new Objet[0];
        } else {
            this.objets = Objet.cloneArray(objets);
        }
        piece.entrer(this);
    }

    /** 
     * Fait prendre un objet de sa pièce au vivant.
     * @param nomObjet Le nom de l'objet.
     */
    public void prendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        Objet objet = this.piece.retirer(nomObjet);
        this.objets = Objet.ajouterObjetArray(this.objets, objet);
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
        int index = Objet.locateObjetArray(this.objets, nomObjet);
        if (index == -1) {
            throw new ObjetNonPossedeParLeVivantException(String.format("L'objet %s n'est pas possédé par le vivant %s", nomObjet, this.getNom()));
        }
        Objet objet = this.objets[index];
        this.objets = Objet.retirerObjetArray(this.objets, index);
        this.piece.deposer(objet);
    }

    /**
     * Dépose un objet de son inventaire dans la pièce où il se trouve.
     * @param objet L'objet à déposer.
     */
    public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException {
        this.deposer(objet.getNom());
    }

    /**
     * Récupère tous les objets de l'inventaire du vivant.
     * @return Les objets de l'inventaire du vivant.
     */
    public Objet[] getObjets() {
        return Objet.cloneArray(this.objets);
    }

    /**
     * Récupère la pièce où se trouve le vivant.
     * @return La pièce où se trouve le vivant.
     */
    public Piece getPiece() {
        return this.piece;
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
     * Récupère les points de force du vivant.
     * @return Les points de force du vivant.
     */
    public int getPointsForce() {
        return this.pointsForce;
    }

    /** 
     * Vérifie si le vivant possède un objet.
     * @param nomObjet Le nom de l'objet.
     * @return Vrai si le vivant possède l'objet, faux sinon.
     */
    public boolean contientObjet(String nomObjet) {
        return Objet.contientObjetArray(this.objets, nomObjet);
    }

    /**
     * Vérifie si le vivant possède un objet.
     * @param objet L'objet.
     * @return Vrai si le vivant possède l'objet, faux sinon.
     */
    public boolean contientObjet(Objet objet) {
        return this.contientObjet(objet.getNom());
    }

    // -- Static utility methods --

    /**
     * Ajoute un vivant dans un tableau de vivants.
     */
    public static Vivant[] ajouterVivantArray(Vivant[] vivants, Vivant vivant) {
        if (locateVivantArray(vivants, vivant.getNom()) != -1) {
            return vivants;
        }
        Vivant[] newArray = new Vivant[vivants.length + 1];
        System.arraycopy(vivants, 0, newArray, 0, vivants.length);
        newArray[vivants.length] = vivant;
        return newArray;
    }

    public static int locateVivantArray(Vivant[] vivants, String nomVivant) {
        for (int i = 0; i < vivants.length; i++) {
            if (vivants[i].getNom().equals(nomVivant))
                return i;
        }
        return -1;
    }

    /**
     * Retire un vivant d'un tableau de vivants.
     */
    public static Vivant[] retirerVivantArray(Vivant[] vivants, int index) {
        if (index == -1) {
            return vivants;
        }
        
        // Copy what's before and after the object
        Vivant[] newVivants = new Vivant[vivants.length - 1];
        System.arraycopy(vivants, 0, newVivants, 0, index);
        System.arraycopy(vivants, index + 1, newVivants, index, vivants.length - index - 1);

        return newVivants;
    }

    /**
     * Vérifie si un tableau de vivants contient un vivant.
     */
    public static boolean contientVivantArray(Vivant[] vivants, String nomVivant) {
        for (int i = 0; i < vivants.length; i++) {
            if (vivants[i].getNom().equals(nomVivant))
                return true;
        }
        return false;
    }

    /**
     * Clone un tableau de vivants.
     */
    public static Vivant[] cloneArray(Vivant[] vivants) {
        Vivant[] cloneVivants = new Vivant[vivants.length];
        System.arraycopy(vivants, 0, cloneVivants, 0, vivants.length);
        return cloneVivants;
    }

    public String toString() {
        return String.format("Vivant(%s, %s)", this.getNom(), this.piece.getNom());
    }
}
