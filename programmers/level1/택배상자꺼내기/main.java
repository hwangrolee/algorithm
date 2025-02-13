package level1.택배상자꺼내기;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/389478
 */
class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] testcase = {
                {22, 6, 8},
                {13, 3, 6}
        };

        for (int i = 0; i < testcase.length ; i++) {
            int answer = solution.solution(testcase[i][0], testcase[i][1], testcase[i][2]);
            System.out.println("answer: " + answer);
        }

    }
}

class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int maxRowCount = (int) Math.ceil((float) n / (float) w);
        int rowNumOfTargetNum = (int) Math.ceil((float) num / (float) w);
        //  홀수 인지 파악 :: 홀수 일때와 짝수일때 구하는 공식이 다름.
        boolean isOddRowNumber = rowNumOfTargetNum % 2 == 1;

        int cellNumber;
        if (isOddRowNumber) {
            // 홀수 일때
            cellNumber = Math.max(1, num - (w * (rowNumOfTargetNum - 1)));
            /*
                num = 13 일때
                rowNumOfTargetNum = 3

                w * (rowNumOfTargetNum - 1) : 12 - 2번쨰 줄 제일 큰 수
                num - 12 = 1

                답 : 1
             */
        } else {
            // 짝수 일때
            /*
                2번쟤 로우에 있다는건 짝수 행에 있다. 그렇기 때문에 w * numberOfTargetNum = 12 - 8 + 1(인덱스 맞추기) = 5
            */
            cellNumber = Math.max(1, w * rowNumOfTargetNum - num + 1);
        }

        // 최대 로우 수와 같은 셀과 최대 로우 수보다 1 낮은 셀 범위를 구한다.
        int maxNumber = maxRowCount * w;
        int minus = maxNumber - n;
        if (maxRowCount % 2 == 0) {
            // 짝수 인 경우
            if (cellNumber > minus) {
                answer = maxRowCount - rowNumOfTargetNum;
            } else {
                answer = maxRowCount - rowNumOfTargetNum - 1;
            }
        } else {
            // 홀수 인 경우
            if (cellNumber > w - minus) {
                answer = maxRowCount - rowNumOfTargetNum - 1;
            } else {
                answer = maxRowCount - rowNumOfTargetNum;
            }
        }

        answer += 1;

        /*

        각 로우 맥스값 구하는 공식
        1열 : 6 (w 그대로 사용)
        2열 : 12 (w * 2열)
        3열 : 18 (w * 3열)
        4열 : 24 (w * 24)

        maxRowCount : 4 인데 여기서 로우수가 3인 셀과 4인 셀을 구해야한다.

        maxRowCount : 4 로 짝수인 경우 최대값(24) - n(22) = 2 ----> 1,2 셀이 3이고 나머지는 4이다
        maxRowCount : 6 로 짝수인 경우 최대값(36) - n(35) = 1 ----> 1 셀이 5이고 나머지는 6이다.
        만약 maxRowCount : 5이고 홀수인경우 최대값(30) - n(25) = 5 ----> w(6) - 5 -------> 1셀만 5rows, 나머지는 4rows
         만약 maxRowCount : 5이고 홀수인경우 최대값(30) - n(26) = 4 ----> w(6) - 4 -------> 1,2셀만 5 rows, 나머지는 4rows


         */


        return answer;
    }
}