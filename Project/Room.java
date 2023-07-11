package Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Room implements AutoCloseable{
	protected static Server server;// used to refer to accessible server functions
	private String name;
	private List<ServerThread> clients = new ArrayList<ServerThread>();
	private boolean isRunning = false;
	// Commands
	private final static String COMMAND_TRIGGER = "/";
	private final static String CREATE_ROOM = "createroom";
	private final static String JOIN_ROOM = "joinroom";
	private final static String DISCONNECT = "disconnect";
	private final static String LOGOUT = "logout";
	private final static String LOGOFF = "logoff";
	private final static String FLIP = "flip";
	private final static String ROLL = "roll";

	public Room(String name) {
		this.name = name;
		isRunning = true;
	}

	private void info(String message) {
		System.out.println(String.format("Room[%s]: %s", name, message));
	}

	public String getName() {
		return name;
	}

	protected synchronized void addClient(ServerThread client) {
		if (!isRunning) {
			return;
		}
		client.setCurrentRoom(this);
		if (clients.indexOf(client) > -1) {
			info("Attempting to add a client that already exists");
		} else {
			clients.add(client);
			new Thread() {
				@Override
				public void run() {
					// slight delay to let potentially new client to finish
					// binding input/output streams
					// comment out the Thread.sleep to see what happens
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//sendMessage(client, "joined the room " + getName());
					sendConnectionStatus(client, true);
				}
			}.start();

		}
	}

	protected synchronized void removeClient(ServerThread client) {
		if (!isRunning) {
			return;
		}
		clients.remove(client);
		// we don't need to broadcast it to the server
		// only to our own Room
		if (clients.size() > 0) {
			//sendMessage(client, "left the room");
			sendConnectionStatus(client, false);
		}
		checkClients();
	}

	/***
	 * Checks the number of clients.
	 * If zero, begins the cleanup process to dispose of the room
	 */
	private void checkClients() {
		// Cleanup if room is empty and not lobby
		if (!name.equalsIgnoreCase("lobby") && clients.size() == 0) {
			close();
		}
	}
//-------------------------------Coin Flip Function-------------------------------------------------------
/*
    UCID: sjc65
    Date: 07/09/2023
    Explanation: The "coinFlip()" function creates a random object called "rand" and a String variable called "message". 
	The ".nextInt(2)" is used on the rand object to randomize between 0 and 1 and then assign the result of it to the 
	"result" variable. If the result is equal to 0, then the message's value is "heads" otherwise the value is "tails".
	Lastly, the "message" variable is used in the "sendMessage" parameters to be displayed to all clients.
*/
	protected synchronized void coinFlip(ServerThread client) {
		String message;
		Random rand = new Random();

		int result = rand.nextInt(2);
    	if(result == 0) {
    		message = " flipped a coin! Result is heads";
		} else {
    		message = " flipped a coin! Result is tails";
		}
		
		sendMessage(client, message);
    }
//----------------------Dice Roll Format: /roll #-----------------------------------------------------------
/*
	UCID: sjc65
	Date: 07/11/2023
	Explanation: In the "diceRoll()" method, it uses "num" as it's parameter and processes the "/roll #" command format.
	First a Random object, called rand, is created, then "rand.nextInt(num)" is used to return a random value between
	0 and num, then that value is assigned to the "result" variable. Lastly, it is appended to a string and assigned to
	the "message" variable which is then used in the "sendMessage(client, message)" function
*/
	protected synchronized void diceRoll(ServerThread client, int num) {
		Random rand = new Random();
		int result = rand.nextInt(num);
		String message = String.format(" rolled a number 0 - %d! Result is %d.", num, result);
		sendMessage(client, message);
	}
//--------------------------------------------------------------------------------------------------------
//-------------------Dice Roll Format: /roll #d#----------------------------------------------------------
/*
	UCID: sjc65
	Date: 07/11/2023
	Explanation: In the "diceRoll()" method, it uses "num" and "sides" as it's parameters and processes the "/roll #d#" 
	command format. First a random object and string variable are created, rand and rollResults respectively. In the for-loop,
	"num" is used as the iteration limit. Through each iteration, the "roll" integer variable is assigned the value of
	the result of "rand.nextInt(sides) + 1", which randomly chooses a number between 1 and "sides" and appends that to "rollResults".  
	If "i" is less than "num" - 1, a comma is appended to rollResults as well. Lastly, the string results are assigned to "message"
	and then "message" is used in the "diceRoll(client, message)" function call.
*/
	protected synchronized void diceRoll(ServerThread client, int num, int sides) {
		Random rand = new Random();
		String rollResults = "";
	
		for (int i = 0; i < num; i++) {
        	int roll = rand.nextInt(sides) + 1;
			rollResults += roll;
        
			if (i < num - 1) {
            	rollResults += ", ";
        	}
		}
		String message = String.format(" rolled %dd%d! Results are %s.", num, sides, rollResults);
    	sendMessage(client, message);
	}	
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
	/***
	 * Helper function to process messages to trigger different functionality.
	 * 
	 * @param message The original message being sent
	 * @param client  The sender of the message (since they'll be the ones
	 *                triggering the actions)
	 */
	private boolean processCommands(String message, ServerThread client) {
		boolean wasCommand = false;
		try {
			if (message.startsWith(COMMAND_TRIGGER)) {
				String[] comm = message.split(COMMAND_TRIGGER);
				String part1 = comm[1];
				String[] comm2 = part1.split(" ");
				String command = comm2[0];
				String roomName;
				wasCommand = true;
				switch (command) {
					case CREATE_ROOM:
						roomName = comm2[1];
						Room.createRoom(roomName, client);
						break;
					case JOIN_ROOM:
						roomName = comm2[1];
						Room.joinRoom(roomName, client);
						break;
					case DISCONNECT:
					case LOGOUT:
					case LOGOFF:
						Room.disconnectClient(client, this);
						break;
//-----------------------Coin Flip Command Process--------------------------------------
/*
	UCID: sjc65
	Date: 07/10/2023
	Explanation: the case takes in a string variable called "FLIP"(which is "flip") and uses "client" as the parameter
	in the function call. Then "wasCommand" is set to true to imply that the case had a command, and lastly a break statement
	to end the case.
*/
					case FLIP:
						coinFlip(client);
						wasCommand = true;
						break;
//---------------------------------------------------------------------------------------	
//-----------------------Dice Roll Command Process---------------------------------------
/*
	UCID: sjc65
	Date: 07/11/2023
	Explanation: In the Case ROLL, the code first checks if the string "comm2[1]" contains the letter "d". If it does then
	the "#d#"" roll format code is executed. The x and y char variables have the values of "comm2[1].charAt(0)" and 
	"comm2[1].charAt(2)", respectively, assigned to them. Then the characters are converted to ints and assigned to the int
	variables, num and sides. They are then used in the parameters of the diceRoll function call. If the string "comm2[1]"
	does not contain the letter "d" then "comm2[1]" is used in "Integer.parseInt(comm2[1])" to convert the string to an int.
	Then "num" is used in the parameter of the diceRoll function call.
*/
					case ROLL:
						if(comm2[1].contains("d")) {
							char x = comm2[1].charAt(0);
        					char y = comm2[1].charAt(2);
							int num = Character.getNumericValue(x);
        					int sides = Character.getNumericValue(y);
							diceRoll(client, num, sides);
							wasCommand = true;
							break;
						} else {
							int num = Integer.parseInt(comm2[1]);
							diceRoll(client, num);	
							wasCommand = true;
							break;
						}
//---------------------------------------------------------------------------------------
					default:
						wasCommand = false;
						break;
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wasCommand;
	}

	// Command helper methods
	protected static void createRoom(String roomName, ServerThread client) {
		if (server.createNewRoom(roomName)) {
			//server.joinRoom(roomName, client);
			Room.joinRoom(roomName, client);
		} else {
			client.sendMessage("Server", String.format("Room %s already exists", roomName));
		}
	}

	protected static void joinRoom(String roomName, ServerThread client) {
		if (!server.joinRoom(roomName, client)) {
			client.sendMessage("Server", String.format("Room %s doesn't exist", roomName));
		}
	}

	protected static void disconnectClient(ServerThread client, Room room) {
		client.setCurrentRoom(null);
		client.disconnect();
		room.removeClient(client);
	}
	// end command helper methods

	/***
	 * Takes a sender and a message and broadcasts the message to all clients in
	 * this room. Client is mostly passed for command purposes but we can also use
	 * it to extract other client info.
	 * 
	 * @param sender  The client sending the message
	 * @param message The message to broadcast inside the room
	 */
	protected synchronized void sendMessage(ServerThread sender, String message) {
		if (!isRunning) {
			return;
		}
		info("Sending message to " + clients.size() + " clients");
		if (sender != null && processCommands(message, sender)) {
			// it was a command, don't broadcast
			return;
		}
		
		String from = (sender == null ? "Room" : sender.getClientName());
		Iterator<ServerThread> iter = clients.iterator();
		while (iter.hasNext()) {
			ServerThread client = iter.next();
			boolean messageSent = client.sendMessage(from, message);
			if (!messageSent) {
				handleDisconnect(iter, client);
			}
		}
	}
	protected synchronized void sendConnectionStatus(ServerThread sender, boolean isConnected){
		Iterator<ServerThread> iter = clients.iterator();
		while (iter.hasNext()) {
			ServerThread client = iter.next();
			boolean messageSent = client.sendConnectionStatus(sender.getClientName(), isConnected);
			if (!messageSent) {
				handleDisconnect(iter, client);
			}
		}
	}
	private void handleDisconnect(Iterator<ServerThread> iter, ServerThread client){
		iter.remove();
		info("Removed client " + client.getClientName());
		checkClients();
		sendMessage(null, client.getClientName() + " disconnected");
	}
	public void close() {
		server.removeRoom(this);
		server = null;
		isRunning = false;
		clients = null;
	}
}
