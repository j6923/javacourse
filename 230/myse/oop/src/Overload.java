
class Calculator{
	void plus(int a, int b) {
		System.out.println(a+b);
	}
	void plus(double a, double b) {
		System.out.println(a+b);
	}
	void plus(int a, int b, int c) {
		System.out.println(a+b+c);  //비슷한 기능 
	}
	void plus(int a, double b) {
		System.out.println(a+b);
	}
	void plus(double a, int b) {
		System.out.println(a+b);
		//자바는 메서드에서만 오버로드가 있음. 
		//연산자 사이에서는 오버로드 불가 
		//매개변수 자료형이 중요하지 매개변수 이름은 아무 관련이 없음 
	}
}
public class Overload {

	public static void main(String[] args) {
		//main메서드 이름이 public되어야 하고 그 메서드 가지고 있는 것이 .java파일이 되어야 함. 
		Calculator c = new Calculator();
		c.plus(1,2);  //앞에 this 가 생략된 형태이다. 
		c.plus(3.5, 5.6);
		c.plus(1, 2, 3);
		 
	}

}
