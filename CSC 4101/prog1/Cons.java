// Timothy Rine and Joseph Nguyen
// Baumgartner
// CSC4101 Project 1

class Cons extends Node 
{
    private Node car;
    private Node cdr;
    private Special form;

    void parseList() 
    { 
        if (car instanceof Ident) 
        {
            Ident ident = (Ident)car;
            String identName = ident.getIdentName();

            if (identName.equals("quote") || identName.equals("'")) 
            {
		form = new Quote((Cons)cdr);
            } 
            else if (identName.equals("lambda")) 
            {
		form = new Lambda();
            } 
            else if (identName.equals("begin")) 
            {
		form = new Begin();
            } 
            else if (identName.equals("if")) 
            {
		form = new If();
            } 
            else if (identName.equals("let")) 
            {
		form = new Let();
            } 
            else if (identName.equals("cond")) 
            {
		form = new Cond();
            } 
            else if (identName.equals("define")) 
            {
		form = new Define();
            } 
            else if (identName.equals("set!")) 
            {
		form = new Set(this);
            } 
            else 
            {
		form = new Regular(this);
            }
        } 
        else if (car instanceof IntLit) 
        {
            form = new Regular(this);
        } 
        else if (car instanceof StrLit) 
        {
            form = new Regular(this);
        }
        else if (car instanceof BooleanLit) 
        {
            form = new Regular(this);
        } 
        else if (car instanceof Nil) 
        {
            form = new Regular(this);
        } 
        else 
        {
            form = new Regular(this);
        }
    }

    public Cons(Node a, Node d) 
    {
        car = a;
        cdr = d;
        parseList();
    }

    void print(int n) 
    {
        form.print(this, n, false);
    }

    void print(int n, boolean p) 
    {
        form.print(this, n, p);
    }

    @Override
    public boolean isPair() 
    {
	return car != null && cdr != null;
    }

    @Override
    public Node getCar()
    {
	return car;
    }

    @Override
    public Node getCdr()
    {
	return cdr;
    }

    @Override
    public void setCar(Node a)
    {
        car = a; //had 2 ; "a;;"
    }

    @Override
    public void setCdr(Node d)
    {
	car = d;
    }

    public void printQuote(int n, boolean p)
    {
	form.printQuote(this, n, p);
    }
}