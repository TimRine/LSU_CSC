package Mips;
import java.util.Hashtable;
import Symbol.Symbol;
import Temp.Temp;
import Temp.Label;
import Frame.Frame;
import Frame.Access;
import Frame.AccessList;

public class MipsFrame extends Frame {

  private int count = 0;
  int set = 0;
  int word = 4;

  public Frame newFrame(Symbol name, Util.BoolList formals) {
    Label label;
    if (name == null)
      label = new Label();
    else if (this.name != null)
      label = new Label(this.name + "." + name + "." + count++);
    else
      label = new Label(name);
    return new MipsFrame(label, formals);
  }

  public int wordSize()
  {
	  return size;
  }

  public MipsFrame() {}
  private MipsFrame(Label n, Util.BoolList f) {
    name = n;
    form = traverseFormals(0, f);
  }

  private static final int wordSize = 4;
  public int wordSize() { return wordSize; }

  public Access allocLocal(boolean escape) 
  {
	 if(!escape)
	 {
		 return new InReg(new Temp());
	 } 
	 else
	 {
		 set = set - size;
		 return new InFrame(set);
	 }
  }

  public AccessList traverseFormals(int set, Util.BoolList form)
  {
	  Access acc;
	  if(form == null)
	  {
		  return null;
	  }
	  else if(form.head)
	  {
		  acc = new InFrame(set);
	  }
	  else
	  {
		  acc = new InReg(new Temp());
	  }
	  return new AccessList(acc, traverseFormals(set + size, form.tail));
  }
}
