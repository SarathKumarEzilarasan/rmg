package tests;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //[1,2,3,4,5,6,7,8] , x = 3 => [6,7,8,1,2,3,4,5] => old+ new* length
        //[49,58,67,4,5,6,7,8]
        //[6,7,8,1,2,3,4,5]


        //[6,7,8,4,5,6,7,8] => [

        int x = 100 % 8;
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int count = arr.length;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (x <= arr.length) {
            for (int i = 0; i < count - x; i++) { //0 to 4
                arrayList.add(arr[i]);
            }

            int j = 0;
            for (int i = count - x; i < arr.length; i++) {//5 to 7
                arrayList.add(j, arr[i]);
                j++;
            }
        }
        System.out.println(arrayList);
    }
}
