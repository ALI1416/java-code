package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class Main {
	String path = "src/game/img/";// 图片路径
	int i, j, a, b, c, d;
	int maxFigure = 2;// 最大数字
	int countFigure = 0;// 数字个数
	int score = 0;// 分数
	int v[][] = { // 初始化
			{ 0, 0, 0, 0 }, // 第一行
			{ 0, 0, 0, 0 }, // 第二行
			{ 0, 0, 0, 0 }, // 第三行
			{ 0, 0, 0, 0 } };// 第四行

	int random(int x, int y) {// 产生随机数x-y
		return (int) (Math.random() * (y - x + 1)) + x;
	}

	void init() {// 初始化
		for (int i = 0; i < random(2, 4); i++)// 产生2-4个2
			showFigure(2);
	}

	void showFigure(int m) {// 产生数字m
		while (countFigure != 16) {
			int i = random(0, 3);
			int j = random(0, 3);
			if (v[i][j] == 0) {
				v[i][j] = m;
				break;
			}
		}
	}

	void countFigure() {
		int n = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (v[i][j] != 0)
					n++;
			}
		}
		countFigure = n;
	}

	int addFigure() {
		int n = random(0, 4);
		switch (maxFigure) {
		case 2:
		case 4:
		case 8:
		case 16:
		case 32: {
			return 2;
		}
		case 64:
		case 128:
		case 256:
		case 512:
		case 1024: {
			if (n >= 1)
				return 2;
			if (n < 1)
				return 4;
		}
		}
		return 0;
	}

	int over() {// 结束返回1，继续返回0
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((v[i][j] == v[i + 1][j]) || (v[i][j] == v[i][j + 1]))
					return 0;
			}
			if ((v[i][3] == v[i + 1][3]) || (v[3][i] == v[3][i + 1]))
				return 0;
		}
		return 1;
	}

	void moveUp() {// 向上移动
		for (j = 0; j < 4; j++) {// 共4列
			if (v[0][j] == 0 && v[1][j] == 0 && v[2][j] == 0 && v[3][j] == 0) { // 全为0
				continue;// 继续下一个循环
			} else {
				for (a = 0; a < 3; a++) {// 最多操作3次
					// 检查是否存在0
					for (c = 0; c < 3; c++) {// 有4行，前3行存在0再前移
						if (v[c][j] == 0) {// 存在有0的，从最上方开始检索
							for (b = c; b < 3; b++) {// 后面的向前移动
								v[b][j] = v[b + 1][j];// 后面覆盖前面
							}
							v[3][j] = 0;// 末尾赋值为0
						}
					}
					// 检查是否存在数值相邻且相等
					for (c = 0; c < 3; c++) {// 有3种相邻的情况
						if (v[c][j] == v[c + 1][j]) { // 有相邻相同情况
							v[c][j] *= 2;// 首值翻倍
							score += v[c][j];// 分数增加
							if (v[c][j] > maxFigure) {// 判断翻倍后是否大于最大数字
								maxFigure = v[c][j];// 最大数字被覆盖
								for (d = c + 1; d < 3; d++) {// 后面的向前移动
									v[d][j] = v[d + 1][j];// 后面覆盖前面
								}
								v[3][j] = 0;// 末尾赋值为0
							}
						}
					}
				}
			}
		}
	}

	void moveDown() {// 向下移动
		for (j = 0; j < 4; j++) {
			if (v[0][j] == 0 && v[1][j] == 0 && v[2][j] == 0 && v[3][j] == 0)
				continue;
			else {
				for (a = 0; a < 3; a++) {
					for (c = 0; c < 3; c++) {
						if (v[3 - c][j] == 0) {
							for (b = 3 - c; b > 0; b--) {
								v[b][j] = v[b - 1][j];
							}
							v[0][j] = 0;
						}
					}
					for (c = 3; c > 0; c--) {
						if (v[c][j] == v[c - 1][j]) {
							v[c][j] *= 2;
							score += v[c][j];
							if (v[c][j] > maxFigure)
								maxFigure = v[c][j];
							for (d = c - 1; d > 0; d--) {
								v[d][j] = v[d - 1][j];
							}
							v[0][j] = 0;
						}
					}
				}
			}
		}
	}

	void moveLeft() {
		for (i = 0; i < 4; i++) {
			if (v[i][0] == 0 && v[i][1] == 0 && v[i][2] == 0 && v[i][3] == 0)
				continue;
			else {
				for (a = 0; a < 3; a++) {
					for (c = 0; c < 3; c++) {
						if (v[i][c] == 0) {
							for (b = c; b < 3; b++) {
								v[i][b] = v[i][b + 1];
							}
							v[i][3] = 0;
						}
					}
					for (c = 0; c < 3; c++) {
						if (v[i][c] == v[i][c + 1]) {
							v[i][c] *= 2;
							score += v[i][c];
							if (v[i][c] > maxFigure)
								maxFigure = v[i][c];
							for (d = c + 1; d < 3; d++) {
								v[i][d] = v[i][d + 1];
							}
							v[i][3] = 0;
						}
					}
				}
			}
		}
	}

	void moveRight() {// 向右移动
		for (i = 0; i < 4; i++) {
			if (v[i][0] == 0 && v[i][1] == 0 && v[i][2] == 0 && v[i][3] == 0)
				continue;
			else {
				for (a = 0; a < 3; a++) {
					for (c = 0; c < 3; c++) {
						if (v[i][3 - c] == 0) {
							for (b = 3 - c; b > 0; b--) {
								v[i][b] = v[i][b - 1];
							}
							v[i][0] = 0;
						}
					}
					for (c = 3; c > 0; c--) {
						if (v[i][c] == v[i][c - 1]) {
							v[i][c] *= 2;
							score += v[i][c];
							if (v[i][c] > maxFigure)
								maxFigure = v[i][c];
							for (d = c - 1; d > 0; d--) {
								v[i][d] = v[i][d - 1];
							}
							v[i][0] = 0;
						}
					}
				}
			}
		}
	}

	void pr() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.printf("%-4d", v[i][j]);
			}
			System.out.println();
		}
	}

	void frame() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (v[i][j] == 0)
					p[i][j].setIcon(null);
				if (v[i][j] == 2)
					p[i][j].setIcon(new ImageIcon(path + "2.png"));
				if (v[i][j] == 4)
					p[i][j].setIcon(new ImageIcon(path + "4.png"));
				if (v[i][j] == 8)
					p[i][j].setIcon(new ImageIcon(path + "8.png"));
				if (v[i][j] == 16)
					p[i][j].setIcon(new ImageIcon(path + "16.png"));
				if (v[i][j] == 32)
					p[i][j].setIcon(new ImageIcon(path + "32.png"));
				if (v[i][j] == 64)
					p[i][j].setIcon(new ImageIcon(path + "64.png"));
				if (v[i][j] == 128)
					p[i][j].setIcon(new ImageIcon(path + "128.png"));
				if (v[i][j] == 256)
					p[i][j].setIcon(new ImageIcon(path + "256.png"));
				if (v[i][j] == 512)
					p[i][j].setIcon(new ImageIcon(path + "512.png"));
				if (v[i][j] == 1024)
					p[i][j].setIcon(new ImageIcon(path + "1024.png"));
				if (v[i][j] == 2048)
					p[i][j].setIcon(new ImageIcon(path + "2048.png"));
			}
		}
	}

	JLabel[][] p = new JLabel[4][4];
	{
		p[0][0] = new JLabel();
		p[0][1] = new JLabel();
		p[0][2] = new JLabel();
		p[0][3] = new JLabel();
		p[1][0] = new JLabel();
		p[1][1] = new JLabel();
		p[1][2] = new JLabel();
		p[1][3] = new JLabel();
		p[2][0] = new JLabel();
		p[2][1] = new JLabel();
		p[2][2] = new JLabel();
		p[2][3] = new JLabel();
		p[3][0] = new JLabel();
		p[3][1] = new JLabel();
		p[3][2] = new JLabel();
		p[3][3] = new JLabel();
	}

	Main() {
		JFrame f = new JFrame("2048");
		f.setSize(656, 840);// 650*800
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		JLabel top = new JLabel();
		JLabel frame = new JLabel();
		f.add(top, "North");
		f.add(frame);
		top.setLayout(null);
		top.setIcon(new ImageIcon(path + "top.png"));// 650*150
		JLabel scoreShow = new JLabel();
		top.add(scoreShow);
		scoreShow.setBounds(350, 55, 270, 65);
		scoreShow.setHorizontalAlignment(SwingConstants.CENTER);// 居中
		scoreShow.setFont(new Font("等线", Font.BOLD, 80));
		scoreShow.setForeground(Color.WHITE);
		scoreShow.setText("0");
		frame.setIcon(new ImageIcon(path + "frame.png"));// 650*650
		frame.setLayout(null);
		p[0][0].setBounds(10, 10, 150, 150);
		p[0][1].setBounds(170, 10, 150, 150);
		p[0][2].setBounds(330, 10, 150, 150);
		p[0][3].setBounds(490, 10, 150, 150);
		p[1][0].setBounds(10, 170, 150, 150);
		p[1][1].setBounds(170, 170, 150, 150);
		p[1][2].setBounds(330, 170, 150, 150);
		p[1][3].setBounds(490, 170, 150, 150);
		p[2][0].setBounds(10, 330, 150, 150);
		p[2][1].setBounds(170, 330, 150, 150);
		p[2][2].setBounds(330, 330, 150, 150);
		p[2][3].setBounds(490, 330, 150, 150);
		p[3][0].setBounds(10, 490, 150, 150);
		p[3][1].setBounds(170, 490, 150, 150);
		p[3][2].setBounds(330, 490, 150, 150);
		p[3][3].setBounds(490, 490, 150, 150);
		frame.add(p[0][0]);
		frame.add(p[0][1]);
		frame.add(p[0][2]);
		frame.add(p[0][3]);
		frame.add(p[1][0]);
		frame.add(p[1][1]);
		frame.add(p[1][2]);
		frame.add(p[1][3]);
		frame.add(p[2][0]);
		frame.add(p[2][1]);
		frame.add(p[2][2]);
		frame.add(p[2][3]);
		frame.add(p[3][0]);
		frame.add(p[3][1]);
		frame.add(p[3][2]);
		frame.add(p[3][3]);
		f.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W
						|| e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A
						|| e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
						moveUp();
					if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
						moveDown();
					if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
						moveLeft();
					if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
						moveRight();
					System.out.println("score:" + score);
					showFigure(addFigure());
					countFigure();
					pr();
					frame();
					scoreShow.setText(Integer.toString(score));
					if (countFigure == 16 && over() == 1) {
						System.out.println("over");
					}
				}
			}
		});
		init();
		pr();
		frame();
		f.setVisible(true);
		f.setDefaultCloseOperation(3);
	}
}

public class Game2048 {
	public static void main(String s[]) {
		new Main();
	}
}
