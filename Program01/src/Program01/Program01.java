package Program01;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Program01 
{
  public static final int DISPLAY_HEIGHT = 480;
  public static final int DISPLAY_WIDTH = 640;
  public static ArrayList<Primitive> primitives;

  public static void main(String[] args) 
  {
      primitives = FileHandler.getInstance().readFile();
      
      Program01 program01 = new Program01();
      program01.start();      
  }
  
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
      Display.setTitle("Program 01"); 
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
              
              for(Primitive primitive : primitives)
              {
                  if(primitive.getType().equals("Line"))
                      drawLine((Line)primitive);
                  else if(primitive.getType().equals("Circle"))
                      drawCircle((Circle)primitive);
                  else
                      drawEllipse((Ellipse)primitive);
              }
              
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
  
  /**
   * The function to draw a line.
   * @param line - The line to draw.
   */
  private void drawLine(Line line)
  {
      float dx = line.getxFinal() - line.getxInitial();
      float dy = line.getyFinal() - line.getyInitial();
      float incrementRight = 2 * dy;
      float incrementUpRight = 2 * (dy - dx);
      float d = 2 * (dy) - dx;
      float x = line.getxInitial();
      float y = line.getyInitial();
      
//      System.out.println("dx = " + dx + " dy: " + dy + " IR: " + incrementRight
//        + " IUP: " + incrementUpRight + " d: " + d + " x: " + x + " y: " + y);
      
      while(x < line.getxFinal())
      {
          drawPoint(x, y, 1, "RED");
          
          if(d > 0)
          {
              d += incrementUpRight;
              x++;
              y++;
          }
          else
          {
              d += incrementRight;
              x++;
          }
      }   
  }
  
  /**
   * The function that draws a circle.
   * @param circle - The circle to draw.
   */
  private void drawCircle(Circle circle)
  {
      float x = circle.getxCenter();
      float y = circle.getyCenter();
      double theta = 0;
      
      for(int i = 0; i < 360; i++)
      {
          
          x = (int) (circle.getRadius() * Math.cos(theta));
          y = (int) (circle.getRadius() * Math.sin(theta)); 
          
          theta += Math.toDegrees(1);
          
          drawPoint(circle.getxCenter() + x, circle.getyCenter() + y, 1, "BLUE");
      }
      
      
//      while(theta != Math.PI * 2)
//      {
//          
//      }
  }
  
  private void drawEllipse(Ellipse ellipse)
  {
      float x = ellipse.getxCenter();
      float y = ellipse.getyCenter();
      double theta = 0;
      
      for(int i = 0; i < 360; i++)
      {
          
          x = (float) (ellipse.getRx() * Math.cos(theta));
          y = (float) (ellipse.getRy() * Math.sin(theta));
          
          theta += Math.toDegrees(1);
          
          drawPoint(ellipse.getxCenter() + x, ellipse.getyCenter() + y,
                  1, "GREEN");
      }
      
//      while(theta != Math.PI * 2)
//      {
//          x = (float) (ellipse.getRx() * Math.cos(theta));
//          y = (float) (ellipse.getRy() * Math.sin(theta));
//          
//          drawPoint(ellipse.getxCenter() + x, ellipse.getyCenter() + y,
//                  1, "Green");
//      }
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