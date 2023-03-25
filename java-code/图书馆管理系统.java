package librarySystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
class Book2 {
	void addDialog() {// 添加内容
		JDialog jd = new JDialog();// 弹窗
		jd.setTitle("添加");// 设置标题
		jd.setSize(400, 300);// 设置大小
		jd.setLocationRelativeTo(null);// 设置显示居中
		JLabel l1 = new JLabel("书号");// 标签
		JLabel l2 = new JLabel("书名");
		JLabel l3 = new JLabel("作者");
		JLabel l4 = new JLabel("出版日期");
		JLabel l5 = new JLabel("出版社");
		JLabel l6 = new JLabel("存放位置");
		JLabel l7 = new JLabel("数量");
		JTextField t1 = new JTextField();// 文本框
		JTextField t2 = new JTextField();
		JTextField t3 = new JTextField();
		JTextField t4 = new JTextField();
		JTextField t5 = new JTextField();
		JTextField t6 = new JTextField();
		JTextField t7 = new JTextField();
		JPanel p1 = new JPanel();// 面板
		p1.setLayout(new GridLayout(7, 2));// 设置格式
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		p1.add(l3);
		p1.add(t3);
		p1.add(l4);
		p1.add(t4);
		p1.add(l5);
		p1.add(t5);
		p1.add(l6);
		p1.add(t6);
		p1.add(l7);
		p1.add(t7);
		jd.add(p1);
		JButton b1 = new JButton("添加");// 按钮
		JButton b2 = new JButton("关闭");
		JButton b3 = new JButton("清空");
		JPanel p2 = new JPanel();
		p2.add(b1);
		p2.add(b3);
		p2.add(b2);
		jd.add(p2, "South");// 添加到北侧
		jd.setVisible(true);// 设置可见
		b1.addActionListener(new ActionListener() {// 点击添加
			public void actionPerformed(ActionEvent arg0) {// 事件监听器
				JDialog jd = new JDialog();
				if (t1.getText().length() == 0 || t2.getText().length() == 0 || t3.getText().length() == 0
						|| t4.getText().length() == 0 || t5.getText().length() == 0 || t6.getText().length() == 0
						|| t7.getText().length() == 0) {// 当没有全部填入时
					jd.setTitle("错误");
					jd.setSize(200, 200);
					jd.setLocationRelativeTo(null);
					JLabel l = new JLabel("请全部输入！");
					l.setFont(new Font("宋体", Font.PLAIN, 30));
					jd.add(l);
					JButton b = new JButton("确认");
					jd.add(b, "South");
					b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							jd.setVisible(false);
						}
					});
					jd.setVisible(true);
				} else {// 把输入内容添加到表格
					add(t1.getText(), t2.getText(), t3.getText(), t4.getText(), t5.getText(), t6.getText(),
							t7.getText());
				}
			}
		});
		b2.addActionListener(new ActionListener() {// 关闭
			public void actionPerformed(ActionEvent arg0) {
				jd.setVisible(false);
			}
		});
		b3.addActionListener(new ActionListener() {// 清空
			public void actionPerformed(ActionEvent arg0) {
				t1.setText(null);// 设置内容为空
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
				t5.setText(null);
				t6.setText(null);
				t7.setText(null);
			}
		});
	}

	void add(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {// 添加函数
		Vector vTmp = new Vector();// 向量
		vTmp.add(s1);// 挨个添加字符串
		vTmp.add(s2);
		vTmp.add(s3);
		vTmp.add(s4);
		vTmp.add(s5);
		vTmp.add(s6);
		vTmp.add(s7);
		vDate.add(vTmp);// 向量加到另一个向量里，形成二维向量
		table.updateUI();// 更新表格
	}

	void delete() {// 删除函数(删除行)
		int i = table.getSelectedRow();// 获取行数，为空时等于-1
		JDialog jd = new JDialog();
		jd.setTitle("删除");
		jd.setSize(200, 200);
		jd.setLocationRelativeTo(null);
		JLabel l = new JLabel("确认删除吗？");
		if (i == -1)
			l.setText("无法删除！");
		l.setFont(new Font("宋体", Font.PLAIN, 30));
		jd.add(l);
		JButton b1 = new JButton("确认");
		JButton b2 = new JButton("取消");
		if (i == -1) {// 提示是否确认删除
			b1.setVisible(false);
			b2.setText("确认");
		}
		JPanel p = new JPanel();
		p.add(b1);
		p.add(b2);
		jd.add(p, "South");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vDate.remove(i);// 移除行
				table.updateUI();
				jd.setVisible(false);
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jd.setVisible(false);
			}
		});
		jd.setVisible(true);
	}

	@SuppressWarnings("resource")
	void open() {// 打开文件
		try {
			File file = null;
			JFileChooser fc = new JFileChooser();
			int select = fc.showOpenDialog(jf);
			if (select == JFileChooser.APPROVE_OPTION) {// 弹出打开文件对话框
				file = fc.getSelectedFile();
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); // 建立一个输入流对象reader
				BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
				String line = "";
				for (int i = 0; (line = br.readLine()) != null; i++) {
					add(null, null, null, null, null, null, null);
					String[] strs = line.split("\t");// 分栏放入字符串
					for (int j = 0; j < 7; j++) {
						table.setValueAt(strs[j], i, j);// 赋值给每个单元格
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void save() {
		int num = table.getRowCount();// 总行
		if (num == 0) {// 没有数据
			JDialog jd = new JDialog();
			jd.setTitle("保存");
			jd.setSize(200, 200);
			jd.setLocationRelativeTo(null);
			JLabel l = new JLabel("无数据！");
			l.setFont(new Font("宋体", Font.PLAIN, 30));
			JButton b = new JButton("确认");
			jd.add(l);
			jd.add(b, "South");
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jd.setVisible(false);
				}
			});
			jd.setVisible(true);
		} else {
			try {
				File file = null;
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xls", "xls");// 保存为xls
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileFilter(filter);
				int result = jFileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					file = jFileChooser.getSelectedFile();
					if (!file.getName().endsWith(".xls")) {// 只显示以xls结尾的文件
						file = new File(file.getPath() + ".xls");// 保存文件以xls结尾
					}
				}
				FileWriter out = new FileWriter(file);// 新建输出
				for (int i = 0; i < table.getRowCount(); i++) {
					for (int j = 0; j < table.getColumnCount(); j++) {
						out.write(table.getValueAt(i, j).toString() + "\t");// 输出各个单元格内容，每列用\t分割，每行用\n分割
					}
					out.write("\n");
				}
				out.close();
				JOptionPane.showMessageDialog(null, "文件导出成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void findcase(String s) {// 查找分支
		int k = 0;
		int n[] = new int[table.getRowCount()];
		for (int j = 0; j < table.getRowCount(); j++) {
			if (table.getValueAt(j, findflag - 1).toString().equals(s)) {// 传入的字符串与某列所有单元格内容比较
				n[k++] = j;
			}
		}
		int r[] = new int[k];
		for (int i = 0; i < k; i++) {
			r[i] = n[i];
		}
		table.setDefaultRenderer(Object.class, new EvenOddRenderer(r));// 设置行颜色
		table.updateUI();
	}

	void find() {// 查找函数
		int num = table.getRowCount();
		if (num == 0) {// 没有内容
			JDialog jd = new JDialog();
			jd.setTitle("查找");
			jd.setSize(200, 200);
			jd.setLocationRelativeTo(null);
			JLabel l = new JLabel("无数据！");
			l.setFont(new Font("宋体", Font.PLAIN, 30));
			JButton b = new JButton("确认");
			jd.add(l);
			jd.add(b, "South");
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jd.setVisible(false);
				}
			});
			jd.setVisible(true);
		} else {
			JDialog jd = new JDialog();
			jd.setTitle("查找");
			jd.setSize(500, 170);
			jd.setLocationRelativeTo(null);
			JPanel p1 = new JPanel();
			JButton b1 = new JButton("查找");
			JButton b3 = new JButton("关闭");
			jd.add(p1, "South");
			p1.add(b1);
			p1.add(b3);
			JPanel p2 = new JPanel();
			JRadioButton rb1 = new JRadioButton("书号");
			JRadioButton rb2 = new JRadioButton("书名");
			JRadioButton rb3 = new JRadioButton("作者");
			JRadioButton rb4 = new JRadioButton("出版日期");
			JRadioButton rb5 = new JRadioButton("出版社");
			JRadioButton rb6 = new JRadioButton("存放位置");
			JRadioButton rb7 = new JRadioButton("数量");
			ButtonGroup bg = new ButtonGroup();// 划为一组，只能选一个
			bg.add(rb1);
			bg.add(rb2);
			bg.add(rb3);
			bg.add(rb4);
			bg.add(rb5);
			bg.add(rb6);
			bg.add(rb7);
			p2.add(rb1);
			p2.add(rb2);
			p2.add(rb3);
			p2.add(rb4);
			p2.add(rb5);
			p2.add(rb6);
			p2.add(rb7);
			JTextField t1 = new JTextField();
			t1.setFont(new Font("宋体", Font.PLAIN, 20));
			jd.add(t1);
			b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (t1.getText().length() != 0 && findflag != 0) {// 输入了内容且选择了列
						findcase(t1.getText());
					}
				}
			});

			rb1.addActionListener(new ActionListener() {// 选择了列，标记
				public void actionPerformed(ActionEvent arg0) {
					if (rb1.isSelected() == true)
						findflag = 1;
				}
			});
			rb2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rb2.isSelected() == true)
						findflag = 2;
				}
			});
			rb3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rb3.isSelected() == true)
						findflag = 3;
				}
			});
			rb4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rb4.isSelected() == true)
						findflag = 4;
				}
			});
			rb5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rb5.isSelected() == true)
						findflag = 5;
				}
			});
			rb6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rb6.isSelected() == true)
						findflag = 6;
				}
			});
			rb7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rb7.isSelected() == true)
						findflag = 7;
				}
			});
			jd.add(p2, "North");
			b3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int r[] = new int[0];
					table.setDefaultRenderer(Object.class, new EvenOddRenderer(r));// 关闭后，设置颜色为默认
					table.updateUI();
					jd.setVisible(false);
				}
			});
			jd.setVisible(true);
		}
	}

	JFrame jf = new JFrame("图书管理系统");
	private JTable table = null;
	private JScrollPane sPane = null;
	private DefaultTableModel atm = null;
	Vector vDate = new Vector();
	Vector vName = new Vector();
	int changeflag = -1;
	int findflag = 0;

	Book2() {
		jf.setSize(1200, 900);// 设置大小
		jf.setLocationRelativeTo(null);// 设置居中显示
		vName.add("书号");
		vName.add("书名");
		vName.add("作者");
		vName.add("出版日期");
		vName.add("出版社");
		vName.add("存放位置");
		vName.add("数量");
		atm = new DefaultTableModel(vDate, vName);// 表格模式为二维向量
		table = new JTable(atm);// 把模式添加到表格
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 设置表格选择模式
		sPane = new JScrollPane(table);// 表格添加到滚动条
		table.setEnabled(true);// 设置表格可编辑
		jf.add(sPane);// 滚动条添加到框架
		table.updateUI();// 更新表格
		JButton open = new JButton("打开");
		JButton save = new JButton("保存");
		JButton add = new JButton("添加");
		JButton delete = new JButton("删除");
		JButton find = new JButton("查找");
		JButton change = new JButton("　可编辑");
		JPanel p1 = new JPanel();
		p1.add(open);
		p1.add(save);
		p1.add(add);
		p1.add(delete);
		p1.add(find);
		p1.add(change);
		open.addActionListener(new ActionListener() {// 打开文件
			public void actionPerformed(ActionEvent arg0) {
				open();
			}
		});
		save.addActionListener(new ActionListener() {// 保存文件
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		add.addActionListener(new ActionListener() {// 增加
			public void actionPerformed(ActionEvent arg0) {
				addDialog();
			}
		});
		delete.addActionListener(new ActionListener() {// 删除
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		find.addActionListener(new ActionListener() {// 查找
			public void actionPerformed(ActionEvent arg0) {
				find();
			}
		});

		change.addActionListener(new ActionListener() {// 修改
			public void actionPerformed(ActionEvent arg0) {
				changeflag *= -1;// 点一次标记换一次
				if (changeflag == 1) {
					table.setEnabled(false);// 设置表格不可编辑
					change.setText("不可编辑");// 更改按钮显示
				} else {
					table.setEnabled(true);// 设置表格可编辑
					change.setText("　可编辑");// 更改按钮显示
				}
			}
		});
		jf.add(p1, "South");// 面板添加到框架南侧
		jf.setVisible(true);// 设置框架显示
		jf.setDefaultCloseOperation(3);// 设置关闭退出
	}
}

class EvenOddRenderer implements TableCellRenderer {// 设置表格颜色
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	int[] r;

	public EvenOddRenderer(int[] r) {// 传入参数
		this.r = r;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);
		Color foreground = null, background = null;
		for (int i = 0; i < r.length; i++) {// 上色
			if (row == r[i]) {
				foreground = Color.WHITE;
				background = Color.GRAY;
			}
		}
		renderer.setForeground(foreground);
		renderer.setBackground(background);
		return renderer;
	}
}

public class Library {
	public static void main(String[] args) {
		new Book2();
	}
}
