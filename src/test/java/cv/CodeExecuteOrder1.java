package cv;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-6-11
 * Time: 下午11:27
 */
public class CodeExecuteOrder1 {
    public static void main(String[] args){

        new Dervied();

//        今天无意中看到这个题目 去试验了一下。
//        结果确实是：
//        Dervied tell name: null
//        Dervied print name: null
//        Base say name: base
//        Dervied tell name: dervied
//        Dervied print name: dervied
//                原因有两个
//        构造方法的调用顺序中规定了
//        先调用父类的构造方法 ，然后才去调用子类的构造方法
//        至于为什么父类中的tellName（）和printName（）方法没有调到，
//        是因为java里面重载的概念，子类重写了方法，所以调用子类的相应方法，
//        而为什么name是null，因为这里还没有加载到子类里面的变量name属性，
    }
}
 class Dervied extends Base {
    private  String name = "dervied";
    public Dervied() {
            tellName();
            printName();
    }
    public void tellName() {
        System.out.println("Dervied tell name: " + name);
    }
    public void printName() {
        System.out.println("Dervied print name: " + name);
    }
}

class Base {
    private String name = "base";
    public Base() {
        tellName();
        printName();
        sayName();
    }
    public void tellName() {
        System.out.println("Base tell name: " + name);
    }
    public void printName() {
        System.out.println("Base print name: " + name);
    }
    public void sayName() {
        System.out.println("Base say name: " + name);
    }
}
