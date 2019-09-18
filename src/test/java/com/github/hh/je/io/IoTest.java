package com.github.hh.je.io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.FilterReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

public class IoTest {

    private String resourceFile(String fileName) {
        return "src/test/resources/" + fileName;
    }

    @Test
    public void testFileReader() {
        try(FileReader fileReader = new FileReader(resourceFile("FileToBeRead"))) {
            char[] buf = new char[10];
            while (fileReader.read(buf) != -1) {
                System.out.print(buf);
                Arrays.fill(buf, '\0');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileWriter() {
        try(FileWriter fileWriter = new FileWriter(resourceFile("FileToBeWritten"))) {
            for (int i = 0; i< 5; i++) {
                Thread.sleep(10);
                String timeString = Long.toString(System.currentTimeMillis());
                fileWriter.write(timeString);
                fileWriter.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileInputStream() {
        try(FileInputStream fileInputStream = new FileInputStream(resourceFile("data_model.jpg"))) {
            byte[] bytes = new byte[10];
            int size = 0;
            int read = 0;
            while((read = fileInputStream.read(bytes)) != -1) {
                    size += read;
            }
            System.out.println("size = " + size + "B");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileOutputStream() {

        try(FileInputStream fileInputStream = new FileInputStream(resourceFile("data_model.jpg"));
                FileOutputStream fileOutputStream = new FileOutputStream(resourceFile("data_model_copy.jpg"))) {
            byte[] bytes = new byte[1000];

            while(fileInputStream.read(bytes) != -1) {
                fileOutputStream.write(bytes, 0 , 999);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

















    // https://blog.csdn.net/jingzi123456789/article/details/72123937
    // https://juejin.im/post/5af79bcc51882542ad771546

    public void test() {
        Class[] ioFileOrMemory = new Class[]{
                // from file
                FileReader.class,
                FileWriter.class,
                FileInputStream.class,
                FileOutputStream.class,

                // from memory
                CharArrayReader.class,
                CharArrayWriter.class,
                ByteArrayInputStream.class,
                ByteArrayOutputStream.class,
                StringReader.class,
                StringWriter.class
        };

        Class[] bufferIo = new Class[] {
                BufferedReader.class,
                BufferedWriter.class,
                BufferedInputStream.class,
                BufferedOutputStream.class
        };

        Class[] filterIo = new Class[] {
                FilterReader.class,
                FilterWriter.class,
                FilterInputStream.class,
                FilterOutputStream.class
        };

        Class[] conversionIo = new Class[] {
                InputStreamReader.class,
                OutputStreamWriter.class,

                ObjectInputStream.class,
                ObjectOutputStream.class,

                DataInputStream.class,
                DataOutputStream.class,
        };

        Class[] otherIo = new Class[] {
                LineNumberReader.class,
                PrintStream.class,
                PrintWriter.class
        };
    }
}
