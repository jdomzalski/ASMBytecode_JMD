import java.io.*;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class gen7 {

    public static void main(String[] args){
        // Creating the ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"Strings",null,"java/lang/Object",null);

        {
            // Created the MethodVisitor for the initial method
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); //loading this variable onto the stack
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();
        }

        {
            // Creating the MethodVisitor for the main method
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            mv.visitLdcInsn(0);
            mv.visitVarInsn(Opcodes.ISTORE, 0);

            // Defining the loop
            LoopCondition startLoop = new LoopCondition();
            mv.visitLabel(startLoop);
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitLdcInsn(10);
            LoopCondition endLoop = new LoopCondition();
            mv.visitJumpInsn(Opcodes.IFEQ, endLoop); // If stored value equal to 10, the loop will end


            // Content of loop
            mv.visitVarInsn(Opcodes.ILOAD, 0); // loading the value at index 0 and adding 1 to it. This is how we go to the next step in the loop
            mv.visitIns(Opcodes.ICONST_1); // decalring a constant value of 1 that will be added to the variable at position 0
            mv.visitInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE, 0); // Storing the incremented value


            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program7.class");

        public static void writeFile(byte[] bytearray, String fileName){
            try{
                FileOutputStream out = new FileOutputStream(fileName);
                out.write(bytearray);
                out.close();
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Done!");
    } // end main

} // end class