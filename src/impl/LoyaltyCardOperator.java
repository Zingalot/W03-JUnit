package impl;

import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;

import java.util.*;

/**
 * This class represents a simple loyalty card operator.
 *
 */
public class LoyaltyCardOperator extends AbstractFactoryClient implements ILoyaltyCardOperator {
    private LinkedHashMap<String,LoyaltyCard> registeredOwners;
    private LinkedHashMap<String,Integer> cardUses;
    private int numberOfCustomers;

    public LoyaltyCardOperator(){
        this.registeredOwners = new LinkedHashMap();
        this.cardUses = new LinkedHashMap();
        this.numberOfCustomers = 0;
    }

    @Override
    public void registerOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerAlreadyRegisteredException {
        boolean alreadyRegistered = false;
        if(registeredOwners.containsKey(loyaltyCardOwner.getEmail())){
            alreadyRegistered = true;
        }

        if(alreadyRegistered){
            throw new OwnerAlreadyRegisteredException("This email address has already been registered");
        }else{
            this.registeredOwners.put(loyaltyCardOwner.getEmail(),new LoyaltyCard(loyaltyCardOwner));
            this.cardUses.put(loyaltyCardOwner.getEmail(),0);
            this.numberOfCustomers++;
        }
    }

    @Override
    public void unregisterOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerNotRegisteredException {
        boolean unregistered = false;
        if(registeredOwners.containsKey(loyaltyCardOwner.getEmail())){
            registeredOwners.remove(loyaltyCardOwner.getEmail());
            cardUses.remove(loyaltyCardOwner.getEmail());
            unregistered = true;
        }

        if(!unregistered){
            throw new OwnerNotRegisteredException("This email address is not registered");
        }
    }

    @Override
    public void processMoneyPurchase(String ownerEmail, int pence) throws OwnerNotRegisteredException {
        if(!registeredOwners.containsKey(ownerEmail)){
            throw new OwnerNotRegisteredException("This email address is not registered");
        }
        LoyaltyCard cardForUpdate = registeredOwners.get(ownerEmail);
        cardForUpdate.addPoints(pence/100);
        registeredOwners.put(ownerEmail,cardForUpdate);
        Integer useUpdate = cardUses.get(ownerEmail);
        useUpdate++;
        cardUses.put(ownerEmail,useUpdate);

    }

    @Override
    public void processPointsPurchase(String ownerEmail, int pence)
            throws InsufficientPointsException, OwnerNotRegisteredException {
        if(!registeredOwners.containsKey(ownerEmail)){
            throw new OwnerNotRegisteredException("This email address is not registered");
        }
        LoyaltyCard cardForUpdate = registeredOwners.get(ownerEmail);
        cardForUpdate.usePoints(pence);
        registeredOwners.put(ownerEmail,cardForUpdate);
        Integer useUpdate = cardUses.get(ownerEmail);
        useUpdate++;
        cardUses.put(ownerEmail,useUpdate);
    }

    @Override
    public int getNumberOfCustomers() {
        return this.numberOfCustomers;
    }

    @Override
    public int getTotalNumberOfPoints() {
        int totalPoints = 0;
        Iterator iterator = registeredOwners.keySet().iterator();
        while(iterator.hasNext()){
            totalPoints += registeredOwners.get(iterator.next()).getNumberOfPoints();
        }
        return totalPoints;
    }

    @Override
    public int getNumberOfPoints(String ownerEmail) throws OwnerNotRegisteredException {
        if(registeredOwners.containsKey(ownerEmail)) {
            return registeredOwners.get(ownerEmail).getNumberOfPoints();
        }
        else {
            throw new OwnerNotRegisteredException("This email address is not registered");
        }
    }

    @Override
    public int getNumberOfUses(String ownerEmail) throws OwnerNotRegisteredException {
        if(cardUses.containsKey(ownerEmail)) {
            return cardUses.get(ownerEmail);
        }
        else {
            throw new OwnerNotRegisteredException("This email address is not registered");
        }
    }

    @Override
    public ILoyaltyCardOwner getMostUsed() throws OwnerNotRegisteredException {
        int maxUses = 0;
        String currentEmail = "";
        String maxEmail = "";
        Iterator iterator = cardUses.keySet().iterator();
        while(iterator.hasNext()){
            currentEmail = (String) iterator.next();
            if(cardUses.get(currentEmail)> maxUses){
                maxUses = cardUses.get(currentEmail);
                maxEmail = currentEmail;
            }

        }

        if(registeredOwners.containsKey(maxEmail)) {
            return registeredOwners.get(maxEmail).getOwner();
        }
        else{
            return null;
        }
    }
}
