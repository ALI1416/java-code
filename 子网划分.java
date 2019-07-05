package net;

import java.util.Arrays;

public class Subnetting {
	public static void main(String[] args) {

		/**
		 * 初始化部分
		 */
		int i, j, k, t;
		String str = "1.2.3.0/24/14";// 初始化网络号、子网掩码、需求子网数量

		/**
		 * 切割网络号、子网掩码、子网数量
		 */
		String temp[] = str.split("/");
		if (temp.length != 3) {
			System.out.println("输入不合法！程序终止！");
			return;
		}

		/**
		 * 转换子网掩码、子网数量
		 */
		int subnetMask = 0;// 子网掩码
		int subnetNumber = 0;// 子网数量
		try {
			subnetMask = Integer.parseInt(temp[1]);
			subnetNumber = Integer.parseInt(temp[2]);
			if (subnetMask < 2 || subnetNumber < 2) {
				System.out.println("子网掩码、子网数量至少为2！程序终止！");
				return;
			}
		} catch (Exception e) {
			System.out.println("子网掩码或子网数量格式错误！程序终止！");
			return;
		}

		/**
		 * 切割、转换网络号到十进制
		 */
		String temp2[] = temp[0].split("\\.");
		if (temp2.length != 4) {
			System.out.println("网络号不合法！程序终止！");
			return;
		}

		int ip10[] = new int[4];// 初始化每一段IP地址，十进制
		try {
			for (i = 0; i < 4; i++) {// 转为整形
				ip10[i] = Integer.parseInt(temp2[i]);
				if (ip10[i] < 0 || ip10[i] > 255) {
					System.out.println("网络号不合法！程序终止！");
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("网络号格式错误！程序终止！");
			return;
		}

		/**
		 * 转换网络号到二进制
		 */
		int ip2[] = new int[32];// 初始化每一位IP地址，二进制
		int flag = 0;// 标记是否停止计算网络前缀
		int preNet = 32;// 网络前缀个数(32-从后往前数，第一个不为0的数字)
		k = 31;
		for (i = 3; i >= 0; i--) {// 转为二进制，并保存到数组
			for (j = 0; j < 8; j++) {
				ip2[k] = ip10[i] >> j & 1;// >>运算，右移n位，高位补0
				k--;
				if (flag == 0 && ip2[k + 1] == 0) {// 计算网络前缀
					preNet--;
				} else {
					flag = 1;
				}
			}
		}
		if (subnetMask < preNet) {
			System.out.println("该网络号的子网掩码不合法！程序终止！");
			return;
		}
		if ((1 << (32 - subnetMask)) < subnetNumber) {// 子网数不可超过主机数量
			// <<运算，左移n位，相当于2的n次方
			System.out.println("需求子网数超出最大主机数量！程序终止！");
			return;
		}

		/**
		 * 计算应当划分的子网数量和每个子网的最大主机数量
		 */
		int realSubnetNumber = subnetNumber - 1;// 假设输入的子网数量为2的幂次方
		// 此处-1是避免是2的幂的情况，1是例外，但是上面已经排除
		int pow = 1;// 应该为2的几次幂
		while ((realSubnetNumber = realSubnetNumber >> 1) != 0) {// 相当于a=a/2,a/2!=0
			pow++;
		}
		realSubnetNumber = 1 << pow;
		int maxHost = 1 << (32 - preNet - pow);// 每个子网的最大主机数量

		/**
		 * 分配ip
		 */
		int preIp[][] = new int[realSubnetNumber][4];// 第一个ip
		int sufIp[][] = new int[realSubnetNumber][4];// 最后一个ip
		for (i = 0; i < realSubnetNumber; i++) {
			/** 设置子网号 */
			for (j = 0; j < pow; j++) {// 子网号，递增
				ip2[subnetMask + pow - j - 1] = i >> j & 1;
			}
			/** 设置第一个ip */
			Arrays.fill(ip2, subnetMask + pow, 32, 0);// 填充0
			for (j = 0; j < 4; j++) {// 计算每一段IP地址，十进制
				t = 0;// 计算数组对应的值
				for (k = 0; k < 8; k++) {
					t += ip2[(j << 3) + k] << (7 - k);
				}
				preIp[i][j] = t;// 第一个ip
			}
			/** 设置最后一个ip */
			Arrays.fill(ip2, subnetMask + pow, 32, 1);// 填充1
			for (j = 0; j < 4; j++) {// 计算每一段IP地址，十进制
				t = 0;// 计算数组对应的值
				for (k = 0; k < 8; k++) {
					t += ip2[(j << 3) + k] << (7 - k);
				}
				sufIp[i][j] = t;// 第一个ip
			}
		}

		/**
		 * 输出
		 */
		System.out.println("根IP地址为：" + temp[0] + " 子网掩码位数为：" + subnetMask);
		System.out.println("需求子网数量为：" + subnetNumber + " 实际划分的子网数量为：" + realSubnetNumber);
		System.out.println("每个子网最大的主机数量为：" + maxHost);
		for (i = 0; i < realSubnetNumber; i++) {
			System.out.print("第 " + i + " 个子网可用地址：");
			System.out.print(preIp[i][0] + "." + preIp[i][1] + "." + preIp[i][2] + "." + (preIp[i][3] + 1) + "\t ~ ");
			System.out.print(sufIp[i][0] + "." + sufIp[i][1] + "." + sufIp[i][2] + "." + (sufIp[i][3] - 1));
			System.out.print("\t网络地址：" + preIp[i][0] + "." + preIp[i][1] + "." + preIp[i][2] + "." + preIp[i][3]);
			System.out.println("\t广播地址：" + sufIp[i][0] + "." + sufIp[i][1] + "." + sufIp[i][2] + "." + sufIp[i][3]);

		}
	}
}
