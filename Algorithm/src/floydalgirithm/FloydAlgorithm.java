package floydalgirithm;

import java.util.Arrays;

/**
 * 该程序的说明如下：
 * 弗洛伊德算法-各顶点到顶点之间的最短路径
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        final int N = 65535;
        int[][] matrix = new int[][]{
                {0,5,7,N,N,N,2},
                {5,0,N,9,N,N,3},
                {7,N,0,N,8,N,N},
                {N,9,N,0,N,4,N},
                {N,N,8,N,0,5,4},
                {N,N,N,4,5,0,6},
                {2,3,N,N,4,6,0}
        };

        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}

class Graph{
    private char[] vertex;
    private int[][] dis;
    private int[][] pre;    //保存到达目标顶点的前驱顶点

    public Graph(int length,int[][] matrix,char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组初始化，存放的是前驱顶点的下标
        for(int i = 0;i < length;i++){
            Arrays.fill(pre[i],i);
        }
    }

    public void floyd(){
        int len = 0;
        //对中间顶点遍历
        for(int k = 0;k < dis.length;k++){
            //从i顶点开始出发
            for(int i = 0;i< dis.length;i++){
                for(int j = 0;j < dis.length;j++){
                    len = dis[i][k] + dis[k][j]; //从i顶点出发 经过k中间顶点 到j顶点
                    if(len < dis[i][k]){
                        dis[i][j] = len;
                        pre[i][j]=pre[k][j];
                    }
                }
            }
        }

    }

    public void show(){
        char[] vertex = {'A','B','C','D','E','F','G'};
        for(int k = 0;k < dis.length;k++){
            //pre
            for(int i = 0;i < dis.length;i++){
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();

            //dis
            for(int i = 0;i < dis.length;i++){
                System.out.print("("+vertex[k] + "到" + vertex[i] + "最短路径是" + dis[k][i] + " )");
            }
            System.out.println();
            System.out.println();
        }
    }


}
