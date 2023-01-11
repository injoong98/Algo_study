import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, R, res;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] cowPositions;
    static Position[][] positionMap;
    static boolean[][] visited;


    static class Position {
        boolean isCow;
        boolean[] roadBuilt;
        private Position() {
            isCow = false;
            roadBuilt = new boolean[4];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        res = 0;

        cowPositions = new int[K][2];
        positionMap = new Position[N][N];
        visited = new boolean[N][N];

        for(int r=0; r<R; r++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            int r2 = Integer.parseInt(st.nextToken())-1;
            int c2 = Integer.parseInt(st.nextToken())-1;
            buildRoad(r1, c1, r2, c2);
        }

        for(int k=0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            cowPositions[k] = new int[] {r,c};
            if(positionMap[r][c] == null)
                positionMap[r][c] = new Position();
            positionMap[r][c].isCow = true;
        }

        for(int[] cowPos : cowPositions) {
            int cowR = cowPos[0];
            int cowC = cowPos[1];
            if(!visited[cowR][cowC]){
                bfs(cowR, cowC);
            }
        }

        System.out.println(res/2);
    }

    private static void bfs(int cowR, int cowC) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {cowR, cowC});
        visited[cowR][cowC] = true;
        int cowCnt = 1;

        while(!queue.isEmpty()) {
            int[] currPos = queue.poll();
            int r = currPos[0];
            int c = currPos[1];
            Position currPosition = positionMap[r][c];

            for(int d=0; d<4; d++) {
                if(currPosition.roadBuilt[d]) continue;
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if(visited[nr][nc]) continue;

                if(positionMap[nr][nc] == null)
                    positionMap[nr][nc] = new Position();
                if(positionMap[nr][nc].isCow == true)
                    cowCnt++;

                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
            }
        }
//        System.out.println("r, c, cowCnt : "+cowR+", "+cowC+", "+cowCnt);
        res += cowCnt * (K-cowCnt);
    }

    private static void buildRoad(int r1, int c1, int r2, int c2) {
        if(positionMap[r1][c1] == null)
            positionMap[r1][c1] = new Position();
        if(positionMap[r2][c2] == null)
            positionMap[r2][c2] = new Position();

        Position position1 = positionMap[r1][c1];
        Position position2 = positionMap[r2][c2];

        if(r1-r2 == 1){
            position1.roadBuilt[0] = true;
            position2.roadBuilt[2] = true;
        }
        if(r1-r2 == -1){
            position1.roadBuilt[2] = true;
            position2.roadBuilt[0] = true;
        }
        if(c1-c2 == 1){
            position1.roadBuilt[3] = true;
            position2.roadBuilt[1] = true;
        }
        if(c1-c2 == -1){
            position1.roadBuilt[1] = true;
            position2.roadBuilt[3] = true;
        }
    }
}