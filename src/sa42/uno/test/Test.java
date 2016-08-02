package sa42.uno.test;

import sa42.uno.model.Card;
import sa42.uno.model.DeckOfCards;
import sa42.uno.model.Game;

public class Test {
    
    
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        Game game = new Game();
        
        System.out.println(">>Before shuffle:");
        System.out.println(deck.toString());
        System.out.print(listOfCards(deck));
        deck.shuffle();
        System.out.println(">>After shuffle:");
        System.out.println(deck.toString());
        System.out.print(listOfCards(deck));
        
        
        Card chosenCard = game.takeCardFromDeck();
        System.out.println(">>Chosen card:");
        System.out.println(chosenCard.toString());
        System.out.println(">>Remaining cards:");
        System.out.println(deck.toString());
        System.out.print(listOfCards(deck));
    }
    
    private static String listOfCards(DeckOfCards deck){       
        String listOfCards = "";
        for (int i = 0; i < deck.getDeck().size(); i++) {
            listOfCards += deck.getDeck().get(i).toString() + "\n";
        }
        return listOfCards;
    }
}
