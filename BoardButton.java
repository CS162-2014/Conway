import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class BoardButton extends JButton implements ActionListener
{
    public boolean alive;
    public JFrame frame;
    public JPanel board;
    public int surround;
    
    public BoardButton(JFrame fr, JPanel b)
    {
        super();
        board=b;
        frame=fr;
        alive=false;
        this.setBackground(Color.WHITE);
        this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        alive=!alive;
        if(alive)
        {
            setBackground(Color.BLACK);
        }
        else
            setBackground(Color.WHITE);
    }
    public void eval()
    {
        if(surround <2)
        {
            alive=false;
            setBackground(Color.WHITE);
        }
        if(surround>3)
        {
            alive=false;
            setBackground(Color.WHITE);
        }
        if(surround==3)
        {
            alive=true;
            setBackground(Color.BLACK);
        }
    }
}
    
