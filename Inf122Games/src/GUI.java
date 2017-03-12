import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI{// extends JFrame {
    private JPanel panel;
    private JFrame frame;
    public GUI(int rows,int cols,int hgap,int vgap){
    	frame = new JFrame();
        panel=new JPanel(new GridLayout(rows, cols, hgap, vgap));
        
        for(int i=1;i<=rows;i++)
        {
            for(int j=1;j<=cols;j++)
            {
                JButton btn=new JButton(String.valueOf(i));
                panel.add(btn);
            }
        }
        frame.setPreferredSize(new Dimension(1000,1000));
        frame.add(panel);
        frame.setTitle("Game Name to Be Set From Server");
        frame.pack();
        frame.setVisible(true);
    }
}