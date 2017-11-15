package Program02;

import java.util.*;

public class Polygon
{
    private float[] color = new float[3];
    private ArrayList<Integer[]> vertices = new ArrayList<>();
    private ArrayList<Transformation> transformations = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public Polygon()
    {
    }

    public Polygon(float[] color)
    {
        this.color = color;
    }

    public float[] getColor()
    {
        return color;
    }

    public void setColor(float[] color)
    {
        this.color = color;
    }

    public ArrayList<Integer[]> getVertices()
    {
        return vertices;
    }

    public void setVertices(ArrayList<Integer[]> vertices)
    {
        this.vertices = vertices;
    }

    public ArrayList<Transformation> getTransformations() {
        return transformations;
    }

    public void setTransformations(ArrayList<Transformation> transformations) {
        this.transformations = transformations;
    }

    public ArrayList<Edge> getEdges()
    {
        if(edges.size() == 0)
            calculateEdges();

        return edges;
    }

    public void calculateEdges()
    {
        Edge edge = null;

        for (int i = 0; i < vertices.size(); i++)
        {
            if(i < vertices.size() - 1)
                edge = new Edge(vertices.get(i), vertices.get(i + 1));
            else if(i == vertices.size() - 1)
                edge = new Edge(vertices.get(i), vertices.get(0));

            edges.add(edge);
        }
    }

    @Override
    public String toString()
    {
        String string = "Polygon:\n"
                + "\tColor = {R: " + color[0]
                + ", G: " + color[1]
                + ", B: " + color[2] + "}\n"
                + "Vertices:\n";
        for (int i = 0; i < vertices.size(); i++)
        {
            string += "\tV" + (i + 1) + " = {X: "
                    + vertices.get(i)[0]
                    + ", Y: " + vertices.get(i)[1] + "}\n";
        }
        string += "Transformations:\n";
        for (int i = 0; i < transformations.size(); i++)
        {
            string += "\tT" + (i + 1) + " = " + transformations.get(i).getType()
                    + " {X: " + transformations.get(i).getX()
                    + " ,Y: " + transformations.get(i).getY()
                    + " ,Z: " + transformations.get(i).getZ() + "}\n";
        }
        getEdges();
        string += "Edges:\n";
        for(int i = 0; i < edges.size(); i++)
        {
            string += "\tY-Min: " + edges.get(i).getyMin()
                    + " Y-Max: " + edges.get(i).getYMax()
                    + " X-Val: " + edges.get(i).getXValue()
                    + " 1/m: " + edges.get(i).getSlopeInverse() + "\n";
        }

        return string;
    }
}
