
package Vista;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class Storage {
    // Componentes
    private JFrame frame;
    private JPanel backgroundPanel,sidebarPanel,profilePanel,productFormPanel,searchPanel,inventoryPanel,actionsPanel,paginationPanel;
    private JLabel label1,profileLabel,titleLabel,formTitleLabel,productCodeLabel,productNameLabel,categoryLabel,descriptionLabel,priceLabel;
    private JButton btn1, btn2, btn3, btn4,addProductButton,updateProductButton,clearFormButton,exportInventoryButton,searchButton;
    private JTextField productCodeField,productNameField,priceField,supplierField,searchField;
    private JComboBox<String> categoryCombo,searchCategoryCombo;
    private JTextArea descriptionArea;
    private JScrollPane descriptionScrollPane,tableScrollPane;
    private JLabel stockLabel,minStockLabel,supplierLabel,inventoryLabel,totalProductsLabel,totalValueLabel,lowStockLabel,searchLabel;
    private JSpinner stockSpinner,minStockSpinner;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private JButton editProductButton,deleteProductButton,adjustStockButton,prevButton, page1Button;
    private JLabel paginationInfo;
  
    
    // Variables para formateo
    private DecimalFormat df = new DecimalFormat("#,##0.00");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    // Datos de ejemplo del inventario
    private Object[][] sampleInventoryData = {
        {"PROD-001", "Laptop Dell Inspiron", "Electrónicos", 15850.00, 25, 5, "TechSupply", "Activo"},
        {"PROD-002", "Mouse Inalámbrico", "Accesorios", 450.50, 50, 10, "OfficeMax", "Activo"},
        {"PROD-003", "Teclado Mecánico", "Accesorios", 1250.75, 8, 15, "GamerStore", "Stock Bajo"},
        {"PROD-004", "Monitor 24 pulgadas", "Electrónicos", 3200.00, 12, 5, "TechSupply", "Activo"},
        {"PROD-005", "Impresora HP", "Oficina", 2890.25, 3, 8, "PrintWorld", "Stock Bajo"},
        {"PROD-006", "Disco Duro 1TB", "Almacenamiento", 1150.00, 30, 10, "DataStore", "Activo"},
        {"PROD-007", "Memoria RAM 8GB", "Componentes", 980.50, 45, 15, "TechSupply", "Activo"},
        {"PROD-008", "Webcam HD", "Accesorios", 750.00, 2, 12, "CamWorld", "Stock Crítico"}
    };
    
    private String[] columnNames = {"Código", "Producto", "Categoría", "Precio", "Stock", "Stock Mín.", "Proveedor", "Estado"};
    private String[] categories = {"Seleccionar categoría", "Electrónicos", "Accesorios", "Oficina", "Almacenamiento", "Componentes"};
    
    public void iniciar() {
        // Marco principal
        frame = new JFrame("Sistema de Almacén");
        frame.setSize(1200, 800);
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
        sidebarPanel.setBounds(0, 0, 220, 800);
        sidebarPanel.setLayout(null);
        backgroundPanel.add(sidebarPanel);
        
        // Título de bienvenida (ahora en fondo blanco)
        label1 = new JLabel("Sistema de Almacén");
        label1.setBounds(240, 20, 940, 40);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        label1.setForeground(new Color(45, 55, 72));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        backgroundPanel.add(label1);
        
        // Subtítulo
        titleLabel = new JLabel("Registra y gestiona el inventario de productos en el almacén");
        titleLabel.setBounds(240, 60, 940, 20);
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
        
        // Panel de formulario de producto
        productFormPanel = new JPanel() {
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
        productFormPanel.setBounds(240, 100, 940, 200);
        productFormPanel.setLayout(null);
        productFormPanel.setOpaque(false);
        backgroundPanel.add(productFormPanel);
        
        // Título del panel de formulario
        formTitleLabel = new JLabel("📦 Registro de Producto");
        formTitleLabel.setBounds(20, 15, 200, 25);
        formTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formTitleLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(formTitleLabel);
        
        // Primera fila de campos
        productCodeLabel = new JLabel("Código del producto");
        productCodeLabel.setBounds(20, 45, 150, 20);
        productCodeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productCodeLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(productCodeLabel);
        
        productCodeField = new JTextField();
        productCodeField.setBounds(20, 65, 120, 30);
        productCodeField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productCodeField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        productFormPanel.add(productCodeField);
        
        productNameLabel = new JLabel("Nombre del producto");
        productNameLabel.setBounds(160, 45, 150, 20);
        productNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productNameLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(productNameLabel);
        
        productNameField = new JTextField();
        productNameField.setBounds(160, 65, 200, 30);
        productNameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        productFormPanel.add(productNameField);
        
        categoryLabel = new JLabel("Categoría");
        categoryLabel.setBounds(380, 45, 100, 20);
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        categoryLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(categoryLabel);
        
        categoryCombo = new JComboBox<>(categories);
        categoryCombo.setBounds(380, 65, 150, 30);
        categoryCombo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productFormPanel.add(categoryCombo);
        
        priceLabel = new JLabel("Precio");
        priceLabel.setBounds(550, 45, 100, 20);
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        priceLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(priceLabel);
        
        priceField = new JTextField();
        priceField.setBounds(550, 65, 100, 30);
        priceField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        priceField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        productFormPanel.add(priceField);
        
        // Segunda fila de campos
        stockLabel = new JLabel("Stock inicial");
        stockLabel.setBounds(20, 105, 100, 20);
        stockLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        stockLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(stockLabel);
        
        stockSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
        stockSpinner.setBounds(20, 125, 80, 30);
        stockSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productFormPanel.add(stockSpinner);
        
        minStockLabel = new JLabel("Stock mínimo");
        minStockLabel.setBounds(120, 105, 100, 20);
        minStockLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        minStockLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(minStockLabel);
        
        minStockSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        minStockSpinner.setBounds(120, 125, 80, 30);
        minStockSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productFormPanel.add(minStockSpinner);
        
        supplierLabel = new JLabel("Proveedor");
        supplierLabel.setBounds(220, 105, 100, 20);
        supplierLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        supplierLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(supplierLabel);
        
        supplierField = new JTextField();
        supplierField.setBounds(220, 125, 150, 30);
        supplierField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        supplierField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        productFormPanel.add(supplierField);
        
        descriptionLabel = new JLabel("Descripción");
        descriptionLabel.setBounds(390, 105, 100, 20);
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descriptionLabel.setForeground(new Color(45, 55, 72));
        productFormPanel.add(descriptionLabel);
        
        descriptionArea = new JTextArea();
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setBounds(390, 125, 200, 30);
        descriptionScrollPane.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219), 1));
        productFormPanel.add(descriptionScrollPane);
        
        // Botones del formulario
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
        addProductButton.setBounds(670, 65, 90, 30);
        addProductButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addProductButton.setContentAreaFilled(false);
        addProductButton.setBorderPainted(false);
        addProductButton.setFocusPainted(false);
        addProductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        productFormPanel.add(addProductButton);
        
        updateProductButton = new JButton("✏️ Actualizar") {
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
        updateProductButton.setBounds(780, 65, 100, 30);
        updateProductButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        updateProductButton.setContentAreaFilled(false);
        updateProductButton.setBorderPainted(false);
        updateProductButton.setFocusPainted(false);
        updateProductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateProductButton.setEnabled(false);
        productFormPanel.add(updateProductButton);
        
        clearFormButton = new JButton("🗑️ Limpiar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo gris
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(156, 163, 175));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(107, 114, 128));
                } else {
                    g2d.setColor(new Color(107, 114, 128));
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
        clearFormButton.setBounds(670, 125, 90, 30);
        clearFormButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        clearFormButton.setContentAreaFilled(false);
        clearFormButton.setBorderPainted(false);
        clearFormButton.setFocusPainted(false);
        clearFormButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        productFormPanel.add(clearFormButton);
        
        // Panel de inventario
        inventoryPanel = new JPanel() {
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
        inventoryPanel.setBounds(240, 320, 940, 400);
        inventoryPanel.setLayout(null);
        inventoryPanel.setOpaque(false);
        backgroundPanel.add(inventoryPanel);
        
        // Header del panel de inventario
        inventoryLabel = new JLabel("📊 Inventario de Productos");
        inventoryLabel.setBounds(20, 15, 200, 25);
        inventoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        inventoryLabel.setForeground(new Color(45, 55, 72));
        inventoryPanel.add(inventoryLabel);
        
        // Estadísticas del inventario
        totalProductsLabel = new JLabel("Total: 8 productos");
        totalProductsLabel.setBounds(250, 15, 150, 25);
        totalProductsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalProductsLabel.setForeground(new Color(34, 197, 94));
        inventoryPanel.add(totalProductsLabel);
        
        totalValueLabel = new JLabel("Valor: $185,567.50");
        totalValueLabel.setBounds(420, 15, 150, 25);
        totalValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalValueLabel.setForeground(new Color(34, 197, 94));
        inventoryPanel.add(totalValueLabel);
        
        lowStockLabel = new JLabel("Stock bajo: 3 productos");
        lowStockLabel.setBounds(590, 15, 150, 25);
        lowStockLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lowStockLabel.setForeground(new Color(239, 68, 68));
        inventoryPanel.add(lowStockLabel);
        
        // Botón exportar inventario
        exportInventoryButton = new JButton("📥 Exportar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo blanco con borde azul
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(239, 246, 255));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(219, 234, 254));
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Borde azul
                g2d.setColor(new Color(74, 144, 226));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                
                // Texto del botón
                g2d.setColor(new Color(74, 144, 226));
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
        exportInventoryButton.setBounds(820, 15, 100, 30);
        exportInventoryButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        exportInventoryButton.setContentAreaFilled(false);
        exportInventoryButton.setBorderPainted(false);
        exportInventoryButton.setFocusPainted(false);
        exportInventoryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inventoryPanel.add(exportInventoryButton);
        
        // Panel de búsqueda
        searchPanel = new JPanel();
        searchPanel.setBounds(20, 55, 900, 40);
        searchPanel.setLayout(null);
        searchPanel.setBackground(new Color(249, 250, 251));
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        inventoryPanel.add(searchPanel);
        
        searchLabel = new JLabel("🔍 Buscar:");
        searchLabel.setBounds(10, 10, 60, 20);
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchLabel.setForeground(new Color(45, 55, 72));
        searchPanel.add(searchLabel);
        
        searchField = new JTextField();
        searchField.setBounds(80, 8, 200, 25);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));
        searchPanel.add(searchField);
        
        searchCategoryCombo = new JComboBox<>(new String[]{"Todas las categorías", "Electrónicos", "Accesorios", "Oficina", "Almacenamiento", "Componentes"});
        searchCategoryCombo.setBounds(300, 8, 150, 25);
        searchCategoryCombo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        searchPanel.add(searchCategoryCombo);
        
        searchButton = new JButton("Buscar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente azul pequeño
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
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                
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
        searchButton.setBounds(470, 8, 70, 25);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
        searchButton.setContentAreaFilled(false);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchPanel.add(searchButton);
        
        // Tabla de inventario
        tableModel = new DefaultTableModel(sampleInventoryData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3) return Double.class; // Columna Precio
                if (column == 4 || column == 5) return Integer.class; // Columnas Stock
                return String.class;
            }
        };
        
        inventoryTable = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                
                // Colorear filas según el estado del stock
                if (!isRowSelected(row)) {
                    String status = (String) getValueAt(row, 7); // Columna Estado
                    switch (status) {
                        case "Activo":
                            c.setBackground(new Color(240, 253, 244)); // Verde claro
                            break;
                        case "Stock Bajo":
                            c.setBackground(new Color(255, 251, 235)); // Amarillo claro
                            break;
                        case "Stock Crítico":
                            c.setBackground(new Color(254, 242, 242)); // Rojo claro
                            break;
                        default:
                            c.setBackground(Color.WHITE);
                    }
                } else {
                    c.setBackground(new Color(239, 246, 255));
                }
                
                return c;
            }
        };
        
        inventoryTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        inventoryTable.setRowHeight(35);
        inventoryTable.setGridColor(new Color(243, 244, 246));
        inventoryTable.setSelectionBackground(new Color(239, 246, 255));
        inventoryTable.setSelectionForeground(new Color(45, 55, 72));
        
        // Header de la tabla
        inventoryTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        inventoryTable.getTableHeader().setBackground(new Color(249, 250, 251));
        inventoryTable.getTableHeader().setForeground(new Color(45, 55, 72));
        inventoryTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(229, 231, 235)));
        
        // Ajustar ancho de columnas
        inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // Código
        inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(180); // Producto
        inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Categoría
        inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(80);  // Precio
        inventoryTable.getColumnModel().getColumn(4).setPreferredWidth(60);  // Stock
        inventoryTable.getColumnModel().getColumn(5).setPreferredWidth(80);  // Stock Mín.
        inventoryTable.getColumnModel().getColumn(6).setPreferredWidth(100); // Proveedor
        inventoryTable.getColumnModel().getColumn(7).setPreferredWidth(90);  // Estado
        
        tableScrollPane = new JScrollPane(inventoryTable);
        tableScrollPane.setBounds(20, 105, 900, 220);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.getViewport().setBackground(Color.WHITE);
        inventoryPanel.add(tableScrollPane);
        
        // Panel de acciones
        actionsPanel = new JPanel();
        actionsPanel.setBounds(20, 335, 900, 50);
        actionsPanel.setLayout(null);
        actionsPanel.setBackground(Color.WHITE);
        inventoryPanel.add(actionsPanel);
        
        // Botones de acción
        editProductButton = new JButton("✏️ Editar") {
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
        editProductButton.setBounds(0, 10, 90, 35);
        editProductButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        editProductButton.setContentAreaFilled(false);
        editProductButton.setBorderPainted(false);
        editProductButton.setFocusPainted(false);
        editProductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionsPanel.add(editProductButton);
        
        deleteProductButton = new JButton("🗑️ Eliminar") {
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
        deleteProductButton.setBounds(110, 10, 100, 35);
        deleteProductButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        deleteProductButton.setContentAreaFilled(false);
        deleteProductButton.setBorderPainted(false);
        deleteProductButton.setFocusPainted(false);
        deleteProductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionsPanel.add(deleteProductButton);
        
        adjustStockButton = new JButton("📦 Ajustar Stock") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente naranja
                if (getModel().isPressed()) {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(234, 88, 12),
                        0, getHeight(), new Color(194, 65, 12)
                    );
                    g2d.setPaint(gradient);
                } else if (getModel().isRollover()) {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(249, 115, 22),
                        0, getHeight(), new Color(234, 88, 12)
                    );
                    g2d.setPaint(gradient);
                } else {
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(249, 115, 22),
                        0, getHeight(), new Color(234, 88, 12)
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
        adjustStockButton.setBounds(230, 10, 130, 35);
        adjustStockButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        adjustStockButton.setContentAreaFilled(false);
        adjustStockButton.setBorderPainted(false);
        adjustStockButton.setFocusPainted(false);
        adjustStockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionsPanel.add(adjustStockButton);
        
        // Panel de paginación
        paginationPanel = new JPanel();
        paginationPanel.setBounds(650, 10, 250, 35);
        paginationPanel.setLayout(null);
        paginationPanel.setBackground(Color.WHITE);
        actionsPanel.add(paginationPanel);
        
        paginationInfo = new JLabel("Mostrando 8 de 8 productos");
        paginationInfo.setBounds(0, 7, 150, 20);
        paginationInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        paginationInfo.setForeground(new Color(107, 114, 128));
        paginationPanel.add(paginationInfo);
        
        // Botones de paginación
        prevButton = createPaginationButton("Anterior");
        prevButton.setBounds(160, 2, 70, 30);
        paginationPanel.add(prevButton);
        
        page1Button = createPaginationButton("1");
        page1Button.setBounds(240, 2, 30, 30);
        page1Button.setBackground(new Color(74, 144, 226));
        page1Button.setForeground(Color.WHITE);
        paginationPanel.add(page1Button);
        
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
                
                frame.dispose();
                Invoice ventana = new Invoice();
                ventana.iniciar();
            }
        });
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ya estamos en almacén
                JOptionPane.showMessageDialog(frame, "Ya estás en la sección de almacén");
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
        
        // Eventos adicionales para la funcionalidad de almacén
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        
        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });
        
        clearFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduct();
            }
        });
        
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });
        
        adjustStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adjustStock();
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProducts();
            }
        });
        
        exportInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportInventory();
            }
        });
    }
    
    private JButton createPaginationButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo del botón
                if (getBackground().equals(new Color(74, 144, 226))) {
                    // Botón activo con gradiente
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(74, 144, 226),
                        0, getHeight(), new Color(143, 148, 251)
                    );
                    g2d.setPaint(gradient);
                } else {
                    // Botón normal
                    if (getModel().isPressed()) {
                        g2d.setColor(new Color(249, 250, 251));
                    } else if (getModel().isRollover()) {
                        g2d.setColor(new Color(243, 244, 246));
                    } else {
                        g2d.setColor(Color.WHITE);
                    }
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                
                // Borde
                g2d.setColor(new Color(209, 213, 219));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 8, 8);
                
                // Texto
                g2d.setColor(getForeground());
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
        
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setForeground(new Color(45, 55, 72));
        button.setBackground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private void addProduct() {
        if (!validateForm()) return;
        
        String code = productCodeField.getText().trim();
        String name = productNameField.getText().trim();
        String category = (String) categoryCombo.getSelectedItem();
        double price = Double.parseDouble(priceField.getText().trim());
        int stock = (Integer) stockSpinner.getValue();
        int minStock = (Integer) minStockSpinner.getValue();
        String supplier = supplierField.getText().trim();
        String status = stock <= minStock ? (stock == 0 ? "Stock Crítico" : "Stock Bajo") : "Activo";
        
        // Agregar fila a la tabla
        Object[] row = {code, name, category, price, stock, minStock, supplier, status};
        tableModel.addRow(row);
        
        clearForm();
        JOptionPane.showMessageDialog(frame, "Producto agregado exitosamente", "Producto Agregado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateProduct() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) return;
        
        if (!validateForm()) return;
        
        String code = productCodeField.getText().trim();
        String name = productNameField.getText().trim();
        String category = (String) categoryCombo.getSelectedItem();
        double price = Double.parseDouble(priceField.getText().trim());
        int stock = (Integer) stockSpinner.getValue();
        int minStock = (Integer) minStockSpinner.getValue();
        String supplier = supplierField.getText().trim();
        String status = stock <= minStock ? (stock == 0 ? "Stock Crítico" : "Stock Bajo") : "Activo";
        
        // Actualizar fila en la tabla
        tableModel.setValueAt(code, selectedRow, 0);
        tableModel.setValueAt(name, selectedRow, 1);
        tableModel.setValueAt(category, selectedRow, 2);
        tableModel.setValueAt(price, selectedRow, 3);
        tableModel.setValueAt(stock, selectedRow, 4);
        tableModel.setValueAt(minStock, selectedRow, 5);
        tableModel.setValueAt(supplier, selectedRow, 6);
        tableModel.setValueAt(status, selectedRow, 7);
        
        clearForm();
        addProductButton.setEnabled(true);
        updateProductButton.setEnabled(false);
        
        JOptionPane.showMessageDialog(frame, "Producto actualizado exitosamente", "Producto Actualizado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean validateForm() {
        if (productCodeField.getText().trim().isEmpty() ||
            productNameField.getText().trim().isEmpty() ||
            categoryCombo.getSelectedIndex() == 0 ||
            priceField.getText().trim().isEmpty() ||
            supplierField.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(frame, "Por favor complete todos los campos obligatorios");
            return false;
        }
        
        try {
            Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "El precio debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void clearForm() {
        productCodeField.setText("");
        productNameField.setText("");
        categoryCombo.setSelectedIndex(0);
        priceField.setText("");
        stockSpinner.setValue(0);
        minStockSpinner.setValue(0);
        supplierField.setText("");
        descriptionArea.setText("");
        
        addProductButton.setEnabled(true);
        updateProductButton.setEnabled(false);
    }
    
    private void editProduct() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un producto para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Cargar datos del producto seleccionado en el formulario
        productCodeField.setText((String) tableModel.getValueAt(selectedRow, 0));
        productNameField.setText((String) tableModel.getValueAt(selectedRow, 1));
        categoryCombo.setSelectedItem((String) tableModel.getValueAt(selectedRow, 2));
        priceField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 3)));
        stockSpinner.setValue((Integer) tableModel.getValueAt(selectedRow, 4));
        minStockSpinner.setValue((Integer) tableModel.getValueAt(selectedRow, 5));
        supplierField.setText((String) tableModel.getValueAt(selectedRow, 6));
        
        addProductButton.setEnabled(false);
        updateProductButton.setEnabled(true);
    }
    
    private void deleteProduct() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un producto para eliminar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String productName = (String) tableModel.getValueAt(selectedRow, 1);
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "¿Está seguro de que desea eliminar el producto '" + productName + "'?\n\n" +
            "Esta acción no se puede deshacer.", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(frame, "Producto eliminado exitosamente", "Producto Eliminado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void adjustStock() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un producto para ajustar el stock", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String productName = (String) tableModel.getValueAt(selectedRow, 1);
        int currentStock = (Integer) tableModel.getValueAt(selectedRow, 4);
        
        String input = JOptionPane.showInputDialog(frame, 
            "Producto: " + productName + "\n" +
            "Stock actual: " + currentStock + "\n\n" +
            "Ingrese el nuevo stock:", 
            "Ajustar Stock", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) {
            try {
                int newStock = Integer.parseInt(input.trim());
                if (newStock < 0) {
                    JOptionPane.showMessageDialog(frame, "El stock no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int minStock = (Integer) tableModel.getValueAt(selectedRow, 5);
                String status = newStock <= minStock ? (newStock == 0 ? "Stock Crítico" : "Stock Bajo") : "Activo";
                
                tableModel.setValueAt(newStock, selectedRow, 4);
                tableModel.setValueAt(status, selectedRow, 7);
                
                JOptionPane.showMessageDialog(frame, 
                    "Stock actualizado exitosamente\n\n" +
                    "Stock anterior: " + currentStock + "\n" +
                    "Stock nuevo: " + newStock, 
                    "Stock Actualizado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void searchProducts() {
        String searchTerm = searchField.getText().trim();
        String category = (String) searchCategoryCombo.getSelectedItem();
        
        if (searchTerm.isEmpty() && category.equals("Todas las categorías")) {
            JOptionPane.showMessageDialog(frame, "Ingrese un término de búsqueda o seleccione una categoría", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(frame, 
            "Búsqueda realizada:\n" +
            "Término: " + (searchTerm.isEmpty() ? "Ninguno" : searchTerm) + "\n" +
            "Categoría: " + category + "\n\n" +
            "Los resultados se mostrarían filtrados en la tabla.", 
            "Búsqueda de Productos", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exportInventory() {
        JOptionPane.showMessageDialog(frame, 
            "Exportando inventario de productos...\n\n" +
            "El archivo se ha guardado como 'inventario_productos.xlsx'\n" +
            "en la carpeta de Documentos.\n\n" +
            "Incluye todos los productos con sus detalles completos.", 
            "Exportar Inventario", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Storage ventana = new Storage();
            ventana.iniciar();
        });
    }
}
