
package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class History {
    //componentes
    private JFrame frame;
    private JPanel backgroundPanel,sidebarPanel,profilePanel,filterPanel,historyPanel,paginationPanel,actionsPanel;
    private JLabel label1,titleLabel,profileLabel,filterTitleLabel,dateFromLabel,dateToLabel,clientFilterLabel,statusFilterLabel;
    private JButton btn1, btn2, btn3, btn4,filterButton,resetFilterButton,prevButton, page1Button,exportHistoryButton;
    private JTextField dateFromField,dateToField,clientFilterField;
    private JComboBox<String> statusFilterCombo;
    private JLabel historyLabel,totalInvoicesLabel,totalAmountLabel,paginationInfo;
    private JButton deleteInvoiceButton,printInvoiceButton,viewDetailsButton;
    private JTable historyTable;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;

    
    // Variables para formateo
    private DecimalFormat df = new DecimalFormat("#,##0.00");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    // Datos de ejemplo del historial
    private Object[][] sampleHistoryData = {
        {"FAC-001", "15/01/2024", "Juan Pérez", "juan@email.com", "Pagada", 1250.50, "3 productos"},
        {"FAC-002", "14/01/2024", "María García", "maria@email.com", "Pendiente", 890.75, "2 productos"},
        {"FAC-003", "13/01/2024", "Carlos López", "carlos@email.com", "Pagada", 2150.00, "5 productos"},
        {"FAC-004", "12/01/2024", "Ana Martín", "ana@email.com", "Cancelada", 675.25, "1 producto"},
        {"FAC-005", "11/01/2024", "Luis Rodríguez", "luis@email.com", "Pagada", 1890.80, "4 productos"},
        {"FAC-006", "10/01/2024", "Carmen Ruiz", "carmen@email.com", "Pagada", 3250.00, "7 productos"},
        {"FAC-007", "09/01/2024", "Pedro Sánchez", "pedro@email.com", "Pendiente", 1125.60, "3 productos"},
        {"FAC-008", "08/01/2024", "Laura Jiménez", "laura@email.com", "Pagada", 780.90, "2 productos"}
    };
    
    private String[] columnNames = {"N° Factura", "Fecha", "Cliente", "Email", "Estado", "Total", "Productos"};
    
    public void iniciar() {
        // Marco principal
        frame = new JFrame("Historial de Facturas");
        frame.setSize(1100, 750);
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
        sidebarPanel.setBounds(0, 0, 220, 750);
        sidebarPanel.setLayout(null);
        backgroundPanel.add(sidebarPanel);
        
        // Título de bienvenida (ahora en fondo blanco)
        label1 = new JLabel("Historial de Facturas");
        label1.setBounds(240, 20, 840, 40);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        label1.setForeground(new Color(45, 55, 72));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        backgroundPanel.add(label1);
        
        // Subtítulo
        titleLabel = new JLabel("Revisa y gestiona todas las facturas generadas en el sistema");
        titleLabel.setBounds(240, 60, 840, 20);
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
        
        // Panel de filtros
        filterPanel = new JPanel() {
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
        filterPanel.setBounds(240, 100, 840, 120);
        filterPanel.setLayout(null);
        filterPanel.setOpaque(false);
        backgroundPanel.add(filterPanel);
        
        // Título del panel de filtros
        filterTitleLabel = new JLabel("🔍 Filtros de Búsqueda");
        filterTitleLabel.setBounds(20, 15, 200, 25);
        filterTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        filterTitleLabel.setForeground(new Color(45, 55, 72));
        filterPanel.add(filterTitleLabel);
        
        // Campos de filtro
        dateFromLabel = new JLabel("Fecha desde");
        dateFromLabel.setBounds(20, 45, 100, 20);
        dateFromLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateFromLabel.setForeground(new Color(45, 55, 72));
        filterPanel.add(dateFromLabel);
        
        dateFromField = new JTextField("01/01/2024");
        dateFromField.setBounds(20, 65, 120, 30);
        dateFromField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateFromField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        filterPanel.add(dateFromField);
        
        dateToLabel = new JLabel("Fecha hasta");
        dateToLabel.setBounds(160, 45, 100, 20);
        dateToLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateToLabel.setForeground(new Color(45, 55, 72));
        filterPanel.add(dateToLabel);
        
        dateToField = new JTextField("31/12/2024");
        dateToField.setBounds(160, 65, 120, 30);
        dateToField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateToField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        filterPanel.add(dateToField);
        
        clientFilterLabel = new JLabel("Cliente");
        clientFilterLabel.setBounds(300, 45, 100, 20);
        clientFilterLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientFilterLabel.setForeground(new Color(45, 55, 72));
        filterPanel.add(clientFilterLabel);
        
        clientFilterField = new JTextField();
        clientFilterField.setBounds(300, 65, 150, 30);
        clientFilterField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clientFilterField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        filterPanel.add(clientFilterField);
        
        statusFilterLabel = new JLabel("Estado");
        statusFilterLabel.setBounds(470, 45, 100, 20);
        statusFilterLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusFilterLabel.setForeground(new Color(45, 55, 72));
        filterPanel.add(statusFilterLabel);
        
        statusFilterCombo = new JComboBox<>(new String[]{"Todos", "Pagada", "Pendiente", "Cancelada"});
        statusFilterCombo.setBounds(470, 65, 120, 30);
        statusFilterCombo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        filterPanel.add(statusFilterCombo);
        
        // Botón filtrar
        filterButton = new JButton("🔍 Filtrar") {
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
        filterButton.setBounds(610, 65, 90, 30);
        filterButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        filterButton.setContentAreaFilled(false);
        filterButton.setBorderPainted(false);
        filterButton.setFocusPainted(false);
        filterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        filterPanel.add(filterButton);
        
        // Botón limpiar filtros
        resetFilterButton = new JButton("🗑️ Limpiar") {
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
        resetFilterButton.setBounds(720, 65, 90, 30);
        resetFilterButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        resetFilterButton.setContentAreaFilled(false);
        resetFilterButton.setBorderPainted(false);
        resetFilterButton.setFocusPainted(false);
        resetFilterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        filterPanel.add(resetFilterButton);
        
        // Panel de historial
        historyPanel = new JPanel() {
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
        historyPanel.setBounds(240, 240, 840, 380);
        historyPanel.setLayout(null);
        historyPanel.setOpaque(false);
        backgroundPanel.add(historyPanel);
        
        // Header del panel de historial
        historyLabel = new JLabel("📊 Historial de Facturas");
        historyLabel.setBounds(20, 15, 200, 25);
        historyLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        historyLabel.setForeground(new Color(45, 55, 72));
        historyPanel.add(historyLabel);
        
        // Estadísticas
        totalInvoicesLabel = new JLabel("Total: 8 facturas");
        totalInvoicesLabel.setBounds(250, 15, 150, 25);
        totalInvoicesLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalInvoicesLabel.setForeground(new Color(34, 197, 94));
        historyPanel.add(totalInvoicesLabel);
        
        totalAmountLabel = new JLabel("Monto total: $12,012.80");
        totalAmountLabel.setBounds(420, 15, 200, 25);
        totalAmountLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalAmountLabel.setForeground(new Color(34, 197, 94));
        historyPanel.add(totalAmountLabel);
        
        // Botón exportar historial
        exportHistoryButton = new JButton("📥 Exportar") {
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
        exportHistoryButton.setBounds(720, 15, 100, 30);
        exportHistoryButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        exportHistoryButton.setContentAreaFilled(false);
        exportHistoryButton.setBorderPainted(false);
        exportHistoryButton.setFocusPainted(false);
        exportHistoryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        historyPanel.add(exportHistoryButton);
        
        // Tabla de historial
        tableModel = new DefaultTableModel(sampleHistoryData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) return Double.class; // Columna Total
                return String.class;
            }
        };
        
        historyTable = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                
                // Colorear filas según el estado
                if (!isRowSelected(row)) {
                    String status = (String) getValueAt(row, 4); // Columna Estado
                    switch (status) {
                        case "Pagada":
                            c.setBackground(new Color(240, 253, 244)); // Verde claro
                            break;
                        case "Pendiente":
                            c.setBackground(new Color(255, 251, 235)); // Amarillo claro
                            break;
                        case "Cancelada":
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
        
        historyTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        historyTable.setRowHeight(35);
        historyTable.setGridColor(new Color(243, 244, 246));
        historyTable.setSelectionBackground(new Color(239, 246, 255));
        historyTable.setSelectionForeground(new Color(45, 55, 72));
        
        // Header de la tabla
        historyTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        historyTable.getTableHeader().setBackground(new Color(249, 250, 251));
        historyTable.getTableHeader().setForeground(new Color(45, 55, 72));
        historyTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(229, 231, 235)));
        
        // Ajustar ancho de columnas
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // N° Factura
        historyTable.getColumnModel().getColumn(1).setPreferredWidth(80);  // Fecha
        historyTable.getColumnModel().getColumn(2).setPreferredWidth(120); // Cliente
        historyTable.getColumnModel().getColumn(3).setPreferredWidth(150); // Email
        historyTable.getColumnModel().getColumn(4).setPreferredWidth(80);  // Estado
        historyTable.getColumnModel().getColumn(5).setPreferredWidth(80);  // Total
        historyTable.getColumnModel().getColumn(6).setPreferredWidth(90);  // Productos
        
        tableScrollPane = new JScrollPane(historyTable);
        tableScrollPane.setBounds(20, 55, 800, 250);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.getViewport().setBackground(Color.WHITE);
        historyPanel.add(tableScrollPane);
        
        // Panel de acciones
        actionsPanel = new JPanel();
        actionsPanel.setBounds(240, 640, 840, 50);
        actionsPanel.setLayout(null);
        actionsPanel.setBackground(Color.WHITE);
        backgroundPanel.add(actionsPanel);
        
        // Botones de acción
        viewDetailsButton = new JButton("👁️ Ver Detalles") {
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
        viewDetailsButton.setBounds(0, 10, 130, 35);
        viewDetailsButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        viewDetailsButton.setContentAreaFilled(false);
        viewDetailsButton.setBorderPainted(false);
        viewDetailsButton.setFocusPainted(false);
        viewDetailsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionsPanel.add(viewDetailsButton);
        
        printInvoiceButton = new JButton("🖨️ Imprimir") {
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
        printInvoiceButton.setBounds(150, 10, 110, 35);
        printInvoiceButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        printInvoiceButton.setContentAreaFilled(false);
        printInvoiceButton.setBorderPainted(false);
        printInvoiceButton.setFocusPainted(false);
        printInvoiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionsPanel.add(printInvoiceButton);
        
        deleteInvoiceButton = new JButton("🗑️ Eliminar") {
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
        deleteInvoiceButton.setBounds(280, 10, 100, 35);
        deleteInvoiceButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        deleteInvoiceButton.setContentAreaFilled(false);
        deleteInvoiceButton.setBorderPainted(false);
        deleteInvoiceButton.setFocusPainted(false);
        deleteInvoiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionsPanel.add(deleteInvoiceButton);
        
        // Panel de paginación
        paginationPanel = new JPanel();
        paginationPanel.setBounds(600, 10, 240, 35);
        paginationPanel.setLayout(null);
        paginationPanel.setBackground(Color.WHITE);
        actionsPanel.add(paginationPanel);
        
        paginationInfo = new JLabel("Mostrando 8 de 8 registros");
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
                
                frame.dispose();
                Storage ventana = new Storage();
                ventana.iniciar();
            }
        });
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ya estamos en historial
                JOptionPane.showMessageDialog(frame, "Ya estás en la sección de historial");
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
        
        // Eventos adicionales para la funcionalidad de historial
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });
        
        resetFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFilters();
            }
        });
        
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInvoiceDetails();
            }
        });
        
        printInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printInvoice();
            }
        });
        
        deleteInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteInvoice();
            }
        });
        
        exportHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportHistory();
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
    
    private void applyFilters() {
        String dateFrom = dateFromField.getText();
        String dateTo = dateToField.getText();
        String client = clientFilterField.getText();
        String status = (String) statusFilterCombo.getSelectedItem();
        
        JOptionPane.showMessageDialog(frame, 
            "Filtros aplicados:\n" +
            "Fecha desde: " + dateFrom + "\n" +
            "Fecha hasta: " + dateTo + "\n" +
            "Cliente: " + (client.isEmpty() ? "Todos" : client) + "\n" +
            "Estado: " + status, 
            "Filtros Aplicados", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void resetFilters() {
        dateFromField.setText("01/01/2024");
        dateToField.setText("31/12/2024");
        clientFilterField.setText("");
        statusFilterCombo.setSelectedIndex(0);
        
        JOptionPane.showMessageDialog(frame, "Filtros restablecidos", "Filtros", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void viewInvoiceDetails() {
        int selectedRow = historyTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione una factura para ver los detalles", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String invoiceNumber = (String) tableModel.getValueAt(selectedRow, 0);
        String client = (String) tableModel.getValueAt(selectedRow, 2);
        String date = (String) tableModel.getValueAt(selectedRow, 1);
        Double total = (Double) tableModel.getValueAt(selectedRow, 5);
        
        String details = "Detalles de la Factura\n\n" +
                        "Número: " + invoiceNumber + "\n" +
                        "Fecha: " + date + "\n" +
                        "Cliente: " + client + "\n" +
                        "Total: $" + df.format(total) + "\n\n" +
                        "Esta funcionalidad mostraría todos los productos\n" +
                        "y detalles completos de la factura seleccionada.";
        
        JOptionPane.showMessageDialog(frame, details, "Detalles de Factura", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void printInvoice() {
        int selectedRow = historyTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione una factura para imprimir", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String invoiceNumber = (String) tableModel.getValueAt(selectedRow, 0);
        JOptionPane.showMessageDialog(frame, 
            "Imprimiendo factura " + invoiceNumber + "...\n\n" +
            "La factura se ha enviado a la impresora predeterminada.", 
            "Imprimir Factura", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void deleteInvoice() {
        int selectedRow = historyTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione una factura para eliminar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String invoiceNumber = (String) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "¿Está seguro de que desea eliminar la factura " + invoiceNumber + "?\n\n" +
            "Esta acción no se puede deshacer.", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(frame, "Factura eliminada exitosamente", "Factura Eliminada", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void exportHistory() {
        JOptionPane.showMessageDialog(frame, 
            "Exportando historial de facturas...\n\n" +
            "El archivo se ha guardado como 'historial_facturas.xlsx'\n" +
            "en la carpeta de Documentos.", 
            "Exportar Historial", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            History ventana = new History();
            ventana.iniciar();
        });
    }
}
