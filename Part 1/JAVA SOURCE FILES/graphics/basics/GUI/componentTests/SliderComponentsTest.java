package graphics.basics.GUI.componentTests;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Dictionary;
import java.util.Hashtable;

import java.awt.geom.*;

public class SliderComponentsTest extends JFrame
{
    private JPanel slidersPanel = new JPanel();
    private CircleComponent circle = new CircleComponent();

    public SliderComponentsTest()
    {  
        circle.setCircleSize(100);
        setTitle("Text Components Test");
        setLocationByPlatform(true);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        slidersPanel.setLayout(new GridLayout(0,1));
        slidersPanel.add(new circleSliderPanel());
        slidersPanel.add(new sliderCheckPanel());
        add(slidersPanel, BorderLayout.WEST);
        add(circle, BorderLayout.CENTER);

        pack();
    }

    //just show of sliders
    private class sliderCheckPanel extends JPanel
    {
        private int MIN=10;
        private int MAX=200;
        private int DEFAULT=100;

        public sliderCheckPanel()
        {
            setLayout(new GridLayout(0 ,2));
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Slider Show"));
            add(createSliderPanel(new JSlider(MIN, MAX, DEFAULT), "Basic"));
            //snapped
            JSlider snapped = new JSlider();
            snapped.setPaintTicks(true);
            snapped.setSnapToTicks(true);
            snapped.setMajorTickSpacing(20);
            snapped.setMinorTickSpacing(5);
            add(createSliderPanel(snapped, "Snapped"));
            //no paint track
            JSlider nPT = new JSlider();
            nPT.setPaintTicks(true);
            nPT.setPaintTrack(false);
            nPT.setMajorTickSpacing(20);
            nPT.setMinorTickSpacing(5);
            add(createSliderPanel(nPT, "No Paint Track"));
            //reversed
            JSlider reversed = new JSlider();
            reversed.setPaintTicks(true);
            reversed.setInverted(true);
            reversed.setMajorTickSpacing(20);
            reversed.setMinorTickSpacing(5);
            add(createSliderPanel(reversed, "Reversed"));
            //with labels
            JSlider labeled = new JSlider();
            labeled.setPaintTicks(true);
            labeled.setPaintLabels(true);
            labeled.setMajorTickSpacing(20);
            labeled.setMinorTickSpacing(5);
            add(createSliderPanel(labeled, "Labeled"));
            //with letter labels
            JSlider letterLabeled = new JSlider();
            letterLabeled.setPaintTicks(true);
            letterLabeled.setMajorTickSpacing(20);
            letterLabeled.setMinorTickSpacing(5);
            letterLabeled.setPaintLabels(true);
            //labels
            Dictionary<Integer, Component> labels = new Hashtable<>();
            labels.put(10, new JLabel("A"));
            labels.put(50, new JLabel("B"));
            labels.put(100, new JLabel("C"));
            labels.put(150, new JLabel("D"));
            labels.put(200, new JLabel("E"));

            letterLabeled.setLabelTable(labels);

            add(createSliderPanel(letterLabeled, "Custom Labeled"));

            //i could also add labels like icons, but i didnt have icons to put here
        }

        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
        }
    }

    private JPanel createSliderPanel(JSlider slider, String label)
    {
        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.add(slider);
        panel.add(new JLabel(label, SwingConstants.LEFT));

        return panel;
    }

    private class circleSliderPanel extends JPanel
    {
        private Integer DEFAULT_VAL=100;
        private int val = DEFAULT_VAL;

        private JSlider circleSlider = new JSlider(SwingConstants.HORIZONTAL, 10, 200, 100);
        private JLabel valueLabel = new JLabel(DEFAULT_VAL.toString(), SwingConstants.RIGHT);

        public circleSliderPanel()
        {
            //setPreferredSize(new Dimension(200,100));

            circleSlider.setPreferredSize(new Dimension(200,25));

            setLayout(new GridLayout(1,3 , 5, 5));
            add(new JLabel("Circle Size"));
            add(circleSlider);
            add(valueLabel);
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Circle settings"));

            circleSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event)
                {
                    JSlider slider = (JSlider)event.getSource();
                    Integer value= slider.getValue();
                    valueLabel.setText(value.toString());
                    val  = value;
                    circle.setCircleSize(val);
                    circle.repaint();
                } 
            });
        }

        @SuppressWarnings("all")
        public int getValue()
        {
            return val;
        }

        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
        }
    }

    private class CircleComponent extends JComponent
    {
        private int Size = 10;
        private static final int DEFAULT_HEIGHT=200;
        private static final int DEFAULT_WIDTH=400;

        public CircleComponent()
        {
            setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
            setBackground(Color.BLACK);
        }

        protected void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D)g;
            
            Dimension size = getSize();
            Rectangle2D frame = new Rectangle2D.Double((size.getHeight()/2)-(Size/2), (size.getWidth()/2)-(Size/2), Size, Size); 
            Ellipse2D circle = new Ellipse2D.Double();
            circle.setFrame(frame);

            g2d.setPaint(Color.red);
            g2d.draw(circle);
        }

        public void setCircleSize(int size)
        {
            Size=size;
        }
    }
}
