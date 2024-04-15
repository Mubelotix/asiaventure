package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import java.util.Scanner;

import java.util.Set;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.ASIAventureException;

import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
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

    public void executer() throws ASIAventureException {
        Scanner s = new Scanner(this.ordre);

        String commande = s.next();
        List<String> args = new ArrayList<String>();
        while(s.hasNext()){
            args.add(s.next());
        }

        try{
            Method methode = this.getMethod(commande, args);
            methode.invoke(this, args.toArray());
        } catch(InvocationTargetException e1) {
            throw (ASIAventureException)e1.getTargetException();
        } catch(Exception e) {
            throw new CommandeImpossiblePourLeVivantException(String.format("La commande %s ne peut pas être utilisée", commande));
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Joueur ");
        sb.append(this.getNom());

        Set<String> objets = this.getObjets().keySet();
        if (objets.size() > 0) {
            sb.append(" portant ");
            for (String objet : objets) {
                sb.append(objet);
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        sb.append(" dans ");
        sb.append(this.getPiece().getNom());

        Collection<Objet> objetsPiece = this.getPiece().getObjets();
        if (objetsPiece.size() > 0) {
            sb.append(" contenant ");
            for (Objet objet : objetsPiece) {
                sb.append(objet.getNom());
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        Collection<Porte> portes = this.getPiece().getPortes();
        if (portes.size() > 0) {
            sb.append(" avec les portes ");
            for (Porte porte : portes) {
                sb.append(porte.getNom());
                Etat etat = porte.getEtat();
                sb.append("[");
                sb.append(etat);
                sb.append("]");
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
