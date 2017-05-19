package VirtualWorld;
/**
 * Created by Bartosz Łuczak on 22.04.2017.
 */

import java.awt.*;


public abstract class Organism {
    protected int strength;
    protected int initiative;
    protected int x,y;
    protected int age;
    protected char symbol;
    private int actionRange;
    protected String type;
    protected Color color;
    protected World world;
    protected String speciesName;

    Organism(){
        this.setActionRange(1);
    }

    public void setStrength(int str){
        this.strength =str;
    }
    public void setInitiative(int ini){
        this.initiative=ini;
    }
    public void setY(int y){
        this.y=y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setSymbol(char symbol){
        this.symbol=symbol;
    }
    public void setColor(Color color){
        this.color=color;
    }
    public void setWorld(World world){
        this.world=world;
    }
    public void setSpeciesName(String name){
        this.speciesName=name;
    }

    public int getStrength() {
        return this.strength;
    }
    public int getInitiative(){
        return this.initiative;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getAge(){
        return this.age;
    }
    public char getSymbol(){
        return this.symbol;
    }
    public Color getColor(){
        return this.color;
    }
    public World getWorld(){
        return this.world;
    }
    public String getSpeciesName(){
        return this.speciesName;
    }
    public String getType(){
        return this.type;
    }

    public boolean checkPosition(int x, int y){
        if(x>=this.getWorld().getWidth() || x<0 || y>=this.getWorld().getHeight() || y<0)
            return false;
	    else return true;
    };

    public abstract Organism creator(World world, int x, int y);
    public abstract void action();
    public abstract void collision(Organism other);
    public abstract void fight(Organism other);
    public abstract void multiplication(Organism other);


    public int getActionRange() {
        return actionRange;
    }

    public void setActionRange(int actionRange) {
        this.actionRange = actionRange;
    }
}
