
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class BasicFrame implements GLEventListener {
    private final static String FILE_NAME = "src/main/resources/coordinates.dat";
    private List<Point> coordinatesList;
    private List<Integer> codeList;
    private Point currentPoint;

    public BasicFrame() {
        coordinatesList = new LinkedList<>();
        codeList = new LinkedList<>();
        readDataFromFile();
        this.currentPoint = new Point(0, 0);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1, 1, 1, 0);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(0, 0, 0);
        gl.glColor3d(1, 0, 0);

        for (int i = 0; i < codeList.size(); i++){
            if (codeList.get(i) < 0) moveTo(coordinatesList.get(codeList.get(i)*(-1) - 1));
            else lineTo(coordinatesList.get(codeList.get(i) - 1), gl);
        }
        gl.glFlush();
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
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0,width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D(0, 100, 0, 100);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private void readDataFromFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            int coordinatesCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < coordinatesCount; i++){
                String[] coordinates = reader.readLine().split(" ");
                if (coordinates.length > 2 || coordinates.length < 2) throw new Exception("Parsing Error");
                coordinatesList.add(new Point(
                        Integer.parseInt(coordinates[0]),
                        Integer.parseInt(coordinates[1]))
                );
            }
            int codeCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < codeCount; i++){
                String line = reader.readLine();
                codeList.add(Integer.parseInt(line));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            codeList.clear();
            coordinatesList.clear();
        }

    }

    private void moveTo(Point p){
        currentPoint.setX(p.getX());
        currentPoint.setY(p.getY());
    }

    private void lineTo(Point p, GL2 gl){
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2i(currentPoint.getX(), currentPoint.getY());
        gl.glVertex2i(p.getX(), p.getY());
        gl.glEnd();
        currentPoint.setX(p.getX());
        currentPoint.setY(p.getY());
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //adding canvas to it
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

    }

}