package BaoCao;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class SanPham extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SanPham frame = new SanPham();
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

	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtTenSP;
	private JTextField txtDungTich;
	private JTextField txtGia;
	private JTextField txtSoLuong;
	private JTable table;
	private JTextField txtMauSac;
	private JComboBox comboBoxNhanHieu;
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
			pst = con.prepareStatement("select * from sanpham");
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
	
	
	/**
	 * Create the frame.
	 */
	public SanPham() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÍ SẢN PHẨM");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 10, 1176, 118);
		contentPane.add(lblNewLabel);
		
		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtTenSP.setBounds(175, 138, 270, 40);
		contentPane.add(txtTenSP);
		txtTenSP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(20, 138, 155, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nhãn hiệu");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(20, 188, 155, 40);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Loại sản phẩm");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(20, 237, 155, 40);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Dung tích");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(20, 287, 155, 40);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Giá");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_4.setBounds(20, 337, 155, 40);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Số lượng");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_5.setBounds(20, 387, 155, 40);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Mô tả");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_6.setBounds(20, 504, 155, 40);
		contentPane.add(lblNewLabel_1_6);
		
		txtDungTich = new JTextField();
		txtDungTich.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtDungTich.setColumns(10);
		txtDungTich.setBounds(175, 287, 270, 40);
		contentPane.add(txtDungTich);
		
		txtGia = new JTextField();
		txtGia.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtGia.setColumns(10);
		txtGia.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String newText = getText(0, getLength()) + str;
                if (newText.matches("\\d*") && newText.length() <= 10) { // Kiểm tra chuỗi chỉ chứa các chữ số và có độ dài tối đa là 10
                    super.insertString(offs, str, a);
                }else {
//                	JOptionPane.showMessageDialog(null, "chỉ được nhập 10 kí tự và số");
                }
                	
            }
        });
		txtGia.setBounds(175, 337, 270, 40);
		contentPane.add(txtGia);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtSoLuong.setColumns(10);
		txtSoLuong.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String newText = getText(0, getLength()) + str;
                if (newText.matches("\\d*") && newText.length() <= 5) { // Kiểm tra chuỗi chỉ chứa các chữ số và có độ dài tối đa là 10
                    super.insertString(offs, str, a);
                }else {
//                	JOptionPane.showMessageDialog(null, "chỉ được nhập 10 kí tự và số");
                }
                	
            }
        });
		txtSoLuong.setBounds(175, 387, 270, 40);
		contentPane.add(txtSoLuong);
		
		comboBoxNhanHieu = new JComboBox();
		comboBoxNhanHieu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		comboBoxNhanHieu.setModel(new DefaultComboBoxModel(new String[] {"Honda", "Yamaha", "Suzuki", "SYM", "Kawasaki", "Ducati"}));
		comboBoxNhanHieu.setBounds(175, 189, 270, 40);
		contentPane.add(comboBoxNhanHieu);
		
		JComboBox comboBoxLoai = new JComboBox();
		comboBoxLoai.setModel(new DefaultComboBoxModel(new String[] {"Xe máy phổ thông", "Xe tay ga", "Xe phân khối lớn"}));
		comboBoxLoai.setFont(new Font("Times New Roman", Font.BOLD, 16));
		comboBoxLoai.setBounds(175, 238, 270, 40);
		contentPane.add(comboBoxLoai);
		
		JTextArea txtMoTa = new JTextArea();
		txtMoTa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtMoTa.setBackground(new Color(228, 231, 233));
		txtMoTa.setBounds(175, 500, 270, 103);
		contentPane.add(txtMoTa);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 String TenSP = txtTenSP.getText();
				        String NhanHieu = (String) comboBoxNhanHieu.getSelectedItem();
				        String LoaiSP = (String) comboBoxLoai.getSelectedItem();
				        String DungTich = txtDungTich.getText();
				        String Gia = txtGia.getText();
				        String SoLuong = txtSoLuong.getText();
				        String MauSac = txtMauSac.getText();
				        String MoTa = txtMoTa.getText();

				        // Kiểm tra các trường rỗng
				        if (TenSP.isEmpty() || NhanHieu.isEmpty() || LoaiSP.isEmpty() || DungTich.isEmpty() || Gia.isEmpty() || SoLuong.isEmpty() || MauSac.isEmpty() || MoTa.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
				        } else {
				            try {
				                pst = con.prepareStatement("SELECT * FROM sanpham WHERE TenSP = ?");
				                pst.setString(1, TenSP);
				                ResultSet rs = pst.executeQuery();

				                if (rs.next()) {
				                    int currentQuantity = rs.getInt("SoLuong");
				                    int newQuantity = currentQuantity + Integer.parseInt(SoLuong);

				                    pst = con.prepareStatement("UPDATE sanpham SET SoLuong = ? WHERE TenSP = ?");
				                    pst.setInt(1, newQuantity);
				                    pst.setString(2, TenSP);
				                    pst.executeUpdate();

				                    JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại. Số lượng đã được cập nhật.");
				                    table_load();
				                } else {
				                    pst = con.prepareStatement("INSERT INTO sanpham(TenSP, NhanHieu, LoaiSP, DungTich, Gia, SoLuong, MauSac, MoTa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				                    pst.setString(1, TenSP);
				                    pst.setString(2, NhanHieu);
				                    pst.setString(3, LoaiSP);
				                    pst.setString(4, DungTich);
				                    pst.setString(5, Gia);
				                    pst.setString(6, SoLuong);
				                    pst.setString(7, MauSac);
				                    pst.setString(8, MoTa);
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
	
		btnThem.setForeground(new Color(0, 0, 255));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setBounds(470, 492, 130, 52);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("Sữa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        int row = table.getSelectedRow();
			        DefaultTableModel model = (DefaultTableModel) table.getModel();
			        
			        if (row < 0) {
			            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để cập nhật");
			        } else {
			            int id = (int) model.getValueAt(row, 0);
			            
			            // Kiểm tra các trường rỗng
			            String tenSP = txtTenSP.getText();
			            String nhanHieu = (String) comboBoxNhanHieu.getSelectedItem();
			            String loaiSP = (String) comboBoxLoai.getSelectedItem();
			            String dungTich = txtDungTich.getText();
			            String gia = txtGia.getText();
			            String soLuong = txtSoLuong.getText();
			            String mauSac = txtMauSac.getText();
			            String moTa = txtMoTa.getText();

			            if (tenSP.isEmpty() || nhanHieu.isEmpty() || loaiSP.isEmpty() || dungTich.isEmpty() || gia.isEmpty() || soLuong.isEmpty() || mauSac.isEmpty() || moTa.isEmpty()) {
			                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
			            } else {
			                pst = con.prepareStatement("UPDATE sanpham SET TenSP=?, NhanHieu=?, LoaiSP=?, DungTich=?, Gia=?, SoLuong=?, MauSac=?, MoTa=? WHERE ID=?");
			                pst.setString(1, tenSP);
			                pst.setString(2, nhanHieu);
			                pst.setString(3, loaiSP);
			                pst.setString(4, dungTich);
			                pst.setString(5, gia);
			                pst.setString(6, soLuong);
			                pst.setString(7, mauSac);
			                pst.setString(8, moTa);
			                pst.setInt(9, id);
			                JOptionPane.showMessageDialog(null, "Sửa thành công !!!");
			                pst.executeUpdate();
			                table_load();
			            }
			        }
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}
		});

		btnSua.setForeground(new Color(0, 255, 64));
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSua.setBounds(610, 492, 130, 52);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 
					 	int row = table.getSelectedRow();
			            DefaultTableModel model = (DefaultTableModel) table.getModel();

			            if (row < 0) {
			                JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa");
			            } else {
			            int id = (int) model.getValueAt(row, 0);

			            // Xóa các hàng trong bảng "donhang" có liên kết với ID của hàng muốn xóa trong bảng "sanpham"
			            pst = con.prepareStatement("DELETE FROM donhang WHERE ID_SanPham = ?");
			            pst.setInt(1, id);
			            pst.executeUpdate();

			            // Xóa hàng trong bảng "sanpham"
			            pst = con.prepareStatement("DELETE FROM sanpham WHERE ID = ?");
			            pst.setInt(1, id);
			            pst.executeUpdate();

			            JOptionPane.showMessageDialog(null, "Xóa thành công !!!");
			            table_load();
			            }	 
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			}
		});
		btnXoa.setForeground(new Color(255, 0, 0));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoa.setBounds(750, 492, 130, 52);
		contentPane.add(btnXoa);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					txtTenSP.setText(null);
					txtSoLuong.setText(null);
					txtGia.setText(null);
					txtMauSac.setText(null);
					txtMoTa.setText(null);
					txtDungTich.setText(null);					
				}
		});
		btnReset.setForeground(new Color(255, 0, 128));
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnReset.setBounds(890, 492, 130, 52);
		contentPane.add(btnReset);
		
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
		
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.BOLD, 14));
		table.setBackground(new Color(218, 221, 224));
		table.setBounds(455, 138, 721, 344);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					int row = table.getSelectedRow();
					DefaultTableModel model = (DefaultTableModel)table.getModel();				
					txtTenSP.setText(model.getValueAt(row, 1).toString());
					txtDungTich.setText(model.getValueAt(row, 4).toString());
					txtGia.setText(model.getValueAt(row, 5).toString());
					txtSoLuong.setText(model.getValueAt(row, 6).toString());
					txtMauSac.setText(model.getValueAt(row, 7).toString());
					txtMoTa.setText(model.getValueAt(row, 8).toString());
				}
			
		});
		
		contentPane.add(table);
		table_load();
		JButton btnThoat = new JButton("Back");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToHome();
			}
		});
		btnThoat.setForeground(new Color(0, 255, 255));
		btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThoat.setBounds(1076, 492, 100, 52);
		contentPane.add(btnThoat);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Màu sắc");
		lblNewLabel_1_6_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_6_1.setBounds(20, 437, 155, 40);
		contentPane.add(lblNewLabel_1_6_1);
		
		txtMauSac = new JTextField();
		txtMauSac.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtMauSac.setColumns(10);
		txtMauSac.setBounds(175, 437, 270, 40);
		contentPane.add(txtMauSac);
	}
}
