package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 周赛 2019年1月5日  5305. 获取你好友已观看的视频
 * <p>
 * 有 n 个人，每个人都有一个  0 到 n-1 的唯一 id 。
 * <p>
 * 给你数组 watchedVideos  和 friends ，其中 watchedVideos[i]  和 friends[i] 分别表示 id = i 的人观看过的视频列表和他的好友列表。
 * <p>
 * Level 1 的视频包含所有你好友观看过的视频，level 2 的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 k 的视频包含所有从你出发，最短距离为 k 的好友观看过的视频。
 * <p>
 * 给定你的 id  和一个 level 值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按名字字典序从小到大排列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
 * 输出：["B","C"]
 * 解释：
 * 你的 id 为 0 ，你的朋友包括：
 * id 为 1 -> watchedVideos = ["C"]
 * id 为 2 -> watchedVideos = ["B","C"]
 * 你朋友观看过视频的频率为：
 * B -> 1
 * C -> 2
 * <p>
 * 示例 2：
 * <p>
 * 输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
 * 输出：["D"]
 * 解释：
 * 你的 id 为 0 ，你朋友的朋友只有一个人，他的 id 为 3 。
 * <p>
 * 提示：
 * <p>
 * n == watchedVideos.length == friends.length
 * 2 <= n <= 100
 * 1 <= watchedVideos[i].length <= 100
 * 1 <= watchedVideos[i][j].length <= 8
 * 0 <= friends[i].length < n
 * 0 <= friends[i][j] < n
 * 0 <= id < n
 * 1 <= level < n
 * 如果 friends[i] 包含 j ，那么 friends[j] 包含 i
 *
 * @author zm
 */
public class LEET5305 {
    public static void main(String[] args) {
        LEET5305 l5305 = new LEET5305();
        String[][] watchedVideos = {{"A", "B"}, {"C"}, {"B", "C"}, {"D"}};
        int[][] friends = {{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        System.out.println(JSON.toJSONString(l5305.watchedVideosByFriends(CommonFunctions.stringArrayToList(watchedVideos), friends, 0, 1)));
        System.out.println(JSON.toJSONString(l5305.watchedVideosByFriends(CommonFunctions.stringArrayToList(watchedVideos), friends, 0, 2)));
        String[][] watchedVideos2 = {{"m"}, {"xaqhyop", "lhvh"}};
        int[][] friends2 = {{1}, {0}};
        System.out.println(JSON.toJSONString(l5305.watchedVideosByFriends(CommonFunctions.stringArrayToList(watchedVideos2), friends2, 1, 1)));
        String[][] watchedVideos3 = {{"bjwtssmu"}, {"aygr", "mqls"}, {"vrtxa", "zxqzeqy", "nbpl", "qnpl"}, {"r", "otazhu", "rsf"}, {"bvcca", "ayyihidz", "ljc", "fiq", "viu"}};
        int[][] friends3 = {{3, 2, 1, 4}, {0, 4}, {4, 0}, {0, 4}, {2, 3, 1, 0}};
        System.out.println(JSON.toJSONString(l5305.watchedVideosByFriends(CommonFunctions.stringArrayToList(watchedVideos3), friends3, 3, 1)));
    }

    /**
     * 还未完成
     *
     * @param watchedVideos
     * @param friends
     * @param id
     * @param level
     * @return
     */
    // TODO
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        HashMap<Integer, Integer> map = new HashMap();
        map.put(id, -1);
        for (int i = 0; i < level; i++) {
            HashMap<Integer, Integer> newMap = new HashMap();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int[] hisFriends = friends[entry.getKey()];
                for (int j = 0; j < hisFriends.length; j++) {
                    if (hisFriends[j] != entry.getValue()) {
                        newMap.put(hisFriends[j], entry.getKey());
                    }
                }
            }
            map = newMap;
        }
        //System.out.println(JSON.toJSONString(map));

        HashMap<String, Integer> watchedVideosMap = new HashMap<>();
        for (int friendId : map.keySet()) {
            for (int i = 0; i < watchedVideos.get(friendId).size(); i++) {
                watchedVideosMap.put(watchedVideos.get(friendId).get(i), watchedVideosMap.getOrDefault(watchedVideos.get(friendId).get(i), 0) + 1);
            }
        }
        //System.out.println(JSON.toJSONString(watchedVideosMap));
        ArrayList<Map.Entry<String, Integer>> retTempList = new ArrayList<>();
        for (Map.Entry<String, Integer> watchedVideosSet : watchedVideosMap.entrySet()) {
            retTempList.add(watchedVideosSet);
        }
        retTempList.sort((o1, o2) -> {
            if (o1.getValue() == o2.getValue()) {
                char[] chars1 = o1.getKey().toCharArray();
                char[] chars2 = o2.getKey().toCharArray();
                int i = 0;
                for (; i < chars1.length; i++) {
                    if (i == chars2.length) {
                        return 1;
                    }
                    if (chars1[i] > chars2[i]) {
                        return 1;
                    } else if (chars1[i] < chars2[i]) {
                        return -1;
                    }
                }
                if (i < chars2.length) {
                    return -1;
                }
                return 0;
            }
            return o1.getValue() > o2.getValue() ? 1 : -1;
        });
        ArrayList<String> retList = new ArrayList<>();
        for (int i = 0; i < retTempList.size(); i++) {
            retList.add(retTempList.get(i).getKey());
        }
        return retList;
    }

}
