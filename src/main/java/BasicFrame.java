
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

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
        System.out.println(coordinatesList);
        System.out.println(codeList);

    }

    @Override
    public void display(GLAutoDrawable drawable) {

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
                codeList.add(Integer.parseInt(reader.readLine()));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            codeList.clear();
            coordinatesList.clear();
        }

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