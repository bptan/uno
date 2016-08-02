package sa42.uno.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

    private int numberOfCards;
    private List<Card> deck;

    public DeckOfCards() {
        populate();        
        numberOfCards = deck.size();
    }
    
    public Card takeCard(){
        
        Card chosenCard = getDeck().get(getNumberOfCards()-1);
        setNumberOfCards(getNumberOfCards()-1);
        getDeck().remove(getDeck().size()-1);
        
        if(getDeck().size()==getNumberOfCards()){
            return chosenCard;
        }else{
            return null;
        }       
    }

    public void shuffle() {
        Random randomCards = new Random();
        
        for(int first = 0;first < getDeck().size();first++){
            
            int second = randomCards.nextInt(getDeck().size());
            Card temp = getDeck().get(first);
            getDeck().set(first, getDeck().get(second));
            getDeck().set(second,temp);
            
        }
    }

    private void populate() {

        setDeck(new LinkedList<>());
        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        String[] types = {"Skip", "Reverse", "TakeTwo"};

        for (String c : colors) {
            setDeck(loopNumbers(c, getDeck()));
            for (String t : types) {
                setDeck(loopSpecialTypes(t, c, getDeck()));
            }
        }

        setDeck(loopWild(getDeck()));

    }

    private List<Card> loopNumbers(String color, List<Card> deck) {

        for (int value = 0; value < 10; value++) {
            Card c = new Card();
            c.setColor(color);
            c.setType("Normal");
            c.setValue(value);
            c.setImage(color + "Normal" + value);

            if (value != 0) {
                deck.add(c);
            }

            deck.add(c);

        }
        return deck;
    }

    private List<Card> loopSpecialTypes(String type, String color, List<Card> deck) {
        Card c = new Card();
        c.setColor(color);
        c.setType(type);
        c.setValue(20);
        c.setImage(color + type + 20);
        deck.add(c);
        deck.add(c);
        return deck;
    }

    private List<Card> loopWild(List<Card> deck) {

        String color = "Wild";
        String[] types = {"Normal", "TakeFour"};
        

        for (String t : types) {
            Card c = new Card();
            c.setColor(color);
            c.setType(t);
            c.setValue(50);
            c.setImage(color + t + 50);

            for (int i = 0; i < 4; i++) {
                deck.add(c);
            }
        }
        return deck;
    }

    public List<Card> getDeck() {
        return deck;
    }
    
    private void setDeck(List<Card> deck) {
        this.deck = deck;
    }
    
    @Override
    public String toString() {
        return "Deck: " + "number of cards=" + getNumberOfCards();        
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }
}
