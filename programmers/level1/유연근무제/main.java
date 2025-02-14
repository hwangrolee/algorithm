package level1.유연근무제;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/388351
 */
class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] schedules = {700, 800, 1100};
        int[][] timelogs = {{710, 2359, 1050, 700, 650, 631, 659}, {800, 801, 805, 800, 759, 810, 809}, {1105, 1001, 1002, 600, 1059, 1001, 1100}};
        int startday = 5;

        int answer = solution.solution(schedules, timelogs, startday);
        System.out.println("answer: " + answer);

//        int[] schedules2 = {730, 855, 700, 720};
//        int[][] timelogs2 = {{710, 700, 650, 735, 700, 931, 912}, {908, 901, 805, 815, 800, 831, 835}, {705, 701, 702, 705, 710, 710, 711}, {707, 731, 859, 913, 934, 931, 905}};
//        int startday2 = 1;
//        int answer2 = solution.solution(schedules2, timelogs2, startday2);
//        System.out.println("answer: " + answer2);
//
//        int[] schedules3 = {759};
//        int[][] timelogs3 = {{700, 755, 750, 809, 809, 900, 900}};
//        int startday3 = 1;
//        int answer3 = solution.solution(schedules3, timelogs3, startday3);
//        System.out.println("answer: " + answer3);

    }
}

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = schedules.length;
        int saturdayIndex = (6 - startday) >= 0 ? (6 - startday) : 6; // 토요일을 가리키는 index 찾기
        int sundayIndex = saturdayIndex == 6 ? 0 : saturdayIndex + 1; // 토요일 + 1 or 0
        /*
                1은 월요일, 2는 화요일, 3은 수요일, 4는 목요일, 5는 금요일, 6은 토요일, 7은 일요일
                기본 : 0: 월, 1: 화, 2: 수, 3: 목, 4: 금, 5: 토, 6: 일

                startday: 1 라면
                    - 토요일 인덱스: 5, 일요일 인덱스: 6
                    - 0: 월, 1: 화, 2: 수, 3: 목, 4: 금, 5: 토, 6: 일
                startday: 2 라면
                    - 토요일 인덱스: 4, 일요일 인덱스: 5
                    - 1: 화, 2: 수, 3: 목, 4: 금, 5: 토, 6: 일, 0: 월
                startday: 3 라면
                    - 토요일 인덱스: 3, 일요일 인덱스: 4
                    - 2: 수, 3: 목, 4: 금, 5: 토, 6: 일, 0: 월, 1: 화
                startday: 4 라면
                    - 토요일 인덱스: 2, 일요일 인덱스: 3
                    - 3: 목, 4: 금, 5: 토, 6: 일, 0: 월, 1: 화, 2: 수
                startday: 5 라면
                    - 토요일 인덱스: 1, 일요일 인덱스: 2
                    - 4: 금, 5: 토, 6: 일, 0: 월, 1: 화, 2: 수, 3: 목
                startday: 6 라면
                    - 토요일 인덱스: 6, 일요일 인덱스: 0
                    - 5: 토, 6: 일, 0: 월, 1: 화, 2: 수, 3: 목, 4: 금
                startday: 7 라면
                    - 토요일 인덱스: 0, 일요일 인덱스: 0
                    - 6: 일, 0: 월, 1: 화, 2: 수, 3: 목, 4: 금, 5: 토
         */

        for (int i = 0; i < schedules.length; i++) {
            int schedule = schedules[i];
            int newSchedule = (schedule / 100) * 60 + (schedule % 100) + 10;

            for (int j = 0; j < timelogs[i].length; j++) {

                if (saturdayIndex == j || sundayIndex == j) {
                    continue;
                }

                int newTimelog = (timelogs[i][j] / 100) * 60 + (timelogs[i][j] % 100);
                if (newSchedule < newTimelog) {
                    answer -= 1;
                    break;
                }
            }
        }

        return answer;
    }
}