package VirtualWorld.plants;

import VirtualWorld.Plant;
import VirtualWorld.World;

import java.awt.*;

/**
 * Created by Bartosz Łuczak on 24.04.2017.
 */
public class Grass extends Plant{

    public Grass(World world, int x, int y){
        this.strength =0;
        this.initiative=0;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='G';
        this.speciesName="Grass";
        this.chanceForSpreading=10;
        this.color= Color.getHSBColor(0.35f,0.84f,0.34f); //Darkgreen
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New grass has appeared!");
    }
    @Override
    public Plant creator(World world, int x, int y){
        Plant p = new Grass(world,x,y);
        return p;
    }

}
