package demo;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.Vector;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class TableDemo extends JFrame {

  private JTable table = null;
  private JScrollPane sPane = null;
  private DefaultTableModel atm = null;

  Vector vDate = new Vector();

  Vector vName = new Vector();
  String[] str = { "ID", "姓名", "性别", "家庭地址", "联系电话" };

  public static void main(String args[]) {
    try {
      TableDemo frame = new TableDemo();
      frame.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public TableDemo() {
    super();
    vName.add("ID");
    vName.add("姓名");
    vName.add("性别");
    vName.add("家庭地址");
    vName.add("联系电话");
    getContentPane().setLayout(null);
    setBounds(100, 100, 500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setBounds(200, 200, 533, 410);
    this.setResizable(false);

    atm = new DefaultTableModel(vDate, vName);

    table = new JTable(atm);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    sPane = new JScrollPane(table);

    sPane.setBounds(0, 0, 529, 234);
    this.getContentPane().add(sPane);

    table.updateUI();

    final JButton button = new JButton();
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        delDate();
      }
    });
    button.setText("删除数据");
    button.setBounds(70, 298, 158, 46);
    getContentPane().add(button);

    final JButton button_1 = new JButton();
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addDate();
      }
    });
    button_1.setText("添加数据");
    button_1.setBounds(298, 298, 158, 46);
    getContentPane().add(button_1);
  }

  public void addDate() {
    Vector vTmp = new Vector();
    vTmp.add(new Integer(vDate.size()));
    vTmp.add("牛鼻子");
    vTmp.add(true);
    vTmp.add("牛鼻子道观");
    vTmp.add("1234567890");
    vDate.add(vTmp);
    table.updateUI();
  }

  public void delDate() {
    int i = table.getSelectedRow();
    if (i < 0)
      return;
    vDate.remove(i);
    table.updateUI();

  }
}
