package Program02;

public class Transformation
{
    private String type;
    private float x;
    private float y;
    private float z;
    private float rotationAngle;
    private float pivotX;
    private float pivotY;
    private float scaleX;
    private float scaleY;
    private float scaleZ;

    public Transformation(String type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getRotationAngle()
    {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle)
    {
        this.rotationAngle = rotationAngle;
    }

    public float getPivotX()
    {
        return pivotX;
    }

    public void setPivotX(float pivotX)
    {
        this.pivotX = pivotX;
    }

    public float getPivotY()
    {
        return pivotY;
    }

    public void setPivotY(float pivotY)
    {
        this.pivotY = pivotY;
    }

    public float getScaleX()
    {
        return scaleX;
    }

    public void setScaleX(float scaleX)
    {
        this.scaleX = scaleX;
    }

    public float getScaleY()
    {
        return scaleY;
    }

    public void setScaleY(float scaleY)
    {
        this.scaleY = scaleY;
    }

    public float getScaleZ()
    {
        return scaleZ;
    }

    public void setScaleZ(float scaleZ)
    {
        this.scaleZ = scaleZ;
    }

    @Override
    public String toString() {
        return "Transformation{" +
                "type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
