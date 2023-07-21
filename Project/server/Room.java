package Project.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import Project.common.Constants;

public class Room implements AutoCloseable {
	private String name;
	private List<ServerThread> clients = Collections.synchronizedList(new ArrayList<ServerThread>());
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
	private static Logger logger = Logger.getLogger(Room.class.getName());

	public Room(String name) {
		this.name = name;
		isRunning = true;
	}

	private void info(String message) {
		logger.log(Level.INFO, String.format("Room[%s]: %s", name, message));
	}

	public String getName() {
		return name;
	}

	public boolean isRunning() {
		return isRunning;
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
			sendConnectionStatus(client, true);
			sendRoomJoined(client);
			sendUserListToClient(client);
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
			// sendMessage(client, "left the room");
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
	//--------------------------------------------------------------------------------------------------
	/*
	 * UCID: sjc65
	 * Date: 07/21/2023
	 * Explanation: In the function "coinFlip()", it takes in "client" parameter from ServerThread class. 
	 * A String message and Random rand object are created. The result variable takes in a random number between 1 and 0.
	 * if the result is equal to 0, message is equal to heads(wrapped in bold tags) else message is equal to 
	 * tails(wrapped in bold tags). Then message is used as a parameter in the sendMessage() function call.
	 */
	protected synchronized void coinFlip(ServerThread client) {
		String message;
		Random rand = new Random();

		int result = rand.nextInt(2);
    	if(result == 0) {
		// The string wraps the selected text in bold tags (<b></b>) which are applied once the message is sent.
    		message = " flipped a coin! Result is <b>heads</b>";
		} else {
		// The string wraps the selected text in bold tags (<b></b>) which are applied once the message is sent.
    		message = " flipped a coin! Result is <b>tails</b>";
		}
		
		sendMessage(client, message);
    }
	//--------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
	/*
	 * UCID: sjc65
	 * Date: 07/21/2023
	 * Explanation: For the first diceRoll() method, it assigns the random number of the num parameter to the result to randomize
	 * the diceRoll() method parameter. Then it assigns the text, num, and result to the message to be used as a parameter in
	 * the sendMessage() function call. The message has bold tags around "0 - %d" and "%d" to display the 0 to # and result in
	 * bold font. In the second diceRoll() method, it takes num and sides as parameters. for every "i" in num, the dice are rolled
	 * to a random number from 0 to the number of sides of the dice. Then the results are applied to rollResults at the end of
	 * each loop. Which is then assigned to message and used as a parameter in the sendMessage() function call. The message has
	 * "%dd%d" and "%s" bolded.
	 */
	protected synchronized void diceRoll(ServerThread client, int num) {
		Random rand = new Random();
		int result = rand.nextInt(num);
		// The string wraps the selected text in bold tags (<b></b>) which are applied once the message is sent.
		String message = String.format(" rolled a number <b>0 - %d</b>! Result is <b>%d</b>.", num, result);
		sendMessage(client, message);
	}

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
		// The string wraps the selected text in bold tags (<b></b>) which are applied once the message is sent.
		String message = String.format(" rolled <b>%dd%d</b>! Results are <b>%s</b>.", num, sides, rollResults);
    	sendMessage(client, message);
	}	
	//---------------------------------------------------------------------------------------------------

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
//------------------------------------------------------------------------------------
/*
 * UCID: sjc65
 * Date: 07/21/2023
 * Explanation: In case FLIP (/flip command), the coinFlip method is called and takes in client as a parameter, 
 * then wasCommand is set to true and lastly the break statement is used to exit the cases. In case ROLL, 
 * if the /roll command contains "d" then it is parsed to retrieve the first and last numbers(num and sides respectively).
 * Then they are used in the diceRoll function call, wasCommand is set to true, and the break statement is used to exit
 * the cases.
 */
					case FLIP:
						coinFlip(client);
						wasCommand = true;
						break;
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
//------------------------------------------------------------------------------------
					default:
						wasCommand = false;
						break;
				}
//------------------------------------------------------------------------------------
/*
 * UCID: sjc65
 * Date: 07/20/2023
 * Explanation: First the code checks if the start of the text statement begins with "@", if it does then the statement
 * is split to retrieve the recipientName and the privateMessage. In the synchronized(clients) block, it access the 
 * list of clients that are active. For every clients in the recipient of ServerThread, it checks if client name matches
 * the recipient name. If it does, then a message is displayed to the sender on who the private message was sent to in bold
 * and then the private message is sent to the client ID rather than the user name in order for the client to privately recieve 
 * the message. Then the break statement is used to end the code block and finally wasCommand is set to true.
 */
			} else if (message.startsWith("@")) {
			String[] comm = message.split(" ", 2);
			String recipientName = comm[0].substring(1); 
			String privateMessage = comm[1];

			synchronized (clients) {
				for (ServerThread recipient : clients) {
					if (recipient.getClientName().equals(recipientName)) {
						recipient.sendMessage(client.getClientId(),
						"(Private message from <b>" + client.getClientName() + "</b>): " + privateMessage);
						
						client.sendMessage(client.getClientId(),
						"(Private message to <b>" + recipientName + "</b>): " + privateMessage);
						
						break;
					}
				}
			}
			wasCommand = true;
		}
//------------------------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wasCommand;
	}

	// Command helper methods

	protected static void getRooms(String query, ServerThread client) {
		String[] rooms = Server.INSTANCE.getRooms(query).toArray(new String[0]);
		client.sendRoomsList(rooms,(rooms!=null&&rooms.length==0)?"No rooms found containing your query string":null);
	}

	protected static void createRoom(String roomName, ServerThread client) {
		if (Server.INSTANCE.createNewRoom(roomName)) {
			Room.joinRoom(roomName, client);
		} else {
			client.sendMessage(Constants.DEFAULT_CLIENT_ID, String.format("Room %s already exists", roomName));
			client.sendRoomsList(null, String.format("Room %s already exists", roomName));
		}
	}

	protected static void joinRoom(String roomName, ServerThread client) {
		if (!Server.INSTANCE.joinRoom(roomName, client)) {
			client.sendMessage(Constants.DEFAULT_CLIENT_ID, String.format("Room %s doesn't exist", roomName));
			client.sendRoomsList(null, String.format("Room %s doesn't exist", roomName));
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
		long from = (sender == null) ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
		synchronized (clients) {
			Iterator<ServerThread> iter = clients.iterator();
			while (iter.hasNext()) {
				ServerThread client = iter.next();
				boolean messageSent = client.sendMessage(from, message);
				if (!messageSent) {
					handleDisconnect(iter, client);
				}
			}
		}
	}

	protected synchronized void sendUserListToClient(ServerThread receiver) {
		logger.log(Level.INFO, String.format("Room[%s] Syncing client list of %s to %s", getName(), clients.size(),
				receiver.getClientName()));
		synchronized (clients) {
			Iterator<ServerThread> iter = clients.iterator();
			while (iter.hasNext()) {
				ServerThread clientInRoom = iter.next();
				if (clientInRoom.getClientId() != receiver.getClientId()) {
					boolean messageSent = receiver.sendExistingClient(clientInRoom.getClientId(),
							clientInRoom.getClientName());
					// receiver somehow disconnected mid iteration
					if (!messageSent) {
						handleDisconnect(null, receiver);
						break;
					}
				}
			}
		}
	}

	protected synchronized void sendRoomJoined(ServerThread receiver) {
		boolean messageSent = receiver.sendRoomName(getName());
		if (!messageSent) {
			handleDisconnect(null, receiver);
		}
	}

	protected synchronized void sendConnectionStatus(ServerThread sender, boolean isConnected) {
		// converted to a backwards loop to help avoid concurrent list modification
		// due to the recursive sendConnectionStatus()
		// this should only be needed in this particular method due to the recusion
		if (clients == null) {
			return;
		}
		synchronized (clients) {
			for (int i = clients.size() - 1; i >= 0; i--) {
				ServerThread client = clients.get(i);
				boolean messageSent = client.sendConnectionStatus(sender.getClientId(), sender.getClientName(),
						isConnected);
				if (!messageSent) {
					clients.remove(i);
					info("Removed client " + client.getClientName());
					checkClients();
					sendConnectionStatus(client, false);
				}
			}
		}
	}

	private synchronized void handleDisconnect(Iterator<ServerThread> iter, ServerThread client) {
		if (iter != null) {
			iter.remove();
		}
		info("Removed client " + client.getClientName());
		checkClients();
		sendConnectionStatus(client, false);
		// sendMessage(null, client.getClientName() + " disconnected");
	}

	public void close() {
		Server.INSTANCE.removeRoom(this);
		isRunning = false;
		clients = null;
	}
}
