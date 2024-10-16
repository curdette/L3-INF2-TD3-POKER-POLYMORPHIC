package fr.pantheonsorbonne.cri;
import java.util.Arrays;
import java.util.Random;

public class Deck {

    // les attributs
    private final static Card[] cards; // attribut représentant le jeu de cartes (deck)
    private static int remainingCardCount; // nombre de cartes restantes dans le deck

    // le constructeur
    static {
        // initialisation du jeu de cartes
        cards = new Card[32]; // créer un jeu de 32 cartes (tableau)
        remainingCardCount = cards.length;
        int index = 0;
        // itère sur chacune des valeurs possibles des cartes
        for (Value value : Value.values()) {
            // itère sur chacune des couleurs possibles
            for (Color color : Color.values()) {
                cards[index++] = new Card(value, color); // création et ajout des cartes dans le tableau (deck)
            }
        }

        // Mélange les cartes de manière aléatoire
        Random rand = new Random(); // créer un générateur de nombres aléatoires
        for (int i = 0; i < cards.length; i++) {
            int randIndex = rand.nextInt(cards.length); // génère un index aléatoire compris entre 0 et 31
            Card c = cards[randIndex]; // sauvegarde temporairement la carte située à l'index aléatoire
            cards[randIndex] = cards[i]; // échange la carte à l'index aléatoire avec celle à l'index `i`
            cards[i] = c; // place la carte précédemment à l'index aléatoire à la position `i`
        }
    }

    // méthode pour tirer un certain nombre de cartes aléatoirement
    static public Card[] getRandomCards(int deckSize) {
        // nombre de cartes aléatoires que l'on souhaite tirer du jeu
        if (remainingCardCount >= deckSize) {
            // vérifie s'il y a assez de cartes dans le jeu avant d'en tirer le nombre
            // demandé
            Card[] res = Arrays.copyOfRange(cards, cards.length - remainingCardCount,
                    cards.length - remainingCardCount + deckSize);
            // copie une partie du tableau pour renvoyer le nombre de cartes tirées
            remainingCardCount -= deckSize; // met à jour le nombre de cartes restantes
            return res; // retourne les cartes tirées
        }
        return new Card[0]; // s'il n'y a pas assez de cartes, renvoie un tableau vide
    }

    // méthode pour obtenir une nouvelle main de cartes aléatoires
    static public Card[] newRandomHand() {
        // ici on peut implémenter la logique de distribution des cartes (par exemple 5
        // cartes par joueur)
        return getRandomCards(5); // retourne une main de 5 cartes
    }
}