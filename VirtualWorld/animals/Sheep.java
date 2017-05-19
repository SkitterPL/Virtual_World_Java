package VirtualWorld.animals;

import VirtualWorld.Animal;
import VirtualWorld.World;

import java.awt.*;

//import javafx.scene.paint.Color;

/**
 * Created by Bartosz Łuczak on 23.04.2017.
 */
public class Sheep extends Animal{
    public Sheep(World world, int x, int y){
        this.strength =4;
        this.initiative=4;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='O';
        this.color= Color.WHITE;
        this.speciesName="Sheep";
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New sheep has appeared!");
    }
    @Override
    public Animal creator(World world, int x, int y)
    {
        Animal w = new Sheep(world,x,y);
        return w;
    }
}
