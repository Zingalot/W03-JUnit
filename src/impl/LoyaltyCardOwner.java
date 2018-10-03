package impl;

import interfaces.ILoyaltyCardOwner;

/**
 * This class represents loyalty card owners.
 *
 */
public class LoyaltyCardOwner implements ILoyaltyCardOwner {

    private String name;
    private String email;

    /**
     * Creates a new instance of the class.
     * @param name the name of the owner
     * @param email the email of the owner
     */
    public LoyaltyCardOwner(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
