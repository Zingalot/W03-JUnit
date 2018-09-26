package test;

import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is a JUnit test class for the loyalty card ADT.
 *
 */
public class Tests extends AbstractFactoryClient {

    private ILoyaltyCardOwner owner;
    private ILoyaltyCard card;
    private ILoyaltyCardOperator operator;
    private String email;
    private String name;


    @BeforeClass
    public static void organiseOutput(){
        System.out.println();
    }

    @Before
    public void setupOwner(){
        email = "test@test.com";
        name = "Test";
        owner = getFactory().makeLoyaltyCardOwner(email, name);
        card = getFactory().makeLoyaltyCard(owner);
        operator = getFactory().makeLoyaltyCardOperator();
    }

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ILoyaltyCardOwner.
     */
    @Test
    public void loyaltyCardOwnerCreationNonNull() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        assertFalse(loyaltyCardOwner == null);
    }

    @Test
    public void loyaltyCardCreationNonNull(){
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(owner);
        assertFalse(loyaltyCard == null);
    }

    @Test
    public void loyaltyCardOperatorCreationNonNull(){
        ILoyaltyCardOperator loyaltyCardOperator = getFactory().makeLoyaltyCardOperator();
        assertFalse(loyaltyCardOperator == null);
    }

    @Test
    public void loyaltyCardGetOwner(){
        assertEquals(card.getOwner(), owner);
    }

    @Test
    public void loyaltyCardAddPoints(){
        card.addPoints(10);
        assertEquals(10, card.getNumberOfPoints());
    }

    @Test
    public void loyaltyCardAddNegativePoints(){
        card.addPoints(-20);
        assertEquals(0, card.getNumberOfPoints());
    }

    @Test
    public void loyaltyCardUsePoints(){
        card.addPoints(100);
        int currentPoints = card.getNumberOfPoints();
        try {
            card.usePoints(10);
            assertEquals(currentPoints - 10, card.getNumberOfPoints());
        }
        catch(InsufficientPointsException e){
            System.out.println(e.getMessage());
            fail("Should not have thrown an exception");
        }
    }

    @Test
    public void loyaltyCardUseNegativePoints(){
        try {
            card.usePoints(-10);
            fail("Should have thrown InsufficientPointsException" );
        }
        catch(InsufficientPointsException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void loyaltyCardInsufficientPoints(){
        try{
            card.usePoints(1000);
            fail("Should have thrown InsufficientPointsException");
        }
        catch( InsufficientPointsException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void loyaltyCardNumberOfUses(){
        int numberOfUses = 0;
        for(int x = 0; x < 15; x++){
            card.addPoints(5);
            numberOfUses++;
            if(x%5 == 0){
                try{
                    card.usePoints(3);
                    numberOfUses++;
                } catch(InsufficientPointsException e){
                    System.out.println(e.getMessage());
                    fail("Should not have thrown an exception");
                }

            }
        }
        assertEquals(card.getNumberOfUses(),numberOfUses);
    }

    @Test
    public void loyaltyCardOwnerGetEmail(){
        assertEquals(email, owner.getEmail());
    }

    @Test
    public void loyaltyCardOwnerGetName(){
        assertEquals(name, owner.getName());
    }

    @Test
    public void loyaltyCardOperatorRegisterOwner(){
        try{
            operator.registerOwner(owner);
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }

    @Test
    public void loyaltyCardOperatorAlreadyRegistered(){
        try{
            operator.registerOwner(owner);
            operator.registerOwner(owner);
            fail("Should throw OwnerAlreadyRegisteredException");
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void loyaltyCardOperatorUnregister(){
        try{
            operator.registerOwner(owner);
            operator.unregisterOwner(owner);
        } catch(OwnerNotRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch (OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }

    @Test
    public void loyaltyCardOperatorInvalidUnregistr(){
        try{
            operator.unregisterOwner(owner);
            fail("OwnerNotRegisteredException should be thrown");
        } catch(OwnerNotRegisteredException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void loyaltyCardOperatorProcessMoneyPurchase(){
        try{
            operator.processMoneyPurchase(owner.getEmail(),23456);
        } catch(OwnerNotRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }
}
