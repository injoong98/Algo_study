import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, M, res;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            set = new HashSet<>();

            st = new StringTokenizer(br.readLine());
            for(int n=0; n<N; n++) {
                set.add(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++) {
                set.add(st.nextToken());
            }
            
            res = N+M-set.size();

            System.out.printf("#%d %d\n",tc,res);
        }
    }
}