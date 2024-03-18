package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import java.util.List;
import java.util.stream.Collectors;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.Executable;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;

public class Monstre extends Vivant implements Executable {
    public Monstre(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointsVie, pointsForce, piece);
    }
    
    public void executer() {
        if (this.estMort()) { return; }

        // Déposer tous ses objets
        List<String> nomsObj = this.getObjets().keySet().stream().collect(Collectors.toList());
        for (String nom : nomsObj) {
            try {
                this.deposer(nom);
            } catch(ObjetNonPossedeParLeVivantException e) {
                // Then no need to drop it anyway
            }
        }
        
        // Changer de pièce
        List<Porte> portes = this.getPiece().getPortes().values().stream().filter(p->p.getEtat()==Etat.OUVERT).collect(Collectors.toList());
        if (!portes.isEmpty()) {
            Porte porte = portes.get((int) (Math.random() * portes.size()));
            try {
                this.franchir(porte);
            } catch(PorteFermeException e) {
            } catch(PorteInexistanteDansLaPieceException e) {
            }
        }

        // Take damage
        this.setPointsVie(this.getPointsVie() - 1);
        if (this.estMort()) { return; }

        // Prendre tous les objets
        nomsObj = this.getPiece().getObjets().keySet().stream().collect(Collectors.toList());
        for (String nom : nomsObj) {
            try {
                this.prendre(nom);
            } catch(ObjetAbsentDeLaPieceException e) {
                // Then no need to drop it anyway
            } catch(ObjetNonDeplacableException e) {

            }
        }
    }
}
