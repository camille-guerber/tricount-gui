/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricount.projet;

public class Participant {
    
    public String nom;
    public float totalDepense;
    public float solde;
    
    public Participant(String nom) {
        this.nom = nom;
        this.totalDepense = 0;
        this.solde = 0;
    }
    
    public Participant() {}
}
