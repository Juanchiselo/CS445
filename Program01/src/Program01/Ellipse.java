package Program01;

public class Ellipse extends Primitive
{
    private float xCenter;
    private float yCenter;
    private float rx;
    private float ry;
    
    public Ellipse(float xCenter, float yCenter, float rx, float ry) 
    {
        super("Ellipse");
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.rx = rx;
        this.ry = ry;
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

    public float getRx() {
        return rx;
    }

    public void setRx(float rx) {
        this.rx = rx;
    }

    public float getRy() {
        return ry;
    }

    public void setRy(float ry) {
        this.ry = ry;
    }
    
    @Override
    public String toString() {
        return "Ellipse{" + "xCenter=" + xCenter + ", yCenter=" + yCenter + ", rx=" + rx + ", ry=" + ry + '}';
    }
}
