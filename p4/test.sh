#!/bin/bash
make
java -cp ./deps:. P4 nameErrors.b nameErrors.out 2> nameErrors.err
if diff nameErrors.err nameErrors.exp; then
  echo "======================"
  echo "PASSED!"
  echo "======================"
else
  echo "======================"
  echo "FAILED!"
  echo "======================"
fi
make clean
make cleantest
