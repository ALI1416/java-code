package demo;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Line extends JFrame {
  MyPanel mp;

  public Line() {
    mp = new MyPanel();
    this.setContentPane(mp);
    this.setSize(400, 400);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    new Line();
  }
}

@SuppressWarnings("serial")
class MyPanel extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawLine(20, 20, 40, 40);
    System.out.println("paintComponent");
  }
}
