package demo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.undo.*;

@SuppressWarnings("serial")
public class Undo extends JFrame {

  static JTextArea text = new JTextArea();
  static JPanel pnl = new JPanel();
  static JButton unbtn = new JButton("撤销");
  static JButton rebtn = new JButton("恢复");
  static UndoManager undomg = new UndoManager();

  Undo() {

    super("撤销、恢复功能实例");
    setVisible(true);
    setSize(400, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(5, 5));

    pnl.setLayout(new FlowLayout(5));
    pnl.add(unbtn);
    pnl.add(rebtn);
    add(pnl, BorderLayout.NORTH);
    add(text, BorderLayout.CENTER);

    text.getDocument().addUndoableEditListener(undomg);

    unbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (undomg.canUndo()) {
          undomg.undo();
        } else {
          JOptionPane.showMessageDialog(null, "无法撤销", "警告", JOptionPane.WARNING_MESSAGE);
        }
      }
    });

    rebtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (undomg.canRedo()) {
          undomg.redo();
        } else {
          JOptionPane.showMessageDialog(null, "无法恢复", "警告", JOptionPane.WARNING_MESSAGE);
        }
      }
    });
  }

  public static void main(String[] args) {
    new Undo();
  }
}
