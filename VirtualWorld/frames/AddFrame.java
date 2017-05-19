package VirtualWorld.frames;

import VirtualWorld.Organism;
import VirtualWorld.World;
import VirtualWorld.animals.*;
import VirtualWorld.buttons.CreateButton;
import VirtualWorld.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Bartosz Łuczak on 23.04.2017.
 */
public class AddFrame extends JPanel implements ActionListener {

    public static final int HEIGHT = 200;
    public static final int WIDTH = 300;
    private World world;
    private LogFrame log;
    private BoardFrame board;
    private int x,y;
    private ArrayList<CreateButton> buttons;

    public AddFrame(World world, LogFrame log) {
        this.log=log;
        this.world=world;
        this.buttons=new ArrayList<>();
        add(new JLabel("                        Creator panel:                            "));
        addButtons();

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == buttons.get(0)) {
            this.world.getOrganisms().add(new CyberSheep(this.world, this.x, this.y));
        }

        else if(source == buttons.get(1)) {
            this.world.getOrganisms().add(new Sheep(this.world, this.x, this.y));
        }
        else if(source == buttons.get(2)){
            this.world.getOrganisms().add(new Wolf(this.world,this.x,this.y));
        }
        else if(source == buttons.get(3)){
            this.world.getOrganisms().add(new Dandelion(this.world,this.x,this.y));
        }
        else if(source == buttons.get(4)){
            this.world.getOrganisms().add(new Guarana(this.world,this.x,this.y));
        }
        else if(source == buttons.get(5)){
            this.world.getOrganisms().add(new Grass(this.world,this.x,this.y));
        }
        else if(source == buttons.get(6)){
            this.world.getOrganisms().add(new Antilope(this.world,this.x,this.y));
        }
        else if(source == buttons.get(7)){
            this.world.getOrganisms().add(new Hogweed(this.world,this.x,this.y));
        }
        else if(source == buttons.get(8)){
            this.world.getOrganisms().add(new Turtle(this.world,this.x,this.y));
        }
        else if(source == buttons.get(9)){
            this.world.getOrganisms().add(new Belladona(this.world,this.x,this.y));
        }
        else if(source == buttons.get(10)){
            this.world.getOrganisms().add(new Fox(this.world,this.x,this.y));
        }
        else if(source == buttons.get(11)){
            Organism temp = this.world.isAlive('H');
            if(temp==null)
                this.world.getOrganisms().add(new Human(this.world,this.x,this.y));
            else
                this.world.getEvents().addEvent("You cannot create more than one human!");
        }
        disableButtons();
        board.repaint();
        log.logOneEvent();
    }

    public void unlockButtons(int x, int y, BoardFrame board){
        this.board=board;
        this.x=x;
        this.y=y;
        for(int i=0;i<buttons.size();i++)
        {
            buttons.get(i).setEnabled(true);
        }
    }

    public void disableButtons(){
        for(int i=0;i<buttons.size();i++)
        {
            buttons.get(i).setEnabled(false);
        }
    }

    private void addButtons(){
        buttons.add(new CreateButton("Cyber-Sheep",Color.getHSBColor(0.58f,0.79f,0.33f),Color.WHITE));
        buttons.add(new CreateButton("Sheep", Color.WHITE,Color.BLACK));
        buttons.add(new CreateButton("Wolf", Color.BLACK, Color.WHITE));
        buttons.add(new CreateButton("Dandelion", Color.YELLOW,Color.BLACK));
        buttons.add(new CreateButton("Guarana",Color.red.brighter(),Color.WHITE));
        buttons.add(new CreateButton("Grass",Color.getHSBColor(0.35f,0.84f,0.34f),Color.WHITE));
        buttons.add(new CreateButton("Antilope",Color.getHSBColor(0.083f,0.55f,0.55f),Color.WHITE));
        buttons.add(new CreateButton("S.Hogweed",Color.green.darker(),Color.BLACK));
        buttons.add(new CreateButton("Turtle",Color.getHSBColor(0.1527f,0.79f,0.32f),Color.WHITE));
        buttons.add(new CreateButton("Belladona",Color.getHSBColor(0.74f,0.79f,0.65f),Color.WHITE));
        buttons.add(new CreateButton("Fox", Color.getHSBColor(0.072f,1,1),Color.BLACK));
        buttons.add(new CreateButton("Human",Color.getHSBColor(0.061f,0.39f,0.99f),Color.BLACK));
        for(int i=0;i<buttons.size();i++)
        {
            buttons.get(i).addActionListener(this);
            add(buttons.get(i));
        }
    }
}
