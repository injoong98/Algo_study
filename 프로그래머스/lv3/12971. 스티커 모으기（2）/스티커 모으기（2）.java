class Solution {
    public int solution(int sticker[]) {
        int N = sticker.length;
        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        
        if(N == 1) return sticker[0];
        
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];
        for(int i=2; i<N-1; i++) {
            dp1[i] = Math.max(dp1[i-1], sticker[i] + dp1[i-2]);
        }
        
        dp2[0] = 0;
        dp2[1] = sticker[1];
        for(int i=2; i<N; i++) {
            dp2[i] = Math.max(dp2[i-1], sticker[i] + dp2[i-2]);
        }
        
        return Math.max(dp1[N-2], dp2[N-1]);
//         int len = sticker.length;
//         int sum1 = sticker[0];
//         int sum2 = len < 2 ? 0 : sticker[1];
//         int sum3 = len < 3 ? 0 : sticker[2];
        
//         for(int i=3; i<len; i+=3) {
//             if(i == len-1) {
//                 sum1 += sticker[i-1];
//                 break;
//             }
//             if(i == len-2) {
//                 sum1 += Math.max(sticker[i], sticker[i-1]);
//                 break;
//             }
//             if(i == len-3) {
//                 sum1 += Math.max(sticker[i-1]+sticker[i+1], sticker[i]);
//                 break;
//             }
            
//             if(sticker[i] < sticker[i-1] + sticker[i+1]) i--;
            
//             sum1 += sticker[i];
//         }
        
//         for(int i=4; i<=len; i+=3) {
//             if(i == len) {
//                 sum2 += sticker[i-1];
//                 break;
//             }
//             if(i == len-1) {
//                 sum2 += Math.max(sticker[i], sticker[i-1]);
//                 break;
//             }
//             if(i == len-2) {
//                 sum2 += Math.max(sticker[i-1]+sticker[i+1], sticker[i]);
//                 break;
//             }
            
//             if(sticker[i] < sticker[i-1] + sticker[i+1]) i--;
            
//             sum2 += sticker[i];
//         }
        
//         for(int i=5; i<=len; i+=3) {
//             if(i == len) {
//                 sum3 += sticker[i-1];
//                 break;
//             }
//             if(i == len-1) {
//                 sum3 += Math.max(sticker[i], sticker[i-1]);
//                 break;
//             }
//             if(i == len-2) {
//                 sum3 += Math.max(sticker[i-1]+sticker[i+1], sticker[i]);
//                 break;
//             }
            
//             if(sticker[i] < sticker[i-1] + sticker[i+1]) i--;
            
//             sum3 += sticker[i];
//         }
        
//         System.out.println("sum1, sum2, sum3 ::: "+sum1+", "+sum2+", "+sum3);
        
//         int answer = Math.max(sum1, Math.max(sum2, sum3));
//         return answer;
    }
}