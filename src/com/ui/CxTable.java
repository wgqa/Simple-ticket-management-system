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
import com.dao.OrderDao;
import com.dao.UserDao;
import com.entity.Hb;
import com.entity.User;
import com.entity.User;

public class CxTable extends JFrame {

	// �ű��������
	private JPanel jpn = new JPanel();
	// ���û��������룬��½��ȡ����ť������
	private JPanel jpa = new JPanel();

	private JButton jba = new JButton("��ѯ�۳�Ʊ�����");
	private JButton jbb = new JButton("��ѯ��������");
	private JButton jbc = new JButton("��ѯ����ʣ��Ʊ��");
	private JButton jbd = new JButton("��ѯƱ��");
	private JButton jbe = new JButton("��ѯ��˾��Ʊ������");
	private JButton jbf = new JButton("��ѯ��˾ĳ���۳����");
	private JButton jbg = new JButton("��ѯ�����۳����");

	// ������
	private JPanel jpz = new JPanel();

	public CxTable() {
		this.setTitle("��ѯ����");
		this.setSize(350, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		this.setResizable(false);// ���ô����޷��ı��С
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(30, 0, 0, 0);
		gbl.setConstraints(jba, gbc);// ��Լ��������Լ��jla(����)
		jpa.add(jba);// ��jla���ӵ�������

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbl.setConstraints(jbb, gbc);
		jpa.add(jbb);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(jbc, gbc);
		jpa.add(jbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(jbd, gbc);
		jpa.add(jbd);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(jbe, gbc);
		jpa.add(jbe);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbl.setConstraints(jbf, gbc);
		jpa.add(jbf);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbl.setConstraints(jbg, gbc);
		jpa.add(jbg);

		// ��ѯ�۳�Ʊ�����
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String address=JOptionPane.showInputDialog("���۵㣺");
				String month=JOptionPane.showInputDialog("�·ݣ�");
				float sum=new OrderDao().select(address, month);
				JOptionPane.showMessageDialog(null, "���۶��Ϊ��"+sum);
			}
		});

		// ��ѯ��������
		jbb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gsmc=JOptionPane.showInputDialog("��˾���ƣ�");
				int hbs=new HbDao().getHbs(gsmc);
				JOptionPane.showMessageDialog(null, "��������Ϊ��"+hbs);
			}
		});
		
		// ��ѯ����ʣ��Ʊ��
		jbc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gsmc=JOptionPane.showInputDialog("��˾���ƣ�");
				String sj=JOptionPane.showInputDialog("ʱ�䣺");
				int ps=new FlyDao().getPs(gsmc, sj);
				JOptionPane.showMessageDialog(null, "ʣ��Ʊ����Ϊ��"+ps);
			}
		});
		
		// ��ѯƱ��
		jbd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gsmc=JOptionPane.showInputDialog("��˾���ƣ�");
				String sj=JOptionPane.showInputDialog("ʱ�䣺");
				String wlx=JOptionPane.showInputDialog("λ�����ͣ�");
				float pj=new FlyDao().getPs(gsmc, sj, wlx);
				JOptionPane.showMessageDialog(null, "Ʊ��Ϊ��"+pj);
			}
		});
		
		// ��ѯ��˾��Ʊ������
		jbe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gsmc=JOptionPane.showInputDialog("��˾���ƣ�");
				int spds=new FlyDao().getSpds(gsmc);
				JOptionPane.showMessageDialog(null, "��Ʊ������Ϊ��"+spds);
			}
		});
		
		// ��ѯ��˾ĳ���۳����
		jbf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String hbname=JOptionPane.showInputDialog("�������֣�");
				String month=JOptionPane.showInputDialog("ʱ�䣺");
				float sce=new FlyDao().getSce(hbname, month);
				JOptionPane.showMessageDialog(null, "�۳��ܽ��Ϊ��"+sce);
			}
		});
		
		// ��ѯĳ���ߵ����۶��
		jbg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String hbname=JOptionPane.showInputDialog("�������֣�");
				float sce=new FlyDao().getSce(hbname);
				JOptionPane.showMessageDialog(null, "�۳��ܽ��Ϊ��"+sce);
			}
		});

		// ��С����������������
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
