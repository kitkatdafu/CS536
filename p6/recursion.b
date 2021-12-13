int fib(int n) {
    if (n == 1) {
        ret 0;
    } else {
        if (n == 2) {
            ret 1;
        } else {
            int r;
            r = fib(n - 1) + fib(n - 2);
            ret r;
        }
    }
}

void main() {
    int f;
    int n;
    print << "Please enter n: ";
    receive >> n;
    print << "\n";
    f = fib(n);

    print << "fib(";
    print << n;
    print << ") is ";
    print << f;
    print << "\n";
}
