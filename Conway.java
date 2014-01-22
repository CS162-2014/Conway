import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class Conway implements ActionListener
{
    public JFrame frame;
    public JButton quit, auto, step, stepTime, noauto, clear;
    public JPanel panelBoard;
    public BoardButton[][] board;
    public Timer timer;
    public int dim=150; //edit this to change size of space. default is 150 x 150 boxes.
    public int speed=100; //edit this to change animate speed, 100 (fast) is default this is good for long evolutions 
                            // The lower the speed number, the faster. 
                            // If you have a shorter "evolution", increase the number, to see more intermediary steps
    
    public Conway()
    {
        frame=new JFrame("Conway's Game of Life");
        frame.setLayout(new BorderLayout());
        panelBoard=new JPanel();
        panelBoard.setLayout(new GridLayout(dim,dim));
        board=new BoardButton[dim][dim]; 
        for(int y=0; y<dim; y++)
        {
            for(int x=0; x<dim; x++)
            {
                BoardButton button=new BoardButton(frame, panelBoard);
                panelBoard.add(button);
                board[y][x]=button;
                button.addActionListener(this);
            }
        }
        quit=new JButton("Exit");
        quit.addActionListener(this);
        clear=new JButton("clear");
        clear.addActionListener(this);
        step=new JButton("step");
        step.addActionListener(this);
        noauto=new JButton("stop animation");
        auto=new JButton("animate");
        noauto.addActionListener(this);
        auto.addActionListener(this);
        JPanel menu=new JPanel(new GridLayout(1,5));
        menu.add(step);
        menu.add(auto);
        menu.add(noauto);
        menu.add(clear);
        menu.add(quit);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menu, BorderLayout.NORTH);
        frame.add(panelBoard,BorderLayout.CENTER);
        frame.pack();
        frame.setSize(new Dimension(1000,1000));
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==(Object)(quit))
        {
            frame.dispose();
            System.exit(0);
        }
        
        else if(e.getSource()==(Object)(step))
        {
            step();
        }
        
        else if(e.getSource()==(Object)(auto))
        {
            timer=new Timer();
            timer.schedule(new TimerTask()
            {
                public void run()
                {
                    step();
                }
            }, 10, speed);
        }
        else if(e.getSource()==(Object)(noauto))
        {
            timer.cancel();
        }
        else if(e.getSource()==(Object)(clear))
        {
            clear();
        }
    }
    public int getNeighbors(int y, int x)
    {
        int alive=0;
        int startingx=x-1;
        int startingy=y-1;
        if(startingx<0)
        {
            startingx++;
        }
        if(startingy<0)
        {
            startingy++;
        }
        int startx=startingx;
        int starty=startingy;
        while(starty<=y+1 && starty<board.length)
        {
            startx=startingx;
            while(startx<=x+1 && startx<board[0].length)
            {
                if(starty==y && startx ==x)
                {
                }
                else if(board[starty][startx].alive==true)
                {
                    alive++;
                }
                startx=startx+1;
            }
            starty=starty+1;
        }
        return alive;
    }
    public void getState()
    {
        for(int y=0; y<board.length; y++)
        {
            for(int x=0; x<board[0].length; x++)
            {
                BoardButton square=board[y][x];
                square.surround=getNeighbors(y,x);
            }
        }
    }
    public void evalState()
    {
        for(int y=0; y<board.length; y++)
        {
            for(int x=0; x<board[0].length; x++)
            {
                BoardButton square=board[y][x];
                square.eval();
            }
        }
    }
    public void step()
    {
        getState();
        evalState();
        frame.revalidate();
        frame.repaint();
    }
    public void clear()
    {
        for(int y=0; y<board.length; y++)
        {
            for(int x=0; x<board[0].length; x++)
            {
                BoardButton square=board[y][x];
                square.alive=false;
                square.setBackground(Color.WHITE);
            }
        }
        frame.revalidate();
        frame.repaint();
    }
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run()
             {
                 new Conway();
             }
         });
     }
 }
