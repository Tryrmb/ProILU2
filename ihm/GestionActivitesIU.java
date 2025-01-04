package ihm;

import gestion.DataStorage;
import modele.Enfant;

import javax.swing.*;
import java.awt.*;

public class GestionActivitesIU extends JFrame {
    public GestionActivitesIU(String emailEducateur, DataStorage dataStorage) {
        setTitle("Gestion des Activités");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea activitesTextArea = new JTextArea();
        activitesTextArea.setEditable(false);

        // Récupération des informations de l'éducateur
        var educateur = dataStorage.trouverEducateurParEmail(emailEducateur);
        if (educateur == null) {
            activitesTextArea.setText("Erreur : Éducateur introuvable.");
        } else {
            // Nettoyage du nom de l'éducateur
            String nomEducateur = educateur.getNom().trim().replaceAll("^\"|\"$", "");
            activitesTextArea.append("Nom de l'éducateur connecté : \"" + nomEducateur + "\"\n");

            // Détection de la catégorie
            String categorie = null;
            if ("Medhi Souaki".equalsIgnoreCase(nomEducateur)) {
                categorie = "Sorties en Forêt et Aquatiques";
            } else if ("Julie Cazeneuve".equalsIgnoreCase(nomEducateur)) {
                categorie = "Activités Récréatives";
            } else if ("Christelle Meudon".equalsIgnoreCase(nomEducateur)) {
                categorie = "Activités Culinaires";
            } else {
                activitesTextArea.append("Erreur : Catégorie non définie pour cet éducateur.\n");
            }

            if (categorie != null) {
                activitesTextArea.append("Catégorie détectée : " + categorie + "\n");

                // Afficher les activités compatibles pour chaque jour
                afficherActivitesParCategorie(categorie, dataStorage, activitesTextArea);
            }
        }

        JButton fermerButton = new JButton("Fermer");
        fermerButton.addActionListener(e -> dispose());

        panel.add(new JScrollPane(activitesTextArea), BorderLayout.CENTER);
        panel.add(fermerButton, BorderLayout.SOUTH);
        add(panel);
    }

    private void afficherActivitesParCategorie(String categorie, DataStorage dataStorage, JTextArea activitesTextArea) {
        // Demander le nom de l'enfant
        Enfant enfant = demanderEnfant(dataStorage);
        if (enfant != null) {
            activitesTextArea.append("Allergies : " + String.join(", ", enfant.getAllergies()) + "\n");
            activitesTextArea.append("Problèmes de santé : " + String.join(", ", enfant.getProblemesDeSante()) + "\n");

            String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};

            if ("Sorties en Forêt et Aquatiques".equals(categorie)) {
                // Afficher les activités pour "Sorties en Forêt"
                activitesTextArea.append("\nSortie Forêt :\n");
                String[] activitesForet = dataStorage.getActivitesCompatiblesParCategorie("Sorties en Forêt", enfant);
                for (int i = 0; i < jours.length; i++) {
                    String activite = i < activitesForet.length ? activitesForet[i] : "Aucune activité compatible.";
                    activitesTextArea.append("Activité disponible " + jours[i] + " : " + activite + "\n");
                }

                // Afficher les activités pour "Sorties Aquatiques"
                activitesTextArea.append("\nSortie Aquatique :\n");
                String[] activitesAquatiques = dataStorage.getActivitesCompatiblesParCategorie("Sorties Aquatiques", enfant);
                for (int i = 0; i < jours.length; i++) {
                    String activite = i < activitesAquatiques.length ? activitesAquatiques[i] : "Aucune activité compatible.";
                    activitesTextArea.append("Activité disponible " + jours[i] + " : " + activite + "\n");
                }
            } else {
                // Afficher les activités pour les autres catégories
                String[] activitesCompatibles = dataStorage.getActivitesCompatiblesParCategorie(categorie, enfant);
                for (int i = 0; i < jours.length; i++) {
                    String activite = i < activitesCompatibles.length ? activitesCompatibles[i] : "Aucune activité compatible.";
                    activitesTextArea.append("Activité disponible " + jours[i] + " : " + activite + "\n");
                }
            }

            // Demander à l'utilisateur de choisir une activité
            String activiteChoisie = JOptionPane.showInputDialog(this, "Choisissez une activité (ou 0 pour retour) :");
            if (activiteChoisie != null && !"0".equals(activiteChoisie)) {
                enfant.ajouterActivite(activiteChoisie);
                JOptionPane.showMessageDialog(this, "Activité \"" + activiteChoisie + "\" ajoutée pour l'enfant \"" + enfant.getNom() + "\".");
            }
        } else {
            activitesTextArea.append("Erreur : Aucun enfant valide trouvé.\n");
        }
    }


    private Enfant demanderEnfant(DataStorage dataStorage) {
        String nomEnfant = JOptionPane.showInputDialog(this, "Nom de l'enfant :");
        if (nomEnfant != null && !nomEnfant.trim().isEmpty()) {
            return dataStorage.trouverEnfantParNom(nomEnfant.trim());
        }
        return null;
    }
}
