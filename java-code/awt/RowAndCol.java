package demo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class RowAndCol extends JFrame {

  private JPanel panel = null;
  private JTextArea textArea = null;
  private JLabel theLabelShowRowAndColumn = null;
  private JScrollPane scrollPane = null;

  /**
   * This is the default constructor
   */
  public RowAndCol() {
    super();
    initialize();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    this.setTitle("行列");
    this.add(getJPanel(), BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);
  }

  /**
   * This method initializes panel
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJPanel() {
    if (panel == null) {
      panel = new JPanel(new BorderLayout());
      panel.add(getScrollPane(), BorderLayout.CENTER);
      panel.add(getJLabel(), BorderLayout.SOUTH);
    }
    return panel;
  }

  /**
   * This method initializes textArea
   * 
   * @return javax.swing.JTextArea
   */
  private JTextArea getJTextArea() {
    if (textArea == null) {
      textArea = new JTextArea();
      textArea.addMouseListener(new TextMouseListener());
      textArea.addKeyListener(new TextKeyListener());
    }
    return textArea;
  }

  /**
   * This method initializes scrollPane
   * 
   * @return javax.swing.JScrollPane
   */
  private JScrollPane getScrollPane() {
    if (scrollPane == null) {
      scrollPane = new JScrollPane();
      scrollPane.setPreferredSize(new Dimension(320, 180));
      scrollPane.setViewportView(getJTextArea());
    }
    return scrollPane;
  }

  /**
   * This method initializes theLabelShowRowAndColumn
   * 
   * @return javax.swing.JTextArea
   */
  private JLabel getJLabel() {
    if (theLabelShowRowAndColumn == null) {
      theLabelShowRowAndColumn = new JLabel();
      theLabelShowRowAndColumn.setText("行:1   列:1");
      theLabelShowRowAndColumn.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
    }
    return theLabelShowRowAndColumn;
  }

  // 文本区的鼠标事件的适配器
  class TextMouseListener extends MouseAdapter {
    // 按下鼠标
    public void mousePressed(MouseEvent e) {
      // 敲击左键
      if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
        getCurrenRowAndCol(); // 获得当前的行和列位置
      }
    }

    // 松开鼠标
    public void mouseReleased(MouseEvent e) {
      // 敲击左键
      if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
        getCurrenRowAndCol(); // 获得当前的行和列位置
      }
    }
  }

  // 文本区的键盘事件的适配器
  class TextKeyListener extends KeyAdapter {
    // 按下某键
    public void keyPressed(KeyEvent e) {
      getCurrenRowAndCol(); // 获得当前的行和列位置
    }

    // 释放某键
    public void keyReleased(KeyEvent e) {
      getCurrenRowAndCol(); // 获得当前的行和列位置
    }
  }

  // 获得当前的行和列位置,并显示在theLabelShowRowAndColumn上
  private void getCurrenRowAndCol() {
    int row = 0;
    int col = 0;
    int pos = textArea.getCaretPosition(); // 获得光标相对0行0列的位置

    // 列!!!
    col = pos - textArea.getText().substring(0, pos).lastIndexOf("\n");
    // 行!!!
    try {
      row = textArea.getLineOfOffset(pos) + 1; // 返回行是从0算起的,所以+1
    } catch (Exception exception) {
    }
    theLabelShowRowAndColumn.setText("行:" + row + " 列:" + col);
  }

  public static void main(String[] args) {
    new RowAndCol();
  }
}
