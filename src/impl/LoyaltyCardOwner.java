package impl;

import interfaces.ILoyaltyCardOwner;

/**
 * This class represents loyalty card owners.
 *
 */
public class LoyaltyCardOwner implements ILoyaltyCardOwner {

    String name;
    String email;

    public LoyaltyCardOwner(String name, String email){
        this.name = name;
        this.email = email;
    }

    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

}
