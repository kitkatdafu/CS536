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
FLAGS = -g -cp $(CP)
CP = ./deps:.
IN = ./inputs
OUT = ./outputs


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
	diff $(IN)/allTokens.in $(OUT)/allTokens.out
	diff $(IN)/validIdentifier.in $(OUT)/validIdentifier.out
	diff $(IN)/validIntegerLiteral.in $(OUT)/validIntegerLiteral.out
	diff $(IN)/validStringLiteral.in $(OUT)/validStringLiteral.out
	diff $(IN)/validSymbols.in $(OUT)/validSymbols.out

###
# clean up
###

clean:
	rm -f *~ *.class b.jlex.java

cleantest:
	rm -f $(OUT)/*.out
