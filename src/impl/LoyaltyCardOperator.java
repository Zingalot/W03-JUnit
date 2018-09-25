package impl;

import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;

/**
 * This class represents a simple loyalty card operator.
 *
 */
public class LoyaltyCardOperator extends AbstractFactoryClient implements ILoyaltyCardOperator {

    @Override
    public void registerOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerAlreadyRegisteredException {
        // TODO Auto-generated method stub
    }

    @Override
    public void unregisterOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
    }

    @Override
    public void processMoneyPurchase(String ownerEmail, int pence) throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
    }

    @Override
    public void processPointsPurchase(String ownerEmail, int pence)
            throws InsufficientPointsException, OwnerNotRegisteredException {
        // TODO Auto-generated method stub
    }

    @Override
    public int getNumberOfCustomers() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalNumberOfPoints() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfPoints(String ownerEmail) throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfUses(String ownerEmail) throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ILoyaltyCardOwner getMostUsed() throws OwnerNotRegisteredException {
        // TODO Auto-generated method stub
        return null;
    }

}
