import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * https://www.hackerrank.com/challenges/clique
 * @author jake
 *
 */
public class Clique {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		int t = in.nextInt();
		int n,m;
		while(t--!=0) {
			n=in.nextInt();
			m=in.nextInt();
			System.out.println(binary(n, m, 0, n));
		}
		in.close();

	}
	public static int binary(int n,int m,int low,int high) {
		if(low>=high) return low;
		int mid=low+high;
		mid/=2;
		double d = func(n,mid);
		double d1 = func(n,mid-1);
		if(d>=m&&d1<m) return mid;
		if(d<m) return binary(n, m, mid+1, high);
		else return binary(n, m, low, mid-1);
	}
	public static double func(int n,int r) {
		if(r==0) return 0;
		return 0.5*(n*n-(n%r)*Math.pow(Math.ceil((double)n/r),2)-(r-(n%r))*Math.pow(Math.floor((double)n/r), 2));
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
