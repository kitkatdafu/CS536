/// varDecl
int haha;
bool nohaha;
struct lambda calculus;

/// structDecl
struct node {
  int val; /// varDecl, type id
  struct node next; /// varDecl struct id id;
}

/// fnDecl
void testEmpty() {}
void testReturnEmpty() { ret; }
void testReturnSome() { ret Some; }
void testEmptyCall() { result = hello_world(); }
void testNonEmptyCall() { result = hello_world("hihihi"); }
void testDeclOnly(void a) { bool x; void x; int x; void d; struct haha a;}
void testStmtOnly(void a) { a = 10; b = 20; c = 30; a = 1 + 2 / 3; }
void testDeclStmt(void a) { bool x; void y; x = 1 && 1; y = 1 - 1; }

void testFuncWithArgs(int x, int y) { int sum; sum = x + y; }
void testFuncWithNoArgs() {int sum; sum = sum + 10; ret sum;}

/// structDecl (again)
struct testStruct { int num1;}
struct testDoubleStruct { int val; bool next; struct testStruct a; }

/// stmt
int stmt(int a, int b) {
    a = 10;
    ++b;
    --c;
    receive >> d;
    print << "world";
    if (a == 0) {
        int b;
        ++b;
    } else {
        int a;
        ++a;
    }
    if (c == 0) {
       ++c;
    }
    while tru {
        ++a;
    }
    repeat fls {
        --c;
    }
}

int exp(){ void x; x = 2132; x = wergnojsa; x = True; x = False; x = tru; y = fls; x = -1; x = 2 + (x + 1); x = 2*(x+1); x = 1*-1; x = -1; ret x;}
void testinvokeAndDot() { func1(2,3); hash.next = 3;  }
void testDotDotDot() { head.next.next.next = tail; }
bool testterm() {int x; int l; l = 1; x = 4+7; x = x+l;x = x && l; x = (1 == 1); x = (1 != 0); x = (4 < 2); x = (4 > 7); x = (5 <= 3); x = (3 >= 1); l = 3; ++l; --l; read("file.txt"); receive >> test; print<<"nice"; if x == 1{ ++x; }else{--x;} while x==1 { ++x; } repeat x>1 { ++x; } e =0; func1(e); ret fls; }
int testret(){ret 1; ret;}
void complicated(){ while(x == 1){ int b; b = -1; ++b; if b == 0 {x = 2;}} }
void others(){if(b == 0) {x = 4 + 3 *5 -y; while (c) {y=y*2 + x;}} else {x = 0;} x = -"dasd"; x=- fls; x=- (x+4); ret foo();  }
void testPrecedence1() { x = 1 + 2 * 3 + 4; y = !a || !b && !c; }
void testPrecedence2() { ret !a && -10 / 2 + -100 * 100 - 4 >= 5; }
