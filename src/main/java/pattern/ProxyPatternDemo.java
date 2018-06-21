package pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 代理模式
 *
 * @author wanghao
 * @create 2018-06-22 上午5:40
 **/
public class ProxyPatternDemo {
    public static void main(String[] args)
    {
        Léon léonProxy =  new CglibProxyFactory<Léon>(new Léon()).getProxyInstance();
        System.out.println(léonProxy);
        léonProxy.kill();


        Killer killer = (Killer)ProxyFactory.getProxy(Léon.class);
        System.out.println(killer);
        killer.kill();
    }

}

/**
 * 杀手职业
 *
 * @author wanghao
 * @version C10 2018年5月10日
 * @since SDP V300R003C10
 */
interface Killer
{
    void kill();
}

/**
 * 杀手：莱昂登场
 *
 * @author wanghao
 * @version C10 2018年5月10日
 * @since SDP V300R003C10
 */
class Léon implements Killer
{
    public void kill()
    {
        System.out.println("莱昂杀了一只鸡");
    }

    public void léonSay()
    {
        System.out.println("莱昂:总是如此");
    }
}

/**
 * 创建代理类：收钱人玛蒂达
 *
 * @author wanghao
 * @version C10 2018年5月10日
 * @since SDP V300R003C10
 */
class Mathilda implements Killer
{
    private Léon léon;

    Mathilda()
    {
        if (léon == null)
        {
            léon = new Léon();
        }
    }

    @Override
    public void kill()
    {
        getDeposit();
        léon.kill();
        getBalance();
        mathildaSay();
        léon.léonSay();
    }

    public void getDeposit()
    {
        System.out.println("收取定金");
    }

    public void getBalance()
    {
        System.out.println("收取余款");
    }

    public void mathildaSay()
    {
        System.out.println("\n玛蒂达:人生总是这么痛苦吗？还是只有小时候是这样");
    }
}

/**
 * 简化版动态代理
 *
 * @author wanghao
 * @version C10 2018年5月10日
 * @since SDP V300R003C10
 */
class ProxyFactory
{
    public static <T> Object getProxy(Class<T> t)
    {
        return Proxy.newProxyInstance(t.getClassLoader(), t.getInterfaces(), new InvocationHandler()
        {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable
            {
                System.out.println("收取定金");
                Object result = method.invoke(t.newInstance(), args);
                System.out.println("收取全款");
                return result;
            }
        });
    }
}

/**
 * 模板化JDK动态代理模式
 *
 * @author wanghao
 * @version C10 2018年5月10日
 * @since SDP V300R003C10
 */
class ProxyFactory2<T>
{
    // 持有目标对象
    private T t;

    // 因为不知道代理者是谁，所以需要传入代理者
    ProxyFactory2(T t)
    {
        this.t = t;
    }
    // 方法实现
    public Object getProxy()
    {
        return Proxy
                .newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler()
                {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable
                    {
                        System.out.println("收取定金");
                        Object result = method.invoke(t, args);
                        System.out.println("收取全款");
                        return result;
                    }
                });
    }
}
/**
 * Cglib动态代理工厂类
 *
 * @author wanghao
 * @version C10 2018年5月10日
 * @param <T>
 * @since SDP V300R003C10
 */
class CglibProxyFactory<T> implements MethodInterceptor
{
    //被代理或者目标对象
    private T t;
    //传入被代理对象
    CglibProxyFactory (T t){
        this.t = t;
    }
    //给被代理者创建一个代理对象
    public T getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(t.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return (T)en.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable
    {
        System.out.println("Cglib动态代理：收取定金");
        Object result = method.invoke(t, args);
        System.out.println("Cglib动态代理：收取全款");
        return result;
    }
}
