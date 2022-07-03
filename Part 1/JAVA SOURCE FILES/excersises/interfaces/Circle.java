package excersises.interfaces;

public class Circle extends Shape
{
    private int radius;

    {
        radius =0;
    }

    public boolean fitsText(String text)
    {
        int maxChars = (int)(Math.PI * Math.pow(radius, 2));
        if(text.length()<= maxChars)
            return true;
        return false;
    }

    public Circle()
    {
        super();
    }

    public Circle(int _radius)
    {
        super();
        radius=_radius;
    }

    public Circle(int _radius, Color color)
    {
        super(color);
        radius=_radius;
    }

    public int getRadius(){return radius;}
    public void setRadius(int _radius){radius=_radius;}
}
