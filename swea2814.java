import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class swea2814 {
	static int N,M;
	static int ans;
	static boolean[] v;
	static ArrayList<Integer>[] a;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			ans = 1;
			N = sc.nextInt();
			M = sc.nextInt();
			a = new ArrayList[N+1];
			for(int i=1;i<=N;i++) {
				a[i] = new ArrayList<>();
			}
			int n1,n2;
			for(int i=0;i<M;i++) {
				n1 = sc.nextInt();
				n2 = sc.nextInt();
				a[n1].add(n2);
				a[n2].add(n1);
			}//end input
			v = new boolean[N+1];
			method();
			System.out.println("#"+t+" "+ans);
		}
	}
	static void method() {
		for(int i=1;i<=N;i++) {
			Arrays.fill(v, false);
			v[i] = true;
			dfs(i,1);
		}
	}
	static void dfs(int idx, int depth) {
		for(int i=0;i<a[idx].size();i++) {
			int temp = a[idx].get(i);
			if(v[temp])
				continue;
			v[temp] = true;
			ans = Math.max(ans, depth+1);
			dfs(temp,depth+1);
			v[temp] = false;
		}
	}
}






