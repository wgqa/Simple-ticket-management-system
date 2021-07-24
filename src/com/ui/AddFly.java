package com.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.dao.FlyDao;
import com.dao.HbDao;
import com.entity.Fly;
import com.entity.Hb;

public class AddFly extends JFrame {

	// 放标题的容器
	private JPanel jpn = new JPanel();
	private JPanel jpa = new JPanel();

	private JLabel jla = new JLabel("价格：");
	private JTextField jcba = new JTextField(12);
	private JLabel jlb = new JLabel("飞机型号：");
	private JTextField jcbb = new JTextField(12);
	private JLabel jlc = new JLabel("座位类型：");
	private JTextField jtfc = new JTextField(12);
	private JLabel jld = new JLabel("航班：");
	private JComboBox jtfd = new JComboBox();
	private JLabel jle = new JLabel("时间：");
	private JTextField jtfe = new JTextField(12);
	private JLabel jlf = new JLabel("售票点：");
	private JTextField jtff = new JTextField(12);
	private JLabel jlg = new JLabel("具体位置：");
	private JTextField jtfg = new JTextField(12);
	
	public void jiazai() {
		List<Hb> mys = new HbDao().getAll("");
		for (int i = 0; i < mys.size(); i++) {
			jtfd.addItem(mys.get(i).getHbid() + "-" + mys.get(i).getHbname());
		}
	}

	private JButton jba = new JButton("增加");

	// 总容器
	private JPanel jpz = new JPanel();

	public AddFly(FlyTable t) {
		this.setTitle("增加界面");
		this.setSize(350, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		this.setResizable(false);// 设置窗体无法改变大小
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);
		
		// 价格以及其文本框的位置
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbl.setConstraints(jla, gbc);// 让约束对象来约束jla(坐标)
		jpa.add(jla);// 将jla增加到容器中

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(jcba, gbc);
		jpa.add(jcba);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbl.setConstraints(jlb, gbc);
		jpa.add(jlb);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbl.setConstraints(jcbb, gbc);
		jpa.add(jcbb);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(jlc, gbc);
		jpa.add(jlc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbl.setConstraints(jtfc, gbc);
		jpa.add(jtfc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(jld, gbc);
		jpa.add(jld);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(jtfd, gbc);
		jpa.add(jtfd);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(jle, gbc);
		jpa.add(jle);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbl.setConstraints(jtfe, gbc);
		jpa.add(jtfe);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbl.setConstraints(jlf, gbc);
		jpa.add(jlf);

		gbc.gridx = 1;
		gbc.gridy = 5;
		gbl.setConstraints(jtff, gbc);
		jpa.add(jtff);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbl.setConstraints(jlg, gbc);
		jpa.add(jlg);

		gbc.gridx = 1;
		gbc.gridy = 6;
		gbl.setConstraints(jtfg, gbc);
		jpa.add(jtfg);
		
		// 按钮位置
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbl.setConstraints(jba, gbc);
		jpa.add(jba);
		
		jiazai();

		// 增加方法
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取对应文本框的值
				String a = jcba.getText();
				String b = jcbb.getText();
				String c = jtfc.getText();
				String d = jtfd.getSelectedItem()+"";
				String[] ds = d.split("-");
				String ea = jtfe.getText();
				String fa = jtff.getText();
				String g = jtfg.getText();
				Fly f=new Fly(0, Float.parseFloat(a), b, c, Integer.parseInt(ds[0]),ea,fa,g);
				int i = new FlyDao().add(f);
				if (i > 0) {
					jtfc.setText("");
					jcba.setText("");
					jcbb.setText("");
					jtfe.setText("");
					jtff.setText("");
					jtfg.setText("");
					JOptionPane.showMessageDialog(null, "增加成功！");
					t.myShow("");
				} else {
					JOptionPane.showMessageDialog(null, "增加失败！");
				}
			}
		});

		// 将小容器加入总容器中
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
