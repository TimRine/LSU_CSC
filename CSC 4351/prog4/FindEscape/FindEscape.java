package FindEscape;

public class FindEscape {
  Symbol.Table escEnv = new Symbol.Table(); // escEnv maps Symbol to Escape
  Abysn.FunctionDec parentFunc = null;

  public FindEscape(Absyn.Exp e) { traverseExp(0, e);  }

  void traverseVar(int depth, Absyn.Var var) 
  {
	  if(var instanceof Absyn.FieldVar)
	  {
		  traverseVar(depth, (Absyn.FieldVar)var);
	  }
	  else if(var instanceof Absyn.SimpleVar)
	  {
		  traverseVar(depth, (Absyn.SimpleVar)var);
	  }
	  else if(var instanceof Absyn.SubscriptVar)
	  {
		  traverseVar(depth, (Absyn.SubscriptVar)var);
	  }
	  else
	  {
		  throw new Error("Invalid traverse.");
	  }
  }

  void traverseExp(int depth, Absyn.Exp exp) 
  {
	  if(exp instanceof Absyn.ArrayExp)
	  {
		  traverseExp(depth, (Absyn.ArrayExp)exp);
	  }
	  else if(exp instanceof Absyn.AssignExp)
	  {
		  traverseExp(depth, (Absyn.AssignExp)exp);
	  }
	  else if(exp instanceof Absyn.WhileExp)
	  {
		  traverseExp(depth, (Absyn.WhileExp)exp);
	  }
	  else if(exp instanceof Absyn.VarExp)
	  {
		  traverseExp(depth, (Absyn.VarExp)exp);
	  }
	  else if(exp instanceof Absyn.StringExp)
	  {
		  traverseExp(depth, (Absyn.StringExp)exp);
	  }
	  else if(exp instanceof Absyn.SeqExp)
	  {
		  traverseExp(depth, (Absyn.SeqExp)exp);
	  }
	  else if(exp instanceof Absyn.RecordExp)
	  {
		  traverseExp(depth, (Absyn.RecordExp)exp);
	  }
	  else if(exp instanceof Absyn.OpExp)
	  {
		  traverseExp(depth, (Absyn.OpExp)exp);
	  }
	  else if(exp instanceof Absyn.NilExp)
	  {
		  traverseExp(depth, (Absyn.NilExp)exp);
	  }
	  else if(exp instanceof Absyn.LetExp)
	  {
		  traverseExp(depth, (Absyn.LetExp)exp);
	  }
	  else if(exp instanceof Absyn.IntExp)
	  {
		  traverseExp(depth, (Absyn.IntExp)exp);
	  }
	  else if(exp instanceof Absyn.IfExp)
	  {
		  traverseExp(depth, (Absyn.IfExp)exp);
	  }
	  else if(exp instanceof Absyn.ForExp)
	  {
		  traverseExp(depth, (Absyn.ForExp)exp);
	  }
	  else if(exp instanceof Absyn.CallExp)
	  {
		  traverseExp(depth, (Absyn.CallExp)exp);
	  }
	  else if(exp instanceof Absyn.BreakExp)
	  {
		  traverseExp(depth, (Absyn.BreakExp)exp);
	  }
	  else
	  {
		  throw new Error("Invalid traverse.");
	  }
  }

  void traverseDec(int depth, Absyn.Dec dec) 
  {
	  if(dec instanceof Absyn.FunctionDec)
	  {
		  traverseDec(depth, (Absyn.FunctionDec)dec);
	  }
	  else if(dec instanceof Absyn.VarDec)
	  {
		  traverseDec(depth, (Absyn.VarDec)dec);
	  }
	  else if(dec instanceof Absyn.TypeDec)
	  {
		  traverseDec(depth, (Absyn.TypeDec)dec);
	  }
	  else
	  {
		  throw new Error("Invalid traverse.");
	  }
  }

  void traverseVar(int depth, Absyn.FieldVar v)
  {
	  traverseVar(depth, v.var);
  }

  void traverseVar(int depth, Absyn.Subscript v)
  {
	  traverseVar(depth, v.var);
	  traverseExp(depth, v.index);
  }

  void traverseVar(int depth, Absyn.SimpleVar v)
  {
	  Escape vEsc = (Escape)escEnv.get(v.name);
	  if(vEsc != null && vEsc.depth < depth)
	  {
		  vEsc.setEscape();
	  }
  }

  void traverseExp(int depth, Absyn.ArrayExp e)
  {
	  traverseExp(depth, e.init);
	  traverseExp(depth, e.size);
  }

  void traverseExp(int depth, Absyn.AssignExp e) 
  { 
	  traverseExp(depth, e.exp);
	  traverseVar(depth, e.var);
  }

  void traverseExp(int depth, Absyn.BreakExp exp){ 
  }

  void traverseExp(int depth, Absyn.StringExp exp){
  }

  void traverseExp(int depth, Absyn.NilExp exp){
  }

  void traverseExp(int depth, Absyn.IntExp exp){
  }

  void traverseExp(int depth, Absyn.WhileExp e)
  {
	  traverseExp(depth,e.body);
	  traverseExp(depth,e.test);
  }

  void traverseExp(int depth, Absyn.VarExp exp)
  {
	  traverseVar(depth, exp.var);
  }

  void traverseExp(int depth, Absyn.SeqExp exp)
  {
	  for(Absyn.ExpList expList = exp.list; expList != null; expList = expList.tail) 
	  {
		  traverseExp(depth, expList.head);
	  }
  }

  void traverseExp(int depth, Absyn.RecordExp exp)
  {
	  for(Absyn.FieldExpList field = exp.fields; field != null; field = field.tail)
	  {
		  traverseExp(depth, field.init);
	  }
  }

  void traverseExp(int depth, Absyn.OpExp e)
  {
	  traverseExp(depth, e.right);
	  traverseExp(depth, e.left);
  }

  void traverseExp(int depth, Absyn.LetExp e)
  {
	  escEnv.beginScope();
	  for(Absyn.DecList decl = e.decs; decl != null; decl = decl.tail)
	  {
		  traverseDec(depth, decl.head);
	  }
	  traverseExp(depth, e.body);
	  escEnv.endScope();
  }

  void traverseExp(int depth, Absyn.IfExp e)
  {
	  traverseExp(depth, e.thenclause);
	  traverseExp(depth, e.test);
	  if(e.elseclause != null)
	  {
		  traverseExp(depth, e.elseclause);
	  }
  }

  void traverseExp(int depth, Absyn.CallExp e)
  {
	  if(parentsFunction != null)
	  {
		  parentFunction.leaf = false;
	  }
	  for(Absyn.ExpList args = e.args; args != null; args = args.tail)
	  {
		  traverseExp(depth, args.head);
	  }
  }

  void traverseExp(int depth, Absyn.ForExp e)
  {
	  traverseExp(depth, e.var.init);
	  escEnv.beginScope();
	  escEnv.put(e.var.name, new VarEscape(depth, e.var));
	  traverseExp(depth, e.hi);
	  traverseExp(depth, e.body);
	  escEnv.endScope();
  }

  void traverseDec(int depth, Absyn.VarDec dec)
  {
	  traverseExp(depth, dec.init);
	  escEnv.put(dec.name, new VarEscape(depth, dec));
  }

  void traverseDec(int depth, Absyn.FunctionDec d)
  {
	  depth = depth + 1;
	  Absyn.FunctionDec old = parentFunction;
	  for(Absyn.FunctionDec func = d; func != null; func = func.next)
	  {
		  parentFunction = func;
		  escEnv.beginScope();
	  }

	  for(Absyn.FieldList par = func.par; par != null; par = par.tail)
	  {
		  escEnv.put(param.name, new FormalEscape(depth, par));
	  }
	  
	  traverseExp(depth, func.body);
	  escEnv.endScope();

	  parentFunction = old;
  }

  void traverseDec(int depth, Absyn.TypeDec dec){
  }
}
