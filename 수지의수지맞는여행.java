package hw200303;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 수지의수지맞는여행 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static class Pos{
		int x;
		int y;
		int cnt;
		Pos(int x, int y, int cnt){
			this.x=x;
			this.y=y;
			this.cnt=cnt;
		}
	}
	static int R,C;
	static int ans;
	static int cnt;
	static char[][] a;
//	static boolean[][] v;
	static int v;
	static boolean[] arr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			R = sc.nextInt();
			C = sc.nextInt();
			a = new char[R][C];
//			v = new boolean[R][C];
			v = 0;
			sc.nextLine();
			for(int i=0;i<R;i++) {
				String s = sc.nextLine();
				a[i] = s.toCharArray();
			}
			arr = new boolean[30];
			cnt = 1;
			ans = 0;
//			v[0][0]=true;
			v |= 1<< (a[0][0]-65);
			arr[a[0][0]-65]=true;
			dfs(0,0);
			System.out.println("#"+t+" "+ans);
		}
		//65
	}
	
	static void dfs(int x, int y) {
		if(ans<cnt)
			ans=cnt;// ans가 cnt 보다 작을 때만 갱신한다.
		int nx,ny;
		for(int i=0;i<4;i++) {
			nx = x+ dx[i];
			ny = y+ dy[i];
			if(isOut(nx,ny)||(v&1<<(a[nx][ny]-65)) !=0||arr[a[nx][ny]-65])
				continue;
			cnt++;
//			v[nx][ny]=true;
			v |= 1<<(a[nx][ny]-65);
			arr[a[nx][ny]-65]=true;
//			ans = Math.max(cnt, ans);
			dfs(nx,ny);
			cnt--;
//			v[nx][ny]=false;
			v &= ~(1<<(a[nx][ny]-65));
			arr[a[nx][ny]-65]=false;
		}
	}
	static boolean isOut(int x, int y) {
		return (x<0||x>=R||y<0||y>=C);
	}
}
