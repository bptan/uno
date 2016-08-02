package sa42.uno.model;

import java.util.List;

public class Game {
    private int id;
    private List<Player> players;
    private String status;
    private DeckOfCards deck;
    private List<Card> discardPile;
    
    public Game(){
        
    }
    
    public boolean addPlayer(Player p){
        
        return players.add(p);
    }
    
    public Card takeCardFromDeck (){
        return deck.takeCard();
    }
    
    public boolean addToDiscardPile(Card c){
        return discardPile.add(c);
    }
    public Card takeFromTopOfDiscardPile(){
        
        Card c = discardPile.get(discardPile.size()-1);
        discardPile.remove(discardPile.size()-1);
        return c;
        
    }
}
