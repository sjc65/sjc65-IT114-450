package Project.client.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Project.client.Card;
import Project.client.Client;
import Project.client.ClientUtils;
import Project.client.ICardControls;

public class ChatPanel extends JPanel {
    private static Logger logger = Logger.getLogger(ChatPanel.class.getName());
    private JPanel chatArea = null;
    private UserListPanel userListPanel;

    public ChatPanel(ICardControls controls) {
        super(new BorderLayout(10, 10));
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        // wraps a viewport to provide scroll capabilities
        JScrollPane scroll = new JScrollPane(content);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        // no need to add content specifically because scroll wraps it
        wrapper.add(scroll);
        this.add(wrapper, BorderLayout.CENTER);

        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
        JTextField textValue = new JTextField();
        input.add(textValue);
        JButton button = new JButton("Send");
        // ----------------------------------------------------------------------------------------------------------------------------
        /*
         * UCID: sjc65
         * Date: 08/01/2023
         * Explanation: The code creates a JButton object called "export" and labels the
         * button "Export History". When the
         * button is activated, it calls the "exportChat()" method. Then the button is
         * added to the chat panel UI on the
         * bottom-right of the window.
         */
        // Creates JButton object for "exportButton" with "Export History" button label
        JButton export = new JButton("Export History");

        // Event listener for when button is pressed, calls "exportChat()" method.
        export.addActionListener((event) -> {
            exportChat();
        });
        // ----------------------------------------------------------------------------------------------------------------------------
        // lets us submit with the enter key instead of just the button click
        textValue.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
        button.addActionListener((event) -> {
            try {
                String text = textValue.getText().trim();
                if (text.length() > 0) {
                    Client.INSTANCE.sendMessage(text);
                    textValue.setText("");// clear the original text

                    // debugging
                    logger.log(Level.FINEST, "Content: " + content.getSize());
                    logger.log(Level.FINEST, "Parent: " + this.getSize());

                }
            } catch (NullPointerException e) {
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        chatArea = content;
        input.add(button);
        // Adds button to bottom of border layout, to the right of the "send" button.
        input.add(export);
        userListPanel = new UserListPanel(controls);
        this.add(userListPanel, BorderLayout.EAST);
        this.add(input, BorderLayout.SOUTH);
        this.setName(Card.CHAT.name());
        controls.addPanel(Card.CHAT.name(), this);
        chatArea.addContainerListener(new ContainerListener() {

            @Override
            public void componentAdded(ContainerEvent e) {
                if (chatArea.isVisible()) {
                    chatArea.revalidate();
                    chatArea.repaint();
                }
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                if (chatArea.isVisible()) {
                    chatArea.revalidate();
                    chatArea.repaint();
                }
            }

        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // System.out.println("Resized to " + e.getComponent().getSize());
                // rough concepts for handling resize
                // set the dimensions based on the frame size
                Dimension frameSize = wrapper.getParent().getParent().getSize();
                int w = (int) Math.ceil(frameSize.getWidth() * .3f);

                userListPanel.setPreferredSize(new Dimension(w, (int) frameSize.getHeight()));
                userListPanel.revalidate();
                userListPanel.repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // System.out.println("Moved to " + e.getComponent().getLocation());
            }
        });
    }

    public void addUserListItem(long clientId, String clientName) {
        userListPanel.addUserListItem(clientId, clientName);
    }

    public void removeUserListItem(long clientId) {
        userListPanel.removeUserListItem(clientId);
    }

    public void clearUserList() {
        userListPanel.clearUserList();
    }

    public void addText(String text) {
        JPanel content = chatArea;
        // ----------------------------------------------------------------------------------------------------------------------------
        /*
         * UCID: sjc65
         * Date: 07/21/2023
         * Explanation: This code simply translates the textformatting commands from the
         * ServerThread.java from Milestone2 into HTML tags
         * using regex. It receives the messages from the client-side and then outputs
         * the final result which replaces the product of
         * the ServerThread.java textformatting and replaces it with real HTML code. The
         * JeditorPane "textContainer" translates
         * the HTML into a visual style rather than just labels.
         */
        // Translates to bold font
        text = text.replaceAll("<b>(.*?)</b>", "<b style=\"font-weight:bold;\">$1</b>");

        // Translates to italic font
        text = text.replaceAll("<i>(.*?)</i>", "<i style=\"font-style:italic;\">$1</i>");

        // Translates to underline font
        text = text.replaceAll("<u>(.*?)</u>", "<u style=\"text-decoration:underline;\">$1</u>");

        // Translates to red color font
        text = text.replaceAll("<red>(.*?)</red>", "<font color=\"red\">$1</font>");

        // Translates to blue color font
        text = text.replaceAll("<blue>(.*?)</blue>", "<font color=\"blue\">$1</font>");

        // Translates to green color font
        text = text.replaceAll("<green>(.*?)</green>", "<font color=\"green\">$1</font>");

        // add message
        JEditorPane textContainer = new JEditorPane("text/html", text);
        // ----------------------------------------------------------------------------------------------------------------------------

        // sizes the panel to attempt to take up the width of the container
        // and expand in height based on word wrapping
        textContainer.setLayout(null);
        textContainer.setPreferredSize(
                new Dimension(content.getWidth(), ClientUtils.calcHeightForText(this, text, content.getWidth())));
        textContainer.setMaximumSize(textContainer.getPreferredSize());
        textContainer.setEditable(false);
        ClientUtils.clearBackground(textContainer);
        // add to container and tell the layout to revalidate
        content.add(textContainer);
        // scroll down on new message
        JScrollBar vertical = ((JScrollPane) chatArea.getParent().getParent()).getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    // ----------------------------------------------------------------------------------------------------------------------------
    /*
     * UCID: sjc65
     * Date: 08/01/2023
     * Explanation: The method first retrieves the chat history from the chat area
     * by getting the text of each message
     * in the history. Then it appends the chat history to the StringBuilder object,
     * "chatHistory" to combine and build the
     * chat messages with "/n" appended at the end of each message in order to
     * seperate each message by a new line. Then
     * the JFileChooser object, "pathChooser", prompts the user with a
     * "File Explorer" window to select the name of the file
     * and where it will be saved. Then the file is created with a ".txt" file
     * extension at the end. The BufferedWriter
     * object, "writer", is used to write the contents of the chat history to the
     * file. Lastly, a windowed success message is
     * shown to the user if the export was successful. If the export was not
     * successful, a windowed error message is displayed
     * instead.
     */
    private void exportChat() {
        try {
            // Retrieves the chat history from the chat area
            StringBuilder chatHistory = new StringBuilder();
            Component[] components = chatArea.getComponents();
            for (Component comp : components) {
                if (comp instanceof JEditorPane) {
                    JEditorPane textContainer = (JEditorPane) comp;
                    String text = textContainer.getText();

                    // Appends each chat message to "chatHistory" StringBuilder object.
                    chatHistory.append(text).append("\n");
                }
            }

            // Creates a file path chooser window to select the file path for saving the
            // chat history.
            JFileChooser pathChooser = new JFileChooser();
            int option = pathChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {

                // Gets the selected file path
                String filePath = pathChooser.getSelectedFile().getAbsolutePath();

                // Adds ".txt" file extension to end of file
                if (!filePath.endsWith(".txt")) {
                    filePath += ".txt";
                }

                // Saves the chat history to the selected file.
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(chatHistory.toString());
                }

                // Shows success message, using a windowed dialog box, to the user.
                JOptionPane.showMessageDialog(this, "Chat history exported successfully!",
                        "Export History", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            // Shows an error message if exporting fails, using a windowed dialog box, to
            // the user.
            JOptionPane.showMessageDialog(this, "Error exporting chat history: " + e.getMessage(),
                    "Export History Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // ----------------------------------------------------------------------------------------------------------------------------
}
