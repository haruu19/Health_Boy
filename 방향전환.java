package hw200303;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 방향전환 {
	static class Point{
		int x,y,cnt;
		boolean dir;
		Point(int x,int y,int cnt,boolean dir){
			this.x=x;
			this.y=y;
			this.cnt=cnt;
			this.dir=dir;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int sx,sy,fx,fy;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			sx=sc.nextInt()+100;
			sy=sc.nextInt()+100;
			fx=sc.nextInt()+100;
			fy=sc.nextInt()+100;
			//bfs -> queue,visited,
			Queue<Point> queue = new LinkedList<>();
			boolean[][][] visited = new boolean[201][201][2];
			//시작점 큐에 삽입
			queue.add(new Point(sx,sy,0,true));
			queue.add(new Point(sx,sy,0,false));
			//첫 시작점 현재위치, 다음 이동을 가로/세로 이동하는 상태
			//큐에 이미 삽입 된 상태에 대해서는 방문체크
			visited[sx][sy][0] = true;
			visited[sx][sy][1] = true;
			//큐가 빌때까지 상태 탐색
			while(!queue.isEmpty()) {
				Point point = queue.poll();
				//현재 도달한 상태의 좌표가 목적지라면 종료
				if(point.x==fx&&point.y==fy) {
					System.out.println("#"+t+" "+point.cnt);
					break;
				}
				
				if(point.dir) {
					//현재 자신의 방향이 가로로 허용된 경우
					//두가지 세로이동에 대해 유효하다면(범위,방문체크), 상태노드를 큐에 추가 후 방문체크
					for(int d=0;d<2;d++) {
						int nx = point.x + dx[d];
						int ny = point.y + dy[d];
						if(nx<0||ny<0||nx>=201||ny>=201)
							continue;
						if(visited[nx][ny][0])
							continue;
						queue.add(new Point(nx,ny,point.cnt+1,!point.dir));
						visited[nx][ny][0]=true;
					}
				}
				else{
					//현재 자신의 방향이 세로로 허용된 경우
					//두가지 가로이동에 대해 유효하다면 상태노드를 큐에 추가 후 방문체크
					for(int d=2;d<4;d++) {
						int nx = point.x + dx[d];
						int ny = point.y + dy[d];
						if(nx<0||ny<0||nx>=201||ny>=201)
							continue;
						if(visited[nx][ny][1])
							continue;
						queue.add(new Point(nx,ny,point.cnt+1,!point.dir));
						visited[nx][ny][1]=true;
					}
				}
				
			}
		}
	}
}
