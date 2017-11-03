package Program01;

public class Line extends Primitive
{
    private float xInitial;
    private float yInitial;
    private float xFinal;
    private float yFinal;

    public Line(float xInitial, float yInitial, float xFinal, float yFinal) {
        super("Line");
        this.xInitial = xInitial;
        this.yInitial = yInitial;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
    }

    public float getxInitial() {
        return xInitial;
    }

    public void setxInitial(float xInitial) {
        this.xInitial = xInitial;
    }

    public float getyInitial() {
        return yInitial;
    }

    public void setyInitial(float yInitial) {
        this.yInitial = yInitial;
    }

    public float getxFinal() {
        return xFinal;
    }

    public void setxFinal(float xFinal) {
        this.xFinal = xFinal;
    }

    public float getyFinal() {
        return yFinal;
    }

    public void setyFinal(float yFinal) {
        this.yFinal = yFinal;
    }

    @Override
    public String toString() {
        return "Line{" + "xInitial=" + xInitial + ", yInitial=" + yInitial + ", xFinal=" + xFinal + ", yFinal=" + yFinal + '}';
    }
    
}
