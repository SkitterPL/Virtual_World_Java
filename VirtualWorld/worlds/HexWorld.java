package VirtualWorld.worlds;

import VirtualWorld.Organism;
import VirtualWorld.World;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
public class HexWorld extends World {
    public HexWorld(int x, int y){
        this.setSides(6);
        this.width=x;
        this.height=y;

    }

    @Override
    public World creator(int x, int y){
        World w = new HexWorld(x,y);
        return w;
    }
    @Override
    public Point rollPosition(Organism organism){
        Random generator = new Random();
        int x,y,fate;
        do{
            x= organism.getX();
            y= organism.getY();
            fate=generator.nextInt(150);
            if(fate<25) {
                x= organism.getX()+ organism.getActionRange();
                if (organism.getX() % 2 == 1) {
                    y = organism.getY() + organism.getActionRange();
                }
            }
            else if(fate<50) {
                x = organism.getX() + organism.getActionRange();
                if (organism.getX() % 2 == 0) {
                    y = organism.getY() - organism.getActionRange();
                }
            }
            else if(fate<75)
                y= organism.getY()+ organism.getActionRange();
            else if(fate<100)
                y= organism.getY()- organism.getActionRange();
            else if(fate<125){
                x = organism.getX() - organism.getActionRange();
                if (organism.getX() % 2 == 0) {
                    y = organism.getY() - organism.getActionRange();
                }
            }
            else if(fate<150){
                x = organism.getX() - organism.getActionRange();
                if (organism.getX() % 2 == 1) {
                    y = organism.getY() + organism.getActionRange();
                }
            }
            organism.checkPosition(x,y);
        }while(!(organism.checkPosition(x,y)));
        return new Point(x,y);
    }
}
