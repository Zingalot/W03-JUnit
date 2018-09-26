package impl;

import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a simple loyalty card operator.
 *
 */
public class LoyaltyCardOperator extends AbstractFactoryClient implements ILoyaltyCardOperator {
    private List<ILoyaltyCardOwner> registeredOwners;

    public LoyaltyCardOperator(){
        this.registeredOwners = new ArrayList<>();
    }

    @Override
    public void registerOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerAlreadyRegisteredException {
        boolean alreadyRegistered = false;
        for(int x = 0; x <= this.registeredOwners.size()-1; x++){
            if(registeredOwners.get(x).getEmail().equals(loyaltyCardOwner.getEmail())){
                alreadyRegistered = true;
            }
        }
        if(alreadyRegistered){
            throw new OwnerAlreadyRegisteredException("This email address has already been registered");
        }else{
            this.registeredOwners.add(loyaltyCardOwner);
        }
    }

    @Override
    public void unregisterOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerNotRegisteredException {
        boolean unregistered = false;
        for(int x = 0; x <= this.registeredOwners.size()-1; x++){
            if(registeredOwners.get(x).getEmail().equals(loyaltyCardOwner.getEmail())){
                unregistered = true;
                registeredOwners.remove(x);
            }
        }
        if(!unregistered){
            throw new OwnerNotRegisteredException("This email address has not been registered before");
        }
    }

    @Override
    public void processMoneyPurchase(String ownerEmail, int pence) throws OwnerNotRegisteredException {
        // TODO Rewrite list to a Map or similar to allow for key-value pairs
        // TODO This allows for owner's points to be kept in the same collection as their registration
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
