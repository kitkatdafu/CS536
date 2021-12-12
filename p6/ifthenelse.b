void main(int c, int d, bool e) {
    int a;
    int b;
    print << "Please enter the value of a: ";
    receive >> a;
    print << "\n";
    print << "Please enter the value of b: ";
    receive >> b;
    print << "\n";
    if (a >= b) {
        print << a;
        print << " >= ";
        print << b;
    } else {
        print << a;
        print << "  <  ";
        print << b;
    }
}

