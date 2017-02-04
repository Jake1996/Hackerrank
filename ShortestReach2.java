import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;
/**
 * https://www.hackerrank.com/challenges/dijkstrashortreach
 * @author jake
 *
 */
public class ShortestReach2 {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		int t = in.nextInt();
		ArrayList<TreeSet<edge>> g = new ArrayList<>();
		int u,v,w,n,m,s,i;
		StringBuilder str = new StringBuilder();
		for(i=0;i<3001;i++) g.add(new TreeSet<edge>(new Comparator<edge>() {

			@Override
			public int compare(edge o1, edge o2) {
				return o1.v-o2.v;
			}
		}));
		new Dijikstra();
		Dijikstra.graph = g;
		edge ref;
		while(t--!=0){
			n = in.nextInt();
			m = in.nextInt();
			for(i=0;i<n;i++) g.get(i).clear();
			while(m--!=0) {
				u=in.nextInt()-1;
				v=in.nextInt()-1;
				w=in.nextInt();
				if(g.get(u).contains(new edge(w, v))) {
					ref = g.get(u).floor(new edge(w, v));
					ref.weight = Math.min(ref.weight, w);
					ref = g.get(v).floor(new edge(w, u));
					ref.weight = Math.min(ref.weight, w);
				}
				else {
					g.get(u).add(new edge(w, v));
					g.get(v).add(new edge(w, u));
				}
			}
			s = in.nextInt()-1;
			Dijikstra.dijikstra(s,n);
			for(i=0;i<n;i++) {
				if(i!=s) {
					if(Dijikstra.dist[i]==Long.MAX_VALUE) {
						str.append("-1 ");
					}
					else {
						str.append(Dijikstra.dist[i]+" ");
					}
				}
			}
			str.append("\n");
		}
		System.out.print(str);
		in.close();
	}
	static class edge{
		long weight;
		int v;
		public edge(long w,int x) {
			weight = w;
			v = x;
		}
	}
	static class Dijikstra {
		public static ArrayList<TreeSet<edge>> graph;
		public static long[] dist= new long[3001];
		public static boolean[] visited = new boolean[3001];
		public static void dijikstra(int source,int n) {
			for(int i=0;i<n;i++) {
				visited[i] = false;
				dist[i] = Long.MAX_VALUE;
			}
			dist[source] = 0;
			PriorityQueue<edge> Q = new PriorityQueue<>(new Comparator<edge>() {
				@Override
				public int compare(edge o1, edge o2) {
					if(o1.weight-o2.weight>0) return 1;
					if(o1.weight-o2.weight<0) return -1;
					return 0;
				}
			});
			Q.add(new edge(0, source));
			edge ref;
			int u,v;
			long w;
			while(!Q.isEmpty()) {
				ref = Q.remove();
				u = ref.v;
				if(visited[u]) continue;
				visited[u] = true;
				for(edge e:graph.get(u)) {
					v = e.v;
					w = e.weight;
					if(dist[u] + w < dist[v]) {
						dist[v] = w + dist[u];
						Q.add(new edge(dist[v], v));
					}
				}
			}
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