package impl;

import interfaces.IFactory;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;


/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public ILoyaltyCardOwner makeLoyaltyCardOwner(String email, String name) {
        ILoyaltyCardOwner loyaltyCardOwner = new LoyaltyCardOwner(name, email);
        return loyaltyCardOwner;
    }

    @Override
    public ILoyaltyCard makeLoyaltyCard(ILoyaltyCardOwner loyaltyCardOwner) {
        ILoyaltyCard loyaltyCard = new LoyaltyCard(loyaltyCardOwner);
        return loyaltyCard;
    }

    @Override
    public ILoyaltyCardOperator makeLoyaltyCardOperator() {
        ILoyaltyCardOperator loyaltyCardOperator = new LoyaltyCardOperator();
        return loyaltyCardOperator;
    }
}
