package sa42.uno.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Game {

    private String id;
    private List<Player> players;
    private String status;
    private DeckOfCards deck;
    private List<Card> discardPile;

    //enum status = {"Waiting","Started","Ended"};
    public Game() {
        UUID id = new UUID(0, 8);
        //get deck of cards and shuffle it
        this.deck = new DeckOfCards();
        getDeck().shuffle();
        //generate id
        this.id = id.randomUUID().toString().substring(0, 8);
        System.out.println(this.id);

        this.players = new ArrayList<>();

        this.discardPile = new LinkedList<>();
        this.status = "Waiting";
    }

    public void distributeCards() {
        for (int whichCard = 0; whichCard < 7; whichCard++) {
            for (int whichPlayer = 0; whichPlayer < players.size(); whichPlayer++) {
                players.get(whichPlayer).addCard(deck.takeCard());
            }
        }
    }

    public boolean addPlayer(Player p) {

        return players.add(p);
    }

    public Card takeCardFromDeck() {
        return getDeck().takeCard();
    }
    
    public boolean addToDiscardPile(Card c){
        return discardPile.add(c);
    }
    
//    public Card takeFromTopOfDiscardPile(){
//        
//        Card c = discardPile.get(discardPile.size()-1);
//        discardPile.remove(discardPile.size()-1);
//        return c;
//        
//    }

    public DeckOfCards getDeck() {
        return deck;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
