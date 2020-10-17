package matrix;

public class RotateBy90
{

    public void rotate(int[][] matrix)
    {
        // convert it into its transpose matrix
        // then sort it row wise.
        int diagonal =  0;
        int i = 0;
        int j = 0;
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        while(i < rowLen && j < colLen) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
            if(j+1 == colLen) {
                ++diagonal;
                i = diagonal;
                j = diagonal;
            }
            ++j;
        }
        i = 0;
        while(i < matrix.length ) {
            int start = 0;
            int end = matrix[i].length -1;
            while(start < end) {
                int temp = matrix[i][end];
                matrix[i][end] = matrix[i][start];
                matrix[i][start] = temp;
                ++start;
                --end;
            }
            ++i;
        }
        
        for(i = 0 ; i< matrix.length;i++) {
            for(j = 0 ; j < matrix[0].length ; j++) {
                System.out.print(matrix[i][j]+"-");
            } 
            System.out.println();
        }

    }

    public static void main(String[] args)
    {

        int mat[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        new RotateBy90().rotate(mat);
        
    }

}
