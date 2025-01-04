package modele;

public class Educateur {
    private String nom;
    private String email;
    private String motDePasse;
    private Enfant[] enfants; // Tableau d'enfants
    private int enfantCount; // Compteur pour suivre le nombre d'enfants associés

    private static final int MAX_ENFANTS = 100; // Limite maximale pour les enfants par éducateur

    public Educateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.enfants = new Enfant[MAX_ENFANTS]; // Initialisation du tableau
        this.enfantCount = 0;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Enfant[] getEnfants() {
        Enfant[] enfantsActifs = new Enfant[enfantCount];
        System.arraycopy(enfants, 0, enfantsActifs, 0, enfantCount);
        return enfantsActifs;
    }

    public void ajouterEnfant(Enfant enfant) {
        if (enfantCount < MAX_ENFANTS) {
            enfants[enfantCount++] = enfant;
        } else {
            System.err.println("Erreur : Limite maximale d'enfants atteinte pour l'éducateur " + nom);
        }
    }
}
