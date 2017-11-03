package Program01;

import java.io.*;
import java.util.ArrayList;

public class FileHandler
{
    private static FileHandler instance = null;
    private String line;
    private String fileName;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public static FileHandler getInstance()
    {
        if(instance == null)
            instance = new FileHandler();
        return instance;
    }

    private FileHandler()
    {
    }

    private void openFile()
    {
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch(FileNotFoundException e) {
            System.err.println("ERROR: Unable to open file '" + fileName + "'.");
        }
    }

    private void closeFile()
    {
        try {
            bufferedReader.close();
            fileReader.close();
        } catch(IOException e) {
            System.err.println("ERROR: Unable to close file '" + fileName + "'.");
        }
    }

   
    public ArrayList<Primitive> readFile()
    {
        fileName = System.getProperty("user.dir") 
                + "\\src\\Program01\\Resources\\coordinates.txt";
        
        ArrayList<Primitive> primitives = new ArrayList<Primitive>();
        
        openFile();
        try {
            while((line = bufferedReader.readLine()) != null)
            {
                //System.out.println(line);
                
                String[] parts = line.split(" ");
                
                String primitiveType = parts[0];
                
                float xCenter;
                float yCenter;
                float radius;
                String[] centerCoordinates;
                String[] radii;
                Primitive primitive = null;
                
                
                switch(primitiveType)
                {
                    case "c":
                        centerCoordinates = parts[1].split(",");
                        xCenter = Float.parseFloat(centerCoordinates[0]);
                        yCenter = Float.parseFloat(centerCoordinates[1]);
                        radius = Float.parseFloat(parts[2]);
                        Circle circle = new Circle(xCenter, yCenter, radius);
                        primitive = circle;
                        //System.out.println(circle);
                        break;
                    case "e":
                        centerCoordinates = parts[1].split(",");
                        xCenter = Float.parseFloat(centerCoordinates[0]);
                        yCenter = Float.parseFloat(centerCoordinates[1]);
                        radii = parts[2].split(",");
                        float rx = Float.parseFloat(radii[0]);
                        float ry = Float.parseFloat(radii[1]); 
                        Ellipse ellipse = new Ellipse(xCenter, yCenter, rx, ry);
                        primitive = ellipse;
                        //System.out.println(ellipse);
                        break;
                    case "l":
                        String[] initialCoordinates = parts[1].split(",");
                        String[] finalCoordinates = parts[2].split(",");
                        float xInitial = Float.parseFloat(initialCoordinates[0]);
                        float yInitial = Float.parseFloat(initialCoordinates[1]);
                        float xFinal = Float.parseFloat(finalCoordinates[0]);
                        float yFinal = Float.parseFloat(finalCoordinates[1]);
                        Line line = new Line(xInitial, yInitial, xFinal, yFinal);
                        primitive = line;
                        //System.out.println(line);
                        break;
                    default:
                }
                
                primitives.add(primitive);
            }
                
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read from file '" + fileName + "'.");
        } finally {
            closeFile();
        }
        
        return primitives;
    }
}