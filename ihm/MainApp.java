package ihm;

import gestion.DataStorage;
import gestion.EnfantController;

import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        // Initialisation du DataStorage et EnfantController
        EnfantController enfantController = new EnfantController();
        DataStorage dataStorage = new DataStorage(enfantController);

        // Chargement des données depuis des fichiers CSV (vous pouvez personnaliser les chemins)
        try {
            dataStorage.chargerDonneesParentsDepuisCSV("src/ressources/parents_enfants.csv");
            dataStorage.chargerDonneesEducateursDepuisCSV("src/ressources/educateurs_donnees.csv");
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des données : " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("EnfantPro+");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));

            JLabel welcomeLabel = new JLabel("Bienvenue sur EnfantPro+", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            mainPanel.add(welcomeLabel);

            JButton parentButton = new JButton("Espace Parent");
            JButton educateurButton = new JButton("Espace Éducateur");
            JButton quitButton = new JButton("Quitter");

            // Ajout des actions pour les boutons
            parentButton.addActionListener(e -> {
                LoginIU loginUI = new LoginIU("parent", dataStorage);
                loginUI.setVisible(true);
            });

            educateurButton.addActionListener(e -> {
                LoginIU loginUI = new LoginIU("educateur", dataStorage);
                loginUI.setVisible(true);
            });

            quitButton.addActionListener(e -> System.exit(0));

            mainPanel.add(parentButton);
            mainPanel.add(educateurButton);
            mainPanel.add(quitButton);

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
