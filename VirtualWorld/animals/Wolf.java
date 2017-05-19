package VirtualWorld.animals;

import VirtualWorld.Animal;
import VirtualWorld.World;

import java.awt.*;

/**
 * Created by Bartosz Łuczak on 22.04.2017.
 */
public class Wolf extends Animal {
    public Wolf(World world, int x, int y){
        this.strength =9;
        this.initiative=5;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='W';
        this.color= Color.BLACK;
        this.speciesName="Wolf";
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New wolf has appeared!");

    }
    @Override
    public Animal creator(World world, int x, int y)
    {
        Animal w = new Wolf(world,x,y);
        return w;
    }

}
