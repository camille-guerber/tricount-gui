package tricount.projet;
import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    // Objet onglet
    JTabbedPane jtp = new JTabbedPane();
    
    // Onglet 1 (Gestion des projets)
    JPanel jp1 = new JPanel();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JTextField tf1 = new JTextField();
    JComboBox cb1 = new JComboBox();
    JButton bt1 = new JButton("Créer");
    JButton bt2 = new JButton("Détruire");
    JButton bt3 = new JButton("Equilibre");
    JButton bt4 = new JButton("Bilan graphique");
    JLabel label12 = new JLabel();
    JLabel label13 = new JLabel();
    
    // Onglet 2 (Gestion des participants)
    JLabel label7 = new JLabel();
    JComboBox cb7 = new JComboBox();
    JButton bt11 = new JButton("Sélectionner");
    JPanel jp2 = new JPanel();
    JLabel label3 = new JLabel();
    JTextField tf2 = new JTextField();
    JButton bt5 = new JButton("Ajouter");
    JButton bt6 = new JButton("Supprimer");
    JComboBox cb2 = new JComboBox();
    
    // Onglet 3 (Gestion des dépenses)
    JLabel label8 = new JLabel();
    JComboBox cb8 = new JComboBox();
    JButton bt12 = new JButton("Sélectionner");
    JPanel jp3 = new JPanel();
    JLabel label4 = new JLabel();
    JTextField tf3 = new JTextField();
    JButton bt7 = new JButton("Ajouter");
    JLabel label5 = new JLabel();
    JTextField tf4 = new JTextField();
    JComboBox cb3 = new JComboBox();
    JButton bt8 = new JButton("Ajouter");
    JComboBox cb4 = new JComboBox();
    
    // Onglet 4 (Gestion individuelle)
    JLabel label9 = new JLabel();
    JComboBox cb9 = new JComboBox();
    JButton bt13 = new JButton("Sélectionner");
    JPanel jp4 = new JPanel();
    JLabel label6 = new JLabel();
    JComboBox cb5 = new JComboBox();
    JButton bt9 = new JButton("Supprimer");
    JButton bt10 = new JButton("Bilan chiffré");
    JButton bt14 = new JButton("Sélectionner");
    JLabel label10 = new JLabel();
    JComboBox cb10 = new JComboBox();
    JLabel label11 = new JLabel();
    
    
    public Home(Ecouteur e) {
        
        // Paramètre de la frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Projet Tricount");
        setSize(new Dimension(1000,500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // Objet onglet
        getContentPane().add(jtp);
        
        // Onglet 1 (Gestion des projets)
        label1.setText("Nom du projet :");
        label2.setText("Liste des projets :");
        tf1.setColumns(10);
        bt1.addActionListener(e);
        bt1.setActionCommand("create_project");
        bt2.addActionListener(e);
        bt2.setActionCommand("delete_project");
        bt3.addActionListener(e);
        bt3.setActionCommand("projet_equilibre");
        bt4.addActionListener(e);
        bt4.setActionCommand("projet_bilan_graphique");
        jp1.add(label1);jp1.add(tf1);jp1.add(bt1);jp1.add(label2);jp1.add(cb1);jp1.add(bt2);jp1.add(bt3);jp1.add(bt4);jp1.add(label12);jp1.add(label13);
        
        // Onglet 2 (Gestion des participants)
        bt11.addActionListener(e);
        bt11.setActionCommand("selectionner_projet");
        label7.setText("Projets :");
        label3.setText("Nom du participant :");
        tf2.setColumns(10);
        bt5.addActionListener(e);
        bt5.setActionCommand("ajouter_participants");
        bt6.addActionListener(e);
        bt6.setActionCommand("supprimer_participant");
        jp2.add(label7);jp2.add(cb7);jp2.add(bt11);jp2.add(label3);jp2.add(tf2);jp2.add(bt5);jp2.add(cb2);jp2.add(bt6);
        
        // Onglet 3 (Gestion des dépenses)
        label8.setText("Projets :");
        bt12.addActionListener(e);
        bt12.setActionCommand("selectionner_projet2");
        label4.setText("Type de dépense :");
        tf3.setColumns(10);
        bt7.addActionListener(e);
        bt7.setActionCommand("ajouter_type_depense");
        label5.setText("Montant :");
        tf4.setColumns(10);
        bt8.addActionListener(e);
        bt8.setActionCommand("ajouter_depense");
        jp3.add(label8);jp3.add(cb8);jp3.add(bt12);jp3.add(label4);jp3.add(tf3);jp3.add(bt7);jp3.add(label5);jp3.add(tf4);jp3.add(cb3);jp3.add(cb4);jp3.add(bt8);
        
        // Onglet 4 (Gestion individuelle)
        label9.setText("Projets :");
        label6.setText("Participants :");
        bt13.addActionListener(e);
        bt13.setActionCommand("selectionner_projet3");
        bt14.addActionListener(e);
        bt14.setActionCommand("selectionner_participant");
        bt9.addActionListener(e);
        bt9.setActionCommand("supprimer_depense");
        bt10.addActionListener(e);
        bt10.setActionCommand("bilan_chiffré");
        label10.setText("Dépenses");
        jp4.add(label9);jp4.add(cb9);jp4.add(bt13);jp4.add(label6);jp4.add(cb5);jp4.add(bt14);jp4.add(label10);jp4.add(cb10);jp4.add(bt9);jp4.add(bt10);jp4.add(label11);
        
        // Ajout des onglets dans tabbedPane
        jtp.addTab("Gestion des projets", jp1);
        jtp.addTab("Gestion des participants", jp2);
        jtp.addTab("Gestion des dépenses", jp3);
        jtp.addTab("Gestion individuelle", jp4);
        
    }
   
}
