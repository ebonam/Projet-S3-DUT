package view.old;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.shape.mxBasicShape;
import com.mxgraph.view.mxCellState;

public class mxHeaderShape extends mxBasicShape
{
    public Shape createShape(mxGraphics2DCanvas canvas, mxCellState state)
    {
        Rectangle temp = state.getRectangle();
        int x = temp.x;
        int y = temp.y;
        int w = temp.width;
        int h = temp.height;
        // String direction = mxUtils.getString(state.getStyle(), mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_EAST);
        Polygon square = new Polygon();

        square.addPoint(x + 50, y);
        square.addPoint(x, y + h);
        square.addPoint(x + w - 50, y + h);
        square.addPoint(x + w, y);

        return square;
    }
}
