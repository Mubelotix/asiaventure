package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import java.util.List;
import java.util.stream.Collectors;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.Executable;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;

public class Monstre extends Vivant implements Executable {
    public Monstre(String nom, Monde monde, int pointsVie, int pointsForce, Piece piece) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointsVie, pointsForce, piece);
    }
    
    public void executer() {
        if (this.estMort()) { return; }

        // Déposer tous ses objets
        for (Objet obj : this.getObjets().values()) {
            try {
                this.deposer(obj);
            } catch(ObjetNonPossedeParLeVivantException e) {
                // Then no need to drop it anyway
            }
        }
        
        // Changer de pièce
        List<Porte> portes = this.getPiece().getPortes().values().stream().filter(p->p.getEtat()==Etat.OUVERT).collect(Collectors.toList());
        Porte porte = portes.get((int) (Math.random() * portes.size()));
        try {
            this.franchir(porte);
        } catch(PorteFermeException e) {
        } catch(PorteInexistanteDansLaPieceException e) {
        }

        // Prendre tous les objets
        for (Objet obj : this.getPiece().getObjets().values()) {
            try {
                this.prendre(obj);
            } catch(ObjetAbsentDeLaPieceException e) {
                // Then no need to drop it anyway
            } catch(ObjetNonDeplacableException e) {

            }
        }

        // Take damage
        this.setPointsVie(this.getPointsVie() - 1);
    }
}
