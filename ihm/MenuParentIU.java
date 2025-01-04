package ihm;

import gestion.DataStorage;
import modele.Enfant;
import modele.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuParentIU extends JFrame {
    private Parent parent;

    public MenuParentIU(String email, DataStorage dataStorage) {
        this.parent = dataStorage.trouverParentParEmail(email);

        if (parent == null) {
            JOptionPane.showMessageDialog(this, "Erreur : Parent introuvable.");
            dispose();
            return;
        }

        setTitle("Menu Parent - EnfantPro+");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Bienvenue, " + parent.getNom(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Zone centrale
        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton activitiesButton = new JButton("Voir les activités des enfants");
        JButton healthButton = new JButton("Voir les informations santé des enfants");
        JButton reportsButton = new JButton("Voir les bilans");
        JButton logoutButton = new JButton("Retour au menu principal");

        // Actions pour chaque bouton
        activitiesButton.addActionListener(e -> afficherActivites());
        healthButton.addActionListener(e -> afficherSante());
        reportsButton.addActionListener(e -> afficherBilans());
        logoutButton.addActionListener(e -> dispose());

        mainPanel.add(activitiesButton);
        mainPanel.add(healthButton);
        mainPanel.add(reportsButton);
        mainPanel.add(logoutButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void afficherActivites() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        Enfant[] enfants = parent.getEnfants();
        if (enfants == null || enfants.length == 0) {
            textArea.setText("Aucun enfant enregistré.");
        } else {
            for (Enfant enfant : enfants) {
                if (enfant != null) {
                    textArea.append("Enfant : " + enfant.getNom() + "\n");
                    String[] activites = enfant.getActivites();
                    if (activites == null || activites.length == 0) {
                        textArea.append(" - Aucune activité enregistrée.\n");
                    } else {
                        for (String activite : activites) {
                            if (activite != null) {
                                textArea.append(" - Activité : " + activite + "\n");
                            }
                        }
                    }
                    textArea.append("\n");
                }
            }
        }

        afficherPopup("Activités des enfants", textArea);
    }

    private void afficherSante() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        Enfant[] enfants = parent.getEnfants();
        if (enfants == null || enfants.length == 0) {
            textArea.setText("Aucun enfant enregistré.");
        } else {
            for (Enfant enfant : enfants) {
                if (enfant != null) {
                    textArea.append("Enfant : " + enfant.getNom() + "\n");
                    textArea.append(" - Allergies : " + String.join(", ", enfant.getAllergies()) + "\n");
                    textArea.append(" - Régime alimentaire : " + enfant.getRegimeAlimentaire() + "\n\n");
                }
            }
        }

        afficherPopup("Informations santé des enfants", textArea);
    }

    private void afficherBilans() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        Enfant[] enfants = parent.getEnfants();
        if (enfants == null || enfants.length == 0) {
            textArea.setText("Aucun enfant enregistré.");
        } else {
            for (Enfant enfant : enfants) {
                if (enfant != null) {
                    textArea.append("Enfant : " + enfant.getNom() + "\n");
                    String bilan = enfant.getBilan();
                    textArea.append(" - Bilan : " + (bilan == null || bilan.isEmpty() ? "Non disponible" : bilan) + "\n\n");
                }
            }
        }

        afficherPopup("Bilans des enfants", textArea);
    }

    private void afficherPopup(String title, JTextArea content) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);

        JScrollPane scrollPane = new JScrollPane(content);
        dialog.add(scrollPane);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
