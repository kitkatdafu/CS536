bool true() {
    print << "RETURNING TRUE\n";
    ret tru;
}

bool false() {
    print << "RETURNING FALSE\n";
    ret fls;
}

void main() {

    print << "=================\n";

    if (true() && true()) {
    } else {
        print << "Bad\n";
    }

    print << "=================\n";

    if (false() && false()) {
        print << "Bad\n";
    } else {
    }

    print << "=================\n";

    if (false() && true()) {
        print << "Bad\n";
    } else {
    }

    print << "=================\n";

    if (true() && false()) {
        print << "Bad\n";
    } else {
    }

    print << "=================\n";
    
    if (true() || false()) {
    } else {
        print << "Bad\n";
    }

    print << "=================\n";

    if (true() || true()) {
    } else {
        print << "Bad\n";
    }

    print << "=================\n";

    if (false() || true()) {
    } else {
        print << "Bad\n";
    }

    print << "=================\n";

    if (false() || false()) {
        print << "Bad\n";
    } else {
    }
}
