package ihm;

import ihm.AjouterModifierAllergieIU;
import gestion.DataStorage;
import javax.swing.*;
import java.awt.*;

public class MenuEducateurIU extends JFrame {
    public MenuEducateurIU(String email, DataStorage dataStorage) {
        setTitle("Menu Éducateur");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Récupération des informations de l'éducateur
        var educateur = dataStorage.trouverEducateurParEmail(email);
        if (educateur == null) {
            JOptionPane.showMessageDialog(this, "Erreur : Éducateur introuvable.");
            dispose();
            return;
        }

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        JLabel welcomeLabel = new JLabel("Bienvenue, " + educateur.getNom(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(welcomeLabel);

        JButton allergieButton = new JButton("Ajouter ou modifier une allergie");
        JButton santeButton = new JButton("Ajouter ou modifier un problème de santé");
        JButton activitesButton = new JButton("Gérer les activités");
        JButton bilansButton = new JButton("Gérer les bilans");
        JButton retourButton = new JButton("Retour");

        // Bouton pour gérer les allergies
        allergieButton.addActionListener(e -> {
            new AjouterModifierAllergieIU(dataStorage).setVisible(true);
        });

        // Bouton pour gérer les problèmes de santé
        santeButton.addActionListener(e -> {
            new AjouterModifierAllergieIU(dataStorage).setVisible(true);
        });

        // Bouton pour gérer les activités
        activitesButton.addActionListener(e -> {
            new GestionActivitesIU(email, dataStorage).setVisible(true);
        });

        // Bouton pour gérer les bilans
        bilansButton.addActionListener(e -> {
            new GestionBilanIU(email, dataStorage).setVisible(true);
        });

        // Bouton pour retourner au menu principal
        retourButton.addActionListener(e -> dispose());

        panel.add(allergieButton);
        panel.add(santeButton);
        panel.add(activitesButton);
        panel.add(bilansButton);
        panel.add(retourButton);

        add(panel);
    }
}
