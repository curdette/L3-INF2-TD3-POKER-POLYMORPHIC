package fr.pantheonsorbonne.cri;
public class ClosedPoker {

    public static void main(String... args) {
        // création des joueurs
        Player p1 = new Player("Nicolas");
        Player p2 = new Player("Elio");
        Player p3 = new Player("Flavio");

        // donner des cartes aux joueurs
        p1.setHand(Deck.newRandomHand());
        p2.setHand(Deck.newRandomHand());
        p3.setHand(Deck.newRandomHand());

        // récupérer les cartes que les joueurs veulent défausser et leur donner de
        // nouvelles cartes
        Card[] cardsP1 = p1.getCardsToDiscard();
        p1.addCard(Deck.getRandomCards(cardsP1.length));

        Card[] cardsP2 = p2.getCardsToDiscard();
        p2.addCard(Deck.getRandomCards(cardsP2.length));

        Card[] cardsP3 = p3.getCardsToDiscard();
        p3.addCard(Deck.getRandomCards(cardsP3.length));

        // vérifier qui gagne
        if (p1.beats(p2) && p1.beats(p3)) {
            System.out.println(p1.name+" wins with hand \n" + p1.getHandString());
        } else if (p2.beats(p1) && p2.beats(p3)) {
            System.out.println(p2.name+" wins with hand \n" + p2.getHandString());
        } else if (p3.beats(p1) && p3.beats(p2)) {
            System.out.println(p3.name+" wins with hand \n" + p3.getHandString());
        } else {
            System.out.println("There is a draw.");
        }
    }
}