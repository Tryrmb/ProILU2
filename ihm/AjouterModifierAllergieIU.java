package ihm;

import gestion.DataStorage;
import modele.Enfant;

import javax.swing.*;
import java.awt.*;

public class AjouterModifierAllergieIU extends JFrame {
    public AjouterModifierAllergieIU(DataStorage dataStorage) {
        setTitle("Ajouter ou Modifier une Allergie");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel enfantLabel = new JLabel("Nom de l'enfant :");
        JTextField enfantField = new JTextField();
        JLabel allergieLabel = new JLabel("Nouvelle allergie :");
        JTextField allergieField = new JTextField();
        JButton ajouterButton = new JButton("Ajouter/Modifier");
        JButton fermerButton = new JButton("Fermer");

        ajouterButton.addActionListener(e -> {
            String nomEnfant = enfantField.getText().trim();
            String nouvelleAllergie = allergieField.getText().trim();

            if (nomEnfant.isEmpty() || nouvelleAllergie.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            Enfant enfant = dataStorage.trouverEnfantParNom(nomEnfant);
            if (enfant != null) {
                enfant.ajouterAllergie(nouvelleAllergie);
                JOptionPane.showMessageDialog(this, "L'allergie \"" + nouvelleAllergie + "\" a été ajoutée/modifiée pour l'enfant \"" + nomEnfant + "\".");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur : Enfant introuvable.");
            }
        });

        fermerButton.addActionListener(e -> dispose());

        panel.add(enfantLabel);
        panel.add(enfantField);
        panel.add(allergieLabel);
        panel.add(allergieField);
        panel.add(ajouterButton);
        panel.add(fermerButton);

        add(panel);
    }
}
