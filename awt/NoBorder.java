package demo;

import java.awt.event.*;
import javax.swing.*;

public class NoBorder {
  static JFrame f = new JFrame();
  // 用于处理拖动事件，表示鼠标按下时的坐标，相对于JFrame
  int xOld = 0;
  int yOld = 0;

  public NoBorder() {
    f.setLayout(null);
    f.setSize(500, 500);
    f.setUndecorated(true);
    f.setLocationRelativeTo(null);
    // 处理拖动事件---去掉默认边框后，不能拖动了，具体实现如下
    f.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        xOld = e.getX();// 记录鼠标按下时的坐标
        yOld = e.getY();
      }
    });
    f.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        int xOnScreen = e.getXOnScreen();
        int yOnScreen = e.getYOnScreen();
        int xx = xOnScreen - xOld;
        int yy = yOnScreen - yOld;
        f.setLocation(xx, yy);// 设置拖拽后，窗口的位置
      }
    });
    // 关闭按钮
    JButton closeButton = new JButton();
    closeButton.setSize(30, 30);
    f.add(closeButton);
    closeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    f.setVisible(true);
  }

  public static void main(String[] args) {
    new NoBorder();
  }
}