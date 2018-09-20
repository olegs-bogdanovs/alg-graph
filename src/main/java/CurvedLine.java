import java.util.ArrayList;
import java.util.List;

public class CurvedLine {
    private List<Point> points = new ArrayList<>();

    public void addPoint(Point point){
        points.add(point);
    }

    public List<Point> getPoints(){
        return points;
    }

    @Override
    public String toString() {
        return "CurvedLine{" +
                "points=" + points +
                '}';
    }
}
