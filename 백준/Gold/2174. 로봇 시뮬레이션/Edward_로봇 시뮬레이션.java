import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int A, B, N, M;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static List<Robot> robotList;

    static class Robot {
        int no;
        int row;
        int col;
        int dir;
        private Robot(int no, int row, int col, int dir) {
            this.no = no;
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[B][A];
        robotList = new ArrayList<>();
        robotList.add(new Robot(0,0,0,0));

        //로봇들을 리스트에 저장 & map에 위치 표시
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            saveInitialRobot(st, i);
        }

        //명령 하나씩 실행하기
        boolean isExecutable = true;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            isExecutable = executeCommand(st);
            if(!isExecutable) break;
        }

        if(isExecutable) System.out.println("OK");
    }

    private static boolean executeCommand(StringTokenizer st) {
        int no = Integer.parseInt(st.nextToken());
        String command = st.nextToken();
        int repeatCount = Integer.parseInt(st.nextToken());

        switch(command) {
            case "F":
                return goForward(no, repeatCount);
            case "L":
                turnLeft(no, repeatCount);
                return true;
            case "R":
                turnRight(no, repeatCount);
                return true;
        }

        return true;
    }

    private static boolean goForward(int no, int repeatCount) {
        Robot robot = robotList.get(no);
        map[robot.row][robot.col] = 0;

        for(int i=0; i<repeatCount; i++) {
            robot.row += dr[robot.dir];
            robot.col += dc[robot.dir];

            if(robot.row < 0 || robot.row >= B || robot.col < 0 || robot.col >= A) {
                System.out.println("Robot "+robot.no+" crashes into the wall");
                return false;
            }

            if(map[robot.row][robot.col] != 0) {
                System.out.println("Robot "+robot.no+" crashes into robot "+map[robot.row][robot.col]);
                return false;
            }
        }

        map[robot.row][robot.col] = robot.no;
        return true;
    }

    private static void turnLeft(int no, int repeatCount) {
        Robot robot = robotList.get(no);
        robot.dir = (robot.dir + 3*repeatCount) % 4;
    }

    private static void turnRight(int no, int repeatCount) {
        Robot robot = robotList.get(no);
        robot.dir = (robot.dir + repeatCount) % 4;
    }

    private static void saveInitialRobot(StringTokenizer st, int index) {
        int no = index + 1;
        int col = Integer.parseInt(st.nextToken()) - 1;
        int row = Integer.parseInt(st.nextToken()) - 1;
        int dir = toIntegerDirection(st.nextToken());

        robotList.add(new Robot(no, row, col, dir));
        map[row][col] = no;
    }

    private static int toIntegerDirection(String dirString) {
        switch (dirString){
            case "N":
                return 0;
            case "E":
                return 1;
            case "S":
                return 2;
            case "W":
                return 3;
        }
        return 0;
    }
}
