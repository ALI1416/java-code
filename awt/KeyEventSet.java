package demo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyEventSet {
  public static void main(String s[]) {
    JFrame f = new JFrame();
    f.setSize(500, 200);
    f.setLocationRelativeTo(null);
    JPanel p = new JPanel();
    p.setLayout(new GridLayout(1, 3));
    JButton b1 = new JButton("Alt+A");
    JButton b2 = new JButton("Ctrl+A");
    JButton b3 = new JButton("A");
    b1.setMnemonic(KeyEvent.VK_A);
    // b2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R ,
    // KeyEvent.CTRL_MASK))
    b3.addKeyListener(new KeyAdapter() {
      @SuppressWarnings("unused")
      public void KetPressed(KeyEvent e) {
        // 获取键盘键 KeyEvent.getKeyCode()
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          b3.setBackground(Color.BLACK);
        }
      }
    });
    p.add(b1);
    p.add(b2);
    p.add(b3);
    f.add(p);
    f.setVisible(true);
  }
}
