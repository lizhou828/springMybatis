package cv;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-6-11
 * Time: 下午10:40
 */
public class CodeExecuteOrder {

//    说实话我觉得这题很好，考查静态语句块、构造语句块（就是只有大括号的那块）以及构造函数的执行顺序。
//
//    对象的初始化顺序：
// （1）类加载之后，按从上到下（从父类到子类）执行被static修饰的语句；
// （2）当static语句执行完之后,再执行main方法；
// （3）如果有语句new了自身的对象，将从上到下执行构造代码块、构造器（两者可以说绑定在一起）。
    public static void main(String[] args) {

//        总之，一句话：子类没有显示调用父类构造函数，不管子类构造函数是否带参数都默认调用父类无参的构造函数，若父类没有则编译出错。
         new HelloB("CAONIMA");
     }
}
class HelloA {

    static { System.out.println("A static "); }

    { System.out.println("A  construct code"); }
    public HelloA() {
        System.out.println("A constructor");
    }

    public HelloA(String str){
        System.out.println("A constructor with parameter,Astr="+str);
    }
}

class HelloB extends HelloA {

    static { System.out.println("B static "); }

    { System.out.println("B  construct code"); }
    public HelloB() {
        System.out.println("B constructor");
    }

    public HelloB(String str){
//        super(str);
        System.out.println("B constructor with parameter,Bstr="+str);
    }







}
