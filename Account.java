import java.util.ArrayList;
public class Account {

	private String name;
	
	private String IDcode;
	
	private User holder;
	
	private ArrayList<Transaction> transactions;
	
	/**
	 * Create new Account
	 * @param name
	 * @param holder
	 * @param theBank
	 */
	public Account(String name, User holder, Bank theBank) {
		//set the account name and holder
		this.name = name;
		this.holder = holder;
		
		//get new account IDcode
		this.IDcode = theBank.getNewAccountIDcode();
		
		//init transactions
		this.transactions = new ArrayList<Transaction>();
				
	}
	/**
	 * get the account ID
	 * @return
	 */
	public String getIDcodd() {
		return this.IDcode;
	}
	
	/**
	 * Get summary line for the account
	 * @return
	 */
	public String getSummaryLine() {
		
		//get the account's balance
		double balance = this.getBalance();
		
		//format the summary line, depending on the whether the balance is negative
		if(balance >=0) {
			return String.format("%s : tenge%.02f : %s", this.IDcode, balance, this.name);
			
		}else {
			return String.format("%s : tenge(%.02f) : %s", this.IDcode, balance, this.name);
		}
		
	}
	
	public double getBalance() {
		
		double balance =0;
		for(Transaction t: this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}
	
	
	/**
	 *Print the transaction history of the account 
	 */
	public void printTransHistory() {
		System.out.printf("\nTransaction history for account %s\n", this.IDcode);
		for (int i = this.transactions.size()-1; i>=0; i--) {
			System.out.println(this.transactions.get(i).getSummaryLine());
		}
		System.out.println();
	}
	
	
	
	/**
	 * Add a new transaction in this account
	 * @param amount
	 * @param memo
	 */
	public void addTransaction(double amount, String memo) {
		
		//create new transaction object and add it to our list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
	}

}
