package jvm;

import java.util.ArrayList;
import java.util.List;

public class TestOOM {
    private static int arrSize = 1024*1024;
        public static void main(String[] args){
        int i =0;
            List<int[]> list = new ArrayList<>();
        while (++i>0){
            int[] arr = new int[arrSize];
            list.add(arr);
            echoMemory(i);
        }

    }

    private static void echoMemory(int i){
        long totalM =  Runtime.getRuntime().totalMemory()/1024/1024 ;
        long maxM =  Runtime.getRuntime().maxMemory()/1024/1024 ;
        long freeM =  Runtime.getRuntime().freeMemory()/1024/1024 ;
        System.out.println("i="+i+",totalM="+totalM+" MB,"+"maxM="+maxM+" MB,"+"freeM="+freeM+" MB");

    }
}
