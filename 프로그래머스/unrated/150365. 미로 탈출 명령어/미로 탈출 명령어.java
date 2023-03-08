import java.util.*;

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder sb = new StringBuilder();
        
        int dist = Math.abs(x-r) + Math.abs(y-c);
        
        if(!isReachable(dist, k))
            return "impossible";
        
        int overCnt = (k - dist) / 2;
        int[] requiredCnt = countCommands(x, y, r, c);
        
        while(x < n) {
            if(requiredCnt[0] == 0 && overCnt == 0) 
                break;
            
            if(requiredCnt[0] > 0) {
                requiredCnt[0]--;
            }else {
                overCnt--;
                requiredCnt[3]++;
            }
            sb.append("d");
            x++;
        }
        
        while(y > 1) {
            if(requiredCnt[1] == 0 && overCnt == 0) 
                break;
            
            if(requiredCnt[1] > 0) {
                requiredCnt[1]--;
            }else {
                overCnt--;
                requiredCnt[2]++;
            }
            sb.append("l");
            y--;
        }
        
        while(overCnt != 0) {
            sb.append("rl");
            overCnt--;
        }
        
        while(requiredCnt[2]-- > 0) {
            sb.append("r");
        }
        
        while(requiredCnt[3]-- > 0) {
            sb.append("u");
        }
        
        return sb.toString();
    }
    
    public int[] countCommands(int x, int y, int r, int c) {
        // 0: d, 1: l, 2: r, 3: u
        int[] commands = new int[4];
        
        for(int i=0; i<Math.abs(x-r); i++) {
            if(x-r < 0) commands[0]++;
            else commands[3]++;
        }
        
        for(int i=0; i<Math.abs(y-c); i++) {
            if(y-c < 0) commands[2]++;
            else commands[1]++;
        }
        
        return commands;
    }
    
    public boolean isReachable(int dist, int k) {
        if(k - dist < 0 || (k - dist) % 2 == 1)
            return false;
        
        return true;
    }
}