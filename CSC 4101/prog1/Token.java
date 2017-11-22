// Timothy Rine and Joseph Nguyen
// Baumgartner
// CSC4101 Project 1

class Token implements TokenType 
{
    private int tt;

    Token(int t) 
    {
        tt = t;
    }

    int getType() 
    {
        return tt;
    }

    int getIntVal() 
    { 
        return 0; 
    }
    String getStrVal() 
    { 
        return ""; 
    }
    String getName() 
    { 
        return ""; 
    }
}