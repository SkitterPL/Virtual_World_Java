package VirtualWorld;
/**
 * Created by Bartosz Łuczak on 21.04.2017.
 */

import VirtualWorld.animals.*;
import VirtualWorld.plants.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class World {

    protected int height;
    protected int width;
    private int turn;
    private int howManyOrganisms;
    private int sides;
    private ArrayList<Organism> organisms;
    private EventLog events;

    public World() {
        this.turn=0;
        this.howManyOrganisms=0;
        this.organisms= new ArrayList<Organism>();
        this.events=new EventLog();
    }

    public void setHeight(int height){
        this.height=height;
    }
    public void setWidth(int width){
        this.width=width;
    }
    public void setTurn(int turn){
        this.turn=turn;
    }
    public void setHowManyOrganisms(int howmany)
    {
        this.howManyOrganisms=howmany;
    }
    public void setOrganism(Organism organism)
    {
        this.organisms.add(organism);
    }

    public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.width;
    }
    public int getTurn(){
        return this.turn;
    }
    public int getHowManyOrganisms(){
        return this.howManyOrganisms;
    }
    public ArrayList<Organism> getOrganisms(){
        return this.organisms;
    }
    public Organism getOrganism(int index){
        return this.organisms.get(index);
    }
    public Organism getOrganism(int x, int y){
        for(int i=0;i<getHowManyOrganisms();i++)
        {
            if(this.getOrganism(i).getX()==x && this.getOrganism(i).getY()==y)
                return this.getOrganism(i);
        }
        return null;
    }
    public EventLog getEvents(){
        return this.events;
    }
    public void deleteOrganism(Organism o){
        organisms.remove(o);
        setHowManyOrganisms(getHowManyOrganisms()-1);
    }

    public void makeTurn(){
        this.setTurn(this.getTurn() + 1);
        sortOrganisms();
        for(int i=0;i<getHowManyOrganisms();i++)
        {
            if(getOrganism(i)!=null)
                this.getOrganism(i).action();
            //Test to test sort
            //this.getEvents().addEvent(getOrganism(i).getSymbol() +" Ini:" + getOrganism(i).getInitiative()+" Age: " +getOrganism(i).getAge() );

        }
    }
    private void sortOrganisms(){
        OrganismsComparator sortByInitiative = new OrganismsComparator();
        Collections.sort(organisms, sortByInitiative);
    }

    public abstract World creator(int x, int y);

    public Organism isAlive(char symbol){
        for(int i=0;i<getHowManyOrganisms();i++){
            if(getOrganism(i).getSymbol()==symbol){
                return getOrganism(i);
            }
        }
        return null;
    }

    public void saveWorld() throws FileNotFoundException {
        PrintWriter save = new PrintWriter("save.txt");
        save.println(this.getSides());
        save.println(getWidth());
        save.println(getWidth());
        save.println(getTurn());
        save.println(getHowManyOrganisms());
        for(int i=0;i<getHowManyOrganisms();i++){
            save.println(this.getOrganism(i).getSymbol());
            save.println(this.getOrganism(i).getX());
            save.println(this.getOrganism(i).getY());
            save.println(this.getOrganism(i).getStrength());
            save.println(this.getOrganism(i).getAge());
            if(this.getOrganism(i).getSymbol()=='H') {
                Human temp = (Human) isAlive('H');
                save.println(temp.getCooldown());
            }
        }
        save.close();
    }

    public void loadWorld() throws IOException {
        organisms.clear();
        setHowManyOrganisms(0);
        FileReader file = new FileReader("save.txt");
        BufferedReader buff = new BufferedReader(file);
        int temp, tempX, tempY;
        char tempSymbol;
        Organism newOrg;
        World b;
        this.setSides(Integer.valueOf(buff.readLine()));
        this.setWidth(Integer.valueOf(buff.readLine()));
        this.setHeight(Integer.valueOf(buff.readLine()));

        this.setTurn(Integer.valueOf(buff.readLine()));
        temp = Integer.valueOf(buff.readLine());
        for (int i = 0; i < temp; i++) {
            if(buff!=null) {
                tempSymbol = buff.readLine().charAt(0);
                tempX = Integer.valueOf(buff.readLine());
                tempY = Integer.valueOf(buff.readLine());
                switch(tempSymbol){
                    case 'W': newOrg = new Wolf(this,tempX,tempY); break;
                    case 'S': newOrg = new Sheep(this,tempX,tempY); break;
                    case 'F': newOrg = new Fox(this,tempX,tempY); break;
                    case 'A': newOrg = new Antilope(this,tempX,tempY); break;
                    case 'T': newOrg = new Turtle(this,tempX,tempY); break;
                    case 'D': newOrg = new Dandelion(this,tempX,tempY); break;
                    case 'G': newOrg = new Grass(this,tempX,tempY); break;
                    case 'H': newOrg = new Human(this,tempX,tempY); break;
                    case 'U': newOrg = new Guarana(this,tempX,tempY); break;
                    case 'B': newOrg = new Belladona(this,tempX,tempY); break;
                    case 'X': newOrg = new Hogweed(this,tempX,tempY); break;
                    case 'C': newOrg = new CyberSheep(this,tempX,tempY); break;
                    default: newOrg = new Sheep(this,tempX,tempY); break;
                }
                newOrg.setStrength(Integer.valueOf(buff.readLine()));
                newOrg.setAge(Integer.valueOf(buff.readLine()));
                if(newOrg.getSymbol()=='H') {
                    ((Human) newOrg).setCooldown(Integer.valueOf(buff.readLine()));
                }
                getOrganisms().add(newOrg);
            }
        }
        this.getEvents().cleanLog();
    }

    abstract public Point rollPosition(Organism organism);


    Point rollPosition(int actionRange, Organism organism){
        int temp = organism.getActionRange();
        organism.setActionRange(actionRange);
        Point p = rollPosition(organism);
        organism.setActionRange(temp);
        return p;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }
}

 class OrganismsComparator implements Comparator<Organism>{
    @Override
    public int compare(Organism o1, Organism o2) {
        int temp = o2.getInitiative()-o1.getInitiative();
        if(temp!=0)
            return temp;
        else return o2.getAge()-o1.getAge();
    }
}

