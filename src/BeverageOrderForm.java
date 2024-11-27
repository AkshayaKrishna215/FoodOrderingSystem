import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.*;


public class BeverageOrderForm extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel lblSys, lblSize, lblBev, lblGlass, lblReport;
    private JComboBox<String> size;
    private JRadioButton rdJuice, rdWater, rdTea, rdCoffee;
    private ButtonGroup btnGroup;
    private JTextField txtGlass;
    private JButton btnAdd, btnOrder;

    int amount_of;
    ArrayList<Beverage> list_of_bvr = new ArrayList<>();

    public BeverageOrderForm() {
        setLayout(null);
        setSize(1350, 920);
        setLocationRelativeTo(null);
        setTitle("Order");
        init();
        setupListeners();
        setVisible(true);
        
        // Background color
        getContentPane().setBackground(Color.CYAN);
        // background frame and layered pane for move to back
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        JLabel imageLabel = new JLabel(new ImageIcon("D:\\images\\bev.jpg"));
        imageLabel.setSize(getSize());
        JLabel Label = new JLabel(new ImageIcon("D:\\images\\wood.png"));
        Label.setBounds(300, 0, 800, 180);
        layeredPane.add(Label, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(lblSys, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(lblSize, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(size, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(lblBev, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(lblGlass, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(lblReport, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(rdJuice, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(rdWater, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(rdTea, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(rdCoffee, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(txtGlass, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(btnAdd, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(btnOrder, JLayeredPane.PALETTE_LAYER);
    }

    private void init() {
        lblSys = new JLabel("BEVERAGES ORDERING SYSTEM");
        lblSys.setBounds(410, 85, 800, 30);
        lblSys.setFont(new Font("Algerian", Font.PLAIN, 32));
        lblSys.setForeground(Color.BLACK);
        add(lblSys);

        lblSize = new JLabel("Select size:");
        lblSize.setSize(250, 50);
        lblSize.setLocation(500, 160);
        lblSize.setFont(new Font("Algerian", Font.PLAIN, 26));
        lblSize.setForeground(Color.CYAN);
        add(lblSize);

        String[] sizes = { "Small", "Medium", "Large" };
        size = new JComboBox<>(sizes);
        //JComboBox<String>size = new JComboBox<>(sizes);
        //size = new JComboBox<String>(sizes);
        size.setSelectedIndex(0);
        size.setSize(300, 35);
        size.setLocation(500, 200);
        size.setForeground(Color.blue);
        add(size);

        lblBev = new JLabel("Select Beverages:");// a JLabel that labels the radioButtons
        lblBev.setSize(500, 50);
        lblBev.setLocation(500, 255);
        lblBev.setFont(new Font("Algerian", Font.PLAIN, 26));
        lblBev.setForeground(Color.CYAN);
        add(lblBev);

        btnGroup = new ButtonGroup();

        rdJuice = new JRadioButton("Juice");
        rdJuice.setSize(75, 50);
        rdJuice.setLocation(500, 300);
        rdJuice.setForeground(Color.blue);

        add(rdJuice);

        rdWater = new JRadioButton("Water");
        rdWater.setSize(75, 50);
        rdWater.setLocation(575, 300);
        rdWater.setForeground(Color.blue);
        add(rdWater);

        rdTea = new JRadioButton("Tea");
        rdTea.setSize(75, 50);
        rdTea.setLocation(650, 300);
        rdTea.setForeground(Color.blue);
        add(rdTea);

        rdCoffee = new JRadioButton("Coffee");
        rdCoffee.setSize(75, 50);
        rdCoffee.setLocation(725, 300);
        rdCoffee.setForeground(Color.blue);
        add(rdCoffee);

        btnGroup.add(rdJuice);
        btnGroup.add(rdWater);
        btnGroup.add(rdTea);
        btnGroup.add(rdCoffee);

        lblGlass = new JLabel("No.of glasses:");// a JLabel that labels the Text Field
        lblGlass.setSize(500, 50);
        lblGlass.setLocation(500, 345);
        lblGlass.setFont(new Font("Algerian", Font.PLAIN, 26));
        lblGlass.setForeground(Color.CYAN);
        add(lblGlass);

        txtGlass = new JTextField();// a JTextField to get how many glasses of beverage is the user want
        txtGlass.setSize(300, 35);
        txtGlass.setLocation(500, 385);
        txtGlass.setForeground(Color.blue);
        add(txtGlass);

        btnAdd = new JButton("Add");
        btnAdd.setSize(120, 40);
        btnAdd.setLocation(500, 430);
        btnAdd.setForeground(Color.blue);
        add(btnAdd);

        btnOrder = new JButton("Order");
        btnOrder.setSize(120, 40);
        btnOrder.setLocation(680, 430);
        btnOrder.setEnabled(false);
        btnOrder.setForeground(Color.blue);
        add(btnOrder);

        lblReport = new JLabel();// will be showing report of the beverages that added.
        lblReport.setSize(500, 50);
        lblReport.setLocation(500, 480);
        lblReport.setFont(new Font("Arial", Font.PLAIN, 22));
        lblReport.setForeground(Color.blue);
        add(lblReport);
    }
    private void setupListeners() {
        btnAdd.addActionListener(this);
        btnOrder.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String size_of = (String) size.getSelectedItem();
        if (e.getSource().equals(btnAdd)) {
            if ((rdJuice.isSelected() || rdTea.isSelected() || rdCoffee.isSelected() || rdWater.isSelected())
                    && !(txtGlass.getText().isEmpty())) 
            {
                try {
                    amount_of = Integer.parseInt(txtGlass.getText().trim());
                    Beverage bvg;
                    if (rdJuice.isSelected()) {
                        bvg = new Juice(size_of, amount_of, this);
                    } else if (rdWater.isSelected()) {
                        bvg = new Water(size_of, amount_of, this);
                    } else if (rdTea.isSelected()) {
                        bvg = new Tea(size_of, amount_of, this);
                    } else {
                        bvg = new Coffee(size_of, amount_of, this);
                    }
                    txtGlass.setText(null);
                    list_of_bvr.add(bvg);
                    lblReport.setText(bvg.toString() + " added");
                    btnGroup.clearSelection();
                    btnOrder.setEnabled(true);
                } catch (NumberFormatException e1) {// if written data in TextField can't be converted to an integer[String,char,double etc...]
                    JOptionPane.showMessageDialog(this, "Enter an integer as amount");
                }
            } else 
            {
                JOptionPane.showMessageDialog(this, "Choose a beverage type and enter an amount");
                // if none of the radio buttons are selected or the textField is empty
            }
        }
        if (e.getSource().equals(btnOrder)) {
            String report = "";
            double pay = 0.0;
            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO orders (beverage_type, size, no_of_glasses, price_per_glass, total_amount) VALUES (?, ?, ?, ?, ?)")) {
                for (Beverage bvgi : list_of_bvr) {
                    String beverageType = bvgi.getClass().getSimpleName();
                    String sizes = bvgi.getSize();
                    int noOfGlasses = bvgi.getAmount();  // no_of_glasses field in the schema
                    double pricePerGlass = bvgi.getPrice();  // Assuming this is the price per glass for the beverage
                    double totalAmount = noOfGlasses * pricePerGlass;
        
                    report += beverageType + " (" + sizes + ") - " + noOfGlasses + " - " + totalAmount + " $\n";
                    pay += totalAmount;
        
                    stmt.setString(1, beverageType);
                    stmt.setString(2, sizes);
                    stmt.setInt(3, noOfGlasses);  // Insert into no_of_glasses column
                    stmt.setDouble(4, pricePerGlass); // Insert into price_per_glass column
                    stmt.setDouble(5, totalAmount); // Insert into total_amount column
                    stmt.addBatch();
                }
        
                stmt.executeBatch();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving order to database: " + ex.getMessage());
                return;
            }
        
            JOptionPane.showMessageDialog(this, report);
            JOptionPane.showMessageDialog(this, "You should pay " + pay + " $");
        
            // Display QR image
            ImageIcon qrIcon = new ImageIcon("D:\\images\\beverages.jpg");
            qrIcon.setImage(qrIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)); // Adjust the size as needed
            JOptionPane.showMessageDialog(this, "", "Scan to Pay", JOptionPane.INFORMATION_MESSAGE, qrIcon);
        
            lblReport.setText(null);
            btnOrder.setEnabled(false);
            list_of_bvr.clear();
        }
    }
        

    

    public static void main(String[] args) {
      
        SwingUtilities.invokeLater(() -> new BeverageOrderForm());
    }
    
    
        
            
}

