int const(int v) {
    ret v;
}

void main() {
    int r;
    r = const(1 + 1) + const(2 * 2);
    print << r;
}
