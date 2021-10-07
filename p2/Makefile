###
# This Makefile can be used to make a scanner for the b language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
CP = ./deps:.
FLAGS = -g -cp $(CP)
IN = ./inputs
OUT = ./outputs
EX = ./expects


P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: b.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) b.jlex.java

b.jlex.java: b.jlex sym.class
	java -cp $(CP) JLex.Main b.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

	
###
# testing - add more here to run your tester and compare its results
# to expected results
###
test:
	java -cp $(CP) P2
	diff $(EX)/allTokens.ex $(OUT)/allTokens.out
	diff $(EX)/validIdentifier.ex $(OUT)/validIdentifier.out
	diff $(EX)/validIntegerLiteral.ex $(OUT)/validIntegerLiteral.out
	diff $(EX)/validStringLiteral.ex $(OUT)/validStringLiteral.out
	diff $(EX)/validSymbols.ex $(OUT)/validSymbols.out
	diff $(EX)/validSymbols2.ex $(OUT)/validSymbols2.out
	diff $(EX)/validReservedWords.ex $(OUT)/validReservedWords.out
	diff $(EX)/testComments.ex $(OUT)/testComments.out
	diff $(EX)/testIllegalChar.ex $(OUT)/testIllegalChar.out
	diff $(EX)/testBadString.ex $(OUT)/testBadString.out
	diff $(EX)/testBadString2.ex $(OUT)/testBadString2.out
	diff $(EX)/eof.ex $(OUT)/eof.out
	diff $(EX)/eofBad.ex $(OUT)/eofBad.out
	diff $(EX)/eofOk.ex $(OUT)/eofOk.out
	diff $(EX)/eofBackslash.ex $(OUT)/eofBackslash.out
	diff $(EX)/eofBadBackslash.ex $(OUT)/eofBadBackslash.out
	diff $(EX)/eofBadTerm.ex $(OUT)/eofBadTerm.out
	diff $(EX)/generalTest.ex $(OUT)/generalTest.out


###
# clean up
###

clean:
	rm -f *~ *.class b.jlex.java

cleantest:
	rm -f $(OUT)/*.out
