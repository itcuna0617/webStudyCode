package com.greedy.section03.abstraction;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		
		/*
		 * 객체 지향 프로그래밍(oop - Object Oriented Programming language)
		 * 현실 세계의 모든 사건(Event)는 객체와 객체의 상호작용에 의해 일어난다는 세계관을
		 * 프로그램으로 만들 때 이용하여 새로운 세계를 창조하는 방법론이다.
		 * 현실 세계와 닮아 있지만 현실 세계와 다른 점은, 
		 * 객체 간의 상호작용을 한다는 사실은 현실 세계와 동일하지만 의인화 기법이 적용되어
		 * 현실세계에서 불가능한 무생물이나 개념 같은 존재들도 하나의 주체로
		 * 본인의 상태를 스스로 제어하고 행동하도록 한다는 것이 현실 세계와는 다른점이다.(캡슐화를 의미)
		 * 모든 객체는 책임을 가지고 자신의 역할을 수행한다.
		 * (단일 책임의 원칙(Single Responsibility Principle - SRP))
		 * 또한 하나의 객체는 너무 많은 일을 수행하는 것이 아닌 적절한 책임을 가지고 있어야 한다.
		 */
		
		/*
		 * 이 때 객체를 설계하기 위해서는 복잡한 현실 세계를 그대로 반영하기에는 너무 방대하고
		 * 복잡하기 때문에 현실 세계를 프로그램의 목적에 맞게 단순화하는 추상화라는 기법을 적용하게 된다.
		 * 
		 * 추상화란?
		 * 공통된 부분을 추출하고 공통되지 않은 부분을 제거한다는 의미를 가지며,
		 * 추상화의 목적은 유연성을 확보하기 위함이다.
		 * 유연성 확보는 여러 곳에 적용될 수 있는 유연한 객체를 의미하며, 즉 재사용성이
		 * 높아질 수 있게 한다는 의미이다.
		 * 객체의 재사용성이 증가하면 중복 작성되는 코드를 줄일 수 있으며, 오류 발생 가능성을
		 * 감소시키고 유지보수성을 증가시킨다.
		 * 
		 * 캡슐화란?
		 * 본인의 상태를 스스로 제어한다는 의미를 부여하고,(SRP, 데이터 은닉)
		 * 의도하지 않은 값의 접근을 막기 위해 적용하는 기술
		 */
		
		/*
		 * 객체와 객체의 상호작용이란?
		 * 객체와 객체는 메세지를 통해 서로 상호작용을 한다. 메세지(메소드 호출)를 보내는 쪽을 송신자라고
		 * 하고 메세지를 수신하는 쪽을 수신자라고 한다. 수신자는 메세지를 전달 받아 그 메세지에 해당하는
		 * 내용을 처리하는 방법을 스스로 결정하고, 그 결정한 방법대로 처리 할 명령어들을 순차적으로
		 * 기술한 것을 메소드라고 한다.
		 * 
		 * 책임주도 설계란?
		 * 상호작용에 중요한 것은 메세지를 송수신하고 그 방법을 기술하여 메세지에 응답하는 상호작용을
		 * 설계하는 것이다. 따라서 객체를 설계할 때는 속성은 부가적이며 적절한 책임을 가진 객체들간에
		 * 주고 받는 메세지를 이용해 상호작용하는 것을 설계하는 것이 중요하다.
		 * 즉, 필드보다는 메소드를 중점으로 추상화 기법을 적용하여 객체를 설계하고,
		 * 그 객체를 구현하는 방법으로 설계한 대로 클래스를 작성하는 것이다.
		 */
		
		/*
		 * <카레이서가 자동차를 운전하는 프로그램>을 만들려고 한다.
		 * 
		 * 기능을 최대한으로 단순화 시켜서 프로그램을 만들어 보자.
		 * 1. 자동차는 처음에 멈춘 상태로 대기하고 있는다.
		 * 2. 카레이서는 먼저 자동차에 시동을 건다. 이미 걸려있는 경우 다시 시동을 걸 수 없다.
		 * 3. 카레이서가 엑셀레이터를 밟으면 시동이 걸린 상태일 경우 자동차는 시속이 10km/h씩 증가하며
		 *    앞으로 나아간다.
		 * 4. 자동차가 달리는 중인 경우 브레이크를 밟으면 자동차의 시속은 0으로 떨어지며 멈춘다.
		 * 5. 브레이크를 밟을 때 자동차가 달리는 중이 아니라면 이미 멈춰있는 상태라고 안내한다.
		 * 6. 카레이서가 시동을 끄면 더 이상 자동차는 움직이지 않는다.
		 * 7. 자동차가 달리는 중이라면 시동을 끌 수 없다.
		 */
		
		/*
		 * 여기서 필요한 객체는 카레이서와 자동차 객체이다.
		 * 카레이서가 수신할 수 있는 메세지는 카레이서가 해야 할 일과 동일하다.
		 * 1. 시동을 걸어라.
		 * 2. 엑셀레이터를 밟아라.
		 * 3. 브레이크를 밟아라.
		 * 4. 시동을 꺼라. 
		 * 
		 * 자동차가 수신할 수 있는 메세지는 자동차가 해야 할 일과 동일하다.
		 * 1. 시동이 걸려라.
		 * 2. 앞으로 가라.
		 * 3. 멈춰라.
		 * 4. 시동이 꺼져라.
		 * 
		 * 우리는 카레이서에게 명령할 수 있는 메뉴를 만들어서 카레이서와 자동차를 상호작용 시킬 것이다.
		 * 에러가 나더라도 작성을 하고 그 에러나는 부분을 따라가며 작성을 해볼 것이다.
		 */
		
		/* 카레이서가 한 명 등장합니다.(카레이서 객체를 생성 시 카레이서 객체는 이미 자신의 자동차 객체를 가지고 있다.) */
		CarRacer racer = new CarRacer();

		/* 입력 받은 값에 따라 메뉴가 실행되고 카레이서의 행동이 달라지게 해보자. */
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("============ 카레이싱 프로그램 ============");
			System.out.println("1. 시동 걸기");
			System.out.println("2. 전진");
			System.out.println("3. 정지");
			System.out.println("4. 시동 끄기");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 선택(번호로 입력하시오): ");
			int no = sc.nextInt();
			
			switch(no) {
				case 1: racer.startUp(); break;
				case 2: racer.stepAccelator(); break;
				case 3: racer.stepBreak(); break;
				case 4: racer.turnOff(); break;
//				case 9: System.out.println("프로그램을 종료합니다."); break;
				case 9: System.out.println("프로그램을 종료합니다."); return;	// 한번에 return으로 종료하자.
				default: System.out.println("잘못된 번호를 선택하셨습니다.");
			}
			
//			if(no == 9) break;		// return이 아닌 break만으로 처리 시
			
		} while(true);
	}
}






