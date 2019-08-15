package org.technicalad.ads.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 稀疏数组
 * @author: zhangyafeng1
 * @Date 2019-08-15 12:55:26
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        // 创建一个原始的二维数组 11*11 用来表示五子棋棋盘
        // 0：表示没有棋子，1：表示黑子 2：表示蓝子
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;// 这个位置有一颗黑子
        chessArr[2][3] = 2;// 这个位置有一颗蓝子
        // 输出原始的二维数组
        System.out.println("================原始数组================");
        for (int[] row : chessArr) {
            for(int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        String fileUrl = "sparsearray.txt";
        int[][] ints = twoDimensionalArrToSparseArr(chessArr);
        saveToFile(ints, fileUrl);
        int[][] ints1 = readFormFile(fileUrl);
        int[][] ints2 = sparseArrTotwoDimensionalArr(ints1);

    }

    private static int[][] twoDimensionalArrToSparseArr(int[][] twoDimensionalArr) {
        // 二维数组转稀疏数组
        // 1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                if(twoDimensionalArr[i][j] != 0) {
                    sum ++;
                }
            }
        }
        // 2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历二维数组，将非0的值存放到sparyArr中
        // count 用于记录是第几个非零的值
        int count = 0;
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                if(twoDimensionalArr[i][j] != 0) {
                    count ++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = twoDimensionalArr[i][j];
                }
            }
        }

        System.out.println("================稀疏数组================");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        return sparseArr;
    }

    private static int[][] sparseArrTotwoDimensionalArr(int[][] sparseArr) {
        int twoDimensionalArr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i = 1; i < sparseArr.length; i++) {
            twoDimensionalArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("================还原数组================");
        for (int[] row : twoDimensionalArr) {
            for(int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return twoDimensionalArr;
    }

    private static void saveToFile(int[][] array, String url) throws IOException {
        File file = new File(url);
        FileWriter fw = new FileWriter(file);
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                fw.write(array[i][j]+"\t");
            }
            if(i < array.length - 1) {
                fw.write(System.lineSeparator());
            }
        }
        fw.close();
    }

    private static int[][] readFormFile(String url) throws IOException {
        File file = new File(url);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        List<int[]> list = new ArrayList<>();
        while ((str = br.readLine()) != null) {
            String[] arr = str.split("\t");
            int[] intArr = new int[arr.length];
            for(int i = 0; i < arr.length; i++) {
                intArr[i] = Integer.valueOf(arr[i]);
            }
            list.add(intArr);
        }
        br.close();
        int sparseArr[][] = new int[list.size()][list.get(0).length];
        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < list.get(i).length; j++) {
                sparseArr[i][j] = list.get(i)[j];
            }
        }
        for (int[] row : sparseArr) {
            for(int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return sparseArr;
    }

}
