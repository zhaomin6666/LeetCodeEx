package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.*;

/**
 * 355. 设计推特
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 * <p>
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 * 示例:
 * <p>
 * Twitter twitter = new Twitter();
 * <p>
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 * <p>
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 * <p>
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 * <p>
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 *
 * @author zm
 */
public class LEET355 {
    public static void main(String[] args) {
        LEET355 l355 = new LEET355();
        Twitter2 obj = l355.new Twitter2();
        obj.postTweet(1, 5);
        System.out.println(obj.getNewsFeed(1));
        obj.follow(1, 2);
        obj.postTweet(2, 6);
        System.out.println(obj.getNewsFeed(1));
        obj.unfollow(1, 2);
        System.out.println(obj.getNewsFeed(1));
    }

    /**
     * 最简单的一种实现
     */
    class Twitter {

        private HashMap<Integer, HashSet<Integer>> everyonesFollow;

        private LinkedList<int[]> twitters;

        /**
         * Initialize your data structure here.
         */
        public Twitter() {
            everyonesFollow = new HashMap<>();
            twitters = new LinkedList<>();
        }

        /**
         * Compose a new tweet.
         */
        public void postTweet(int userId, int tweetId) {
            twitters.addFirst(new int[]{userId, tweetId});
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> list = new LinkedList<>();
            HashSet<Integer> followSet = everyonesFollow.get(userId);
            for (int[] tweet : twitters) {
                if ((followSet != null && followSet.contains(tweet[0])) || tweet[0] == userId) {
                    list.add(tweet[1]);
                    if (list.size() >= 10) {
                        return list;
                    }
                }
            }
            return list;
        }

        /**
         * Follower follows a followee. If the operation is invalid, it should be a no-op.
         */
        public void follow(int followerId, int followeeId) {
            HashSet<Integer> followSet = everyonesFollow.getOrDefault(followerId, new HashSet<>());
            followSet.add(followeeId);
            everyonesFollow.put(followerId, followSet);
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            HashSet<Integer> followSet = everyonesFollow.get(followerId);
            if (followSet != null) {
                followSet.remove(followeeId);
                if (followSet.isEmpty()) {
                    everyonesFollow.remove(followerId);
                }
                everyonesFollow.put(followerId, followSet);
            }
        }
    }

    /**
     * 每个用户发的推特单独保存，使用优先队列获取所有关注者的前十条
     */
    class Twitter2 {
        private int timestamp = 0;

        private HashMap<Integer, Tweet> userTwitterMap;

        private HashMap<Integer, HashSet<Integer>> everyonesFollow;

        public Twitter2() {
            everyonesFollow = new HashMap<>();
            userTwitterMap = new HashMap<>();
        }

        /**
         * user 发表一条 tweet 动态
         */
        public void postTweet(int userId, int tweetId) {
            Tweet newTweet = new Tweet(tweetId, timestamp++);
            Tweet tweet = userTwitterMap.get(userId);
            if (tweet != null) {
                newTweet.next = tweet;
            }
            userTwitterMap.put(userId, newTweet);
        }

        public void follow(int followerId, int followeeId) {
            HashSet<Integer> followSet = everyonesFollow.getOrDefault(followerId, new HashSet<>());
            if (followerId != followeeId) {
                followSet.add(followeeId);
                everyonesFollow.put(followerId, followSet);
            }
        }

        public void unfollow(int followerId, int followeeId) {
            HashSet<Integer> followSet = everyonesFollow.get(followerId);
            if (followSet != null) {
                followSet.remove(followeeId);
                everyonesFollow.put(followerId, followSet);
            }
        }

        /**
         * 返回该 user 关注的人（包括他自己）最近的动态 id，
         * 最多 10 条，而且这些动态必须按从新到旧的时间线顺序排列。
         * 把所有关注人的tweet的头结点（最新一条）放入优先队列，
         * 然后每次取出所有关注人最新一条tweet后把这条twitter的下一个结点放入优先队列
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            // 关注列表的用户 Id
            Set<Integer> myFollows = everyonesFollow.get(userId);
            if (myFollows == null) {
                myFollows = new HashSet<>();
            }
            // 自动通过 time 属性从大到小排序，容量为 users 的大小加上自己
            PriorityQueue<Tweet> pq =
                    new PriorityQueue<>(myFollows.size() + 1, (a, b) -> (b.time - a.time));

            // 先将所有链表头节点插入优先级队列
            for (int id : myFollows) {
                Tweet twt = userTwitterMap.get(id);
                if (twt == null) {
                    continue;
                }
                pq.add(twt);
            }
            Tweet myTwt = userTwitterMap.get(userId);
            if (myTwt != null) {
                pq.add(myTwt);
            }

            while (!pq.isEmpty()) {
                // 最多返回 10 条就够了
                if (res.size() == 10) {
                    break;
                }
                // 弹出 time 值最大的（最近发表的）
                Tweet twt = pq.poll();
                res.add(twt.id);
                // 将下一篇 Tweet 插入进行排序
                if (twt.next != null) {
                    pq.add(twt.next);
                }
            }
            return res;
        }

        class Tweet {
            private int id;
            private int time;
            private Tweet next;

            // 需要传入推文内容（id）和发文时间
            public Tweet(int id, int time) {
                this.id = id;
                this.time = time;
                this.next = null;
            }
        }
    }

    /**
     * 把方法二实现为面向对象
     */
    class Twitter3 {
        private int timestamp = 0;

        // 我们需要一个映射将 userId 和 User 对象对应起来
        private HashMap<Integer, User> userMap = new HashMap<>();

        /**
         * user 发表一条 tweet 动态
         */
        public void postTweet(int userId, int tweetId) {
            // 若 userId 不存在，则新建
            if (!userMap.containsKey(userId)) {
                userMap.put(userId, new User(userId));
            }
            User u = userMap.get(userId);
            u.post(tweetId);
        }

        /**
         * follower 关注 followee
         */
        public void follow(int followerId, int followeeId) {
            // 若 follower 不存在，则新建
            if (!userMap.containsKey(followerId)) {
                User u = new User(followerId);
                userMap.put(followerId, u);
            }
            // 若 followee 不存在，则新建
            if (!userMap.containsKey(followeeId)) {
                User u = new User(followeeId);
                userMap.put(followeeId, u);
            }
            userMap.get(followerId).follow(followeeId);
        }

        /**
         * follower 取关 followee，如果 Id 不存在则什么都不做
         */
        public void unfollow(int followerId, int followeeId) {
            if (userMap.containsKey(followerId)) {
                User flwer = userMap.get(followerId);
                flwer.unfollow(followeeId);
            }
        }

        /**
         * 返回该 user 关注的人（包括他自己）最近的动态 id，
         * 最多 10 条，而且这些动态必须按从新到旧的时间线顺序排列。
         * 把所有关注人的tweet的头结点（最新一条）放入优先队列，
         * 然后每次取出所有关注人最新一条tweet后把这条twitter的下一个结点放入优先队列
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            if (!userMap.containsKey(userId)) {
                return res;
            }
            // 关注列表的用户 Id
            Set<Integer> users = userMap.get(userId).followed;
            // 自动通过 time 属性从大到小排序，容量为 users 的大小
            PriorityQueue<Tweet> pq =
                    new PriorityQueue<>(users.size(), (a, b) -> (b.time - a.time));

            // 先将所有链表头节点插入优先级队列
            for (int id : users) {
                Tweet twt = userMap.get(id).head;
                if (twt == null) {
                    continue;
                }
                pq.add(twt);
            }

            while (!pq.isEmpty()) {
                // 最多返回 10 条就够了
                if (res.size() == 10) {
                    break;
                }
                // 弹出 time 值最大的（最近发表的）
                Tweet twt = pq.poll();
                res.add(twt.id);
                // 将下一篇 Tweet 插入进行排序
                if (twt.next != null) {
                    pq.add(twt.next);
                }
            }
            return res;
        }


        class User {
            private int id;
            public Set<Integer> followed;
            // 用户发表的推文链表头结点
            public Tweet head;

            public User(int userId) {
                followed = new HashSet<>();
                this.id = userId;
                this.head = null;
                // 关注一下自己
                follow(id);
            }

            public void follow(int userId) {
                followed.add(userId);
            }

            public void unfollow(int userId) {
                // 不可以取关自己
                if (userId != this.id) {
                    followed.remove(userId);
                }
            }

            public void post(int tweetId) {
                Tweet twt = new Tweet(tweetId, timestamp);
                timestamp++;
                // 将新建的推文插入链表头
                // 越靠前的推文 time 值越大
                twt.next = head;
                head = twt;
            }
        }

        class Tweet {
            private int id;
            private int time;
            private Tweet next;

            // 需要传入推文内容（id）和发文时间
            public Tweet(int id, int time) {
                this.id = id;
                this.time = time;
                this.next = null;
            }
        }
    }
}
