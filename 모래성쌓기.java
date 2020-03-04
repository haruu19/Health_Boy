package hw200303;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 모래성쌓기 {
	static class Node{
		int r,c;
		Node(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	static int[] dr= {-1,-1,-1,0,1,1,1,0};
	static int[] dc ={-1,0,1,1,1,0,-1,-1};
	static int H,W;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			H = sc.nextInt();
			W = sc.nextInt();
			int[][] map = new int[H][W];
			for(int r=0;r<H;r++) {
				String str = sc.next();
				for(int c=0;c<W;c++) {
					if(str.charAt(c)!='.')
						map[r][c]=str.charAt(c)-'0';
				}
			}
			Queue<Node> queue = new LinkedList<>();
			for(int r=0;r<H;r++) {
				for(int c=0;c<W;c++) {
					//바다라면
					if(map[r][c]==0){
						//8방에 대해 모래존재한다면 견고함1감소
						for(int d=0;d<8;d++) {
							int nr=r+dr[d];
							int nc=c+dc[d];
							if(nr<0|nc<0||nr>=H||nc>=W)
								continue;
							if(map[nr][nc]!=0) {
								map[nr][nc]--;
								if(map[nr][nc]==0) {
									//원래모래였으나 깍여 바다된친구는 -1
									map[nr][nc]=-1;
									//다음 회차에서 주변 모래의 견고함을 줄이기 위해 큐에 저장.
									queue.add(new Node(nr,nc));
								}
							}
						}
					}
				}
			}
			int ans=0;
			while(!queue.isEmpty()){					
				int size=queue.size();
				for(int i=0;i<size;i++) {
					//직전 루프에서 바다 된 친구들
					Node node = queue.poll();
					for(int d=0;d<8;d++) {
						int nr=node.r+dr[d];
						int nc=node.c+dc[d];
						if(nr<0|nc<0||nr>=H||nc>=W)
							continue;
						//모래 발견되면
						if(map[nr][nc]>0) {
							map[nr][nc]--;
							if(map[nr][nc]==0) {
								//원래모래였으나 깍여 바다된친구는 -1
								map[nr][nc]=-1;
								//다음 회차에서 주변 모래의 견고함을 줄이기 위해 큐에 저장.
								queue.add(new Node(nr,nc));
							}

						}
					}
				}
				ans++;
			}
			System.out.println("#"+t+" "+ans);
		}
	}
}
	
//......
//.839..
//.3428.
//.9393.
//......