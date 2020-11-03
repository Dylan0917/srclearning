package org.pa;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/2 19:51
 */
public class MyMethodVisitor extends MethodVisitor implements Opcodes {

    public MyMethodVisitor(int api) {
        super(api);
    }

    public MyMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }
    public MyMethodVisitor(MethodVisitor methodVisitor) {
        super(Opcodes.ASM5, methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;"
        );
        mv.visitLdcInsn("start");
        mv.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/io/PrintStream;)V",false);


    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN){
            mv.visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
            mv.visitLdcInsn("end");
            mv.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V",false);

        }
        mv.visitInsn(opcode);
    }
}
