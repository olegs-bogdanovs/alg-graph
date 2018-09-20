import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelParser {
    final private String CURVED_LINE_PATTERN = "^CURVED_LINE (\\d+ \\d+/)+";
    final private String COORDINATES_PATTERN = "\\d+ \\d+";
    private Pattern curvedPattern = Pattern.compile(CURVED_LINE_PATTERN);
    private Pattern coordinatesPattern = Pattern.compile(COORDINATES_PATTERN);


    private List<CurvedLine> curvedLines = new ArrayList<>();

    public void parseFile(String fileName){
        curvedLines.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            while((line = reader.readLine()) != null) {
                if (line.startsWith("CURVED_LINE")) {
                    parseCurvedLine(line);
                } else if (!line.isEmpty()) {
                    throw new Exception("Cannot parse line: " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseCurvedLine(String line) throws Exception{
        if (!curvedPattern.matcher(line).matches()){
            throw new Exception("Parsing error");
        }

        CurvedLine curvedLine = new CurvedLine();
        Matcher coordinatesMatcher = coordinatesPattern.matcher(line);
        while (coordinatesMatcher.find()){
            String[] xy = coordinatesMatcher.group().split(" ");
            curvedLine.addPoint(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
        }
        if (curvedLine.getPoints().size() != 0) curvedLines.add(curvedLine);
    }

    public List<CurvedLine> getCurvedLines(){
        return curvedLines;
    }
}
