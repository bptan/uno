package sa42.uno.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.Session;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;



    public enum Status {
        Waiting, Started, Ended
    };
    private final String id;
    private final String title;
    private DeckOfCards deck;
    private final Map<String, Player> players;
    private final Stack<Card> table;
    private Session gameSession;
    private Status status;

    public Game(String id, String title) {
        this.id = id;
        this.title = title;
        deck = new DeckOfCards();
        deck = shuffle(deck);
        players = new HashMap<>();
        table = new Stack<>();
        status = Status.Waiting;
    }

    public Session getGameSession() {
        return gameSession;
    }

    public void setGameSession(Session gameSession) {
        this.gameSession = gameSession;
        System.out.println("session set inside game"+this.gameSession.getId());
    }

    public Stack<Card> getTable() {
        return table;
    }

    public JsonObject toJson() {

        return (Json.createObjectBuilder()
                .add("gameid", id)
                .add("title", title)
                .add("status", status.toString())
                .add("numOfPlayers", players.size())
                .build());
    }

    public JsonObject toGameTableJson() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        Iterator it = players.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry<String, Player>) it.next();
            Player p = (Player) pair.getValue();
            arrayBuilder.add(p.getName());

        }
        JsonArray playerNameAsJsonArray = arrayBuilder.build();

        return (Json.createObjectBuilder()
                .add("gameid", id)
                .add("title", title)
                .add("status", status.toString())
                .add("deckSize", deck.getNumberOfCards())
                .add("numOfPlayers", players.size())
                .add("playerNames", playerNameAsJsonArray)
                .add("topCardOfDiscardPile", getTable()
                        .get(getTable().size() - 1).getImage())
                .build());
    }
    
    public JsonArray playerNameAsJsonArray(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        Iterator it = players.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry<String, Player>) it.next();
            Player p = (Player) pair.getValue();
            arrayBuilder.add(p.getName());

        }        
        return arrayBuilder.build();       
    }

    public Optional<Player> getPlayer(String name) {

        return Optional.ofNullable(players.get(name));
    }

    public void distributeCards() {
        for (int whichCard = 0; whichCard < 7; whichCard++) {

            Iterator it = players.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry<String, Player>) it.next();
                Player p = (Player) pair.getValue();
                p.addCard(deck.takeCard());
            }
//            for (int whichPlayer = 0; whichPlayer < players.size(); whichPlayer++) {
//                players.get(whichPlayer).addCard(deck.takeCard());
//            }
        }
    }

    private DeckOfCards shuffle(DeckOfCards deckOfCards) {
        Random randomCards = new Random();

        for (int first = 0; first < deckOfCards.getDeck().size(); first++) {
            int second = randomCards.nextInt(deckOfCards.getDeck().size());
            Card temp = deckOfCards.getDeck().get(first);
            deckOfCards.getDeck().set(first, deckOfCards.getDeck().get(second));
            deckOfCards.getDeck().set(second, temp);
        }
        return deckOfCards;
    }

    //To shuffle discard pile in future
    public Stack<Card> shuffle(Stack<Card> cards) {

        Random randomCards = new Random();
        for (int first = 0; first < cards.size(); first++) {
            int second = randomCards.nextInt(cards.size());
            Card temp = cards.get(first);
            cards.set(first, cards.get(second));
            cards.set(second, temp);
        }
        return cards;
    }

    public Player addPlayer(Player p) {

        return players.put(p.getName(), p);
    }

    public Card takeCardFromDeck() {
        return deck.takeCard();
    }

    public DeckOfCards getDeck() {
        return deck;
    }

    public List<Player> getPlayers() {
        return (Collections.unmodifiableList(new LinkedList<>(players.values())));
    }
    public Map<String,Player> getPlayersMAP() {
        return players;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {

        return "Game ID: " + id + ", Title: " + title + ", Status: " + status
                + ", Number Of Players: " + players.size();

    }
    public void send(JsonObject msg) throws IOException {
        gameSession.getBasicRemote().sendText(msg.toString());
    }

}
