package Module4.Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    Socket server = null;   //"server, out, in" are the client class objects
    PrintWriter out = null;     //"PrintWriter" is used to send data over the Socket to the Server (communication channel to server)
    BufferedReader in = null;   //"BufferedReader" is used to get a response from the server (communication channel from server)
    final String ipAddressPattern = "connect\\s+(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{3,5})";  //"ipAddressPattern" accepts IP address
    final String localhostPattern = "connect\\s+(localhost:\\d{3,5})";                                  //"localhostPattern" accepts localhost
    boolean isRunning = false;  //"isRunning" serves as a flag to check if something is running

    public Client() {       //Client constructor is immediately called when a new object is created.
        System.out.println("");     // This constructor does not do anything and just prints out an empty string
    }

    public boolean isConnected() {      //The "isConnected" method will return a boolean to show if the server is connected or not
        if (server == null) {
            return false;
        }
        //https://stackoverflow.com/a/10241044
        //Note: these check the client's end of the socket connect; therefore they don't really help determine
        //if the server had a problem
        return server.isConnected() && !server.isClosed() && !server.isInputShutdown() && !server.isOutputShutdown();
     
    }
    /**
     * Takes an ip address and a port to attempt a socket connection to a server.
     * @param address
     * @param port
     * @return true if connection was successful
     */
    private boolean connect(String address, int port) {     //The "connect" method takes in address and port parameters and returns true or false for the connection's status
        try {
            server = new Socket(address, port);
            //channel to send to server
            out = new PrintWriter(server.getOutputStream(), true);
            //channel to list to server
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            System.out.println("Client connected");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isConnected();
    }

    /**
     * <p>
     * Check if the string contains the <i>connect</i> command
     * followed by an ip address and port or localhost and port.
     * </p>
     * <p>
     * Example format: 123.123.123:3000
     * </p>
     * <p>
     * Example format: localhost:3000
     * </p>
     * https://www.w3schools.com/java/java_regex.asp
     * 
     * @param text
     * @return
     */
    private boolean isConnection(String text) {         // "isConnection" checks to see if the recieving IP address and localhost pattern matches the expected pattern
        // https://www.w3schools.com/java/java_regex.asp
        return text.matches(ipAddressPattern)
                || text.matches(localhostPattern);
    }

    private boolean isQuit(String text) {
        return text.equalsIgnoreCase("quit");
    }

    /**
     * Controller for handling various text commands.
     * <p>
     * Add more here as needed
     * </p>
     * 
     * @param text
     * @return true if a text was a command or triggered a command
     */
    private boolean processCommand(String text) {
        if (isConnection(text)) {
            // replaces multiple spaces with single space
            // splits on the space after connect (gives us host and port)
            // splits on : to get host as index 0 and port as index 1
            String[] parts = text.trim().replaceAll(" +", " ").split(" ")[1].split(":");
            connect(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            return true;
        } else if (isQuit(text)) {
            isRunning = false;
            return true;
        }
        return false;
    }

    public void start() throws IOException {

        System.out.println("Listening for input");
        try (Scanner si = new Scanner(System.in);) {
            String line = "";
            isRunning = true;
            while (isRunning) {
                try {
                    System.out.println("Waiting for input");
                    line = si.nextLine();
                    if (!processCommand(line)) {
                        if(isConnected()){
                            out.println(line);
                            //https://stackoverflow.com/a/8190411
                            //you'll notice it triggers on the second request after server socket closes
                            if(out.checkError()){
                                System.out.println("Connection to server may have been lost");
                            }
                        }
                        else{
                            System.out.println("Not connected to server");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Connection dropped");
                    break;
                }
            }
            System.out.println("Exited loop");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {      //The "close" method closing the stream and cleans up any resources
        try {
            System.out.println("Closing output stream");
            out.close();    //Closes the output stream
        } catch (NullPointerException ne) {
            System.out.println("Server was never opened so this exception is ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Closing input stream");
            in.close();     //Closes the input stream
        } catch (NullPointerException ne) {
            System.out.println("Server was never opened so this exception is ok");
        } catch (Exception e) {
            e.printStackTrace();
        }                              //***The code from the first "try()" and above this comment is likely redundant because closing the socket also closes the I/O stream
        try {
            System.out.println("Closing connection");
            server.close(); //Closes the connection and socket
            System.out.println("Closed socket");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ne) {
            System.out.println("Server was never opened so this exception is ok");
        }
    }

    public static void main(String[] args) {        //***Driver class that will start up client***
        Client client = new Client();   //Creates new client object

        try {
            // if start is private, it's valid here since this main is part of the class
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
    Go to Terminal, go to the "+" on top-right, press the down arrow next to the "+", click "Git Bash"
    (optional: go to "split terminal", under the drop-down menu of the "+", to open another bash terminal side-by-side)
    Run this command: javac Module4/Part1/Client.java
    (this compiles the client)
    Run: java Module4.Part1.Client
    (This starts the client)
    Run: connect localhost:3000
    (This results in a "connection refused" because the server has not started and therefore there is nothing to connect to)
    Run: quit
    (This closes the connection)
*/
