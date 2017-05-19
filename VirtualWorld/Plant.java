package VirtualWorld;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bartosz Łuczak on 24.04.2017.
 */
public abstract class Plant extends Organism {
    protected int chanceForSpreading;

    public Plant(){
        this.type="Plant";
    }
    @Override
    public void collision(Organism other){
        if(other==null)
            multiplication(other);
        else
            fight(other);
    }
    @Override
    public void action(){
        Random generator = new Random();
        this.setAge(this.getAge()+1);
        Point roll= world.rollPosition(this);
        int x=(int)roll.getX();
        int y=(int)roll.getY();
        Organism temp=this.world.getOrganism(x,y);
        if(temp==null){
            int fate=generator.nextInt(100);
            if(fate<this.getChanceForSpreading())
                collision(temp);
        }
    }
    @Override
    public void multiplication(Organism other){
        Point roll= world.rollPosition(this);
        int x=(int)roll.getX();
        int y=(int)roll.getY();
        Organism temp=this.getWorld().getOrganism(x,y);
        if(temp==null){
            this.getWorld().getOrganisms().add(this.creator(this.getWorld(),x,y));
        }
    }
    @Override
    public void fight(Organism other){
        if(this.getStrength()>other.getStrength()){
            this.getWorld().getEvents().addEvent(this.getSpeciesName()+ " defends himself and kills " + other.getSpeciesName()+"!");
            this.getWorld().deleteOrganism(other);
        }
        else {
            this.getWorld().getEvents().addEvent(other.getSpeciesName()+ " eats " + this.getSpeciesName()+"!");
            other.setX(this.getX());
            other.setY(this.getY());
            this.getWorld().deleteOrganism(this);
        }
    }
    public int getChanceForSpreading(){
        return this.chanceForSpreading;
    }
    public void setChanceForSpreading(int chance){
        this.chanceForSpreading=chance;
    }
    @Override
    public abstract Plant creator(World world, int x, int y);
}
