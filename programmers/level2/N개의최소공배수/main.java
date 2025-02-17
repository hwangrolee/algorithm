package level2.N개의최소공배수;

import java.util.*;

/**
 *
 */
class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int answer = solution.solution(new int[]{14, 8, 6, 2});
        System.out.println("answer: " + answer);

        int answer2 = solution.solution(new int[]{1, 2, 3});
        System.out.println("answer: " + answer2);

    }
}


class Solution {

    public int solution(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = lcm(result, arr[i]);
        }

        return result;

    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}