import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.RenderingHints;
import javax.swing.JComboBox;


public class barcode extends JFrame {
   
    private JTextField customerNameField;
    private JTextField motorTypeField;
    private JTextField plateNumberField;
    private JTextField priceField;
    private JComboBox<String> barcodeTypeCombo;
    private JPanel receiptPanel;
    private JButton printButton;
    private JLabel barcodeLabel;
    private BufferedImage barcodeImage;
    private String receiptId;
    
    // Receipt paper dimensions in points (1/72 inch)
    private static final double RECEIPT_WIDTH = 226.8;    // 80mm ~ 3.15 inches
    private static final double RECEIPT_HEIGHT = 600.0;   // Variable length
    private static final double MARGIN = 10.0;           // Small margins
    
    public barcode() {
        initComponents();
        setTitle("Fortune Wash");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(600, 600);
        setVisible(true);
    }

    /**
     * This method initializes all UI components
     */
    private void initComponents() {
        // Initialize components
        receiptPanel = new JPanel();
        customerNameField = new JTextField(20);
        motorTypeField = new JTextField(20);
        plateNumberField = new JTextField(20);
        priceField = new JTextField(20);
        
        // Add barcode type selection
        String[] barcodeTypes = {"Code 128", "Code 39", "EAN-13"};
        barcodeTypeCombo = new JComboBox<>(barcodeTypes);
        
        printButton = new JButton("Buat dan Cetak");
        barcodeLabel = new JLabel("Click 'Generate & Print' to create receipt");
        barcodeLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Set up the main panel with proper spacing
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Add components to the panel
        mainPanel.add(new JLabel("Nama Pelanggan:"));
        mainPanel.add(customerNameField);
        
        mainPanel.add(new JLabel("Tipe Kendaraan:"));
        mainPanel.add(motorTypeField);
        
        mainPanel.add(new JLabel("Plat Nomor:"));
        mainPanel.add(plateNumberField);
        
        mainPanel.add(new JLabel("Harga (Rp):"));
        mainPanel.add(priceField);
        
        mainPanel.add(new JLabel("Tipe Barcode:"));
        mainPanel.add(barcodeTypeCombo);
        
        mainPanel.add(new JLabel(""));
        mainPanel.add(printButton);
        
        // Configure receipt panel
        receiptPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Receipt Preview"));
        receiptPanel.setLayout(new BorderLayout(10, 10));
        receiptPanel.add(barcodeLabel, BorderLayout.CENTER);
        
        // Add action listener to print button with full implementation
        printButton.addActionListener(evt -> {
            try {
                // Validate input fields
                if (customerNameField.getText().isEmpty() || 
                    motorTypeField.getText().isEmpty() || 
                    plateNumberField.getText().isEmpty() || 
                    priceField.getText().isEmpty()) {
                    
                    JOptionPane.showMessageDialog(this, 
                        "Mohon di isi bagian yang kosong", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Generate receipt ID (timestamp)
                receiptId = generateReceiptId();
                
                // Generate barcode based on selected type
                String selectedType = (String) barcodeTypeCombo.getSelectedItem();
                // Create narrower barcode for receipt paper
                generateStandardBarcode(receiptId, selectedType);
                
                // Print the receipt
                printReceipt(receiptId);
                
                // Success message
                JOptionPane.showMessageDialog(this, 
                    "Cetak Struk Sukses!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Cetak struk error: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
        
        // Set up the main layout using a border layout for better organization
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(mainPanel, BorderLayout.NORTH);
        getContentPane().add(receiptPanel, BorderLayout.CENTER);
    }
    
    private String generateReceiptId() {
        // Generate a unique ID based on timestamp and plate number
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        String plateClean = plateNumberField.getText().replaceAll("\\s+", "");
        
        // Limit ID length to prevent barcode overlap
        if (plateClean.length() > 5) {
            plateClean = plateClean.substring(0, 5);
        }
        
        return "FW" + timestamp + plateClean;
    }
    
    private void generateStandardBarcode(String barcodeText, String barcodeType) {
        // Create a narrower barcode image for receipt paper
        int width = 200;  // Narrower width for receipt
        int height = 70;  // Shorter height
        barcodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = barcodeImage.createGraphics();
        
        // Enable anti-aliasing for better output quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fill background white
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        
        // Draw barcode bars based on selected type
        g2d.setColor(Color.BLACK);
        
        switch (barcodeType) {
            case "Code 128":
                generateCode128(g2d, barcodeText, width, height);
                break;
            case "Code 39":
                generateCode39(g2d, barcodeText, width, height);
                break;
            case "EAN-13":
                generateEAN13(g2d, barcodeText, width, height);
                break;
            default:
                generateCode128(g2d, barcodeText, width, height);
        }
        
        // Draw the text under the barcode with smaller font
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        
        // Improved text positioning to avoid overlap
        int textWidth = g2d.getFontMetrics().stringWidth(barcodeText);
        int textX = (width - textWidth) / 2;  // Center text
        g2d.drawString(barcodeText, textX, height - 8);
        
        g2d.dispose();
        
        // Display the barcode in the label
        barcodeLabel.setIcon(new ImageIcon(barcodeImage));
        barcodeLabel.setText("");
        
        // Force the receipt panel to repaint and validate
        receiptPanel.revalidate();
        receiptPanel.repaint();
    }
    
    /**
     * Code 128 is a high-density linear barcode that can encode the entire ASCII character set
     * This implementation uses a simplified approach with proper spacing
     */
    private void generateCode128(Graphics2D g2d, String text, int width, int height) {
        int barHeight = height - 20;  // Increased space for text
        int x = 10;  // Starting position
        int moduleWidth = 1;  // Width of the narrowest bar (smaller for receipt)
        
        // Draw the Code 128 barcode with proper spacing
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int value = c;
            
            // Draw 6 modules per character (3 bars, 3 spaces)
            for (int j = 0; j < 6; j++) {
                int barWidth = ((value + j) % 4) + 1;  // 1-4 units wide
                
                if (j % 2 == 0) {  // Draw bars
                    g2d.fillRect(x, 5, barWidth * moduleWidth, barHeight);
                }
                
                x += barWidth * moduleWidth;
            }
            
            // Add inter-character gap
            x += moduleWidth;
        }
    }
    
    /**
     * Code 39 is a discrete barcode that can encode uppercase letters, numbers and some special characters
     * This implementation ensures proper spacing between characters
     */
    private void generateCode39(Graphics2D g2d, String text, int width, int height) {
        int barHeight = height - 20;  // Increased space for text
        int x = 10;  // Starting position
        int narrowWidth = 1;  // Width of narrow bars (smaller for receipt)
        int wideWidth = 2;    // Width of wide bars (smaller for receipt)
        
        // Draw start character '*'
        x = drawCode39Char(g2d, '*', x, narrowWidth, wideWidth, barHeight);
        
        // Draw each character
        for (char c : text.toCharArray()) {
            // Force uppercase for Code 39
            c = Character.toUpperCase(c);
            x = drawCode39Char(g2d, c, x, narrowWidth, wideWidth, barHeight);
        }
        
        // Draw stop character '*'
        drawCode39Char(g2d, '*', x, narrowWidth, wideWidth, barHeight);
    }
    
    private int drawCode39Char(Graphics2D g2d, char c, int x, int narrowWidth, int wideWidth, int barHeight) {
        // Add gap between characters
        x += narrowWidth;
        
        // Generate a pattern based on the character - simplified version
        boolean[] pattern = new boolean[9];
        int value = c;
        
        for (int i = 0; i < 9; i++) {
            pattern[i] = ((value + i) % 3 == 0);
        }
        
        // Draw the pattern
        for (int i = 0; i < 9; i++) {
            int width = pattern[i] ? wideWidth : narrowWidth;
            
            if (i % 2 == 0) {  // Draw bars
                g2d.fillRect(x, 5, width, barHeight);
            }
            
            x += width;
        }
        
        // Add gap between characters
        x += narrowWidth;
        
        return x;
    }
    
    /**
     * EAN-13 is a numeric-only barcode used worldwide for marking retail goods
     * This implementation ensures proper spacing and structure of the barcode
     */
    private void generateEAN13(Graphics2D g2d, String text, int width, int height) {
        int barHeight = height - 20;  // Increased space for text
        int x = 10;  // Starting position
        int moduleWidth = 1;  // Width of the narrowest bar (smaller for receipt)
        
        // EAN-13 requires exactly 12 digits (13th is a check digit)
        String normalizedText = normalizeForEAN13(text);
        
        // Draw left guard pattern
        g2d.fillRect(x, 5, moduleWidth, barHeight + 5);  // Longer bar
        x += moduleWidth * 2;
        g2d.fillRect(x, 5, moduleWidth, barHeight + 5);  // Longer bar
        x += moduleWidth * 2;
        
        // Draw left data section (6 digits)
        for (int i = 0; i < 6; i++) {
            int digit = Character.getNumericValue(normalizedText.charAt(i));
            x = drawEAN13Digit(g2d, digit, x, moduleWidth, barHeight, false);
            x += moduleWidth;  // Space between digits
        }
        
        // Draw center guard pattern
        x += moduleWidth;
        g2d.fillRect(x, 5, moduleWidth, barHeight + 5);  // Longer bar
        x += moduleWidth * 2;
        g2d.fillRect(x, 5, moduleWidth, barHeight + 5);  // Longer bar
        x += moduleWidth * 2;
        
        // Draw right data section (6 digits) 
        for (int i = 6; i < 12; i++) {
            int digit = Character.getNumericValue(normalizedText.charAt(i));
            x = drawEAN13Digit(g2d, digit, x, moduleWidth, barHeight, true);
            x += moduleWidth;  // Space between digits
        }
        
        // Draw right guard pattern
        x += moduleWidth;
        g2d.fillRect(x, 5, moduleWidth, barHeight + 5);  // Longer bar
        x += moduleWidth * 2;
        g2d.fillRect(x, 5, moduleWidth, barHeight + 5);  // Longer bar
    }
    
    private String normalizeForEAN13(String text) {
        // Extract only digits
        StringBuilder digits = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c) && digits.length() < 12) {
                digits.append(c);
            }
        }
        
        // Pad with zeros if needed
        while (digits.length() < 12) {
            digits.append('0');
        }
        
        return digits.toString();
    }
    
    private int drawEAN13Digit(Graphics2D g2d, int digit, int x, int moduleWidth, int barHeight, boolean rightSide) {
        // Simplified pattern for demonstration
        boolean[][] patterns = {
            {false, false, false, true, true, false, true},  // 0
            {false, false, true, true, false, false, true},  // 1
            {false, false, true, false, false, true, true},  // 2
            {false, true, true, true, true, false, true},   // 3
            {false, true, false, false, false, true, true},  // 4
            {false, true, true, false, false, false, true},  // 5
            {false, true, false, true, true, true, true},   // 6
            {false, true, true, true, false, true, true},   // 7
            {false, true, true, false, true, true, true},   // 8
            {false, false, false, true, false, true, true}   // 9
        };
        
        boolean[] pattern = patterns[digit];
        
        // Invert pattern for right side
        if (rightSide) {
            for (int i = 0; i < pattern.length; i++) {
                pattern[i] = !pattern[i];
            }
        }
        
        // Draw the pattern
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i]) {
                g2d.fillRect(x, 5, moduleWidth, barHeight);
            }
            x += moduleWidth;
        }
        
        return x;
    }
    
    private void printReceipt(String receiptId) {
        PrinterJob job = PrinterJob.getPrinterJob();
        
        // Create a custom page format for thermal receipt printer (80mm width)
        PageFormat pageFormat = job.defaultPage();
        Paper paper = new Paper();
        
        // Set paper size to 80mm width (226.8 points) with variable length
        paper.setSize(RECEIPT_WIDTH, RECEIPT_HEIGHT);
        paper.setImageableArea(MARGIN, MARGIN, RECEIPT_WIDTH - (2 * MARGIN), RECEIPT_HEIGHT - (2 * MARGIN));
        
        pageFormat.setPaper(paper);
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        
        // Set the custom page format
        job.setPrintable((Graphics graphics, PageFormat pf, int pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }
            
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            
            // Enable anti-aliasing for better output quality
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            // Get current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String currentDate = dateFormat.format(new Date());
            
            // Draw receipt content - reduced font sizes and spacing for thermal receipt
            int y = 10;
            int lineHeight = 15;  // Reduced line height for receipt
            double contentWidth = pf.getImageableWidth();
            
            // Header
            g2d.setFont(new Font("Arial", Font.BOLD, 12));  // Smaller font
            String header = "STRUK FORTUNE WASH";
            int headerWidth = g2d.getFontMetrics().stringWidth(header);
            g2d.drawString(header, (int)(contentWidth / 2) - (headerWidth / 2), y);
            y += lineHeight * 1.5;
            
            // Shop info
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));  // Smaller font
            String shopName = "FORTUNE WASH";
            int shopNameWidth = g2d.getFontMetrics().stringWidth(shopName);
            g2d.drawString(shopName, (int)(contentWidth / 2) - (shopNameWidth / 2), y);
            y += lineHeight;
            
            String address = "Jl. Budi Utomo, Prambon, Nganjuk";
            int addressWidth = g2d.getFontMetrics().stringWidth(address);
            g2d.drawString(address, (int)(contentWidth / 2) - (addressWidth / 2), y);
            y += lineHeight;
            
            String tel = "Tel: 0856-4888-8656";
            int telWidth = g2d.getFontMetrics().stringWidth(tel);
            g2d.drawString(tel, (int)(contentWidth / 2) - (telWidth / 2), y);
            y += lineHeight * 1.5;
            
            // Receipt Info - Left aligned for better readability on narrow paper
            g2d.drawString("ID: " + receiptId, 10, y);
            y += lineHeight;
            g2d.drawString("TGL: " + currentDate, 10, y);
            y += lineHeight * 1.5;
            
            // Customer Info
            g2d.drawString("Pelanggan: " + customerNameField.getText(), 10, y);
            y += lineHeight;
            g2d.drawString("Kendaraan: " + motorTypeField.getText(), 10, y);
            y += lineHeight;
            g2d.drawString("Plat No: " + plateNumberField.getText(), 10, y);
            y += lineHeight * 1.5;
            
            // Separator line
            g2d.drawLine(10, y - 5, (int)contentWidth - 10, y - 5);
            
            // Service details
            g2d.drawString("DETAIL LAYANAN:", 10, y);
            y += lineHeight;
            g2d.drawString("- Cuci Biasa", 15, y);
            y += lineHeight;
            g2d.drawString("- Cuci Steam", 15, y);
            y += lineHeight;
            g2d.drawString("- Waxing dan Poles", 15, y);
            y += lineHeight * 1.5;
            
            // Separator line
            g2d.drawLine(10, y - 5, (int)contentWidth - 10, y - 5);
            
            // Payment details
            g2d.setFont(new Font("Arial", Font.BOLD, 10));
            g2d.drawString("HARGA: Rp " + priceField.getText(), 10, y);
            y += lineHeight;
            g2d.drawString("Status: PENDING", 10, y);
            y += lineHeight * 1.5;
            
            // Draw barcode - centered
            if (barcodeImage != null) {
                // Scale barcode if needed for receipt paper
                double scale = Math.min(1.0, (contentWidth - 20) / barcodeImage.getWidth());
                int scaledWidth = (int)(barcodeImage.getWidth() * scale);
                int scaledHeight = (int)(barcodeImage.getHeight() * scale);
                int barcodeX = (int)(contentWidth / 2) - (scaledWidth / 2);
                
                g2d.drawImage(barcodeImage, barcodeX, y, scaledWidth, scaledHeight, null);
            }
            
            // Footer
            y += 80; // Space for barcode
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            
            // Separator line
            g2d.drawLine(10, y - 5, (int)contentWidth - 10, y - 5);
            
            // Center the footer text
            String footer = "Terima kasih telah mempercayai kami!";
            int footerWidth = g2d.getFontMetrics().stringWidth(footer);
            g2d.drawString(footer, (int)(contentWidth / 2) - (footerWidth / 2), y);
            
            y += lineHeight;
            String note = "Tolong jaga struk ini untuk pembayaran.";
            int noteWidth = g2d.getFontMetrics().stringWidth(note);
            g2d.drawString(note, (int)(contentWidth / 2) - (noteWidth / 2), y);
            
            return Printable.PAGE_EXISTS;
        }, pageFormat);
        
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, 
                    "Cetak gagal: " + e.getMessage(), 
                    "Cetak Gagal", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Main method - application entry point
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Set the Nimbus look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Create and display the form using SwingUtilities for proper EDT handling
        SwingUtilities.invokeLater(() -> {
            new barcode();
        });
    }
}