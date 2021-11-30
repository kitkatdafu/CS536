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

	bool d;
	bool d1;

	struct atp e;
	struct set e1;

	///logical oper
	d = a && a1;
	d = a && b;
	d = a && c;
	d = a && d;
	d = a && e;
	d = b && b;	

	d = a || a1;
	d = a || b;
	d = a || c;
	d = a || d;
	d = a || e;
	d = b || b;	

	d = ! a;
	d = ! b;
	d = ! c;
	d = ! d;
	d = ! e;

	if(a){}
	if(b){}
	if(c){}
	if(d){}
	if(e){}


	while(a){}
	while(b){}
	while(c){}
	while(d){}
	while(e){}

	repeat a {}
	repeat b {}
	repeat c {}
	repeat d {}
	repeat e {}

	///ari and rel
	a = a + a1;
	b = b + a;
	c = c + b;
	d = d - a;
	e.f = a + e.f;
	a = a < a1;
	b = a > a1;
	b = a <= c;
	b = d < b1;
	e.f = a >= a1;

	///eql
	d = a == a1;
	d = b == b1;
	d = c != b1;
	d = d == d1;
	d = e.f != e.f;
	d = e.f == b1;


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
	print << "Hello World";

	receive >> a;
	receive >> b;
	receive >> c;
	receive >> d;
	receive >> e;
	receive >> e.f;
	receive >> e.g;

	///func call
	func1(a,b);
	func2(a);
	func3();
	
	func1(a,c);
	func2();
	func3();

	///func returns
	c = func3();

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
	d2 = func1() == func2();
	func1 = func2();
	func1 = func2;

	d1 = e1 == e;
	atp = set;
	e1 = e;
	a();
	func4();	
}
