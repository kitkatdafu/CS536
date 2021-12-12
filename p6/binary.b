int main(){
    int a;
    int b;

    bool c;
    bool d;

    a = 10;
    b = 20;

    c = tru;
    d = fls;

    print << a + b;
    print << "\n";
    print << a - b;
    print << "\n";
    print << a * b - b;
    print << "\n";
    print << a / b;
    print << "\n";

    print << (a + b) > (a - b);
    print << "\n";
    print << (a + b) >= (a - b);
    print << "\n";
    print << (a + b) < (a - b);
    print << "\n";
    print << (a + b) <= (a - b);
    print << "\n";
    print << (a + b) == (a - b);
    print << "\n";

    print << c || d;
    print << "\n";

    print << c && d;
    print << "\n";

    print << (!c) && (!d);
    print << "\n";
}
