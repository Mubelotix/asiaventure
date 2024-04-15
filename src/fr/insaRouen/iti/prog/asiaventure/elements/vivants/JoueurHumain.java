package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import java.util.Scanner;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;

public class JoueurHumain extends Vivant{
    private String ordre;
    
    public JoueurHumain(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde, pointsVie, pointsForce, piece, objets);
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public void executer() throws CommandeImpossiblePourLeVivantException, Throwable{
        Scanner s = new Scanner(this.ordre);
        try{
            //switch(s.next()){
            //    case "Prendre":
            //        commandePrendre(s.next());
            //    case "Poser":
            //        commandePoser(s.next());
            //    case "Franchir":
            //        commandeOuvrirPorte(s.next());
            //    case "OuvrirPorte":
            //        String nomPorte = s.next();
            //        if(s.hasNext()){
            //            String nomObjet = s.next();
            //            commandeOuvrirPorte(nomPorte, nomObjet);
            //        }else{
            //            commandeOuvrirPorte(nomPorte);
            //        }
            //    default:
            //        throw new CommandeImpossiblePourLeVivantException("cette commande n'existe pas");
            //}
            Method methode = this.getClass().getDeclaredMethod(String.format("commande%s", s.next()));
            List<String> arg = new ArrayList<String>();
            while(s.hasNext()){
                arg.add(s.next());
            }
            methode.invoke(this, arg);

        }catch(Throwable e1){
            System.out.println(String.format("Cause exception : %s", e1.getCause()));
        }

    }

    void commandePrendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        this.prendre(nomObjet);
    }

    void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException{
        this.deposer(nomObjet);
    }

    void commandeFranchir(String nomObjet) throws PorteFermeException, PorteInexistanteDansLaPieceException{
        this.franchir(nomObjet);
    }

    void commandeOuvrirPorte(String nomPorte, String nomObjet) throws ActivationException, PorteFermeException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException {
        this.franchir(nomPorte, nomObjet);
    }

    void commandeOuvrirPorte(String nomPorte) throws ActivationException, PorteFermeException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException {
        this.franchir(nomPorte);
    }
}
