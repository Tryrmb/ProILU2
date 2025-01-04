package ihm;

import gestion.CompteController;
import gestion.DataStorage;
import ihm.LoginIU;

import javax.swing.*;
import java.awt.*;

public class LoginIU extends JFrame {
    private String userType; // "parent" ou "educateur"
    private CompteController compteController;

    public LoginIU(String userType, DataStorage dataStorage) {
        this.userType = userType;
        this.compteController = new CompteController(dataStorage);

        setTitle("Login - " + (userType.equals("parent") ? "Parent" : "Éducateur"));
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Se connecter");

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            boolean isAuthenticated;

            if (userType.equals("parent")) {
                isAuthenticated = compteController.verifierIdentifiants(email, password);
                if (isAuthenticated) {
                    JOptionPane.showMessageDialog(this, "Authentification réussie !");
                    new MenuParentIU(email, dataStorage).setVisible(true); // Afficher le menu parent
                    dispose(); // Fermer l'écran de connexion
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de l'authentification.");
                }
            } else if (userType.equals("educateur")) {
                isAuthenticated = compteController.verifierIdentifiantsEducateur(email, password);
                if (isAuthenticated) {
                    JOptionPane.showMessageDialog(this, "Authentification réussie !");
                    new MenuEducateurIU(email, dataStorage).setVisible(true); // Afficher le menu éducateur
                    dispose(); // Fermer l'écran de connexion
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de l'authentification.");
                }
            }
        });


        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
    }
}
