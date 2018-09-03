package studygroup.daybreak.java8inaction.chap9.prrain;


public class HelloWorld{

	public static void main(String[] args) {
		new C().hello();
	}

	static interface A{
        public default void hello() {
            System.out.println("Hello from A");
        }
    }
	static interface B extends A{
        public default void hello() {
            System.out.println("Hello from B");
        }
   
    }
	
/*	static class C implements B,A{
		
	}*/
	
	static class C extends D implements B,A{
		//B와 A를 상속받는 관계이므로 
	}
	static class D implements A{
		//A를 오버라이드 하지 않고 단수히 인터페이스 A 구현 
	}
}
