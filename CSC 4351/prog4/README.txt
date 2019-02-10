SC 4351
Project 4 -Activation Recrods
Harry Ly cs435131
Timothy Rine cs435142
Baumgartner

-------------------------------
Compilation:
    javac -g */*.java
    
Testing (on test file, test.tig):
    java Semant.Main test.tig
    
--------------------------------
For this project, we "augmented the Semant package to allocate locations for local variables, and to 
keep track of the nesting level, which includes the options for finding escapes and handling functions
with more than k formal parameters" as described in the project handout. The main files that we worked 
with were FindEscape, MipsFrame, & Semant.
