package VirtualWorld.animals;

import VirtualWorld.Animal;
import VirtualWorld.Organism;
import VirtualWorld.World;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bartosz Łuczak on 24.04.2017.
 */
public class Turtle extends Animal {

    public Turtle(World world, int x, int y){
        this.strength =2;
        this.initiative=1;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='T';
        this.color=Color.getHSBColor(0.1527f,0.79f,0.32f);
        this.speciesName="Turtle";
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New turtle has appeared!");
    }
    @Override
    public Animal creator(World world, int x, int y)
    {
        Animal w = new Sheep(world,x,y);
        return w;
    }
    @Override
    public void action(){
        Random generator=new Random();
        int fate=generator.nextInt(100);
        if(fate>75)
            super.action();
    }
    @Override
    public void fight(Organism other){
        if(other.getStrength()>2 && other.getStrength()<5)
            this.getWorld().getEvents().addEvent("Turtle has blocked the attack from "+other.getSpeciesName());
        else
            super.fight(other);
    }
}
