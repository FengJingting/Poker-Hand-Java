package comp1721.cwk2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Implement Deck class here
public class Deck extends CardCollection{
    protected List<Card> deck;
    private String[] ranks = {"A","2","3","4","5","6","7","8","9","T","J","Q","K"};
    private String[] suits = {"C","D","H","S"};
    /**
     * Creates an deck.
     */
    public Deck(){
        deck = new LinkedList<>();
        for (int j = 0;j<4;j++){
            for(int i = 0;i<13;i++){
                Card card = new Card(ranks[i].concat(suits[j]));
                deck.add(card);
            }
        }
    }
    /**
     * Provides the number of cards in this deck.
     *
     * @return Number of cards
     */
    @Override
    public int size(){
        return deck.size();
    }
    /**
     * Indicates whether this deck is empty or not.
     *
     * @return true if collection is empty, false otherwise
     */
    @Override
    public boolean isEmpty(){
        return deck.isEmpty();
    }
    /**
     * Discards all the cards from the deck.
     */
    @Override
    public void discard(){
        deck.clear();
    }
    /**
     * Indicates whether a particular card is present in this deck.
     *
     * @param card Card we are looking for
     * @return true if the card is present, false otherwise
     */
    @Override
    public boolean contains(Card card){
        return deck.contains(card);
    }

    public Card deal()throws CardException{
        if(deck.size()==0){
            throw new CardException("No card in deck!");
        }
        Card card = deck.remove(0);
        return card;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }
    /**
     * Adds the given card to the deck.
     *
     * @param card Card to be added
     */
    @Override
    public void add(Card card)throws CardException{
        if (deck.size()==52){
            throw new CardException("No more than 52 cards in a deck!");
        }else{
            if(deck.contains(card)){
                throw new CardException("Already contains this card in a poker hand!");
            }else{
                deck.add(card);
            }
        }
    }
}
