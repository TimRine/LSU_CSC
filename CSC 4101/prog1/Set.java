// Timothy Rine and Joseph Nguyen
// Baumgartner
// CSC4101 Project 1

import java.io.*;

class Set extends Special 
{
    private Cons cons;
   
    public Set(Cons cons)
    {
        this.cons = cons;
    }

    void print(Node t, int n, boolean p) 
    {
        if(!p)
        {
            System.out.print("(");
        }
    	if (cons.getCar() instanceof Cons) 
        {
            cons.getCar().print(n, false);	
    	}
    	else 
        { 
            cons.getCar().print(n, false);
    	}
    	if (cons.getCdr() != null)
        {
            System.out.print(" ");  
        }                        
    	if (cons.getCdr() != null) 
        {
            cons.getCdr().print(n, true);
	}
            else 
        {
            System.out.print(")");		
	}
    }

    void printQuote(Node t, int n, boolean p)
    {
        print(t, n, p);
    }
}