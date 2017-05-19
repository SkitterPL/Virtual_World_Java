package VirtualWorld.buttons;

import VirtualWorld.Organism;
import VirtualWorld.World;
import VirtualWorld.animals.*;
import VirtualWorld.frames.BoardFrame;
import VirtualWorld.frames.LogFrame;
import VirtualWorld.frames.MainFrame;
import VirtualWorld.plants.*;
import VirtualWorld.worlds.HexWorld;
import VirtualWorld.worlds.SquareWorld;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Bartosz Łuczak on 07.05.2017.
 */
public class LoadButton extends JButton {
    private World world;
    private LogFrame log;
    private BoardFrame board;
    private MainFrame main;

    public LoadButton(MainFrame main) {
        super("Load world");
        this.world = main.getWorld();
        this.log = main.getLog();
        this.board = main.getBoard();
        this.main=main;

        addActionListener(e -> {
            try {
                loadWorld();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            world.getEvents().addEvent("\nWorld has been loaded!");
            log.refreshLog();
            board.repaint();
        });
    }


    private void loadWorld() throws IOException {
        world.getOrganisms().clear();
        world.setHowManyOrganisms(0);
        FileReader file = new FileReader("save.txt");
        BufferedReader buff = new BufferedReader(file);
        int temp, tempX, tempY;
        char tempSymbol;
        Organism newOrg;
        world.setSides(Integer.valueOf(buff.readLine()));
        world.setWidth(Integer.valueOf(buff.readLine()));
        world.setHeight(Integer.valueOf(buff.readLine()));
        if(world.getSides()==4)
            world=new SquareWorld(world.getWidth(),world.getHeight());
        else if(world.getSides() ==6)
            world=new HexWorld(world.getWidth(),world.getHeight());
        main.dispose();
        MainFrame itWorks= new MainFrame(world);
        board.repaint();

        world.setTurn(Integer.valueOf(buff.readLine()));
        temp = Integer.valueOf(buff.readLine());
        for (int i = 0; i < temp; i++) {
            tempSymbol = buff.readLine().charAt(0);
            tempX = Integer.valueOf(buff.readLine());
            tempY = Integer.valueOf(buff.readLine());
            switch(tempSymbol){
                case 'W': newOrg = new Wolf(world,tempX,tempY); break;
                case 'S': newOrg = new Sheep(world,tempX,tempY); break;
                case 'F': newOrg = new Fox(world,tempX,tempY); break;
                case 'A': newOrg = new Antilope(world,tempX,tempY); break;
                case 'T': newOrg = new Turtle(world,tempX,tempY); break;
                case 'D': newOrg = new Dandelion(world,tempX,tempY); break;
                case 'G': newOrg = new Grass(world,tempX,tempY); break;
                case 'H': newOrg = new Human(world,tempX,tempY); break;
                case 'U': newOrg = new Guarana(world,tempX,tempY); break;
                case 'B': newOrg = new Belladona(world,tempX,tempY); break;
                case 'X': newOrg = new Hogweed(world,tempX,tempY); break;
                case 'C': newOrg = new CyberSheep(world,tempX,tempY); break;
                default: newOrg = new Sheep(world,tempX,tempY); break;
            }
            newOrg.setStrength(Integer.valueOf(buff.readLine()));
            newOrg.setAge(Integer.valueOf(buff.readLine()));
            if(newOrg.getSymbol()=='H') {
                ((Human) newOrg).setCooldown(Integer.valueOf(buff.readLine()));
            }
            world.getOrganisms().add(newOrg);
        }
        world.getEvents().cleanLog();
    }
}
