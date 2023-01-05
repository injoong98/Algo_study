import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer; 

public class Main {
    static int M, N;
    static int[] initEdge, initGrowth;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        initEdge = new int[2*M - 1];
        initGrowth = new int[3];
        map = new int[M][M];

        // 초기 높이 1 설정
        for(int i=0; i<(2*M)-1; i++) {
            initEdge[i] = 1;
        }

        // 가장자리의 N일 후 높이 저장
        for(int day=0; day<N; day++) {
            st = new StringTokenizer(br.readLine());
            initGrowth[0] = Integer.parseInt(st.nextToken());
            initGrowth[1] = Integer.parseInt(st.nextToken());
            initGrowth[2] = Integer.parseInt(st.nextToken());
            sumInitEdgeGrowth();
        }

        // 성장치 map에 기록
        for(int col=0; col<M; col++) {
            writeColumn(col);
        }

        for(int r=0; r<M; r++) {
            for(int c=0; c<M; c++) {
                sb.append(map[r][c]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void writeColumn(int col) {
        for(int i=0; i<M; i++) {
            // 0번 column일 경우 가장자리 성장치 기록했던 배열에서 값 복사
            if(col == 0)
                map[i][col] = initEdge[(M-i)-1];
            // 아닐 경우 해당하는 column의 최상단 값 복사
            else
                map[i][col] = initEdge[(M+col)-1];
        }
    }

    private static void sumInitEdgeGrowth() {
        for(int i=0; i<(2*M)-1; i++) {
            for(int height=0; height<3; height++) {
                if(initGrowth[height] != 0) {
                    initEdge[i] += height;
                    initGrowth[height]--;
                    break;
                }
            }
        }
    }
}
