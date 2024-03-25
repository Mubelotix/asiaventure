package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import java.util.List;
import java.util.stream.Collectors;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.Executable;
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
    
    public void executer() throws PorteFermeException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException, ObjetAbsentDeLaPieceException, ActivationImpossibleException {
        if (this.estMort()) { return; }

        // Déposer tous ses objets
        List<String> nomsObj = this.getObjets().keySet().stream().collect(Collectors.toList());
        for (String nom : nomsObj) {
            this.deposer(nom);
        }
        
        // Changer de pièce
        List<Porte> portes = this.getPiece().getPortes().values().stream().filter(p->p.getEtat()!=Etat.VERROUILLE).collect(Collectors.toList());
        if (!portes.isEmpty()) {
            Porte porte = portes.get((int) (Math.random() * portes.size()));
            if (porte.getEtat() == Etat.FERME) {
                porte.casser();
            }
            this.franchir(porte);
        }

        // Take damage
        this.setPointsVie(this.getPointsVie() - 1);
        if (this.estMort()) { return; }

        // Prendre tous les objets
        nomsObj = this.getPiece().getObjets().keySet().stream().collect(Collectors.toList());
        for (String nom : nomsObj) {
            try {
                this.prendre(nom);
            } catch(ObjetNonDeplacableException e) {
                // Laisser l'objet où il est
            }
        }
    }
}
