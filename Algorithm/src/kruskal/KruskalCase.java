package kruskal;

import java.util.Arrays;

/**
 * 该程序的说明如下：
 * 克鲁斯卡尔算法
 */
public class KruskalCase {
    private int edgeNum;    //边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    //使用 INF 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A','B','C','D','E','F','G'};
        int matrix[][] ={
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        System.out.println("排序前："+Arrays.toString(edges));
        kruskalCase.sortEdges(edges);
        System.out.println("排序后："+Arrays.toString(edges));
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;

        //初始化顶点
        this.vertexs = new char[vlen];
        for(int i = 0;i < vertexs.length;i++){
            this.vertexs[i] = vertexs[i];
        }

        //初始化边
        this.matrix = new int[vlen][vlen];
        for(int i = 0;i<vlen;i++){
            for(int j = 0;j < vlen;j++){
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边
        for(int i = 0;i < vlen;i++){
            for(int j = i+1 ;j < vlen;j++){
                if(this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }

    public void print(){
        System.out.println("邻接矩阵为：\n");
        for(int i = 0;i < vertexs.length;i++){
            for(int j = 0;j < vertexs.length;j++){
                System.out.printf("%d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 功能：对边进行排序处理，冒泡排序
     * @param edges 边的集合
     */
    public void sortEdges(EData[] edges){
        for(int i = 0;i < edges.length-1;i++){
            for(int j = 0;j < edges.length-1-i;j++){
                if(edges[j].weight > edges[j+1].weight){
                    EData tmp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1]=tmp;
                }
            }
        }
    }

    /**
     * @param ch    顶点的值
     * @return  返回ch顶点对应的下标，如果找不到，返回-1
     */
    private int getPosition(char ch){
        for(int i = 0;i < vertexs.length;i++){
            if(vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for(int i = 0;i < vertexs.length;i++){
            for(int j = i+1;j< vertexs.length;j++){
                if(matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends  数组，记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成
     * @param i 表示传入的顶点对应的下标
     * @return  返回的就是 下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while(ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    public void kruskal(){
        int index = 0;  //表示最后结果数组的索引
        int[] ends = new int[edgeNum];  //用于保存“已有最小生成树”终点额每个顶点在最小生成树中的终点
        //保存最小的生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有的边的集合
        EData[] edges = getEdges();
        //排序
        sortEdges(edges);

        //将边添加到最小生成树中，判断是准备加入的边形成了回路，如果没有，就加入rets
        for(int i = 0;i < edgeNum;i++){
            //获取起点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends,p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends,p2);
            //是否构成回路
            if(m != n){
                ends[m]=n;  //设置m 在“已有最小生成树”中的终点 <E,F>
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树为=" + Arrays.toString(rets));
    }
}

//EDate,对象实例是一条边
class EData{
    char start; //边的一个点
    char end;   //边的另一个点
    int weight; //边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

}
