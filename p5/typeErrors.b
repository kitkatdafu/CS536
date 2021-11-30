struct atp {
    int f;
    bool g;
}
int func1(int num, bool boo){
    ret 1;
}
bool func2(int e){
    ret fls;
}
void func3(){}
bool func4(){ret 0;}
void func5(){}
struct set{
    int h;
}


int main(){
    int a;
    int a1;

    bool b;
    bool b1;

    bool d;
    bool d1;

    struct atp e;
    struct set e1;

    ///logical oper
    d = a && a1;
    d = a && b;
    d = a && d;
    d = a && e;
    d = b && b;	

    d = a || a1;
    d = a || b;
    d = a || d;
    d = a || e;
    d = b || b;	

    d = ! a;
    d = ! b;
    d = ! d;
    d = ! e;

    if(a){}
    if(b){}
    if(d){}
    if(e){}


    while(a){}
    while(b){}
    while(d){}
    while(e){}

    repeat a {}
    repeat b {}
    repeat d {}
    repeat e {}

    ///ari and rel
    a = a + a1;
    b = b + a;
    d = d - a;
    e.f = a + e.f;
    a = a < a1;
    b = a > a1;
    b = d < b1;
    e.f = a >= a1;

    ///eql
    d = a == a1;
    d = b == b1;
    d = d == d1;
    d = e.f != e.f;
    d = e.f == b1;


    ///ass
    a = 1;
    b = fls;
    a = fls;
    d = "yes";
    e.f = "no";

    ///print and rece
    print << a;
    print << b;
    print << d;
    print << e;
    print << e.f;
    print << "Hello World";

    receive >> a;
    receive >> b;
    receive >> d;
    receive >> e;
    receive >> e.f;
    receive >> e.g;

    ///func call
    func1(a,b);
    func2(a);
    func3();

    func1(b,a);
    func2();
    func3();

    ///func returns

    a = func1(a,b);
    b = func1(a,b);
    b = func2(a);
    a = func2(a);

    print << func1(a,b);
    print << atp;	
    print << e;
    print << func3();

    receive >> func1;
    receive >> atp;
    receive >> e;

    d1 = func3() == func5();
    d = func1 == func3;
    func1 = func2();
    func1 = func2;

    d1 = e1 == e;
    atp = set;
    e1 = e;
    a();
    func4();	
}
