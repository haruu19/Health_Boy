package hw200305;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 가중치 방향성 그래프의 간선 정보가 주어졌을 때 N개의 정점 사이에서 최소비용의 사이클 찾기
 * 
 * 주의사항
 * 1)두 마을의 왕복도 사이클로 들어가야함. 고려되는지 확인하자
 * 2)간선의 길이는 자연수
 * 3)사이클이 없어서 -1 출력해야 되는 경우
 * 
 */

public class 운동 {
	static int[][] graph; // 방향성 있는 가중치 그래프를 인접 행렬을 써보자.
	static int ans;
	static boolean[] visit;
	static int N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			graph = new int[N+1][N+1];
			for(int i=0;i<M;i++) {
				int s,e,c;
				s = sc.nextInt();
				e = sc.nextInt();
				c = sc.nextInt();
				graph[s][e] = c;
			}
			visit = new boolean[N+1];
			ans = Integer.MAX_VALUE;
			for(int i=1;i<=N;i++) {
				Arrays.fill(visit, false);
				dfs(i, i, 0);
			}

			System.out.println("#"+t+" "+(ans==Integer.MAX_VALUE?-1:ans));
		}
	}
	static void dfs(int start, int now, int dist) {
		if(now==start && visit[now]) {
			if(dist<ans)
				ans=dist;
			return;
		}
		
		if(visit[now]) {
			return;
		}
		
		if(dist>=ans) {
			return;
		}
		visit[now] = true;
		for(int i=1;i<=N;i++) {
			if(graph[now][i]>0) {
				dfs(start, i, dist + graph[now][i]);
			}
		}
	}
}
