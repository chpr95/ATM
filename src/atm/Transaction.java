package atm;

import java.util.Date;

public class Transaction {
    /**
     * The amount of this account
     */
    private double amount;
    /**
     * The time and date of this transaction
     */
    private Date timestamp;
    /**
     * A memo for this transaction
     */
    private String memo;
    /**
     * The account in which the transaction was performed
     */
    private Account inAccount;

    /**
     * Create a new transaction
     * @param amount    the amount transacted
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     * Create a new transaction
     * @param amount    the amount transacted
     * @param memo      the memo for the transaction
     * @param inAccount the amount the transaction belongs to
     */

    public Transaction(double amount, String memo, Account inAccount) {
        //call the two args-constructor first
        this(amount,inAccount);
        //set the memo
        this.memo = memo;

    }
}
