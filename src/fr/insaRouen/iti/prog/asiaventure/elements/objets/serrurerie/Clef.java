package fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;

/**
 * Classe représentant une clef
 */
public final class Clef extends Objet {
    private static int numero; // Compteur de clefs pour un nommage automatique unique

    protected Clef(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    /**
     * Crée une clef avec un nom unique
     * @param monde Le monde dans lequel la clef est créée
     * @return La clef créée
     */
    protected static Clef creer(Monde monde) {
        numero++;
        try {
            return new Clef(String.format("clef_%d",numero), monde);
        } catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
            return Clef.creer(monde);
        }
    }

    @Override
    public boolean estDeplacable() {
        return true;
    }

    public String toString() {
        return String.format("Clef(nom: %s, monde: %s)", this.getNom(), this.getMonde().getNom());
    }
}
