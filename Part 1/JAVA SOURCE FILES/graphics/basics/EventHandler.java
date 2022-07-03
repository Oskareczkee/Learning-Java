package graphics.basics;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.*;

public class EventHandler
{

    public static void main(String... args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
              ButtonTestFrame frame = new ButtonTestFrame();
              frame.setVisible(true);
            }  
          });
    }

    

    static class ButtonTestFrame extends JFrame
    {
        private JPanel buttonPanel;

        public ButtonTestFrame()
        {
            buttonPanel = new JPanel();

            //bind ctrl y to change color to yellow
            InputMap imap = buttonPanel.getInputMap();
            imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
            ActionMap amap = buttonPanel.getActionMap();
            amap.put("panel.yellow", new ColorActionAA("",Color.YELLOW));


            setTitle("Testing Actions and Buttons in Java!");
            setLocationByPlatform(true);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //various ways to attach event/action to the button
            JButton yellowButton = new JButton("<html><b>Yellow</b></html>");
            JButton blueButton = new JButton(new ColorActionAA("Blue", Color.BLUE));
            JButton redButton = new JButton("Red");
            JButton motifButton = new JButton("Change to random style!");

            buttonPanel.add(yellowButton);
            buttonPanel.add(blueButton);
            buttonPanel.add(redButton);
            buttonPanel.add(motifButton);

            add(buttonPanel);

            yellowButton.addActionListener(new ColorAction(Color.YELLOW));
            redButton.addActionListener(new ColorAction(Color.RED));

            motifButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent event)
              {
                var Styles = UIManager.getInstalledLookAndFeels();
                String plaf = Styles[new Random(System.currentTimeMillis()).nextInt(Styles.length)].getClassName();

                try{
                    UIManager.setLookAndFeel(plaf);
                    SwingUtilities.updateComponentTreeUI(buttonPanel);
                }
                catch(Exception e){e.printStackTrace();}
              }  
            });

            pack();
        }

        private class ColorAction implements ActionListener
        {
            private Color backgroundColor;
    
            public ColorAction(Color c)
            {
                backgroundColor=c;
            }
    
            public void actionPerformed(ActionEvent event)
            {
                buttonPanel.setBackground(backgroundColor);
            }
        }

        private class ColorActionAA extends AbstractAction
        {
            public ColorActionAA(String name, Color c)
            {
                putValue(Action.NAME, name);
                putValue("color", c);
                putValue(Action.SHORT_DESCRIPTION, "Change color to "+name.toLowerCase());
            }

            public void actionPerformed(ActionEvent event)
            {
                Color c = (Color) getValue("color");
                buttonPanel.setBackground(c);
            }
        }
    }
}
