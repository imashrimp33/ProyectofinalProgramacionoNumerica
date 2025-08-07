
package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Invoice {
    // Componentes 
    private JFrame frame;
    private JPanel backgroundPanel, clientPanel,sidebarPanel, profilePanel,productPanel,invoicePanel,totalsPanel;
    private JLabel label1,profileLabel,titleLabel,clientTitleLabel,clientNameLabel,clientEmailLabel,clientPhoneLabel;
    private JButton btn1, btn2, btn3, btn4,generateInvoiceButton,clearButton,addProductButton;
    private JLabel clientAddressLabel,productTitleLabel,productNameLabel,quantityLabel,priceLabel,invoiceLabel;
    private JTextField clientNameField, clientEmailField,clientPhoneField,clientAddressField,productNameField,priceField;
    private JSpinner quantitySpinner;
    private JTable invoiceTable;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;
    private JLabel subtotalLabel,subtotalValue,taxLabel,taxValue,totalLabel,totalValue;
    
    // Variables para cálculos
    private double subtotal = 0.0;
    private double taxRate = 0.16; // 16% IVA
    private DecimalFormat df = new DecimalFormat("#,##0.00");
    
    // Datos de la tabla
    private String[] columnNames = {"Producto", "Cantidad", "Precio Unit.", "Total"};
    
    public void iniciar() {
        // Marco principal
        frame = new JFrame("Sistema de Facturación");
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        // Panel de fondo blanco
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setLayout(null);
        
        // Panel lateral con gradiente para los botones
        sidebarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Gradiente azul en el panel lateral
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(74, 144, 226),
                    0, getHeight(), new Color(143, 148, 251)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        sidebarPanel.setBounds(0, 0, 220, 700);
        sidebarPanel.setLayout(null);
        backgroundPanel.add(sidebarPanel);
        
        // Título de bienvenida (ahora en fondo blanco)
        label1 = new JLabel("Sistema de Facturación");
        label1.setBounds(240, 20, 740, 40);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        label1.setForeground(new Color(45, 55, 72));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        backgroundPanel.add(label1);
        
        // Subtítulo
        titleLabel = new JLabel("Genera facturas para productos comprados por clientes");
        titleLabel.setBounds(240, 60, 740, 20);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(107, 114, 128));
        backgroundPanel.add(titleLabel);
        
        // Panel para foto de perfil (ahora con fondo gris claro)
        profilePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo circular gris claro
                g2d.setColor(new Color(248, 250, 252));
                g2d.fillOval(5, 5, getWidth()-10, getHeight()-10);
                
                // Borde gris
                g2d.setColor(new Color(203, 213, 225));
                g2d.setStroke(new BasicStroke(3));
                g2d.drawOval(5, 5, getWidth()-10, getHeight()-10);
                
                g2d.dispose();
            }
        };
        profilePanel.setBounds(75, 120, 70, 70);
        profilePanel.setOpaque(false);
        sidebarPanel.add(profilePanel);
        
        // Etiqueta para la foto de perfil
        profileLabel = new JLabel("👤");
        profileLabel.setBounds(95, 140, 30, 30);
        profileLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sidebarPanel.add(profileLabel);
        
        // Panel de datos del cliente
        clientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo blanco con borde gris claro
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Borde gris claro
                g2d.setColor(new Color(229, 231, 235));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                
                g2d.dispose();
            }
        };
        clientPanel.setBounds(240, 100, 360, 160);
        clientPanel.setLayout(null);
        clientPanel.setOpaque(false);
        backgroundPanel.add(clientPanel);
        
        // Título del panel de cliente
        clientTitleLabel = new JLabel("👤 Datos del Cliente");
        clientTitleLabel.setBounds(20, 15, 200, 25);
        clientTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        clientTitleLabel.setForeground(new Color(45, 55, 72));
        clientPanel.add(clientTitleLabel);
        
        // Campos del cliente
        clientNameLabel = new JLabel("Nombre completo");
        clientNameLabel.setBounds(20, 45, 150, 20);
        clientNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientNameLabel.setForeground(new Color(45, 55, 72));
        clientPanel.add(clientNameLabel);
        
        clientNameField = new JTextField();
        clientNameField.setBounds(20, 65, 150, 30);
        clientNameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        clientPanel.add(clientNameField);
        
        clientEmailLabel = new JLabel("Email");
        clientEmailLabel.setBounds(190, 45, 150, 20);
        clientEmailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientEmailLabel.setForeground(new Color(45, 55, 72));
        clientPanel.add(clientEmailLabel);
        
        clientEmailField = new JTextField();
        clientEmailField.setBounds(190, 65, 150, 30);
        clientEmailField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientEmailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        clientPanel.add(clientEmailField);
        
        clientPhoneLabel = new JLabel("Teléfono");
        clientPhoneLabel.setBounds(20, 105, 150, 20);
        clientPhoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientPhoneLabel.setForeground(new Color(45, 55, 72));
        clientPanel.add(clientPhoneLabel);
        
        clientPhoneField = new JTextField();
        clientPhoneField.setBounds(20, 125, 150, 30);
        clientPhoneField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientPhoneField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        clientPanel.add(clientPhoneField);
        
        clientAddressLabel = new JLabel("Dirección");
        clientAddressLabel.setBounds(190, 105, 150, 20);
        clientAddressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientAddressLabel.setForeground(new Color(45, 55, 72));
        clientPanel.add(clientAddressLabel);
        
        clientAddressField = new JTextField();
        clientAddressField.setBounds(190, 125, 150, 30);
        clientAddressField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientAddressField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        clientPanel.add(clientAddressField);
        
        // Panel de productos
        productPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo blanco con borde gris claro
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Borde gris claro
                g2d.setColor(new Color(229, 231, 235));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                
                g2d.dispose();
            }
        };
        productPanel.setBounds(620, 100, 360, 160);
        productPanel.setLayout(null);
        productPanel.setOpaque(false);
        backgroundPanel.add(productPanel);
        
        // Título del panel de productos
        productTitleLabel = new JLabel("🛍️ Agregar Producto");
        productTitleLabel.setBounds(20, 15, 200, 25);
        productTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        productTitleLabel.setForeground(new Color(45, 55, 72));
        productPanel.add(productTitleLabel);
        
        // Campos del producto
        productNameLabel = new JLabel("Nombre del producto");
        productNameLabel.setBounds(20, 45, 150, 20);
        productNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productNameLabel.setForeground(new Color(45, 55, 72));
        productPanel.add(productNameLabel);
        
        productNameField = new JTextField();
        productNameField.setBounds(20, 65, 320, 30);
        productNameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        productPanel.add(productNameField);
        
        quantityLabel = new JLabel("Cantidad");
        quantityLabel.setBounds(20, 105, 100, 20);
        quantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        quantityLabel.setForeground(new Color(45, 55, 72));
        productPanel.add(quantityLabel);
        
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        quantitySpinner.setBounds(20, 125, 80, 30);
        quantitySpinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productPanel.add(quantitySpinner);
        
        priceLabel = new JLabel("Precio unitario");
        priceLabel.setBounds(120, 105, 100, 20);
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        priceLabel.setForeground(new Color(45, 55, 72));
        productPanel.add(priceLabel);
        
        priceField = new JTextField();
        priceField.setBounds(120, 125, 100, 30);
        priceField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        priceField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        productPanel.add(priceField);
        
        // Botón agregar producto
        addProductButton = new JButton("➕ Agregar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente verde
                if (getModel().isPressed()) {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(34, 197, 94),
                        0, getHeight(), new Color(22, 163, 74)
                    );
                    g2d.setPaint(gradient);
                } else if (getModel().isRollover()) {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(54, 217, 114),
                        0, getHeight(), new Color(42, 183, 94)
                    );
                    g2d.setPaint(gradient);
                } else {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(34, 197, 94),
                        0, getHeight(), new Color(22, 163, 74)
                    );
                    g2d.setPaint(gradient);
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Texto del botón
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 2;
                g2d.drawString(getText(), x, y);
                
                g2d.dispose();
            }
        };
        addProductButton.setBounds(240, 125, 100, 30);
        addProductButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addProductButton.setContentAreaFilled(false);
        addProductButton.setBorderPainted(false);
        addProductButton.setFocusPainted(false);
        addProductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        productPanel.add(addProductButton);
        
        // Panel de factura
        invoicePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo blanco con borde gris claro
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Borde gris claro
                g2d.setColor(new Color(229, 231, 235));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                
                g2d.dispose();
            }
        };
        invoicePanel.setBounds(240, 280, 740, 280);
        invoicePanel.setLayout(null);
        invoicePanel.setOpaque(false);
        backgroundPanel.add(invoicePanel);
        
        // Header del panel de factura
        invoiceLabel = new JLabel("🧾 Factura");
        invoiceLabel.setBounds(20, 15, 200, 25);
        invoiceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        invoiceLabel.setForeground(new Color(45, 55, 72));
        invoicePanel.add(invoiceLabel);
        
        // Tabla de productos
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        invoiceTable = new JTable(tableModel);
        invoiceTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        invoiceTable.setRowHeight(30);
        invoiceTable.setGridColor(new Color(243, 244, 246));
        invoiceTable.setSelectionBackground(new Color(239, 246, 255));
        invoiceTable.setSelectionForeground(new Color(45, 55, 72));
        
        // Header de la tabla
        invoiceTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        invoiceTable.getTableHeader().setBackground(new Color(249, 250, 251));
        invoiceTable.getTableHeader().setForeground(new Color(45, 55, 72));
        invoiceTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(229, 231, 235)));
        
        tableScrollPane = new JScrollPane(invoiceTable);
        tableScrollPane.setBounds(20, 50, 500, 180);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.getViewport().setBackground(Color.WHITE);
        invoicePanel.add(tableScrollPane);
        
        // Panel de totales
        totalsPanel = new JPanel();
        totalsPanel.setBounds(540, 50, 180, 180);
        totalsPanel.setLayout(null);
        totalsPanel.setBackground(new Color(249, 250, 251));
        totalsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        invoicePanel.add(totalsPanel);
        
        subtotalLabel = new JLabel("Subtotal:");
        subtotalLabel.setBounds(10, 20, 80, 20);
        subtotalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtotalLabel.setForeground(new Color(45, 55, 72));
        totalsPanel.add(subtotalLabel);
        
        subtotalValue = new JLabel("$0.00");
        subtotalValue.setBounds(100, 20, 70, 20);
        subtotalValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        subtotalValue.setForeground(new Color(45, 55, 72));
        subtotalValue.setHorizontalAlignment(SwingConstants.RIGHT);
        totalsPanel.add(subtotalValue);
        
        taxLabel = new JLabel("IVA (16%):");
        taxLabel.setBounds(10, 50, 80, 20);
        taxLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        taxLabel.setForeground(new Color(45, 55, 72));
        totalsPanel.add(taxLabel);
        
        taxValue = new JLabel("$0.00");
        taxValue.setBounds(100, 50, 70, 20);
        taxValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        taxValue.setForeground(new Color(45, 55, 72));
        taxValue.setHorizontalAlignment(SwingConstants.RIGHT);
        totalsPanel.add(taxValue);
        
        // Línea separadora
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 80, 160, 1);
        separator.setForeground(new Color(229, 231, 235));
        totalsPanel.add(separator);
        
        totalLabel = new JLabel("Total:");
        totalLabel.setBounds(10, 100, 80, 25);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setForeground(new Color(45, 55, 72));
        totalsPanel.add(totalLabel);
        
        totalValue = new JLabel("$0.00");
        totalValue.setBounds(100, 100, 70, 25);
        totalValue.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalValue.setForeground(new Color(34, 197, 94));
        totalValue.setHorizontalAlignment(SwingConstants.RIGHT);
        totalsPanel.add(totalValue);
        
        // Botones de acción
        generateInvoiceButton = new JButton("📄 Generar Factura") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente azul
                if (getModel().isPressed()) {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(64, 134, 216),
                        0, getHeight(), new Color(133, 138, 241)
                    );
                    g2d.setPaint(gradient);
                } else if (getModel().isRollover()) {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(84, 154, 236),
                        0, getHeight(), new Color(153, 158, 255)
                    );
                    g2d.setPaint(gradient);
                } else {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(74, 144, 226),
                        0, getHeight(), new Color(143, 148, 251)
                    );
                    g2d.setPaint(gradient);
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Texto del botón
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 2;
                g2d.drawString(getText(), x, y);
                
                g2d.dispose();
            }
        };
        generateInvoiceButton.setBounds(240, 580, 150, 40);
        generateInvoiceButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        generateInvoiceButton.setContentAreaFilled(false);
        generateInvoiceButton.setBorderPainted(false);
        generateInvoiceButton.setFocusPainted(false);
        generateInvoiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backgroundPanel.add(generateInvoiceButton);
        
        clearButton = new JButton("🗑️ Limpiar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo rojo
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(220, 38, 38));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(239, 68, 68));
                } else {
                    g2d.setColor(new Color(239, 68, 68));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Texto del botón
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 2;
                g2d.drawString(getText(), x, y);
                
                g2d.dispose();
            }
        };
        clearButton.setBounds(410, 580, 100, 40);
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.setContentAreaFilled(false);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backgroundPanel.add(clearButton);
        
        // Botones dentro del panel lateral
        btn1 = new JButton("Facturar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Botón con fondo blanco semi-transparente
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(255, 255, 255, 200));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(255, 255, 255, 180));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 150));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Borde sutil
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                
                // Texto del botón
                g2d.setColor(new Color(45, 55, 72));
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 2;
                g2d.drawString(getText(), x, y);
                
                g2d.dispose();
            }
        };
        btn1.setBounds(20, 220, 180, 45);
        btn1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn1.setContentAreaFilled(false);
        btn1.setBorderPainted(false);
        btn1.setFocusPainted(false);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebarPanel.add(btn1);
        
        btn2 = new JButton("Almacenar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(255, 255, 255, 200));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(255, 255, 255, 180));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 150));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Borde sutil
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                
                // Texto del botón
                g2d.setColor(new Color(45, 55, 72));
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 2;
                g2d.drawString(getText(), x, y);
                
                g2d.dispose();
            }
        };
        btn2.setBounds(20, 280, 180, 45);
        btn2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn2.setContentAreaFilled(false);
        btn2.setBorderPainted(false);
        btn2.setFocusPainted(false);
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebarPanel.add(btn2);
        
        // Nuevo botón Historial
        btn3 = new JButton("Historial") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(255, 255, 255, 200));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(255, 255, 255, 180));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 150));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Borde sutil
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                
                // Texto del botón
                g2d.setColor(new Color(45, 55, 72));
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 2;
                g2d.drawString(getText(), x, y);
                
                g2d.dispose();
            }
        };
        btn3.setBounds(20, 340, 180, 45);
        btn3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn3.setContentAreaFilled(false);
        btn3.setBorderPainted(false);
        btn3.setFocusPainted(false);
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebarPanel.add(btn3);
        
        btn4 = new JButton("Buscar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Botón con fondo blanco semi-transparente
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(255, 255, 255, 200));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(255, 255, 255, 180));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 150));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Borde sutil
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                
                // Texto del botón
                g2d.setColor(new Color(45, 55, 72));
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 2;
                g2d.drawString(getText(), x, y);
                
                g2d.dispose();
            }
        };
        btn4.setBounds(20, 400, 180, 45);
        btn4.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn4.setContentAreaFilled(false);
        btn4.setBorderPainted(false);
        btn4.setFocusPainted(false);
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebarPanel.add(btn4);
        
        frame.add(backgroundPanel);
        frame.setVisible(true);
        
        // Acciones de los botones 
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ya estamos en facturación
                JOptionPane.showMessageDialog(frame, "Ya estás en la sección de facturación");
            }
        });
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                frame.dispose();
                Storage ventana = new Storage();
                ventana.iniciar();
            }
        });
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                frame.dispose();
                History ventana = new History();
                ventana.iniciar();
            }
        });
        
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                frame.dispose();
                Search ventana = new Search();
                ventana.iniciar();
            }
        });
        
        // Eventos adicionales para la funcionalidad de facturación
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        
        generateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateInvoice();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }
    
    private void addProduct() {
        String productName = productNameField.getText().trim();
        String priceText = priceField.getText().trim();
        
        if (productName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor complete todos los campos del producto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int quantity = (Integer) quantitySpinner.getValue();
            double price = Double.parseDouble(priceText);
            double total = quantity * price;
            
            // Agregar fila a la tabla
            Object[] row = {productName, quantity, "$" + df.format(price), "$" + df.format(total)};
            tableModel.addRow(row);
            
            // Actualizar totales
            subtotal += total;
            updateTotals();
            
            // Limpiar campos del producto
            productNameField.setText("");
            priceField.setText("");
            quantitySpinner.setValue(1);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "El precio debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateTotals() {
        double tax = subtotal * taxRate;
        double total = subtotal + tax;
        
        subtotalValue.setText("$" + df.format(subtotal));
        taxValue.setText("$" + df.format(tax));
        totalValue.setText("$" + df.format(total));
    }
    
    private void generateInvoice() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Agregue al menos un producto para generar la factura", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (clientNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese el nombre del cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Simular generación de factura
        String clientName = clientNameField.getText().trim();
        double total = subtotal + (subtotal * taxRate);
        
        String message = "Factura generada exitosamente!\n\n" +
                        "Cliente: " + clientName + "\n" +
                        "Total de productos: " + tableModel.getRowCount() + "\n" +
                        "Total a pagar: $" + df.format(total) + "\n\n" +
                        "La factura ha sido guardada en el sistema.";
        
        JOptionPane.showMessageDialog(frame, message, "Factura Generada", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearForm() {
        // Limpiar campos del cliente
        clientNameField.setText("");
        clientEmailField.setText("");
        clientPhoneField.setText("");
        clientAddressField.setText("");
        
        // Limpiar campos del producto
        productNameField.setText("");
        priceField.setText("");
        quantitySpinner.setValue(1);
        
        // Limpiar tabla
        tableModel.setRowCount(0);
        
        // Resetear totales
        subtotal = 0.0;
        updateTotals();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Invoice ventana = new Invoice();
            ventana.iniciar();
        });
    }
}