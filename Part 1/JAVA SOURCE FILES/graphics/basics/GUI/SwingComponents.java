package graphics.basics.GUI;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graphics.basics.GUI.componentTests.OptionComponentsTest;
import graphics.basics.GUI.componentTests.SliderComponentsTest;
import graphics.basics.GUI.componentTests.TextComponentTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class SwingComponents
{

    public static void main(String... args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                SwingComponents sc = new SwingComponents();
                MainFrame mf  = sc.new MainFrame();
                mf.setVisible(true);
            }  
          });
    }

    public class MainFrame extends JFrame
    {
        public MainFrame()
        {
            //To feel like scam
            JOptionPane.showConfirmDialog(null, "Gratulacje Użytkowniku\nWłaśnie wygrałeś nowego IPhone6s!", "O mój Boże wygrałeś1!!!!11!", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
            JColorChooser colorChooser = new JColorChooser();

            colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event)
                {
                    Color c = colorChooser.getColor();
                    getContentPane().setBackground(c);
                }
            });
            
            //set calculator layout to border layout
            setLayout(new BorderLayout());
            
            setSize(720, 480);
            
            setTitle("Java Components Demo");
            setLocationByPlatform(true);
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JMenuBar menuBar = new JMenuBar();
            setJMenuBar(menuBar);
            
            JMenu componentsMenu = new JMenu("Components");
            menuBar.add(componentsMenu);
            
            JMenuItem textComponent = new JMenuItem(new AbstractAction("Text Components Test") {
                public void actionPerformed(ActionEvent event)
                {
                    EventQueue.invokeLater(new Runnable() {
                        public void run()
                        {
                            TextComponentTest tct = new TextComponentTest();
                            tct.setVisible(true);
                        }  
                    });
                }
            });
            JMenuItem optionsComponent = new JMenuItem(new AbstractAction("Options Selection Components Test") {
                public void actionPerformed(ActionEvent event)
                {
                    EventQueue.invokeLater(new Runnable() {
                        public void run()
                        {
                            OptionComponentsTest oct = new OptionComponentsTest();
                            oct.setVisible(true);
                        }  
                    });
                }
            });
            
            JMenuItem slidersComponent = new JMenuItem(new AbstractAction("Slider Components Test") {
                public void actionPerformed(ActionEvent event)
                {
                    EventQueue.invokeLater(new Runnable() {
                        public void run()
                        {
                            SliderComponentsTest oct = new SliderComponentsTest();
                            oct.setVisible(true);
                        }  
                    });
                }
            });
            
            componentsMenu.add(textComponent);
            componentsMenu.add(optionsComponent);
            componentsMenu.add(slidersComponent);
            add(colorChooser, BorderLayout.WEST);
        }
    }
    
}
