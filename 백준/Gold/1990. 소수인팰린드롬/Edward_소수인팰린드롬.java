import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Math.min(Integer.parseInt(st.nextToken()),10000000);



        int[] eratos = new int[b+1];
        boolean[] notPrime = new boolean[b+1];
        for(int i=2; i<=b; i++) {
            if(notPrime[i] == true) continue;

            for(int j=2*i; j<=b; j+=i)
                notPrime[j] = true;

            if(i >= a && i <= b && isPelindrom(i)) sb.append(i+"\n");
        }
        sb.append(-1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isPelindrom(int no) {
        String str = String.valueOf(no);
        for(int i=0; i<str.length()/2; i++) {
            if(str.charAt(i) != str.charAt(str.length()-1-i)) return false;
        }
        return true;
    }
}
