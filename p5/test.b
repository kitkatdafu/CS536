struct atp {
	int f;
	bool g;
}
int func1(int num, bool boo){
	ret 1;
}
bool func2(struct e){
	ret fls;
}
void func3(){}
string func4(){ret 0;}
void func5(){ret 1;}
struct set{
	int h;
}


int main(){
	int a;
	int a1;

	bool b;
	bool b1;

	void c;
	void c1;

	string d;
	string d1;

	struct atp e;


	///logical oper
	a && a1;
	a && b;
	a && c;
	a && d;
	a && e;
	b && b;	

	a || a1;
	a || b;
	a || c;
	a || d;
	a || e;
	b || b;	

	! a;
	! b;
	! c;
	! d;
	! e;

	if(a){};
	if(b){};
	if(c){};
	if(d){};
	if(e){};


	while(a){};
	while(b){};
	while(c){};
	while(d){};
	while(e){};

	repeat a>1 {};
	repeat b {};
	repeat c {};
	repeat d>1 {};
	repeat e {};

	///ari and rel
	a = a + a1;
	b = b + a;
	c = c + b;
	d = d - a;
	e.f = a + e.f;
	a = a < a1;
	b = a > a1;
	c = a <= a1;
	d = d < b1;
	e.f = a >= a1;

	///eql
	a == a1;
	b == b1;
	c != b1;
	d == d1;
	e.f != e.f;
	e.f == b1;


	///ass
	a = 1;
	b = fls;
	a = fls;
	c = 1;
	d = "yes";
	e.f = "no";

	///print and rece
	print << a;
	print << b;
	print << c;
	print << d;
	print << e;
	print << e.f;

	receive << a;
	receive << b;
	receive << c;
	receive << d;
	receive << e;
	receive << e.f;
	receive << e.g;

	///func call
	func1(a,b);
	func2(e);
	func3();
	
	func1(a,c);
	func2();
	func3();

	///func returns
	c = func3();

	a = func1(a,b);
	b = func1(a,b)		
	b = func2(e);
	a = func2(e);
	
	print << func1(a,b);
	print << atp;	
	print << e;
	print << func3();
	
	receive >> fun1(a,b);
	receive >> atp;
	receive >> e;

	func3() == func5();
	func1() == func2();
	func1() = func2();
	struct set e1;
	e1 == e;
	atp = set;
	e1 = e;
	a();
	func4();	
}
