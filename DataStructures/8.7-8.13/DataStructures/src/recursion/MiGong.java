package recursion;

import java.util.ArrayList;

/**
 * 该程序的说明如下：
 */
public class MiGong {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        //二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //0 路
        //1 墙
        //2 标记路
        //上下墙
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("地图的情况：");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(list.size());
        boolean b = setWay(map, 1, 1, list);

        System.out.println("小球走过，并标识过的地图的情况：");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        if (b) {
            System.out.println("找到路径为" + list);
        } else {
            System.out.println("没有最短路径");
        }
    }

    public static boolean setWay(int[][] map, int i, int j, ArrayList list) {
        if (map[6][5] == 2) { //已到目标点
            list.add("(" + i + "," + j + ")");
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                list.add("(" + i + "," + j + ")");
                if (setWay(map, i + 1, j, list)) {  //下
                    return true;
                } else if (setWay(map, i, j + 1, list)) {  //右
                    return true;
                } else if (setWay(map, i - 1, j, list)) {  //上
                    return true;
                } else if (setWay(map, i, j - 1, list)) {  //左
                    return true;
                } else {
                    map[i][j] = 3;
                    list.remove(list.size()-1);
                    return false;
                }
            } else {  //如果map[i][j] != 0，可能是1，2，3
                return false;
            }
        }
    }

    public static boolean setWay2(int[][] map, int i, int j,ArrayList list) {
        if (map[6][5] == 2) { //已到目标点
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                list.add(map[i][j]);
                if (setWay2(map, i -1, j,list)) {  //上
                    return true;
                } else if (setWay2(map, i, j + 1,list)) {  //右
                    return true;
                } else if (setWay2(map, i + 1, j,list)) {  //下
                    return true;
                } else if (setWay2(map, i, j - 1,list)) {  //左
                    return true;
                } else {
                    map[i][j] = 3;
                    list.remove(map[i][j]);
                    return false;
                }
            } else {  //如果map[i][j] != 0，可能是1，2，3
                return false;
            }
        }
    }
}