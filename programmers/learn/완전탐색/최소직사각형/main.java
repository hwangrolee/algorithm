package learn.완전탐색.최소직사각형;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86491
 */
class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] case1 = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int answer1 = solution.solution(case1);
        System.out.println("answer1: " + answer1);

        int[][] case2 = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        int answer2 = solution.solution(case2);
        System.out.println("answer2: " + answer2);

        int[][] case3 = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
        int answer3 = solution.solution(case3);
        System.out.println("answer3: " + answer3);

    }
}

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxH = 0;
        int maxW = 0;
        for (int[] size : sizes) {
            int w = size[0];
            int h = size[1];

            if (w < h) {
                int temp = w;
                w = h;
                h = temp;
            }

            maxH = maxH > h ? maxH : h;
            maxW = maxW > w ? maxW : w;
        }
        answer = maxH * maxW;
        return answer;
    }
}