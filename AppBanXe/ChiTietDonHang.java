package BaoCao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChiTietDonHang extends JFrame {

	protected static final String TenSP = null;


	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private JTextField txt_IDDonHang;
////	public static void main(String[] args) {
////		EventQueue.invokeLater(new Runnable() {
////			public void run() {
////				try {
////					ChiTietDonHang frame = new ChiTietDonHang(idKhachHang, idDonHang);
////					frame.setVisible(true);
////				} catch (Exception e) {
////					e.printStackTrace();
////				}
////			}
//
//			private int DonHang() {
//				// TODO Auto-generated method stub
//				return (Integer) null;
//			}
//		});
//	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	private String idDonHang;
	private JTextField txtTenSP;
	private JTable table;
	private JTextField txtGia;
	private JTextField txtMauSac;
	private JTextField txtSoLuong;
	private JTextField txtTongTien;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtNgayTao;
	private JTextField txtSDT;
	private JTextField txtEmail;
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
	
	public void showDonHang() {
		this.setVisible(false);
		DonHang DonHang = new DonHang();
		DonHang.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ChiTietDonHang(int idDonHang, int idKhachHang) {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHI TIẾT ĐƠN HÀNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblNewLabel.setBounds(10, 10, 866, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(48, 108, 200, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Giá");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(48, 161, 200, 40);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Màu sắc");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(48, 211, 200, 40);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số lượng");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(48, 261, 200, 40);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Tổng tiền");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_4.setBounds(48, 311, 200, 40);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Tên khách hàng");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_5.setBounds(48, 361, 200, 40);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Địa chỉ");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_6.setBounds(48, 411, 200, 40);
		contentPane.add(lblNewLabel_1_6);
		
		txtTenSP = new JTextField();
		txtTenSP.setForeground(new Color(204, 0, 0));
		txtTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtTenSP.setBounds(160, 110, 200, 40);
		contentPane.add(txtTenSP);
		txtTenSP.setColumns(10);
		
		table = new JTable();
		table.setBackground(new Color(204, 255, 255));
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.getSelectedRow();
		        DefaultTableModel model = (DefaultTableModel) table.getModel();	
		        txtTenSP.setText(model.getValueAt(row, 1).toString());				
		        txtGia.setText(model.getValueAt(row, 2).toString());
		        txtMauSac.setText(model.getValueAt(row, 3).toString());
		        txtSoLuong.setText(model.getValueAt(row, 4).toString());
		        txtTongTien.setText(model.getValueAt(row, 5).toString());		     
		        try {
		        	int id = (int) model.getValueAt(row, 0);
		            pst = con.prepareStatement("SELECT TenSP FROM SanPham WHERE ID = ?");
		            pst.setInt(1, id);
		            rs = pst.executeQuery();
		            if (rs.next()) {
		                String TenSPCT = rs.getString("TenSP");
//		                txtTenSPCT.setSelectedItem(TenSPCT);
		            }
		        } catch (SQLException e2) {
		            e2.printStackTrace();
		        }
		    }
		    });
		table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table.setBounds(389, 172, 495, 217);
		contentPane.add(table); 
		
		try {
		    int ID = idDonHang;
		    pst = con.prepareStatement("SELECT * FROM ChiTietDonHang WHERE ID_DonHang=?");
		    pst.setInt(1, ID);
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (SQLException e){
				e.printStackTrace();
		}
		    txtGia = new JTextField();
		    txtGia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    txtGia.setColumns(10);
		    txtGia.setBounds(160, 158, 200, 40);
		    contentPane.add(txtGia);
		    
		    txtMauSac = new JTextField();
		    txtMauSac.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    txtMauSac.setColumns(10);
		    txtMauSac.setBounds(160, 211, 200, 40);
		    contentPane.add(txtMauSac);
		    
		    txtSoLuong = new JTextField();
		    txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    txtSoLuong.setColumns(10);
		    txtSoLuong.setBounds(160, 261, 200, 40);
		    contentPane.add(txtSoLuong);
		    
		    txtTongTien = new JTextField();
		    txtTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    txtTongTien.setColumns(10);
		    txtTongTien.setBounds(160, 311, 200, 40);
		    contentPane.add(txtTongTien);

			JLabel lblNewLabel_1_6_1 = new JLabel("Ngày tạo");
			lblNewLabel_1_6_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblNewLabel_1_6_1.setBounds(48, 459, 200, 40);
			contentPane.add(lblNewLabel_1_6_1);
			
			txtTenKH = new JTextField();
			txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtTenKH.setColumns(10);
			txtTenKH.setBounds(160, 361, 200, 40);
			contentPane.add(txtTenKH);
			
			txtDiaChi = new JTextField();
			txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtDiaChi.setColumns(10);
			txtDiaChi.setBounds(160, 411, 200, 40);
			contentPane.add(txtDiaChi);
			
			txtNgayTao = new JTextField();
			txtNgayTao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtNgayTao.setColumns(10);
			txtNgayTao.setBounds(160, 461, 200, 40);
			contentPane.add(txtNgayTao);
			
			JLabel lblBngSnPhm = new JLabel("Bảng sản phẩm");
			lblBngSnPhm.setForeground(new Color(64, 0, 128));
			lblBngSnPhm.setHorizontalAlignment(SwingConstants.CENTER);
			lblBngSnPhm.setFont(new Font("Times New Roman", Font.BOLD, 32));
			lblBngSnPhm.setBounds(388, 108, 488, 60);
			contentPane.add(lblBngSnPhm);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 204, 255));
			panel.setBounds(10, 10, 886, 503);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel_1_6_2 = new JLabel("SDT khách hàng");
			lblNewLabel_1_6_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblNewLabel_1_6_2.setBounds(378, 402, 112, 40);
			panel.add(lblNewLabel_1_6_2);
			
			txtSDT = new JTextField();
			txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtSDT.setColumns(10);
			txtSDT.setBounds(520, 404, 200, 40);
			panel.add(txtSDT);
			
			JLabel lblNewLabel_1_6_2_1 = new JLabel("Email khách hàng");
			lblNewLabel_1_6_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblNewLabel_1_6_2_1.setBounds(378, 452, 135, 40);
			panel.add(lblNewLabel_1_6_2_1);
			
			JButton btnThoat = new JButton("Quay lại");
			btnThoat.setBounds(745, 418, 131, 60);
			panel.add(btnThoat);
			btnThoat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showDonHang();
				}
			});
			btnThoat.setForeground(new Color(255, 0, 0));
			btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 16));
			
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtEmail.setColumns(10);
			txtEmail.setBounds(520, 452, 200, 40);
			panel.add(txtEmail);
			System.out.println(idDonHang);	
			System.out.println(idKhachHang);	
			
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
	        
			//Xuất họ tên khách hàng
			try {
			    int ID = idKhachHang;
			    pst = con.prepareStatement("SELECT HoTen From KhachHang Where ID = ? ");
			    pst.setInt(1, ID);
			    rs = pst.executeQuery();
			    
			    if (rs.next()) {
			        String TenKH = rs.getString("HoTen");  
			        txtTenKH.setText(TenKH);
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
			
			//Xuất thông tin địa chỉ khách hàng đặc
			try {
			    int ID = idDonHang;
			    pst = con.prepareStatement("SELECT DiaChi From DonHang Where ID = ? ");
			    pst.setInt(1, ID);
			    rs = pst.executeQuery();
			    
			    if (rs.next()) {
			        String DiaChiKH = rs.getString("DiaChi");  
			        txtDiaChi.setText(DiaChiKH);
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
			//Xuất thời gian tạo đơn hàng
			try {
			    int ID = idDonHang;
			    pst = con.prepareStatement("SELECT NgayTao From DonHang Where ID = ? ");
			    pst.setInt(1, ID);
			    rs = pst.executeQuery();
			    
			    if (rs.next()) {
			        String NgayTao = rs.getString("NgayTao");  
			        txtNgayTao.setText(NgayTao);
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
			
			//Truy xuất SDT kahsch hàng
			try {
			    int ID = idKhachHang;
			    pst = con.prepareStatement("SELECT SDT From KhachHang Where ID = ? ");
			    pst.setInt(1, ID);
			    rs = pst.executeQuery();
			    
			    if (rs.next()) {
			        String SDT = rs.getString("SDT");  
			        txtSDT.setText(SDT);
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
			//Truy xuất Email kahsch ahnfg
			try {
			    int ID = idKhachHang;
			    pst = con.prepareStatement("SELECT Email From KhachHang Where ID = ? ");
			    pst.setInt(1, ID);
			    rs = pst.executeQuery();
			    
			    if (rs.next()) {
			        String email = rs.getString("Email");  
			        txtEmail.setText(email);
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
	}
}
