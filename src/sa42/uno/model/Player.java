package sa42.uno.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.Session;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private List<Card> hand;

    private Session session;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public Player(String name, Session session) {
        this(name);
        this.session = session;
        System.out.println(">>new connection " + session.getId());
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

    public List<Card> getHand() {
        return hand;
    }

    public void setSession(Session s) {
        session = s;
        System.out.println(">>new connection " + s.getId());
    }

    public Session getSession() {
        return (session);
    }

    @Override
    public String toString() {
        String listOfCards = "";
        for (int i = 0; i < hand.size(); i++) {
            listOfCards += hand.get(i).toString() + "\n";
        }
        return "Player: " + "Name=" + name + ",\nHand=\n" + listOfCards;

    }

    public JsonArray toJsonHandOnly() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < hand.size(); i++) {
            arrayBuilder.add(hand.get(i).toJson());
        }
        return arrayBuilder.build();
    }

    public JsonObject toJson() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < hand.size(); i++) {
            arrayBuilder.add(hand.get(i).toJson());
        }
        JsonArray handAsJsonArray = arrayBuilder.build();

        return (Json.createObjectBuilder()
                .add("name", name)
                .add("hand", handAsJsonArray)
                .build());
    }

    public void send(JsonObject msg) throws IOException {
        session.getBasicRemote().sendText(msg.toString());
    }
}
