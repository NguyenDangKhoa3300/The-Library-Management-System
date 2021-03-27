package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PhieuDatDAO;
import dao.PhieuThanhLyDAO;

public class GD_SuaPhieuThanhLy extends JFrame {
	private PhieuThanhLyDAO phieuThanhLyDAO = new PhieuThanhLyDAO();
	private JPanel contentPane;
	
	private String tenNV;
	private String ngayTL;
	private String thangTL;
	private String namTL;
	
	private String maptlFromMain;
	private String tenNVFromMain;
	private String ngayTLFromMain;
	private String thangTLFromMain;
	private String namTLFromMain;
	
	private JComboBox comboTenNV;
	private JComboBox comboNgay;
	private JComboBox comboThang;
	private JComboBox comboNam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_SuaPhieuThanhLy frame = new GD_SuaPhieuThanhLy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GD_SuaPhieuThanhLy(String maptlFromMain,String tenNVFromMain,String ngayTLFromMain,String thangTLFromMain,String namTLFromMain) {
		this.maptlFromMain = maptlFromMain;
		this.tenNVFromMain = tenNVFromMain;
		this.ngayTLFromMain = ngayTLFromMain;
		this.thangTLFromMain = thangTLFromMain;
		this.namTLFromMain = namTLFromMain;
		initialize();
	}
	public void setComBoBox() {
		int ngays = Integer.parseInt(ngayTLFromMain);
		for (int i=0; i<comboNgay.getItemCount(); i++) {
			String ngayfor = comboNgay.getItemAt(i)+"";
		      if (ngayfor.equalsIgnoreCase(ngays+"")) {
		    	  comboNgay.setSelectedIndex(i);
		        break;
		      }
		    }
		
		
		int thangs = Integer.parseInt(thangTLFromMain);
		for (int i=0; i<comboThang.getItemCount(); i++) {
			 String thangfor = comboThang.getItemAt(i)+"";
		      if (thangfor.equalsIgnoreCase(thangs+"")) {
		    	  comboThang.setSelectedIndex(i);
		        break;
		      }
		    }
		
		int nams = Integer.parseInt(namTLFromMain);
		for (int i=0; i<comboNam.getItemCount(); i++) {
			
			String namfor = comboNam.getItemAt(i)+"";
		      if (namfor.equalsIgnoreCase(nams+"")) {
		    	  comboNam.setSelectedIndex(i);
		        break;
		      }
		    }
		
		for (int i=0; i<comboTenNV.getItemCount(); i++) {
		      if (comboTenNV.getItemAt(i).equals(this.tenNVFromMain)) {
		    	  comboTenNV.setSelectedIndex(i);
		        break;
		      }
		    }
		
	}
	
	public GD_SuaPhieuThanhLy() {
		initialize();
	}
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 614, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboTenNV = new JComboBox();
		comboTenNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenNV =  (String) comboTenNV.getItemAt(comboTenNV.getSelectedIndex());
			}
		});
		comboTenNV.setBounds(213, 106, 116, 21);
		contentPane.add(comboTenNV);
		ArrayList<String> dsNV = phieuThanhLyDAO.JComBoBoxNV();
		for(int i = 0 ; i < dsNV.size(); i++) {
			comboTenNV.addItem(dsNV.get(i));
		}
		comboNgay = new JComboBox();
		comboNgay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ngayTL =  comboNgay.getItemAt(comboNgay.getSelectedIndex()).toString();
			}
		});
		comboNgay.setBounds(106, 174, 50, 21);
		contentPane.add(comboNgay);
		for(int i = 1 ; i <= 31 ; i++) {
			comboNgay.addItem(i);
		}
		
		comboThang = new JComboBox();
		comboThang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thangTL =  comboThang.getItemAt(comboThang.getSelectedIndex()).toString();
			}
		});
		comboThang.setBounds(245, 174, 50, 21);
		contentPane.add(comboThang);
		for(int i = 1 ; i <= 12 ; i++) {
			comboThang.addItem(i);
		}
		comboNam = new JComboBox();
		comboNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namTL =  comboNam.getItemAt(comboNam.getSelectedIndex()).toString();
			}
		});
		comboNam.setBounds(384, 174, 76, 21);
		contentPane.add(comboNam);
		for(int i = 2019 ; i <= 2024 ; i++) {
			comboNam.addItem(i);
		}
		JButton btnLu = new JButton("Lưu");
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeDat = namTL + "-" + thangTL + "-" + ngayTL;
				new PhieuThanhLyDAO().suaPhieuThanhLy(tenNV, timeDat,maptlFromMain);
				GD_MainPage mainframe = new GD_MainPage().getInstanceOfMainPage();
				mainframe.dulieubangPhieuThanhLy();
			}
		});
		btnLu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLu.setBounds(243, 269, 97, 31);
		contentPane.add(btnLu);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		setComBoBox();
	}
}
