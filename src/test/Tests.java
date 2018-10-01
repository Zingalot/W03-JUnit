package test;

import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;
import impl.LoyaltyCardOwner;
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
//TODO Comment all tests and variables
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

    /**
     *
     */
    @Test
    public void loyaltyCardAddPoints(){
        card.addPoints(10);
        assertEquals(10, card.getNumberOfPoints());
    }

    /**
     * Tests the edge case for 'addPoints' where the specified number of points to be added is
     * negative. Should not make a difference to the number of points.
     */
    @Test
    public void loyaltyCardAddNegativePoints(){
        card.addPoints(-20);
        assertEquals(0, card.getNumberOfPoints());
    }

    /**
     * Tests the centre case for 'usePoints'. The card should have 10 fewer points than before the
     * points are used.
     */
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

    /**
     * Tests the edge case of trying to use a negative number of points, should throw an
     * insufficientPointsException.
     */
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

    /**
     * Tests the edge case of 'usePoints' in which the card does not have enough points to make
     * the purchase. Should throw the insufficientPointsException.
     */
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

    /**
     * Tests the centre case of 'NumberOfUses' for a card. The loops just create a sample.
     */
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

    /**
     * Tests the centre case of 'getEmail'. Should be equal to 'email'.
     */
    @Test
    public void loyaltyCardOwnerGetEmail(){
        assertEquals(email, owner.getEmail());
    }

    /**
     * Tests the centre case of 'getName'. Should be equal to 'name'.
     */
    @Test
    public void loyaltyCardOwnerGetName(){
        assertEquals(name, owner.getName());
    }

    /**
     * Tests the centre case of 'registerOwner', should not throw any exceptions.
     */
    @Test
    public void loyaltyCardOperatorRegisterOwner(){
        try{
            operator.registerOwner(owner);
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }

    /**
     * Tests the edge case of trying to register an already registered owner, should throw an
     * OwnerAlreadyRegisteredException.
     */
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

    /**
     * Tests the centre case of 'unregisterOwner'. Owner is registered and then unregistered.
     */
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

    /**
     * Tests the edge case of trying to unregister an already unregistered owner, should return
     * an OwnerNotRegisteredException.
     */
    @Test
    public void loyaltyCardOperatorInvalidUnregister(){
        try{
            operator.unregisterOwner(owner);
            fail("OwnerNotRegisteredException should be thrown");
        } catch(OwnerNotRegisteredException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the centre case of processMoneyPurchase. Owner should gain 23 points during the purchase
     */
    @Test
    public void loyaltyCardOperatorProcessMoneyPurchase(){
        try{
            operator.registerOwner(owner);
            int currentPoints = operator.getNumberOfPoints(owner.getEmail());
            operator.processMoneyPurchase(owner.getEmail(),2300);
            assertEquals(operator.getNumberOfPoints(owner.getEmail()), currentPoints + 23);
        } catch(OwnerNotRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }

    /**
     * Tests the centre case of processPointsPurchase. Owner should spend 40 points during the purchase
     */
    @Test
    public void loyaltyCardOperatorProcessPointsPurchase(){
        try{
            operator.registerOwner(owner);
            operator.processMoneyPurchase(owner.getEmail(),8000);
            int currentPoints = operator.getNumberOfPoints(owner.getEmail());
            operator.processPointsPurchase(owner.getEmail(), 40);
            assertEquals(operator.getNumberOfPoints(owner.getEmail()), currentPoints - 40);
        } catch(InsufficientPointsException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(OwnerNotRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }

    /**
     * Tests the centre case for counting the number of customers. Two have been registered, so
     * two should be counted
     */
    @Test
    public void loyaltyCardOperatorGetNumberOfCustomers(){
        try{
            operator.registerOwner(owner);
            assertEquals(operator.getNumberOfCustomers(),1);
            operator.registerOwner(new LoyaltyCardOwner("TestTwo", "test2@test.com"));
            assertEquals(operator.getNumberOfCustomers(),2);
        }
        catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }
    //TODO Test for counting zero customers

    /**
     * Tests the edge case for registering owners where that owner has already been registered. Should
     * return an OwnerAlreadyRegisteredException.
     */
    @Test
    public void loyaltyCardOperatorGetNumberOfCustomersDuplicate(){
        try{
            operator.registerOwner(owner);
            operator.registerOwner(owner);
            fail("Should throw OwnerAlreadyRegisteredException");
        }
        catch(OwnerAlreadyRegisteredException e){
            assertEquals(operator.getNumberOfCustomers(),1);
        }
    }
    //TODO Test that two owners with the same email cannot be registered (check overwriting too)

    /**
     * Tests the centre case for 'processMoneyPurchase'. Owner has spend 1200 pence, so should receive
     * 12 points.
     */
    @Test
    public void loyaltyCardOperatorOwnerPoints(){
        try{
            operator.registerOwner(owner);
            operator.processMoneyPurchase(owner.getEmail(),1200);
            assertEquals(operator.getNumberOfPoints(owner.getEmail()),12);
        } catch(OwnerNotRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }

    /**
     * Tests the edge case of 'getNumberOfPoints' where the owner has not been registered. Should return
     * an owner not registered exception.
     */
    @Test
    public void loyaltyCardOperatorUnregisteredOwnerPoints(){
        try{
            operator.getNumberOfPoints(owner.getEmail());
            fail("Should throw OwnerNotRegisteredException");
        } catch(OwnerNotRegisteredException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the centre case for 'getTotalNumberOfPoints', owner and 'TestTwo' are both used, and the
     * total number of points is checked.
     */
    @Test
    public void loyaltyCardOperatorGetTotalPoints(){
        try {
            operator.registerOwner(owner);
            operator.registerOwner(new LoyaltyCardOwner("TestTwo", "test2@test.com"));
            operator.processMoneyPurchase(owner.getEmail(),1200);
            operator.processMoneyPurchase("test2@test.com",3000);
            assertEquals(operator.getTotalNumberOfPoints(),42);
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(OwnerNotRegisteredException e) {
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }

    /**
     * Tests the centre case for 'getNumberOfUses', owner is used three times and the number of uses is
     * checked after each usage. Money and points are both used to check that both are included.
     */
    @Test
    public void loyaltyCardOperatorGetUses(){
        try{
            operator.registerOwner(owner);
            operator.processMoneyPurchase(owner.getEmail(),1200);
            assertEquals(operator.getNumberOfUses(owner.getEmail()),1);
            operator.processMoneyPurchase(owner.getEmail(),3000);
            assertEquals(operator.getNumberOfUses(owner.getEmail()),2);
            operator.processPointsPurchase(owner.getEmail(),25);
            assertEquals(operator.getNumberOfUses(owner.getEmail()),3);
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(OwnerNotRegisteredException e) {
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(InsufficientPointsException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }
    //TODO Test that insufficient points purchases do not increment card uses

    /**
     * Tests the centre case for 'getMostUsed'. Owner is used twice, 'TestTwo' is used once, so owner
     * should be returned.
     */
    @Test
    public void loyaltyCardOperatorMostUsed(){
        try{
            operator.registerOwner(owner);
            operator.processMoneyPurchase(owner.getEmail(),1200);
            operator.processMoneyPurchase(owner.getEmail(),3000);
            operator.registerOwner(new LoyaltyCardOwner("TestTwo", "test2@test.com"));
            operator.processMoneyPurchase("test2@test.com",3000);
            assertEquals(operator.getMostUsed(),owner);
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(OwnerNotRegisteredException e) {
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }

    /**
     * Tests the 'getMostUsed' function on the edge case that two cards have equal uses.
     * The card that was registered first is returned due to the LinkedHashMap preserving insertion
     * order in an iterator. This suits the purposes of the program fine.
     */
    @Test
    public void loyaltyCardOperatorMostUsedEqual(){
        try{
            operator.registerOwner(owner);
            operator.registerOwner(new LoyaltyCardOwner("TestTwo", "test2@test.com"));
            operator.processMoneyPurchase("test2@test.com",3000);
            operator.processMoneyPurchase("test2@test.com",3500);
            operator.processMoneyPurchase(owner.getEmail(),1200);
            operator.processMoneyPurchase(owner.getEmail(),3000);
            assertEquals(operator.getMostUsed(),owner);
        } catch(OwnerAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        } catch(OwnerNotRegisteredException e) {
            System.out.println(e.getMessage());
            fail("Should not throw an exception");
        }
    }
}
