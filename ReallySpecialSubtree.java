import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;


public class ReallySpecialSubtree {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		in.nextInt();
		int m = in.nextInt();
		int u,v,w;
		Kruskal k = new Kruskal();
		k.graph = new ArrayList<>();
		while(m--!=0) {
			u = in.nextInt()-1;
			v = in.nextInt()-1;
			w = in.nextInt();
			k.graph.add(new edge(w, u, v));
		}
		in.close();
		System.out.println(k.kruskal());
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
	static class edge{
		int weight;
		int u;
		int v;
		public edge(int w,int x,int y) {
			weight = w;
			u = x;
			v = y;
		}
	}
	static class Kruskal {
		public ArrayList<edge> graph;
		public int cycle[];
		public int root(int i) {
			while(cycle[i]!=i) {
				cycle[i] = cycle[cycle[i]];
				i=cycle[i];
			}
			return i;
		}
		public void union(int u,int v) {
			int p = root(u);
			int q = root(v);
			cycle[p]=cycle[q];
		}
		public long kruskal() {
			cycle = new int[graph.size()];
			for(int i=0;i<graph.size();i++) {
				cycle[i] = i;
			}
			graph.sort(new Comparator<edge>() {
				@Override
				public int compare(edge o1, edge o2) {
					if(o1.weight==o2.weight) {
						return o1.u+o1.v+o1.weight-o2.u-o2.v-o2.weight;
					}
					return o1.weight-o2.weight;
				}
			});
			long mincost = 0,w;
			int u,v;
			for(int i=0;i<graph.size();i++) {
				u=graph.get(i).u;
				v=graph.get(i).v;
				w = graph.get(i).weight;
				if(root(u)!=root(v)) {
					mincost+=w;
					union(u,v);
				}
			}
			return mincost;
		}
	}

}
