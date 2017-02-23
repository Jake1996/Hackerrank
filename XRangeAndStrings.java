import java.util.ArrayList;
import java.util.Scanner;

public class XRangeAndStrings {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		String a,b;
		while(tc--!=0) {
			a = in.next();
			b = in.next();
			ArrayList<node> list1 = fun(a);
			ArrayList<node> list2 = fun(b);
			if(list1.size()!=list2.size()) {
				System.out.println("NO");
				continue;
			}
			boolean flag = true;
			for(int i=0;i<list1.size();i++) {
				if(list1.get(i).c!=list2.get(i).c) {
					flag = false;
					break;
				}
				if(list1.get(i).count<list2.get(i).count) {
					flag = false;
					break;
				}
				
			}
			if(flag) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
		in.close();
	}
	static class node {
		char c;
		int count=0;
	}
	public static ArrayList<node> fun(String s) {
		int count;
		ArrayList<node> list = new ArrayList<>();
		int len = s.length();
		for(int i=0;i<len;i++) {
			node q=new node();
			char t = s.charAt(i);
			q.c = t;
			count=1;
			i++;
			while(i<len&&s.charAt(i)==t) {
				i++;count++;
			}
			q.count=count;
			list.add(q);
			i--;
		}
		return list;
	}
}

