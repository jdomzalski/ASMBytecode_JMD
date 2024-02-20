import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class GenMul{

    public static void main(String[] args){
        // Creating the ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"MulNumbers",null,"java/lang/Object",null);

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

            // Loading and multiplying two ints and storing/printing the result
            mv.visitLdcInsn(5);
            mv.visitVarInsn(Opcodes.ISTORE,0);
            mv.visitLdcInsn(6);
            mv.visitVarInsn(Opcode.ISTORE,1);
            mv.visitVarInsn(Opcodes.ILOAD,0);
            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitInsn(Opcodes.IMUL);
            mv.visitVarInsn(Opcodes.ISTORE,2);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD,2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);


            // Loading and multiplying two longs and storing/printing the result
            mv.visitLdcInsn((long) 25);
            mv.visitVarInsn(Opcodes.LSTORE,0);
            mv.visitLdcInsn((long) 500);
            mv.visitVarInsn(Opcodes.LSTORE,1);
            mv.visitVarInsn(Opcodes.LLOAD,0);
            mv.visitVarInsn(Opcodes.LLOAD,1);
            mv.visitInsn(Opcodes.LMUL);
            mv.visitVarInsn(Opcodes.LSTORE,2);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD,2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);


            // Loading and multiplying two floats and storing/printing the result
            mv.visitLdcInsn((float) 3.5);
            mv.visitVarInsn(Opcodes.FSTORE,0);
            mv.visitLdcInsn((float) 5.95);
            mv.visitVarInsn(Opcodes.FSTORE,1);
            mv.visitVarInsn(Opcodes.FLOAD,0);
            mv.visitVarInsn(Opcodes.FLOAD,1);
            mv.visitInsn(Opcodes.FMUL);
            mv.visitVarInsn(Opcodes.FSTORE,2);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.FLOAD,2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);


            // Loading and multiplying two doubles and storing/printing the result
            mv.visitLdcInsn((double) 200.53);
            mv.visitVarInsn(Opcodes.DSTORE,0);
            mv.visitLdcInsn((double) 23.23);
            mv.visitVarInsn(Opcodes.DSTORE,1);
            mv.visitVarInsn(Opcodes.DLOAD,0);
            mv.visitVarInsn(Opcodes.DLOAD,1);
            mv.visitInsn(Opcodes.DMUL);
            mv.visitVarInsn(Opcodes.DSTORE,2);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD,2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"gen1.java.program1.class");

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