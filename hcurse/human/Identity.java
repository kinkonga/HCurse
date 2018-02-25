package hcurse.human;


/**
 * Identity - Create Identity
 * 
 * @author Nicola Lacambre
 */
public class Identity {
	
	// TODO Human Age / BirthDate
	
	
	// ENUM -----------------------------------------------------
	public enum Id {
		FIRST(0), LAST(1), NICK(2);
		private int id = 0;

		Id(int id) {
			this.id = id;
		}
	}
	
	// VARIABLES ------------------------------------------------
	
	private String[] name = new String[3];
	
	// CONSTRUCTOR ----------------------------------------------
	
	/**
	 * Build full Identity using an Array of Names
	 * 
	 * @param names
	 *            String[] Array of Names
	 * @return Identity Ctor(String[])
	 */
	public static Identity buildFull(String[] name) {
		return new Identity(name);
	}
	
	// PRIVATE --------------------------------------------------
	
	private Identity(String[] name) {
		this.createId(name);
	}
	private void createId(String[] name) {
		for (Id i : Id.values()) {
			this.name[i.id] = name[i.id];
		}
	}

	// PUBLIC ---------------------------------------------------
	
	/**
	 * Return a String containing the part of name you want.
	 * 
	 * @param n
	 *            Id FIRST = FirstName LAST = LastName NICK = NickName
	 * @return String name
	 */
	public String getName(Id n) {

		return this.name[n.id];

	}
	
}
