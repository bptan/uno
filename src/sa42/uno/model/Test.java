/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.model;

import java.util.List;

/**
 *
 * @author BP
 */
public class Test {

    public static void main(String[] args) {
        Deck d = new Deck();

        System.out.print(d.toString());

        List<Card> deck = d.getDeck();
        System.out.println(deck.size());
    }
}
