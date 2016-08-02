package sa42.uno.model;

import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    
    public boolean addCard(Card c){
        return getHand().add(c);
    }
    
    public Card removeCard(int listIndex){
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
}
