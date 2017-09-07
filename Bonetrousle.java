import java.math.BigInteger;
import java.util.Scanner;

public class Bonetrousle {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		BigInteger n, k, b;
		while (q-- != 0) {
			n = new BigInteger(in.nextLong() + "");
			k = new BigInteger(in.nextLong() + "");
			b = new BigInteger(in.nextInt() + "");
			BigInteger low = b.multiply(b.add(BigInteger.ONE));
			low = low.divide(BigInteger.valueOf(2));
			BigInteger up = BigInteger.valueOf(2).multiply(k).multiply(b).subtract(b.multiply(b)).add(b);
			up = up.divide(BigInteger.valueOf(2));
			if (n.compareTo(low) < 0 || n.compareTo(up) > 0) {
				System.out.println("-1");
			} else {
				StringBuilder sb = new StringBuilder();
				long ans[] = new long[b.intValue()];
				long add = (n.subtract(low)).divide(b).longValue();
				long rem = (n.subtract(low)).mod(b).longValue();
				for (int i = 0; i < b.intValue(); i++) {
					ans[i] = i + add + 1;
				}
				if (rem != 0) {
					for (int i = (int) (b.intValue() - rem); i < b.intValue(); i++) {
						ans[i]++;
					}
				}
				for (int i = 0; i < b.intValue() - 1; i++) {
					sb.append(ans[i] + " ");
				}
				sb.append(ans[b.intValue() - 1]);
				System.out.println(sb);
			}
		}
		in.close();
	}
}
