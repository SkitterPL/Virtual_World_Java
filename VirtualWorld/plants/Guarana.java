package VirtualWorld.plants;

import VirtualWorld.Organism;
import VirtualWorld.Plant;
import VirtualWorld.World;

import java.awt.*;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
public class Guarana extends Plant {
    public Guarana(World world, int x, int y){
        this.strength =0;
        this.initiative=0;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='U';
        this.speciesName="Guarana";
        this.chanceForSpreading=5;
        this.color= Color.red.brighter();
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New guarana has appeared!");
    }

    @Override
    public Plant creator(World world, int x, int y){
        Plant p = new Guarana(world,x,y);
        return p;
    }
    @Override
    public void fight(Organism other){
        int x=this.getX(), y=this.getY();
        other.setX(x); other.setY(y);
        other.setStrength(other.getStrength()+3);
        this.world.getEvents().addEvent(other.getSpeciesName() + " eats guarana and increases its strength to "+ other.getStrength()+"!");
        other.getWorld().deleteOrganism(this);

    }
}
