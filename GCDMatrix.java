import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/**
 * https://www.hackerrank.com/contests/hourrank-17/challenges/gcd-matrix
 * @author jake
 *
 */
class GCDMatrix {
	public static int a[] = new int[100000];
	public static int b[] = new int[100000];
	public static int arr[][] = new int[10000][10000];
	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt();
		int m = in.nextInt();
		int q = in.nextInt();
		for(int i=0;i<n;i++) a[i] = in.nextInt();
		for(int i=0;i<m;i++) b[i] = in.nextInt();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j] = (int)gcd(a[i], b[j]); 
			}
		}
		int a1,a2,b1,b2;
		HashSet<Integer> hs = new HashSet<>();
		while(q--!=0) {
			hs.clear();
			a1=in.nextInt();
			a2=in.nextInt();
			b1=in.nextInt();
			b2=in.nextInt();
			int count=0;
			for(int i=a1;i<=b1;i++) {
				for(int j=a2;j<=b2;j++) {
					if(!hs.contains(arr[i][j])) {
						hs.add(arr[i][j]);
						count++;
					}
				}
			}
			System.out.println(count);
		}
		in.close();
	}
	public static long gcd(long a,long b) {
		long temp;
		while(a!=0) {
			temp = a;
			a=b%a;
			b=temp;
		}
		return b;
	}
	static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
        void close() {
        	try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    } 
}
