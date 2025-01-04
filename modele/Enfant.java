package modele;

/**
 * Classe Enfant
 * Représente un enfant avec ses allergies, son régime alimentaire, et ses activités.
 */
public class Enfant {
    private String nom;
    private String[] allergies; // Tableaux pour les allergies
    private String regimeAlimentaire;
    private String[] activites; // Tableaux pour les activités de l'enfant
    private String[] problemesDeSante; // Tableaux pour les problèmes de santé
    private String bilan; // Bilan individuel

    private int tailleMax = 10; // Taille maximale des tableaux
    private int nombreAllergies = 0;
    private int nombreActivites = 0;
    private int nombreProblemesDeSante = 0;

    // Constructeur existant
    public Enfant(String nom, String allergiesRaw, String regimeAlimentaire) {
        this.nom = nom;
        this.allergies = new String[tailleMax];
        this.activites = new String[tailleMax];
        this.problemesDeSante = new String[tailleMax];
        this.regimeAlimentaire = regimeAlimentaire;
        this.bilan = "";

        // Découpage des allergies
        String[] allergiesDecoupees = allergiesRaw.split(", ");
        for (String allergie : allergiesDecoupees) {
            ajouterAllergie(allergie.trim());
        }
    }

    // Nouveau constructeur
    public Enfant(String nom, String[] allergiesInit, String regimeAlimentaire) {
        this.nom = nom;
        this.allergies = new String[tailleMax];
        this.activites = new String[tailleMax];
        this.problemesDeSante = new String[tailleMax];
        this.regimeAlimentaire = regimeAlimentaire;
        this.bilan = "";

        // Initialisation des allergies
        for (String allergie : allergiesInit) {
            ajouterAllergie(allergie.trim());
        }
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void ajouterAllergie(String allergie) {
        if (nombreAllergies >= tailleMax) {
            System.out.println("Erreur : Le nombre maximum d'allergies est atteint.");
            return;
        }
        for (int i = 0; i < nombreAllergies; i++) {
            if (allergies[i].equalsIgnoreCase(allergie)) {
                return; // L'allergie existe déjà
            }
        }
        allergies[nombreAllergies++] = allergie;
    }

    public boolean supprimerAllergie(String allergie) {
        for (int i = 0; i < nombreAllergies; i++) {
            if (allergies[i].equalsIgnoreCase(allergie)) {
                // Décalage des éléments pour supprimer l'allergie
                for (int j = i; j < nombreAllergies - 1; j++) {
                    allergies[j] = allergies[j + 1];
                }
                allergies[--nombreAllergies] = null;
                return true;
            }
        }
        return false;
    }

    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public String[] getActivites() {
        return activites;
    }

    public boolean ajouterActivite(String activite) {
        if (nombreActivites >= tailleMax) {
            System.out.println("Erreur : Le nombre maximum d'activités est atteint.");
            return false; // Indique que l'ajout a échoué
        }
        for (int i = 0; i < nombreActivites; i++) {
            if (activites[i].equalsIgnoreCase(activite)) {
                System.out.println("Erreur : Cette activité existe déjà.");
                return false; // Indique que l'ajout a échoué
            }
        }
        activites[nombreActivites++] = activite;
        return true; // Indique que l'ajout a réussi
    }

    public String[] getProblemesDeSante() {
        return problemesDeSante;
    }

    public void ajouterProblemeDeSante(String probleme) {
        if (nombreProblemesDeSante >= tailleMax) {
            System.out.println("Erreur : Le nombre maximum de problèmes de santé est atteint.");
            return;
        }
        problemesDeSante[nombreProblemesDeSante++] = probleme;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }
}
