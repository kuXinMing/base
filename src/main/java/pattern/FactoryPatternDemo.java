package pattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 工厂模式
 *
 * @author wanghao
 * @create 2018-06-22 上午6:01
 **/
public class FactoryPatternDemo {
    public static void main(String[] args) throws InterruptedException {
        //线程池允许同时存在两个线程
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
        System.out.println("****************************newFixedThreadPool*******************************");
        for(int i=0;i<4;i++)
        {
            final int index=i;
            newFixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("启动线程");
                }
            });
        }
    }




}





class  ReflexFactory {
    public static <T extends Water> T getWater(Class<T> clz) {
        T t = null;
        try {
            t = (T) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }
}
//多方法工厂模式,需要什么就调什么
class WaterFactory{

    public static NongFuWater getNongFuWater() {
        return new NongFuWater();
    }

    public static WaHaWater getWaHaWater() {
        return new WaHaWater();
    }

    public static BaiSuiWater getBaiSuiWater() {
        return new BaiSuiWater();
    }
}


/**
 * 我家卖水的
 * @author wanghao
 *
 */
class Water{
    //卖的是可以喝的水
    public void drink() {};
}
//农夫三拳 水
class NongFuWater extends Water{
    @Override
    public void drink() {
        System.out.println("农夫三拳有点甜。");
    }
}
//哇哈哈 水
class WaHaWater extends Water{
    @Override
    public void drink() {
        System.out.println("哇哈哈也有点甜。");
    }
}
//百岁山 水
class BaiSuiWater extends Water{
    @Override
    public void drink() {
        System.out.println("百岁山也是甜的。");
    }
}
