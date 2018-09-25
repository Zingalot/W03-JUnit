package test;

import common.AbstractFactoryClient;
import impl.LoyaltyCardOwner;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOwner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * This is a JUnit test class for the loyalty card ADT.
 *
 */
public class Tests extends AbstractFactoryClient {

    private ILoyaltyCardOwner owner;

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ILoyaltyCardOwner.
     */
    @Test
    public void loyaltyCardOwnerCreationNonNull() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        assertFalse(loyaltyCardOwner == null);
    }

    @Before
    public void setupOwner(){
        owner = getFactory().makeLoyaltyCardOwner("jon@jon.com","Jon");
    }

    @Test
    public void loyaltyCardCreationNonNull(){
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(owner);
        assertFalse(loyaltyCard == null);
    }

}
