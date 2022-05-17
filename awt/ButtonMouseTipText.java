package demo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
class MyButton extends JButton {
  public JToolTip createToolTip() {
    JToolTip jt = super.createToolTip();
    jt.setBackground(Color.WHITE);
    jt.setFont(new Font("宋体", Font.PLAIN, 20));
    jt.updateUI();
    return jt;
  }
}

@SuppressWarnings("serial")
public class ButtonMouseTipText extends JFrame {
  public void init() {
    MyButton btn = new MyButton();
    btn.setToolTipText("测试");
    btn.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        btn.setBackground(Color.red);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        btn.setBackground(Color.white);
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        btn.setBackground(Color.black);
      }

      @Override
      public void mousePressed(MouseEvent e) {
        btn.setBackground(Color.yellow);
      }
    });
    this.add(btn);
    this.setSize(100, 100);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    new ButtonMouseTipText().init();
  }
}