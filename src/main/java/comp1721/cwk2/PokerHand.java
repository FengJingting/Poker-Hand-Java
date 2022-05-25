package comp1721.cwk2;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

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
    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < pokerHands.size(); i++){
            Card card = pokerHands.get(i);
            str.concat(card.toString());
            str.concat(" ");
        }
            return str;
    }

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
            map = check(map);
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

    public boolean isStraight(){
        return false;
    }

}