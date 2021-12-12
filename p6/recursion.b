int fib(int n) {
    print << n;
    print << "\n";
    if (n == 1) {
        ret 0;
    } else {
        if (n == 2) {
            ret 1;
        } else {
            int left;
            int right;
            left = fib(n - 1);
            right = fib(n - 2);
            print << "left ";
            print << left;
            print << "\n";
            print << "right ";
            print << right;
            print << "\n";

            ret left + right;
        }
    }
}

void main() {
    int f;
    int n;
    receive >> n;
    print << "Begin...\n";
    f = fib(n);
    print << "fib(";
    print << n;
    print << ") is ";
    print << f;
    print << "\n";
}
