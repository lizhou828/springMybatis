package cv;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-6-11
 * Time: 下午10:54
 *
 * java 的值传递（地址值）
 * 不管是对象、基本类型还是对象数组、基本类型数组，在函数中都不能改变其实际地址但能改变其中的内容。
 */
public class ValuePass {

    String str = new String("good");

    char[] ch = { 'a', 'b', 'c' };

    public static void main(String args[]) {

        ValuePass ex = new ValuePass();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");
        System.out.println(ex.ch);

        System.out.println("____________________________");

        String str1 = "AB";
        String str2 = "AB";
        StringBuilder str3 = new StringBuilder("AB");
        StringBuilder str4 = new StringBuilder("AB");
        StringBuffer str5 = new StringBuffer("AB");
        StringBuffer str6 = new StringBuffer("AB");
        testStr1(str1);
        testStr2(str2);
        testStr3(str3);
        testStr4(str4);
        testStr5(str5);
        testStr6(str6);
        System.out.println("str1="+str1);
        System.out.println("str2="+str2);
        System.out.println("str3="+str3);
        System.out.println("str4="+str4);
        System.out.println("str5="+str5);
        System.out.println("str6="+str6);
    }

    private static void testStr1(String str) {
        str = str+"CD";
    }

    private static void testStr2(String str) {
        str = "CD";
    }
    private static void testStr3(StringBuilder str) {
        str = str.append("CD");
    }
    private static void testStr4(StringBuilder str) {
        str = new StringBuilder("CD");
    }

    private static void testStr5(StringBuffer str) {
        str = str.append("CD");
    }
    private static void testStr6(StringBuffer str) {
        str = new StringBuffer("CD");
    }

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'g';
    }


}