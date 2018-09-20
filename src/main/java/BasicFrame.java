
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;

public class BasicFrame implements GLEventListener {
    private ModelParser modelParser;

    public BasicFrame() {
        this.modelParser = new ModelParser();
        this.modelParser.parseFile("src/main/java/initials.txt");
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        //gl.glClearColor(1, 1, 1, 0);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D(0, 130, 0,100);

        for (CurvedLine curvedLine : modelParser.getCurvedLines())
            drawCurvedLine(curvedLine, gl);
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable arg0) {
        // method body
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }

    private void drawCurvedLine(CurvedLine curvedLine, GL2 gl){


        gl.glBegin(GL.GL_LINE_STRIP);
        for (Point point : curvedLine.getPoints())
            gl.glVertex2d(point.getX() / 1.0 , point.getY() / 1.0);
        gl.glEnd();
    }

    public static void main(String[] args) {

        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        BasicFrame b = new BasicFrame();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(800, 800);

        //creating frame
        final JFrame frame = new JFrame (" Basic Frame");

        //adding canvas to it
        frame.getContentPane().add(glcanvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

    }

}