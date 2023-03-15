import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        for(int i=0; i<sequence.length; i++) {
            sequence[i] *= 1 - ( 2 * (i%2) );
        }
        
        answer = Math.max(answer, calcMax(sequence));
        
        for(int i=0; i<sequence.length; i++) {
            sequence[i] *= -1;
        }
        
        answer = Math.max(answer, calcMax(sequence));
        
        return answer;
    }
    
    private long calcMax(int[] sequence) {
        long max = Integer.MIN_VALUE;
        long sum = 0;
        
        for(int no : sequence) {
            sum += no;
            if(sum < 0) sum = 0;
            max = Math.max(max, sum);
        }
        
        return max;
    }
}