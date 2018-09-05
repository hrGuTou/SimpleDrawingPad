/*
        BY Haoran He
        CSC221
        23528972

        Assignment 5
 */

import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class DrawFrame extends JFrame {
    private JButton save;
    private JButton load;
    private JButton redo;
    private JButton undo;
    private JButton clear;
    private JComboBox color;
    private JComboBox shape;
    private JCheckBox filled;
    private JLabel status;
    private DrawPanel drawPad;
    private JPanel comp;    // contains undo color shape filled and clear
    private JPanel statBar;
    private String[] colorChoice = {"Black", "Blue" , "Cyan", "Dark gray", "Gray", "Green", "Light gray", "Magenta", "Orange" , "Pink", "Red", "White", "Yellow"};
    private String[] shapeSelect = {"Line", "Oval", "Rectangle"};
    private final BorderLayout layout;

    public DrawFrame() {
        super("Java Draw by Haoran He CSC221");

        layout = new BorderLayout(5,5);
        setLayout(layout);
        status = new JLabel("Position");
        comp = new JPanel();
        statBar = new JPanel();
        statBar.setLayout(new BoxLayout(statBar, BoxLayout.Y_AXIS));
        drawPad = new DrawPanel(status);
        undo = new JButton("Undo");
        redo = new JButton("Redo"); //provide redo feature
        clear = new JButton("Clear");
        color = new JComboBox(colorChoice);
        shape = new JComboBox(shapeSelect);
        filled = new JCheckBox("Fill");
        save = new JButton("Save");
        load = new JButton("Load");
        comp.add(undo);
        comp.add(redo);
        comp.add(clear);
        comp.add(color);
        comp.add(shape);
        comp.add(filled);
        statBar.add(Box.createHorizontalStrut(10));
        statBar.add(save);
        statBar.add(Box.createVerticalStrut(10));
        statBar.add(load);

        save.addActionListener(new ActionListener() {
            ArrayList<MyShape> saveToFile = drawPad.getShapes();
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();

                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Save to: ");
                int selection = chooser.showSaveDialog(frame);
                if(selection == JFileChooser.APPROVE_OPTION){
                    try {

                        FileOutputStream out = new FileOutputStream(chooser.getSelectedFile()+".ser");
                        ObjectOutputStream o = new ObjectOutputStream(out);
                        o.writeObject(saveToFile);
                        o.close();
                        out.close();

                        JOptionPane.showMessageDialog(null, "File saved successfully as " +chooser.getSelectedFile()+ ".ser" );
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        load.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();

                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Open:  ");
                int selection = chooser.showOpenDialog(frame);
                if(selection == JFileChooser.APPROVE_OPTION){
                    try {
                        FileInputStream in = new FileInputStream(chooser.getSelectedFile());
                        ObjectInputStream i = new ObjectInputStream(in);
                        ArrayList<MyShape>filein = (ArrayList<MyShape>) i.readObject();
                        i.close();
                        in.close();
                        int ans = JOptionPane.showOptionDialog(null, "Do you want to override your current drawing? \n Press No to add to current drawing","File loaded successfully", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);

                        if(ans == JOptionPane.YES_OPTION){
                            drawPad.loadFileComplete(filein);

                        }
                        else{
                            drawPad.loadFile(filein);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }





            }
        });

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPad.clearLastShape();
            }
        });

        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPad.redoDrawing();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPad.clearDrawing();
            }
        });

        color.addItemListener(new ItemListener() {
            Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    drawPad.setCurrentColor(colors[color.getSelectedIndex()]);
                }
            }
        });

        shape.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    //System.out.println(shape.getSelectedIndex()+1);
                    drawPad.setShapeType(shape.getSelectedIndex()+1);
                }
            }
        });

        filled.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean select = filled.isSelected();
                drawPad.setfilledFalse(select);
            }
        });

        statBar.add(Box.createVerticalStrut(630));
        statBar.add(status);
        add(comp, BorderLayout.NORTH);
        add(statBar, BorderLayout.WEST);
        add(drawPad, BorderLayout.CENTER);


    }


    public static void main(String args[]){
        DrawFrame run = new DrawFrame();
        run.setSize(800,800);
        run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        run.setVisible(true);
    }
}
