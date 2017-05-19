package VirtualWorld.plants;

import VirtualWorld.Organism;
import VirtualWorld.Plant;
import VirtualWorld.World;

import java.awt.*;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
public class Belladona extends Plant {
    public Belladona(World world, int x, int y){
        this.strength =99;
        this.initiative=0;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='B';
        this.speciesName="Belladona";
        this.chanceForSpreading=5;
        this.color= Color.getHSBColor(0.74f,0.79f,0.65f);
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New belladona has appeared!");
    }

    @Override
    public Plant creator(World world, int x, int y){
        Plant p = new Belladona(world,x,y);
        return p;
    }
    @Override
    public void fight(Organism other){
        this.world.getEvents().addEvent(other.getSpeciesName() + " eats belladona and dies!");
        this.getWorld().deleteOrganism(this);
        other.getWorld().deleteOrganism(other);

    }
}
