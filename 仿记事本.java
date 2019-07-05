package note;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.undo.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

@SuppressWarnings({ "resource", "unchecked", "rawtypes" })
class Text {

	String fontStyle(Font t) {
		int i = t.getStyle();
		if (i == 0)
			return "常规";
		if (i == 2)
			return "倾斜";
		if (i == 1)
			return "粗体";
		if (i == 3)
			return "粗偏斜体";
		return "";
	}

	int fontStyle2(String s) {
		if (s.equals("常规") == true)
			return 0;
		if (s.equals("倾斜") == true)
			return 2;
		if (s.equals("粗体") == true)
			return 1;
		if (s.equals("粗偏斜体") == true)
			return 3;
		return -1;
	}

	Font defaultFont = new Font("宋体", Font.PLAIN, 24);
	Font newFont = new Font("宋体", Font.PLAIN, 24);

	void showFont(JTextArea t, JLabel l) {
		if (t != null) {
			t.setFont(newFont);
		} else {
			l.setFont(newFont);
		}
	}

	void font() {
		JDialog jd = new JDialog(jf);
		jd.setTitle("字体");
		jd.setSize(680, 600);
		jd.setLocationRelativeTo(null);
		jd.setIconImage(ico.getImage());
		jd.setLayout(null);
		JLabel lName = new JLabel("字体");
		JLabel lStyle = new JLabel("字形");
		JLabel lSize = new JLabel("大小");
		JLabel lDemo = new JLabel();// 示例
		JButton bOK = new JButton("确定");
		JButton bCancel = new JButton("取消");
		JButton bDefault = new JButton("默认");
		JTextField tName = new JTextField();
		JTextField tStyle = new JTextField();
		JTextField tSize = new JTextField();
		tName.setEditable(false);
		tName.setBackground(Color.WHITE);
		tStyle.setEditable(false);
		tStyle.setBackground(Color.WHITE);
		tSize.setEditable(false);
		tSize.setBackground(Color.WHITE);
		String[] style = { "常规", "倾斜", "粗体", "粗偏斜体" };// 字体风格
		String[] size = { "6", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "32", "36", "40",
				"44", "48", "52", "56", "60", "68", "76", "84", "92", "100" };// 字体大小
		tName.setText(text.getFont().getFontName());
		tStyle.setText(fontStyle(text.getFont()));
		tSize.setText(Integer.toString(text.getFont().getSize()));
		Vector vName = new Vector();
		JList listName = new JList(vName);
		JList listStyle = new JList(style);
		JList listSize = new JList(size);
		JScrollPane scName = new JScrollPane(listName);
		JScrollPane scStyle = new JScrollPane(listStyle);
		JScrollPane scSize = new JScrollPane(listSize);
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();// 遍历字体
		String[] fontName = e.getAvailableFontFamilyNames();
		for (int i = 0; i < fontName.length; i++) {
			vName.addElement(fontName[i]);
		}
		listName.setSelectedValue(text.getFont().getFontName(), true);
		listStyle.setSelectedValue(fontStyle(text.getFont()), true);
		listSize.setSelectedValue(Integer.toString(text.getFont().getSize()), true);
		lDemo.setBorder(BorderFactory.createTitledBorder("示例"));
		lDemo.setText("漢字Abc123");
		lDemo.setHorizontalAlignment(SwingConstants.CENTER);// 居中显示
		lDemo.setFont(newFont);// 设置示例字体
		lName.setBounds(30, 20, 260, 30);
		lStyle.setBounds(320, 20, 180, 30);
		lSize.setBounds(530, 20, 100, 30);
		tName.setBounds(30, 50, 260, 30);
		tStyle.setBounds(320, 50, 180, 30);
		tSize.setBounds(530, 50, 100, 30);
		scName.setBounds(30, 80, 260, 200);
		scStyle.setBounds(320, 80, 180, 200);
		scSize.setBounds(530, 80, 100, 200);
		lDemo.setBounds(30, 310, 600, 130);
		bDefault.setBounds(230, 470, 120, 40);
		bOK.setBounds(370, 470, 120, 40);
		bCancel.setBounds(510, 470, 120, 40);
		jd.add(lName);
		jd.add(lStyle);
		jd.add(lSize);
		jd.add(tName);
		jd.add(tStyle);
		jd.add(tSize);
		jd.add(scName);
		jd.add(scStyle);
		jd.add(scSize);
		jd.add(lDemo);
		jd.add(bOK);
		jd.add(bCancel);
		jd.add(bDefault);
		listName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tName.setText(listName.getSelectedValue().toString());
				newFont = new Font(listName.getSelectedValue().toString(), newFont.getStyle(), newFont.getSize());
				showFont(null, lDemo);
			}
		});
		listStyle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tStyle.setText(listStyle.getSelectedValue().toString());
				newFont = new Font(newFont.getFontName(), fontStyle2(listStyle.getSelectedValue().toString()),
						newFont.getSize());
				showFont(null, lDemo);
			}
		});
		listSize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tSize.setText(listSize.getSelectedValue().toString());
				newFont = new Font(newFont.getFontName(), newFont.getStyle(),
						Integer.parseInt(listSize.getSelectedValue().toString()));
				showFont(null, lDemo);
			}
		});
		bOK.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showFont(text, null);
				jd.setVisible(false);
			}
		});
		bCancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jd.setVisible(false);
			}
		});
		listName.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
					tName.setText(listName.getSelectedValue().toString());
					newFont = new Font(listName.getSelectedValue().toString(), newFont.getStyle(), newFont.getSize());
					showFont(null, lDemo);
				}
			}
		});
		listStyle.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
					tStyle.setText(listStyle.getSelectedValue().toString());
					newFont = new Font(newFont.getFontName(), fontStyle2(listStyle.getSelectedValue().toString()),
							newFont.getSize());
					showFont(null, lDemo);
				}
			}
		});
		listSize.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
					tSize.setText(listSize.getSelectedValue().toString());
					newFont = new Font(newFont.getFontName(), newFont.getStyle(),
							Integer.parseInt(listSize.getSelectedValue().toString()));
					showFont(null, lDemo);
				}
			}
		});
		bDefault.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				listName.setSelectedValue(defaultFont.getFontName(), true);
				listStyle.setSelectedValue(fontStyle(defaultFont), true);
				listSize.setSelectedValue(Integer.toString(defaultFont.getSize()), true);
				tName.setText(listName.getSelectedValue().toString());
				tStyle.setText(listStyle.getSelectedValue().toString());
				tSize.setText(listSize.getSelectedValue().toString());
				newFont = defaultFont;
				showFont(null, lDemo);
			}
		});
		jd.setVisible(true);
	}

	int colorArea = 0;
	int colorValue = 0;
	Color[] defaultColor = new Color[4];
	{
		defaultColor[0] = new Color(0, 0, 0);
		defaultColor[1] = new Color(255, 255, 255);
		defaultColor[2] = new Color(0, 0, 0);
		defaultColor[3] = new Color(192, 192, 192);
	}
	Color[] newColor = new Color[4];
	{
		newColor[0] = new Color(0, 0, 0);
		newColor[1] = new Color(255, 255, 255);
		newColor[2] = new Color(0, 0, 0);
		newColor[3] = new Color(192, 192, 192);
	}

	void showColor(JTextArea t) {
		t.setForeground(newColor[0]);
		t.setBackground(newColor[1]);
		t.setSelectedTextColor(newColor[2]);
		t.setSelectionColor(newColor[3]);
	}

	void color() {
		Color[] c = new Color[16];
		c[0] = new Color(0, 0, 0);
		c[1] = new Color(0, 0, 128);
		c[2] = new Color(0, 128, 0);
		c[3] = new Color(0, 128, 128);
		c[4] = new Color(128, 0, 0);
		c[5] = new Color(128, 0, 128);
		c[6] = new Color(128, 128, 0);
		c[7] = new Color(192, 192, 192);
		c[8] = new Color(128, 128, 128);
		c[9] = new Color(0, 0, 255);
		c[10] = new Color(0, 255, 0);
		c[11] = new Color(0, 255, 255);
		c[12] = new Color(255, 0, 0);
		c[13] = new Color(255, 0, 255);
		c[14] = new Color(255, 255, 0);
		c[15] = new Color(255, 255, 255);
		JDialog jd = new JDialog(jf);
		jd.setTitle("颜色");
		jd.setSize(622, 520);
		jd.setLocationRelativeTo(null);
		jd.setIconImage(ico.getImage());
		jd.setLayout(null);
		JButton bOK = new JButton("确定");
		JButton bCancel = new JButton("取消");
		JButton bDefault = new JButton("默认");
		bDefault.setBounds(180, 400, 120, 40);
		bOK.setBounds(320, 400, 120, 40);
		bCancel.setBounds(460, 400, 120, 40);
		jd.add(bDefault);
		jd.add(bOK);
		jd.add(bCancel);
		JRadioButton b1 = new JRadioButton("屏幕文字", true);
		JRadioButton b2 = new JRadioButton("屏幕背景");
		JRadioButton b3 = new JRadioButton("选中文字");
		JRadioButton b4 = new JRadioButton("选中背景");
		ButtonGroup g = new ButtonGroup();
		g.add(b1);
		g.add(b2);
		g.add(b3);
		g.add(b4);
		JPanel pc[] = new JPanel[4];
		pc[0] = new JPanel();
		pc[1] = new JPanel();
		pc[2] = new JPanel();
		pc[3] = new JPanel();
		JTextField t[] = new JTextField[4];
		t[0] = new JTextField();
		t[1] = new JTextField();
		t[2] = new JTextField();
		t[3] = new JTextField();
		JButton moreColor = new JButton("<html>" + "更多" + "<br>" + "颜色" + "</html>");
		JPanel pBig = new JPanel();
		pBig.setBackground(Color.WHITE);
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(0, 0, 600, 180);
		b1.setBounds(10, 10, 150, 40);
		b2.setBounds(10, 50, 150, 40);
		b3.setBounds(10, 90, 150, 40);
		b4.setBounds(10, 130, 150, 40);
		pc[0].setBounds(200, 15, 30, 30);
		pc[1].setBounds(200, 55, 30, 30);
		pc[2].setBounds(200, 95, 30, 30);
		pc[3].setBounds(200, 135, 30, 30);
		t[0].setBounds(300, 15, 150, 30);
		t[1].setBounds(300, 55, 150, 30);
		t[2].setBounds(300, 95, 150, 30);
		t[3].setBounds(300, 135, 150, 30);
		moreColor.setBounds(490, 95, 70, 70);
		pBig.setBounds(490, 15, 70, 70);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(pc[0]);
		p1.add(pc[1]);
		p1.add(pc[2]);
		p1.add(pc[3]);
		p1.add(t[0]);
		p1.add(t[1]);
		p1.add(t[2]);
		p1.add(t[3]);
		p1.add(moreColor);
		p1.add(pBig);
		JPanel colorBar = new JPanel();
		colorBar.setBounds(0, 200, 600, 70);
		colorBar.setLayout(null);
		JLabel l = new JLabel();
		l.setIcon(new ImageIcon("D:\\1\\color16.png"));
		l.setBounds(11, 5, 577, 40);
		JLabel arrow = new JLabel();
		arrow.setIcon(new ImageIcon("D:\\1\\arrow.png"));
		colorBar.add(arrow);
		colorBar.add(l);
		jd.add(p1);
		jd.add(colorBar);
		JTextArea tDemo = new JTextArea();// 示例
		tDemo.setText("示例文字");
		tDemo.setFont(newFont);
		tDemo.setForeground(newColor[0]);
		tDemo.setBackground(newColor[1]);
		tDemo.setSelectedTextColor(newColor[2]);
		tDemo.setSelectionColor(newColor[3]);
		for (int i = 0; i < 4; i++) {
			pc[i].setBackground(newColor[i]);
			t[i].setText(Integer.toHexString(c[i].getRGB()).toUpperCase());
			t[i].setEditable(false);
			t[i].setBackground(Color.WHITE);
		}
		// for (int i = 0; i < 16; i++) {
		// if (c[i] == pc[colorArea].getBackground())
		// pBig.setBackground(c[i]);
		// }
		tDemo.setBounds(20, 280, 560, 100);
		jd.add(tDemo);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorArea = 0;
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorArea = 1;
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorArea = 2;
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorArea = 3;
			}
		});
		bOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showColor(text);
				jd.setVisible(false);
			}
		});
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jd.setVisible(false);
			}
		});
		bDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 4; i++) {
					pc[i].setBackground(defaultColor[i]);
					t[i].setText(Integer.toHexString(c[i].getRGB()).toUpperCase());
					newColor[i] = defaultColor[i];
				}
				pBig.setBackground(Color.WHITE);
				showColor(tDemo);
			}
		});
		moreColor.addActionListener(new ActionListener() {// 更多颜色
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				JColorChooser chooser = new JColorChooser(); // 实例化颜色选择器
				Color color = chooser.showDialog(jd, "更多颜色", Color.lightGray); // 得到选择的颜色
				if (color != null) {
					pBig.setBackground(color);
					pc[colorArea].setBackground(color);// 设置颜色
					t[colorArea].setText(Integer.toHexString(color.getRGB()).toUpperCase());// 显示16进制大写颜色代码
					arrow.setVisible(false);// 箭头取消显示
					newColor[colorArea] = color;
					showColor(tDemo);
				}
			}
		});

		colorBar.addMouseListener(new MouseAdapter() {// 颜色条
			public void mouseClicked(MouseEvent e) {
				arrow.setVisible(true);// 显示箭头
				colorValue = (e.getX() - 12) / 36;// 点击色块颜色值
				arrow.setBounds(36 * colorValue + 15, 45, 30, 20);// 箭头显示位置
				t[colorArea].setText(Integer.toHexString(c[colorValue].getRGB()).toUpperCase());// 显示16进制大写颜色代码
				pc[colorArea].setBackground(c[colorValue]);// 设置颜色
				pBig.setBackground(c[colorValue]);// 设置大色块颜色
				newColor[colorArea] = c[colorValue];
				showColor(tDemo);
			}
		});
		jd.setVisible(true);
	}

	void about() {
		String message = "版本 1.0\n感谢您的使用!";
		JOptionPane.showMessageDialog(jf, message, "关于\"记事本\"", JOptionPane.PLAIN_MESSAGE);
	}

	String readFile(File file) {// 读文件
		try {
			if (file != null && file.canRead()) {
				Reader in;
				in = new FileReader(file);
				StringWriter out = new StringWriter();
				int c = -1;
				while ((c = in.read()) != -1) {
					out.write(c);
				}
				return out.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	void writeFile(File file) {// 写文件
		try {
			file = new File(file.getPath());
			PrintWriter pw = new PrintWriter(new FileWriter(file));
			pw.print(text.getText());
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int newFile() {// 新建文件
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");// 保存为txt
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileFilter(filter);
		int result = jFileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = jFileChooser.getSelectedFile();
			if (!file.getName().endsWith(".txt")) {
				file = new File(file.getPath() + ".txt");
				return 1;
			}
		}
		return 0;
	}

	File file = null;

	int exitOption() {// 退出选项
		if ((text.getText().length() == 0 && file == null) || readFile(file).equals(text.getText()) == true) {// 文件位空或未被修改
			return 1;// 退出
		} else {
			int value = JOptionPane.showConfirmDialog(null, "是否更改保存", "记事本", 1);
			if (value == JOptionPane.OK_OPTION) {
				if (file == null) {// 没有打开文件
					if (newFile() == 1) {
						writeFile(file);
					} else {
						return 0;
					}
				} else {// 打开文件进行编辑
					writeFile(file);
				}
				return 1;
			}
			if (value == JOptionPane.NO_OPTION) {
				return 1;
			}
			if (value == JOptionPane.CANCEL_OPTION) {
				return 0;// 取消
			}
		}
		return 0;
	}

	void getRowCol() {
		int row = 0;
		int col = 0;
		int pos = text.getCaretPosition(); // 获得光标相对0行0列的位置
		col = pos - text.getText().substring(0, pos).lastIndexOf("\n");
		try {
			row = text.getLineOfOffset(pos) + 1; // 返回行是从0算起的,所以+1
		} catch (Exception exception) {
		}
		showRowCol.setText(" 第 " + row + " 行 , 第 " + col + " 列 ");
	}

	JFrame jf = new JFrame("记事本");
	JTextArea text = new JTextArea();
	JFileChooser fc = new JFileChooser();
	JLabel showRowCol = new JLabel();
	ImageIcon ico = new ImageIcon("D:\\1\\notepad.png");
	String findStr = null;// 查找和查找下一个所需的字符串

	public Text() {
		jf.setIconImage(ico.getImage());
		jf.setSize(1100, 730);
		jf.setLocationRelativeTo(null);
		jf.addKeyListener(null);
		text.setFont(defaultFont);
		text.setForeground(defaultColor[0]);
		text.setBackground(defaultColor[1]);
		text.setSelectedTextColor(defaultColor[2]);
		text.setSelectionColor(defaultColor[3]);
		JMenuBar jmb = new JMenuBar();
		JScrollPane jsp = new JScrollPane(text);
		JMenu mFile = new JMenu("文件(F)");
		JMenu mEdit = new JMenu("编辑(E)");
		JMenu mOther = new JMenu("其他(O)");
		mFile.setMnemonic(KeyEvent.VK_F);
		mEdit.setMnemonic(KeyEvent.VK_E);
		mOther.setMnemonic(KeyEvent.VK_O);
		JMenuItem fNew = new JMenuItem("新建(N)", KeyEvent.VK_N);
		JMenuItem fOpen = new JMenuItem("打开(O)...", KeyEvent.VK_O);
		JMenuItem fSave = new JMenuItem("保存(S)", KeyEvent.VK_S);
		JMenuItem fSaveAs = new JMenuItem("另存为(A)...    ", KeyEvent.VK_A);
		JMenuItem fExit = new JMenuItem("退出(X)", KeyEvent.VK_X);
		fNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exitOption() == 1)
					text.setText(null);
			}
		});
		fOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exitOption() == 1) {
					int select = fc.showOpenDialog(jf);
					if (select == JFileChooser.APPROVE_OPTION) {
						file = fc.getSelectedFile();
						text.setText(readFile(file));
					}
				}
			}
		});
		fSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (file == null) {
					if (newFile() == 1)
						writeFile(file);
				} else {
					writeFile(file);
				}
			}
		});
		fSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (newFile() == 1)
					writeFile(file);
			}
		});
		fExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exitOption() == 1)
					System.exit(0);
			}
		});
		jf.addWindowListener(new WindowAdapter() {// 关闭按钮
			public void windowClosing(WindowEvent e) {
				if (exitOption() == 1)
					System.exit(0);
			}
		});
		fNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		fOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		fSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		fSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.ALT_MASK));
		fExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_MASK));
		mFile.add(fNew);
		mFile.add(fOpen);
		mFile.add(fSave);
		mFile.add(fSaveAs);
		mFile.addSeparator();
		mFile.add(fExit);
		JMenuItem eUndo = new JMenuItem("撤消(U)", KeyEvent.VK_U);
		JMenuItem eRedo = new JMenuItem("恢复(E)", KeyEvent.VK_E);
		JMenuItem eCut = new JMenuItem("剪切(T)", KeyEvent.VK_T);
		JMenuItem eCopy = new JMenuItem("复制(C)", KeyEvent.VK_C);
		JMenuItem ePaste = new JMenuItem("粘贴(V)", KeyEvent.VK_V);
		JMenuItem eDelete = new JMenuItem("删除(Del)", KeyEvent.VK_DELETE);
		JMenuItem eFind = new JMenuItem("查找(F)...", KeyEvent.VK_F);
		JMenuItem eFindNext = new JMenuItem("查找下一个(F3)...    ", KeyEvent.VK_F3);
		JMenuItem eReplace = new JMenuItem("替换(R)...", KeyEvent.VK_R);
		JMenuItem eGoto = new JMenuItem("转到(G)...", KeyEvent.VK_G);
		JMenuItem eAll = new JMenuItem("全选(A)", KeyEvent.VK_A);
		JMenuItem eDate = new JMenuItem("时间/日期(F5)", KeyEvent.VK_F5);
		eUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		eRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
		eCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
		eCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		ePaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		eDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
		eFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
		eReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
		eGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
		eAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
		eDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		eFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		eDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		UndoManager undomg = new UndoManager();
		text.getDocument().addUndoableEditListener(undomg);
		eUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (undomg.canUndo()) {
					undomg.undo();
				}
			}
		});
		eRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (undomg.canRedo()) {
					undomg.redo();
				}
			}
		});
		eCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.copy();
			}
		});
		ePaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.paste();
			}
		});
		eCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.cut();
			}
		});
		eAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.selectAll();
			}
		});
		eDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int start;
				int end;
				start = text.getSelectionStart();
				end = text.getSelectionEnd();
				text.replaceRange("", start, end + 1);
			}
		});
		eDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date nowTime = new Date();
				SimpleDateFormat times = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
				text.insert(times.format(nowTime), text.getCaretPosition());
			}
		});
		eGoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String row = JOptionPane.showInputDialog(jf, "请输入要转到的行!");
					if ((row != null) && !row.equals("")) {
						int position = text.getLineStartOffset(Integer.parseInt(row) - 1);
						text.setCaretPosition(position);
					} else
						JOptionPane.showMessageDialog(jf, "必须输入要转到的行！");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(jf, "行数不存在！");
				}
			}
		});
		eFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findStr = JOptionPane.showInputDialog(jf, "请输入要找的字符串!");
				if ((findStr != null) && !findStr.equals("")) {
					int position = text.getText().indexOf(findStr);
					if (position != -1) {
						text.setSelectionStart(position);
						text.setSelectionEnd(position + findStr.length());
					} else
						JOptionPane.showMessageDialog(jf, "没有你要查找的字符串！");
				} else
					JOptionPane.showMessageDialog(jf, "必须输入待查找的字符串！");
			}
		});
		eFindNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((findStr != null) && !findStr.equals("")) {
					int position = text.getText().indexOf(findStr, text.getSelectionStart() + 1);
					if (position != -1) {
						text.setSelectionStart(position);
						text.setSelectionEnd(position + findStr.length());
					} else
						JOptionPane.showMessageDialog(jf, "不存在要找的字符串！");
				} else
					JOptionPane.showMessageDialog(jf, "必须要输入字符串！");
			}
		});
		eReplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String findStr = null;
				String replaceStr;
				String str;
				int position;
				findStr = JOptionPane.showInputDialog(jf, "请输入要替换的字符串!");
				if ((findStr != null) && !findStr.equals("")) {
					position = text.getText().indexOf(findStr);
					if (position != -1) {
						text.setSelectionStart(position);
						text.setSelectionEnd(position + findStr.length());
						replaceStr = JOptionPane.showInputDialog(jf, "请输入替换的字符串!");
						if (replaceStr != null) {
							str = text.getText().replace(findStr, replaceStr);
							text.setText(str);
						}
					} else
						JOptionPane.showMessageDialog(jf, "没有你要替换的字符串");
				} else
					JOptionPane.showMessageDialog(jf, "没有你想替换的的字符！");
			}
		});

		mEdit.add(eUndo);
		mEdit.add(eRedo);
		mEdit.addSeparator();
		mEdit.add(eCut);
		mEdit.add(eCopy);
		mEdit.add(ePaste);
		mEdit.add(eDelete);
		mEdit.addSeparator();
		mEdit.add(eFind);
		mEdit.add(eFindNext);
		mEdit.add(eReplace);
		mEdit.add(eGoto);
		mEdit.addSeparator();
		mEdit.add(eAll);
		mEdit.add(eDate);
		JCheckBoxMenuItem oWrap = new JCheckBoxMenuItem("自动换行(W)    ");
		JCheckBoxMenuItem oState = new JCheckBoxMenuItem("状态栏(S)");
		JMenuItem oFont = new JMenuItem("字体(F)...", KeyEvent.VK_F);
		JMenuItem oColor = new JMenuItem("颜色(C)...", KeyEvent.VK_C);
		JMenuItem oAbout = new JMenuItem("关于(A)", KeyEvent.VK_A);
		oFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				font();
			}
		});
		oColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color();
			}
		});
		oAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
		oWrap.addActionListener(new ActionListener() {// 自动换行
			public void actionPerformed(ActionEvent e) {
				if (oWrap.isSelected() == true)
					text.setLineWrap(true);
				else
					text.setLineWrap(false);
			}
		});
		oState.addActionListener(new ActionListener() {// 状态栏
			public void actionPerformed(ActionEvent e) {
				if (oState.isSelected() == true) {
					showRowCol.setFont(new Font("宋体", Font.PLAIN, 22));
					showRowCol.setVisible(true);
				} else
					showRowCol.setVisible(false);
			}
		});
		oWrap.setMnemonic(KeyEvent.VK_W);
		oState.setMnemonic(KeyEvent.VK_S);
		oWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.ALT_MASK));
		oState.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));
		oFont.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_MASK));
		oColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_MASK));
		oAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_MASK));
		mOther.add(oWrap);
		mOther.add(oState);
		mOther.addSeparator();
		mOther.add(oFont);
		mOther.add(oColor);
		mOther.addSeparator();
		mOther.add(oAbout);
		jmb.add(mFile);
		jmb.add(mEdit);
		jmb.add(mOther);
		JPopupMenu jpm = new JPopupMenu();
		JMenuItem pUndo = new JMenuItem("撤消(U)", KeyEvent.VK_U);
		JMenuItem pRedo = new JMenuItem("恢复(E)", KeyEvent.VK_E);
		JMenuItem pCut = new JMenuItem("剪切(T)", KeyEvent.VK_T);
		JMenuItem pCopy = new JMenuItem("复制(C)", KeyEvent.VK_C);
		JMenuItem pPaste = new JMenuItem("粘贴(P)", KeyEvent.VK_P);
		JMenuItem pDelete = new JMenuItem("删除(D)", KeyEvent.VK_D);
		JMenuItem pAll = new JMenuItem("全选(A)", KeyEvent.VK_A);
		jpm.add(pUndo);
		jpm.add(pRedo);
		jpm.addSeparator();
		jpm.add(pCut);
		jpm.add(pCopy);
		jpm.add(pPaste);
		jpm.add(pDelete);
		jpm.addSeparator();
		jpm.add(pAll);
		text.addMouseListener(new MouseAdapter() {// 右键弹出菜单
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger())
					jpm.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		text.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// 敲击左键
				if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
					getRowCol(); // 获得当前的行和列位置
				}
			}

			// 松开鼠标
			public void mouseReleased(MouseEvent e) {
				// 敲击左键
				if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
					getRowCol(); // 获得当前的行和列位置
				}
			}
		});
		text.addKeyListener(new KeyAdapter() {
			// 按下某键
			public void keyPressed(KeyEvent e) {
				getRowCol(); // 获得当前的行和列位置
			}

			// 释放某键
			public void keyReleased(KeyEvent e) {
				getRowCol(); // 获得当前的行和列位置
			}
		});
		jf.add(jmb, "North");
		jf.add(showRowCol, "South");
		showRowCol.setVisible(false);
		jf.add(jsp);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}

public class MyNotepad {
	public static void main(String s[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println(e);
		}
		new Text();
	}
}
