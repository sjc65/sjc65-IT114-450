package Part3HW;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Server {
    int port = 3001;
    // connected clients
    private List<ServerThread> clients = new ArrayList<ServerThread>();

    //---implementation 1 code below---:
    private boolean isGameActive = false;
    private int hiddenNumber;
    //----------------------------------

    private void start(int port) {
        this.port = port;
        // server listening
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket incoming_client = null;
            System.out.println("Server is listening on port " + port);
            do {
                System.out.println("waiting for next client");
                if (incoming_client != null) {
                    System.out.println("Client connected");
                    ServerThread sClient = new ServerThread(incoming_client, this);
                    
                    clients.add(sClient);
                    sClient.start();
                    incoming_client = null;
                    
                }
            } while ((incoming_client = serverSocket.accept()) != null);
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("closing server socket");
        }
    }
    protected synchronized void disconnect(ServerThread client) {
		long id = client.getId();
        client.disconnect();
		broadcast("Disconnected", id);
	}
    
    protected synchronized void broadcast(String message, long id) {
        if(processCommand(message, id)){

            return;
        }
        /*
         * UCID: sjc65
         * Date: 06/16/2023
         * Explanation: The code first checks if a client's input string, says "guesserStart", if it does then the random number generator is executed and a prompt
         * is displayed to all clients that the game has started. Then it will check if a client's input says "guess [#]", the code will then process the
         * number input and run it through the "checkGuess()" method and return a result. If a client's input string says "guesserStop", the game will stop and
         * further guesses will not be processed.
         */
        //---Implementation 1 code below---
        if (message.equalsIgnoreCase("guesserStart")) {
            isGameActive = true;
            Random random = new Random();
            hiddenNumber = random.nextInt(15) + 1;
            message = "Number Guesser game started. Guess the number.";
        } else if (message.equalsIgnoreCase("guesserStop")) {
            isGameActive = false;
            message = "Number Guesser game stopped. Guesses will not be considered";
        } else if (isGameActive && message.startsWith("guess")) {
            String[] parts = message.split(" ");
            if (parts.length == 2) {
                int guess = Integer.parseInt(parts[1]);
                String result = checkGuess(guess);
                message = "guessed " + guess + ", " + result;
            }
        }
        //---------------------------------
        /*
         * UCID: sjc65
         * Date: 06/16/2023
         * Explanation: The code checks to see if the client's input string, says "coin". If it does, then the "performCoinToss" method is run and returns 
         * the result.
         */
        //---Implementation 2 code below---
        if (message.equalsIgnoreCase("coin")) {
            String result = performCoinToss();
            message ="flipped a coin and got " + result;
        } 
        //----------------------------------

        // let's temporarily use the thread id as the client identifier to
        // show in all client's chat. This isn't good practice since it's subject to
        // change as clients connect/disconnect
        message = String.format("User[%d]: %s", id, message);
        // end temp identifier
        // loop over clients and send out the message
        Iterator<ServerThread> it = clients.iterator();
        while (it.hasNext()) {
            ServerThread client = it.next();
            boolean wasSuccessful = client.send(message);
            if (!wasSuccessful) {
                System.out.println(String.format("Removing disconnected client[%s] from list", client.getId()));
                it.remove();
                broadcast("Disconnected", id);
            }
        }
    }

    private boolean processCommand(String message, long clientId){
        System.out.println("Checking command: " + message);
        if(message.equalsIgnoreCase("disconnect")){
            Iterator<ServerThread> it = clients.iterator();
            while (it.hasNext()) {
                ServerThread client = it.next();
                if(client.getId() == clientId){
                    it.remove();
                    disconnect(client);
                    
                    break;
                }
            }
            return true;
        }
        return false;
    }
    /*
     * UCID: sjc65
     * Date: 06/16/2023
     * Explanation: The "checkGuess()" method takes in the client's number guess as a parameter and checks to see if it is equal to the "hiddenNumber" value. If it is
     * equal, the game ends and the method returns "correct" to the "checkGuess()" method call in the "broadcast()" method.
     */
    //---NumberGuesser Implementation 1 below---
    private String checkGuess(int guess) {
        if (guess == hiddenNumber) {
            isGameActive = false;
            return "correct";
        } else {
            return "incorrect";
        }
    }
    //-------------------------------------------
    /*
     * UCID: sjc65
     * Date: 06/16/2023
     * Explanation: The "performCoinToss()" method randomizes between numbers 0 and 1, every time it is called, and assigns it to "toss" variable. If "toss" is 
     * equal to 0, the method returns "heads", otherwise it will return "tails".
     */
    //---CoinToss Implementation 2 below---
    private String performCoinToss() {
        Random random = new Random();
        int toss = random.nextInt(2);

        if (toss == 0) {
            return "heads";
        } else {
            return "tails";
        }
    }
    //----------------------------------------
    public static void main(String[] args) {
        System.out.println("Starting Server");
        Server server = new Server();
        int port = 3000;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            // can ignore, will either be index out of bounds or type mismatch
            // will default to the defined value prior to the try/catch
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}