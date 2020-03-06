package hw200306;

import java.util.Scanner;

public class swea2105_3 {
	static int N,ans,sx,sy;
	static int[][] map;
	static boolean[] check;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			N = sc.nextInt();
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			check = new boolean[101];
			ans=-1;
			for(int i=0;i<=N-1;i++) {
				for(int j=1;j<=N-2;j++) {
					sx=i;
					sy=j;
					check[map[i][j]]=true;
					dfs(i,j,0,1);
					check[map[i][j]]=false;
				}
			}
			System.out.println("#"+t+" "+ans);
		}
	}
	static void dfs(int x, int y, int dir, int cnt) {
		//종료
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if(dir==3&&nx==sx&&ny==sy) {
			if(ans<cnt)
				ans=cnt;
			return;
		}
		//실행
		//재귀
		if(nx>=0&&ny>=0&&nx<=N-1&&ny<=N-1&&!check[map[nx][ny]]) {
			check[map[nx][ny]]=true;
			dfs(nx,ny,dir,cnt+1);
			if(dir<=2)
				dfs(nx,ny,dir+1,cnt+1);
			check[map[nx][ny]]=false;
		}
	}
}
