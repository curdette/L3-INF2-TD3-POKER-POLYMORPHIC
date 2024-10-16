package fr.pantheonsorbonne.cri;

public class Player {
    // Attributs du joueur
    final String name; // Le nom du joueur
    private Card[] cards; // La main du joueur (tableau de cartes)

    // Constructeur
    public Player(String name) {
        this.name = name;
        this.cards = new Card[5]; // Par exemple, une main de 5 cartes
    }

    // Le joueur reçoit cette main de cartes
    public void setHand(Card[] deck) {
        if (deck.length != 0) {
            this.cards = deck; // Attribuer les cartes passées en paramètre à la main du joueur
        } else {
            System.out.println("La taille de la main ne correspond pas.");
        }
    }

    // Méthode pour récupérer la main du joueur
    public Card[] getCards() {
        return this.cards;
    }

    // méthode pour vérifier si un joueur bat un autre joueur
    public boolean beats(Player other) {
        HandResult mine = new HandResult(this.cards);
        HandResult his = new HandResult(other.cards);

        if (mine.getNumRepetition() > his.getNumRepetition()) {
            return true;
        } else if (mine.getNumRepetition() == his.getNumRepetition()) {
            if (mine.getHighCard().ordinal() < his.getHighCard().ordinal()) {
                return true;
            }
        }

        return false;

    }

    // méthode pour afficher la main du joueur en chaîne de caractères
    public String getHandString() {
        StringBuilder builder = new StringBuilder();
        for (Card c : this.cards) {
            if(c!=null){
                builder.append("\t - "+c.toString());
            }
            else{
                builder.append("\t - ");
            }
            builder.append("\n ");
        }
        ;
        return builder.toString().substring(0, builder.length() - 2);
    }

    // méthode pour que le joueur indique les cartes qu'il veut défausser
    public Card[] getCardsToDiscard() {
        System.out.println(this.name + " Discarding Card: hand before: \n" + this.getHandString());
        HandResult mine = new HandResult(this.cards);
        Card[] cardsToDiscard = new Card[this.cards.length - mine.getNumRepetition()];
        int discardIndex = 0;
        for (int i = 0; i < this.cards.length; i++) {
            if (!this.cards[i].value.equals(mine.getHighCard())) {
                cardsToDiscard[discardIndex++] = this.cards[i];
                this.cards[i] = null;
            }
        }
        System.out.println(this.name + " Discarding Card: hand after: \n" + this.getHandString());
        return cardsToDiscard;

    }

    // méthode pour ajouter des cartes à la main du joueur
    public void addCard(Card[] cards) {
        System.out.println(this.name + " Adding cards in hand: hand before: \n" + this.getHandString());
        int myHandIndex = 0;
        for (int i = 0; i < cards.length; i++) {
            for (; myHandIndex < this.cards.length; myHandIndex++) {
                if (this.cards[myHandIndex] == null) {
                    this.cards[myHandIndex] = cards[i];
                    break;
                }
            }

        }
         System.out.println(this.name + " Adding cards in hand: hand after: \n" + this.getHandString());
    }
}