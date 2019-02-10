// UPDATED 
We aren't sure why this didn't compile using the grading script because it compiled and tested fine for us on the classes server.
In an attempt to fix this error, we added the correct directory structure from tiger/chap2 along with the provided makefile.



Project 1
Harry Ly cs435131
Timothy Rine cs435142

From our test cases, the lexical analyzer outputs the identifiers for each of the variables in the symbols class accurately. We used the three states of comments, strings, and ignores that were instructed to us. For each of the specific characters/reserved words, we returned the token that represents them. For the comments, we started a counter for when it detects /* and */ to signify the end of the comments. For the strings, we detected if it was a new line or not to continue or pause the parsing. For the ignore, we ignored new lines, tabs, and comments starting with //. 
