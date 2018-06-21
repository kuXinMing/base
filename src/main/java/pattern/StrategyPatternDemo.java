package pattern;

/**
 * 策略模式
 *
 * @author wanghao
 * @create 2018-06-22 上午6:06
 **/
public class StrategyPatternDemo {
    public static void main(String [] args){
        Context context = new Context(new Addition());
        //因为我需要加法所以创建了加法对象
        System.out.println("5和3数相加的结果为："+ context.operation(5,3));
        //现在我需要减法了，所以我重新创建减法对象
        context = new Context(new Subtraction());
        System.out.println("5和3两个数相减的结果为："+ context.operation(5,3));
        //现在我需要乘法了，所以我重新创建乘法对象
        context = new Context(new Multiplication());
        System.out.println("5和3两个数相减的结果为："+ context.operation(5,3));
    }
}

/**
 * 创建获取方法的工具类
 */
class Context{

    private Operation operation;

    Context(Operation operation){
        this.operation = operation;
    }

    public double operation(double d1,double d2){
        return operation.operation(d1,d2);
    }
}
/**
 * 运算接口，所有算法的"工厂"
 *
 * @author: wanghao
 * @Date:  2018/5/12 下午3:34
 */
interface Operation{

    double operation(double d1,double d2);
}
/**
 * 加法实现
 *
 * @author: wanghao
 * @Date:  2018/5/12 下午3:38
 */
class Addition implements  Operation{

    @Override
    public double operation(double d1, double d2) {
        return d1 + d2;
    }
}

/**
 * 减法实现
 *
 * @author: wanghao
 * @Date:  2018/5/12 下午3:38
 */
class Subtraction implements  Operation{

    @Override
    public double operation(double d1, double d2) {
        return d1 - d2;
    }
}

/**
 * 乘法实现
 *
 * @author: wanghao
 * @Date:  2018/5/12 下午3:38
 */
class Multiplication implements  Operation{

    @Override
    public double operation(double d1, double d2) {
        return d1 * d2;
    }
}
