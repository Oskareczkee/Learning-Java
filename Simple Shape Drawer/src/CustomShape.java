import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomShape {
    private List<Point> points = new ArrayList<>();
    public void setPoints(List<Point> points)
    {
        this.points = points;
    }

    private double Distance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
    public double CalculateCircumference()
    {
        int circumference = 0;
        int pointCount = points.size();
        for(int x =0; x<pointCount;x++)
        {
            Point p1 = points.get(x);
            Point p2 = points.get((x+1) % pointCount);

            circumference += Distance(p1,p2);
        }
        return circumference;
    }
    public List<Point> getPoints()
    {
        return points;
    }
}
