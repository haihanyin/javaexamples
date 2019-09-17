package com.github.hh.je.io;

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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.FilterReader;
import java.io.FilterWriter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class IoTest {

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
