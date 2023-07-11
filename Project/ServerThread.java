package Project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Room room) {
        info("Thread created");
        // get communication channels to single client
        this.client = myClient;
        this.currentRoom = room;

    }

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
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    // send methods
    public boolean sendMessage(String from, String message) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientName(from);
        p.setMessage(message);
        //-----------------------
        setPayloadTimestamp(p);
        //-----------------------
        return send(p);
    }
    public boolean sendConnectionStatus(String who, boolean isConnected){
        Payload p = new Payload();
        p.setPayloadType(isConnected?PayloadType.CONNECT:PayloadType.DISCONNECT);
        p.setClientName(who);
        p.setMessage(isConnected?"connected":"disconnected");
        return send(p);
    }

    private boolean send(Payload payload) {
        // added a boolean so we can see if the send was successful
        try {
            out.writeObject(payload);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            // comment this out to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            info("Message was attempted to be sent before outbound stream was opened");
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
                processMessage(fromClient);

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
//----------------------------------Text Formatting----------------------------------
/*
    UCID: sjc65
    Date: 07/11/2023
    Explanation: This function takes in "message" as a parameter. then the "replaceAll()" function is used on the "message"
    based on what special characters are wrapped around the text. Then the special characters in the message are replaced by 
    the associated tags and returned.
*/
    private String formatText(String message) {
        return message.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>")     // Bold Format
                      .replaceAll("\\*(.*?)\\*", "<i>$1</i>")           // Italics Format
                      .replaceAll("_(.*?)_", "<u>$1</u>")               // Underlined Format

                      //NOTE: If UI is in HTML, replace the second values (after regex) with actual HTML tags for text color.
                      .replaceAll("!(.*?)!", "<red>$1<red>")            // Red text color
                      .replaceAll("\\+(.*?)\\+", "<green>$1<green>")    // Green text color
                      .replaceAll("\\-(.*?)\\-", "<blue>$1<blue>");     // Blue text color
                      
    }
//------------------------------------------------------------------------------------
    //------------------------------------------------------
    private void setPayloadTimestamp(Payload payload) {
        long currentTimeStamp = System.currentTimeMillis();
        payload.setStamp(currentTimeStamp);
    }
    //------------------------------------------------------
    void processMessage(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setClientName(p.getClientName());
                break;
            case DISCONNECT://TBD
                break;
            case MESSAGE:
                if (currentRoom != null) {
//----------------------------------Message Format Rerouter----------------------------------
/*
    UCID: sjc65
    Date: 07/11/2023
    Explanation: This code assigns the "formatText(p.getMessage())"" function call, with "p.getMessage()" as its parameter,
    to the "formattedMessage" String variable. Then "formattedMessage" is used in the "currentRoom.sendMessage()" function.
    Essentially, rerouting the usual message thread through the "formatText()" method first.
*/
                    String formattedMessage = formatText(p.getMessage());
                    currentRoom.sendMessage(this, formattedMessage);
                    //currentRoom.sendMessage(this, p.getMessage());
//-------------------------------------------------------------------------------------------
                } else {
                    // TODO migrate to lobby
                    Room.joinRoom("lobby", this);
                }
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
