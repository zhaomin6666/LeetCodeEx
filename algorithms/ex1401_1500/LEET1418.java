package com.zm.LeetCodeEx.algorithms.ex1401_1500;

import com.zm.LeetCodeEx.CommonFunctions;

import java.util.*;

/**
 * 1418. 点菜展示表
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 * <p>
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
 * <p>
 * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * 解释：
 * 点菜展示表如下所示：
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
 * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
 * 餐桌 10：Corina 点了 "Beef Burrito"
 * 示例 2：
 * <p>
 * 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
 * 输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
 * 解释：
 * 对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
 * 而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
 * 示例 3：
 * <p>
 * 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
 * 输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= orders.length <= 5 * 10^4
 * orders[i].length == 3
 * 1 <= customerNamei.length, foodItemi.length <= 20
 * customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
 * tableNumberi 是 1 到 500 范围内的整数。
 *
 * @author zm
 */
public class LEET1418 {
    public static void main(String[] args) {
        System.out.println(new Solution2().displayTable(
                CommonFunctions.stringArrayToList(
                        new String[][]{{"David", "3", "Ceviche"}, {"Corina", "10", "Beef Burrito"},
                                {"David", "3", "Fried Chicken"}, {"Carla", "5", "Water"},
                                {"Carla", "5", "Ceviche"}, {"Rous", "3", "Ceviche"}})));
    }

    static class Solution {
        public List<List<String>> displayTable(List<List<String>> orders) {
            TreeSet<String> food = new TreeSet<>();
            HashMap<String, Integer>[] tables = new HashMap[501];
            for (List<String> order : orders) {
                int tableNo = Integer.parseInt(order.get(1));
                if (tables[tableNo] == null) {
                    tables[tableNo] = new HashMap<>();
                }
                food.add(order.get(2));
                tables[tableNo].put(order.get(2), tables[tableNo].getOrDefault(order.get(2), 0) + 1);
            }
            List<List<String>> orderByTableList = new ArrayList<>();
            List<String> firstRow = new ArrayList<>();
            firstRow.add("Table");
            firstRow.addAll(food);
            orderByTableList.add(firstRow);
            int i = 0;
            for (HashMap<String, Integer> table : tables) {
                if (table != null) {
                    List<String> orderByTable = new ArrayList<>();
                    orderByTable.add(i + "");
                    for (String f : food) {
                        orderByTable.add(table.getOrDefault(f, 0).toString());
                    }
                    orderByTableList.add(orderByTable);
                }
                i++;
            }
            return orderByTableList;
        }
    }

    /**
     * 使用HashMap套HashMap方式保存
     */
    static class Solution2 {
        public List<List<String>> displayTable(List<List<String>> orders) {
            // 保存菜单信息，桌子id在map的key中有
            // HashSet<Integer> tableIds = new HashSet<>();
            HashSet<String> foodNames = new HashSet<>();
            HashMap<String, HashMap<String, Integer>> tableAndOrder = new HashMap<>(orders.size());
            for (List<String> order : orders) {
                HashMap<String, Integer> tableOrder = tableAndOrder.computeIfAbsent(
                        order.get(1), e -> new HashMap<>(orders.size()));
                tableOrder.compute(order.get(2), (k, v) -> v == null ? 1 : v + 1);
                // tableIds.add(Integer.valueOf(order.get(1)));
                foodNames.add(order.get(2));
            }
            // 排序
            List<String> tableIdList = new ArrayList<>(tableAndOrder.keySet());
            List<String> foodNameList = new ArrayList<>(foodNames);
            tableIdList.sort(Comparator.comparingInt(Integer::parseInt));
            foodNameList.sort(null);
            // 生成结果
            List<List<String>> result = new ArrayList<>();
            List<String> firstRow = new ArrayList<>();
            firstRow.add("Table");
            firstRow.addAll(foodNameList);
            result.add(firstRow);
            for (String tableId : tableIdList) {
                List<String> row = new ArrayList<>();
                row.add(tableId);
                for (String food : foodNameList) {
                    row.add(tableAndOrder.get(tableId).getOrDefault(food, 0).toString());
                }
                result.add(row);
            }
            return result;
        }
    }
}
