package VirtualWorld.animals;

import VirtualWorld.Animal;
import VirtualWorld.Organism;
import VirtualWorld.World;

import java.awt.*;

/**
 * Created by Bartosz Łuczak on 07.05.2017.
 */


public class Human extends Animal {
    private int cooldown;
    private int actionKey;


    public Human(World world, int x, int y){
        this.strength =5;
        this.initiative=4;
        this.x=x;
        this.y=y;
        this.age=0;
        this.world=world;
        this.symbol='H';
        this.cooldown=0;
        this.color= Color.getHSBColor(0.061f,0.39f,0.99f);
        this.speciesName="Human";
        this.world.setHowManyOrganisms(this.world.getHowManyOrganisms()+1);
        this.world.getEvents().addEvent("New human has appeared!");

    }
    @Override
    public Animal creator(World world, int x, int y)
    {
        Animal w = new Human(world,x,y);
        return w;
    }
    public int getCooldown(){
        return this.cooldown;
    }
    public void setCooldown(int cd){
        this.cooldown=cd;
    }
    public void setActionKey(int key){
        this.actionKey=key;
    }
    private boolean strengthElixir(){
        if(getCooldown()==0) {
            this.setStrength(getStrength() + 5);
            this.world.getEvents().addEvent("You've used Elixir Of Strength!");
            this.world.getEvents().addEvent("Your actual strength: " + this.getStrength());
            this.setCooldown(10);
            return true;
        }
        else{
            this.world.getEvents().addEvent("You cannot use this skill! Patience, my fiend!");
            return false;
        }
    }

    private void checkSkill(){
        if(this.getCooldown()>0){
            this.setCooldown(getCooldown()-1);
            if(this.getCooldown()>=5) {
                this.setStrength((getStrength() - 1));
                if (this.getCooldown() > 5)
                    this.world.getEvents().addEvent("Your actual strength: " + this.getStrength());
                if (this.getCooldown() == 5)
                    this.world.getEvents().addEvent("Elixir has stopped working");
            }
        }
    }

    @Override
    public void action(){
        checkSkill();
        this.setAge(getAge()+1);
        int tempX=this.getX();
        int tempY=this.getY();
        switch(actionKey){
            case 1:
                tempX++;
                if (this.getWorld().getSides()==6 && this.getX()%2==0) {
                    tempY--;
                }
                break;
            case 2:
                tempX--;
                if (this.getWorld().getSides()==6 && this.getX()%2==1) {
                    tempY++;
                }
                break;
            case 3: tempY++;break;
            case 4: tempY--;break;
            case 5:
                tempX++;
                if (this.getWorld().getSides()==6 && this.getX()%2==1) {
                    tempY++;
                }
                break;
            case 6:
                tempX--;
                if (this.getWorld().getSides()==6 && this.getX()%2==0) {
                    tempY--;
                }
                break;
            case 10: strengthElixir();break;
        }
        if(tempX!=this.getX() || tempY!=this.getY()) {
            Organism other = this.world.getOrganism(tempX, tempY);
            if (other != null) other.collision(this);
            else if (checkPosition(tempX, tempY)) {
                this.setX(tempX);
                this.setY(tempY);
            }
        }
    }





}
