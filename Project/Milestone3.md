<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone3</td></tr>
<tr><td> <em>Student: </em> Sai Chandra (sjc65)</td></tr>
<tr><td> <em>Generated: </em> 7/25/2023 1:01:27 AM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-chatroom-milestone3/grade/sjc65" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone3 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Connection Screens </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots showing the screens with the following data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-20T21.41.54host%20%26%20port.PNG.webp?alt=media&token=6c863e80-3c6d-429c-9751-1073e54421ed"/></td></tr>
<tr><td> <em>Caption:</em> <p>This window shows the host address and port number auto-filled.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-20T21.42.02username.PNG.webp?alt=media&token=52fdb103-8152-4b9e-acfa-d285eb7eb27e"/></td></tr>
<tr><td> <em>Caption:</em> <p>In this window, I typed my name in as the username.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the code for each step of the process</td></tr>
<tr><td> <em>Response:</em> <ol><li>ClientUI extends JFrame to set up a windowed card layout with input fields<br>that accept the host, port, and username.</li><li>The "createConnectionScreen()" method sets up a panel<br>for accepting the host and port numbers from the user. This method also<br>auto-fills the host number and port number when the client is opened. This<br>method also has a "next" button that checks if the user input for<br>the host and port is valid before proceeding to the next panel.</li><li>The "createUserInputScreen()"<br>method sets up a panel for accepting the user name from the user.<br>This part of the method is not auto-filled and the user must enter<br>a valid username. This method also has two buttons, "previous" and "connect". "previous"<br>lets the user go back to the previous connection panel and the "connect"<br>button lets the user connect to the chat room once all information is<br>valid.</li><li>The "createChatScreen()" method sets up the main chat panel. In this method, the<br>user can send a message by inputting a string into the text box<br>and either clicking the "send" button or pressing the "enter" key. Then the<br>message is displayed in the "content" panel, which displays all the messages of<br>the chat room. The method also uses JScrollPane to let the user vertically<br>scroll the chat panel.</li><li>Lastly, the "calcHeightForText()" method calculates the exact text size needed<br>to display a wrapped string of words, allowing for the messages to be<br>displayed correctly in the "content" panel without having the messages appear in different<br>sizes.</li></ol><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Chatroom view </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots showing the related UI</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-20T21.48.48users%20%26%20message%20area.PNG.webp?alt=media&token=5a23d6a3-43d5-4b86-9059-669e7c66745b"/></td></tr>
<tr><td> <em>Caption:</em> <p>This window shows the message area with chat history on the left-side, the<br>list of users in the room on the right-side, and the create message<br>input field and send button on the bottom.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-20T22.49.58enter%20key%20submit%20action.PNG.webp?alt=media&token=a9ccb47b-60d0-4e79-a7a6-d51e45d999fc"/></td></tr>
<tr><td> <em>Caption:</em> <p>The following code adds a key listener that allows the &quot;enter&quot; key to<br>be used to send a message when it is pressed.<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Chat Activities </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show screenshots of the result of the following commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T00.39.29flip%20command.PNG.webp?alt=media&token=b49c7b36-7a95-432e-94fb-5d68a8da7b3f"/></td></tr>
<tr><td> <em>Caption:</em> <p>The output displays the &quot;heads&quot; text in bold.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T00.39.39roll%20command%20format%202.PNG.webp?alt=media&token=a22e4035-c867-4c2b-a654-828dd33d3017"/></td></tr>
<tr><td> <em>Caption:</em> <p>The output displays &quot;2d6&quot; and &quot;4, 5&quot; texts in bold.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T00.39.45roll%20command%20format%201.PNG.webp?alt=media&token=23d6a026-5dc7-456f-b0ac-584c516fc929"/></td></tr>
<tr><td> <em>Caption:</em> <p>The output displays &quot;0-12&quot; and &quot;8&quot; texts in bold.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show the code snippets for each command</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T04.31.20coin%20flip.PNG.webp?alt=media&token=768c73a2-9833-4425-8b43-f1fddd69d5fc"/></td></tr>
<tr><td> <em>Caption:</em> <p>This screenshot shows the code behind the coin flip logic and the explanation<br>for it.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T04.31.27dice%20roll.PNG.webp?alt=media&token=c23fd29d-cea7-4e61-8739-6eac32d0d737"/></td></tr>
<tr><td> <em>Caption:</em> <p>This screenshot shows the code behind the dice roll logic for both formats<br>and the explanation for it.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T04.31.32case%20code.PNG.webp?alt=media&token=5627c465-7440-4e48-a2cb-df8d9f7d584f"/></td></tr>
<tr><td> <em>Caption:</em> <p>This screenshot shows the code behind the command call formats for both coin<br>flip and dice roll and how they are handled.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T04.41.10textformatting.PNG.webp?alt=media&token=55262990-6fb2-4401-b954-0f0cfd54a6f0"/></td></tr>
<tr><td> <em>Caption:</em> <p>This code snippet shows how the text formatting labels are processed and translated<br>to a visual appearence on the client-side.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T05.11.46serverthread%20textformatting.PNG.webp?alt=media&token=5c7b6f68-5e4c-460c-808f-332d091562ea"/></td></tr>
<tr><td> <em>Caption:</em> <p>In ServerThread.java, the first block of code (wrapped in green lines) shows how<br>the text is processed from the client&#39;s text formatting commands. The second block<br>of code (wrapped in green lines) shows how the server thread message flow<br>is rerouted through the text formatting method.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the code flow of each command</td></tr>
<tr><td> <em>Response:</em> <ul><li>Coin Flip Command: /flip</li></ul><div>For the coin flip, the code first processes the "/flip"<br>command in the "processCommands()" method. Then the "coinFlip()" method in Room.java randomly assigns<br>the "result" variable the number 1 or 0. Then the result is used<br>in the "sendMessage()" parameters to display the message to all clients.</div><ul><li>Dice Roll Command<br>Format 1 and 2: Format1: /roll # (ex: /roll 12) and Format2: /roll<br>#d# (ex: /roll 2d6)</li></ul><div>For the first format, the code first processes whether the<br>"/roll" command has "#" or "#d#" format using the command splitting in the<br>"processCommands()" method. The command checks to see if the command at "comm2[1]" contains<br>"d", if it does then format 2 is detected and it passes the<br>command's first and second values through the diceRoll() method. If it does not<br>contain "d" then format 1 is detected and the first value is passed<br>through the diceRoll() method in Room.java. In the diceRoll() methods, the first method<br>accepts the first value and the second method accepts two values to indicate<br>which format it is processing. For the first method, the result is assigned<br>a random value between 0 and #, then it is used as a<br>parameter in the "sendMessage()" method. The second method has each dice, specified in<br>the first # in #d#, randomized from numbers 0 - #, specified in<br>the second # in #d#. And then the result is used as a<br>parameter in the "sendMessage()" method.&nbsp;</div><ul><li>Text Formatting commands</li></ul><div>For the text formatting commands, the code<br>first redirects the message stream through the "formatText()" method in the ServerThread.java In<br>the method, there are sections of regex that check if the receiving input<br>contains any of the client commands for the text formatting they are trying<br>to use. Then they are replaced with tags that indicate whether the command<br>wrap of the text should be bolded, italicized, underlined, etc. In ChatPanel.java, the<br>tags are then processed even further and replaced by true HTML tags which<br>are visually translated to the respective text formatting.</div><div><ul><li>client-&gt;server-&gt;client flow</li></ul><div>When the client sends a<br>command, the command is detected from the text stream in the client based<br>on the wording and format of the message, like /flip. Then the server<br>processes the command and then is sent to the usual messaging flow in<br>the server that sends messages to all the clients. The commands essentially are<br>processed from the client flow and sent back to the client flow with<br>the result of the command processing.</div></div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Custom Text </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Screenshots of examples</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T01.34.24bolded%20text.PNG.webp?alt=media&token=36c5d214-6552-4958-a140-445c622d3d0b"/></td></tr>
<tr><td> <em>Caption:</em> <p>The message shows &quot;text&quot; in bolded format.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T01.34.52italic%20text.PNG.webp?alt=media&token=859c6425-cbbe-4266-ac6a-081835a5a3f2"/></td></tr>
<tr><td> <em>Caption:</em> <p>The second message shows &quot;text&quot; in italicized format.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T01.35.07underline%20text.PNG.webp?alt=media&token=0ded38e4-e386-47a7-9468-2ecd20e29e78"/></td></tr>
<tr><td> <em>Caption:</em> <p>The third message shows &quot;text&quot; in underlined format.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T01.35.21red%20and%20blue%20and%20green%20text.PNG.webp?alt=media&token=e007497b-4fa0-4381-b47f-314ba0553a92"/></td></tr>
<tr><td> <em>Caption:</em> <p>Each message line shows &quot;text&quot; in red, blue, and green respectively.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T01.36.03formats%20in%20input%20field.PNG.webp?alt=media&token=c101ef93-c0a8-4ed1-84da-4dcfc3d2a8f4"/></td></tr>
<tr><td> <em>Caption:</em> <p>The input field shows what the message, with combined formats, looks like before<br>it is sent.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T01.36.49combined%20formats%20output.PNG.webp?alt=media&token=ac6fa539-2ce3-4916-91f8-398a318ae329"/></td></tr>
<tr><td> <em>Caption:</em> <p>The chat panel shows what the combined formats look like after it is<br>sent.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T04.46.54textformatting.PNG.webp?alt=media&token=2bbd8d7a-5320-437f-9d5c-55b1ac6e8424"/></td></tr>
<tr><td> <em>Caption:</em> <p>This code shows the final form of the text from the ServerThread.java<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T04.47.00serverthread%20textformatting.PNG.webp?alt=media&token=1a3601fb-7a48-4309-b20f-d7ab14b1fed3"/></td></tr>
<tr><td> <em>Caption:</em> <p>In ServerThread.java, the first code block(in green comment lines) shows where the text<br>formatting is processed initially and the second code block (in green comment lines)<br>shows where the text thread is rerouted through.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how you got the UI side to render the text accordingly</td></tr>
<tr><td> <em>Response:</em> <p>The process begins in ServerThread.java from Milestone2, where the code captures the text<br>stream and redirects it through the text formatting method. In the method, it<br>converts the input commands into wrapper tags using regex. For example, if the<br>user sends <strong>message</strong>, the message is replaced by &lt;b&gt;message&lt;/b&gt;. Then in ChatPanel.java, the<br>tags are further processed to be converted to true HTML using regex again.<br>In order for the HTML tags to take effect, the JEditorPane object, textContainer,<br>has its parameter set to &quot;text/HTML&quot; so when the HTML text is received<br>from the regex replacement in this class, it is translated to a visual<br>style in the UI instead of just labels.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 5: </em> Whisper </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots showing a demo of whisper commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T03.55.07before%20private%20message.PNG.webp?alt=media&token=1ed82ac8-5cc0-48e2-8957-42abd2a7ea94"/></td></tr>
<tr><td> <em>Caption:</em> <p>On the right panel, the text input shows the format for sending a<br>private message with the name of the user after the &quot;@&quot;.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T03.55.15after%20private%20message.PNG.webp?alt=media&token=ea2d8c61-6fea-42d2-98a4-fb8a4a071a1f"/></td></tr>
<tr><td> <em>Caption:</em> <p>In the right panel, it shows who the private message was sent to<br>and in the middle panel it shows who the private message was recieved<br>from. In this case, &quot;chan&quot; (right panel) sent a private message to &quot;jay&quot;<br>(middle panel) but &quot;sai&quot;(left panel) cannot see the message.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show the server-side code snippets of how this feature works</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-21T05.07.37private%20messaging.PNG.webp?alt=media&token=7c522778-cfc1-4efd-b9a3-172795ac9b7f"/></td></tr>
<tr><td> <em>Caption:</em> <p>The code block shows how the whisper command is processed and how the<br>sender and whisper target are the only ones to get the message.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the code logic of how this was achieved</td></tr>
<tr><td> <em>Response:</em> <div>First, the code checks if the start of the text statement begins with<br>"@", if it does then the statement is split to retrieve the recipientName<br>and the privateMessage. In the synchronized(clients) block, it accesses the list of clients<br>that are active. For every client in the recipient of ServerThread, it checks<br>if the client name matches the recipient name. If it does, then a<br>message is displayed to the sender to who the private message was sent<br>in bold and then the private message is sent to the client ID<br>rather than the user name in order for the client to privately receive&nbsp;<br>the message. Then the break statement is used to end the code block<br>and finally, wasCommand is set to true.</div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 6: </em> Mute/Unmute </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots demoing this feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-25T04.19.36Mute%20Demo.PNG.webp?alt=media&token=5d687e30-4be7-4080-94e6-5e3b4d59ec48"/></td></tr>
<tr><td> <em>Caption:</em> <p>In this demo, it shows 3 users in the room talking with each<br>other. Then the left client (sai) mutes the middle client (jay). The picture<br>shows the messages from the middle client only displaying to itself and the<br>right client (chan).<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-25T04.19.41unmute%20demo.PNG.webp?alt=media&token=1ba17a7c-e8a0-4ae1-a385-0c46a97bed6b"/></td></tr>
<tr><td> <em>Caption:</em> <p>In this snapshot, it shows that the left client has unmuted the middle<br>client and is once again receiving messages from the middle client.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshots of the code snippets that achieve this feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-25T04.45.21ServerThread%20mute%20list%20code.PNG.webp?alt=media&token=967b8b01-ed68-46d7-bd91-0d6d8dca0d55"/></td></tr>
<tr><td> <em>Caption:</em> <p>This picture shows the mute array list, from the ServerThread, and the methods<br>that access it.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-25T04.45.33command%20processing%20code.PNG.webp?alt=media&token=68ca7bdb-0676-43c7-ba4b-c69fcea43a10"/></td></tr>
<tr><td> <em>Caption:</em> <p>This picture shows how the /mute and /unmute commands are processed.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-25T04.45.43mute%20and%20unmute%20methods%20code.PNG.webp?alt=media&token=970b118f-0409-48f5-a83f-6e28ff101b82"/></td></tr>
<tr><td> <em>Caption:</em> <p>This picture shows how the user is added to the mute list from<br>ServerThread when the mute and unmute methods are called.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-25T04.50.35checks%20if%20user%20is%20muted%20code.PNG.webp?alt=media&token=62ff2816-60cd-42c7-a93d-0d3d18539d44"/></td></tr>
<tr><td> <em>Caption:</em> <p>This code, in the &quot;sendMessage()&quot; method, checks whether the client is muted or<br>not and decides if the muted/unmuted client should receive messages from the mute/unmute<br>command sender.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the code logic of how this was achieved</td></tr>
<tr><td> <em>Response:</em> <p>In ServerThread.java, the code has a private mute list array list and methods<br>to access the array list. These methods, when called, add the target user<br>to the mute list and remove the user from the mute list. Another<br>method &quot;isMuted()&quot; checks whether the user is muted or not. Another method called<br>&quot;getMuteList()&quot; returns the mute list when the method is called. In Room.java, the<br>cases, MUTE and UNMUTE are used to extract the name of the target<br>from the command, the name is trimmed to remove whitespaces and then used<br>in the parameters of the mute or unmute methods, depending on which command<br>was detected from the sender message. If the &quot;muteUser()&quot; method is called, the<br>target name is used in the &quot;addToMutedUsers&quot; method call from the method in<br>ServerThread, which adds the user to the muted list. If the &quot;unmuteUser()&quot; method<br>is called, the target name is used in the &quot;removeFromMutedUsers()&quot; method call, which<br>removes the user from the muted list. In the fourth picture (code in<br>the sendMessage() method), the code checks whether the target user is muted or<br>not to determine if the target should still be muted or be allowed<br>to receive messages again. The code essentially double-checks the muted/unmuted status of the<br>target.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 7: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Pull request from milestone3 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/sjc65/sjc65-IT114-450/pull/13">https://github.com/sjc65/sjc65-IT114-450/pull/13</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-chatroom-milestone3/grade/sjc65" target="_blank">Grading</a></td></tr></table>