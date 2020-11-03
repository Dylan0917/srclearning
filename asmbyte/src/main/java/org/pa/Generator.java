package org.pa;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/2 19:29
 */
public class Generator {
    public static void main(String[] args) {
        try {
            ClassReader classReader = new ClassReader("org.pa.Base");
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            ClassVisitor classVisitor = new MyClassVisitor(classWriter);
            classReader.accept(classVisitor,ClassReader.SKIP_DEBUG);
            byte[] data = classWriter.toByteArray();
            File f = new File("E:\\myProject\\srclearning\\asmbyte\\target\\classes\\org\\pa\\Base.class");
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(data);
            fileOutputStream.close();
            System.out.println("now generator cc success1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
