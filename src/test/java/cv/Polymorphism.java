package cv;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-6-12
 * Time: 下午10:32
 *
 * java 多态
 *
 * 多态出现的缘由：java的引用变量有两种类型：一个是编译时的类型，一个是运行时类型。
 编译时类项：声明该变量时使用的类型决定。
 运行时类项：实际赋给该变量的对象决定
 如果编译时和运行时类项不一样就会出现所谓的多态（polymorphism）。

 多态：相同类项的变量执行同一个方法时，呈现出不同的行为特征这就是多态。
 注意：引用变量在编译阶段只能调用其编译时类项所具有的方法，
 但运行时则执行运行时类项所具有的方法，因此编译java代码时，
 引用变量只能调用声明该变量时类项里包含的方法，
 如：Object p = new Person()定义一个变量p，p只能调用Object类得方法而不能调用person类里定义的方法。
 */
public class Polymorphism {

    public static void main(String [] args){
        BaseClass bc = new BaseClass();
        SubClass sc = new SubClass();
        BaseClass bsc = new SubClass();

        //方法的多态表现
        bc.base();
        sc.base();
        bsc.base();

        //与方法不同的是，对象的属性则不具有多态性：

        System.out.println(bc.book);  //8
        System.out.println(sc.book); //子类属性
        System.out.println(bsc.book); //8

        // Java  OOP 里面的三大特性：多态、封装、继承
        //多态的表现在：方法的重写
        //重写方法只能存在于具有继承关系中，重写方法只能重写父类非私有的方法。否则编译报错

        /**
        总结：
        重写方法的规则：
        1、参数列表必须完全与被重写的方法相同，否则不能称其为重写而是重载。
        2、返回的类型必须一直与被重写的方法的返回类型相同，否则不能称其为重写而是重载。
        3、访问修饰符的限制一定要大于被重写方法的访问修饰符（public>protected>default>private）
        4、重写方法一定不能抛出新的检查异常或者比被重写方法申明更加宽泛的检查型异常。例如：
        父类的一个方法申明了一个检查异常IOException，在重写这个方法是就不能抛出Exception,只能抛出IOException的子类异常，可以抛出非检查异常。

        而重载的规则：
        1、必须具有不同的参数列表；
        2、可以有不责骂的返回类型，只要参数列表不同就可以了；
        3、可以有不同的访问修饰符；
        4、可以抛出不同的异常；

        重写与重载的区别在于：
        重写多态性起作用，对调用被重载过的方法可以大大减少代码的输入量，同一个方法名只要往里面传递不同的参数就可以拥有不同的功能或返回值。
        用好重写和重载可以设计一个结构清晰而简洁的类，可以说重写和重载在编写代码过程中的作用非同一般.
      */
    }
}

class BaseClass{
    public int book = 8;
    public  void base(){
        System.out.println("baseClass.base()");
    }
}
class SubClass extends BaseClass{
    public String book = "子类属性";
    public void base(){   //重写了父类的base()方法   ，方法的重写是子类与父类之间的
        System.out.println("SubClass.base() overRide");
    }
    public void base(String str){ // 该类里面的方法重载 ， 方法的重载是在一个类面的
        System.out.println("SubClass.base() overLoad");
    }
}

