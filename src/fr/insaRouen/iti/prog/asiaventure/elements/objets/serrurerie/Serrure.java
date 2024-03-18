package fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Clef;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
/**
 * Classe représentant une serrure Serrure 
 */
public class Serrure extends Objet implements Activable{
    private Etat etat = Etat.VERROUILLE;
    private static int numero = 0;
    private Clef clef;
    private boolean utilisee = false;

    /**
     * Constructeur de Serrure 
     * 
     */
    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
        this.clef = new Clef(String.format("clef_%d", numero++), monde);
    }

    public Serrure (Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(String.format("serrure_%d", numero++), monde);
        this.clef = new Clef(String.format("clef_%d", numero++), monde);
    }

    public final Clef creerClef() {
        if(this.utilisee){
            return null;
        }
        this.utilisee = true;
        return this.clef;
    }

    public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException {
        if(! activableAvec(objet)){
            throw new ActivationImpossibleAvecObjetException(String.format("Activation impossible entre l'objet %s et la serrure %s", objet.getNom(), this.getNom()));
        }
        if(this.clef.getNom().equals(objet.getNom())){
            this.activer();
        }
    }

    public boolean activableAvec(Objet objet){
        return objet instanceof Clef;
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
}

