import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class SpecialSubtree {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<ArrayList<edge>> g = new ArrayList<>();
		for(int i=0;i<n;i++) g.add(new ArrayList<>());
		int u,v,w;
		while(m--!=0) {
			u = in.nextInt()-1;
			v = in.nextInt()-1;
			w = in.nextInt();
			g.get(u).add(new edge(w, v));
			g.get(v).add(new edge(w, u));
		}
		int s = in.nextInt()-1;
		in.close();
		new Prim();
		Prim.graph = g;
		System.out.println(Prim.prim(s));
	}
	static class edge{
		int weight;
		int v;
		public edge(int w,int x) {
			weight = w;
			v = x;
		}
	}
	static class Prim {
		public static ArrayList<ArrayList<edge>> graph;
		public static boolean marked[];
		public static long prim(int start) {
			marked = new boolean[graph.size()];
			long mincost = 0;
			int u,v;
			PriorityQueue<edge> Q = new PriorityQueue<edge>(new Comparator<edge>() {
				@Override
				public int compare(edge o1, edge o2) {
					return o1.weight-o2.weight;
				}
			});
			Q.add(new edge(0,start));
			edge ref;
			while(!Q.isEmpty()) {
				ref = Q.remove();
				u=ref.v;
				if(marked[u]) continue;
				mincost+=ref.weight;
				marked[u]=true;
				for(int i=0;i<graph.get(u).size();i++) {
					v = graph.get(u).get(i).v;
					if(!marked[v])
						Q.add(graph.get(u).get(i));
				}
			}
			return mincost;
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
