package hw200306;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class swea7396 {
	static class Point implements Comparable<Point>{
		int x,y;
		char ch;
		Point(int x, int y, char ch){
			this.x=x;
			this.y=y;
			this.ch=ch;
		}

		@Override
		public int compareTo(Point p) {
			// TODO Auto-generated method stub
			return this.ch-p.ch;
		}
	}
	
	static int N,M;
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			sc.nextLine();
			map = new char[N][];
			for(int i=0;i<N;i++)
				map[i] = sc.nextLine().toCharArray();
			visit = new boolean[N][M];
			PriorityQueue<Point> pq = new PriorityQueue<>();
			ArrayList<Point> list = new ArrayList<>();
			pq.add(new Point(0,0,map[0][0]));
			visit[0][0] = true;
			System.out.print("#"+t+" ");
			while(!pq.isEmpty()) {
				int size = pq.size();
				Point first = pq.peek();// 현재 큐에 있는 글자중에 알파벳순 제일 빠른게 뭔지 일단 확보.
				for(int s=0; s<size; s++) { // 이름의 같은 위치에 올 가능성이 있는 글자들 싹 빼기
					Point now = pq.poll();
					if(first.ch==now.ch) {
						for(int i=0;i<2;i++) {
							int nx = now.x + dx[i];
							int ny = now.y + dy[i];
							if(nx>=0&&nx<N&&ny>=0&&ny<M&&!visit[nx][ny]) {
								list.add(new Point(nx,ny,map[nx][ny]));
								visit[nx][ny]=true;
							}
						}
					}else {
						break;
					}
				}//출발점에서 거리같은 좌표의 탐색이 끝남.
				pq.clear();//남은 좌표 몰라 버려.
				pq.addAll(list);//알파벳 빠른거 따로 추렸으니까 얘네만 스케쥴에 넣어 탐색하자
				list.clear();
				System.out.print(first.ch);
			}
			System.out.println();
		}
	}
}
