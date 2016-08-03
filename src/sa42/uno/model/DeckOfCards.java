package sa42.uno.model;

import java.util.Random;
import java.util.Stack;

public class DeckOfCards {

    private Stack<Card> deck;

    public DeckOfCards() {
        deck = new Stack<>();
        populate();
    }

    public Card takeCard() {
        return getDeck().pop();
    }

    private void populate() {

        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        String[] types = {"Skip", "Reverse", "TakeTwo"};

        for (String c : colors) {
            setDeck(loopNumbers(c, getDeck()));
            for (String t : types) {
                setDeck(getSpecialTypeCards(t, c, getDeck()));
            }
        }

        setDeck(getWildCards(getDeck()));

    }

    private Stack<Card> loopNumbers(String color, Stack<Card> deck) {

        for (int value = 0; value < 10; value++) {
            Card c = new Card();
            c.setColor(color);
            c.setType("Number");
            c.setValue(value);
            c.setImage(color + "Number" + value);

            if (value != 0) {
                deck.add(c);
            }

            deck.add(c);

        }
        return deck;
    }

    private Stack<Card> getSpecialTypeCards(String type, String color, Stack<Card> deck) {
        Card c = new Card();
        c.setColor(color);
        c.setType(type);
        c.setValue(20);
        c.setImage(color + type + 20);
        deck.add(c);
        deck.add(c);
        return deck;
    }

    private Stack<Card> getWildCards(Stack<Card> deck) {

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

    protected Stack<Card> getDeck() {
        return deck;
    }

    private void setDeck(Stack<Card> deck) {
        this.deck = deck;
    }

    public int getNumberOfCards() {
        return deck.size();
    }

    @Override
    public String toString() {
        return "Deck: " + "number of cards="
                + deck.size();
    }

}
