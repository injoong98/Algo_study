import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] days = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int n=0; n<N; n++) {
                days[n] = Integer.parseInt(st.nextToken());
            }

            int currMax = 0;
            long sum = 0;
            for(int i=N-1; i>=0; i--) {
                if(days[i] > currMax) {
                    currMax = days[i];
                }else {
                    sum += currMax - days[i];
                }
            }

            sb.append(sum+"\n");
        }

        System.out.println(sb.toString());
    }
}