import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());

            long X = Long.parseLong(st.nextToken());
            long Y = Long.parseLong(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            long power2 = power(2,K,X+Y);
            long X2 = X * power2 % (X+Y);
            long Y2 = (X+Y) - X2;

            long res = Math.min(X2, Y2);

            sb.append("#"+tc+" "+res+"\n");
        }

        System.out.println(sb.toString());
    }

    private static long power(int no, int cnt, long mod) {
        if(cnt == 1) return no;

        long power = power(no, cnt/2, mod);

        if(cnt % 2 == 0) {
            return power * power % mod;
        } else {
            return power * power * no % mod;
        }
    }
}