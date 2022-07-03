package graphics.basics.GUI.componentTests;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class TextComponentTest extends JFrame
{
    public static void main(String...args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                TextComponentTest tct = new TextComponentTest();
                tct.setVisible(true);
            }  
          });
    }

    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JTextArea output = new JTextArea();

    public TextComponentTest()
    {
        setSize(520,720);
            
        setTitle("Text Components Test");
        setLocationByPlatform(true);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,2));
        
        northPanel.add(new JLabel("Username  ", SwingConstants.RIGHT));
        northPanel.add(usernameField);

        northPanel.add(new JLabel("Password  ", SwingConstants.RIGHT));
        northPanel.add(passwordField);

        add(northPanel, BorderLayout.NORTH);

        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(output);

        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();

        JButton readFileButton = new JButton("Read from file");
        readFileButton.addActionListener(new LoadTXTFileAction());

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e)
           {
                output.append("Username: "+ usernameField.getText()+"\nPassword: "+ new String(passwordField.getPassword())+"\n\n");
           }
        });
        JButton dataInserter = new JButton("Insert data using dialog");
        dataInserter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                var dialog = new DataInserter();
    
                if(dialog.showDialog(TextComponentTest.this, "Feed me with data"))
                {
                    output.append("Username: "+ dialog.getUsername()+"\nPassword: "+ new String(dialog.getPassword())+"\n\n");
                }
            }
        });

        southPanel.add(readFileButton);
        southPanel.add(insertButton);
        southPanel.add(dataInserter);

        add(southPanel, BorderLayout.SOUTH);
    }

    private class LoadTXTFileAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            var fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            if(fileChooser.showDialog(TextComponentTest.this, "Choose your weapon!") == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                try {
                    output.append(("\n\nChosen file: "+file.getName()+"\n\n"));

                    try (Scanner sc = new Scanner(file))
                    {                       
                        while(sc.hasNextLine())
                        {
                            output.append(sc.nextLine());
                        }
                    };
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class DataInserter extends JPanel
    {
        private JTextField username= new JTextField();
        private JPasswordField password = new JPasswordField();
        private boolean cancelled=false;
        private JDialog dialog = new JDialog();
        private JButton okButton = new JButton("Ok");
        private JButton cancelButton = new JButton("Cancel");

        private String _username = null;
        private char[] _password = null;

        public DataInserter()
        {
            JPanel panel = new JPanel(new GridLayout(2,2));

            panel.add(new JLabel("Username:  "));
            panel.add(username);
            panel.add(new JLabel("Password:  "));
            panel.add(password);

            add(panel, BorderLayout.CENTER);

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event)
                {
                    _username = username.getText();
                    _password = password.getPassword();
                    dialog.dispose();
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event)
                {
                    cancelled=true;
                    dialog.dispose();
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        public String getUsername()
        {
            return _username;
        }

        public char[] getPassword()
        {
            return _password;
        }

        public boolean showDialog(Component parent, String title)
        {
            Frame owner = null;
            if(parent instanceof Frame) owner =(Frame)parent;
            else
            //get the frame of the component or whatever this is
                owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);

            if(dialog==null || dialog.getOwner()!=owner)
            {
                dialog = new JDialog(owner, true);
                //add this panel to newly created dialog
                dialog.add(this);
                dialog.getRootPane().setDefaultButton(okButton);
                dialog.pack();
            }

            dialog.setTitle(title);
            dialog.setVisible(true);

            //we want to return true if everything went good, so we inverse this
            return !cancelled;
        }
    }
}

