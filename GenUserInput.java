import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class GenUserInput{

    public static void main(String[] args){
        // Creating the ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"UserInput",null,"java/lang/Object",null);

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

            // Creating a Scanner object
            mv.visitTypeinsn(Opcodes.NEW, "java/util/Scanner");
            // Get the input
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            // Invoking the Scanner for a long value
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "nextLong", "()L", false);
            // Storing the long
            mv.visitVarInsn(Opcodes.LSTORE, 0);

            // Creating a Scanner object
            mv.visitTypeinsn(Opcodes.NEW, "java/util/Scanner");
            // Get the input
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            // Invoking the Scanner for a double value
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "nextDouble", "()D", false);
            // Storing the double
            mv.visitVarInsn(Opcodes.DSTORE, 0);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"gen1.java.program6.class");

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