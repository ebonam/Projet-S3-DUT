package controller;

import java.util.Stack;

import view.old.FrameCanvas;

public class Arbre
{
    private static Arbre INSTANCE;

    private FigureHeader header;
    private FrameCanvas canvas;

    private Arbre()
    {
        this.canvas = new FrameCanvas();
    }

    public static Arbre getInstance()
    {
        if (Arbre.INSTANCE == null)
        {
            Arbre.INSTANCE = new Arbre();
        }
        return Arbre.INSTANCE;
    }

    public FigureHeader getHeader()
    {
        return header;
    }

    public void setHeader(FigureHeader header)
    {
        this.header = header;
    }

    public FrameCanvas getCanvas()
    {
        return canvas;
    }

    public void setCanvas(FrameCanvas canvas)
    {
        this.canvas = canvas;
    }
}
