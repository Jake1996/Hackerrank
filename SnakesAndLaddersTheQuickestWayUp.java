import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/the-quickest-way-up
 * @author jake
 *
 */
public class SnakesAndLaddersTheQuickestWayUp {
	public static ArrayList<ArrayList<edge>> graph;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		graph = new ArrayList<>();
		for(int i=0;i<100;i++) graph.add(new ArrayList<>());
		while(t--!=0) {
			init();
			int u,v;
			int lad = in.nextInt();
			while(lad--!=0) {
				u=in.nextInt()-1;
				v=in.nextInt()-1;
				graph.get(u).clear();
				graph.get(u).add(new edge(0, v));
			}
			int snakes = in.nextInt();
			while(snakes--!=0) {
				u=in.nextInt()-1;
				v=in.nextInt()-1;
				graph.get(u).clear();
				graph.get(u).add(new edge(0, v));
			}
			Dijikstra d = new Dijikstra();
			d.graph = graph;
			d.dijikstra(0);
			if(d.dist[99]!=Long.MAX_VALUE) System.out.println(d.dist[99]);
			else System.out.println("-1");
		}
		in.close();
	}
	public static void init() {
		for(int i=0;i<100;i++) graph.get(i).clear();
		for(int i=0;i<99;i++) {
			for(int j=i+1;j<i+7&&j<100;j++) {
				graph.get(i).add(new edge(1, j));
			}
		}
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
		public ArrayList<ArrayList<edge>> graph;
		public long[] dist;
		public boolean[] visited;
		public void dijikstra(int source) {
			dist = new long[graph.size()];
			visited = new boolean[graph.size()];
			for(int i=0;i<graph.size();i++) {
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
				//w = ref.weight;
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

}
