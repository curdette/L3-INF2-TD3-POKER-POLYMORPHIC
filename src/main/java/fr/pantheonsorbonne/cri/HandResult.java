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

    public int getNumRepetition() {
        return maxCardCount;

    }

    public Value getHighCard() {
        return cardValue;
    }
}