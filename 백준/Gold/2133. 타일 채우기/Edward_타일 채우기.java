import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        if(N % 2 == 1){
            System.out.println(0);
            return;
        }

        int[] arr = new int[N+1];
        arr[0] = arr[1] = 0;
        arr[2] = 3;

        for(int n=3; n<N+1; n++) {
            if(n % 2 == 0) {
                arr[n] = calcDP(n, arr);
            }
        }

        System.out.println(arr[N]);
    }

    private static int calcDP(int N, int[] arr){
        int sum = 0;

        for(int n=N-2; n>=0; n-=2) {
            sum += arr[n] * 2;
        }
        sum += arr[N-2] + 2;

        return sum;
    }
}