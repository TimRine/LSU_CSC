README.txt

Harry Ly 	CSC435131
Timothy Rine 	CSC435142
Project 2
Baumgartner

PARSER
---------------
This project was to use CUP to implement a parser for the Tiger language and write semantic actions to produce a parse
tree. We tested Grm.cup using the run command 'Parse.Main test.tig', on files such as test99.tig and test98.tig which 
are included in our prog2 directory and contain Tiger programs such as '(a := 5; a+1)' which was given on page 102 
of the textbook. 

To implement error recovery, we followed the CUP manual on the course webpage. The error recovery 
example given was: 

stmt ::= expr SEMI | while_stmt SEMI | if_stmt SEMI | ... |
	     error SEMI
	     ;

We used this syntax as a model for implementing error recovery for dec, optArguments, fieldExprs,  optFieldExprs, optFields, 
fields, and nameType. The method of error recovery is described as this in the project details: while parsing, if a syntax 
error is discovered then the parser replaces the bad input token with 'error' and then continues parsing. 

To eliminate parsing conflicts, we again followed the outline in the CUP user manual on the course webpage 
(http://www.csc.lsu.edu/~gb/csc4351/CUP/manual.html#precedence). By assigning precedence (from top to 
bottom is low to high precedence) we can resolve shift-reduce problems. We defined the following precedence rules:

precedence nonassoc THEN;
precedence nonassoc ELSE;
precedence nonassoc ASSIGN;
precedence left OR;
precedence left AND;
precedence nonassoc EQ, NEQ, LT, LE, GT, GE;
precedence left PLUS, MINUS;
precedence left TIMES, DIVIDE;