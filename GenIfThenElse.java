import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class GenIfThenElse{

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
            
            mv.visitVarInsn(Opcodes.ILOAD, 0);

            // Defining the else condition
            ElseCondition elseC = new ElseCondition();
            mv.visitJumpInsn(Opcodes.IFGE, elseC); // Else is jumped to if the number is greater than or equal to 1

            // Using the print statement I have been using. This will act as the if statement, and will be executed assuming else is not jumped to
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn("The value is negative ");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            
            // Else statment that will be possibly be jumped to
            mv.visitLabel(elseC);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn("The value is a positive number ot it is zero");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"gen1.java.program8.class");

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
