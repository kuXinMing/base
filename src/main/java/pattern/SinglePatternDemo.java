package pattern;

/**
 * 单例模式
 *
 * @author wanghao
 * @create 2018-06-22 上午5:57
 **/
public class SinglePatternDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(SinglePattern.getInstance());
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(SinglePattern.getInstance());
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(SinglePattern.getInstance());
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * 加锁后的，单例模式类 +优化
 * @author wanghao
 *
 */
class SinglePattern{
    private static SinglePattern instance ;
    //1.构造方法私有化
    private SinglePattern() {
    }
    //2.提供一个全局访问点 加锁后
    public static  SinglePattern getInstance() {
        if(instance == null) {
            synchronized(SinglePattern.class) {
                if(instance == null) {
                    instance = new SinglePattern();
                }
            }
        }
        return instance;
    }
}
