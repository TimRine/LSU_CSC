// Timothy Rine and Joseph Nguyen
// Baumgartner
// CSC4101 Project 1

import java.io.*;

class Ident extends Node 
{
    private String name;

    public Ident(String n)
    {
        name = n;
    }

    public void print(int n) 
    {
        System.out.print(name);
    }

    public void print(int n, boolean parenPrintedLast) 
    {
            print(n);
    }
    
    public String getIdentName() 
    {
        return name;
    }

    @Override
    public boolean isSymbol() 
    {
        return true;
    }
}