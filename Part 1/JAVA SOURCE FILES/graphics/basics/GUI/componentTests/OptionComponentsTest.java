package graphics.basics.GUI.componentTests;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//and also border test as well
public class OptionComponentsTest extends JFrame
{
    private JCheckBox italic = new JCheckBox("Italic");
    private JCheckBox bold = new JCheckBox("Bold");
    private final int DEFAULT_SIZE =12;
    private int FONT_SIZE = DEFAULT_SIZE;
    
    private ButtonGroup sizeButtons = new ButtonGroup();
    private JPanel sizeButtonsPanel = new JPanel();
    private JPanel fontOptions = new JPanel();
    private JLabel textLabel = new JLabel("Sample text", SwingConstants.CENTER);
    private JComboBox<String> availableFonts = getFontBox();

    public OptionComponentsTest()
    {
        italic.addActionListener(new FontAction());
        bold.addActionListener(new FontAction());

        setSize(480,320);
        setResizable(false);
        
        setTitle("Options Components Test");
        setLocationByPlatform(true);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        //add items to panel and panels itself
        sizeButtonsPanel.setLayout(new GridLayout(6,1));
        add(textLabel, BorderLayout.CENTER);
        
        addRadioButton("Small", 8);
        addRadioButton("Medium", 12);
        addRadioButton("Large", 16);
        addRadioButton("Extra Large", 20);


        fontOptions.setLayout(new GridLayout(1,2));
        fontOptions.add(italic);
        fontOptions.add(bold);
        sizeButtonsPanel.add(fontOptions);
        sizeButtonsPanel.add(availableFonts);

        add(sizeButtonsPanel, BorderLayout.WEST);

        //add panel borders
        fontOptions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Font Options"));
        sizeButtonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Font Size"));
        availableFonts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Font Family"));
    }

    private class FontAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int mode = 0;
            if(italic.isSelected()) mode += Font.ITALIC;
            if(bold.isSelected()) mode +=Font.BOLD;

            Font actualFont = textLabel.getFont();
            Font newFont = new Font("Serif", mode, actualFont.getSize());
            textLabel.setFont(newFont);
        }
    }

    private void addRadioButton(String name, final int size)
    {
        boolean selected = size == DEFAULT_SIZE;
        JRadioButton button = new JRadioButton(name, selected);
        sizeButtons.add(button);
        sizeButtonsPanel.add(button);

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                FONT_SIZE = size;
                Font actualFont = textLabel.getFont();
                Font newFont = new Font(actualFont.getFontName(), actualFont.getStyle(), FONT_SIZE);
                textLabel.setFont(newFont);
            }
        };

        button.addActionListener(listener);

    }

    private class chooseFontAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Font actual = textLabel.getFont();
            Font newFont = new Font((String)availableFonts.getSelectedItem(), actual.getStyle(), actual.getSize());
            textLabel.setFont(newFont);
        }
    }

    private JComboBox<String> getFontBox()
    {
        JComboBox<String> output = new JComboBox<>();
        
        for (String name : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            output.addItem(name);
        }
        output.addActionListener(new chooseFontAction());
        return output;
    }
}
