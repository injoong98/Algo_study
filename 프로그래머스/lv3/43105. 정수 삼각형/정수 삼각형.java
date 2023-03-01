class Solution {
    public int solution(int[][] triangle) {
        
        for(int r=triangle.length-1; r>0; r--) {
            for(int c=0; c<triangle[r-1].length; c++) {
                triangle[r-1][c] += Math.max(triangle[r][c], triangle[r][c+1]);   
            }
        }
        
        return triangle[0][0];
    }
}