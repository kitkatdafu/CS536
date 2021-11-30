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
    d = b && b;	

    d = b || b;	

    d = ! b;
    d = ! d;

    if(b){}
    if(d){}


    while(b){}
    while(d){}

    repeat a {}

    ///ari and rel
    a = a + a1;
    e.f = a + e.f;
    b = a > a1;

    ///eql
    d = a == a1;
    d = b == b1;
    d = d == d1;
    d = e.f != e.f;


    ///ass
    a = 1;
    b = fls;

    ///print and rece
    print << a;
    print << b;
    print << d;
    print << e.f;
    print << "Hello World";

    receive >> a;
    receive >> b;
    receive >> d;
    receive >> e.f;
    receive >> e.g;

    ///func call
    func1(a,b);
    func2(a);
    func3();


    ///func returns

    a = func1(a,b);
    b = func2(a);

}
