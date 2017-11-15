package Program02;

import java.util.Arrays;

public class Edge
{
    private Integer[] vertexA = new Integer[2];
    private Integer[] vertexB = new Integer[2];
    //private float slope;
    private float slopeInverse;
    private int xValue;
    private int yMin;
    private int yMax;

    public Edge(Integer[] vertexA, Integer[] vertexB)
    {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        calculateValues();
    }

    public Integer[] getVertexA() {
        return vertexA;
    }

    public void setVertexA(Integer[] vertexA) {
        this.vertexA = vertexA;
    }

    public Integer[] getVertexB() {
        return vertexB;
    }

    public void setVertexB(Integer[] vertexB) {
        this.vertexB = vertexB;
    }

//    public float getSlope()
//    {
//        return slope;
//    }
//
//    public void setSlope(float slope) {
//        this.slope = slope;
//    }

    public float getSlopeInverse()
    {
        return slopeInverse;
    }

    private void calculateValues()
    {
        try
        {
//            System.out.println("(" + vertexB[0] + " - " + vertexA[0] + ") / ("
//                    + vertexB[1] + " - " +  vertexA[1] + ")");
            //slope = (vertexB[1] - vertexA[1]) / (vertexB[0] - vertexA[0]);
            slopeInverse = (float)(vertexB[0] - vertexA[0]) / (vertexB[1] - vertexA[1]);
            //System.out.println("SlopeInverse: " + slopeInverse);
        }
        catch(ArithmeticException exception)
        {
            slopeInverse = Float.POSITIVE_INFINITY;
        }

        if(vertexA[1] <= vertexB[1])
        {
            yMin = vertexA[1];
            yMax = vertexB[1];
            xValue = vertexA[0];
        }
        else
        {
            yMin = vertexB[1];
            yMax = vertexA[1];
            xValue = vertexB[0];
        }
    }

    public int getyMin()
    {
        return yMin;
    }

    public int getYMax()
    {
        return yMax;
    }

    public int getXValue()
    {
        return xValue;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertexA=" + Arrays.toString(vertexA) +
                ", vertexB=" + Arrays.toString(vertexB) +
                //", slope=" + slope +
                '}';
    }
}
