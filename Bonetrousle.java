import java.util.Scanner;

public class Bonetrousle {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		long n,b,k;
		while(q--!=0) {
			n = in.nextLong();
			k = in.nextLong();
			b = in.nextLong();
			long low = b*(b+1);
			low/=2;
			long up = 2*k*b-b*b+b;
			up/=2;
			if(n<low||n>up) {
				System.out.println("-1");
			}
			else {
				
			}
			
		}
		in.close();
	}
}
