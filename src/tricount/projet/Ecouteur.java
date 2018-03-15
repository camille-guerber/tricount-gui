/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricount.projet;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Camille
 */
public class Ecouteur implements ActionListener {
    public Home h;
   
    public Ecouteur(){};
   
    public void enregistrerModele(Home h) {
        this.h = h;
    }

    public void actionPerformed(ActionEvent e) {
        // Onglet 1, création d'un projet
        if(e.getActionCommand() == "create_project") {
            if(h.tf1.getText().length() > 0) {
                // Création de l'objet projet
                Projet p = new Projet(h.tf1.getText());
                Projet.Projets.add(p);
                System.out.println("Projet crée : "+p.nom);
                this.refreshProjectList();
                this.refreshTextFields();
            } else {
                JOptionPane.showMessageDialog(h, "Veuillez renseigner un nom de projet");
            }
        // Onglet 1, suppression d'un projet
        } else if(e.getActionCommand() == "delete_project") {
            if(h.cb1.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un projet à détruire");
            } else {
                Projet p = this.chercherProjet(h.cb1.getSelectedItem().toString());
                Projet.Projets.remove(p);
                System.out.println("Projet supprimé : "+p.nom);
                this.refreshProjectList();
            }
        // Onglet 2, sélection d'un projet pour onglet gestion des participants
        } else if(e.getActionCommand() == "selectionner_projet") {
            if(h.cb1.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un projet");
            } else {
                Projet p = this.chercherProjet(h.cb7.getSelectedItem().toString());
                h.cb2.removeAllItems();
                for(Participant pt: p.Participants) {
                   h.cb2.addItem(pt.nom);
                }
            }
        // Onglet 2, ajout d'un participant pour un projet
        } else if(e.getActionCommand() == "ajouter_participants") {
            if(h.tf2.getText().length() > 0) {
                Participant p = new Participant(h.tf2.getText());
                this.chercherProjet(h.cb7.getSelectedItem().toString()).Participants.add(p);
                System.out.println("Ajout d'un participant : "+this.chercherProjet(h.cb7.getSelectedItem().toString()).nom+" Nom du participant : "+p.nom);
                h.cb2.removeAllItems();
                for(Participant pt: this.chercherProjet(h.cb7.getSelectedItem().toString()).Participants) {
                    h.cb2.addItem(pt.nom);
                }
                this.refreshTextFields();
            } else {
                JOptionPane.showMessageDialog(h, "Veuillez renseigner un nom de participant");
            }
        // Onglet 2, suppression d'un participant pour un projet
        } else if(e.getActionCommand() == "supprimer_participant") {
            if(h.cb2.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un participant");
            } else {
                String participant = h.cb2.getSelectedItem().toString();
                String projet = h.cb7.getSelectedItem().toString();
                h.cb2.removeItemAt(h.cb2.getSelectedIndex());
                for(Participant p: this.chercherProjet(projet).Participants) {
                    if(p.nom == participant) {
                        this.chercherProjet(projet).Participants.remove(p);
                        break;
                    }
                }
            }
        // Onglet 3, selection d'un projet pour gestion des dépenses
        } else if (e.getActionCommand() == "selectionner_projet2") {
            if(h.cb8.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un projet");
            } else {
                h.cb3.removeAllItems();
                h.cb4.removeAllItems();
                String index = h.cb8.getSelectedItem().toString();
                Projet p = this.chercherProjet(h.cb8.getSelectedItem().toString());
                for(Participant participant: p.Participants) {
                    h.cb3.addItem(participant.nom);
                }
                for(DepenseType dt: p.DepensesType) {
                    h.cb4.addItem(dt.nom);
                }
            }
        // Onglet 3, ajpout d'un type de dépense pour un projet
        } else if(e.getActionCommand() == "ajouter_type_depense") {
            if(h.tf3.getText().length() > 0) {
                DepenseType d = new DepenseType(h.tf3.getText());
                this.chercherProjet(h.cb8.getSelectedItem().toString()).DepensesType.add(d);
                h.cb4.addItem(d.nom);
                this.refreshTextFields();
            } else {
                JOptionPane.showMessageDialog(h, "Veuillez renseigner un nom de type de dépense");
            }
        // Onglet 3, ajout d'une dépense pour un projet
        } else if(e.getActionCommand() == "ajouter_depense") {
            if(h.tf4.getText().length() > 0) {
                if(h.cb8.getSelectedIndex() == -1 || h.cb3.getSelectedIndex() == -1 || h.cb4.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(h, "Veuillez sélectionner un projet, participant & un type de dépense");
                } else {
                    Float montant = Float.parseFloat(h.tf4.getText());
                    Projet p = chercherProjet(h.cb8.getSelectedItem().toString());
                    DepenseType dt = chercherDepenseType(h.cb4.getSelectedItem().toString(), p);
                    Participant pt = chercherParticipant(h.cb3.getSelectedItem().toString(), p);

                    System.out.println("Récapitulatif de la dépense : ");
                    System.out.println("Projet : "+p.nom);
                    System.out.println("Participant : "+pt.nom);
                    System.out.println("Type dépense : "+dt.nom);
                    System.out.println("Montant : "+montant+" €");

                    Depense de = new Depense(pt, p, dt, montant);
                    de.participant.totalDepense += de.montant;
                    p.Depenses.add(de);
                    h.tf4.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(h, "Veuillez renseigner un montant");
            }
        // Onglet 4, sélection d'un projet pour gestion individuelle
        } else if(e.getActionCommand() == "selectionner_projet3") {
            if(h.cb8.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un projet");
            } else {
                h.cb5.removeAllItems();
                Projet p = this.chercherProjet(h.cb8.getSelectedItem().toString());
                for(Participant participant: p.Participants) {
                    h.cb5.addItem(participant.nom);
                }

            }
        // Onglet 4, sélection d'un participant
        } else if(e.getActionCommand() == "selectionner_participant") {
            if(h.cb5.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un participant");
            } else {
                Projet p = this.chercherProjet(h.cb9.getSelectedItem().toString());
                h.cb10.removeAllItems();
                for(Depense d: p.Depenses) {
                    if(d.participant.nom == h.cb5.getSelectedItem().toString()) {
                        h.cb10.addItem(d.type.nom);
                    }
                }
                
            }
        // Onglet 4, suppression d'un dépense pour un participant
        } else if(e.getActionCommand() == "supprimer_depense") {
            if(h.cb10.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner une dépense");
            } else {
                Projet p = this.chercherProjet(h.cb9.getSelectedItem().toString());
                Participant pt = this.chercherParticipant(h.cb5.getSelectedItem().toString(), p);
                for(Depense d: p.Depenses) {
                    if(d.projet.nom == p.nom && d.participant.nom == pt.nom && d.type.nom == h.cb10.getSelectedItem().toString()) {
                        p.Depenses.remove(d);
                        d.participant.totalDepense -= d.montant;
                        h.cb10.removeItem(d.type.nom);
                        break;
                    }
                }
            }
        // Onglet 4, Bilan chiffré pour un participant
        } else if(e.getActionCommand() == "bilan_chiffré") {
            if(h.cb9.getSelectedIndex() == -1 || h.cb5.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un participant");
            } else {
                h.label11.setText("");
                h.label11.setText(
                    "<html>\n"+
                        "<table border=1>\n"+
                            "<tr>\n"+
                                "<th>Montant (€)</th>\n"+
                                "<th>Type de dépense</th>\n"+
                            "</tr>"
                );
                for(Depense d: this.chercherProjet(h.cb9.getSelectedItem().toString()).Depenses) {
                    if(h.cb5.getSelectedItem().toString() == d.participant.nom) {
                       h.label11.setText(h.label11.getText()+"<tr><td>"+d.montant+" €</td>"+"<td>"+d.type.nom+"</td>"); 
                    }
                }
                h.label11.setText(h.label11.getText()+"</tr></table></html>");
            }
            
        // Onglet 1, Equilibre d'un projet
        } else if(e.getActionCommand() == "projet_equilibre") {
            if(h.cb7.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un projet pour l'équilibre");
            } else {
                Projet p = this.chercherProjet(h.cb7.getSelectedItem().toString());
                float totalD = 0;
                int totalP = p.Participants.size();
                for(Depense d: p.Depenses) {
                    totalD += d.montant;
                }
                
                h.label13.setText("");
                h.label13.setText(
                    "<html>"+
                        "<table style='font-family:Trebuchet MS;border-collapse:collapse;width:100%;'>"+
                            "<tr>"+
                                "<th style='background-color:#96a096;padding:8px;'>Qui ?</th>"+
                                "<th style='background-color:#96a096;padding:8px;'>Doit</th>"+
                                "<th style='background-color:#96a096;padding:8px;'>A qui ?</th>"+
                            "</tr>"
                );
                
                float moyenneD = totalD/totalP;
                for(Participant pt: p.Participants) {
                    pt.solde = pt.totalDepense - moyenneD;
                }
                
                ArrayList<Participant> pts = new ArrayList<Participant>();
                pts = p.Participants;
                
                NumberFormat nf = new DecimalFormat("0.##");
                for(int i = 0; i < pts.size(); i++) {
                    for(int y = i+1; y < pts.size(); y++) {
                            if(pts.get(i).solde > 0 && pts.get(y).solde < 0) {
                                float s = 0;
                                // P2 peut rembourser toute sa dette
                                if(Math.abs(pts.get(i).solde) > Math.abs(pts.get(y).solde)) {
                                    s = Math.abs(pts.get(y).solde);
                                    pts.get(i).solde -= s;
                                    pts.get(y).solde = 0;
                                // P2 ne peut pas rembourser la totalité de sa dette
                                } else if(Math.abs(pts.get(i).solde) < Math.abs(pts.get(y).solde)){
                                    s = Math.abs(pts.get(i).solde);
                                    pts.get(i).solde = 0;
                                    pts.get(y).solde += s;
                                // Egalité des soldes
                                } else if(Math.abs(pts.get(i).solde) == Math.abs(pts.get(y).solde)) {
                                    s = Math.abs(pts.get(i).solde);
                                    pts.get(i).solde = 0;
                                    pts.get(y).solde = 0;
                                }
                                h.label13.setText(h.label13.getText()+"<tr><td>"+pts.get(y).nom+"</td>"+"<td>"+nf.format(s)+"</td>"+"<td>"+pts.get(i).nom+"</td></tr>");
                            } else if(pts.get(y).solde > 0 && pts.get(i).solde < 0) {
                                float s = 0;
                                // P1 peut rembourser toute sa dette
                                if(Math.abs(pts.get(y).solde) > Math.abs(pts.get(i).solde)) {
                                    s = Math.abs(pts.get(i).solde);
                                    pts.get(y).solde -= s;
                                    pts.get(i).solde = 0;
                                // P1 ne peut pas rembourser la totalité de sa dette
                                } else if(Math.abs(pts.get(y).solde) < Math.abs(pts.get(i).solde)){
                                    s = Math.abs(pts.get(y).solde);
                                    pts.get(y).solde = 0;
                                    pts.get(i).solde += s;
                                // Egalité des soldes
                                } else if(Math.abs(pts.get(y).solde) == Math.abs(pts.get(i).solde)) {
                                    s = Math.abs(pts.get(y).solde);
                                    pts.get(y).solde = 0;
                                    pts.get(i).solde = 0;
                                }
                                h.label13.setText(h.label13.getText()+"<tr><td>"+pts.get(i).nom+"</td>"+"<td>"+nf.format(s)+"</td>"+"<td>"+pts.get(y).nom+"</td></tr>");
                            }
                            
                    }

                }
                h.label13.setText(h.label13.getText()+"</tr></table></html>");
            }

        // Onglet 1, Bilan graphique d'un projet
        } else if(e.getActionCommand() == "projet_bilan_graphique") {
            if(h.cb7.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(h, "Veuillez sélectionner un projet pour le bilan");
            } else {
                Projet p = this.chercherProjet(h.cb7.getSelectedItem().toString());
                NumberFormat nf = new DecimalFormat("0.##");
                float totalD = 0;
                int totalP = p.Participants.size();
                for(Depense d: p.Depenses) {
                    totalD += d.montant;
                }
                
                h.label12.setText("");
                h.label12.setText(
                    "<html>"+
                        "<table style='font-family:Trebuchet MS;border-collapse:collapse;width:100%;'>"+
                            "<tr>"+
                                "<th style='background-color:#96a096;padding:8px;'>Participant</th>"+
                                "<th style='background-color:#96a096;padding:8px;'>Solde</th>"+
                            "</tr>"
                );
                float moyenneD = totalD/totalP;
                for(Participant pt: p.Participants) {
                    pt.solde = pt.totalDepense - moyenneD;
                    if(pt.solde >= 0) {
                        h.label12.setText(h.label12.getText()+"<tr><td style='background-color:#80c380;padding:8px;border: 1px solid #ddd;'>"+pt.nom+"</td><td style='background-color:#80c380;padding:8px;border: 1px solid #ddd;'>"+nf.format(pt.solde)+"€</td>"); 
                    } else {
                        h.label12.setText(h.label12.getText()+"<tr><td style='background-color:#cd4747;padding:8px;border: 1px solid #ddd;'>"+pt.nom+"</td><td style='background-color:#cd4747;padding:8px;border: 1px solid #ddd;'>"+nf.format(pt.solde)+"€</td>"); 
                    }
                }
                
                h.label12.setText(h.label12.getText()+"</tr></table></html>");

            }     
        }
    }
    
    public void refreshTextFields() {
        h.tf1.setText("");
        h.tf2.setText("");
        h.tf3.setText("");
        h.tf4.setText("");
    }
    
    public void refreshProjectList() {
        h.cb1.removeAllItems();
        h.cb7.removeAllItems();
        h.cb8.removeAllItems();
        h.cb9.removeAllItems();
        for(Projet p: Projet.Projets) {
            h.cb1.addItem(p.nom);
            h.cb9.addItem(p.nom);
            h.cb7.addItem(p.nom);
            h.cb8.addItem(p.nom);
        }
    }
    
    public Projet chercherProjet(String nom) {
        for(Projet projet: Projet.Projets) {
            if(projet.nom == nom) {
                return projet;
            }
        }
        return null;
    }
    
    public Participant chercherParticipant(String nom, Projet projet) {
        for(Participant p: projet.Participants) {
            if(p.nom == nom) {
                return p;
            }
        }
        return null;
    }
    
    public DepenseType chercherDepenseType(String nom, Projet projet) {
        for(DepenseType d: projet.DepensesType) {
            if(d.nom == nom) {
                return d;
            }
        }
        return null;
    }
}
