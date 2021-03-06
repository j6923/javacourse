class A{
	//하나의 main 메소드를 가지고 있는 애는 main 클래스가 되어야 함. 
	
	int iv; 
	//컴파일시에 생성자가 없으면 매개변수 없는 생성자를 자동 포함시킨다. 
	//다음과 같은 생성자코드를 포함시킨다. 
	//A(){} 
	//이런 형태의 생성자를 컴파일 타임에 자동 포함시킨다. 
	//defalut 생성자라고 부름.
	//자동 포함되는 생성자 : default constructor (기본 생성자)  
	A(){} // 이거이 기본 생성자가 아닌 명시적으로 생성자를 만드는 것일 뿐임. 
	//생성자 직접 만들어봄
	//생성자 일므은 클래스 이름과 동일하다 
	//리턴타입 없어야 함. -> 생성자는 
	// 객체 생성시에 자동으로 호출됨 
	// 
	
	A(int iv){
		//생성자명시함. 이럴 경우 위와 같이 생성자 자동 포함되지 않음. 
		//명시적 생성자 
		this.iv = iv;  //자기 자신을 가리킴. 
	}
	//void A() {}//는 일반 매서드 

}
class Rectangle{
	int width;
	int height;
	int area; 
	
	Rectangle(){}  //셍성자 
	Rectangle(int width, int height){   //생성자 
		this.width = width;
		this.height = height;
	}
	
	Rectangle(int length){
//		this.width = length;
//		this.height= length;
		
//		System.out.println("생성자");// 있으면 밑의 코드 에러 발생 
		this(length,length); // this 생성자 호출 
		//this 생성자 호출이란 : this();
		// 현재 객체가 갖는 다른 생성자를 호출한다. 
		//                 생성자에서만 호출할 수 있다.
		//                 생성자의 처음에 와야한다. 
	}
	void makeArea() {
		
		this.area = width * height; 
	}
	
	
		
	void printInfo() {
		System.out.println("가로" + width + ", 세로" + height + "인 사각형의 면적은 " + area + "입니다.");
		
	}
	
}

public class Constructor {
	//
//자바에는 소멸자가 없다. 
	public static void main(String[] args) {
		A a1 = new A();   //객체 생성  
		a1.iv = 1;    // iv 변수값 대입 
		
		A a2 = new A();  //위에 선언했으면 매개변수 없는 A생성자가 없어서 안됨. 
		a2.iv = 2; 
		
		A a3 = new A();
		a3.iv = 3; 
		
		A a4 = new A(4);
		
		Rectangle r1 = new Rectangle(3,4); //가로길이가 3, 세로길이가 4인 사각형객체 
		Rectangle r2 = new Rectangle(5); //가로길이와 세로길이가 5인 사각형객체 
		r1.makeArea();   //사각형의 면적을 계산한다. 
		r1.printInfo();  //"가로3, 세로4인 사각형의 면적은 12입니다."를 출력한다. 
		
		r2.makeArea();  //사각형의 면적을 계산한다. 
		r2.printInfo(); //가로5, 세로5인 사각형의 면적은 25입니다."를 출력한다. 

		Rectangle r3 = new Rectangle();
		r3.width = 6; 
		r3.height = 6;
		
		
	}

}
