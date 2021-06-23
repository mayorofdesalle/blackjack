
import java.util.ArrayList;

public class Dealer {
    
    private ArrayList<Card> hand;
    private int cardToPrint;
    
    public Dealer() {
        hand = new ArrayList<Card>();
        cardToPrint = 0;
    }
    
    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public void addToHand(Card card) {
        hand.add(card);
    }
    
    public void resetHand() {
        hand = new ArrayList<Card>();
        cardToPrint = 0;
    }
    
    public int cardToPrint() {
        int temp = cardToPrint;
        cardToPrint++;
        return temp;
    }
    
    public int getHandValue() {
        // It is important here to take care of two possible values of A
        // If the hand has an A, it may have two values
        ArrayList<Integer> handValues = new ArrayList<Integer>();
        handValues.add(0);

        for(Card card: hand) {
            // If the card is A, add different values to the hands
            if (card.getName().charAt(0) == 'A') {
                // Copy the current hands
                int size = handValues.size();
                for (int i = 0; i < size; i++){
                    handValues.add(handValues.get(i));
                }
                
                // Add one value to a half and the other value to another half
                for (int i = 0; i < handValues.size(); i++) {
                    if (i < size) {
                        handValues.set(i, handValues.get(i) + card.getValue()); // Add 1
                    }
                    else {
                        handValues.set(i, handValues.get(i) + 11); // Add 11
                    }
                }
            }
            // Else just add the value
            else {
                for (int i = 0; i < handValues.size(); i++) {
                    handValues.set(i, handValues.get(i) + card.getValue());
                }
            }
        }
        
        // Now we have two possible values of a hand
        // We have to return the value of the best option (if there is any)
        
        // If all the values are over 21 it doesn't matter
        boolean check = true;
        for (int value: handValues) {
            if (value < 22)
                check = false;
        }
        
        if (check) {
           return handValues.get(0);
        } else {
            // Find the best value under 22 and return it
            int bestHandValue = 0;
            
            for (int value: handValues) {
                if(value < 22) {
                    if (value > bestHandValue) {
                        bestHandValue = value;
                    }
                }
            }
            
            return bestHandValue;
        }
    }
}
