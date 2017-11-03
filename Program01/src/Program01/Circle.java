package Program01;

public class Circle extends Primitive
{
    private float xCenter;
    private float yCenter;
    private float radius;
    
    public Circle(float xCenter, float yCenter, float radius)
    {
        super("Circle");
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
    }
    
    public float getxCenter() {
        return xCenter;
    }

    public void setxCenter(float xCenter) {
        this.xCenter = xCenter;
    }

    public float getyCenter() {
        return yCenter;
    }

    public void setyCenter(float yCenter) {
        this.yCenter = yCenter;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }    
    
    @Override
    public String toString() 
    {
        return "Circle{" + "xCenter=" + xCenter + ", yCenter=" + yCenter + ", radius=" + radius + '}';
    }
}
