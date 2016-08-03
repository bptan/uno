package sa42.uno.test;

import java.util.List;
import sa42.uno.model.Card;
import sa42.uno.model.Game;
import sa42.uno.model.Player;

public class Test {

    public static void main(String[] args) {

        Game game = new Game();
        
        //create 5 players
        Player player1 = new Player("Elon");
        Player player2 = new Player("Carl");
        Player player3 = new Player("Warren");
        Player player4 = new Player("Bill");
        Player player5 = new Player("Jack");
        

        //add players to game      
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.addPlayer(player5);
        

        game.setStatus(Game.Status.Started);
        
        System.out.println(game.toString());

        List<Player> players = game.getPlayers();

        game.distributeCards();

        System.out.print(listOfPlayers(players));

        //get value of each player's hand       
        for (int playerNumber = 0;
                playerNumber < players.size();
                playerNumber++) {

            List<Card> hand = players.get(playerNumber).getHand();
            int value = 0;
            for (int cardNumber = 0;
                    cardNumber < hand.size();
                    cardNumber++) {
                value += hand.get(cardNumber).getValue();
            }
            System.out.println(players.get(playerNumber).getName() + ": Value = " + value);
        }

        //get top card of discarded pile
        Card card = game.takeCardFromDeck();

        int sizeOfPile = game.addToDiscardPile(card);

        System.out.println("\nJust Discarded:\n"
                + game.getDiscardPile().get(sizeOfPile - 1));

        System.out.println(">>Remaining cards: "
                + game.getDeck().getNumberOfCards());
    }

    private static String listOfPlayers(List<Player> players) {
        String listOfPlayers = "";
        for (int i = 0; i < players.size(); i++) {
            listOfPlayers += players.get(i).toString() + "\n";
        }
        return listOfPlayers;
    }
}
