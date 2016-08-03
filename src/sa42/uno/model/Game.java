package sa42.uno.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.UUID;

public class Game {

    public enum Status {
        Waiting, Started, Ended
    };
    private String id;
    private DeckOfCards deck;
    private List<Player> players;
    private Stack<Card> discardPile;
    private Status status;

    public Game() {
        UUID uuid = new UUID(0, 8);
        id = uuid.randomUUID().toString().substring(0, 8);
        deck = new DeckOfCards();
        deck = shuffle(deck);
        players = new ArrayList<>();
        discardPile = new Stack<>();
        status = Status.Waiting;
    }

    public void distributeCards() {
        for (int whichCard = 0; whichCard < 7; whichCard++) {
            for (int whichPlayer = 0; whichPlayer < players.size(); whichPlayer++) {
                players.get(whichPlayer).addCard(deck.takeCard());
            }
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

    public boolean addPlayer(Player p) {

        return players.add(p);
    }

    public Card takeCardFromDeck() {
        return getDeck().takeCard();
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

    public List<Player> getPlayers() {
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

        return "Game ID: " + id + ", Status: " + status
                + ", Number Of Players: " + players.size();

    }
}
