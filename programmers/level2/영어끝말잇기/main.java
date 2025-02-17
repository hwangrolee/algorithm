package level2.영어끝말잇기;

import java.util.Arrays;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12981
 */
class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n1 = 3;
        String[] case1 = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] answer1 = solution.solution(n1, case1);
        System.out.println("answer: " + answer1);

        int n2 = 5;
        String[] case2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        int[] answer2 = solution.solution(n2, case2);
        System.out.println("answer: " + answer2);

        int n3 = 2;
        String[] case3 = {"hello", "one", "even", "never", "now", "world", "draw"};
        int[] answer3 = solution.solution(n3, case3);
        System.out.println("answer: " + answer3);

    }
}

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        List<String> wordList = Arrays.asList(words);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            String prevWord = words[i - 1];

            if (!prevWord.substring(prevWord.length() - 1, prevWord.length()).equals(word.substring(0, 1))) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }

            List<String> newList = wordList.subList(0, i);
            int index = newList.indexOf(word);
            if (index >= 0) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
        }

        return answer;
    }
}