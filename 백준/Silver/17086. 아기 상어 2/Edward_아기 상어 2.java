import java.io.*;
import java.util.*;

public class Main {
    static List<Shark> sharkList;
    static int[][] map;
    static int N,M,res;
    static class Shark{
        int row;
        int col;
        Shark(int row, int col) {
            this.row = row;
            this.col = col;  
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());   // row 크기
        M = Integer.parseInt(st.nextToken());   // column 크기
        sharkList = new ArrayList<>();  // 상어의 좌표들을 저장하는 리스트
        map = new int[N][M];    // 전체 map
        res = Integer.MIN_VALUE;    // 결과값(안전 거리의 최댓값)


        //map 만들기
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                //상어의 위치는 sharkList에 따로 추가
                if(map[r][c] == 1) sharkList.add(new Shark(r,c));
            }
        }

        //map 순회
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                //해당 칸이 0일 경우 그 칸을 기준으로 안전거리 구하기
                if(map[r][c] == 0) calcMinDist(r,c);
            }
        }

        System.out.println(res);
    }

    private static void calcMinDist(int stRow, int stCol) {
        int minSafeDist = Integer.MAX_VALUE;    // 가장 짧은 안전거리

        //sharkList 순회하며 상어 하나씩 뽑기
        for(Shark shark : sharkList) {
            int dr = Math.abs(stRow - shark.row); // 기준칸과 상어 사이의 row 거리
            int dc = Math.abs(stCol - shark.col); // 기준칸과 상어 사이의 column 거리
            int safeDist = Math.min(dr, dc) + Math.abs(dr - dc); // 안전거리(대각선 이동거리 + 가로세로 이동거리)

            //이전까지 가장 짧았던 안전거리와 방금 계산한 안전거리 중 더 짧은 값 저장
            minSafeDist = Math.min(minSafeDist, safeDist);
        }

        // 현재 칸에서 계산한 가장 짧은 안전거리가 res보다 클 경우 res에 저장
        res = Math.max(res, minSafeDist);
    }

}
