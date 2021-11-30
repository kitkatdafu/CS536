#!/bin/bash

lastname1=chen
firstname1=reid
lastname2=hu
firstname2=gaoyi

zip -r ~/Downloads/$lastname1.$firstname1.$lastname2.$firstname2.P5.zip\
 deps/\
 ast.java\
 b.cup\
 b.jlex\
 DuplicateSymException.java\
 EmptySymTableException.java\
 WrongArgumentException.java\
 ErrMsg.java\
 Makefile\
 P5.java\
 Symb.java\
 SymTable.java\
 Type.java\
 typeErrors.b\
 test.b\
 $lastname1.$firstname1.$lastname2.$firstname2.P5.pdf\
