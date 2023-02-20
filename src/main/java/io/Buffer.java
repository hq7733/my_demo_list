package io;

import java.io.*;

/**
 * @author zhangheqi
 * @version 1.0
 * @date 2023/2/20 2:48 PM
 */
public class Buffer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        File read = new File("/Users/***/word.txt");
        File write = new File("/Users/***/word.txt");
        System.out.println(buffer.read(read));
    }

    /**
     * 创建文件
     *
     * @param file File对象
     * @return 执行结果
     */
    public boolean createFile(File file) {
        try {
            if (file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 写入文件内容
     *
     * @param read  原文件
     * @param write 新文件
     * @return
     */
    public boolean write(File read, File write) {
        try {
            // 获取文件输入流
            InputStream inputStream = new FileInputStream(read);
            byte[] bs = new byte[1024];
            int len;
            // 获取文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(write);
            // 循环写入
            while ((len = inputStream.read(bs)) != -1) {
                fileOutputStream.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String read(File file) {
        StringBuffer stringBuffer = new StringBuffer();
        try (InputStream stream = new FileInputStream(file)) {
            byte[] bs = new byte[1024];
            int len;
            while ((len = stream.read(bs)) != -1) {
                stringBuffer.append(bs.toString());
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
