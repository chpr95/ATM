package atm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    /**
     * The first name of the user
     */
    private String firstname;
    /**
     * The lastname of the user
     */
    private String lastname;
    /**
     * The ID number of the user
     */
    private String uuid;
    /**
     * The MD5 hash of the user's pin number
     */
    private byte[] pinHash;
    /**
     * The list of accounts for this user
     */
    private ArrayList<Account> accounts;

    /**
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's account pin
     * @param theBank   the Bank Object that the user is a customer of
     */

    public User(String firstName, String lastName, String pin, Bank theBank) {

        //set user's name
        this.firstname = firstName;
        this.lastname = lastName;

        // store the pin's MD5 hash, rather than the original value for security reason
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error caught NoSuchAlgorithmException");
            throw new RuntimeException(e);

        }
        // get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        //  create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //print log message
        System.out.printf("New User %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
    }

    /**
     * Add an account for the user
     *
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct) {

        this.accounts.add(anAcct);
    }

    /**
     * Return the user's UUID
     *
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Check whether a given pin matches the true User pin
     *
     * @param aPin the pin to check
     * @return whether the pin is valid or not
     */

    public String getFirstName() {
        return this.firstname;
    }

    /**
     * Print summaries for the accounts of this user
     */
    public void printAccountsSummary() {
        System.out.printf("\n\n%s' accounts summary\n", this.firstname);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("  %d) %s\n", a + 1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");

            System.exit(1);
        }
        return false;


    }

    /**
     * Get the number of accounts of the user
     * @return  the number of accounts
     */
    public int numAccounts(){
        return this.accounts.size();
    }

    public void printAcctTransactionHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransactionHistory();
    }

    /**
     * Get the balance of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the balance of the account
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the UUID of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}









