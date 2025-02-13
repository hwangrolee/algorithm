/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/120956
 */
class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[][] testcase = {
                {"aya", "yee", "u", "maa", "wyeoo"},
                {"ayaye", "uuuma", "ye", "yemawoo", "ayaa"}
        };

        for (int i = 0; i < testcase.length ; i++) {
            int answer = solution.solution(testcase[i]);
            System.out.println("answer: " + answer);
        }

    }
}

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;

        for (int i = 0; i < babbling.length ; i++) {
            String result = babbling[i].replaceAll("aya|ye|woo|ma", "");
            if (result.length() == 0) {
                answer += 1;
            }
        }

        return answer;
    }
}