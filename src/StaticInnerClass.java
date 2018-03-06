/**
 * 最安全的单例模式(支持多线程,推荐)
 */
public class StaticInnerClass {

    private static class Singletion{
        private static Singletion singletion = new Singletion();
    }

    public static Singletion getInstance(){
        return Singletion.singletion;
    }

}
