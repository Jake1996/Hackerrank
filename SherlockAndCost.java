import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SherlockAndCost {
    public static int b[] = new int[100000];
    public static int dp[][] = new int[100000][2];
    public static void main(String args[]) {
        FastReader in = new FastReader();
        int t = in.nextInt();
        while (t-- != 0) {
            int n = in.nextInt();
            for(int i=0;i<n;i++) 
                b[i] = in.nextInt();
            dp[0][0] = 0;
            dp[0][1] = 0;
            for(int i=1;i<n;i++) {
                dp[i][0] = Math.max(dp[i-1][0],b[i-1]-1+dp[i-1][1]);
                dp[i][1] = Math.max(dp[i-1][0]+Math.abs(b[i]-1),Math.abs(b[i-1]-b[i])+dp[i-1][1]);
            }
            System.out.println(Math.max(dp[n-1][0],dp[n-1][1]));
        }
        in.close();
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