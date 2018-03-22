import java.lang.Math;

public class SquareRoot {

	public static void main(String[] args) {
		double a = 3;
		double b = 2.5;
		double c = -0.5;

		double d = b*b - 4 * a * c;
		if(a == 0 && b == 0 || d < 0) {
			System.out.println("x1=");
			System.out.println("x2=");
		} else if(a == 0){
			System.out.println("x1=" + (c == 0 ? 0.0 :(-c) / b));
			System.out.println("x2=" + (c == 0 ? 0.0 :(-c) / b));
		} else {
			System.out.println("x1=" + (-b + Math.sqrt(d)) / (2 * a));
			System.out.println("x2=" + (-b - Math.sqrt(d)) / (2 * a));
		}
	}
}
      
      