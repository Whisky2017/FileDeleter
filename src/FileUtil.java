import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
/**
 * Created by wskyt on 2017/11/8.
 */
public class FileUtil {
    //复制文件
    public void copyFile(String source, String dest) {
        File sourceFile = new File(source);
        File destFile = new File(dest + "/" + sourceFile.getName());

        try {
            FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int len = 0;
            byte[] b = new byte[1024];
            while((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }

            bos.close();
            bis.close();
            fos.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //复制文件及文件夹
    public void copyField(String source, String dest) {
        File sourceFile = new File(source);
        //文件夹复制
        if(sourceFile.isDirectory()) {
            String[] names = sourceFile.list();
            File file = new File(dest + "/" + sourceFile.getName());
            if(!file.exists()) {
                file.mkdir();
            }
            for(String name : names) {
                copyField(source + "/" + name, file.getPath());
            }
        }
        //文件复制
        else {
            this.copyFile(source, dest);
        }
    }

    //删除文件及文件夹
    public void deleteField(String source,int limit) {
        long start = System.currentTimeMillis();

        File sourceFile = new File(source);

        String[] names = sourceFile.list();
        System.out.println("length = " + names.length);
        int i = 0;
        //删除当前文件夹下面的子文件和子文件夹
        for (int j = 0; j < names.length; j++) {
            if (i < limit){
                sourceFile = new File(source + "/" + names[j]);
                sourceFile.delete();
                System.out.println("delete : " + i++);
            }else
                break;

        }

        long end = System.currentTimeMillis();

        System.out.println("cost time : " + (end-start)/1000 +"s");
    }

    //剪切文件及文件夹
    public void cutField(String source, String dest) {
        //复制文件夹
        //删除文件夹
    }

    //从网络上下载资源
    public void download(URL url) {
        try {
            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream("e:/123.gif");
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] b = new byte[1024];
            int len = 0;
            while((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }

            bos.close();
            bis.close();
            fos.close();
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();
        fileUtil.deleteField("D:\\SourceData\\realdata\\tripadvisor",1000);
    }
}
