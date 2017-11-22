Partners: Timothy Rine (cs410164) and Joseph Nguyen (cs410155)
Course: CSC 4101-1
Instructor: Baumgartner
Date: 14 November 2017

The Scheme Pretty Printer was tested in Intellij Idea.

How to compile and run:
  (in the prog2 directory)
    $ javac -g *.java
    $ java Main
  OR
    $ build.sh  
  * build.sh will create and run interpreter.jar
    
    Once running, our interpreter should look like:
        Input:
    Then enter scheme commands to run tests.  The interpreter can be 
    closed using (quit), (exit), or Ctrl+C.
    
    Our interpreter can also be run using standard/file input as outlined in the project instructions, e.g.:
        $ java Main < test.scm
      OR
        $ build.sh < test.scm
    which will run the file's commands in the Interpreter and 
return the output.

The "prog2" directory should include:
    - README.txt
    - build.sh
    - manifest.txt
    - 29 Java source code files:
        Begin.java, BooleanLit.java, BuiltIn.java, Closure.java, 
        Cond.java, Cons.java, Define.java, Environment.java, 
        Ident.java, IdentToken.java, If.java, IntLit.java, 
        IntToken.java, Lambda.java, Let.java, Main.java, Nil.java, 
        Node.java, Parser.java, Printer.java, Quote.java, 
        Regular.java, Scanner.java, Set.java, Special.java, 
        StrLit.java, StrToken.java, Token.java, TokenType.java
        
*Known Issues*
-No newline error when evaluating the factorial testcase using the verify script
-Unneccessary space after ending StrLit while evaluating list operations like cons, cdr, etc
-I/O function "read" does not work properly
