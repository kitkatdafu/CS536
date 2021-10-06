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
class MatchedStr {
    static String str = "";
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
	private final int OKSTR = 1;
	private final int YYINITIAL = 0;
	private final int BACKSLASH = 2;
	private final int BAD_ESCAPED = 3;
	private final int BAD_BACKSLASH = 4;
	private final int yy_state_dtrans[] = {
		0,
		97,
		98,
		99,
		100
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
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_END,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_END,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_END,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_END,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"49:9,44,43,49:2,50,49:18,44,30,48,41,49:2,39,52,33,34,28,26,36,27,37,29,45:" +
"10,49,35,7,38,14,52,49,12,46:7,9,46:2,13,46,10,46:5,11,46:4,8,46,49,42,49:2" +
",47,49,46,1,21,16,22,19,46,25,4,46:2,3,46,5,2,23,46,17,20,6,18,15,24,46:3,3" +
"1,40,32,49:2,0,51")[0];

	private int yy_rmap[] = unpackFromString(1,122,
"0,1,2,3,4,5,6,1,7,8,1:7,9,10,1,11,12,1,13,1:10,13:4,14,13:6,1,15,1:14,16,17" +
",18,19,20,21,22,21,23,24,25,26,27,28,1,29,30,31,32,33,34,35,36,37,38,39,40," +
"41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,13,58,59,60,61,62,63,64," +
"65,66,67,13,68,69,70,71,72")[0];

	private int yy_nxt[][] = unpackFromString(73,53,
"1,2,116:2,62,116,101,3,116:6,4,116:2,102,116,103,118,116,119,120,121,116,5," +
"6,7,8,9,10,11,12,13,14,15,16,17,18,63,71,75,19,20,21,116:2,22,75,-1,1,75,-1" +
":54,116,104,116:4,-1,116:6,-1,116:11,-1:19,105,116,105,-1:12,24,61,-1:29,25" +
",-1:28,26,-1:23,27,-1:40,28,-1:53,29,-1:54,69,-1:61,30,-1:52,31,-1:53,32,-1" +
":57,20,-1:53,21,-1:8,116:6,-1,116:6,-1,116:11,-1:19,105,116,105,-1:6,38:42," +
"-1,38:6,-1:2,38,-1:43,50,-1:6,65,50,-1:9,77,-1:45,116:4,70,116,-1,116:6,-1," +
"116:4,23,116:6,-1:19,105,116,105,-1:45,33,-1:20,61,-1:34,50,-1:6,65,50,-1:4" +
"4,50,-1:17,61,-1:87,57,-1:38,38,-1:24,116:5,34,-1,116:6,-1,116:11,-1:19,105" +
",116,105,-1:46,73,-1:54,57,-1:6,67,57,-1:42,38,-1:12,116:6,-1,116:6,-1,116:" +
"3,35,116:7,-1:19,105,116,105,-1:13,61,-1:34,57,-1:6,67,57,-1:10,79,-1:44,11" +
"6:5,36,-1,116:6,-1,116:6,109,116:4,-1:19,105,116,105,-1:15,81,-1:43,116:6,-" +
"1,116:6,-1,116:5,37,116:5,-1:19,105,116,105,-1:14,83,-1:44,116:2,39,116:3,-" +
"1,116:6,-1,116:11,-1:19,105,116,105,-1:16,85,-1:42,116:6,-1,116:6,-1,116:7," +
"40,116:3,-1:19,105,116,105,-1:14,87,-1:44,116:5,41,-1,116:6,-1,116:11,-1:19" +
",105,116,105,-1:17,89,-1:41,116:6,-1,116:6,-1,116:7,42,116:3,-1:19,105,116," +
"105,-1:18,91,-1:40,116:5,43,-1,116:6,-1,116:11,-1:19,105,116,105,-1:19,93,-" +
"1:39,116:6,-1,116:6,-1,116:7,44,116:3,-1:19,105,116,105,-1:20,94,-1:39,95,-" +
"1:54,96,-1:64,45,-1:36,1,46:6,64,46:34,47,48,46:4,49,46:2,1,46,1,51:4,52:2," +
"66,51:34,52,53,51:4,52,51:2,1,52,1,72:6,76,72:34,54,55,72:4,56,72:2,1,72,1," +
"58:4,59:2,68,58:34,59,60,58:4,59,58:2,1,59,-1,116:6,-1,116:6,-1,116:2,74,11" +
"6:8,-1:19,105,116,105,-1:6,116:6,-1,116:6,-1,116:7,78,116:3,-1:19,105,116,1" +
"05,-1:6,116:2,80,116:3,-1,116:6,-1,116:11,-1:19,105,116,105,-1:6,116,82,116" +
":4,-1,116:6,-1,116:11,-1:19,105,116,105,-1:6,116:6,-1,116:6,-1,116:2,110,11" +
"6:8,-1:19,105,116,105,-1:6,116:6,-1,116:6,-1,116:5,84,116:5,-1:19,105,116,1" +
"05,-1:6,116:3,111,116:2,-1,116:6,-1,116:11,-1:19,105,116,105,-1:6,116:6,-1," +
"116:6,-1,116:7,113,116:3,-1:19,105,116,105,-1:6,116:6,-1,116:6,-1,116:3,114" +
",116:7,-1:19,105,116,105,-1:6,116:4,86,116,-1,116:6,-1,116:11,-1:19,105,116" +
",105,-1:6,116:2,88,116:3,-1,116:6,-1,116:11,-1:19,105,116,105,-1:6,116:3,11" +
"5,116:2,-1,116:6,-1,116:11,-1:19,105,116,105,-1:6,116:6,-1,116:6,-1,116:6,9" +
"0,116:4,-1:19,105,116,105,-1:6,116:6,-1,116:6,-1,92,116:10,-1:19,105,116,10" +
"5,-1:6,116:3,112,116:2,-1,116:6,-1,116:11,-1:19,105,116,105,-1:6,116:5,106," +
"-1,116:6,-1,116:11,-1:19,105,116,105,-1:6,116:2,107,116:3,-1,116:6,-1,116:1" +
"1,-1:19,105,116,105,-1:6,116:6,-1,116:6,-1,116:2,108,116:8,-1:19,105,116,10" +
"5,-1:6,116:6,-1,116:6,-1,116:10,117,-1:19,105,116,105,-1:5");

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
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.LESS, tokenVal);
          }
					case -4:
						break;
					case 4:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.GREATER, tokenVal);
          }
					case -5:
						break;
					case 5:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.PLUS, tokenVal);
          }
					case -6:
						break;
					case 6:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.MINUS, tokenVal);
          }
					case -7:
						break;
					case 7:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.TIMES, tokenVal);
          }
					case -8:
						break;
					case 8:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.DIVIDE, tokenVal);
          }
					case -9:
						break;
					case 9:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.NOT, tokenVal);
          }
					case -10:
						break;
					case 10:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.LCURLY, tokenVal);
          }
					case -11:
						break;
					case 11:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.RCURLY, tokenVal);
          }
					case -12:
						break;
					case 12:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.LPAREN, tokenVal);
          }
					case -13:
						break;
					case 13:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.RPAREN, tokenVal);
          }
					case -14:
						break;
					case 14:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.SEMICOLON, tokenVal);
          }
					case -15:
						break;
					case 15:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.COMMA, tokenVal);
          }
					case -16:
						break;
					case 16:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.DOT, tokenVal);
          }
					case -17:
						break;
					case 17:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num++);
            return new Symbol(sym.ASSIGN, tokenVal);
          }
					case -18:
						break;
					case 18:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -19:
						break;
					case 19:
						{ CharNum.num = 1; }
					case -20:
						break;
					case 20:
						{ CharNum.num += yytext().length(); }
					case -21:
						break;
					case 21:
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
					case -22:
						break;
					case 22:
						{
                        yybegin(OKSTR);
                        MatchedStr.str += yytext();
                        CharNum.num += 1;
                     }
					case -23:
						break;
					case 23:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.IF, tokenVal);
          }
					case -24:
						break;
					case 24:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.WRITE, tokenVal);
          }
					case -25:
						break;
					case 25:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.LESSEQ, tokenVal);
          }
					case -26:
						break;
					case 26:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.READ, tokenVal);
          }
					case -27:
						break;
					case 27:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.GREATEREQ, tokenVal);
          }
					case -28:
						break;
					case 28:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.PLUSPLUS, tokenVal);
          }
					case -29:
						break;
					case 29:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.MINUSMINUS, tokenVal);
          }
					case -30:
						break;
					case 30:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.NOTEQUALS, tokenVal);
          }
					case -31:
						break;
					case 31:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.EQUALS, tokenVal);
          }
					case -32:
						break;
					case 32:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.AND, tokenVal);
          }
					case -33:
						break;
					case 33:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += 2;
            return new Symbol(sym.OR, tokenVal);
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
                CharNum.num += yytext().length();
          }
					case -39:
						break;
					case 39:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.BOOL, tokenVal);
          }
					case -40:
						break;
					case 40:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.ELSE, tokenVal);
          }
					case -41:
						break;
					case 41:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.PRINT, tokenVal);
          }
					case -42:
						break;
					case 42:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.WHILE, tokenVal);
          }
					case -43:
						break;
					case 43:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.STRUCT, tokenVal);
          }
					case -44:
						break;
					case 44:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.RECEIVE, tokenVal);
          }
					case -45:
						break;
					case 45:
						{
            TokenVal tokenVal = new TokenVal(yyline + 1, CharNum.num);
            CharNum.num += yytext().length();
            return new Symbol(sym.VOID, tokenVal);
          }
					case -46:
						break;
					case 46:
						{
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -47:
						break;
					case 47:
						{
                                    yybegin(BACKSLASH);
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -48:
						break;
					case 48:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "unterminated string literal ignored");
                                    MatchedStr.str = "";
                                    CharNum.num = 1;
                              }
					case -49:
						break;
					case 49:
						{
                                    yybegin(YYINITIAL);
                                    MatchedStr.str += yytext();
                                    StrLitTokenVal token = new StrLitTokenVal(yyline +1,
                                                                       CharNum.num++,
                                                                       MatchedStr.str);
                                    MatchedStr.str = "";
                                    return new Symbol(sym.STRINGLITERAL, token);
                              }
					case -50:
						break;
					case 50:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "unterminated string literal ignored");
                                    MatchedStr.str = "";
                                    CharNum.num = 1;
                              }
					case -51:
						break;
					case 51:
						{
                                    yybegin(BAD_ESCAPED);
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -52:
						break;
					case 52:
						{
                                    yybegin(OKSTR);
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -53:
						break;
					case 53:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "unterminated string literal ignored");
                                    MatchedStr.str = "";
                                    CharNum.num = 1;
                              }
					case -54:
						break;
					case 54:
						{
                                    yybegin(BAD_BACKSLASH);
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -55:
						break;
					case 55:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "unterminated string literal with bad escaped character ignored");
                                    MatchedStr.str = "";
                                    CharNum.num = 1;
                              }
					case -56:
						break;
					case 56:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "string literal with bad escaped character ignored");
                                    MatchedStr.str = "";
                                    CharNum.num += 1;
                              }
					case -57:
						break;
					case 57:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "unterminated string literal with bad escaped character ignored");
                                    MatchedStr.str = "";
                                    CharNum.num = 1;
                              }
					case -58:
						break;
					case 58:
						{
                                    yybegin(BAD_ESCAPED);
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -59:
						break;
					case 59:
						{
                                    yybegin(BAD_ESCAPED);
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -60:
						break;
					case 60:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "unterminated string literal with bad escaped character ignored");
                                    MatchedStr.str = "";
                                    CharNum.num = 1;
                              }
					case -61:
						break;
					case 62:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -62:
						break;
					case 63:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -63:
						break;
					case 64:
						{
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -64:
						break;
					case 65:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "unterminated string literal ignored");
                                    MatchedStr.str = "";
                                    CharNum.num = 1;
                              }
					case -65:
						break;
					case 66:
						{
                                    yybegin(BAD_ESCAPED);
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -66:
						break;
					case 67:
						{
                                    yybegin(YYINITIAL);
                                    ErrMsg.fatal(yyline + 1,
                                                 CharNum.num - MatchedStr.str.length(),
                                                 "unterminated string literal with bad escaped character ignored");
                                    MatchedStr.str = "";
                                    CharNum.num = 1;
                              }
					case -67:
						break;
					case 68:
						{
                                    yybegin(BAD_ESCAPED);
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -68:
						break;
					case 70:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -69:
						break;
					case 71:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -70:
						break;
					case 72:
						{
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -71:
						break;
					case 74:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -72:
						break;
					case 75:
						{
            ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -73:
						break;
					case 76:
						{
                                    MatchedStr.str += yytext();
                                    CharNum.num += 1;
                              }
					case -74:
						break;
					case 78:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -75:
						break;
					case 80:
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
					case 84:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -78:
						break;
					case 86:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -79:
						break;
					case 88:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -80:
						break;
					case 90:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -81:
						break;
					case 92:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -82:
						break;
					case 101:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -83:
						break;
					case 102:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -84:
						break;
					case 103:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -85:
						break;
					case 104:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -86:
						break;
					case 105:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -87:
						break;
					case 106:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -88:
						break;
					case 107:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -89:
						break;
					case 108:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -90:
						break;
					case 109:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -91:
						break;
					case 110:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -92:
						break;
					case 111:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -93:
						break;
					case 112:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -94:
						break;
					case 113:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -95:
						break;
					case 114:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -96:
						break;
					case 115:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -97:
						break;
					case 116:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -98:
						break;
					case 117:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -99:
						break;
					case 118:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -100:
						break;
					case 119:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -101:
						break;
					case 120:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -102:
						break;
					case 121:
						{
                IdTokenVal tokenVal = new IdTokenVal(yyline + 1,
                                                     CharNum.num,
                                                     yytext());
                CharNum.num += yytext().length();
                return new Symbol(sym.ID, tokenVal);
             }
					case -103:
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
