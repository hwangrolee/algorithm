package level1.신고결과받기;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/92334?language=java
 */

import java.util.*;

class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] id_list_1 = {"muzi", "frodo", "apeach", "neo"};
        String[] report_1 = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k1 = 2;

        int[] answer1 = solution.solution(id_list_1, report_1, k1);

        System.out.println("answer");
        for (int a : answer1) {
            System.out.println(a);
        }

        String[] id_list_2 = {"con", "ryan"};
        String[] report_2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k2 = 3;

        int[] answer2 = solution.solution(id_list_2, report_2, k2);
        System.out.println("answer");
        for (int a : answer2) {
            System.out.println(a);
        }
    }
}

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 신고했을떄에는 매번 받는다.
        // 신고 받았을 때에는 K번 이상이라면 1번만 이메일을 받는다.

        // key: 신고자, value: 신고 받은
        Map<String, Set<String>> reporterMap = new HashMap<>();

        // key: 신고 받은 자, value: 받은 횟수
        Map<String, Set<String>> reporteeMap = new HashMap<>();

        for (int i = 0; i < report.length; i++) {
            String r = report[i];
            String[] sp = r.split(" ");
            String reporter = sp[0];
            String reportee = sp[1];

            Set<String> reporteeList = reporterMap.getOrDefault(reporter, new HashSet<>());
            reporteeList.add(reportee);
            reporterMap.put(reporter, reporteeList);

            Set<String> reporterList = reporteeMap.getOrDefault(reportee, new HashSet<>());
            reporterList.add(reporter);
            reporteeMap.put(reportee, reporterList);
        }


        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            int result = 0;
            Set<String> reporteeList = reporterMap.getOrDefault(id, new HashSet<>());
            for (String reportee : reporteeList) {
                Set<String> reporterList = reporteeMap.getOrDefault(reportee, new HashSet<>());
                if (reporterList.size() >= k) {
                    result += 1;
                }
            }

            answer[i] = result;
        }

        return answer;
    }
}