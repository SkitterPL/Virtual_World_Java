package VirtualWorld.frames;

import VirtualWorld.World;

import java.awt.*;
import java.awt.event.MouseEvent;

import static java.lang.StrictMath.*;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
public class HexFrame extends BoardFrame {
    HexFrame(World world, AddFrame a){
        this.addf=a;
        this.world=world;
        side=15;
        drawBoard();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        HexGrid temp;
        for (Shape s : shapes) {
            g2d.setColor(Color.GRAY);
            for (int i = 0; i < world.getHowManyOrganisms(); i++) {
                temp = (HexGrid)s;
                if (this.world.getOrganism(i).getX()== temp.x && this.world.getOrganism(i).getY()==temp.y) {
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
        HexGrid temp;
        for (Shape s : this.shapes) {
            temp = (HexGrid)s;
            if (s.contains(me.getPoint())) {
                int x=temp.x;
                int y=temp.y;
                System.out.println("Clicked a x: " + x+" y:"+ y);
                if(this.world.getOrganism(x,y)==null) {
                    this.addf.unlockButtons(x,y, this);
                }
            }
        }
    }

    @Override
    protected void drawBoard(){
        int height=world.getHeight();
        int width=world.getWidth();
        dim = new Dimension(width*2*side, height*2*side);
        for(int i=0, h=side; i<height; i++, h=h+(int)(2*(side/2)*sqrt(3)+3))
        {
            for(int j=0, w=side; j<width; j=j+2, w=(int)(w+2.5*side+11)) {
                shapes.add(new HexGrid(j,i,w,h,side));
            }
        }
        for(int i=0, h=(int)(side+sqrt((side*side)/2)+4); i<height; i++, h=h+(int)(2*(side/2)*sqrt(3)+3))
        {
            for(int j=1, w=(int)(3*side-sqrt((side*side)/2)+5); j<width; j=j+2, w=(int)(w+2.5*side+11)) {
                shapes.add(new HexGrid(j,i,w,h,side));
            }
        }
    }
}


class HexGrid extends Polygon{
    public int x,y;
    HexGrid(int cordX, int cordY, int x, int y, int r){
        this.x=cordX;
        this.y=cordY;
        for(int i=0; i<6; i++)
            this.addPoint((int) (x + r * cos(i * 2 * Math.PI / 6)), (int) (y + r * sin(i * 2 * Math.PI / 6)));
    }
}
