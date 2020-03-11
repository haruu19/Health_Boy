
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    WHITE=0
    BLACK=1
    N=int(input())
    x_loc=list(map(int,input().split()))
    y_loc=list(map(int,input().split()))
    E=float(input())
    color=[WHITE for _ in range(N)]
    graph=[[] for _ in range(N)]
    for i in range(N):
        for j in range(i+1,N):
            dist=(x_loc[i]-x_loc[j])*(x_loc[i]-x_loc[j])+(y_loc[i]-y_loc[j])*(y_loc[i]-y_loc[j])
            graph[i].append([dist,j])
            graph[j].append([dist,i])
            
    import heapq
    def prim(start):
        color[start]=BLACK
        queue=[]
        min_sum=0
        check=1
       
        for v in graph[start]:
            heapq.heappush(queue,v)
            
        while queue:
            cost,vertex=heapq.heappop(queue)
            if color[vertex]==WHITE:
                min_sum+=cost
                check+=1
                color[vertex]=BLACK
                for v in graph[vertex]:
                    heapq.heappush(queue,v)
            
            if check==N:
                return min_sum
    print('#',end='')
    print(test_case,end=' ')
    ans=round((prim(0)*E),0)
    print(int(ans))
