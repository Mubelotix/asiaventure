package fr.insaRouen.iti.prog.asiaventure.elements.serrurerie;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Clef;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;

public class Serrure extends Objet implements Activable{
    private Etat etat = Etat.VERROUILLE;
    private static int numero = 0;
    private static Clef clef;

    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
    }

    public Serrure (Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(String.format("%d", ++numero), monde);
    }
/*
    public final Clef creerClef(){
        if(this.clef == null){
            this.clef = new Clef("clef", );
        }
    }*/

    public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException{
        if(! activableAvec(objet)){
            throw new ActivationImpossibleAvecObjetException(String.format("Activation impossible entre l'objet %s et la serrure %s", objet.getNom(), this.getNom()));
        }
        if(this.getEtat().equals(Etat.VERROUILLE)){
            this.etat = Etat.DEVEROUILLE;
        }else{
            this.etat = Etat.VEROUILLE;
        }
    }

    public boolean activableAvec(Objet objet){
        return objet instanceof Clef;
    }

    public void activer() throws ActivationImpossibleException{
    
    }

    public boolean estDeplacable(){
        return false;
    }

    public Etat getEtat(){
        return this.etat;
    }




}

