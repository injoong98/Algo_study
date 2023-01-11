import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];

        for(int r=0; r<R; r++) {
            st = new StringTokenizer(br.readLine());
            char[] charArr = st.nextToken().toCharArray();
            for(int c=0; c<C; c++) {
                map[r][c] = charArr[c];
            }
        }

        if(N == 0 || N == 1) {
            printMap(map);
            return;
        }

        switch (N % 4) {
            case 1:
                map = boomNearBy(map, R, C);
                reverseMap(map, R, C);
            case 3:
                map = boomNearBy(map, R, C);
                reverseMap(map, R, C);
                break;
            default:
                fillBomb(map, R, C);
        }

        printMap(map);
    }

    private static char[][] boomNearBy(char[][] map, int R, int C) {
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        char[][] newMap = new char[R][C];

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c] == 'O'){

                    newMap[r][c] = 'O';

                    for(int d=0; d<4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                        newMap[nr][nc] = 'O';
                    }
                }
            }
        }

        return newMap;
    }

    private static void reverseMap(char[][] map, int R, int C) {
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c] == 'O')
                    map[r][c] = '.';
                else
                    map[r][c] = 'O';
            }
        }
    }

    private static void fillBomb(char[][] map, int R, int C) {
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                map[r][c] = 'O';
            }
        }
    }

    private static void printMap(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for(char[] arr : map) {
            sb.append(new String(arr));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
