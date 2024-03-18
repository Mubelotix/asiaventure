package fr.insaRouen.iti.prog.asiaventure.elements.serrurerie;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Clef;

public class Serrure extends Objet implements Activable{
    private Etat etat = Etat.VERROUILLE;
    private static int numero = 0; 

    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
    }

    public Serrure (Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(String.format("%d", ++numero), monde);
    }

    public boolean activableAvec(Objet objet){
        if(objet instanceof PiedDeBiche){

        }
        return true;
    }

    public void activableAvec(Objet objet){
    }

    public void activer(){
    }

    public boolean estDeplacable(){
        return true;
    }

    public Etat getEtat(){
        return this.etat;
    }




}

