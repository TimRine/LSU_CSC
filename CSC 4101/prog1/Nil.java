// Timothy Rine and Joseph Nguyen
// Baumgartner
// CSC4101 Project 1

import java.io.*;

class Nil extends Node 
{
    //Default constructor
    public Nil() {}

    public void print(int n)		
    {
        print(n, false); 
    }

    public void print(int n, boolean p) 
    {
        for (int i = 0; i < n; i++)
        {
            System.out.print(" ");
        }

        if (p) 
        {
            System.out.println(")");
        } 
        
        else 
        {
            System.out.println("()");
        }
    }
}