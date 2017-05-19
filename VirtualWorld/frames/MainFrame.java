package VirtualWorld.frames;

/**
 * Created by Bartosz Luczak on 21.04.2017.
 */
import java.awt.*;
import javax.swing.*;
import VirtualWorld.World;
import VirtualWorld.animals.Human;
import VirtualWorld.buttons.LoadButton;
import VirtualWorld.buttons.QuitButton;
import VirtualWorld.buttons.SaveButton;


public class MainFrame extends JFrame {
    private World world;
    private LogFrame log;
    private BoardFrame board;
    private AddFrame add;

    public MainFrame(World world){
        super("Virtual VirtualWorld.World by Bartosz Łuczak");
        this.setWorld(world);
        this.setFocusable(true);
        this.requestFocusInWindow();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(640,300);
        setSize(1000,600);
        setLayout(null);
        initComponents(this);
        setVisible(true);

        addKeyListener(new MultiKeyListener(this) );

    }

    public void turn(){
        getWorld().makeTurn();
        getLog().refreshLog();
        getAdd().disableButtons();
        repaint();
    }

    private void initComponents(Frame frame){
        setFocusable(true);
        setLog(new LogFrame(getWorld()));
        setAdd(new AddFrame(getWorld(), getLog()));
        JScrollPane panel,logpanel;
        if(this.getWorld().getSides() ==4) {
            setBoard(new SquareFrame(getWorld(), getAdd()));
        }
        else if(this.getWorld().getSides()==6) {
            setBoard(new HexFrame(getWorld(), getAdd()));
        }
        panel = new JScrollPane(getBoard());
        logpanel=new JScrollPane(getLog());
        JLabel hello=new JLabel("Welcome to Virtual World!");
        SaveButton save = new SaveButton(getWorld(), getLog());
        LoadButton load = new LoadButton(this);
        QuitButton quit = new QuitButton();
        JButton next = new JButton("Next turn");
        next.addActionListener(e -> {
              Human temp =(Human)getWorld().isAlive('H');
              if(temp==null)
                 turn();
        });
        JButton skill=new JButton("Skill");
        skill.addActionListener(e->{
            Human temp =(Human)getWorld().isAlive('H');
            if(temp!=null) {
                temp.setActionKey(10);
                turn();
            }
        });

        save.setFocusable(false);
        load.setFocusable(false);
        quit.setFocusable(false);
        getAdd().setFocusable(false);
        next.setFocusable(false);
        skill.setFocusable(false);

        hello.setBounds(getWorld().getWidth()*23/2+100,1,200,20);
        panel.setBounds(1,30,frame.getWidth()-350,400);
        save.setBounds(1,530, 100,20);
        load.setBounds(111,530, 100,20);
        quit.setBounds(221,530, 100,20);
        logpanel.setBounds(frame.getWidth()-340, 30, 320,300);
        getAdd().setBounds(frame.getWidth()-350, 340,300,200);
        next.setBounds(1,500,100,20);
        skill.setBounds(111,500,100,20);

        frame.add(hello);
        frame.add(panel);
        frame.add(save);
        frame.add(load);
        frame.add(quit);
        frame.add(logpanel);
        frame.add(getAdd());
        frame.add(next);
        frame.add(skill);
    }

    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }
    public LogFrame getLog() {
        return log;
    }
    public void setLog(LogFrame log) {
        this.log = log;
    }
    public BoardFrame getBoard() {
        return board;
    }
    public void setBoard(BoardFrame board) {
        this.board = board;
    }
    public AddFrame getAdd() {
        return add;
    }
    public void setAdd(AddFrame add) {
        this.add = add;
    }
}



