import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int lnum, int cnum) {
        linenum = lnum;
        charnum = cnum;
    }
}
class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int lnum, int cnum, int val) {
        super(lnum, cnum);
        intVal = val;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int lnum, int cnum, String val) {
        super(lnum, cnum);
    idVal = val;
    }
}
class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int lnum, int cnum, String val) {
        super(lnum, cnum);
        strVal = val;
    }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
    static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
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
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
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
				return YY_EOF;
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
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
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
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"23:9,45,26,23:2,22,23:18,45,35,21,44,23:2,43,24,38,39,30,28,41,29,42,31,18:" +
"10,23,40,33,32,34,24,23,19:26,23,25,23:2,20,23,19,1,13,8,14,11,19,17,4,19:2" +
",3,19,5,2,15,19,9,12,6,10,7,16,19:3,36,27,37,23:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,96,
"0,1,2,3,4,1,5,6,1,7,8,9,10,11,1:7,12,13,14,1:10,13:4,1,15,13:3,16,1,13:4,4," +
"17,1:2,18,19,20,18,21,22,23,24,25,26,27,28,16,29,30,31,32,33,34,35,36,37,38" +
",39,13,40,41,42,43,44,45,46,47,48,49,50,13,51,52,53,54,55,56")[0];

	private int yy_nxt[][] = unpackFromString(57,46,
"1,2,89:2,50,89,73,91,89,74,89,75,92,89,93,94,95,89,3,89:2,4,-1,51:3,5,55,6," +
"7,8,9,10,11,12,13,14,15,16,17,18,19,20,59,62,21,-1:47,89,76,89:15,77,89,77," +
"-1:43,3,-1:28,49:4,-1:2,49:14,23,49:2,-1,53,-1:2,49:18,-1:28,25,-1:46,26,-1" +
":47,57,-1:46,27,-1:45,28,29,-1:44,30,-1,31,-1:43,32,-1:58,21,-1,89:17,77,89" +
",77,-1:26,38:21,-1,38:3,-1,38:19,-1,39:21,-1,39:3,-1,39:19,-1,43:4,52:2,43:" +
"14,44,65,43,52:2,-1,52,43:18,-1,89:4,54,89:5,22,89:6,77,89,77,-1:26,63:4,-1" +
":2,63:14,-1,63:2,-1:2,63:20,-1,89:5,34,89:11,77,89,77,-1:52,24,-1:49,39,-1:" +
"15,89:9,35,89:7,77,89,77,-1:68,33,-1:46,39,-1:2,89:5,36,89:6,82,89:4,77,89," +
"77,-1:69,60,-1:2,43:4,52:2,43:14,44,65,43,52,56,-1,52,43:18,-1,89:11,37,89:" +
"5,77,89,77,-1:26,89:2,40,89:14,77,89,77,-1:26,89:7,41,89:9,77,89,77,-1:26,8" +
"9:13,42,89:3,77,89,77,-1:26,89:5,45,89:11,77,89,77,-1:26,89:13,46,89:3,77,8" +
"9,77,-1:26,89:5,47,89:11,77,89,77,-1:26,89:13,48,89:3,77,89,77,-1:26,89:8,5" +
"8,89:8,77,89,77,-1:26,89:13,61,89:3,77,89,77,-1:26,89:2,64,89:14,77,89,77,-" +
"1:26,89,66,89:15,77,89,77,-1:26,89:3,67,89:13,77,89,77,-1:26,89:8,83,89:8,7" +
"7,89,77,-1:26,89:11,68,89:5,77,89,77,-1:26,89:3,84,89:13,77,89,77,-1:26,89:" +
"13,86,89:3,77,89,77,-1:26,89:9,87,89:7,77,89,77,-1:26,89:4,69,89:12,77,89,7" +
"7,-1:26,89:2,70,89:14,77,89,77,-1:26,89:3,88,89:13,77,89,77,-1:26,89:12,71," +
"89:4,77,89,77,-1:26,89:6,72,89:10,77,89,77,-1:26,89:3,85,89:13,77,89,77,-1:" +
"26,89,78,89:15,77,89,77,-1:26,89:5,79,89:11,77,89,77,-1:26,89:2,80,89:14,77" +
",89,77,-1:26,89:8,81,89:8,77,89,77,-1:26,89:16,90,77,89,77,-1:25");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
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
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
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
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -3:
						break;
					case 3:
						{
            try {
                int val = (new Integer(yytext())).intValue();
                Symbol S = new Symbol(sym.INTLITERAL,
                                 new IntLitTokenVal(yyline+1, CharNum.num, val));
                CharNum.num += yytext().length();
                return S;
            } catch (NumberFormatException e) {
                ErrMsg.warn(yyline + 1,
                            CharNum.num,
                            "integer literal too large; using max value");
                IntLitTokenVal tokenVal = new IntLitTokenVal(yyline + 1,
                                                             CharNum.num,
                                                             Integer.MAX_VALUE);
                CharNum.num += yytext().length();
                return new Symbol(sym.INTLITERAL, tokenVal);
            }
          }
					case -4:
						break;
					case 4:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -5:
						break;
					case 5:
						{ CharNum.num = 1; }
					case -6:
						break;
					case 6:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.PLUS, tokenVal);
          }
					case -7:
						break;
					case 7:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.MINUS, tokenVal);
          }
					case -8:
						break;
					case 8:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.TIMES, tokenVal);
          }
					case -9:
						break;
					case 9:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.DIVIDE, tokenVal);
          }
					case -10:
						break;
					case 10:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.ASSIGN, tokenVal);
          }
					case -11:
						break;
					case 11:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.LESS, tokenVal);
          }
					case -12:
						break;
					case 12:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.GREATER, tokenVal);
          }
					case -13:
						break;
					case 13:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.NOT, tokenVal);
          }
					case -14:
						break;
					case 14:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.LCURLY, tokenVal);
          }
					case -15:
						break;
					case 15:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.RCURLY, tokenVal);
          }
					case -16:
						break;
					case 16:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.LPAREN, tokenVal);
          }
					case -17:
						break;
					case 17:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.RPAREN, tokenVal);
          }
					case -18:
						break;
					case 18:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.SEMICOLON, tokenVal);
          }
					case -19:
						break;
					case 19:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.COMMA, tokenVal);
          }
					case -20:
						break;
					case 20:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.DOT, tokenVal);
          }
					case -21:
						break;
					case 21:
						{ CharNum.num += yytext().length(); }
					case -22:
						break;
					case 22:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.IF, tokenVal);
          }
					case -23:
						break;
					case 23:
						{
            StrLitTokenVal token = new StrLitTokenVal(yyline +1,
                                                      CharNum.num,
                                                      yytext());
            CharNum.num += yytext().length();
            return new Symbol(sym.STRINGLITERAL, token);
          }
					case -24:
						break;
					case 24:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.OR, tokenVal);
          }
					case -25:
						break;
					case 25:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.PLUSPLUS, tokenVal);
          }
					case -26:
						break;
					case 26:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.MINUSMINUS, tokenVal);
          }
					case -27:
						break;
					case 27:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.EQUALS, tokenVal);
          }
					case -28:
						break;
					case 28:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.LESSEQ, tokenVal);
          }
					case -29:
						break;
					case 29:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.WRITE, tokenVal);
          }
					case -30:
						break;
					case 30:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.GREATEREQ, tokenVal);
          }
					case -31:
						break;
					case 31:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.READ, tokenVal);
          }
					case -32:
						break;
					case 32:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.NOTEQUALS, tokenVal);
          }
					case -33:
						break;
					case 33:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.AND, tokenVal);
          }
					case -34:
						break;
					case 34:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.INT, tokenVal);
          }
					case -35:
						break;
					case 35:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.TRUE, tokenVal);
          }
					case -36:
						break;
					case 36:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.RETURN, tokenVal);
          }
					case -37:
						break;
					case 37:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.FALSE, tokenVal);
          }
					case -38:
						break;
					case 38:
						{
                ErrMsg.fatal(yyline+1,
                             CharNum.num,
                             "unterminated string literal ignored");
            }
					case -39:
						break;
					case 39:
						{ CharNum.num += yytext().length(); }
					case -40:
						break;
					case 40:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.BOOL, tokenVal);
          }
					case -41:
						break;
					case 41:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.VOID, tokenVal);
          }
					case -42:
						break;
					case 42:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.ELSE, tokenVal);
          }
					case -43:
						break;
					case 43:
						{
            ErrMsg.fatal(yyline + 1,
                         CharNum.num,
                         "unterminated string literal with bad escaped character ignored");
          }
					case -44:
						break;
					case 44:
						{
                    ErrMsg.fatal(yyline + 1,
                                 CharNum.num,
                                 "string literal with bad escaped character ignored");
                    CharNum.num += yytext().length();
               }
					case -45:
						break;
					case 45:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.PRINT, tokenVal);
          }
					case -46:
						break;
					case 46:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.WHILE, tokenVal);
          }
					case -47:
						break;
					case 47:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.STRUCT, tokenVal);
          }
					case -48:
						break;
					case 48:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.RECEIVE, tokenVal);
          }
					case -49:
						break;
					case 50:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -50:
						break;
					case 51:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -51:
						break;
					case 52:
						{
            ErrMsg.fatal(yyline + 1,
                         CharNum.num,
                         "unterminated string literal with bad escaped character ignored");
          }
					case -52:
						break;
					case 54:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -53:
						break;
					case 55:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -54:
						break;
					case 56:
						{
            ErrMsg.fatal(yyline + 1,
                         CharNum.num,
                         "unterminated string literal with bad escaped character ignored");
          }
					case -55:
						break;
					case 58:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -56:
						break;
					case 59:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -57:
						break;
					case 61:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -58:
						break;
					case 62:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -59:
						break;
					case 64:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -60:
						break;
					case 66:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -61:
						break;
					case 67:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -62:
						break;
					case 68:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -63:
						break;
					case 69:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -64:
						break;
					case 70:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -65:
						break;
					case 71:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -66:
						break;
					case 72:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -67:
						break;
					case 73:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -68:
						break;
					case 74:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -69:
						break;
					case 75:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -70:
						break;
					case 76:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -71:
						break;
					case 77:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -72:
						break;
					case 78:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -73:
						break;
					case 79:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -74:
						break;
					case 80:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -75:
						break;
					case 81:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -76:
						break;
					case 82:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -77:
						break;
					case 83:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -78:
						break;
					case 84:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -79:
						break;
					case 85:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -80:
						break;
					case 86:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -81:
						break;
					case 87:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -82:
						break;
					case 88:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -83:
						break;
					case 89:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -84:
						break;
					case 90:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -85:
						break;
					case 91:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -86:
						break;
					case 92:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -87:
						break;
					case 93:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -88:
						break;
					case 94:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -89:
						break;
					case 95:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -90:
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
						yy_mark_end();
					}
				}
			}
		}
	}
}
