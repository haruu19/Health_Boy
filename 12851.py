from collections import deque

N,K=map(int,input().split())

WHITE=0
BLACK=1
GRAY=2

color=[WHITE]*100002 ####color index range how?????
def bfs(start):
    time=[]
    sec=0
    q=deque()
    q.append([start,sec])
    while q:
        v,s=q.popleft()
        color[v]=BLACK

        if v==K:
            if not time: 
                time.append(s)
            elif time[-1]==s:
                time.append(s)
            # else :
            #     return time
       
        if time:
            if time[-1]<s:
                return time

        s+=1
        if v>=0 and v<=100000: #####v<1000000이면 run time error??
            if color[v-1]==WHITE:
                q.append([v-1,s])
                # color[v-1]=GRAY 왜 gray 있으면 run time error???
                
            if color[v+1]==WHITE:
                q.append([v+1,s])
                # color[v+1]=GRAY

            if 2*v<=100000:
                if color[2*v]==WHITE:        
                    q.append([2*v,s])
                    # color[2*v]=GRAY
        
        color[K]=WHITE
    return time

answer=bfs(N)
print(answer[0],len(answer))