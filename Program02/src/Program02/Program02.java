package Program02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Program02
{
  private static final int DISPLAY_WIDTH = 640;
  private static final int DISPLAY_HEIGHT = 480;
  private static ArrayList<Polygon> polygons;
  private static ArrayList<float[]> all_edges = new ArrayList<>();
  private static ArrayList<float[]> global_edges = new ArrayList<>();
  private static ArrayList<float[]> active_edges = new ArrayList<>();
  private static ArrayList<float[]> edgesToRemove = new ArrayList<>();
  private static boolean parity = false;
  private static int scanLine = 0;
  private static int index = 0;
  private static int xValue = 0;
  private static int previousXValue = 0;

  public static void main(String[] args) 
  {
      polygons = FileHandler.getInstance().readFile();

      for(Polygon polygon : polygons)
      {
          System.out.println(polygon.toString());
      }

      // Populates the all_edges table.
      for (Polygon polygon : polygons)
      {
          for (Edge edge : polygon.getEdges())
          {
              float[] edgeData = new float[4];
              edgeData[0] = edge.getyMin();
              edgeData[1] = edge.getYMax();
              edgeData[2] = edge.getXValue();
              edgeData[3] = edge.getSlopeInverse();

              all_edges.add(edgeData);
          }
      }

      // Populates the global_edges table.
      for (int i = 0; i < all_edges.size(); i++)
      {
          if(all_edges.get(i)[3] != Float.POSITIVE_INFINITY
                  && all_edges.get(i)[3] != Float.NEGATIVE_INFINITY)
              global_edges.add(all_edges.get(i));
      }

      // Prints out the all_edges table.
      System.out.println("all_edges table:");
      printEdgeTable(all_edges);
      // Prints out the global_edges table.
      System.out.println("global_edges table:");
      printEdgeTable(global_edges);
      Collections.sort(global_edges, byCriteria);
      System.out.println("sorted global_edges table:");
      printEdgeTable(global_edges);

      scanLine = (int)global_edges.get(0)[0];


      for(int i = 0; i < global_edges.size(); i++)
      {
          if(global_edges.get(i)[0] == scanLine)
          {
              edgesToRemove.add(global_edges.get(i));
              active_edges.add(global_edges.get(i));
          }
      }

      removeEdges(global_edges, edgesToRemove);

      xValue = (int)active_edges.get(0)[2];

      // Prints out the active_edges table.
      System.out.println("active_edges table:");
      printEdgeTable(active_edges);
      // Prints out the global_edges table.
      System.out.println("global_edges table:");
      printEdgeTable(global_edges);
      
      Program02 program02 = new Program02();
      program02.start();
  }

  private static void printEdgeTable(ArrayList<float[]> edgeTable)
  {
      for (int i = 0; i < edgeTable.size(); i++)
      {
          System.out.println("Y-Min: " + edgeTable.get(i)[0]
                  + " Y-Max: " + edgeTable.get(i)[1]
                  + " X-Val: " + edgeTable.get(i)[2]
                  + " 1/m: " + edgeTable.get(i)[3]);
      }
  }

  private static void removeEdges(ArrayList<float[]> edgeTable, ArrayList<float[]> edgesToRemove)
  {
      int edgesCounter = edgesToRemove.size();
      for(int i = 0; i < edgesCounter; i++)
      {
          edgeTable.remove(edgesToRemove.get(0));
          edgesToRemove.remove(0);
      }
  }

  // Comparator for sorting the global_edges table.
  // Took me a while to figure this one out but comparators are totally worth it!
  private static final Comparator<float[]> byCriteria = (arg0, arg1) ->
  {
      if(Float.compare(arg0[0], arg1[0]) == 0)
      {
          if(Float.compare(arg0[2], arg1[2]) == 0)
          {
              if(Float.compare(arg0[1], arg1[1]) == 0)
              {
                  return Float.compare(arg0[3], arg1[3]);
              }
              else
                  return Float.compare(arg0[1], arg1[1]);
          }
          else
              return Float.compare(arg0[2], arg1[2]);
      }
      else
          return Float.compare(arg0[0], arg1[0]);
  };


    /**
   * Starts the OpenGL application.
   */
  public void start() 
  {
      try 
      {
          createWindow();
          initGL();
          render();
      } 
      catch (Exception e) 
      {
          e.printStackTrace();
      }
  }
  
  /**
   * Creates the Window with the specified size.
   * @throws Exception 
   */
  private void createWindow() throws Exception
  {
      Display.setFullscreen(false); 
      Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT)); 
      Display.setTitle("Program 02");
      Display.create(); 
  }
  
  /**
   * Initializes the GL.
   */
  private void initGL() 
  {
      glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
      glMatrixMode(GL_PROJECTION);
      glLoadIdentity();
      glOrtho(0, DISPLAY_WIDTH, 0, DISPLAY_HEIGHT, 1, -1);
      glMatrixMode(GL_MODELVIEW);
      glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
  }
  
  /**
   * Renders the image.
   */
  private void render() 
  {
      while (!Display.isCloseRequested()) 
      {
          try
          { 
              glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
              glLoadIdentity();

              if(active_edges.size() != 0)
                  xValue = (int)active_edges.get(index)[2];

              for(int y = scanLine; y < DISPLAY_HEIGHT; y++)
              {
                  for(int x = 0; x < DISPLAY_WIDTH; x++)
                  {
                      if(x == xValue)
                      {
                          parity = !parity;

                          if(index < active_edges.size() - 1)
                          {
                              previousXValue = xValue;
                              index++;
                              xValue = (int) active_edges.get(index)[2];
                          }

                          if(xValue == previousXValue)
                          {
                              drawPoint(xValue, scanLine, 1, "RED");
                              index++;
                              xValue = (int)active_edges.get(index)[2];
                          }
                      }

                      if(parity)
                      {
                          drawPoint(x, scanLine, 1, "RED");
                      }
                  }
                  scanLine++;
                  index = 0;
                  parity = false;
                  updateActiveEdgesTable();
                  System.out.println("\n\n");
                  printEdgeTable(active_edges);
              }
              if(active_edges.size() != 0)
                scanLine = (int)active_edges.get(index)[0];


              Display.update();
              Display.sync(60);
         }
          catch(Exception e)
        {
            e.printStackTrace();
        }
      }
      Display.destroy();
  }

  private void updateActiveEdgesTable()
  {
      System.out.println("Scanline: " + scanLine);
      for (int i = 0; i < active_edges.size(); i++)
      {
          active_edges.get(i)[2] = active_edges.get(i)[2] + active_edges.get(i)[3];

          if(active_edges.get(i)[1] == scanLine)
              edgesToRemove.add(active_edges.get(i));
      }

      Collections.sort(active_edges, byCriteria);
      if(edgesToRemove.size() != 0)
          removeEdges(active_edges, edgesToRemove);
  }
  
  private void drawPoint(float x, float y, int size, String color)
  {
      //System.out.println("X: " + x + " - Y: " + y);
      
      // Applies the given color.
      if(color.equals("RED"))      
          glColor3f(1.0f, 0.0f, 0.0f);
      else if(color.equals("GREEN"))
          glColor3f(0.0f, 1.0f, 0.0f);
      else if(color.equals("BLUE"))
          glColor3f(0.0f, 0.0f, 1.0f);
      else if(color.equals("WHITE"))
          glColor3f(1.0f, 1.0f, 1.0f);
      
      glPointSize(size);
      glBegin(GL_POINTS);      
      glVertex2f (x, y);
      glEnd();
  }
}