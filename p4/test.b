bool GLOBAL_LOCK;
int COUNTER;

struct Point {
    int x;
    int y;
}

struct Circ {
    int r;
    struct Point center;
}

struct Rect {
    struct Point upperLeft;
    struct Point downRight;
}

int main(int argc, int argv) {
    int numberOfPoints;
    int numberOfCircs;
    int _b;
    bool shouldExit;
    struct Point point;
    struct Rect rect;
    struct Circ circ;

    numberOfCircs = 10;
    numberOfPoints = 0;
    shouldExit = fls;

    while (!shouldExit) {
        int _a;
        if (numberOfCircs > numberOfPoints) {
            int _a;
            --numberOfCircs;
            ++numberOfPoints;
        } else {
            int _a;
            shouldExit = tru;
        }
        COUNTER = COUNTER + 10;
    }

    point.x = 0;
    point.y = 10;
    circ.r = 10;
    circ.center = point;

    point.x = point.x * 2;
    point.y = point.y / 2;
    rect.upperLeft = point;
    rect.downRight = point;
    rect.upperLeft.x = 100;
    rect.downRight.y = 1000;
    ++rect.downRight.y;
    --rect.upperLeft.x;

    repeat (point.x > point.y) {
        int _b;
        GLOBAL_LOCK = tru;
    }
}

int arithmetic(int x, int y, bool verbose) {
    int result;

    ++x;
    --y;
    result = x + y;
    result = x - y;
    result = x * y;
    result = x / y;
    result = -x;
    verbose = !verbose;
    verbose = verbose && !verbose;
    verbose = verbose || !verbose;
    COUNTER = 10;

    x = 10;
    y = 20;
    if (x == y) {
        int x;
        int y;
        bool verbose;
        int c;

        verbose = (x + y * c) == 10 || verbose;
    } else {
        int x;
        int y;
        int d;
        bool verbose;
    }

    x = 20;
    y = 20;
    if (x != y) {

    }
    
    if (x > y && x < y) {

    } else {

    }

    if (x >= y && x <= y) {

    } else {

    }

    while (verbose) {
        int a;
        int b;
        int c;
        
        a = 10;
        b = 20;
        c = 30;
        repeat (!verbose) {
            int a;
            int b;
            int d;

            d = a + b + c;
            receive >> d;
            print << "counter = " + COUNTER;
        }
    }

    ret;
    ret result + 1;
}

int caller() {
    int result;
    int x;
    int y;
    int verbose;

    arithmetic(10, 20, tru);
    result = arithmetic(10, 20, tru);

    x = 10;
    y = 40;
    verbose = tru;
    arithmetic(x, y, verbose);

    x = 30;
    y = 20;
    verbose = fls;
    arithmetic(x, y, verbose);
}
