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
public class Projet {
    public static ArrayList<Projet> Projets = new ArrayList<Projet>();
    public ArrayList<Participant> Participants = new ArrayList<Participant>();
    public ArrayList<Depense> Depenses = new ArrayList<Depense>();
    public ArrayList<DepenseType> DepensesType = new ArrayList<DepenseType>();
    public int nbParticipants;
    public String nom;
    
    public Projet(String nom) {
        this.nom = nom;
        this.nbParticipants = 0;
    }
}
