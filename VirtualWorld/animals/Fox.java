package VirtualWorld.animals;

import VirtualWorld.Animal;
import VirtualWorld.Organism;
import VirtualWorld.World;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bartosz Łuczak on 24.04.2017.
 */
public class Fox extends Animal {

    public Fox(World world, int x, int y){
        this.strength =3;
        this.initiative=7;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='F';
        this.color= Color.getHSBColor(0.072f,1,1);
        this.speciesName="Fox";
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New fox has appeared!");
    }
    @Override
    public Animal creator(World world, int x, int y)
    {
        Animal w = new Fox(world,x,y);
        return w;
    }
    @Override
    public void action(){
        Random generator = new Random();
        this.setAge(this.getAge()+1);
        Point roll= world.rollPosition(this);
        int x=(int)roll.getX();
        int y=(int)roll.getY();
        Organism other=this.getWorld().getOrganism(x,y);
        if(other==null)
        {
            this.setX(x);
            this.setY(y);
        }
        else if(other.getStrength()<=this.getStrength())
            other.collision(this);
        else
            action();
    }
}
