package graphics.basics.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.EventQueue;

//this class tests layouts with calculator example
public class Layouts 
{

    public static void main(String... args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                Layouts l = new Layouts();
                Calculator c  = l.new Calculator();
                c.setVisible(true);
            }  
          });
    }

    public class Calculator extends JFrame
    {
        private JButton display;
        private JPanel layoutPanel;
        private double result;
        private String lastCommand;
        private boolean start;

        public Calculator()
        {
            //set calculator layout to border layout
            setLayout(new BorderLayout());

            setSize(500, 500);

            setTitle("Simple Calculator");
            setLocationByPlatform(true);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            result =0;
            lastCommand="";
            start=true;

            display = new JButton("0");
            //display should not be enabled to not be clickable
            display.setEnabled(false);
            //display should on the north of the frame
            add(display, BorderLayout.NORTH);

            ActionListener insert = new InsertAction();
            ActionListener command = new CommandAction();

            layoutPanel = new JPanel();
            //set layout of the panel to 4x4 grid and 2x2 gaps
            layoutPanel.setLayout(new GridLayout(4,4, 2, 2));

            addButton("7", insert);
            addButton("8", insert);
            addButton("9", insert);
            addButton("*", command);

            addButton("4", insert);
            addButton("5", insert);
            addButton("6", insert);
            addButton("-", command);

            addButton("1", insert);
            addButton("2", insert);
            addButton("3", insert);
            addButton("+", command);

            addButton("0", insert);
            addButton(".", insert);
            addButton("=", command);
            addButton("/", command);
            
            add(layoutPanel, BorderLayout.CENTER);
        }

        private void addButton(String label, ActionListener listener)
        {
            JButton button = new JButton(label);
            button.addActionListener(listener);
            layoutPanel.add(button);
        }

        private class InsertAction implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                String input = event.getActionCommand();
                if(start)
                {
                    display.setText("");
                    start=false;
                }

                display.setText(display.getText()+ input);
            }
        }

        private class CommandAction implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                String command = event.getActionCommand();

                if(start)
                {
                    if(command.equals("-"))
                    {
                        display.setText(command);
                        start = false;
                    }
                    else lastCommand=command;
                }
                else
                {
                    calculate(Double.parseDouble(display.getText()));
                    lastCommand=command;
                    start = true;
                }
            }
        }

        public void calculate(double x)
        {
            if(lastCommand.equals("+")) result+=x;
            if(lastCommand.equals("-")) result-=x;
            if(lastCommand.equals("*")) result*=x;
            if(lastCommand.equals("/")) result/=x;
            if(lastCommand.equals("=")) result=x;
            display.setText("" +result);
        }
    }
}
