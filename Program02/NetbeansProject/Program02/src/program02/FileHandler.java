package Program02;

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

   
    public ArrayList<Polygon> readFile()
    {
        fileName = System.getProperty("user.dir") 
                + "\\src\\Program02\\Resources\\coordinates.txt";
        
        ArrayList<Polygon> polygons = new ArrayList<Polygon>();
        String currentlyReading = "None";
        int currentPolygon = -1;
        float x = 0;
        float y = 0;
        float z = 0;
        
        openFile();
        try
        {
            while((line = bufferedReader.readLine()) != null)
            {
                //System.out.println(line);
                
                String[] parts = line.split(" ");

                if(parts[0].equals("P"))
                {
                    currentlyReading = "Polygon";
                    currentPolygon++;
                }
                else if(parts[0].equals("T"))
                    currentlyReading = "Transformations";

                switch (currentlyReading) {
                    case "Polygon":
                        Polygon polygon = new Polygon();
                        if (parts[0].equals("P")) {
                            float[] color = new float[3];
                            color[0] = Float.parseFloat(parts[1]);
                            color[1] = Float.parseFloat(parts[2]);
                            color[2] = Float.parseFloat(parts[3]);
                            polygon.setColor(color);
                            polygons.add(polygon);
                        } else {
                            Integer[] vertices = new Integer[2];
                            vertices[0] = Integer.parseInt(parts[0]);
                            vertices[1] = Integer.parseInt(parts[1]);
                            polygons.get(currentPolygon).getVertices().add(vertices);
                        }
                        break;
                    case "Transformations":
                        if(!parts[0].equals("T"))
                        {
                            Transformation transformation = null;

                            switch (parts[0])
                            {
                                case "t":
                                    x = Float.parseFloat(parts[1]);
                                    y = Float.parseFloat(parts[2]);
                                    transformation = new Transformation("Translate", x, y);
                                    break;
                                case "r":
                                    x = Float.parseFloat(parts[2]);
                                    y = Float.parseFloat(parts[3]);
                                    transformation = new Transformation("Rotate", x, y);
                                    transformation.setRotationAngle(Float.parseFloat(parts[1]));
                                    break;
                                case "s":
                                    x = Float.parseFloat(parts[3]);
                                    y = Float.parseFloat(parts[4]);
                                    transformation = new Transformation("Scale", x, y);
                                    transformation.setScaleX(Float.parseFloat(parts[1]));
                                    transformation.setScaleY(Float.parseFloat(parts[2]));
                                    break;
                                default:
                                    break;
                            }

                            polygons.get(currentPolygon).getTransformations().add(transformation);
                        }
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read from file '" + fileName + "'.");
        } finally {
            closeFile();
        }
        
        return polygons;
    }
}