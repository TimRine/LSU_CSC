// Timothy Rine and Joseph Nguyen
// Baumgartner
// CSC4101 Project 1

class IdentToken extends Token 
{
    private String name;
    
    public IdentToken(String s) 
    {
        super(TokenType.IDENT);
        name = s;
    }
    String getName() 
    {
        return name;
    }
}