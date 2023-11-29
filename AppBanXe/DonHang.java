package BaoCao;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.mysql.cj.xdevapi.Statement;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class DonHang extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonHang frame = new DonHang();
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
	private JTable table;
	private JTable table_1;
	private JTextField txtID_DonHang;
	private JTextField txtSoLuongCT;
	private JButton btnThemSP;
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
			pst = con.prepareStatement("select * from donhang");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));			
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
	
	public void Load() {
	    try {
	        String ID_DonHang = txtID_DonHang.getText();
	        pst = con.prepareStatement("SELECT * FROM ChiTietDonHang WHERE ID_DonHang = ?");
	        pst.setString(1, ID_DonHang);
	        rs = pst.executeQuery();	        
	        table_1.setModel(DbUtils.resultSetToTableModel(rs));
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Create the frame.
	 */
	public DonHang() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÍ ĐƠN HÀNG");
		lblNewLabel.setForeground(new Color(255, 51, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblNewLabel.setBounds(10, 10, 1266, 75);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setToolTipText("Đơn hàng");
		panel.setBounds(10, 95, 499, 658);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Địa chỉ giao hàng");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(44, 79, 130, 40);
		panel.add(lblNewLabel_1_3);
		
		JTextPane txtDiaChi = new JTextPane();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtDiaChi.setBounds(200, 82, 268, 60);
		panel.add(txtDiaChi);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Tên khách hàng");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_2.setBounds(44, 148, 130, 40);
		panel.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_4 = new JLabel("ID đơn hàng");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(44, 27, 130, 40);
		panel.add(lblNewLabel_1_4);
		
		txtID_DonHang = new JTextField();
		txtID_DonHang.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtID_DonHang.setEnabled(false);
		txtID_DonHang.setColumns(10);
		txtID_DonHang.setBounds(200, 30, 268, 40);
		panel.add(txtID_DonHang);
		
		JComboBox txtTenKH = new JComboBox();
		txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtTenKH.setBounds(200, 152, 268, 36);
		panel.add(txtTenKH);
		
		JComboBox txtTenNV = new JComboBox();
		txtTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtTenNV.setBounds(200, 204, 268, 36);
		panel.add(txtTenNV);
		
		JLabel lblNewLabel_1_3_2_2 = new JLabel("Tên nhân viên");
		lblNewLabel_1_3_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_2_2.setBounds(44, 200, 130, 40);
		panel.add(lblNewLabel_1_3_2_2);
		
//		Lấy ID của Nhân viên
//		try {
//		    String query = "SELECT ID FROM nhansu WHERE HoTen = ?";
//		    pst = con.prepareStatement(query);
//		    rs = pst.executeQuery();
//
//		    while (rs.next()) {
//		        String TenNV = rs.getString("HoTen");
//		        txtTenNV.addItem(TenNV);
//		    }
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		}
//		
//		//Lấy ID của Khách hàng
//		try {
//		    String query = "SELECT ID FROM khachhang WHERE HoTen = ?";
//		    pst = con.prepareStatement(query);
//		    rs = pst.executeQuery();
//
//		    while (rs.next()) {
//		        String TenKH = rs.getString("HoTen");
//		        txtTenNV.addItem(TenKH);
//		    }
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		}
		
		JButton btnThem = new JButton("Thêm đơn hàng");
		btnThem.setBounds(236, 508, 180, 62);
		panel.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String TenKH = (String) txtTenKH.getSelectedItem();
		        String TenNV = (String) txtTenNV.getSelectedItem();
		        String DiaChi = txtDiaChi.getText();
		        String ID_DonHang = txtID_DonHang.getText();

		        if (TenNV.isEmpty() || DiaChi.isEmpty() || TenKH.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
		        } else {
		            try {
		                pst = con.prepareStatement("SELECT ID FROM khachhang WHERE HoTen = ?");
		                pst.setString(1, TenKH);
		                rs = pst.executeQuery();
		                if (rs.next()) {
		                    int ID_KhachHang = rs.getInt("ID");
		                    pst = con.prepareStatement("SELECT ID FROM nhansu WHERE HoTen = ?");
		                    pst.setString(1, TenNV);
		                    rs = pst.executeQuery();
		                    System.out.println(ID_KhachHang);
		                    
		                    if (rs.next()) {
		                        int ID_NhanSu = rs.getInt("ID");
		                        System.out.println(ID_NhanSu);
		                        pst = con.prepareStatement("INSERT INTO donhang(DiaChi, NgayTao, ID_KhachHang, ID_NhanSu) VALUES (?, NOW(), ?, ?)");
		                        pst.setString(1, DiaChi);
		                        pst.setInt(2, ID_KhachHang);
		                        pst.setInt(3, ID_NhanSu);
		                        pst.executeUpdate();
		                        table_load();
		                        JOptionPane.showMessageDialog(null, "Thêm thành công!");
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên bán hàng: " + TenNV);
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng: " + TenKH);
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});

				btnThem.setForeground(new Color(204, 51, 255));
				btnThem.setFont(new Font("Times New Roman", Font.BOLD, 20));
				btnThem.setBackground(new Color(255, 250, 205));
				
				JButton btnReset = new JButton("Reset");
				btnReset.setBounds(111, 508, 82, 62);
				panel.add(btnReset);
				btnReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtID_DonHang.setText(null);
						txtDiaChi.setText(null);
						txtTenKH.setSelectedItem(null);
						txtTenNV.setSelectedItem(null);
					}
				});
				btnReset.setForeground(new Color(255, 0, 128));
				btnReset.setFont(new Font("Times New Roman", Font.BOLD, 20));
				btnReset.setBackground(new Color(255, 250, 205));
				
				JButton btnXoa = new JButton("Xóa đơn hàng");
				btnXoa.setBounds(236, 580, 180, 62);
				panel.add(btnXoa);
				btnXoa.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        try {
				            int row = table.getSelectedRow();
				            if (row == -1) {
				                JOptionPane.showMessageDialog(null, "Vui lòng chọn một đơn hàng để xóa!");
				                return; // Ngừng thực hiện nếu không có hàng được chọn
				            }
				          
				            DefaultTableModel model = (DefaultTableModel) table.getModel();
				            int id = (int) model.getValueAt(row, 0);

				            // Kiểm tra xem có chi tiết đơn hàng liên quan không
				            pst = con.prepareStatement("SELECT COUNT(*) FROM chitietdonhang WHERE ID_DonHang = ?");
				            pst.setInt(1, id);
				            ResultSet rs = pst.executeQuery();
				            rs.next();
				            int count = rs.getInt(1);
				            rs.close();

				            if (count > 0) {
				                // Xóa các hàng liên quan trong bảng "chitietdonhang"
				                pst = con.prepareStatement("DELETE FROM chitietdonhang WHERE ID_DonHang = ?");
				                pst.setInt(1, id);
				                pst.executeUpdate();
				            }

				            // Xóa hàng trong bảng "donhang"
				            pst = con.prepareStatement("DELETE FROM donhang WHERE ID = ?");
				            pst.setInt(1, id);
				            pst.executeUpdate();

				            JOptionPane.showMessageDialog(null, "Xóa thành công!!!");
				            table_load();
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
				    }
				});
				btnXoa.setForeground(new Color(0, 128, 0));
				btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 20));
				btnXoa.setBackground(new Color(255, 250, 205));
				
				JButton btnSua = new JButton("Sửa");
				btnSua.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        try {
				        	
				        	int row = table.getSelectedRow();				           
				            DefaultTableModel model = (DefaultTableModel) table.getModel();
				            if (row == -1) {
				                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng!");
				                return;
				            }
				            
				            int id = (int) model.getValueAt(row, 0);
				            // Kiểm tra các trường rỗng
				            String ID_DonHang = txtID_DonHang.getText();
				            String DiaChi = txtDiaChi.getText();
				            String TenKH = (String) txtTenKH.getSelectedItem();
				            String TenNV = (String) txtTenNV.getSelectedItem();
				            
				            if (ID_DonHang.isEmpty()|| DiaChi.isEmpty() || TenKH.isEmpty() || TenNV.isEmpty()) {
				                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
				            } else {
				                pst = con.prepareStatement("SELECT ID FROM KhachHang WHERE HoTen = ?");
				                pst.setString(1, TenKH);
				                rs = pst.executeQuery();
				                
				                if (rs.next()) {
				                    int ID_KhachHang = rs.getInt("ID");
				                    
				                    pst = con.prepareStatement("SELECT ID FROM NhanSu WHERE HoTen = ?");
				                    pst.setString(1, TenNV);
				                    rs = pst.executeQuery();
				                    
				                    if (rs.next()) {
				                        int ID_NhanSu = rs.getInt("ID");
				                        
				                        pst = con.prepareStatement("UPDATE DonHang SET DiaChi=?, ID_KhachHang=?, ID_NhanSu=? WHERE ID=?");
				                        pst.setString(1, DiaChi);
				                        pst.setInt(2, ID_KhachHang);
				                        pst.setInt(3, ID_NhanSu);
				                        pst.setInt(4, id);
				                        pst.executeUpdate();          
				                        JOptionPane.showMessageDialog(null, "Sửa thành công !!!");
				                        table_load();
				                    }
				                }
				            }
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
				    }
				});
				btnSua.setForeground(new Color(64, 128, 128));
				btnSua.setFont(new Font("Times New Roman", Font.BOLD, 20));
				btnSua.setBackground(new Color(255, 250, 205));
				btnSua.setBounds(111, 580, 82, 62);
				panel.add(btnSua);
		
		table = new JTable();
		table.setBounds(10, 293, 479, 205);
		panel.add(table);
		table.setCellSelectionEnabled(true);
		table.setBackground(new Color(204, 204, 204));
		table.setForeground(new Color(51, 0, 204));
		table.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Đơn hàng");
		lblNewLabel_2.setBounds(10, 250, 479, 45);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 0, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.getSelectedRow();
		        DefaultTableModel model = (DefaultTableModel) table.getModel();	
		        txtID_DonHang.setText(model.getValueAt(row, 0).toString());										
		        txtDiaChi.setText(model.getValueAt(row, 1).toString());		     
		        String ID_KhachHang = model.getValueAt(row, 3).toString();
		        String ID_NhanSu = model.getValueAt(row, 4).toString();
		        System.out.println("ID khách hàng " + ID_KhachHang);
		        System.out.println("ID Nhân viên " + ID_NhanSu);
		        Load();
		        try {
		            pst = con.prepareStatement("SELECT HoTen FROM NhanSu WHERE ID = ?");
		            pst.setInt(1, Integer.parseInt(ID_NhanSu));
		            rs = pst.executeQuery();
		            if (rs.next()) {
		                String TenNV = rs.getString("HoTen");
		                txtTenNV.setSelectedItem(TenNV);
		            }
		        } catch (SQLException e2) {
		            e2.printStackTrace();
		        }
		        try {
		            pst = con.prepareStatement("SELECT HoTen FROM KhachHang WHERE ID = ?");
		            pst.setInt(1, Integer.parseInt(ID_KhachHang));
		            rs = pst.executeQuery();
		            if (rs.next()) {
		                String TenKH = rs.getString("HoTen");
		                txtTenKH.setSelectedItem(TenKH);
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }

//		        TongCong();
		    }

			private String setText(String string) {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		
		JButton btnChiTiet = new JButton("Chi tiết đơn hàng");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				 if (table.getSelectedRow() != -1) {
						int id = table.getSelectedRow();	
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						int idDonHang = Integer.parseInt(txtID_DonHang.getText());
						int idKhachHang = (int) model.getValueAt(id, 3);
					    ChiTietDonHang ChiTietDonHang = new ChiTietDonHang(idDonHang, idKhachHang);
					    ChiTietDonHang.setVisible(true);
			        } else {
			            // Hiển thị thông báo lỗi nếu không có hàng được chọn
			            JOptionPane.showMessageDialog(null, "Vui lòng chọn một đơn hàng trước khi xem chi tiết!");
			        }
			}
		});
		btnChiTiet.setForeground(new Color(255, 0, 0));
		btnChiTiet.setBackground(new Color(255, 250, 205));
		btnChiTiet.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnChiTiet.setBounds(519, 687, 200, 54);
		contentPane.add(btnChiTiet);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToHome();
			}
		});
		
		// lấy danh sách Sản phẩm
		try {
		    String query = "SELECT TenSP FROM SanPham";
		    pst = con.prepareStatement(query);
		    rs = pst.executeQuery();

		    while (rs.next()) {
		        String TenSP = rs.getString("TenSP");
//		        txtTenSP.addItem(TenSP);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		// lấy danh sách khách hàng
		try {
		    String query = "SELECT HoTen FROM KhachHang";
		    pst = con.prepareStatement(query);
		    rs = pst.executeQuery();

		    while (rs.next()) {
		        String TenKH = rs.getString("HoTen");
		        txtTenKH.addItem(TenKH);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		// lấy danh sách nhân viên
		try {
		    String query = "SELECT HoTen FROM NhanSu";
		    pst = con.prepareStatement(query);
		    rs = pst.executeQuery();

		    while (rs.next()) {
		        String TenNV = rs.getString("HoTen");
		        txtTenNV.addItem(TenNV);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		btnBack.setForeground(new Color(165, 42, 42));
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBack.setBackground(new Color(255, 250, 205));
		btnBack.setBounds(1117, 687, 148, 54);
		contentPane.add(btnBack);
		

		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(519, 395, 757, 261);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Tên sản phẩm");
		lblNewLabel_1_4_1.setBounds(37, 101, 130, 40);
		panel_1.add(lblNewLabel_1_4_1);
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Số lượng");
		lblNewLabel_1_4_1_1.setBounds(37, 151, 130, 40);
		panel_1.add(lblNewLabel_1_4_1_1);
		lblNewLabel_1_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtSoLuongCT = new JTextField();
		txtSoLuongCT.setBounds(177, 154, 230, 40);
		panel_1.add(txtSoLuongCT);
		txtSoLuongCT.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String newText = getText(0, getLength()) + str;
                if (newText.matches("\\d*") && newText.length() <= 5) { 
                	// Kiểm tra chuỗi chỉ chứa các chữ số và có độ dài tối đa là 10
                    super.insertString(offs, str, a);
                }	                	
            }
        });
		txtSoLuongCT.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtSoLuongCT.setColumns(10);
		
		
		
		JComboBox txtTenSPCT = new JComboBox();
		txtTenSPCT.setBounds(177, 105, 230, 36);
		panel_1.add(txtTenSPCT);
		txtTenSPCT.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		try {
		    String query = "SELECT TenSP FROM SanPham";
		    pst = con.prepareStatement(query);
		    rs = pst.executeQuery();

		    while (rs.next()) {
		        String TenSP = rs.getString("TenSP");
				txtTenSPCT.addItem(TenSP);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		btnThemSP = new JButton("Thêm sản phẩm");
		btnThemSP.setForeground(new Color(64, 0, 128));
		btnThemSP.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnThemSP.setBounds(544, 77, 171, 52);
		panel_1.add(btnThemSP);
		btnThemSP.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String TenSPCT = (String) txtTenSPCT.getSelectedItem();
		        String SoLuongCT = txtSoLuongCT.getText();
		        String ID_DonHang = txtID_DonHang.getText();
		        if (TenSPCT.isEmpty() || SoLuongCT.isEmpty() || ID_DonHang.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 đơn hàng!");
		        } else {
		            try {
		                int soLuongCT = Integer.parseInt(SoLuongCT);
		                // Kiểm tra xem ID_DonHang có tồn tại trong bảng DonHang không
		                pst = con.prepareStatement("SELECT ID FROM DonHang WHERE ID = ?");
		                pst.setString(1, ID_DonHang);
		                rs = pst.executeQuery();
		                if (rs.next()) {
		                    int ID_DonHangInt = rs.getInt("ID");
		                    // Lấy thông tin sản phẩm từ bảng SanPham
		                    pst = con.prepareStatement("SELECT ID, Gia, MauSac FROM SanPham WHERE TenSP = ?");
		                    pst.setString(1, TenSPCT);
		                    rs = pst.executeQuery();
		                    if (rs.next()) {
		                        int ID_SanPham = rs.getInt("ID");
		                        int gia = rs.getInt("Gia");
		                        String mauSac = rs.getString("MauSac");
		                        int tongTien = soLuongCT * gia;
		                        pst = con.prepareStatement("INSERT INTO ChiTietDonHang(TenSP, Gia, SoLuong, MauSac, TongTien, ID_DonHang, ID_SanPham ) VALUES (?, ?, ?, ?, ?, ?, ?)");
		                        pst.setString(1, TenSPCT);
		                        pst.setInt(2, gia);
		                        pst.setInt(3, soLuongCT);
		                        pst.setString(4, mauSac);
		                        pst.setInt(5, tongTien);
		                        pst.setInt(6, ID_DonHangInt);
		                        pst.setInt(7, ID_SanPham);		                        
		                        pst.executeUpdate();
		                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công vào đơn hàng!");
		                        Load();
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm có tên: " + TenSPCT);
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Không tìm thấy đơn hàng có ID: " + ID_DonHang);
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ!");
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		JButton btnXoaSP = new JButton("Xóa sản phẩm");
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            int row = table_1.getSelectedRow();
			            if (row == -1) {
			                JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa!");
			                return; // Ngừng thực hiện nếu không có hàng được chọn
			            }			            
			            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			            int id = (int) model.getValueAt(row, 7);

			            // Xóa các hàng liên quan trong bảng "chitietdonhang"
			            pst = con.prepareStatement("DELETE FROM chitietdonhang WHERE ID_SanPham = ?");
			            pst.setInt(1, id);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Xóa thành công!!!");
			            Load();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }	
		});
		btnXoaSP.setForeground(new Color(255, 0, 0));
		btnXoaSP.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnXoaSP.setBackground(new Color(255, 255, 255));
		btnXoaSP.setBounds(544, 199, 171, 52);
		panel_1.add(btnXoaSP);					
		
		JLabel lblNewLabel_2_1 = new JLabel("Thêm sản phẩm cho đơn hàng");
		lblNewLabel_2_1.setBounds(0, 10, 757, 45);
		panel_1.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(new Color(0, 51, 153));
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		JLabel lblNewLabel_1_4_1_2 = new JLabel("ID");
		lblNewLabel_1_4_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4_1_2.setBounds(37, 51, 130, 40);
		panel_1.add(lblNewLabel_1_4_1_2);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			        	
			        	int row = table_1.getSelectedRow();				           
			            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			            if (row == -1) {
			                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng!");
			                return;
			            }
			            
			            int id = (int) model.getValueAt(row, 0);
			            // Kiểm tra các trường rỗng
			            String ID_DonHang = txtID_DonHang.getText();
			            String TenSP = (String) txtTenSPCT.getSelectedItem();
			            String SoLuong = txtSoLuongCT.getText();
			            
			            if (ID_DonHang.isEmpty()|| TenSP.isEmpty() || SoLuong.isEmpty()) {
			                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
			            } else {
			                pst = con.prepareStatement("SELECT ID FROM SanPham WHERE TenSP = ?");
			                pst.setString(1, TenSP);
			                rs = pst.executeQuery();			                
			                if (rs.next()) {
			                    int ID_KhachHang = rs.getInt("ID");
			                    	pst = con.prepareStatement("UPDATE ChiTietDonHang SET TenSP=?, SoLuong=? WHERE ID=?");
			                        pst.setString(1, TenSP);
			                        pst.setString(2, SoLuong);
			                        pst.setInt(3, id);
			                        pst.executeUpdate();          
			                        JOptionPane.showMessageDialog(null, "Sửa thành công !!!");
			                        Load();
			                    }
			                }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			
		});
		btnSa.setForeground(new Color(0, 128, 0));
		btnSa.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSa.setBackground(new Color(255, 250, 205));
		btnSa.setBounds(544, 139, 171, 52);
		panel_1.add(btnSa);
		table_1 = new JTable();
		table_1.setBounds(519, 150, 753, 235);
		table_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table_1.getSelectedRow();
		        DefaultTableModel model = (DefaultTableModel) table_1.getModel();	
		        txtTenSPCT.setSelectedItem(model.getValueAt(row, 1).toString());										
		        txtSoLuongCT.setText(model.getValueAt(row, 4).toString());		     
		        try {
		        	int id = (int) model.getValueAt(row, 0);
		            pst = con.prepareStatement("SELECT TenSP FROM SanPham WHERE ID = ?");
		            pst.setInt(1, id);
		            rs = pst.executeQuery();
		            if (rs.next()) {
		                String TenSPCT = rs.getString("TenSP");
		                txtTenSPCT.setSelectedItem(TenSPCT);
		            }
		        } catch (SQLException e2) {
		            e2.printStackTrace();
		        }
		    }
		    });
		
		table_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		table_1.setBackground(new Color(255, 255, 204));
		contentPane.add(table_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Bảng chi tiết sản phẩm");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setForeground(new Color(255, 0, 255));
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_2_1_1.setBounds(519, 95, 757, 45);
		contentPane.add(lblNewLabel_2_1_1);
		table_load();
	}
}
