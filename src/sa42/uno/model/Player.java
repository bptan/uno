package sa42.uno.model;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public boolean addCard(Card c) {
        return hand.add(c);
    }

    public Card removeCard(int listIndex) {
        Card thrownToPile = getHand().get(listIndex);
        getHand().remove(listIndex);
        return thrownToPile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        String listOfCards = "";
        for (int i = 0; i < hand.size(); i++) {
            listOfCards += hand.get(i).toString() + "\n";
        }

        return "Player: " + "Name=" + name + ",\nHand=\n" + listOfCards;

    }
}
