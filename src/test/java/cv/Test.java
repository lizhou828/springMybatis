package cv;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-6-12
 * Time: 下午10:07
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args){
        int i = 10000000;
        char c = (char)i;
        int j = c;
        System.out.println("i="+i+",c="+c+",j="+j);

        char cc  =Character.MAX_VALUE;
        int ii = cc;
        System.out.println("cc="+cc+",ii="+ii);

        Parent parent = new Child();
        Child child = new Child();

        String str = parent.i +","+child.i;
        int all = parent.i+child.i;
        System.out.println(str+"      "+all);
    }
}

class Parent{
    public int i=1;
}
class Child extends Parent{
    public int i=2;
}
