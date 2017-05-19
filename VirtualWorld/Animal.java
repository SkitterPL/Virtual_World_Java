package VirtualWorld;


import java.awt.*;
import java.util.Random;

/**
 * Created by Bartosz Łuczak on 22.04.2017.
 */
public abstract class Animal extends Organism {
    public Animal(){
        this.type="Animal";
    }

    @Override
    public void action(){
        Random generator = new Random();
        this.setAge(this.getAge()+1);
        Point roll= world.rollPosition(this);
        int x=(int)roll.getX();
        int y=(int)roll.getY();
        Organism other=this.getWorld().getOrganism(x,y);
        if(other==null)
        {
            this.setX(x);
            this.setY(y);
        }

        else other.collision(this);
    }
    @Override
    public void collision(Organism other){
        if(this.getSymbol()==other.getSymbol())
        {
            multiplication(other);
        }
        else
            fight(other);
    }
    @Override
    public void multiplication(Organism other){
        Random generator = new Random();

        if(this.getAge()>=10 && other.getAge()>=10) {

            Point roll= world.rollPosition(1, this);
            int x=(int)roll.getX();
            int y=(int)roll.getY();
            Organism temp=this.getWorld().getOrganism(x,y);
            if(temp==null)
            {
                this.getWorld().getOrganisms().add(other.creator(this.getWorld(),x,y));
            }
        }
    }
    @Override
    public void fight(Organism other){
        if(this.getStrength()>=other.getStrength()){
            this.getWorld().getEvents().addEvent(this.getSpeciesName()+ " defends itself and kills " + other.getSpeciesName()+"!");
            this.getWorld().deleteOrganism(other);
        }
        else {
            this.getWorld().getEvents().addEvent(other.getSpeciesName()+ " attacks and kills " + this.getSpeciesName()+"!");
            other.setX(this.getX());
            other.setY(this.getY());
            this.getWorld().deleteOrganism(this);
        }
    }
    @Override
    public abstract Animal creator(World world, int x, int y);
}
