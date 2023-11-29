package BaoCao;
import java.text.ParseException;
import java.awt.EventQueue;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.PlainDocument;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class NhanSu extends JFrame {

	private JPanel contentPane;
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtNgaySinh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanSu frame = new NhanSu();
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
			pst = con.prepareStatement("select * from nhansu");
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
	public NhanSu() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÍ NHÂN VIÊN");
		lblNewLabel.setForeground(new Color(255, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 10, 1172, 66);
		contentPane.add(lblNewLabel);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtHoTen.setBounds(195, 86, 280, 36);
		contentPane.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Họ tên nhân viên");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(20, 86, 140, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(20, 142, 140, 36);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Số điện thoại");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(20, 199, 140, 36);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Email");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(20, 262, 140, 36);
		contentPane.add(lblNewLabel_1_3);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(195, 142, 280, 36);
		contentPane.add(txtDiaChi);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.BOLD, 16));
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
		txtSDT.setColumns(10);
		txtSDT.setBounds(195, 199, 280, 36);
		contentPane.add(txtSDT);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(195, 262, 280, 36);
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Ngày Sinh");
		lblNewLabel_1_3_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_3_1.setBounds(20, 320, 140, 36);
		contentPane.add(lblNewLabel_1_3_1);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtNgaySinh.setColumns(10);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JFormattedTextField txtNgaySinh = new JFormattedTextField(dateFormat);
        txtNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtNgaySinh.setBounds(195, 320, 280, 36);
		contentPane.add(txtNgaySinh);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Chức vụ");
		lblNewLabel_1_3_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_3_1_1.setBounds(20, 379, 140, 36);
		contentPane.add(lblNewLabel_1_3_1_1);
		
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

		JComboBox comboBoxChucVu = new JComboBox();
		comboBoxChucVu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		comboBoxChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Giám đốc", "Trưởng phòng", "Quản lý", "Bảo vệ"}));
		comboBoxChucVu.setBounds(195, 379, 280, 36);
		contentPane.add(comboBoxChucVu);
		
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String HoTen = txtHoTen.getText();
		        String SDT = txtSDT.getText();
		        String Email = txtEmail.getText();
		        String DiaChi = txtDiaChi.getText();
		        String ngaySinh = txtNgaySinh.getText();
		        String chucVu = (String) comboBoxChucVu.getSelectedItem();

		        if (HoTen.isEmpty() || SDT.isEmpty() || Email.isEmpty() || DiaChi.isEmpty() || ngaySinh.isEmpty() || chucVu.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
		        } else {
		            try {
		                pst = con.prepareStatement("SELECT * FROM NhanSu WHERE HoTen=? AND SDT=? AND Email=? AND DiaChi=?");
		                pst.setString(1, HoTen);
		                pst.setString(2, SDT);
		                pst.setString(3, Email);
		                pst.setString(4, DiaChi);
		                ResultSet rs = pst.executeQuery();
		                if (rs.next()) {
		                    JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại!");
		                } else {
		                    pst = con.prepareStatement("INSERT INTO NhanSu(HoTen,DiaChi,SDT,Email,NgaySinh,ChucVu) VALUES(?,?,?,?,?,?)");
		                    pst.setString(1, HoTen);
		                    pst.setString(2, DiaChi);
		                    pst.setString(3, SDT);
		                    pst.setString(4, Email);
		                    pst.setString(5, ngaySinh);
		                    pst.setString(6, chucVu);
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
		btnThem.setForeground(new Color(0, 255, 64));
		btnThem.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		btnThem.setBounds(20, 466, 190, 49);
		contentPane.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            int row = table.getSelectedRow();
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            
		            if (row < 0) {
		                JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để cập nhật");
		            } else {
		            int id = (int) model.getValueAt(row, 0);
		            String hoTen = txtHoTen.getText().trim();
		            String diaChi = txtDiaChi.getText().trim();
		            String sdt = txtSDT.getText().trim();
		            String email = txtEmail.getText().trim();
		            String ngaySinh = txtNgaySinh.getText().trim();
		            String chucVu = (String) comboBoxChucVu.getSelectedItem();
		         
		            // Kiểm tra nếu có JTextField rỗng
		            if (hoTen.isEmpty() || diaChi.isEmpty() || sdt.isEmpty() || email.isEmpty() || ngaySinh.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
		            } else {
		                pst = con.prepareStatement("update nhansu set HoTen=?, DiaChi=?, SDT=?, Email=?, NgaySinh=?, ChucVu=? where id=?");
		                pst.setString(1, hoTen);
		                pst.setString(2, diaChi);
		                pst.setString(3, sdt);
		                pst.setString(4, email);
		                pst.setString(5, ngaySinh);
		                pst.setString(6, chucVu);
		                pst.setInt(7, id);
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
		btnSua.setForeground(new Color(255, 0, 128));
		btnSua.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		btnSua.setBounds(220, 466, 190, 49);
		contentPane.add(btnSua);
		

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.BOLD, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

					int row = table.getSelectedRow();
					if (row >= 0) {			  
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					int id = (int) model.getValueAt(row, 0);
					txtHoTen.setText(model.getValueAt(row, 1).toString());					
					txtDiaChi.setText(model.getValueAt(row, 2).toString());
					txtSDT.setText(model.getValueAt(row, 3).toString());
					txtEmail.setText(model.getValueAt(row, 4).toString());
					txtNgaySinh.setText(model.getValueAt(row, 5).toString());
//					comboBoxChucVu.getSelectedItem();
					}
				}
			
		});
		table.setBounds(503, 86, 679, 328);
		contentPane.add(table);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int row = table.getSelectedRow();
		            DefaultTableModel model = (DefaultTableModel) table.getModel();		          
		            if (row < 0) {
		                JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để xóa");
		            } else {
		            int id = (int) model.getValueAt(row, 0);
		            // Xóa các hàng liên quan trong bảng DonHang trước
		            pst = con.prepareStatement("DELETE FROM DonHang WHERE ID_NhanSu = ?");
		            pst.setInt(1, id);
		            pst.executeUpdate();
		            // Sau đó xóa hàng trong bảng nhansu
		            pst = con.prepareStatement("DELETE FROM nhansu WHERE ID = ?");
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
		btnXoa.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		btnXoa.setBounds(420, 466, 190, 49);
		contentPane.add(btnXoa);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					txtHoTen.setText(null);
					txtDiaChi.setText(null);
					txtSDT.setText(null);
					txtEmail.setText(null);
					txtNgaySinh.setText(null);
//					JOptionPane.showMessageDialog(null, "Reset thành công !!!");
					
				}
			
		});
		btnReset.setForeground(new Color(255, 255, 0));
		btnReset.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		btnReset.setBounds(620, 466, 190, 49);
		contentPane.add(btnReset);
		
		table_load();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToHome();
			}
		});
		btnBack.setForeground(new Color(0, 64, 64));
		btnBack.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		btnBack.setBounds(986, 466, 190, 49);
		contentPane.add(btnBack);
	}
}
