    import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Conway implements ActionListener
{
    public JFrame frame;
    public JButton quit, auto, step, stepTime;
    public JPanel panelBoard;
    
    public Conway()
    {
        frame=new JFrame("Conway's Game of Life");
        frame.setLayout(new BorderLayout());
        panelBoard=new JPanel();
        panelBoard.setLayout(new GridLayout(10,10));
        BoardButton[][] board=new BoardButton[10][10]; 
        for(int y=0; y<10; y++)
        {
            for(int x=0; x<10; x++)
            {
                BoardButton button=new BoardButton(frame, panelBoard);
                panelBoard.add(button);
                board[x][y]=button;
                button.addActionListener(this);
            }
        }
        quit=new JButton("Exit");
        quit.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(quit, BorderLayout.NORTH);
        frame.add(panelBoard,BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==(Object)(quit))
        {
            frame.dispose();
            System.exit(0);
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
