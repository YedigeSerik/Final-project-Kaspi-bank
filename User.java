import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
public class User{
	
	private String firstName;
	
	private String lastName;
	
	private String IDcode;
	
	private byte pinHash[];
	
	private ArrayList<Account> accounts;
	
	/**
	 * Create new user
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @param theBank
	 */
	public User(String firstName, String lastName, String pin, Bank theBank) 
	{
		
		//set user's name
		this.firstName = firstName;
		this.lastName = lastName;
		
		//store the pin's MD5 hash, rather the original value, for
		//security reasons; 
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		}catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		//get a new, unique universal ID for the user
		this.IDcode = theBank.getNewUserIDcode();
		
		//create empty list of accounts
		this.accounts = new ArrayList<Account>();
		
		//print log message
		System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.IDcode);
	}
	/**
	 * Add an account for the user
	 * @param anAcct
	 */
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	/**
	 * Return the user's IDcode
	 * @return
	 */
	public String getIDcodd() {
		return this.IDcode;
	}
	
	/**
	 * Check whether a given pin matches the true User pin
	 * @param aPin
	 * @return
	 */
	public boolean validatePin(String aPin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		}catch (NoSuchAlgorithmException e){
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}
	
	/**
	 * Return the user's first name
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}

	
	/**
	 * Print summaries for the accounts of this user.
	 */
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int i=0; i<this.accounts.size(); i++) {
			System.out.printf("  %d) %s\n", i+1, this.accounts.get(i).getSummaryLine());
		}
		System.out.println();
	}
	
	/**
	 * Get the number of the accounts of the user
	 * @return
	 */
	public int numAccounts() {
		return this.accounts.size();
	}
	
	/**
	 * Print transaction history for a particular account
	 * @param acctIdx
	 */
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}

	/**
	 * Get the balance of a particular account
	 * @param acctIdx
	 * @return
	 */
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	/**
	 * Get IDcodd of a particular account
	 * @param acctIdx
	 * @return
	 */
	public String getAcctIDcodd(int acctIdx) {
		return this.accounts.get(acctIdx).getIDcodd();
	}
	
	/**
	 * Add a transaction to a particular account
	 * @param acctIdx
	 * @param amount
	 * @param memo
	 */
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
}