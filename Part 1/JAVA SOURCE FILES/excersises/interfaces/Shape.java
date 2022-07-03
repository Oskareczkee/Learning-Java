package excersises.interfaces;

public abstract class Shape implements IShape
{
    public static class Color
    {
        public byte R;
        public byte G;
        public byte B;
        public Color()
        {
            R=0; G=0; B = 0;
        }

        public Color(byte r, byte g, byte b)
        {
            R=r; G = g; B = b;
        }
    }

    public Shape(Color _color)
    {
        color=_color;
    }

    public Shape()
    {
        color = new Color();
    }

    private Color color;
    public Color getColor(){return color;}
}
