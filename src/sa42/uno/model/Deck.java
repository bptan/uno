/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author BP
 */
public class Deck {

    private int numberOfCards;
    private List<Card> deck;

    public Deck() {
        populate();
    }

    public void shuffle() {
        
    }

    private void populate() {

        deck = new LinkedList<>();
        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        String[] types = {"Skip", "Reverse", "TakeTwo"};

        for (String c : colors) {
            deck = loopNumbers(c, getDeck());
            for (String t : types) {
                deck = loopSpecialTypes(t, c, getDeck());
            }
        }

        deck = loopWild(getDeck());

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

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < deck.size(); i++) {
            output += deck.get(i).toString() + "\n";
        }
        return output;
    }

}
