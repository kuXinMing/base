package pattern;

/**
 * @author wanghao
 * @create 2018-06-22 上午6:05
 **/
public class SingletonAndFactory {
    public static void main(String[] args)
    {
        Hero h1 = Hero_One.getInstance();
        h1.setName("马可波罗");
        Hero h2 = Hero_Two.getInstance();
        h2.setName("大小姐");
        Hero h3 = Hero_San.getInstance();
        h3.setName("凯");
        System.out.println("游戏开始：英雄登场，参赛英雄有："+h1.getName()+","+h2.getName()+","+h3.getName());
        //自动增长经验线程
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 100; i++)
                {
                    try
                    {
                        //模拟每隔五秒，每个英雄都自动增长一次经验
                        Thread.sleep(1000);
                        Hero_One.getInstance().addExperience();
                        Hero_Two.getInstance().addExperience();
                        Hero_San.getInstance().addExperience();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        //模拟得分
        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int j = 0; j < 100; j++)
                {
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    int i = (int)(Math.random()*3+1);
                    System.out.println(i);
                    switch (i)
                    {
                        case 1:
                            Hero_One.getInstance().kill(Hero_Two.getInstance());
                            break;
                        case 2:
                            Hero_Two.getInstance().kill(Hero_San.getInstance());
                            break;
                        case 3:
                            Hero_San.getInstance().kill(Hero_One.getInstance());
                            break;
                        default:
                            break;
                    }
                }

            }
        });
        t1.run();
        t2.run();
    }
}

abstract class Hero
{
    String name;

    int level = 0;

    int experience = 0;

    public abstract void kill(Hero hero);
    public abstract void beiKill(Hero hero);

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void addExperience() {
        this.experience +=1;
        if(experience % 10 ==0) {
            this.level++;
            this.experience=0;
        }
        System.out.println(this.name+"的经验为："+this.experience+"等级为："+this.level);
    }

}

class Hero_One extends Hero
{
    private static Hero_One instance;

    private Hero_One() {};

    public static  Hero getInstance() {
        if(instance == null) {
            synchronized(Hero_One.class) {
                if(instance == null) {
                    instance = new Hero_One();
                }
            }
        }
        return instance;
    }
    @Override
    public void kill(Hero hero)
    {
        System.out.println(this.name + "杀死了" + hero.getName());
        this.level++;
        hero.beiKill(this);
    }

    @Override
    public void beiKill(Hero hero)
    {
        System.out.println(this.getName() + "被" + hero.getName() + "杀死了");
    }

}

class Hero_Two extends Hero
{
    private static Hero_Two instance;

    private Hero_Two() {};

    public static  Hero getInstance() {
        if(instance == null) {
            synchronized(Hero_Two.class) {
                if(instance == null) {
                    instance = new Hero_Two();
                }
            }
        }
        return instance;
    }
    @Override
    public void kill(Hero hero)
    {
        System.out.println(this.name + "杀死了" + hero.getName());
        this.level++;
        hero.beiKill(this);
    }

    @Override
    public void beiKill(Hero hero)
    {
        System.out.println(this.getName() + "被" + hero.getName() + "杀死了");
    }

}

class Hero_San extends Hero
{
    private static Hero_San instance;

    private Hero_San() {};

    public static  Hero getInstance() {
        if(instance == null) {
            synchronized(Hero_San.class) {
                if(instance == null) {
                    instance = new Hero_San();
                }
            }
        }
        return instance;
    }

    @Override
    public void kill(Hero hero)
    {
        System.out.println(this.name + "杀死了" + hero.getName());
        this.level++;
        hero.beiKill(this);
    }

    @Override
    public void beiKill(Hero hero)
    {
        System.out.println(this.getName() + "被" + hero.getName() + "杀死了");
    }
}
