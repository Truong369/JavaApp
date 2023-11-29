package BaoCao;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class TrangChu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChu A = new TrangChu();
					A.setVisible(true);
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
	
	
	public void showQLSP() {
		this.dispose();
		SanPham qlsp = new SanPham();
		qlsp.setVisible(true);
	}
	public void showQLKH() {
		this.dispose();
		KhachHang qlkh = new KhachHang();
		qlkh.setVisible(true);
	}
	
	public void showQLNS() {
		this.dispose();
		NhanSu qlns = new NhanSu();
		qlns.setVisible(true);
	}
	
	public void showQLDH() {
		this.dispose();
		DonHang qldh = new DonHang();
		qldh.setVisible(true);
	}
		
	public void XinChao() {

	}
	
	/**
	 * Create the frame.
	 */
	public TrangChu() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
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

		JButton btnQLKH = new JButton("QL khách hàng");
		btnQLKH.setForeground(new Color(204, 0, 102));
		btnQLKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showQLKH();
			}
		});


        // Gán icon cho button
//		btnQLKH.setIcon(new ImageIcon("E:\\KNNN BaiTap\\Ảnh\\icon_khachhang.jpg"));
//		ImageIcon icon = new ImageIcon("E:\\\\KNNN BaiTap\\\\Ảnh\\\\icon_khachhang.jpg");
//		Image image = icon.getImage();
//
//        // Điều chỉnh kích thước của hình ảnh biểu tượng
//        Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		btnQLKH.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnQLKH.setBounds(240, 97, 175, 50);
		contentPane.add(btnQLKH);
		
		JButton btnQLSP = new JButton("QL sản phẩm");
		btnQLSP.setForeground(new Color(204, 204, 102));
		btnQLSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showQLSP();
			}
		});
		btnQLSP.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnQLSP.setBounds(468, 97, 175, 50);
		contentPane.add(btnQLSP);
		
		JButton btnQLNS = new JButton("QL nhân sự");
		btnQLNS.setForeground(new Color(255, 0, 0));
		btnQLNS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showQLNS();
			}
		});
		btnQLNS.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnQLNS.setBounds(240, 211, 175, 50);
		contentPane.add(btnQLNS);
		
		JButton btnQLDH = new JButton("QL đơn hàng");
		btnQLDH.setForeground(new Color(0, 0, 204));
		btnQLDH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showQLDH();
			}
		});
		btnQLDH.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnQLDH.setBounds(468, 211, 175, 50);
		contentPane.add(btnQLDH);
		
		JLabel lblNewLabel = new JLabel("Trang chủ");
		lblNewLabel.setForeground(new Color(153, 0, 204));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
		lblNewLabel.setBounds(10, 10, 866, 67);
		contentPane.add(lblNewLabel);
		
	
	}
}
