package view.old;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;

import controller.Arbre;
import controller.ElementManager;
import controller.Figure;

public class FrameCanvas
{
    private JPanel jframe;
    private mxGraph graph;

    void toPng(String str){
    	BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);
    	try {
			ImageIO.write(image, "PNG", new File(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally{
    		System.out.println("hertr");
    	} 
    } 
    
    
    public FrameCanvas()
    {
        mxGraphics2DCanvas.putShape("square", new mxHeaderShape());

        jframe = new JPanel();

        this.graph = new mxGraph();

        graph.setDropEnabled(false);

        graph.getSelectionModel().setSingleSelection(true);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false);
        graphComponent.setFoldingEnabled(false);

        graphComponent.getGraphControl().addMouseListener(new MouseListener()
        {

            @Override
            public void mouseReleased(MouseEvent e)
            {
                //

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                if (graph.getSelectionCell() != null)
                {
                    if (graph.getSelectionCell() instanceof mxCell)
                    {
                        mxCell cell = (mxCell) graph.getSelectionCell();
                        if (cell.isEdge())
                        {
                            graph.clearSelection();
                        }
                    }
                }

            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                //

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                //

            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
            //    Arbre.getInstance().getHeader().getHeader().setFlagPasPas(false);
                if (graph.getSelectionCell() != null)
                {
                    if (graph.getSelectionCell() instanceof mxCell)
                    {
                        mxCell cell = (mxCell) graph.getSelectionCell();
                        if (!cell.isEdge())
                        {
                            @SuppressWarnings("unused")
                            Figure figure = ElementManager.getInstance().getFigure(cell.getId());
                            // ElementManager.getInstance().ajouterIf(figure);
                        }
                        else
                        {
                            graph.clearSelection();
                        }
                    }
                }
                else
                {
                    // Arbre.getInstance().getHeader().getHeader().launchArbre();
                }
            }
        });

        //
        // graphComponent.getGraphControl().addMouseMotionListener(new mxMouseAdapter()
        // {
        // @Override
        // public void mouseMoved(MouseEvent e)
        // {
        // if (graph.getSelectionModel().size() == 1)
        // {
        // if (graphComponent.getCellAt(e.getX(), e.getY()) != null)
        // {
        // if (graphComponent.getCellAt(e.getX(), e.getY()).equals(graph.getSelectionCell()))
        // {
        // graphComponent.setConnectable(true);
        //
        // }
        // else
        // {
        // graphComponent.setConnectable(false);
        // }
        // }
        // else
        // {
        // graphComponent.setConnectable(false);
        //
        // }
        //
        // }
        // else
        // {
        // graphComponent.setConnectable(false);
        // }
        //
        // // here I want to check if the mouse position is over a cell
        // // I only want to check if the mouse is over one (or more?) cells
        // }
        // });
        jframe.setLayout(new BorderLayout());
        jframe.add(graphComponent, BorderLayout.CENTER);

        // jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // jframe.setSize(800, 600);
        // jframe.setResizable(false);
        // jframe.setTitle("Canvas");
        // jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

    }

    public JPanel getJframe()
    {
        return jframe;
    }

    public void setJframe(JPanel jframe)
    {
        this.jframe = jframe;
    }

    public mxGraph getGraph()
    {
        return graph;
    }

    public void setGraph(mxGraph graph)
    {
        this.graph = graph;
    }

}
