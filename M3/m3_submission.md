<table><tr><td> <em>Assignment: </em> IT114 - Number Guesser</td></tr>
<tr><td> <em>Student: </em> Sai Chandra (sjc65)</td></tr>
<tr><td> <em>Generated: </em> 6/12/2023 9:05:44 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-number-guesser/grade/sjc65" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create the below branch name</li><li>Implement the NumberGuess4 example from the lesson/slides</li><ol><li><a href="https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f&nbsp;">https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f&nbsp;</a><br></li></ol><li>Add/commit the files as-is from the lesson material (this is the base template)</li><li>Pick two (2) of the following options to implement</li><ol><li>Display higher or lower as a hint after a wrong guess</li><li>Implement anti-data tampering of the save file data (reject user direct edits)</li><li>Add a difficulty selector that adjusts the max strikes per level</li><li>Display a cold, warm, hot indicator based on how close to the correct value the guess is (example, 10 numbers away is cold, 5 numbers away is warm, 2 numbers away is hot; adjust these per your preference)</li><li>Add a hint command that can be used once per level and only after 2 strikes have been used that reduces the range around the correct number (i.e., number is 5 and range is initially 1-15, new range could be 3-8 as a hint)</li><li>Implement separate save files based on a "What's your name?" prompt at the start of the game</li></ol><li>Fill in the below deliverables</li><li>Create an m3_submission.md file and fill in the markdown from this tool when you're done</li><li>Git add/commit/push your changes to the HW branch</li><li>Create a pull request to main</li><li>Complete the pull request</li><li>Grab the link to the m3_submission.md from the main branch and submit that direct link to github</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Implementation 1 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-06-13T00.30.10option_1.PNG.webp?alt=media&token=f42fd4cc-b588-43bb-b1c2-03d93217ddf4"/></td></tr>
<tr><td> <em>Caption:</em> <p>Option 1: &quot;Higher/Lower&quot; Code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-06-13T00.30.19option1output.PNG.webp?alt=media&token=68d25bf8-e2c1-4562-a926-999114376715"/></td></tr>
<tr><td> <em>Caption:</em> <p>Option 1: &quot;Higher/Lower&quot; Code Output (Highlighted the output from this feature)<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>For option 1, the feature checks to see if the &quot;guess&quot; is higher<br>or lower than the &quot;number&quot;. If the guess is lower than the number,<br>then the terminal prompts the player to choose a higher number. If the<br>guess is higher than the number, the terminal prompts the player&nbsp;to choose a<br>lower number. This feature is added to the &quot;else&quot; statement if the &quot;guess&quot;<br>is not equal to the &quot;number&quot;.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Implementation 2 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-06-13T00.38.40option%203%20Code.PNG.webp?alt=media&token=0743a5cb-a769-4590-b641-f13dbd92ba77"/></td></tr>
<tr><td> <em>Caption:</em> <p>Option 3: &quot;Difficulty Selector&quot; Code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fsjc65%2F2023-06-13T00.38.46Option%203%20Code%20Output.PNG.webp?alt=media&token=fe369791-a951-4a6a-af5e-da12fa944e1a"/></td></tr>
<tr><td> <em>Caption:</em> <p>Option 3: &quot;Difficulty Selector&quot; Code Output<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>For option 3, the player is first prompted with a selection of level<br>difficulty choices. The player is then asked to enter a number corresponding to<br>their choice. The number is assigned to the &quot;diffChoice&quot; variable and is run<br>through a series of switch-case statements. Based on the diffChoice value, the switch-case<br>finds the best case associated with the value and assigns a number to<br>the &quot;strikesPerLevel&quot; variable. After the switch case is exited, the &quot;strikesPerLevel&quot; value is<br>assigned to the &quot;maxStrikes&quot; variable.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a link to the related pull request of this hw</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/sjc65/sjc65-IT114-450/pull/3">https://github.com/sjc65/sjc65-IT114-450/pull/3</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Discuss anything you learned during this lesson/hw or any struggles you had</td></tr>
<tr><td> <em>Response:</em> <p>I learned how the different methods work together to manage the different features<br>the game uses. I also learned how the game creates a save file<br>and overwrites that data based on the game&#39;s current session.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-450-M23/it114-number-guesser/grade/sjc65" target="_blank">Grading</a></td></tr></table>