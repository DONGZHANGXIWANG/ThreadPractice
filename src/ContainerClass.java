import java.util.Vector;

/**
 * 同步类容器底层源码都实现了synchronized去修饰方法
 * synchronized 修饰方法会造成并发性能底下
 */
public class ContainerClass {

    public static void main(String[] args) {
        Vector<String> strings = new Vector<>();

        strings.add("查看源码,是否是线程安全的");

    }

}
