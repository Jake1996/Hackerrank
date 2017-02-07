import java.util.ArrayList;
import java.util.Scanner;

public class SubsetComponent {
	public static long d[] = new long[20];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for(int i=0;i<n;i++) d[i] = in.nextLong();
		in.close();
		int i=0;
		int end = 1<<n;
		long ans=64;
		long num=0;
		int j;
		ArrayList<Long> l = new ArrayList<>();
		for(i=1;i<end;i++) {
			for(int k=0;k<n;k++) {
				l.clear();
				j = 1<<k;
				j=j&i;
				if(j!=0) {
					l.add(d[k]);
				}
			}
			num|=l.get(l.size()-1);
			l.remove(l.size()-1);
			boolean flag=true;
			int p=-1;
			while(flag&&!l.isEmpty()) {
				flag=false;
				for(p=0;p<l.size();p++) {
					if((num&l.get(p))!=0) {
						num|=l.get(p);
						l.remove(p);
						flag = true;
						break;
					}
				}
				
			}
		}
		System.out.println(ans);
	}
	public static int noOfComponents(long num) {
		if(num==0) return 64;
		else return 64-count1(num)+1;
	}
	public static int count1(long k) {
		int count=0;
		while (k!=0)
	    {
	      k &= (k-1) ;
	      count++;
	    }
	    return count;
	}

}
