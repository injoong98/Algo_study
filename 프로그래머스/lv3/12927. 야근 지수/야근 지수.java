import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;
        
        for(int work : works) {
            pq.add(work);
        }
        
        while(n-- > 0) {
            if(pq.peek() == 0) break;
            pq.add(pq.poll() - 1);
        }
        
        for(int work : pq) {
            answer += work * work;
        }
        
        return answer;
    }
}