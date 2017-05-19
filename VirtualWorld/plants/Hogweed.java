package VirtualWorld.plants;

import VirtualWorld.Organism;
import VirtualWorld.Plant;
import VirtualWorld.World;

import java.awt.*;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
public class Hogweed extends Plant {
    public Hogweed(World world, int x, int y){
        this.strength =10;
        this.initiative=0;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='X';
        this.speciesName="Sosnowsky's Hogweed";
        this.chanceForSpreading=0;
        this.color= Color.green.darker();
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New Sosnowsky's Hogweed has appeared!");
    }

    @Override
    public Plant creator(World world, int x, int y){
        Plant p = new Hogweed(world,x,y);
        return p;
    }
    @Override
    public void action(){
        Organism other;
        String killed  = "Sosnowsky's Hogweed is deadly and kills ";
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                other=this.world.getOrganism(this.getX()+i, this.getY()+j);
                if(other!=null){
                    if(other.getType()=="Plant" || other.getSymbol()=='C')
                        continue;
                    else{
                        killed+=other.getSpeciesName();
                        this.getWorld().deleteOrganism(other);
                    }
                }
            }
        }
        if(killed!="Sosnowsky's Hogweed is deadly and kills "){
            this.getWorld().getEvents().addEvent(killed);
        }
        super.action();
    }
}
