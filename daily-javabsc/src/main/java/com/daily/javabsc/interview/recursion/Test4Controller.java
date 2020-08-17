package com.daily.javabsc.interview.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rocia
 * @description :
 * @CreateTime 2018-08-17-13:41
 */
public class Test4Controller {


    public void combinations(List<Integer> selected, List<Integer> data, int n) {
        if (n == 0) {

            for (Integer in : selected) {
                System.out.print(in + " ");
            }
            System.out.println();
            return;
        }
        if (data.isEmpty()) {
            return;
        }

        //选择第一个节点
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n - 1);
        //不选第一个节点
        selected.remove(selected.size() - 1);
        combinations(selected, data.subList(1, data.size()), n);
    }

    public static void main(String[] a) {
        Test4Controller controller = new Test4Controller();

        controller.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 2);


    }


}
