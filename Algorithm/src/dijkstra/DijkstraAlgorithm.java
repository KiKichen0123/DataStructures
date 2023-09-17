package dijkstra;

import java.util.Arrays;

/**
 * 该程序的说明如下：
 * 迪杰斯科拉算法-最短路径
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        final int N = 65535;
        int[][] matrix = new int[][]{
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N}
        };
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(6);
        graph.showDijkstra();
    }
}

class Graph{
    private char[] vertex;  //顶点数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showDijkstra(){
        vv.show();
    }

    //显示图
    public void showGraph(){
        for(int[] link:matrix){
            System.out.println(Arrays.toString(link));
        }
    }


    /**
     * @param index  表示出发顶点对应的下标
     */
    public void dsj(int index){
         vv = new VisitedVertex(vertex.length, index);
         update(index); //更新index顶点到周围顶点的距离和周围顶点的前驱顶点
        for(int j = 1;j < vertex.length;j++){
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index); //更新index顶点到周围顶点的距离和周围顶点的前驱顶点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index){
        int len = 0;
        for(int j = 0;j < matrix[index].length;j++){
            //出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if(!vv.in(j) && len < vv.getDis(j)){
                vv.updatePre(j,index);//更新j顶点的前驱为index顶点
                vv.updateDis(j,len);//更新出发顶点到j顶点的距离
            }
        }
    }
}

class VisitedVertex{
    //记录各个顶点是否访问过 1表示访问过，0 未访问，会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，会动态更新，求最短距离就会存放到dis
    public int[] dis;

    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis
        Arrays.fill(dis,65535);
        this.already_arr[index]=1;//设置出发顶点被访问过
        //设置出发顶点的访问距离为0
        this.dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index]=len;
    }

    /**
     * 更新pre这个顶点的前驱结点为index顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre]=index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点
     * @return
     */
    public int updateArr(){
        int min = 65535,index = 0;
        for(int i = 0;i < already_arr.length;i++){
            if(already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show(){
        System.out.println("=================================");
        for (int i :already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i :pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i :dis) {
            System.out.print(i + " ");
        }
        System.out.println("\n=================================");
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for(int i:dis){
            if(i != 65535){
                System.out.println(vertex[count] + "("+i+")");
            }else{
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}