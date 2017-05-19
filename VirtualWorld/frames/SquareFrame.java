package VirtualWorld.frames;

import VirtualWorld.World;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
public class SquareFrame extends BoardFrame {
    public SquareFrame(World world, AddFrame a){
        this.addf=a;
        this.world=world;
        side =20;
        drawBoard();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        for (Shape s : shapes) {
            g2d.setColor(Color.GRAY);
            for (int i = 0; i < world.getHowManyOrganisms(); i++) {
                if (this.world.getOrganism(i).getX() + (this.world.getOrganism(i).getY()) * this.world.getWidth() == shapes.indexOf(s)) {
                    g2d.setColor(this.world.getOrganism(i).getColor());
                    break;
                }
            }
            g2d.fill(s);
            g2d.draw(s);
        }
    }
    @Override
    protected void addAnimal(MouseEvent me){
        for (Shape s : this.shapes) {

            if (s.contains(me.getPoint())) {
                int x=this.shapes.indexOf(s)%this.world.getWidth();
                int y=this.shapes.indexOf(s)/world.getWidth();
                System.out.println("Clicked a x: " + x+" y:"+ y);
                if(this.world.getOrganism(x,y)==null) {
                    this.addf.unlockButtons(x,y, this);
                }
            }
        }
    }
    @Override
    protected void drawBoard(){
        int width=world.getWidth();
        int height=world.getHeight();
        dim = new Dimension(width*(side+2), height*(side+2));

        for(int i=0, h=0; i<height; i++, h=h+side+2)
        {
            for(int j=0, w=0; j<width; j++, w=w+side+2) {
                shapes.add(new Rectangle2D.Double(w, h, side, side));
            }
        }
    }
}
