import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj_12851 {
	
	static class Pos{
		int idx;
		int time;
		Pos(int idx, int time){
			this.idx = idx;
			this.time = time;
		}
	}
	static int[] a;
	static int N,K;
	static final int MAX = 987_654_321;
	static int min_time = MAX;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = new int[100001];
		N = sc.nextInt();
		K = sc.nextInt();
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(N,0));
		int nx;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			if(p.idx==K&&p.time<=min_time) {
				min_time = p.time;
				cnt++;
			}
			if(p.time==min_time+1)
				break;
			int[] dx = {p.idx+1, p.idx-1, p.idx*2};
			for(int i=0;i<3;i++) {
				nx = dx[i];
				if(nx<0||nx>100000)
					continue;
				if(a[nx]==0||a[nx]==p.time+1) {
					q.offer(new Pos(nx,p.time+1));
					a[nx]=p.time+1;
				}
			}
		}
		System.out.println(min_time);
		System.out.println(cnt);
	}
}
