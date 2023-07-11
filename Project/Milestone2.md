<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone 2</td></tr>
<tr><td> <em>Student: </em> Sai Chandra (sjc65)</td></tr>
<tr><td> <em>Generated: </em> 7/11/2023 7:27:32 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-chatroom-milestone-2/grade/sjc65" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone2 from the proposal document:&nbsp; <a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Payload </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Payload Screenshots</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-10T01.10.24deliverable%201.5.PNG.webp?alt=media&token=4b2b4d44-ec10-4adf-bfdb-70966c2e3bbc"/></td></tr>
<tr><td> <em>Caption:</em> <p>In this picture, it shows the main payload properties with their purpose on<br>each property.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-10T01.30.40deliverable1.PNG.webp?alt=media&token=9e5da637-ae69-47f3-81bb-0a526d01439b"/></td></tr>
<tr><td> <em>Caption:</em> <p>This shows the added timestamp payload property that has a comment explaining what<br>it does and its purpose<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-10T01.12.54terminal.PNG.webp?alt=media&token=4d4550d5-169a-4389-b668-c17870cf7674"/></td></tr>
<tr><td> <em>Caption:</em> <p>The terminal shows the payload properties displayed in both the server and the<br>client<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Server-side commands </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the mentioned commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T01.10.32coinFlip%20feature.PNG.webp?alt=media&token=8c147443-41b6-491e-aa48-ea87e3cf7626"/></td></tr>
<tr><td> <em>Caption:</em> <p>The code shows the logic behind the coin flip between heads and tails.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T20.35.11Dice%20Roll%20Command%20Processing.PNG.webp?alt=media&token=58d9d6ad-afa7-4d12-9ef9-6d4859690690"/></td></tr>
<tr><td> <em>Caption:</em> <p>This code shows how the dice roll command is processed for both formats.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T20.35.13Code%20for%20Both%20Format%20Processing.PNG.webp?alt=media&token=82c6ffe9-c210-4b07-8789-640812731a8c"/></td></tr>
<tr><td> <em>Caption:</em> <p>This code shows the format processing methods for the &quot;/roll #&quot; and &quot;/roll<br>#d#&quot; formats. <br>(The first method processes the &quot;/roll #&quot; format and the second<br>method processes the &quot;/roll #d#&quot; format).<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T22.32.29Dice%20%26%20Roll%20Commands.PNG.webp?alt=media&token=a2d5aaf9-ed50-4509-9e12-caca99be963d"/></td></tr>
<tr><td> <em>Caption:</em> <p>These are the dice roll and coin flip commands<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Explain the logic/implementation of each commands</td></tr>
<tr><td> <em>Response:</em> <p><b>Coin Toss:</b><div><div>The &quot;coinFlip()&quot; function creates a random object called &quot;rand&quot; and a String<br>variable called &quot;message&quot;. The &quot;.nextInt(2)&quot; is used on the rand object to randomize<br>between 0 and 1 and then assign the result of it to the<br>&quot;result&quot; variable. If the result is equal to 0, then the message&#39;s value<br>is &quot;heads&quot; otherwise the value is &quot;tails&quot;. Lastly, the &quot;message&quot; variable is used<br>in the &quot;sendMessage&quot; parameters to be displayed to all clients.</div></div><div><br></div><div><b>Dice Roll:</b></div><div>In the Case<br>ROLL, the code first checks if the string &quot;comm2[1]&quot; contains the letter &quot;d&quot;.<br>If it does then the &quot;#d#&quot; roll format code is executed. The &quot;x&quot;<br>and &quot;y&quot; char variables have the values of &quot;comm2[1].charAt(0)&quot; and &quot;comm2[1].charAt(2)&quot;, respectively, assigned<br>to them. Then the characters are converted to ints and assigned to the<br>int variables &quot;num&quot; and &quot;sides&quot;. They are then used in the parameters of<br>the diceRoll function call. If the string &quot;comm2[1]&quot; does not contain the letter<br>&quot;d&quot; then &quot;comm2[1]&quot; is used in &quot;Integer.parseInt(comm2[1])&quot; to convert the string to an<br>int. Then &quot;num&quot; is used in the parameter of the diceRoll function call.</div><div><ul><li><b><u>Format:<br>/roll #:</u></b></li></ul></div><div><div style="">In the &quot;diceRoll()&quot; method, it uses &quot;num&quot; as its parameter and<br>processes the &quot;/roll #&quot; command format. First, a Random object, called &quot;rand&quot;, is<br>created, then &quot;rand.nextInt(num)&quot; is used to return a random value between 0 and<br>&quot;num&quot;, then that value is assigned to the &quot;result&quot; variable. Lastly, it is<br>appended to a string and assigned to the &quot;message&quot; variable which is then<br>used in the &quot;sendMessage(client, message)&quot; function.</div><div style=""><ul><li><u><b>Format: /roll #d#:</b></u></li></ul></div><div style="">In the &quot;diceRoll()&quot; method,<br>it uses &quot;num&quot; and &quot;sides&quot; as its parameters and processes the &quot;/roll #d#&quot;<br>command format. First, a random object and string variable are created, &quot;rand&quot; and<br>&quot;rollResults&quot; respectively. In the for-loop, &quot;num&quot; is used as the iteration limit. Through<br>each iteration, the &quot;roll&quot; integer variable is assigned the value of the result<br>of &quot;rand.nextInt(sides) + 1&quot;, which randomly chooses a number between 1 and &quot;sides&quot;<br>and appends that to &quot;rollResults&quot;. If &quot;i&quot; is less than &quot;num&quot; - 1,<br>a comma is appended to rollResults as well. In other words, the for-loop<br>randomizes the value of &quot;roll&quot; and then separates the values in &quot;rollResults&quot; using<br>a comma as &quot;roll&quot; is being appended to &quot;rollResults&quot;.&nbsp; Lastly, the string results<br>are assigned to &quot;message&quot; and then &quot;message&quot; is used in the &quot;diceRoll(client, message)&quot;<br>function call.</div></div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Text Display </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the various style handling via markdown or special characters</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T22.29.19Text%20Formatting%20Code.PNG.webp?alt=media&token=468c0284-fa66-4f6f-b5b5-995bf4b67d48"/></td></tr>
<tr><td> <em>Caption:</em> <p>The code shows how the bold, italics, underline, red, green, and blue text<br>formats are processed. <br><br>The comments next to each &quot;.replaceAll()&quot; explain what formatting each<br>method does.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T22.33.36Message%20Format%20Rerouter%20Code.PNG.webp?alt=media&token=7fb3861a-31ee-4a58-bd47-2a7fbcd941ed"/></td></tr>
<tr><td> <em>Caption:</em> <p>The code shows how the &quot;textFormat()&quot; method is used to apply the text<br>formats.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T22.45.46Text%20Formatting%20Commands.PNG.webp?alt=media&token=1ddfd8d0-22e1-435b-a2a9-23a099bc8e07"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is a list of the text formatting symbols that can be used.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show source message and the result output in the terminal (note, you won't actually see the styles until Milestone3)</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T23.03.00bold%20output.PNG.webp?alt=media&token=536a8071-781a-4e83-b5c1-a86018853532"/></td></tr>
<tr><td> <em>Caption:</em> <p>(Client on right-side) The Terminal shows the input bold text and then the<br>result formatting.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T23.03.06italic%20output.PNG.webp?alt=media&token=f6298e97-bdd9-4f98-a435-2e6b1aab0cbe"/></td></tr>
<tr><td> <em>Caption:</em> <p>(Client on right-side) The terminal shows the input italic text and then the<br>result formatting.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T23.03.10underline%20output.PNG.webp?alt=media&token=a0e6dd98-ed70-48b2-b23c-3f5ca538fdce"/></td></tr>
<tr><td> <em>Caption:</em> <p>(Client on right-side) The Terminal shows the input underline text and then the<br>result formatting.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T23.03.16red%20output.PNG.webp?alt=media&token=3a2a07ef-8223-4d82-be17-941d56d1d7cd"/></td></tr>
<tr><td> <em>Caption:</em> <p>(Client on right-side) The terminal shows the input red text and then the<br>result formatting.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T23.03.21green%20output.PNG.webp?alt=media&token=71cfa598-4fea-49b7-98c3-811b3b6f4021"/></td></tr>
<tr><td> <em>Caption:</em> <p>(Client on right-side) The terminal shows the input green text and then the<br>result formatting.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T23.03.26blue%20output.PNG.webp?alt=media&token=932c48be-3b35-40e3-8032-a9566cf9a967"/></td></tr>
<tr><td> <em>Caption:</em> <p>(Client on right-side) The terminal shows the input blue text and then the<br>result formatting.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-07-11T23.03.33all%20format%20output.PNG.webp?alt=media&token=bbd9ce40-2548-4bd7-a8ec-51a5ad623a88"/></td></tr>
<tr><td> <em>Caption:</em> <p>(Client on right-side) The Terminal shows the input text and then all the<br>formats combined.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Explain how you got each style applied</td></tr>
<tr><td> <em>Response:</em> <p>First I used &quot;p.getMessage()&quot; as a parameter in the &quot;textFormat()&quot; method and then<br>used the method call in the &quot;currentRoom.sendMessage()&quot; function to reroute the client messages<br>through the text format method. In the &quot;textFormat()&quot; method, it takes in &quot;message&quot;<br>as a parameter. I used &quot;.replaceAll()&quot; on &quot;message&quot; with the regex and the<br>replaced text as parameters.&nbsp;<div><ul><li>For Bold:</li></ul><div>I used the regex, &quot;\<em>\</em>(.<em>?)\</em>\<em>&quot;, to replace the message<br>characters wrapped in ** and then returned the bolded message.<br></div><ul><li>For Italics:&nbsp;</li></ul><div>I used the<br>regex, &quot;\</em>(.<em>?)\</em>&quot;, to replace the message characters wrapped in * and then returned<br>the italicized message.<br></div><ul><li>For underline:</li></ul><div>I used the regex, &quot;<em>(.*?)</em>&quot;, to replace the message characters<br>wrapped in _ and then returned the underlined message.<br></div><ul><li>For the colors:</li></ul><div>I used the<br>regex, &quot;!(.<em>?)!&quot;, to replace the message characters wrapped in ! and then returned<br>the red text-colored message.<br></div></div><div>I used the regex, &quot;\+(.</em>?)\+&quot;, to replace the message characters<br>wrapped in + and then returned the green text-colored message.<br></div><div>I used the regex,<br>&quot;\-(.*?)\-&quot;, to replace the message characters wrapped in - and then returned the<br>blue text-colored message.<br></div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Include the pull request for Milestone2 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/sjc65/sjc65-IT114-450/pull/11">https://github.com/sjc65/sjc65-IT114-450/pull/11</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-chatroom-milestone-2/grade/sjc65" target="_blank">Grading</a></td></tr></table>