package demo;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Border {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Swing边框演示");
    frame.setIconImage(new ImageIcon("D:\\1\\qq_256.png").getImage());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0, 2, 5, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    JButton p1 = new JButton();
    p1.setBorder(BorderFactory.createLineBorder(Color.red, 3));
    p1.add(new JLabel("线边框"));
    panel.add(p1);
    JPanel p2 = new JPanel();
    p2.setBorder(BorderFactory.createEtchedBorder());
    p2.add(new JLabel("蚀刻边框"));
    panel.add(p2);
    JPanel p3 = new JPanel();
    p3.setBorder(BorderFactory.createRaisedBevelBorder());
    p3.add(new JLabel("斜面边框(凸)"));
    panel.add(p3);
    JPanel p4 = new JPanel();
    p4.setBorder(BorderFactory.createLoweredBevelBorder());
    p4.add(new JLabel("斜面边框(凹)"));
    panel.add(p4);
    JPanel p5 = new JPanel();
    p5.setBorder(BorderFactory.createTitledBorder("标题"));
    p5.add(new JLabel("标题边框"));
    panel.add(p5);
    JPanel p6 = new JPanel();
    TitledBorder tb = BorderFactory.createTitledBorder("标题");
    tb.setTitleJustification(TitledBorder.RIGHT);
    p6.setBorder(tb);
    p6.add(new JLabel("标签边框(右)"));
    panel.add(p6);
    JPanel p7 = new JPanel();
    p7.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.yellow));
    p7.add(new JLabel("花色边框"));
    panel.add(p7);
    JPanel p8 = new JPanel();
    javax.swing.border.Border b1 = BorderFactory.createLineBorder(Color.blue, 2);
    javax.swing.border.Border b2 = BorderFactory.createEtchedBorder();
    p8.setBorder(BorderFactory.createCompoundBorder(b1, b2));
    p8.add(new JLabel("组合边框"));
    panel.add(p8);
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(new Dimension(300, 300));
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}