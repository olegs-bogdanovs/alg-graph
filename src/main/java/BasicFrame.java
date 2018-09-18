
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.JFrame;

public class BasicFrame implements GLEventListener {

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1, 1, 1, 0);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);


        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D(-100, 100, -100,100);

        gl.glBegin (GL2.GL_LINES);
        gl.glColor3d(0,0,0);
        gl.glVertex2f(0f, 100f);
        gl.glVertex2f(0f, -100f);
        gl.glVertex2f(-100f, 0f);
        gl.glVertex2f(100f, 0f);
        gl.glEnd();

        gl.glColor3d(0, 0,1);
        gl.glBegin(GL.GL_LINE_STRIP);
            for (double x = -100; x < 100; x += 0.5)
                gl.glVertex2d(x, Math.abs(1/4.0 * x + 3 * Math.cos(100 * x) * Math.sin(x)));
        gl.glEnd();
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
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

    }

}