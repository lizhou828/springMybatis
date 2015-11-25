package cv;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-6-11
 * Time: 下午11:51
 *  for 循环原理
 */
public class ForLoop {
    private static boolean foo(char c) {
        System.out.println(c);
        return true;
    }
    public static void main(String[] args) {
        int i = 0;
        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }
    }
}
