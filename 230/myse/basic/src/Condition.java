
public class Condition {

	public static void main(String[] args) {
		//if(조건문 a>b){}   if(논리형){}
		int gend=1; 
		if(gend==1|| gend==3) {
			System.out.println("남자");
		}
		//if else
		if(gend==1||gend==3) {
			System.out.println("남자");
			//;
			//;
		}else {
			System.out.println("여자");
			//;
			//else는 독립적불가 if는 가능 
		}
		
		if(gend==1||gend==3)
			System.out.println("남자");
		else
			System.out.println("여자");
		// if else if   
		int hour = 15;
		if(hour > 12 && hour <= 15) {
			System.out.println("점심");
		}else if(hour > 6 && hour<12) {
			System.out.println("아침");
		}else {
			System.out.println("저녁");
		}
		
		// 년도에 해당하는 12지를 출력하시오. 
		//년도를 12로 나눈 나머지가 0이면 원숭이, 나머지가 1이면 닭, 나머지가 2이면 개, 나머지가 3이면 돼지
		//				     4     쥐,        5 소,  6호랑이,   7토끼, 8용, 9, 뱀, 10말, 11양 
		int year = 2021; 
		if(year %12==0) {
			System.out.println("원숭이");
		}else if(year%12==1) {
			System.out.println("닭");
		}else if(year%12==2) {
			System.out.println("개");
		}else if(year%12==3) {
			System.out.println("돼지");
		}else if(year%12==4) {
			System.out.println("쥐");
		}else if(year%12==5) {
			System.out.println("소");
		}else if(year%12==6) {
			System.out.println("호랑이");
		}else if(year%12==7) {
			System.out.println("토끼");
		}else if(year%12==8) {
			System.out.println("용");
		}else if(year%12==9) {
			System.out.println("뱀");
		}else if(year%12==11) {
			System.out.println("양");
		}
		
			
			
		//2번쨰 방법 
		int year1 = 2021; 
		int mod = year1%12;
		if(mod==0) {
			System.out.println("원숭이");
		}else if(mod==1) {
			System.out.println("닭");
		}else if(mod==2) {
			System.out.println("개");
		}else if(mod==3) {
			System.out.println("돼지");
		}else if(mod==4) {
			System.out.println("쥐");
		}else if(mod==5) {
			System.out.println("소");
		}else if(mod==6) {
			System.out.println("호랑이");
		}else if(mod==7) {
			System.out.println("토끼");
		}else if(mod==8) {
			System.out.println("용");
		}else if(mod==9) {
			System.out.println("뱀");
		}else if(mod==11) {
			System.out.println("양");
		}
				
				
				
		int sal1=41; //1월 급여 
		int sal2=25;
		int sal3=25;
		int sal4=33;
		int sal5=44;
		int sal6=55; //6월 급여 
		// 상반기 급여의 월평균을 계산하시오, 평근급여값은 소숫점이하값을 정확히 갖는다. 
		int salTotal = sal1+sal2+sal3+sal4+sal5+sal6;
		double salAvg = salTotal / 6.0; //(double)salTotal/6; 
		//첫번쨰처럼 쓰는게 더 좋음 
		//double으로 미리 고정하는 것과 int타입을 강제 형변환하는 것을 비교하면 전자가 더 좋음. 
		System.out.println(salAvg);
		System.out.println("상반기 총급여="+salTotal);
		System.out.println("상반기 평균급여=" + salAvg);
		
		// 상반기 평균급여가 15미만이면 C등급, 15~30까지는 B등급, 30~45까지는 A등급, 45초과는 A+등급으로 출력하시오. 
		if(salAvg<15) {
			System.out.println("C등급");
		}else if(salAvg>=15&&salAvg<=30) {
			System.out.println("B등급");
		}else if(salAvg>30&&salAvg<=45) {
			System.out.println("A등급");
		}else {
			System.out.println("A+등급");
		}
		
		String s1 = new String("가나다");
		String s2 = new String("가나다");
		System.out.println(s1==s2);  //false 이렇게 하면 안됨 
		System.out.println(s1.equals(s2));//true 
		
		double r = Math.random();
		System.out.println(r); //임의의 숫자, 혹은 램덤한 숫자 //0.0 <= ㄱ <1.0 
		
		int intR = (int)(r*3)+1;//   1<=(int)(r*3) +1 <4
		System.out.println("컴퓨터가 낸 값:"+ intR);   //1<= intR<4 
		
		// 컴퓨터가 낸값은 intR값이다 1인 경우는 가위, 2인 경우는 바위, 3인 경우 보 
		// 사용자가 낸 값이 1인 경우는 가위, 2인 경우는 바위, 3인 경우는 보 
	    int user = 1; //가위  
	    if(intR==user) {
	    	System.out.println("비겼습니다.");
	    }else {
	    	//TOdo 이겼습니다. 졌습니다를 출력하시요. 
	    	if(intR==3) {
	    		System.out.println("이겼습니다.");//가위인 경우 
	    	}else {
	    		System.out.println("졌습니다."); 
	    	}
	    }
		// 
		
       //switch
			//중복되는 코드는 줄여주자 
	}

}
