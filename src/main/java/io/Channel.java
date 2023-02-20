package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.time.LocalDate;

/**
 * @author hq7733
 * @version 1.0
 * @date 2023/2/20 5:26 PM
 */
public class Channel {
    public static void main(String[] args) {

    }

    public String copyFile(File file) {
        File folder = new File("/Users/***/" + LocalDate.now());
        try {
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            File target = File.createTempFile("copy", getFileExtension(file), folder);
            FileChannel source = new FileInputStream(file).getChannel();
            FileChannel targetChannel = new FileOutputStream(target).getChannel();
            targetChannel.transferFrom(source, 0, source.size());
            return target.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf("."));
        else return "";
    }
}
