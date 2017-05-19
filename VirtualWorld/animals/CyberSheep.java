package VirtualWorld.animals;

import VirtualWorld.Animal;
import VirtualWorld.Organism;
import VirtualWorld.World;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bartosz Łuczak on 08.05.2017.
 */
public class CyberSheep extends Animal {
    public CyberSheep(World world, int x, int y){
        this.strength =11;
        this.initiative=4;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='C';
        this.color= Color.getHSBColor(0.58f,0.79f,0.33f);
        this.speciesName="CyberSheep";
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New CyberSheep has appeared!");
    }
    @Override
    public Animal creator(World world, int x, int y)
    {
        Animal w = new CyberSheep(world,x,y);
        return w;
    }

    private int shortestWay(Organism other){
        int x,y;
        if(this.getX()>=other.getX()) x = this.getX() - other.getX();
        else x=other.getX()-this.getX();
        if(this.getY()>=other.getY()) y = this.getY() - other.getY();
        else y=other.getY()-this.getY();
        return x+y;
    }

    private void goToHogweed(Organism other){
        int x=0,y=0;
        if(this.getX()>=other.getX()) x = this.getX() - other.getX();
        else x=other.getX()-this.getX();
        if(this.getY()>=other.getY()) y = this.getY() - other.getY();
        else y=other.getY()-this.getY();
        int tempX=this.getX();
        int tempY=this.getY();
        if ((x == 1 && y == 0)||(y==1 && x==0))
        {
            other.fight(this);
            return;
        }
        else if (x == 0 && y > 0)
        {
            if (this.getY() >= other.getY()) tempY--;
		else tempY++;
        }
        else if (y == 0 && x > 0)
        {
            if (this.getX() >= other.getX()) tempX--;
		else tempX++;
        }
        else if (x > 0 && y > 0)
        {
            Random generator = new Random();
            int los = generator.nextInt(100);
            if (los >= 50)
            {
                if (this.getY() >= other.getY()) tempY--;
			else tempY++;
            }
            else
            {
                if (this.getX() >= other.getX()) tempX--;
			else tempX++;
            }
        }
        Organism temp = this.getWorld().getOrganism(tempX,tempY);
        if(temp==null){
            this.setX(tempX);
            this.setY(tempY);
        }
        else if(temp!=this) temp.collision(this);
        this.getWorld().getEvents().addEvent("CyberSheep: Turns till next elimination of Hogweed: " + (x+y-1));
    }
    @Override
    public void action(){
        int temp,index=-1;
        boolean cybermode=false;
        int shortest = Integer.MAX_VALUE;
        for(int i=0;i<this.getWorld().getHowManyOrganisms();i++){
            if(this.getWorld().getOrganism(i).getSymbol()=='X'){
                cybermode=true;
                temp=shortestWay(this.getWorld().getOrganism(i));
                if(temp<shortest){
                    shortest=temp;
                    index=i;
                }
            }
        }
        if(cybermode){
            this.goToHogweed(this.getWorld().getOrganism(index));
        }
        else
            super.action();
    }
}
