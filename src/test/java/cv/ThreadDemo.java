package cv;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-6-11
 * Time: 下午10:20
 */
public class ThreadDemo {
    public static void main(String[] args){
        Thread thread = new Thread(){
          public void run(){
            pong();
          }
        };
//  start()用来启动一个线程，当调用start方法后，
// 系统才会开启一个新的线程，进而调用run()方法来执行任务，
// 而单独的调用run()就跟调用普通方法是一样的，已经失去线程的特性了。
// 因此在启动一个线程的时候一定要使用start()而不是run()。
        thread.start();
//        thread.run();
        System.out.println("ping");
    }

    private static void pong() {
        System.out.println("pong");
    }
}
