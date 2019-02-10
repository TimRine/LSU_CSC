package Parse;
import ErrorMsg.ErrorMsg;


class Yylex implements Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YYEOF = '\uFFFF';

private void newline() {
  errorMsg.newline(yychar);
}
private void err(int pos, String s) {
  errorMsg.error(pos,s);
}
private void err(String s) {
  err(yychar,s);
}
private java_cup.runtime.Symbol tok(int kind) {
    return tok(kind, null);
}
private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}
private ErrorMsg errorMsg;
Yylex(java.io.InputStream s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}
String s = "";
int commentCounter = 0;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 2;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int IGNORE = 3;
	private final int yy_state_dtrans[] = {
		0,
		41,
		45,
		49
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		++yychar;
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
private int [][] unpackFromString(int size1, int size2, String st)
    {
      int colonIndex = -1;
      String lengthString;
      int sequenceLength = 0;
      int sequenceInteger = 0;
      int commaIndex;
      String workString;
      int res[][] = new int[size1][size2];
      for (int i= 0; i < size1; i++)
	for (int j= 0; j < size2; j++)
	  {
	    if (sequenceLength == 0) 
	      {	
		commaIndex = st.indexOf(',');
		if (commaIndex == -1)
		  workString = st;
		else
		  workString = st.substring(0, commaIndex);
		st = st.substring(commaIndex+1);
		colonIndex = workString.indexOf(':');
		if (colonIndex == -1)
		  {
		    res[i][j] = Integer.parseInt(workString);
		  }
		else 
		  {
		    lengthString = workString.substring(colonIndex+1);  
		    sequenceLength = Integer.parseInt(lengthString);
		    workString = workString.substring(0,colonIndex);
		    sequenceInteger = Integer.parseInt(workString);
		    res[i][j] = sequenceInteger;
		    sequenceLength--;
		  }
	      }
	    else 
	      {
		res[i][j] = sequenceInteger;
		sequenceLength--;
	      }
	  }
      return res;
    }
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 1, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		2, 0, 3, 0, 0, 0, 0, 0,
		0, 0, 4, 0, 5, 0, 0, 6,
		7, 7, 7, 7, 7, 7, 7, 7,
		7, 7, 8, 0, 9, 10, 11, 0,
		0, 12, 12, 12, 12, 12, 12, 12,
		12, 12, 12, 12, 12, 12, 12, 12,
		12, 12, 12, 12, 12, 12, 12, 12,
		12, 12, 12, 0, 13, 0, 0, 14,
		0, 15, 16, 17, 18, 19, 20, 12,
		21, 22, 12, 23, 24, 12, 25, 26,
		27, 12, 28, 29, 30, 31, 32, 33,
		12, 34, 12, 0, 0, 0, 0, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 1, 1, 1, 2, 1,
		1, 1, 1, 3, 3, 3, 3, 3,
		3, 3, 3, 3, 3, 3, 3, 3,
		3, 3, 3, 3, 3, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1,
		1, 4, 5, 6, 7, 8, 2, 9,
		10, 11, 12, 13, 14, 15, 16, 17,
		3, 18, 19, 20, 21, 22, 23, 24,
		25, 26, 27, 28, 29, 30, 31, 32,
		33, 34, 35, 36, 37, 38, 39, 40,
		41, 42, 43, 44, 45, 46, 47, 48,
		49, 50 
	};
	private int yy_nxt[][] = unpackFromString(51,35,
"1,2,3,1:2,4,42,46,50,52,1,54,56,1:2,81,83,56,58,60,62,56,64,56,66,68,70,56:3,71,56,72,85,56,-1:42,6,-1:34,11,-1:4,11,-1,11:21,29,-1,29:2,44,29,48,29:28,-1:4,5,-1:37,11,-1:4,11,-1,11:4,17,11:16,-1:6,30,-1:28,32,33,32,34,32:9,35,32:21,-1:7,11,-1:4,11,-1,11:14,18,11:6,-1:4,31,-1:30,36,-1,36,37,36:9,38,36:11,39,36:4,40,36:4,-1:10,7,-1:31,11,-1:4,11,-1,11:16,19,11:4,-1:10,8,9,-1:30,11,-1:4,11,-1,11:10,20,11:10,-1:10,10,-1:31,11,-1:4,11,-1,11:14,21,11:6,-1:7,11,-1:4,11,-1,11:5,22,11:15,-1:7,11,-1:4,11,-1,11:12,12,11:8,-1:7,11,-1:4,11,-1,11:11,23,11:9,-1:7,11,-1:4,11,-1,11:10,73,43,11:9,-1:7,11,-1:4,11,-1,11:5,24,11:15,-1:7,11,-1:4,11,-1,11:12,47,11:4,89,11:3,-1:7,11,-1:4,11,-1,11:20,25,-1:7,11,-1:4,11,-1,11:6,13,11:4,14,11:9,-1:7,11,-1:4,11,-1,11:9,26,11:11,-1:7,11,-1:4,11,-1,11:5,51,11:15,-1:7,11,-1:4,11,-1,11:5,27,11:15,-1:7,11,-1:4,11,-1,11:8,53,11:12,-1:7,11,-1:4,11,-1,11:11,28,11:9,-1:7,11,-1:4,11,-1,11:6,15,11:14,-1:7,11,-1:4,11,-1,11:7,74,11:4,16,11:7,75,-1:7,11,-1:4,11,-1,11,55,11:19,-1:7,11,-1:4,11,-1,11:15,57,11:5,-1:7,11,-1:4,11,-1,11:5,59,11:15,-1:7,11,-1:4,11,-1,11:13,61,11:7,-1:7,11,-1:4,11,-1,11,63,11:19,-1:7,11,-1:4,11,-1,11,65,11:19,-1:7,11,-1:4,11,-1,11:10,67,11:10,-1:7,11,-1:4,11,-1,11:12,69,11:8,-1:7,11,-1:4,11,-1,11:14,76,11:6,-1:7,11,-1:4,11,-1,11:14,80,11:6,-1:7,11,-1:4,11,-1,11:5,77,11:15,-1:7,11,-1:4,11,-1,11:14,82,11:6,-1:7,11,-1:4,11,-1,11:8,78,11:12,-1:7,11,-1:4,11,-1,11:7,84,11:13,-1:7,11,-1:4,11,-1,11:8,79,11:12,-1:7,11,-1:4,11,-1,11:16,86,11:4,-1:7,11,-1:4,11,-1,11:3,87,11:17,-1:7,11,-1:4,11,-1,11:11,88,11:9");
	public java_cup.runtime.Symbol nextToken ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YYEOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YYEOF == yy_lookahead && true == yy_initial) {

	{
	 return tok(sym.EOF, null);
        }
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -2:
						break;
					case 2:
						{ newline(); }
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{ return tok(sym.COMMA, null); }
					case -5:
						break;
					case 5:
						{ yybegin(COMMENT); }
					case -6:
						break;
					case 6:
						{ return tok(sym.INT, new Integer(yytext())); }
					case -7:
						break;
					case 7:
						{ return tok(sym.ASSIGN, null); }
					case -8:
						break;
					case 8:
						{ return tok(sym.LE, null); }
					case -9:
						break;
					case 9:
						{ return tok(sym.NEQ, null); }
					case -10:
						break;
					case 10:
						{ return tok(sym.GE, null); }
					case -11:
						break;
					case 11:
						{ return tok(sym.ID, yytext()); }
					case -12:
						break;
					case 12:
						{ return tok(sym.DO); }
					case -13:
						break;
					case 13:
						{ return tok(sym.IF); }
					case -14:
						break;
					case 14:
						{ return tok(sym.IN); }
					case -15:
						break;
					case 15:
						{ return tok(sym.OF); }
					case -16:
						break;
					case 16:
						{ return tok(sym.TO); }
					case -17:
						break;
					case 17:
						{ return tok(sym.END); }
					case -18:
						break;
					case 18:
						{ return tok(sym.FOR); }
					case -19:
						break;
					case 19:
						{ return tok(sym.LET); }
					case -20:
						break;
					case 20:
						{ return tok(sym.NIL); }
					case -21:
						break;
					case 21:
						{ return tok(sym.VAR); }
					case -22:
						break;
					case 22:
						{ return tok(sym.ELSE); }
					case -23:
						break;
					case 23:
						{ return tok(sym.THEN); }
					case -24:
						break;
					case 24:
						{ return tok(sym.TYPE); }
					case -25:
						break;
					case 25:
						{ return tok(sym.ARRAY); }
					case -26:
						break;
					case 26:
						{ return tok(sym.BREAK); }
					case -27:
						break;
					case 27:
						{ return tok(sym.WHILE); }
					case -28:
						break;
					case 28:
						{ return tok(sym.FUNCTION); }
					case -29:
						break;
					case 29:
						{}
					case -30:
						break;
					case 30:
						{
  if (commentCounter <= 0)
    yybegin(YYINITIAL);
  else
    commentCounter--;
}
					case -31:
						break;
					case 31:
						{
  commentCounter++;
  yybegin(COMMENT);
}
					case -32:
						break;
					case 32:
						{ s += yytext(); }
					case -33:
						break;
					case 33:
						{ err("ERROR: While parsing string: " + s + ". Expected '\"'");}
					case -34:
						break;
					case 34:
						{
  yybegin(YYINITIAL);
  return tok(sym.STRING, s);
}
					case -35:
						break;
					case 35:
						{ yybegin(IGNORE); }
					case -36:
						break;
					case 36:
						{ err("ERROR: Unexpected character '" + yytext() + "' after '\\'."); }
					case -37:
						break;
					case 37:
						{ s += "\""; yybegin(STRING); }
					case -38:
						break;
					case 38:
						{ s += "\\"; yybegin(STRING); }
					case -39:
						break;
					case 39:
						{ s += "\n"; yybegin(STRING); }
					case -40:
						break;
					case 40:
						{ s += "\t"; yybegin(STRING); }
					case -41:
						break;
					case 42:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -42:
						break;
					case 43:
						{ return tok(sym.ID, yytext()); }
					case -43:
						break;
					case 44:
						{}
					case -44:
						break;
					case 46:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -45:
						break;
					case 47:
						{ return tok(sym.ID, yytext()); }
					case -46:
						break;
					case 48:
						{}
					case -47:
						break;
					case 50:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -48:
						break;
					case 51:
						{ return tok(sym.ID, yytext()); }
					case -49:
						break;
					case 52:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -50:
						break;
					case 53:
						{ return tok(sym.ID, yytext()); }
					case -51:
						break;
					case 54:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -52:
						break;
					case 55:
						{ return tok(sym.ID, yytext()); }
					case -53:
						break;
					case 56:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -54:
						break;
					case 57:
						{ return tok(sym.ID, yytext()); }
					case -55:
						break;
					case 58:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -56:
						break;
					case 59:
						{ return tok(sym.ID, yytext()); }
					case -57:
						break;
					case 60:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -58:
						break;
					case 61:
						{ return tok(sym.ID, yytext()); }
					case -59:
						break;
					case 62:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -60:
						break;
					case 63:
						{ return tok(sym.ID, yytext()); }
					case -61:
						break;
					case 64:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -62:
						break;
					case 65:
						{ return tok(sym.ID, yytext()); }
					case -63:
						break;
					case 66:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -64:
						break;
					case 67:
						{ return tok(sym.ID, yytext()); }
					case -65:
						break;
					case 68:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -66:
						break;
					case 69:
						{ return tok(sym.ID, yytext()); }
					case -67:
						break;
					case 70:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -68:
						break;
					case 71:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -69:
						break;
					case 72:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -70:
						break;
					case 73:
						{ return tok(sym.ID, yytext()); }
					case -71:
						break;
					case 74:
						{ return tok(sym.ID, yytext()); }
					case -72:
						break;
					case 75:
						{ return tok(sym.ID, yytext()); }
					case -73:
						break;
					case 76:
						{ return tok(sym.ID, yytext()); }
					case -74:
						break;
					case 77:
						{ return tok(sym.ID, yytext()); }
					case -75:
						break;
					case 78:
						{ return tok(sym.ID, yytext()); }
					case -76:
						break;
					case 79:
						{ return tok(sym.ID, yytext()); }
					case -77:
						break;
					case 80:
						{ return tok(sym.ID, yytext()); }
					case -78:
						break;
					case 81:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -79:
						break;
					case 82:
						{ return tok(sym.ID, yytext()); }
					case -80:
						break;
					case 83:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -81:
						break;
					case 84:
						{ return tok(sym.ID, yytext()); }
					case -82:
						break;
					case 85:
						{ err("ERROR: Illegal character: " + yytext()); }
					case -83:
						break;
					case 86:
						{ return tok(sym.ID, yytext()); }
					case -84:
						break;
					case 87:
						{ return tok(sym.ID, yytext()); }
					case -85:
						break;
					case 88:
						{ return tok(sym.ID, yytext()); }
					case -86:
						break;
					case 89:
						{ return tok(sym.ID, yytext()); }
					case -87:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}
