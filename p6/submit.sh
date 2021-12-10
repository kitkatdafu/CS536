#!/bin/bash

lastname1=chen
firstname1=reid
lastname2=hu
firstname2=gaoyi

zip -r $lastname1.$firstname1.$lastname2.$firstname2.P6.zip \
  deps/ \
  ast.java \
  b.cup \
  b.jlex \
  Codegen.java \
  DuplicateSymException.java \
  EmptySymTableException.java \
  WrongArgumentException.java \
  ErrMsg.java \
  Makefile \
  P6.java \
  Symb.java \
  SymTable.java \
  Type.java \
  $lastname1.$firstname1.$lastname2.$firstname2.P6.pdf \
