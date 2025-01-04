package ihm;

import gestion.DataStorage;
import modele.Enfant;

import javax.swing.*;
import java.awt.*;

public class GestionBilanIU extends JFrame {
    public GestionBilanIU(String email, DataStorage dataStorage) {
        setTitle("Gérer les Bilans");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel enfantLabel = new JLabel("Nom de l'enfant :");
        JTextField enfantField = new JTextField();
        JLabel bilanLabel = new JLabel("Entrez le bilan :");
        JTextArea bilanField = new JTextArea();
        JButton enregistrerButton = new JButton("Enregistrer");
        JButton fermerButton = new JButton("Fermer");

        enregistrerButton.addActionListener(e -> {
            String nomEnfant = enfantField.getText().trim();
            String bilan = bilanField.getText().trim();

            if (nomEnfant.isEmpty() || bilan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            Enfant enfant = dataStorage.trouverEnfantParNom(nomEnfant);
            if (enfant != null) {
                enfant.setBilan(bilan);
                JOptionPane.showMessageDialog(this, "Bilan enregistré avec succès pour " + nomEnfant + ".");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur : Enfant introuvable.");
            }
        });

        fermerButton.addActionListener(e -> dispose());

        panel.add(enfantLabel);
        panel.add(enfantField);
        panel.add(bilanLabel);
        panel.add(new JScrollPane(bilanField));
        panel.add(enregistrerButton);
        panel.add(fermerButton);

        add(panel);
    }
}
