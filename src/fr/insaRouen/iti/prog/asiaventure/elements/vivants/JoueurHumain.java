package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import java.util.Scanner;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import fr.insaRouen.iti.prog.asiaventure.ASIAventureException;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;
import fr.insaRouen.iti.prog.asiaventure.elements.Executable;

public class JoueurHumain extends Vivant implements Executable {
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
            String nom_methode = String.format("commande%s", s.next());
            List<String> args = new ArrayList<String>();
            while(s.hasNext()){
                args.add(s.next());
            }
            Class<?>[] argTypes = new Class<?>[args.size()];
            Arrays.fill(argTypes, String.class);

            Method methode = this.getClass().getDeclaredMethod(nom_methode, argTypes);

            methode.invoke(this, args.toArray());
<<<<<<< HEAD
        } catch(Throwable e1) {
            System.out.println(e1.getCause().toString());
=======
        }catch(NoSuchMethodException e){
            throw new CommandeImpossiblePourLeVivantException("la commande n'existe pas");
        }catch(Throwable e1) {
            System.out.println(String.format("Cause exception : %s %s", e1, e1.getCause()));
>>>>>>> 0edd6d3 (ajout de exception CommandeImpossiblePourLeVivantException)
        } finally {
            s.close();
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
