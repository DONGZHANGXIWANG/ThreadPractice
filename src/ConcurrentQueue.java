import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by Administrator on 2017/11/28/028.
 */
public class ConcurrentQueue {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
        concurrentLinkedDeque.add("a");
        concurrentLinkedDeque.add("b");
        concurrentLinkedDeque.add("c");
        concurrentLinkedDeque.add("d");
        concurrentLinkedDeque.add("e");
        concurrentLinkedDeque.add("f");

        System.out.println(concurrentLinkedDeque.poll());
        System.out.println(concurrentLinkedDeque.size());
        System.out.println(concurrentLinkedDeque.peek());
        System.out.println(concurrentLinkedDeque.size());
    }

}
