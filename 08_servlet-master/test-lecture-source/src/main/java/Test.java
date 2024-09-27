
public class Test {

	public static void main(String[] args) {
		Object t = new TT();
		
		System.out.println(System.identityHashCode(t));
		
		((TT)t).setId("dd");
		System.out.println(System.identityHashCode((TT)t));
	}
	
	
}


