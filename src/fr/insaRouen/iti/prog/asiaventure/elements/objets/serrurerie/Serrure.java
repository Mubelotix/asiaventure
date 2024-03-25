package fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;

/**
 * Classe représentant une serrure 
 */
public class Serrure extends Objet implements Activable{
    private Etat etat = Etat.VERROUILLE;
    private static int numero = 0;
    private Clef clef;
    private boolean utilisee = false;

    /**
     * Constructeur de Serrure 
     * @param Monde le monde de la serrure
     * @param String le nom de la serrure
     */
    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
        this.clef = Clef.creer(this.monde);
    }

    /**
     * Constructeur de Serrure avec un nom automatique
     * @param Monde le monde de la serrure
     */

    public Serrure (Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(String.format("serrure_%d", numero), monde);
        this.clef = Clef.creer(this.monde);
        ++numero;
    }

    /**
     * Obtenir la clef d'une serrure une unique fois
     * @return la clef nouvellement créée 
     */
    public final Clef creerClef() {
        if(this.utilisee){
            return null;
        }
        this.utilisee = true;
        return this.clef;
    }

    /**
     * Activer une serrure avec un objet
     * @param Objet l'objet permettant peut être de déverouiller/vérouiller la serrure
     */
    public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException {
        if(! activableAvec(objet)){
            throw new ActivationImpossibleAvecObjetException(String.format("Activation impossible entre l'objet %s (ca aurait du etre %s )et la serrure %s", objet.getNom(), this.clef.getNom(),this.getNom()));
        }
        this.activer();
    }

    /**
     * Vérifier si l'objet permet de vérouiller/déverrouiller la serrure
     * @param Objet l'objet permettant peut être de déverouiller/vérouiller la serrure
     * @return la vie
     */

    public boolean activableAvec(Objet objet){
        return (objet instanceof Clef) && (this.clef != null) && this.clef.getNom().equals(objet.getNom());
    }


    public void activer(){
        if(this.getEtat().equals(Etat.VERROUILLE)){
            this.etat = Etat.DEVERROUILLE;
        }else{
            this.etat = Etat.VERROUILLE;
        }
    }

    public boolean estDeplacable(){
        return false;
    }

    public Etat getEtat(){
        return this.etat;
    }

    public Monde getMonde(){
        return this.monde;
    }

}

