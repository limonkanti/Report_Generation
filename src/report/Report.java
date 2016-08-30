
package report;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.sf.jasperreports.view.*;

import net.sf.jasperreports.engine.*;

public class Report extends JFrame implements ActionListener {

    String colorlayout_ = "#607D8B";
    String colorlabel_ = "#263238";
    String colorpane_ = "#B0BEC5";
    //String colorWhite100_ = "#ffffff";
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    JLabel l_welcome = new JLabel("Welcome To Report Generation Using I-Report");
    JLabel l_reserve_book = new JLabel("Reserve Books");
    JButton b_reserve_book = new JButton("", new ImageIcon("image/kk.jpg"));

    //JPanel panel = new JPanel();
    JDesktopPane pane = new JDesktopPane();
    ImageIcon ic = new ImageIcon();
    Font menufnt = new Font("Times New Roman", Font.PLAIN, 35);
    Font lfnt = new Font("Times New Roman", Font.PLAIN, 23);
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    Report() {
        super("Generate Report Using I-Report");
        Container conn = this.getContentPane();
        conn.setLayout(new BorderLayout());
        conn.setBackground(Color.decode(colorlayout_));

        l_welcome.setHorizontalAlignment(SwingConstants.CENTER);
        l_welcome.setPreferredSize(new Dimension(80, 100));
        conn.add(l_welcome, BorderLayout.NORTH);
        setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
        setLocationRelativeTo(null);
        //pane.setLayout(null);
        conn.add(pane, BorderLayout.CENTER);
        pane.setBackground(Color.decode(colorpane_));
        pane.setLayout(null);
        add(pane);
        //pane.setBackground(Color.LIGHT_GRAY);
        setSize(400, 400);
        setVisible(true);
        //setLocation(400, 70);
        //setResizable(false);
        label();
        button();

    }

    void label() {

        l_reserve_book.setBounds(100, 10, 200, 60);
        l_reserve_book.setFont(lfnt);
        l_reserve_book.setForeground(Color.BLACK);
        pane.add(l_reserve_book);

    }

    void button() {

        b_reserve_book.setBounds(105, 80, 120, 70);
        b_reserve_book.addActionListener(this);
        pane.add(b_reserve_book);

    }

    public static void main(String[] args) {
        Report ob = new Report();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_reserve_book) {

            try {
                Class.forName("com.mysql.jdbc.Driver");//mysql

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "12345");
                String reportPath = "C:\\Users\\User\\a\\report1.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(reportPath);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JasperViewer.viewReport(jp);
                con.close();
            } catch (Exception e1) {
                System.out.println("Connection Error." + e1.toString());

            }

        }

    }

}
