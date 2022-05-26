package comp1721.cwk2;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;

// Implement PokerHand class here
public class PokerHand extends CardCollection{
    public List<Card> pokerHands;

    public PokerHand(){
        pokerHands = new LinkedList<>();
    }

    public PokerHand(String addcard)throws CardException{
        pokerHands = new LinkedList<>();
        String[] strs = addcard.split(" ");
        if(strs.length>5){
            throw new CardException("Too many cards of a hand!");
        }else{
            for(int i = 0;i < strs.length; i++){
                Card card = new Card(strs[i]);
                Deck deck = new Deck();
                if(deck.contains(card)){
                    pokerHands.add(card);
                }else{
                    throw new CardException("Incorrect string representation of a hand!");
                }
            }
        }

    }
    /**
     * Adds the given card to the deck.
     *
     * @param card Card to be added
     */
    @Override
    public void add(Card card)throws CardException{
        if (pokerHands.size()==5){
            throw new CardException("No more than 5 cards in a poker hand!");
        }else{
            if(pokerHands.contains(card)){
                throw new CardException("Already contains this card in a poker hand!");
            }else{
                Deck deck = new Deck();
                if(deck.contains(card)){
                    pokerHands.add(card);
                }else{
                    throw new CardException("No such card in deck!");
                }
            }
        }
    }
    /**
     * Creates a two-character string representation of this card.
     *
     * <p>The first character represents rank, the second represents suit.
     * Special Unicode symbols will be used for the latter if
     * <code>Card.fancySymbols</code> is set to <code>true</code>.</p>
     *
     * @return String representation of this card
     */
    @Override
    public String toString() {
        String str = "";
        if (pokerHands.size()==0){
            return str;
        }else {
            for(int i = 0; i < pokerHands.size(); i++){
                Card card = pokerHands.get(i);
                str = str.concat(card.toString());
                if(i!=pokerHands.size()-1){
                    str = str.concat(" ");
                }
            }
        }
        return str;
    }
    /**
     * Provides the number of cards in this poker hand.
     *
     * @return Number of cards
     */
    @Override
    public int size(){
        return pokerHands.size();

    }
    @Override
    public void discard()throws CardException{
        if(pokerHands.size()==0){
            throw new CardException("No card in a poker hand!");
        }else{
            pokerHands.clear();
        }

    }

    public void discardTo(Deck deck){
        if(pokerHands.size()==0){
            throw new CardException("No card in a poker hand!");
        }else{
            for(int i = 0; i < pokerHands.size(); i++){
                Card card = pokerHands.get(i);
                deck.add(card);
            }
            pokerHands.clear();
        }

    }

    private HashMap<Object,Integer> check(HashMap<Object,Integer> map){
            for (int i = 0; i < pokerHands.size(); i++) {
                Card card = pokerHands.get(i);
                if (map.containsKey(card.getRank())) {
                    map.put(card.getRank(), map.get(card.getRank()) + 1);
                } else {
                    map.put(card.getRank(), 1);
                }
            }
            return map;
    }

    public boolean isPair(){
        if(pokerHands.size()!=5){
            return false;
        }else{
            HashMap<Object,Integer> map = new HashMap<>();
            map = check(map);
            int twonum = 1;
            int onenum = 3;
            Set<Map.Entry<Object,Integer>> entries = map.entrySet();
            for (Map.Entry<Object,Integer> entry : entries){
                Integer value = entry.getValue();
                if(value==1){
                    onenum--;
                }else if(value==2){
                    twonum--;
                }else{
                    return false;
                }
            }
            if(onenum==0&&twonum==0){
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean isTwoPairs(){
        if(pokerHands.size()!=5){
            return false;
        }else{
            HashMap<Object,Integer> map = new HashMap<>();
            map = check(map);
            int twonum = 2;
            int onenum = 1;
            Set<Map.Entry<Object,Integer>> entries = map.entrySet();
            for (Map.Entry<Object,Integer> entry : entries){
                Integer value = entry.getValue();
                if(value==1){
                    onenum--;
                }else if(value==2){
                    twonum--;
                }else{
                    return false;
                }
            }
            if(onenum==0&&twonum==0){
                return true;
            }else{
                return false;
            }
        }
    }
    public boolean isThreeOfAKind(){
        if(pokerHands.size()!=5){
            return false;
        }else{
            HashMap<Object,Integer> map = new HashMap<>();
            map = check(map);
            int threenum = 1;
            int onenum = 2;
            Set<Map.Entry<Object,Integer>> entries = map.entrySet();
            for (Map.Entry<Object,Integer> entry : entries){
                Integer value = entry.getValue();
                if(value==1){
                    onenum--;
                }else if(value==3){
                    threenum--;
                }else{
                    return false;
                }
            }
            if(onenum==0&&threenum==0){
                return true;
            }else{
                return false;
            }
        }
    }
    public boolean isFourOfAKind(){
        if(pokerHands.size()!=5){
            return false;
        }else{
            HashMap<Object,Integer> map = new HashMap<>();
            map = check(map);
            int fournum = 1;
            int onenum = 1;
            Set<Map.Entry<Object,Integer>> entries = map.entrySet();
            for (Map.Entry<Object,Integer> entry : entries){
                Integer value = entry.getValue();
                if(value==1){
                    onenum--;
                }else if(value==4){
                    fournum--;
                }else{
                    return false;
                }
            }
            if(onenum==0&&fournum==0){
                return true;
            }else{
                return false;
            }
        }
    }
    public boolean isFullHouse(){
        if(pokerHands.size()!=5){
            return false;
        }else{
            HashMap<Object,Integer> map = new HashMap<>();
            map = check(map);
            int threenum = 1;
            int twonum = 1;
            Set<Map.Entry<Object,Integer>> entries = map.entrySet();
            for (Map.Entry<Object,Integer> entry : entries){
                Integer value = entry.getValue();
                if(value==3){
                    threenum--;
                }else if(value==2){
                    twonum--;
                }else{
                    return false;
                }
            }
            if(threenum==0&&twonum==0){
                return true;
            }else{
                return false;
            }
        }
    }
    public boolean isFlush(){
        if(pokerHands.size()!=5){
            return false;
        }else{
            HashMap<Object,Integer> map = new HashMap<>();
            for (int i = 0; i < pokerHands.size(); i++) {
                Card card = pokerHands.get(i);
                if (map.containsKey(card.getSuit())) {
                    map.put(card.getSuit(), map.get(card.getSuit()) + 1);
                } else {
                    map.put(card.getSuit(), 1);
                }
            }
            Set<Map.Entry<Object,Integer>> entries = map.entrySet();
            for (Map.Entry<Object,Integer> entry : entries){
                Integer value = entry.getValue();
                if(value==5){
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isStraight() {
        if(pokerHands.size()!=5){
            return false;
        }else{
            ArrayList<String> ranks = new ArrayList<>(Arrays.asList("A","2","3","4","5","6","7","8","9","T","J","Q","K"));
            ArrayList<Integer> position = new ArrayList<>();
            for (int i = 0; i < 13; i++) {
                position.add(0);
            }
            for (int i = 0; i < pokerHands.size(); i++) {
                Card card = pokerHands.get(i);
                String temp = card.toString().substring(0, 1);
                position.set(ranks.indexOf(temp),position.get(ranks.indexOf(temp))+1);
            }
            int num = 0;
            for (int i = 0; i < 13; i++) {
                if(position.get(i)==0){
                    num = 0;
                 }else if (position.get(i)==1){
                    num+=1;
                    if(num==5){
                        return true;
                    }
                }else{
                    return false;
                }

            }

            if(position.get(0)==1&&position.get(12)==1&&position.get(11)==1&&position.get(10)==1&&position.get(9)==1){
                return true;
            }
            return false;
        }
    }

}