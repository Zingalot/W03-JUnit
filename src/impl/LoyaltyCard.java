package impl;

import common.InsufficientPointsException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOwner;

/**
 * This class represents a Loyalty Card, recording information relating to an owners use of the card.
 *
 */
public class LoyaltyCard implements ILoyaltyCard {
    private ILoyaltyCardOwner loyaltyCardOwner;
    private int numberOfPoints;
    private int numberOfUses;

    public LoyaltyCard(ILoyaltyCardOwner loyaltyCardOwner){
        this.loyaltyCardOwner = loyaltyCardOwner;
        this.numberOfPoints = 0;
    }

    @Override
    public ILoyaltyCardOwner getOwner() {
        return this.loyaltyCardOwner;
    }

    @Override
    public int getNumberOfUses() {
        return this.numberOfUses;
    }

    @Override
    public int getNumberOfPoints() {
        return this.numberOfPoints;
    }

    @Override
    public void addPoints(int points) {
        if(points>0) {
            this.numberOfPoints += points;
            this.numberOfUses++;
        }
    }

    @Override
    public void usePoints(int points) throws InsufficientPointsException {
        if(this.numberOfPoints >= points && points > 0){
            this.numberOfPoints -= points;
            this.numberOfUses++;
        }
        else {
            if(points < 0){
                throw new InsufficientPointsException("Invalid Number of Points to use");
            }
            else{
                throw new InsufficientPointsException("Insufficient Points");
            }

        }
    }

}
