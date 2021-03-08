import java.util.ArrayList;
import java.util.Random;
public class Bank {

	public String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	/**
	 * Create a new Bank object with empty lists of users and accounts 
	 * @param name
	 */
	public Bank(String name) {
		
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	/**
	 * Generate a new universally unique ID for a user.
	 * @return the IDcode
	 */
	public String getNewUserIDcode() {
		
		String IDcode;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique;
		
		//continue looping until we get a unique ID
		do {
			
			IDcode="";
			for(int i=0; i<len; i++) {
				IDcode+=((Integer)rng.nextInt(10)).toString();
			}
			
			//check is it unique
			nonUnique=false;
			for(User u:this.users) {
				if(IDcode.compareTo(u.getIDcodd()) ==0){
					nonUnique=true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return IDcode;
	}
	
	
	public String getNewAccountIDcode() {
		
		String IDcode;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique;
		
		//continue looping until we get a unique ID
		do {
			
			IDcode="";
			for(int i=0; i<len; i++) {
				IDcode+=((Integer)rng.nextInt(10)).toString();
			}
			
			//check is it unique
			nonUnique=false;
			for(Account a:this.accounts) {
				if(IDcode.compareTo(a.getIDcodd()) ==0){
					nonUnique=true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return IDcode;
		
	}
	
	/**
	 * Add an account
	 * @param anAcct
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	/**
	 * Create a new user of the bank
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @return
	 */
	public User addUser(String firstName, String lastName, String pin) {
		
		//create a new User object and add it to our list
		User newUser= new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		//create a savings account for the user and add to User and Bank accounts list
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	/**
	 * Get the User object associated with a particular UserID and pin if they are valid
	 * @param UserID
	 * @param pin
	 * @return
	 */
	public User userLogin(String UserID, String pin) {
		
		//search through list of users
		for(User u: this.users) {
			//check user ID of correctness
			if (u.getIDcodd().compareTo(UserID)==0 && u.validatePin(pin)) {
				return u;
			}
		}
		
		//if we have not found user or have an incorrect pin
		return null;
	}
	
	/**
	 * Get the name of the bank
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
}
