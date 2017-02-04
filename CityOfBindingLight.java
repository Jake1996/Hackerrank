import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights
 * @author jake
 *
 */
public class CityOfBindingLight {
	
	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt();
		int m = in.nextInt();
		int arr[][] = new int[n][n];
		int u,v,r;
		while(m--!=0) {
			u = in.nextInt()-1;
			v = in.nextInt()-1;
			r = in.nextInt();
			arr[u][v] = r;
		}
		int q = in.nextInt();
		long[][] dist = FloydWarshall.floydwarshall(arr);
		while(q--!=0) {
			u = in.nextInt()-1;
			v = in.nextInt()-1;
			if(dist[u][v]==Long.MAX_VALUE) System.out.println("-1");
			else System.out.println(dist[u][v]);
		}
		in.close();
		
	}
	static class FloydWarshall {
		public static long[][] floydwarshall(int arr[][]) {
			int n = arr.length;
			long dist[][] = new long[n][n];
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr.length;j++) {
					if(i==j) continue;
					if(arr[i][j]==0) dist[i][j]=Long.MAX_VALUE;
					else dist[i][j] = arr[i][j];
				}
			}
			for(int k = 0; k < n; k++){
			    for(int i = 0; i < n; i++){
			    	if(dist[i][k]!=Long.MAX_VALUE)
			        for(int j = 0; j < n; j++){
			        	if(dist[k][j]!=Long.MAX_VALUE)
			            dist[i][j] = Math.min( dist[i][j], dist[i][k] + dist[k][j] );
			        }
			    }
			}
			return dist;
		}
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
