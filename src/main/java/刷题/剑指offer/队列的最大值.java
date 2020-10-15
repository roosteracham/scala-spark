package 刷题.剑指offer;

import java.util.LinkedList;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *  
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 队列的最大值 {


    public static void main(String[] args) {
        队列的最大值 d = new 队列的最大值();
        d.push_back(1);
        d.push_back(2);
        System.out.println(d.max_value());
        System.out.println(d.pop_front());
        d.push_back(4);
        System.out.println(d.max_value());
        d.push_back(3);
        System.out.println(d.max_value());
        System.out.println(d.pop_front());
        System.out.println(d.max_value());
        System.out.println(d.pop_front());
        System.out.println(d.max_value());
        System.out.println(d.pop_front());
        System.out.println(d.max_value());
    }

    public 队列的最大值() {
    }

    LinkedList<Integer> queue = new LinkedList<>();
    LinkedList<Integer> max = new LinkedList<>();

    public int max_value() {

        if (max.isEmpty()) {
            return -1;
        }
        return max.peek();
    }

    public void push_back(int value) {
        queue.add(value);
        while (!max.isEmpty() && max.peekLast().compareTo(value) < 0) {
            max.pollLast();
        }
        max.addLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        Integer remove = queue.remove();
        if (max.peek().equals(remove)) {
            max.pollFirst();
        }
        return remove;
    }
}
