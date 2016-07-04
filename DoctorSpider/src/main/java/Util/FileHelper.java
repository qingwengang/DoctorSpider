package Util;

import java.io.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/18.
 */
public class FileHelper {
    public static void main(String[] args) throws IOException {
        new FileHelper().WriteAppend("D:/filetest/a/a", "wenwen", new Date().toString() + "/r/n");
//        String result=ReadEbayComments("fds", "fdsf")+"a";
//        System.out.println(result);
//        System.out.println("fds");
    }

    public void Write(String path, String id, String content) throws IOException {
        File mulu = new File(path);
        if (!mulu.exists()) mulu.mkdirs();
        File file = new File(path + "/" + id + ".txt");
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);

        out.write(content.getBytes("utf-8"));
        out.close();
    }
    public String Read(String path,String id) throws IOException{
        String file=path+id+".txt";
        return BufferedReaderDemo(file);
    }

    public void WriteAppend(String path, String id, String content) throws IOException {
        File mulu = new File(path);
        if (!mulu.exists()) mulu.mkdirs();
        File file = new File(path + "/" + id + ".txt");
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file, true);
        out.write((new Date().toString() + " " + content).getBytes("utf-8"));
        out.write("\r\n".getBytes());
        out.close();
    }

    public static String ReadEbayComments(String userId, String iid) throws IOException {
        String file = "D:/AmazonData/Ebay/Comments/" + userId + "/" + iid + ".txt";
        return BufferedReaderDemo(file);
    }

    public static String BufferedReaderDemo(String path) throws IOException {
        File file = new File(path);
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
