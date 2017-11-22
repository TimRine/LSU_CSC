// Timothy Rine and Joseph Nguyen
// Baumgartner
// CSC4101 Project 1

import java.io.*;

class Quote extends Special 
{
    private Cons list = null;

    public Quote(Cons list)
    {
        this.list = list;
    }

    @Override
    void print(Node t, int n, boolean p) 
    {
        System.out.print("'");
        ((Cons)list.getCar()).printQuote(n, false);
    }

    @Override
    void printQuote(Node t, int n, boolean p)
    {
        print(t, n, p);
    }
}