import java.util.ArrayList;
import java.util.Scanner;

class EvenTree {
    public static int answer = 0;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<node> tree = new ArrayList<>();
        for(int i=0;i<n;i++) {
            tree.add(new node());
        }
        int u,v;
        for(int i=0;i<m;i++) {
            u = in.nextInt()-1;
            v = in.nextInt()-1;
            tree.get(v).children.add(u);
        }
        in.close();
        fill(tree,0);
        System.out.println(answer-1);
    }
    public static int fill(ArrayList<node> tree,int cur) {
        if(tree.get(cur).count!=-1) return tree.get(cur).count;
        if(tree.get(cur).children.size()==0) {
            tree.get(cur).count = 1;
            return 1;
        }
        int count = 1;
        node t = tree.get(cur);
        for(int i=0;i<t.children.size();i++) {
            int temp = fill(tree,t.children.get(i));
            count+=temp;
        }
        tree.get(cur).count = count;
        if(count%2==0) answer++;
        return count;
    }
    public static class node {
        public int count = -1;
        public ArrayList<Integer> children = new ArrayList<>();
    }
}