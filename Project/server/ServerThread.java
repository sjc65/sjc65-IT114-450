package Project.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Project.common.Payload;
import Project.common.PayloadType;
import Project.common.RoomResultPayload;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private String clientName;
    private boolean isRunning = false;
    private ObjectOutputStream out;// exposed here for send()
    // private Server server;// ref to our server so we can call methods on it
    // more easily
    private Room currentRoom;
    private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private long myId;

//----------------------------------------------------------------------------
/*
 * UCID: sjc65
 * Date Created: 07/24/2023
 * --->Date Modified: 08/04/2023
 * Explanation: The code creates a mutedUsers array list and a string variable called "users". The two methods, "addToMutedUsers"
 * and "removeFromMutedUsers", add the user to the muted list if the user is not muted and removes the user from the mute list
 * if the user is muted. Then the "isMuted()" method checks if a user is muted or not. 
 * 
 * --->The code also calls the "mutedUsersFile()"
 * method. under each of the two methods, to update the mute list text file based on the status of the muter and the associated 
 * muted usernames.
 */
    private List<String> mutedUsers = new ArrayList<>();
    private String user = "";

    // Returns list of muted users
    public List<String> getMutedUsers() {
        return mutedUsers;
    }

    // Adds users to the mute list
    public synchronized void addToMutedUsers(String username) {
        user = username.trim().toLowerCase();
        if(!isMuted(user)) {
            mutedUsers.add(user);
            // Calls the method to write to the muted users text file
            mutedUsersFile(clientName, user);
        }
    }

    // Removes users from the mute list
    public synchronized void removeFromMutedUsers(String username) {
        user = username.trim().toLowerCase();
        if(isMuted(user)) {
            mutedUsers.remove(user);
            // Calls the method to write to the muted users text file
            mutedUsersFile(clientName, user);
        }
    }

    // Checks if the specified user is muted or not and returns true or false
    public synchronized boolean isMuted(String username) {
        user = username.trim().toLowerCase();
        return mutedUsers.contains(user);
    }
    // ----------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------
/*
 *  UCID: sjc65
 *  Date: 08/04/2023
 *  Explanation: The mutedUsersFile() method creates and reads a text file that stores the muter and the muted information in
 *  the file. The file stores the information per mute command call such as: sai,jay. Where sai is the muter and jay is the
 *  muted. This method creates the text file by setting up the information structure in a list format, so each muter and muted
 *  are seperated as a seperate entry. The "while" block is what writes the entries to the file, it trims the characters
 *  to lower case in order to make it easier to read and then sets the "entryFound" boolean to true. The next block of code 
 *  reads the text file looking for entries in the format: muter,muted. If the user is not muted and entryFound is set to true,
 *  the muted user is removed from the list. If the user is muted and an entryFound is set to false, then the muted user is added
 *  to the list.
 */
private void mutedUsersFile(String muter, String mutedUser) {
    try {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("mute_list.txt"));
        String line;
        boolean entryFound = false;

        while ((line = reader.readLine()) != null) {
            String[] users = line.split(",");
            if (!(users.length == 2 && users[0].trim().equalsIgnoreCase(muter) && users[1].trim().equalsIgnoreCase(mutedUser))) {
                lines.add(line);
            } else {
                entryFound = true;
            }
        }
        reader.close();

        if (!isMuted(mutedUser) && entryFound) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mute_list.txt"));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } else if (isMuted(mutedUser) && !entryFound) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mute_list.txt", true));
            writer.write(muter + "," + mutedUser);
            writer.newLine();
            writer.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
//--------------------------------------------------------------------------------------------------------

    public void setClientId(long id) {
        myId = id;
    }

    public long getClientId() {
        return myId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Room room) {
        info("Thread created");
        // get communication channels to single client
        this.client = myClient;
        this.currentRoom = room;

    }
//-----------------------------------------------------------------------------------------------------------
    /*
     *  UCID: sjc65
     *  Date: 08/04/2023
     *  Explanation: This method is the second half of the implementation that reads from the mute_list.txt and uploads the 
     *  muter and muted names when the method is called. The method reads from the text file, removes the comma and adds 
     *  the muter and the associated muted to the "addToMutedUsers()" method.
     */
    private void muteFromList() {
        try (BufferedReader reader = new BufferedReader(new FileReader("mute_list.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] users = line.split(",");
                if (users.length == 2 && users[0].trim().equalsIgnoreCase(getClientName())) {
                    addToMutedUsers(users[1].trim().toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//-----------------------------------------------------------------------------------------------------------

    protected void setClientName(String name) {
        if (name == null || name.isBlank()) {
            System.err.println("Invalid client name being set");
            return;
        }
        clientName = name;
    }

    protected String getClientName() {
        return clientName;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
        } else {
            info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        sendConnectionStatus(myId, getClientName(), false);
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    // send methods
    public boolean sendRoomName(String name) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.JOIN_ROOM);
        p.setMessage(name);
        return send(p);
    }

    public boolean sendRoomsList(String[] rooms, String message) {
        RoomResultPayload payload = new RoomResultPayload();
        payload.setRooms(rooms);
        //Fixed in Module7.Part9
        if(message != null){
            payload.setMessage(message);
        }
        return send(payload);
    }

    public boolean sendExistingClient(long clientId, String clientName) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.SYNC_CLIENT);
        p.setClientId(clientId);
        p.setClientName(clientName);
        return send(p);
    }

    public boolean sendResetUserList() {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.RESET_USER_LIST);
        return send(p);
    }

    public boolean sendClientId(long id) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.CLIENT_ID);
        p.setClientId(id);
        return send(p);
    }

    public boolean sendMessage(long clientId, String message) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientId(clientId);
        p.setMessage(message);
        return send(p);
    }

    public boolean sendConnectionStatus(long clientId, String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientId(clientId);
        p.setClientName(who);
        p.setMessage(isConnected ? "connected" : "disconnected");
        return send(p);
    }

    private boolean send(Payload payload) {
        // added a boolean so we can see if the send was successful
        try {
            // TODO add logger
            logger.log(Level.FINE, "Outgoing payload: " + payload);
            out.writeObject(payload);
            logger.log(Level.INFO, "Sent payload: " + payload);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            // comment this out to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            info("Message was attempted to be sent before outbound stream was opened: " + payload);
            return true;// true since it's likely pending being opened
        }
    }

    // end send methods
    @Override
    public void run() {
        info("Thread starting");
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            Payload fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from inputStream (null would
                                                                     // likely mean a disconnect)
            ) {

                info("Received from client: " + fromClient);
                processPayload(fromClient);

            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            info("Client disconnected");
        } finally {
            isRunning = false;
            info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }
//-----------------------------------------------------------------------------------
/*
 * UCID: sjc65
 * Date: 07/21/2023
 * Explanation: This function takes in "message" as a parameter. then the "replaceAll()" function is used on the "message"
    based on what special characters are wrapped around the text. Then the special characters in the message are replaced by 
    the associated tags and returned.
 */
private String formatText(String message) {
    return message.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>")     // Bold Format
                  .replaceAll("\\*(.*?)\\*", "<i>$1</i>")           // Italics Format
                  .replaceAll("_(.*?)_", "<u>$1</u>")               // Underlined Format

                  .replaceAll("!(.*?)!", "<red>$1</red>")            // Red text color
                  .replaceAll("\\+(.*?)\\+", "<green>$1</green>")    // Green text color
                  .replaceAll("\\-(.*?)\\-", "<blue>$1</blue>");     // Blue text color
                  
}
//-----------------------------------------------------------------------------------

    void processPayload(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setClientName(p.getClientName());
            //------------------------------------------------------------
            /*
             * UCID: sjc65
             * Date: 08/04/2023
             * Explanation: retrieves every muted user from the list and sends it as a parameter to the "addToMutedUsers()" method call.
             * The the "muteFromList()" method is called underneath to read from the mute list.
             */
                // Mute users based on the mutedUsers list
                for (String mutedUser : mutedUsers) {
                    addToMutedUsers(mutedUser);
                }
                muteFromList();
            //------------------------------------------------------------
                break;
            case DISCONNECT:
                Room.disconnectClient(this, getCurrentRoom());
                break;
            case MESSAGE:
                if (currentRoom != null) {
//------------------------------------------------------------------------------------
/*
 * UCID: sjc65
    Date: 07/21/2023
    Explanation: This code assigns the "formatText(p.getMessage())"" function call, with "p.getMessage()" as its parameter,
    to the "formattedMessage" String variable. Then "formattedMessage" is used in the "currentRoom.sendMessage()" function.
    Essentially, rerouting the usual message thread through the "formatText()" method first.
 */
                    String formattedMessage = formatText(p.getMessage());
                    currentRoom.sendMessage(this, formattedMessage);
//------------------------------------------------------------------------------------
                } else {
                    // TODO migrate to lobby
                    logger.log(Level.INFO, "Migrating to lobby on message with null room");
                    Room.joinRoom("lobby", this);
                }
                break;
            case GET_ROOMS:
                Room.getRooms(p.getMessage().trim(), this);
                break;
            case CREATE_ROOM:
                Room.createRoom(p.getMessage().trim(), this);
                break;
            case JOIN_ROOM:
                Room.joinRoom(p.getMessage().trim(), this);
                break;
            default:
                break;

        }

    }

    private void cleanup() {
        info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            info("Client already closed");
        }
        info("Thread cleanup() complete");
    }
}
