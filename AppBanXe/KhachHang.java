package BaoCao;
import javax.swing.text.*;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.security.auth.login.AccountException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KhachHang extends JFrame {



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHang frame = new KhachHang();
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
	private JTable table_1;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JPanel contentPane;
	private JTextField txtHoTen;
	private JTextField txtCustomerID;
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
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from khachhang");
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
	}
	
	public void backToHome() {
		this.setVisible(false);
		TrangChu home = new TrangChu();
		home.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public KhachHang() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		contentPane.setLayout(null);
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(166, 165, 250, 40);
		txtSDT.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String newText = getText(0, getLength()) + str;
                if (newText.matches("\\d*") && newText.length() <= 10) { 
                	// Kiểm tra chuỗi chỉ chứa các chữ số và có độ dài tối đa là 10
                    super.insertString(offs, str, a);
                }	                	
            }
        });
		contentPane.add(txtSDT);
		
		txtHoTen = new JTextField();
		txtHoTen.setBounds(166, 98, 250, 40);
		contentPane.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Họ Tên");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(31, 96, 95, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setForeground(new Color(0, 128, 255));
		btnThem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String HoTen = txtHoTen.getText();
		        String SDT = txtSDT.getText();
		        String Email = txtEmail.getText();
		        String DiaChi = txtDiaChi.getText();

		        if (HoTen.isEmpty() || SDT.isEmpty() || Email.isEmpty() || DiaChi.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
		        } else {
		            try {
		                // Kiểm tra sự tồn tại của khách hàng
		                pst = con.prepareStatement("SELECT * FROM khachhang WHERE HoTen=? AND SDT=? AND Email=? AND DiaChi=?");
		                pst.setString(1, HoTen);
		                pst.setString(2, SDT);
		                pst.setString(3, Email);
		                pst.setString(4, DiaChi);
		                ResultSet rs = pst.executeQuery();

		                if (rs.next()) {
		                    JOptionPane.showMessageDialog(null, "Khách hàng đã tồn tại!");
		                } else {
		                    // Thêm khách hàng vào cơ sở dữ liệu
		                    pst = con.prepareStatement("INSERT INTO khachhang(HoTen,SDT,Email,DiaChi) VALUES(?,?,?,?)");
		                    pst.setString(1, HoTen);
		                    pst.setString(2, SDT);
		                    pst.setString(3, Email);
		                    pst.setString(4, DiaChi);
		                    pst.executeUpdate();
		                    JOptionPane.showMessageDialog(null, "Thêm thành công !!!");
		                    table_load();
		                }
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnThem.setBounds(31, 420, 114, 64);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("Sữa");
		btnSua.setForeground(new Color(0, 255, 128));
		btnSua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int row = table_1.getSelectedRow();
		            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		            if (row < 0) {
		                JOptionPane.showMessageDialog(null, "Vui lòng chọn một khách hàng để cập nhật");
		            } else {
		                int id = (int) model.getValueAt(row, 0);
		                String hoTen = txtHoTen.getText();
		                String diaChi = txtDiaChi.getText();
		                String email = txtEmail.getText();
		                String sdt = txtSDT.getText();

		                if (hoTen.isEmpty() || diaChi.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
		                } else {
		                    pst = con.prepareStatement("UPDATE khachhang SET HoTen=?, DiaChi=?, Email=?, SDT=? WHERE id=?");
		                    pst.setString(1, hoTen);
		                    pst.setString(2, diaChi);
		                    pst.setString(3, email);
		                    pst.setString(4, sdt);
		                    pst.setInt(5, id);
		                    pst.executeUpdate();
		                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
		                    table_load();
		                }
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSua.setBounds(176, 423, 114, 59);
		contentPane.add(btnSua);
		
		
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

			
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(255, 0, 0));
		btnXoa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int row = table_1.getSelectedRow();
		            DefaultTableModel model = (DefaultTableModel) table_1.getModel();

		            if (row < 0) {
		                JOptionPane.showMessageDialog(null, "Vui lòng chọn một khách hàng để xóa");
		            } else {
		                int customerID = (int) model.getValueAt(row, 0);

		                // Xóa các đơn hàng chi tiết liên quan đến khách hàng
		                pst = con.prepareStatement("DELETE FROM chitietdonhang WHERE ID_DonHang IN (SELECT ID FROM donhang WHERE ID_KhachHang = ?)");
		                pst.setInt(1, customerID);
		                pst.executeUpdate();

		                // Xóa các đơn hàng của khách hàng
		                pst = con.prepareStatement("DELETE FROM donhang WHERE ID_KhachHang = ?");
		                pst.setInt(1, customerID);
		                pst.executeUpdate();

		                // Xóa khách hàng
		                pst = con.prepareStatement("DELETE FROM khachhang WHERE ID = ?");
		                pst.setInt(1, customerID);
		                pst.executeUpdate();

		                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!");
		                table_load();
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnXoa.setBounds(318, 423, 114, 59);
		contentPane.add(btnXoa);
		
		table_1 = new JTable();
		table_1.setBackground(SystemColor.info);
		table_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

					int row = table_1.getSelectedRow();
					if (row >= 0) {
					DefaultTableModel model = (DefaultTableModel)table_1.getModel();
					int id = (int) model.getValueAt(row, 0);
					txtCustomerID.setText(model.getValueAt(row, 0).toString());
					txtHoTen.setText(model.getValueAt(row, 1).toString());
					txtSDT.setText(model.getValueAt(row, 4).toString());
					txtDiaChi.setText(model.getValueAt(row, 2).toString());
					txtEmail.setText(model.getValueAt(row, 3).toString());
					}
				}
			
		});
		table_1.setBounds(443, 98, 520, 235);
		contentPane.add(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("QUẢN LÍ KHÁCH HÀNG");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(10, 10, 966, 54);
		contentPane.add(lblNewLabel_1);
		table_load();
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(166, 228, 250, 40);
		contentPane.add(txtEmail);
		
		JLabel lblSdt = new JLabel("SDT");
		lblSdt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSdt.setBounds(31, 163, 95, 40);
		contentPane.add(lblSdt);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmail.setBounds(31, 226, 95, 40);
		contentPane.add(lblEmail);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(166, 293, 250, 40);
		contentPane.add(txtDiaChi);
		
		JLabel lblEmail_1 = new JLabel("Dịa chỉ");
		lblEmail_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmail_1.setBounds(31, 291, 95, 40);
		contentPane.add(lblEmail_1);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(255, 0, 255));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtHoTen.setText(null);
				txtDiaChi.setText(null);
				txtSDT.setText(null);
				txtEmail.setText(null);
				txtCustomerID.setText(null);
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnReset.setBounds(469, 423, 114, 59);
		contentPane.add(btnReset);
		
		JButton btnThoat = new JButton("Back");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				backToHome();
			}
		});
		btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnThoat.setBounds(826, 423, 114, 59);
		contentPane.add(btnThoat);
		
		txtCustomerID = new JTextField();
		txtCustomerID.setEnabled(false);
		txtCustomerID.setColumns(10);
		txtCustomerID.setBounds(166, 359, 250, 40);
		contentPane.add(txtCustomerID);
		
		JLabel lblEmail_1_1 = new JLabel("ID khách hàng");
		lblEmail_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmail_1_1.setBounds(31, 359, 114, 40);
		contentPane.add(lblEmail_1_1);
		
	}
}
