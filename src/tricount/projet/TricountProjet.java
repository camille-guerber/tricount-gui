/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricount.projet;

/**
 *
 * @author Camille
 */
public class TricountProjet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ecouteur e = new Ecouteur();
        Home h = new Home(e);
        e.enregistrerModele(h);
        h.setVisible(true);
    }
    
}
