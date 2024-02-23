import java.io.*;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class gen9{

    public static void main(String[] args){
        // Creating the ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program9",null,"java/lang/Object",null);

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

            // Creating a Scanner object
            mv.visitTypeinsn(Opcodes.NEW, "java/util/Scanner");
            // Get the input
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            // Invoking the Scanner for a int value
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "nextInt", "()I", false);
            // Storing the int
            mv.visitVarInsn(Opcodes.ISTORE, 0);

            // Creating and storing the accumulator
            mv.visitLdcInsn(0);
            mv.visitVarInsn(Opcodes.ISTORE, 1);

            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);

            // Adding the numbers and storing the result back to the accumulator
            mv.visitVarInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE, 0);

            // Printing the result
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD,0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);


            // Creating a Scanner object
            mv.visitTypeinsn(Opcodes.NEW, "java/util/Scanner");
            // Get the input
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            // Invoking the Scanner for a double value
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "nextInt", "()D", false);
            // Storing the int
            mv.visitVarInsn(Opcodes.DSTORE, 0);

            // Creating and storing the accumulator
            mv.visitLdcInsn(0);
            mv.visitVarInsn(Opcodes.DSTORE, 1);

            mv.visitVarInsn(Opcodes.DLOAD, 0);
            mv.visitVarInsn(Opcodes.DLOAD, 1);

            // Adding the numbers and storing the result back to the accumulator
            mv.visitVarInsn(Opcodes.DADD);
            mv.visitVarInsn(Opcodes.DSTORE, 0);

            // Printing the result
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD,0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program9.class");

        System.out.println("Done!");
    } // end main

    // Moving writeFile method outside of the main method
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

} // end class