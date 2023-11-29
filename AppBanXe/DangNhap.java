package BaoCao;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
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
	
	   private JTextField usernameTextField;
	   private JPasswordField passwordField;
	   private JPasswordField txtMatKhau;

	   public void DangKi() {
			this.dispose();
			DangKi DangKi = new DangKi();
			DangKi.setVisible(true);
		}
	public void showTrangChu() {
		this.dispose();
		TrangChu TrangChu = new TrangChu();
		TrangChu.setVisible(true);
	}
		   
		/**
		 * Create the frame.
		 */
	public DangNhap() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 516, 94);
		contentPane.add(lblNewLabel);
		
		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String inputTaiKhoan = txtTaiKhoan.getText();
		        String inputMatKhau = txtMatKhau.getText();

		        try {
		            pst = con.prepareStatement("SELECT TaiKhoan, MatKhau FROM admin WHERE TaiKhoan = ? AND MatKhau = ?");
		            pst.setString(1, inputTaiKhoan);
		            pst.setString(2, inputMatKhau);
		            ResultSet rs = pst.executeQuery();

		            if (rs.next()) {
		                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
		                showTrangChu();
		            } else {
		                JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính sát!");
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnDangNhap.setForeground(new Color(255, 0, 128));
		btnDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnDangNhap.setBounds(290, 293, 130, 60);
		contentPane.add(btnDangNhap);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTaiKhoan.setBounds(220, 114, 200, 40);
		contentPane.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
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

		JLabel lblNewLabel_1 = new JLabel("Tài khoản");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(120, 114, 130, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setBounds(120, 178, 130, 40);
		contentPane.add(lblNewLabel_2);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(220, 180, 200, 40);
		contentPane.add(txtMatKhau);
		
		JButton btnDangNhap_1 = new JButton("Đăng ký");
		btnDangNhap_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangKi();
			}
		});
		btnDangNhap_1.setForeground(new Color(0, 204, 0));
		btnDangNhap_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnDangNhap_1.setBounds(120, 293, 130, 60);
		contentPane.add(btnDangNhap_1);
	}
}
