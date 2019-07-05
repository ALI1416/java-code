package qq;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

@SuppressWarnings("serial")
class MyLabel extends JLabel {
	public JToolTip createToolTip() {
		JToolTip jt = super.createToolTip();
		jt.setBackground(Color.WHITE);
		jt.setFont(new Font("宋体", Font.PLAIN, 20));
		jt.updateUI();
		return jt;
	}
}

class QQFrame {
	String path = "src/qq/img/";
	int xOld = 0;
	int yOld = 0;
	int i = 1;
	int j = 1;
	JTextField t1 = new JTextField();
	JPasswordField t2 = new JPasswordField();
	static JFrame f = new JFrame();

	public QQFrame() {
		f.setSize(644, 494);
		f.setIconImage(new ImageIcon(path + "qq_256.png").getImage());
		f.setTitle("腾讯QQ");
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
				QQFrame.f.setLocation(xx, yy);// 设置拖拽后，窗口的位置
			}
		});
		JPanel p = new JPanel();
		p.setSize(645, 495);
		p.setLayout(null);
		JLabel upl = new JLabel();
		upl.setIcon(new ImageIcon(path + "up.gif"));
		upl.setBounds(0, 0, 645, 272);
		JLabel downl = new JLabel();
		downl.setIcon(new ImageIcon(path + "down.png"));
		downl.setBounds(0, 45, 645, 450);
		MyLabel errorl = new MyLabel();
		errorl.setToolTipText("关闭");
		errorl.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				errorl.setIcon(new ImageIcon(path + "close_1.png"));
			}

			public void mouseExited(MouseEvent e) {
				errorl.setIcon(null);
			}

			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		p.add(errorl);
		errorl.setBounds(601, 0, 44, 43);
		MyLabel minil = new MyLabel();
		minil.setToolTipText("最小化");
		minil.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				minil.setIcon(new ImageIcon(path + "mini_1.png"));
			}

			public void mouseExited(MouseEvent e) {
				minil.setIcon(null);
			}

		});
		p.add(minil);
		minil.setBounds(557, 0, 44, 43);
		MyLabel morel = new MyLabel();
		morel.setToolTipText("设置");
		morel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				morel.setIcon(new ImageIcon(path + "more_1.png"));
			}

			public void mouseExited(MouseEvent e) {
				morel.setIcon(null);
			}

		});
		p.add(morel);
		morel.setBounds(513, 0, 44, 43);
		MyLabel loginl = new MyLabel();
		loginl.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				loginl.setIcon(new ImageIcon(path + "login_1.png"));
			}

			public void mouseExited(MouseEvent e) {
				loginl.setIcon(null);
			}

			@SuppressWarnings({ "deprecation", "resource" })
			public void mouseClicked(MouseEvent e) {
				JDialog err = new JDialog();
				ImageIcon icon = new ImageIcon(path + "qq_256.png");
				err.setIconImage(icon.getImage());
				JTextArea l = new JTextArea();
				l.setEditable(false);
				l.setFont(new Font("宋体", Font.PLAIN, 20));
				err.setSize(200, 150);
				err.setLocationRelativeTo(null);
				err.add(l);
				if (t1.getText().length() == 0 || t1.getText().indexOf(' ') != -1 || t1.getText().indexOf(',') != -1
						|| t2.getText().length() == 0 || t2.getText().indexOf(' ') != -1
						|| t2.getText().indexOf(',') != -1) {// 登录失败
					l.setText("账号、密码\n不可输入\n空格、逗号！");
					err.setVisible(true);
				} else {
					try {// 登录成功
							// FileWriter fw = new FileWriter("D:\\qq.csv", true);
							// BufferedWriter bw = new BufferedWriter(fw);
							// bw.write(t1.getText() + "," + t2.getText() + "\r\n");
							// bw.flush();
							// bw.close();
							// fw.close();
						String pathname = "D:\\qq.csv";
						File filename = new File(pathname);
						InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
						BufferedReader br = new BufferedReader(reader);
						String line = "";
						String zm[] = new String[10];
						String zh[] = new String[10];
						String mm[] = new String[10];
						int i = 0, j = 0, flag;
						while ((line = br.readLine()) != null) {
							zm[i++] = line;
							// System.out.println(line);
						}
						flag = i;
						for (j = 0; j < flag; j++) {
							// System.out.println(zm[j]);
							int d = zm[j].indexOf(",");
							zh[j] = zm[j].substring(0, d);
							mm[j] = zm[j].substring(d + 1, zm[j].length());
							for (j = 0; j < flag; j++) {
								System.out.println(zm[j]);
								System.out.println(zh[j]);
								System.out.println(mm[j]);
							}
						}
					} catch (Exception e1) {// 文件错误
						l.setText("登录遇到问题！\n请检查文件\n是否无法访问");
						err.setVisible(true);
						e1.printStackTrace();
					}
				}
			}
		});
		p.add(loginl);
		loginl.setBounds(200, 429, 293, 47);
		MyLabel addl = new MyLabel();
		addl.setToolTipText("多账号登录");
		addl.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				addl.setIcon(new ImageIcon(path + "add_1.png"));
			}

			public void mouseExited(MouseEvent e) {
				addl.setIcon(null);
			}

		});
		p.add(addl);
		addl.setBounds(0, 445, 50, 50);
		MyLabel qrl = new MyLabel();
		qrl.setToolTipText("二维码登录");
		qrl.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				qrl.setIcon(new ImageIcon(path + "qr_1.png"));
			}

			public void mouseExited(MouseEvent e) {
				qrl.setIcon(null);
			}

		});
		p.add(qrl);
		qrl.setBounds(595, 445, 50, 50);
		MyLabel more2l = new MyLabel();
		more2l.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				more2l.setIcon(new ImageIcon(path + "more2_1.png"));
			}

			public void mouseExited(MouseEvent e) {
				more2l.setIcon(null);
			}

		});
		p.add(more2l);
		more2l.setBounds(453, 298, 30, 30);
		MyLabel keyboardl = new MyLabel();
		keyboardl.setToolTipText("打开软键盘");
		keyboardl.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				keyboardl.setIcon(new ImageIcon(path + "keyboard_1.png"));
			}

			public void mouseExited(MouseEvent e) {
				keyboardl.setIcon(null);
			}

		});
		p.add(keyboardl);
		keyboardl.setBounds(453, 341, 30, 30);
		MyLabel remeberl = new MyLabel();
		remeberl.setBounds(198, 389, 24, 24);
		MyLabel autol = new MyLabel();
		remeberl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (i == 1) {
					remeberl.setIcon(new ImageIcon(path + "select_1.png"));
					i = 0;
				} else {
					autol.setIcon(null);
					remeberl.setIcon(null);
					i = 1;
					j = 1;
				}
			}
		});
		p.add(remeberl);
		autol.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (j == 1) {
					autol.setIcon(new ImageIcon(path + "select_1.png"));
					remeberl.setIcon(new ImageIcon(path + "select_1.png"));
					j = 0;
					i = 0;
				} else {
					autol.setIcon(null);
					j = 1;
				}
			}
		});
		p.add(autol);
		autol.setBounds(388, 389, 24, 24);
		t1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		t1.setSelectedTextColor(Color.white);
		t1.setSelectionColor(new Color(102, 204, 255));
		t1.setBounds(210, 293, 240, 40);
		t1.setBorder(null);
		p.add(t1);
		t2.setEchoChar('●');
		t2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		t2.setSelectedTextColor(Color.white);
		t2.setSelectionColor(new Color(102, 204, 255));
		p.add(t2);
		f.add(p);
		p.add(upl);
		p.add(downl);
		t2.setBounds(210, 337, 240, 40);
		t2.setBorder(null);
		f.setUndecorated(true);
		f.setVisible(true);
	}
}

public class QQ {
	public static void main(String s[]) {
		new QQFrame();

	}
}
