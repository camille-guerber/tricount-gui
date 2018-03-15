/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricount.projet;

import java.util.ArrayList;

/**
 *
 * @author Camille
 */
public class Depense {
    public Participant participant;
    public Projet projet;
    public DepenseType type;
    public float montant;
    
    public Depense(Participant p, Projet pr, DepenseType t, float m) {
        this.participant = p;
        this.projet = pr;
        this.type = t;
        this.montant = m;
    }
}
