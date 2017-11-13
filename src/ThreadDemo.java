import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wskyt on 2017/11/8.
 */
class MyThread extends Thread {
    private int startNum;
    private int endNum;
    private Thread t;
    private String[] names;

    MyThread(int startNum,int endNum, String[] names) {
        this.startNum = startNum;
        this.endNum = endNum;
        this.names = names;
    }

    public void run() {
        try {
            for (int i = startNum; i < endNum; i++) {
                File file = new File("D:\\SourceData\\realdata\\tripadvisor\\" + names[i]);
                file.delete();
                System.out.println("delete : " + i++);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class ThreadDemo {

    public static String func(int threadNum) throws InterruptedException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("D:\\SourceData\\realdata\\tripadvisor");

        String[] names = sourceFile.list();

        int step = 1000/threadNum;

        List<MyThread> threads = new ArrayList<>();

        for (int i = 0; i < threadNum; i++) {
            MyThread T = new MyThread( i*step,step*(i+1),names);
            T.start();
            threads.add(T);
        }

        for(MyThread T : threads) {
            T.join();
        }

        long end = System.currentTimeMillis();

        return "ThreadNum :" + threadNum + ",cost time : " + (end-start)/1000 +"s";

    }

    public static void main(String args[]) throws InterruptedException {
        List<String> result = new ArrayList<>();

//        for (int i = 1; i <= 2000; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (2000%i == 0){
//                    String str = func(i);
//                    result.add(str);
//                }
//            }
//        }
//
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(result.get(i));
//        }
        for (int i = 0; i < 4; i++) {
            System.out.println("i = " + i);
            System.out.println(func(1000));
        }


    }
}