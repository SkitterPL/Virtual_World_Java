package VirtualWorld.animals;

import VirtualWorld.Animal;
import VirtualWorld.World;

import java.awt.*;

/**
 * Created by Bartosz Łuczak on 26.04.2017.
 */
public class Antilope extends Animal{

    public Antilope(World world, int x, int y){
        this.strength =4;
        this.initiative=4;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='A';
        this.setActionRange(2);
        this.color= Color.getHSBColor(0.083f,0.55f,0.55f);
        this.speciesName="Antilope";
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New antilope has appeared!");
    }
    @Override
    public Animal creator(World world, int x, int y)
    {
        Animal w = new Antilope(world,x,y);
        return w;
    }
}
