package level1.가장많이받은선물;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/258712
 */
class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[][][] testcase = {
                {{"muzi", "ryan", "frodo", "neo"}, {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"}},
                {{"joy", "brad", "alessandro", "conan", "david"}, {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"}},
                {{"a", "b", "c"}, {"a b", "b a", "c a", "a c", "a c", "c a"}}
        };

        for (int i = 0; i < testcase.length ; i++) {
            int answer = solution.solution(testcase[i][0], testcase[i][1]);
            System.out.println((i + 1) + ". answer: " + answer);
        }

    }
}

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        List<String> friendList= Arrays.asList(friends);

        // 선물을 준 친구 이름이 Key
        // 선물을 받은 친구 이름이 리스트 형태로 저장
        Map<String, List<String>> senderMap = new HashMap<>();
        Map<String, Map<String, Integer>> sameMap = new HashMap<>();

        // 선물을 받은 친구 이름이 Key
        // 선물을 준 친구 이름을 리스트 형태로 저장
        Map<String, List<String>> receiverMap = new HashMap<>();

        Map<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < gifts.length; i++) {
            String gift = gifts[i];
            String[] sp = gift.split(" ");
            String sender = sp[0];
            String receiver = sp[1];

            if (friendList.contains(sender) == false || friendList.contains(receiver) == false ) {
                // 선물을 주고 받은 친구 중 friends 목록에 없다면 다음 선물 파악.
                continue;
            }

            // 선물을 받은 친구 목록
            List<String> receiverList = senderMap.getOrDefault(sender, new ArrayList<>());
            receiverList.add(receiver);
            senderMap.put(sender, receiverList);

            // 선물을 받은 친구가 지금 선물을 준 친구에게 준적이 있는지 확인
            List<String> a = senderMap.getOrDefault(receiver, new ArrayList<>());


            Map<String, Integer> m1 = sameMap.getOrDefault(sender, new HashMap<>());
            m1.put(receiver, m1.getOrDefault(receiver, 0) + 1);
            sameMap.put(sender, m1);

            Map<String, Integer> m2 = sameMap.getOrDefault(receiver, new HashMap<>());
            m2.put(sender, m2.getOrDefault(sender, 0) + 1);
            sameMap.put(receiver, m2);

            // 선물을 준 친구 목록
            List<String> senderList = receiverMap.getOrDefault(receiver, new ArrayList<>());
            senderList.add(sender);
            receiverMap.put(receiver, senderList);

            int senderIndex = indexMap.getOrDefault(sender, 0);
            senderIndex += 1;
            indexMap.put(sender, senderIndex);

            int receiverIndex = indexMap.getOrDefault(receiver, 0);
            receiverIndex -= 1;
            indexMap.put(receiver, receiverIndex);
        }

        int result = Integer.MIN_VALUE;
        for (String sender : senderMap.keySet()) {
            int defaultIndex = indexMap.get(sender);
            int index = 0;

            List<String> receiverList = senderMap.getOrDefault(sender, new ArrayList<>());
            Set<String> uniqueReceiverList = new HashSet<>(receiverList);
            Map<String, Integer> sameData = sameMap.getOrDefault(sender, new HashMap<>());

            // 선물을 받은 친구 목록을 순회 :: 선물을 주고받은 기록이 있음.
            for (String receiver : uniqueReceiverList) {
                // 선물 받은 친구가 sender 에게 받은 선물 수
                int giftAmountByReceiver = (int) receiverList.stream().filter(r -> r.equalsIgnoreCase(receiver)).count();

                // 선물을 보낸 친구가 받은 친구로부터 선물을 받은 수
                int giftAmountBySender = (int) senderMap.getOrDefault(receiver, new ArrayList<>()).stream().filter(r -> r.equalsIgnoreCase(sender)).count();
                if (giftAmountBySender < giftAmountByReceiver) {
                    index += 1;
                } else if (giftAmountBySender == giftAmountByReceiver) {
                    // 주고 받은 수가 같을때
                    int tempDefaultIndex = indexMap.getOrDefault(receiver, Integer.MIN_VALUE);
                    if (defaultIndex > tempDefaultIndex) {
                        index += 1;
                    }

                }
            }

            List<String> copiedFriendList = new ArrayList<>(friendList);

            copiedFriendList.remove(sender);
            copiedFriendList.removeAll(sameData.keySet());
            for (String temp: copiedFriendList) {
                int tempDefaultIndex = indexMap.getOrDefault(temp, Integer.MIN_VALUE);
                if (defaultIndex > tempDefaultIndex) {
                    index += 1;
                }
            }

            result = Math.max(result, index);
        }

        answer = result;
        return answer;
    }
}