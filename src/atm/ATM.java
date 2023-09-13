package atm;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        // init scanner
        Scanner sc = new Scanner(System.in);

        // init bank

        Bank theBank = new Bank("Bank of Paris");

        // add a user, which also creates a savings account
        User aUser = theBank.addUser("John","Doe","1234");

        // add a checking account for our user
        Account newAccount = new Account("savings",aUser,theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);
    }
}
