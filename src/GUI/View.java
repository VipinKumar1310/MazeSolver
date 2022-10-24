/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

    package GUI;
import Project.DFS;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.JFrame;

public class View extends JFrame implements ActionListener, MouseListener{
    private int[][] maze = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,1,0,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,1,0,1,1,1,0,1},
        {1,0,0,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,0,0,1},
        {1,0,1,0,1,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,9,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    
    private int [] target = {8, 11};
    JButton submit;
    JButton clear;
    JButton cancel;
    private List<Integer> path = new ArrayList<>();
    View () {
        this.setTitle("Maze Solver");
        this.setSize(500, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setBounds(120, 400, 80, 30);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(220, 400, 80, 30);
        
        clear = new JButton("Clear");
        clear.addActionListener(this);
        clear.setBounds(320, 400, 80, 30);

        this.add(submit);
        this.add(cancel);
        this.add(clear);
        this.addMouseListener(this);
    }
    
    @Override 
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit) {
            try {
                DFS.searchPath(maze, 1, 1, path);
                this.repaint();
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
        if(e.getSource() == cancel) {
            int flag = JOptionPane.showConfirmDialog(null, "Confirm exit ?",
                    "Submit",JOptionPane.YES_NO_OPTION);
            if(flag == 0) {
                System.exit(0);
            }
        }
        if(e.getSource() == clear) {
            path.clear();
            for(int i = 0; i < maze.length; i ++) {
                for(int j = 0; j < maze[0].length; j ++) {
                    if(maze[i][j] == -1) maze[i][j] = 0;
                }
            }
            this.repaint();
        }
    }
    
    @Override 
    public void mouseClicked(MouseEvent e) {
        if(e.getX() >= 0 && e.getX() <= 520 && e.getY() >= 0 && e.getY() <= 440) {
            int x = e.getY() / 40;
            int y = e.getX() / 40;
            if(maze[x][y] != 1) {
                Graphics g = getGraphics();
                g.setColor(Color.ORANGE);
                g.fillRect(40 * target[1], 40 * target[0], 40, 40);
                g.setColor(Color.PINK);
                maze[target[0]][target[1]] = 0;
                maze[x][y] = 9;
                g.fillRect((40 * y), 40 * x, 40, 40);
                target[0] = x;
                target[1] = y;
            }
        }
    }
    
    @Override 
    public void mouseEntered(MouseEvent e) {
        
    }
    
    @Override 
    public void mousePressed(MouseEvent e) {
        
    }
    
    @Override 
    public void mouseReleased(MouseEvent e) {
        
    }
    public static void main(String [] args) {
        View view = new View();
        view.setVisible(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void paint(Graphics g) {
     super.paint(g);
     for(int i = 0; i < maze.length; i ++) {
         for(int j = 0; j < maze[0].length; j ++) {
             Color color;
             switch(maze[i][j]) {
                case 1 :
                    color = Color.DARK_GRAY;
                    break;
                case 9 :
                    color = Color.PINK;
                    break;
                default :
                    color = Color.ORANGE;
               }
               g.setColor(color);
               g.fillRect(40*j, 40*i,40,40);
               g.setColor(Color.BLACK);
               g.drawRect(40*j, 40*i, 40, 40);
         }
      }
      for(int i = 0; i < path.size(); i += 2) {
          int x = path.get(i);
          int y = path.get(i + 1);
          g.setColor(Color.WHITE);
          g.fillRect(40 * y, 40 * x, 40, 40);
      }
      System.out.print(path);
    }

}

