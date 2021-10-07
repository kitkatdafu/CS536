///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            P2
// Files:            P2.java, b.jlex
// Semester:         CS536 Fall 2021
//
// Author:           Reid Chen
// Email:            ychen878@wics.edu
// CS Login:         reid
// Lecturer's Name:  Aws Albarghouthi
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Pair Partner:     Gaoyi Hu
// Email:            ghu37@wisc.edu
// CS Login:         gaoyi
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//
// Persons:          (x) 
//
// Online sources:   https://tinyurl.com/6spnw3k - redirect stderr
//                   
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the b scanner.
 *
 * @author Reid Chen, Gaoyi Hu
 */
public class P2 {

    // these fields store the error messages
    private static final String UNTERM_BAD = "unterminated string literal with"
        + " bad escaped character ignored";
    private static final String BAD = "string literal with bad escaped"
        + " character ignored";
    private static final String UNTERM = "unterminated string literal ignored";
    private static final String BADINT = "integer literal too large; using max"
        + " value";

    public static void main(String[] args) throws IOException {
        // test all tokens
        testAllTokens("allTokens");
        // test basic cases
        basicTests();

        // the tests below also check charnum and linenum.
        // test if illegal chars can be found
        test("testIllegalChar", new String[] {
                "5:8 ***ERROR*** illegal character ignored: @",
                "5:9 ***ERROR*** illegal character ignored: @",
                "5:28 ***ERROR*** illegal character ignored: &",
                "5:29 ***ERROR*** illegal character ignored: ^"
        });

        // test if bad strings can be found
        test("testBadString", new String[] {
                "1:1 ***ERROR*** " + UNTERM_BAD,
                "3:1 ***ERROR*** " + UNTERM,
                "4:1 ***ERROR*** " + UNTERM,
                "5:1 ***ERROR*** " + BAD,
                "6:1 ***ERROR*** " + UNTERM_BAD,
                "12:1 ***ERROR*** " + BAD,
                "12:34 ***ERROR*** " + BAD,
                "13:1 ***ERROR*** " + BAD,
                "13:46 ***ERROR*** " + UNTERM_BAD,
                "14:1 ***ERROR*** " + BAD,
                "14:44 ***ERROR*** " + UNTERM,
                "16:21 ***ERROR*** " + UNTERM_BAD,
                "17:1 ***ERROR*** " + UNTERM_BAD
        });

        // test if bad strings can be found
        test("testBadString2", new String[] {
                "1:1 ***ERROR*** " + UNTERM,
                "2:1 ***ERROR*** " + UNTERM_BAD,
                "4:1 ***ERROR*** " + UNTERM_BAD,
                "5:1 ***ERROR*** " + UNTERM,
                "6:1 ***ERROR*** " + UNTERM,
                "7:1 ***ERROR*** " + UNTERM_BAD,
                "8:1 ***ERROR*** " + BAD,
                "10:1 ***ERROR*** " + UNTERM
        });

        // test eof case
        // test if unterm string end with eof will be captured
        test("eof", new String[] {
                "1:1 ***ERROR*** " + UNTERM,
        });

        // test eof case
        // test if bad string end with eof will be captured
        test("eofBad", new String[] {
                "1:1 ***ERROR*** " + UNTERM_BAD,
        });

        // a general test
        // test integer, illegal char and string, 
        // and value of correct id and integer
        test("generalTest", new String[] {
                "6:6 ***ERROR*** " + UNTERM,
                "7:1 ***ERROR*** " + "illegal character ignored: [",
                "7:2 ***ERROR*** " + "illegal character ignored: [",
                "7:3 ***ERROR*** " + "illegal character ignored: [",
                "7:4 ***ERROR*** " + "illegal character ignored: [",
                "7:5 ***ERROR*** " + "illegal character ignored: [",
                "7:6 ***ERROR*** " + "illegal character ignored: [",
                "7:24 ***ERROR*** " + BAD,
                "9:1 ***WARNING*** " + BADINT
        });
    }

    /**
     * Tests that do not prints error messages.
     *
     * @throws IOException may be thrown by yylex
     */
    private static void basicTests() throws IOException {
        String[] files = {
            "validIdentifier",
            "validReservedWords",
            "validIntegerLiteral",
            "validStringLiteral",
            "validSymbols",
            "validSymbols2",
            "testComments"
        };
        for (String file: files) {
            testAllTokens(file);
        }
    }

    /**
     * Generate corresponding outputs and compare the error messages to the 
     * expected error messages.
     *
     * The actual output generated in by testAllTokens() are compared with the 
     * expected output in the Makefile using diff.
     * 
     * @param fileName fileName of the corresponding input and output
     * @param expectedErr expected error messages
     * @throws IOException may be thrown by yylex
     */
    private static void test(String fileName, String[] expectedErr) 
            throws IOException {
        // create a custom stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        // store the original stderr
        PrintStream old = System.err;

        // set stderr to the custom stream
        System.setErr(ps);

        // run the scanner
        testAllTokens(fileName);

        // flush buffered data
        System.err.flush();
        // restore stderr
        System.setErr(old);

        // split actual error message
        String[] err = baos.toString().split("\n");

        // if the number of error messages is not equal to the expected number
        if (err.length != expectedErr.length) {
            System.err.println("Wrong error mesage in " + fileName);
            System.err.println("\t size not same");
            System.err.println(baos.toString());
            return;
        }
        // check if each error message if equal to the expected error message
        for (int i = 0; i < expectedErr.length; i++) {
            if (err[i].equals(expectedErr[i])) {
                continue;
            }

            System.err.println("Wrong error mesage in " + fileName);
            System.err.println("\t Expected: " + expectedErr[i]);
            System.err.println("\t Actual  : " + err[i]);
            System.err.println(baos.toString());
            return;
        }
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens(String fileName) throws IOException {
        // reset CharNum
        CharNum.num = 1;

        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("./inputs/" + fileName + ".in");
            outFile = new PrintWriter(new FileWriter("./outputs/" 
                        + fileName + ".out"));
        } catch (FileNotFoundException ex) {
            System.err.printf("File %s.in not found.\n", fileName);
            System.exit(-1);
        } catch (IOException ex) {
            System.err.printf("%s.out cannot be opened.\n", fileName);
            System.exit(-1);
        }

        // create and call the scanner
        Yylex my_scanner = new Yylex(inFile);
        Symbol my_token = null;
        try {
            my_token = my_scanner.next_token();
        } catch(ArrayIndexOutOfBoundsException e)  {
            outFile.close();
            return;
        }

        while (my_token.sym != sym.EOF) {
            switch (my_token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("tru");
                    break;
                case sym.FALSE:
                    outFile.println("fls");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.RECEIVE:
                    outFile.println("receive");
                    break;
                case sym.PRINT:
                    outFile.println("print");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("ret");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)my_token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)my_token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)my_token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch


            try {
                my_token = my_scanner.next_token();
            } catch(ArrayIndexOutOfBoundsException e)  {
                break;
            }

        } // end while
        outFile.close();
    }
}
