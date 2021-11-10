#!/usr/bin/env bash

LASTNAME1="chen"
FIRSTNAME1="reid"
LASTNAME2="hu"
FIRSTNAME2="gaoyi"


zip -r $LASTNAME1.$FIRSTNAME1.$LASTNAME2.$FIRSTNAME2.P4.zip\
 deps/\
 ast.java\
 b.cup\
 b.jlex\
 DuplicateSymException.java\
 EmptySymTableException.java\
 WrongArgumentException.java\
 ErrMsg.java\
 Makefile\
 P4.java\
 Sym.java\
 SymTable.java\
 nameErrors.b\
 test.b\
 $LASTNAME1.$FIRSTNAME1.$LASTNAME2.$FIRSTNAME2.P4.pdf
