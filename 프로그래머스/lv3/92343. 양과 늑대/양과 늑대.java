import java.util.*;

class Solution {
    static int N, totalSheepCnt, totalWolfCnt, res;
    static Node[] nodes;
    static boolean[] visited;
    static List<List<Integer>> routesToSheep;
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        totalSheepCnt = 0;
        totalWolfCnt = 0;
        res = 0;
        
        nodes = new Node[N];
        visited = new boolean[N];
        routesToSheep = new ArrayList<>();
        
        for(int i=0; i<N; i++) {
            nodes[i] = new Node(i, info[i]);
        }
        
        for(int[] edge : edges) {
            int st = edge[0];
            int ed = edge[1];
            
            nodes[st].addChild(nodes[ed]);
        }
        
        makeRoutes(new ArrayList<Integer>(), nodes[0]);
        
        for(List<Integer> list : routesToSheep) {
            System.out.println(Arrays.toString(list.toArray()));
        }
        
        dfs(0, 0, 0);
        
        
        
        // while(!routesToSheep.isEmpty()){
        //     if(getSheep() == false) break;
        // }
        
        return totalSheepCnt;
    }
    
    void dfs(int routeIdx, int sheepCnt, int wolfCnt) {
        List<Integer> currRoute = routesToSheep.get(routeIdx);
        List<Integer> newRoute = new ArrayList<>();
        
        for(int nodeIdx : currRoute) {
            if(visited[nodeIdx] == true) continue;

            newRoute.add(nodeIdx);
            if(nodes[nodeIdx].isSheep == true) sheepCnt++;
            else wolfCnt++;

            if(wolfCnt >= sheepCnt) return;
        }
        
        totalSheepCnt = Math.max(totalSheepCnt, sheepCnt);
        
        for(int nodeIdx : newRoute) {
            visited[nodeIdx] = true;
        }
        
        for(int i=0; i<routesToSheep.size(); i++) {
            List<Integer> route = routesToSheep.get(i);
            int sheepIdx = route.get(route.size()-1);
            if(visited[sheepIdx] == true) continue;
            
            dfs(i, sheepCnt, wolfCnt);
        }
        
        for(int nodeIdx : newRoute) {
            visited[nodeIdx] = false;
        }
        
    }
    
//     boolean getSheep() {
//         int maxRouteIdx = -1;
//         int localSheepCnt = -100;
//         int localWolfCnt = 100;
        
//         System.out.println("total sheep : " + totalSheepCnt + ", total wolf : " + totalWolfCnt);
        
//         route: for(int i=0; i<routesToSheep.size(); i++) {
//             int sheepCnt = 0;
//             int wolfCnt = 0;
            
//             List<Integer> currRoute = routesToSheep.get(i);
//             for(int nodeIdx : currRoute) {
//                 if(visited[nodeIdx] == true) continue;
                
//                 if(nodes[nodeIdx].isSheep == true) sheepCnt++;
//                 else wolfCnt++;
                
//                 if(totalSheepCnt + sheepCnt <= totalWolfCnt + wolfCnt) continue route;
//             }
            
//             System.out.println(Arrays.toString(currRoute.toArray()) + ", " + sheepCnt + ", " + wolfCnt);
            
//             if(sheepCnt - wolfCnt > localSheepCnt - localWolfCnt) {
//                 maxRouteIdx = i;
//                 localSheepCnt = sheepCnt;
//                 localWolfCnt = wolfCnt;
//             }
//         }
        
//         System.out.println("max Route index : " + maxRouteIdx);
        
//         if(maxRouteIdx == -1) return false;
        
        
//         for(int nodeIdx : routesToSheep.get(maxRouteIdx)) {
//             visited[nodeIdx] = true;
//         }
        
//         routesToSheep.remove(maxRouteIdx);
//         totalSheepCnt += localSheepCnt;
//         totalWolfCnt += localWolfCnt;
        
//         return true;
//     }
    
    void makeRoutes(List<Integer> currRoute, Node currNode) {
        visited[currNode.idx] = true;
        currRoute.add(currNode.idx);
        
        if(currNode.isSheep == true) {
            routesToSheep.add(new ArrayList<>(currRoute));
        }
        
        if(currNode.leftChild != null)
            makeRoutes(currRoute, currNode.leftChild);
        if(currNode.rightChild != null)
            makeRoutes(currRoute, currNode.rightChild);
        
        visited[currNode.idx] = false;
        currRoute.remove(currRoute.size()-1);
    }
    
    class Node {
        int idx;
        boolean isSheep;
        Node leftChild;
        Node rightChild;
        Node(int idx, int info) {
            this.idx = idx;
            this.isSheep = (info == 0);
        }
        void addChild(Node childNode) {
            if(leftChild == null)
                leftChild = childNode;
            else
                rightChild = childNode;
        }
    }
}