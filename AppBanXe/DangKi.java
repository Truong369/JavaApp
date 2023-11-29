package BaoCao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DangKi extends JFrame {

	private JPanel contentPane;
	private JTextField txttennd;
	private JTextField txttaikhoan;
	private JPasswordField txtmatkhau;
	private JPasswordField txtnhaplaimatkhau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKi frame = new DangKi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public void Connect() {
		String url = "jdbc:mysql://localhost:3306/qlbanxemay";
		String userName = "root";
		String password = "";
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			con = DriverManager.getConnection(url,userName,password); 
			System.out.println("Kết nối thành công"+con);
			
		}
		catch(Exception e){ 
			System.out.println("Kết nối thất bại");
		}  
	}


	/**
	 * Create the frame.
	 */
	public DangKi() {
		Connect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 545);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        int screenWidth = screenSize.width;
	        int screenHeight = screenSize.height;

	        // Tính toán vị trí xuất hiện của frame ở giữa màn hình
	        int frameWidth = this.getWidth();
	        int frameHeight = this.getHeight();
	        int x = (screenWidth - frameWidth) / 2;
	        int y = (screenHeight - frameHeight) / 2;

	        // Thiết lập vị trí xuất hiện của frame
	        this.setLocation(x, y);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(10, 106, 496, 326);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ và tên");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 32, 160, 39);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tài khoản");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 94, 95, 39);
		panel.add(lblNewLabel_1_1);
txttennd = new JTextField();
		txttennd.setColumns(10);
		txttennd.setBounds(205, 39, 249, 29);
		panel.add(txttennd);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(10, 157, 95, 39);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1_2.setBounds(10, 226, 160, 39);
		panel.add(lblNewLabel_1_1_2);
		
		txttaikhoan = new JTextField();
		txttaikhoan.setColumns(10);
		txttaikhoan.setBounds(205, 101, 249, 29);
		panel.add(txttaikhoan);
		
		txtmatkhau = new JPasswordField();
		txtmatkhau.setBounds(205, 164, 249, 29);
		panel.add(txtmatkhau);
		
		txtnhaplaimatkhau = new JPasswordField();
		txtnhaplaimatkhau.setBounds(205, 233, 249, 29);
		panel.add(txtnhaplaimatkhau);
		
		JButton btndangky = new JButton("Đăng ký");
		btndangky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangKy();				
			}
		});
		btndangky.setForeground(Color.BLUE);
		btndangky.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btndangky.setBackground(SystemColor.info);
		btndangky.setBounds(110, 442, 132, 56);
		contentPane.add(btndangky);
		
		JButton btndangnhap = new JButton("Đăng nhập");
		btndangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangNhap();
			}
		});
		btndangnhap.setForeground(new Color(255, 0, 0));
		btndangnhap.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btndangnhap.setBackground(SystemColor.info);
		btndangnhap.setBounds(276, 442, 132, 56);
		contentPane.add(btndangnhap);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.info);
		panel_2.setBounds(10, 10, 496, 88);
		contentPane.add(panel_2);
		
		JLabel lblngK = new JLabel("ĐĂNG KÝ");
		lblngK.setForeground(new Color(255, 0, 128));
		lblngK.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblngK.setBounds(187, 0, 160, 88);
		panel_2.add(lblngK);
	}
	
	public void DangNhap() {
		this.setVisible(false);
		DangNhap home = new DangNhap();
		home.setVisible(true);
	}
	public void DangKy() {
		if(txttennd.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập tên người dùng !!!");
		}
		else if (txttaikhoan.getText().isEmpty()) {
JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập tài khoản !!!");
		}
		else if (txtmatkhau.getPassword().length < 5 ) {
			JOptionPane.showMessageDialog(contentPane, "Mật khẩu phải tối thiểu 5 ký tự !!!");
		}
		else if (txtnhaplaimatkhau.getPassword().length < 5) {
			JOptionPane.showMessageDialog(contentPane, "Mật khẩu nhập lại không đúng !!!");
		}else {
			try {
				char[] password = txtmatkhau.getPassword();
				char[] confirmPass = txtnhaplaimatkhau.getPassword();
				
				String passStr = new String(password);
				String confirmStr = new String(confirmPass);
				
		        if(confirmStr.equals(passStr)) {
		        	pst = con.prepareStatement("insert into admin(HoTen,TaiKhoan,MatKhau) values(?,?,?)");
		        	pst.setString(1, txttennd.getText());
		        	pst.setString(2, txttaikhoan.getText());
		        	pst.setString(3, new String(password));
		        	pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Đăng ký thành công!!!");
		            DangNhap();
		        	
		        }else {
		        	JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không đúng!"); 
		        }
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}