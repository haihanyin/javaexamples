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
import java.io.PushbackReader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
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

    @Test
    public void testCharArrayReader() throws IOException {
        char[] chars = new char[10];
        CharArrayReader charArrayReader = new CharArrayReader("Hello, world".toCharArray());
        charArrayReader.read(chars);
        charArrayReader.close();
        System.out.println(new String(chars));
    }

    @Test
    public void testCharArrayWriter() {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        charArrayWriter.write('A');
        charArrayWriter.write('B');
        charArrayWriter.append('C');
        System.out.println(charArrayWriter);
    }

    @Test
    public void testByteArrayInputStream() throws IOException {
        int size = 4;
        byte[] array = ByteBuffer.allocate(size).putInt(256).array();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        for(int i = 0; i<size; i++) {
            System.out.println(byteArrayInputStream.read());
        }
        byteArrayInputStream.close();
    }

    @Test
    public void testStringReader() throws IOException {
        StringReader stringReader = new StringReader("Hello, world");
        for(int i=0; i <5; i++) {
            System.out.println(stringReader.read());
        }
        stringReader.close();
    }

    @Test
    public void testStringWriter() throws IOException {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write("hello");
        stringWriter.write(", world");
        stringWriter.close();
        System.out.println(stringWriter);
    }

    @Test
    public void testPrintStream() throws FileNotFoundException {
        // PrintStream can "extend" other OutputStream with formated-print related methods
        PrintStream printStream = new PrintStream(new FileOutputStream(resourceFile("tempForPrintStream")));
        printStream.println(1);
        printStream.println(2);
        printStream.printf("%s%d", "hello", 123);
        printStream.close();
    }

    @Test
    public void testPrintWriter() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(resourceFile("tempForPrintWriter")));
        printWriter.println(4);
        printWriter.printf("%.4f", 1.23f);
        printWriter.close();
    }


    @Test
    public void testLineNumberReader() throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(resourceFile("FileToBeRead")));
        String line = null;
        while((line = lineNumberReader.readLine()) != null) {
            System.out.println(lineNumberReader.getLineNumber() + " - " + line);
        }
        lineNumberReader.close();
    }

    @Test
    public void testObjectInputOutputStream() throws IOException {
        Person person = new Person();
        person.name = "John";
        person.age = 33;
        person.height = 1.75f;
        person.isMarried = true;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(resourceFile("PersonObject")));
        oos.writeChars(person.name);
        oos.writeInt(person.age);
        oos.writeFloat(person.height);
        oos.writeBoolean(person.isMarried);
        oos.close();


        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(resourceFile("PersonObject")));
        System.out.println(ois.readChar());
        System.out.println(ois.readChar());
        System.out.println(ois.readChar());
        System.out.println(ois.readChar());
        System.out.println(ois.readInt());
        System.out.println(ois.readFloat());
        System.out.println(ois.readBoolean());
    }

    private static class Person {
        public String name;
        public int age;
        public float height;
        public boolean isMarried;
    }







    // https://blog.csdn.net/jingzi123456789/article/details/72123937
    // https://juejin.im/post/5af79bcc51882542ad771546

    public void test() {

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
