package excersises.interfaces;

public class Sign extends Shape
{
    private String text;
    private Shape signShape;

    {
        text="";
        signShape = new Rectangle();
    }

    public boolean fitsText(String text)
    {
        return signShape.fitsText(text);
    }

    public Sign(){super();}
    public Sign(String _text, Shape shape)
    {
        text=_text;
        signShape=shape;
    }

    public void setText(String _text)
    {
        text=_text;
    }

    public void setShape(Shape shape)
    {
        signShape=shape;
    }

    public String getText(){return text;}
    public Shape getShape(){return signShape;}
}
