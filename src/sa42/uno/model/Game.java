package sa42.uno.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class Game {

    public enum Status {
        Waiting, Started, Ended
    };
    private final String id;
    private final String title;
    private DeckOfCards deck;
    private Map<String,Player> players;
    private Stack<Card> discardPile;
    private Status status;

    public Game(String id, String title) {
        this.id = id;
        
        this.title = title;
        deck = new DeckOfCards();
        deck = shuffle(deck);
        players = new HashMap<>();
        discardPile = new Stack<>();
        status = Status.Waiting;
    }
    public JsonObject toJson() {
        
        
		return (Json.createObjectBuilder()
				.add("gameid", id)
				.add("title", title)
				.add("status", status.toString())
				.add("numOfPlayers", players.size())
                                                       
				.build());
	}
    
    public JsonObject toGameTableJson(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        
        Iterator it = players.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry pair = (Map.Entry<String, Player>)it.next();
                Player p = (Player)pair.getValue();
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
                                .add("topCardOfDiscardPile",discardPile
                                   .get(discardPile.size()-1).getImage())                        
				.build());
    }
    
    public Optional<Player> getPlayer(String name){
        
        return Optional.ofNullable(players.get(name));
    }

    public void distributeCards() {
        for (int whichCard = 0; whichCard < 7; whichCard++) {
            
            Iterator it = players.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry pair = (Map.Entry<String, Player>)it.next();
                Player p = (Player)pair.getValue();
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

    public int addToDiscardPile(Card c) {

        discardPile.push(c);

        return discardPile.size();

    }

    public Card takeFromTopOfDiscardPile() {

        return discardPile.pop();
    }

    public DeckOfCards getDeck() {
        return deck;
    }

    public Map<String,Player> getPlayers() {
        return players;
    }

    public Stack<Card> getDiscardPile() {
        return discardPile;
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

        return "Game ID: " + id + ", Title: " + title+ ", Status: " + status
                + ", Number Of Players: " + players.size();

    }
    
    
}
