package VirtualWorld.plants;

import VirtualWorld.Plant;
import VirtualWorld.World;

import java.awt.*;

/**
 * Created by Bartosz Łuczak on 24.04.2017.
 */
public class Dandelion extends Plant {

    public Dandelion(World world, int x, int y){
        this.strength =0;
        this.initiative=0;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='D';
        this.speciesName="Dandelion";
        this.chanceForSpreading=5;
        this.color= Color.YELLOW;
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New dandelion has appeared!");
    }
    @Override
    public Plant creator(World world, int x, int y){
        Plant p = new Dandelion(world,x,y);
        return p;
    }
    @Override
    public void action(){
        for(int i=0;i<3;i++){
            super.action();
        }
    }

}
