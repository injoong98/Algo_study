import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            Queue<Integer>[] charSequence = new Queue[26];
            for(int i=0; i<26; i++) charSequence[i] = new LinkedList<>();

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            char[] charArr = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());

            for(int i=0; i<charArr.length; i++) {
                int charIdx = charArr[i] - 'a';
                charSequence[charIdx].add(i);

                if(charSequence[charIdx].size() == K) {
                    int len = i - charSequence[charIdx].poll() + 1;
                    min = Math.min(min, len);
                    max = Math.max(max, len);
                }
            }

            if(min == Integer.MAX_VALUE) {
                sb.append("-1\n");
            }else {
                sb.append(min+" "+max+"\n");
            }
        }

        System.out.println(sb.toString());
    }
}