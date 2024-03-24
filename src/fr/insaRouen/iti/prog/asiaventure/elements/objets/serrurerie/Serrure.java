package fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Clef;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;

import java.util.Set;

/**
 * Classe représentant une serrure 
 */
public class Serrure extends Objet implements Activable{
    private Etat etat = Etat.VERROUILLE;
    private static int numero = 0;
    private Clef clef;
    private static String chercherNom ;
    private boolean utilisee = false;

    /**
     * Constructeur de Serrure 
     * 
     */
    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
        this.clef = creerClef();
    }

    public Serrure (Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(String.format("serrure_%d", numero), monde);
        this.clef = creerClef();
        ++numero;

    }

    public Clef getClef(){
        return this.clef;
    }

    public String trouverNomOriginal(){
        chercherNom = String.format("cle_%d", numero);
        while(this.getMonde().getAllNomsEntites().contains(chercherNom)){
            chercherNom = String.format("cle_%d", ++numero);

        }
        return chercherNom;
    }

    public final Clef creerClef() throws NomDEntiteDejaUtiliseDansLeMondeException{
        if(this.utilisee){
            return null;
        }
        this.utilisee = true;
        this.clef = new Clef(this.trouverNomOriginal(), this.getMonde());
        return this.clef;
    }

    public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException {
        if(! activableAvec(objet)){
            throw new ActivationImpossibleAvecObjetException(String.format("Activation impossible entre l'objet %s (ca aurait du etre %s )et la serrure %s", objet.getNom(), this.clef.getNom(),this.getNom()));
        }
        this.activer();
    }

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

