package fr.pantheonsorbonne.cri;

public class HandResult {

    private int maxCardCount = 0;
    private Value cardValue = null;

    public HandResult(Card[] cards) {

        // on parcourt toutes les cartes
        for (int i = 0; i < cards.length; i++) {
            Value currCardValue = cards[i].getValue();
            int currCardCount = 1;

            // on compte combien il y a de carte identique cards[i]
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[j].getValue() == currCardValue) {
                    currCardCount++;
                }
            }

            // Si on a un total égal au maximum déjà compté
            if (currCardCount == maxCardCount) {
                // on regarde si notre combinaison est meilleure
                if (cardValue.ordinal() > currCardValue.ordinal()) {
                    // si c'est le cas, on remplace l'ancienne meilleure combinaison par celle en
                    // cours
                    cardValue = currCardValue;
                }
                // sinon, on ne fait rien
            }
            // si notre combinaison contient un plus grand nombre de carte
            else if (currCardCount > maxCardCount) {
                // on remplace l'ancienne
                cardValue = currCardValue;
                maxCardCount = currCardCount;
            }

        }

    }
    public static Card[] trierCards(Card[] cards){
        for(int i =0;i<cards.length; i++){
            for(int j =i+1; j<cards.length; j++){
                if(cards[i].getValue().ordinal()<cards[j].getValue().ordinal()){
                    Card aux = cards[i];
                    cards[i] = cards[j];
                    cards[j]=aux;
                }
            }
        }
        return cards;
    }
    public int getNumRepetition() {
        return maxCardCount;

    }

    public Value getHighCard() {
        return cardValue;
    }
    public static MainPoker determinerMain(Card[] cards) {
        int compteurSuite=0;
        int compteurQuiteFlush =0;
        cards = HandResult.trierCards(cards);
        HandResult test = new HandResult(cards);
        Value highestCard = test.getHighCard();
        int numrepetition = test.getNumRepetition();
        if (numrepetition == 2) {
            return new Paire(cards);
        } else if (numrepetition==3) {
            return new Brelan(cards);
        }
        else if (numrepetition==4) {
            return new Carre(cards);
        }
        for(int i =0; i<cards.length-1; i++){
            if(cards[i++].getValue().ordinal()==cards[i].getValue().ordinal()-1){
                compteurSuite ++;
                if(cards[i++].getColor().equals(cards[i].getColor())){
                    compteurSuite=0;
                    compteurQuiteFlush ++;
                }
            } 
        }
         if (compteurSuite==4){
            return new Suite(cards);
        }
        else if (compteurQuiteFlush==4){
            return new QuinteFlush(cards);
        }

        // Ajoutez les autres conditions pour les autres combinaisons
    }
}