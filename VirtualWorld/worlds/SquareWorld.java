package VirtualWorld.worlds;

import VirtualWorld.Organism;
import VirtualWorld.World;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bartosz Łuczak on 22.04.2017.
 */
public class SquareWorld extends World{

    public SquareWorld(int x, int y){
        this.width=x;
        this.height=y;
        this.setSides(4);
    }

    @Override
    public World creator(int x, int y){
        World w = new SquareWorld(x,y);
        return w;
    }
    @Override
    public Point rollPosition(Organism organism){
        Random generator = new Random();
        int x,y,fate;
        do{
            x= organism.getX();
            y= organism.getY();
            fate=generator.nextInt(100);
            if(fate<25)
                x= organism.getX()+ organism.getActionRange();
            else if(fate<50)
                x= organism.getX()- organism.getActionRange();
            else if(fate<75)
                y= organism.getY()+ organism.getActionRange();
            else if(fate<100)
                y= organism.getY()- organism.getActionRange();
            organism.checkPosition(x,y);
        }while(!(organism.checkPosition(x,y)));
        return new Point(x,y);
    }
}
