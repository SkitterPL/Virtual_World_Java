package VirtualWorld.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import VirtualWorld.World;

/**
 * Created by Bartosz Łuczak on 22.04.2017.
 */

abstract public class BoardFrame extends JPanel{

    protected Dimension dim;
    protected ArrayList<Shape> shapes;
    protected World world;
    protected int side;
    protected AddFrame addf;

    BoardFrame() {
        shapes = new ArrayList<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                addAnimal(me);
            }
        });
    }

    abstract protected void addAnimal(MouseEvent me);
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return dim;
    }

    abstract protected void drawBoard();


}
