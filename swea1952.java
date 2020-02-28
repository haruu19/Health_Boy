import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class swea1952 {
	static int[] cost;
	static int[] a;
	static int ans;
	static int min;
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			cost = new int[4];
			a = new int[12+1];
			for(int i=0;i<4;i++) 
				cost[i] = sc.nextInt();
			for(int i=1;i<=12;i++) 
				a[i] = sc.nextInt();
			min = Integer.MAX_VALUE;
			dfs(1);
			//1년 이용권은 나중에 비교하자.
			min = Math.min(min, cost[3]);
			System.out.println("#"+t+" "+min);
		}
	}
	static void dfs(int idx) {
		if(idx>12) {
			min = Math.min(min, ans);
			return;
		}
		for(int i=0;i<3;i++) {
			if(i==0) {
				ans += cost[i] * a[idx];
				dfs(idx+1);
				ans -= cost[i] * a[idx];
			}
			if(i==1) {
				ans += cost[i];
				dfs(idx+1);
				ans -= cost[i];
			}
			if(i==2) {
				ans += cost[i];
				dfs(idx+3);
				ans -= cost[i];
			}
		}
	}
}