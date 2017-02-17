import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * https://www.hackerrank.com/challenges/crush
 * @author jake
 *
 */
public class AlgorithmicCrush {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt();
		int m = in.nextInt();
		int a,b,k;
		SegmentTreeWithLazyProp st = new SegmentTreeWithLazyProp(n);
		while(m--!=0) {
			a = in.nextInt()-1;
			b = in.nextInt()-1;
			k = in.nextInt();
			st.updateRange(a, b, k);
		}
		System.out.println(st.queryRange(0, n));
		in.close();

	}
	static class SegmentTreeWithLazyProp {
		public long ST[];
		public long lazy[];
		public int size;
		public SegmentTreeWithLazyProp(int size) {
			this.size = size-1;
			int len =largestPower(size);
			if(len==size) len = len<<1;
			else len = len<<2;
			ST = new long[len];
			lazy = new long[len];
		}
		private static int largestPower(int n){
			n = n|(n>>1);
			n = n|(n>>2);
			n = n|(n>>4);
			n = n|(n>>8);
			return (n+1)>>1;
		}
		public void updateRange(int l,int r,long delta) {
			updateRange(1, 0, size, l, r, delta);
		}
		public long queryRange(int l,int r) {
			return queryRange(1, 0, size, l, r);
		}
		public void updateRange(int node,int start,int end,int l,int r,long delta) {
			if(lazy[node]!=0) {
				ST[node] += lazy[node];
				if(start!=end) {
					lazy[node*2] += lazy[node];
					lazy[node*2+1] += lazy[node];
				}
				lazy[node] = 0;
			}
			if(start>end || start>r || end<l) 
				return;
			if(start >= l && end <=r) {
				ST[node] += delta;
				if(start!=end) {
					lazy[node*2] += delta;
					lazy[node*2+1] += delta;
				}
				return;
			}
			int mid = (start+end)/2;
			updateRange(node*2, start, mid, l, r, delta);
			updateRange(node*2+1, mid+1, end, l, r, delta);
			ST[node] = Math.max(ST[node],Math.max(ST[node*2] , ST[node*2+1]));
		}
		public long queryRange(int node,int start,int end,int l,int r) {
			if(start>end||start>r||end<l)
				return Long.MIN_VALUE;
			if(lazy[node]!=0) {
				ST[node] += lazy[node];
				if(start!=end) {
					lazy[node*2] += lazy[node];
					lazy[node*2+1] += lazy[node];
				}
				lazy[node] = 0;
			}
			if(start >=l && end<=r)
				return ST[node];
			int mid = (start+end)/2;
			return Math.max(queryRange(node*2, start, mid, l, r) , queryRange(node*2+1, mid+1, end, l, r));
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
