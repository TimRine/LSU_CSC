// Timothy Rine and Joseph Nguyen
// Baumgartner
// CSC4101 Project 1

import java.io.*;

class Parser 
{
    private Scanner scanner;
    
    public Parser(Scanner s) 
    {
        scanner = s;
    }

    public Node parseExp() 
    {
        Node exp = null;
        Token token = scanner.getNextToken();
        int ttype = token.getType();

        if (token == null)
        {
            exp = null;
        }

        else if (ttype == 0)
        {
            exp = new Cons(new Ident("'"), new Cons(parseExp(), null));
        }
        
        else if (ttype == 1)
        {
            exp = parseRest(true);
        }
        
        else if (ttype == 2) 
        {
            System.out.println("Token Error: )");
            exp = parseExp();
        }

        else if (ttype == 3) 
        {
            System.out.println("Token Error: .");
            exp = parseExp();
        }

        else if (ttype == 4)
        {
            exp = new BooleanLit(true);
        }
        
        else if (ttype == 5)
        {
            exp = new BooleanLit(false);
        }
        
        else if (ttype == 6)
        {
            exp = new IntLit(token.getIntVal());
        }
        
        else if (ttype == 7)
        {
            exp = new StrLit(token.getStrVal());
        }
        
        else if (ttype == 8)
        {
            exp = new Ident(token.getName());
        }
        
        //Else there must be an error
        else
        {
            System.out.println("Token Error Type: " + ttype);
        }
            return exp;
    }

    protected Node parseRest(boolean first) 
    {
        Token token = scanner.getNextToken();
        int ttype = token.getType();
        Node exp;
        if (token == null)
        {
            exp = null;
        }
        else if (ttype == 2)
        {
            if (first)
            {
                return new Nil();
            }
            else
            {
                return null;
            }
        }
        else if (ttype == 3) 
        {
            token = scanner.getNextToken();
            if (ttype != 2) 
            {
                scanner.putBack(token);
                exp = new Cons(parseExp(), null);
            }

            else 
            {
                System.out.println("Token Error: unexpected ')'");
                exp = parseExp();
            }
        }

        else 
        {
            scanner.putBack(token);
            exp = new Cons(parseExp(), parseRest(false));
        }
        return exp;
    }
}