// Timothy Rine and Joseph Nguyen
// CSC 4101-1
// Baumgartner
// Main.java -- the main program
public class Main 
{
    // Array of token names used for debugging the scanner
    private static final String TokenName[] = 
    { 
        "QUOTE", // '
        "LPAREN", // (
        "RPAREN", // )
        "DOT", // .
	"TRUE", // #t
	"FALSE", // #f
	"INT", // integer constant
	"STRING", // string constant
	"IDENT" // identifier
    };

    public static void main(String argv[]) 
    {
	// Create scanner that reads from standard input
	Scanner scanner = new Scanner(System.in);
	if (argv.length > 1) 
        {
            System.err.println("Usage: java Main [-d]");
            System.exit(1);
	}
	// If commandline option -d is provided, debug the scanner
	if (argv.length == 1 && argv[0].equals("-d")) 
        {
            // Debug scanner
            Token tok = scanner.getNextToken();
            while (tok != null) 
            {
		int tt = tok.getType();
		System.out.print(TokenName[tt]);
		if (tt == Token.INT) 
                {
                    System.out.println(", intVal = " + tok.getIntVal());
                } 
                else if (tt == Token.STRING) 
                {
                    System.out.println(", strVal = " + tok.getStrVal());
		} 
                else if (tt == Token.IDENT) 
                {
                    System.out.println(", name = " + tok.getName());
		} 
                else 
                {
                    System.out.println();
		}
		tok = scanner.getNextToken();
            }
            System.exit(0);
	}

	System.out.print("Input: ");

	// Create parser
	Parser parser = new Parser(scanner);
	Node root;
	
        // Interpreter prog 2 instructions
	Environment builtin_env = new Environment();
	Ident id;
	id = new Ident("b+");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("b-");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("b*");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("b/");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("b=");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("b<");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("b>");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("car");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("cdr");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("cons");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("set-car!");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("set-cdr!");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("symbol?");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("number?");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("null?");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("pair?");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("eq?");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("procedure?");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("display");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("newline");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("exit");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("quit");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("read");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("write");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("eval");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("apply");
	builtin_env.define(id, new BuiltIn(id));
	id = new Ident("interaction-environment");
	builtin_env.define(id, new BuiltIn(id));
	Environment env = new Environment(builtin_env);
	BuiltIn use = new BuiltIn(env);

	// Parse and pretty-print each expression from the input
	root = parser.parseExp();
	while (root != null) 
        {
            if (env != null) 
            {
                try 
                {
                    root.eval(env).print(0);
		} catch (NullPointerException e) 
                {
                    System.err.println("Error: Undefined variable");
		} finally 
                {
                    System.out.print("Input: ");
                    root = parser.parseExp();
		}
            }
            else 
            {
		System.err.println("Error: Environment is not defined");
            }
	}
            System.exit(0);
    }
}