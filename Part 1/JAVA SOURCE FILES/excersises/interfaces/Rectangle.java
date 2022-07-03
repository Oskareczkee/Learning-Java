package excersises.interfaces;

import Tools.*;

public class Rectangle extends Shape
{
    private int length;
    private int width;

    {
        length=0;
        width=0;
    }

    public Rectangle()
    {
        super();
    }

    public Rectangle(int _length, int _width)
    {
        super();
        length=_length;
        width=_width;
    }

    public Rectangle(int _length, int _width, Color color)
    {
        super(color);
        length=_length;
        width=_width;
    }

    //this is just my implementation, propably wouldn't work irl
    public boolean fitsText(String text)
    {
        int maxChars = length*width;
        if(text.length()<=maxChars)
            return true;
        return false;
    }

    public int getLength(){return length;}
    public int getWidth(){return width;}

    public void setLength(int _length){length=_length;}
    public void setWidth(int _width){width=_width;}

    public Pair<Integer, Integer> getDimensions()
    {
        return new Pair<>(length, width);
    }

    public void setDimensions(Pair<Integer, Integer> dims)
    {
        length = dims.first;
        width=dims.second;
    }
}
