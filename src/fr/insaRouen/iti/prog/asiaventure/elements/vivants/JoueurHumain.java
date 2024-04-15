package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import java.util.Scanner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;
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

    private Method getMethod(String commande, List<String> args) throws NoSuchMethodException{
        Class<?>[] argTypes = new Class<?>[args.size()];
        Arrays.fill(argTypes, String.class);
        return this.getClass().getDeclaredMethod(String.format("commande%s", commande), argTypes);
    }

    public void executer() throws CommandeImpossiblePourLeVivantException, Throwable{
        Scanner s = new Scanner(this.ordre);

        String commande = s.next();
        List<String> args = new ArrayList<String>();
        while(s.hasNext()){
            args.add(s.next());
        }

        try{
            Method methode = this.getMethod(commande, args);
            methode.invoke(this, args.toArray());
        } catch(NoSuchMethodException e) {
            throw new CommandeImpossiblePourLeVivantException(String.format("La commande %s n'existe pas", commande));
        } catch(InvocationTargetException e1) {
            throw e1.getTargetException();
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
        Porte porte = (Porte) this.getMonde().getEntite(nomPorte);
        porte.activerAvec(this.getObjet(nomObjet));
    }

    void commandeOuvrirPorte(String nomPorte) throws ActivationException, PorteFermeException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException {
        Porte porte = (Porte) this.getMonde().getEntite(nomPorte);
        porte.activer();
    }
}
