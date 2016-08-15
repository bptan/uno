package sa42.uno.model;

import java.util.LinkedList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

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
    
     
    public JsonArray toJsonHandOnly(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(int i = 0; i< hand.size();i++){
            arrayBuilder.add(hand.get(i).toJson());                   
        }       
        return arrayBuilder.build();
    }
    public JsonObject toJson() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(int i = 0; i< hand.size();i++){
            arrayBuilder.add(hand.get(i).toJson());                   
        }       
        JsonArray handAsJsonArray = arrayBuilder.build();
     
		return (Json.createObjectBuilder()
				.add("name", name)
				.add("hand", handAsJsonArray)
				.build());
	}
}
