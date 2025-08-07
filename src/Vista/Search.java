
package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search {
        // Componentes

    private JFrame frame;
    private JPanel backgroundPanel,sidebarPanel,profilePanel,searchPanel, resultsPanel,paginationPanel;
    private JLabel label1,profileLabel,subtitleLabel,searchTitleLabel,searchFieldLabel,statusLabel,cityLabel,resultsLabel,paginationInfo;
    private JButton btn1, btn2, btn3, btn4,searchButton,exportButton, prevButton, page1Button, page2Button, nextButton;
    private JTextField searchField;
    private JComboBox<String> statusCombo,cityCombo;
    private JTable resultsTable;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;
    
    // Datos de ejemplo
    private Object[][] sampleData = {
        {1, "Juan Pérez", "juan@email.com", "123-456-7890", "Madrid", "Activo", "2024-01-15"},
        {2, "María García", "maria@email.com", "098-765-4321", "Barcelona", "Inactivo", "2024-01-14"},
        {3, "Carlos López", "carlos@email.com", "555-123-4567", "Valencia", "Activo", "2024-01-13"},
        {4, "Ana Martín", "ana@email.com", "777-888-9999", "Sevilla", "Pendiente", "2024-01-12"},
        {5, "Luis Rodríguez", "luis@email.com", "444-555-6666", "Bilbao", "Activo", "2024-01-11"}
    };
    
    private String[] columnNames = {"ID", "Nombre", "Email", "Teléfono", "Ciudad", "Estado", "Fecha"};
    
    public void iniciar() {
        // Marco principal
        frame = new JFrame("Sistema de Búsqueda");
        frame.setSize(900, 650);
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
        sidebarPanel.setBounds(0, 0, 220, 650);
        sidebarPanel.setLayout(null);
        backgroundPanel.add(sidebarPanel);
        
        // Título de bienvenida (ahora en fondo blanco)
        label1 = new JLabel("Sistema de Búsqueda");
        label1.setBounds(240, 20, 640, 40);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        label1.setForeground(new Color(45, 55, 72));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        backgroundPanel.add(label1);
        
        // Subtítulo
        subtitleLabel = new JLabel("Busca y gestiona registros en la base de datos");
        subtitleLabel.setBounds(240, 60, 640, 20);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(107, 114, 128));
        backgroundPanel.add(subtitleLabel);
        
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
        
        // Panel de búsqueda
        searchPanel = new JPanel() {
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
        searchPanel.setBounds(240, 100, 640, 120);
        searchPanel.setLayout(null);
        searchPanel.setOpaque(false);
        backgroundPanel.add(searchPanel);
        
        // Título del panel de búsqueda
        searchTitleLabel = new JLabel("🔍 Filtros de Búsqueda");
        searchTitleLabel.setBounds(20, 15, 200, 25);
        searchTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        searchTitleLabel.setForeground(new Color(45, 55, 72));
        searchPanel.add(searchTitleLabel);
        
        // Campo de búsqueda
        searchFieldLabel = new JLabel("Término de búsqueda");
        searchFieldLabel.setBounds(20, 45, 150, 20);
        searchFieldLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchFieldLabel.setForeground(new Color(45, 55, 72));
        searchPanel.add(searchFieldLabel);
        
        searchField = new JTextField("Buscar por nombre, email o teléfono...");
        searchField.setBounds(20, 65, 150, 35);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setForeground(Color.GRAY);
        searchPanel.add(searchField);
        
        // ComboBox Estado
        statusLabel = new JLabel("Estado");
        statusLabel.setBounds(190, 45, 100, 20);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(45, 55, 72));
        searchPanel.add(statusLabel);
        
        statusCombo = new JComboBox<>(new String[]{"Todos los estados", "Activo", "Inactivo", "Pendiente"});
        statusCombo.setBounds(190, 65, 150, 35);
        statusCombo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchPanel.add(statusCombo);
        
        // ComboBox Ciudad
        cityLabel = new JLabel("Ciudad");
        cityLabel.setBounds(360, 45, 100, 20);
        cityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cityLabel.setForeground(new Color(45, 55, 72));
        searchPanel.add(cityLabel);
        
        cityCombo = new JComboBox<>(new String[]{"Todas las ciudades", "Madrid", "Barcelona", "Valencia", "Sevilla", "Bilbao"});
        cityCombo.setBounds(360, 65, 150, 35);
        cityCombo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchPanel.add(cityCombo);
        
        // Botón de búsqueda
        searchButton = new JButton("🔍 Buscar") {
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
        searchButton.setBounds(530, 65, 90, 35);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchButton.setContentAreaFilled(false);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchPanel.add(searchButton);
        
        // Panel de resultados
        resultsPanel = new JPanel() {
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
        resultsPanel.setBounds(240, 240, 640, 320);
        resultsPanel.setLayout(null);
        resultsPanel.setOpaque(false);
        backgroundPanel.add(resultsPanel);
        
        // Header del panel de resultados
        resultsLabel = new JLabel("📊 Resultados (5)");
        resultsLabel.setBounds(20, 15, 200, 25);
        resultsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultsLabel.setForeground(new Color(45, 55, 72));
        resultsPanel.add(resultsLabel);
        
        // Botón exportar
        exportButton = new JButton("📥 Exportar") {
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
        exportButton.setBounds(520, 15, 100, 30);
        exportButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        exportButton.setContentAreaFilled(false);
        exportButton.setBorderPainted(false);
        exportButton.setFocusPainted(false);
        exportButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resultsPanel.add(exportButton);
        
        // Tabla de resultados
        tableModel = new DefaultTableModel(sampleData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        resultsTable = new JTable(tableModel);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resultsTable.setRowHeight(30);
        resultsTable.setGridColor(new Color(243, 244, 246));
        resultsTable.setSelectionBackground(new Color(239, 246, 255));
        resultsTable.setSelectionForeground(new Color(45, 55, 72));
        
        // Header de la tabla
        resultsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        resultsTable.getTableHeader().setBackground(new Color(249, 250, 251));
        resultsTable.getTableHeader().setForeground(new Color(45, 55, 72));
        resultsTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(229, 231, 235)));
        
        tableScrollPane = new JScrollPane(resultsTable);
        tableScrollPane.setBounds(20, 55, 600, 250);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.getViewport().setBackground(Color.WHITE);
        resultsPanel.add(tableScrollPane);
        
        // Panel de paginación
        paginationPanel = new JPanel();
        paginationPanel.setBounds(240, 580, 640, 40);
        paginationPanel.setLayout(null);
        paginationPanel.setBackground(Color.WHITE);
        backgroundPanel.add(paginationPanel);
        
        paginationInfo = new JLabel("Mostrando 5 de 5 registros");
        paginationInfo.setBounds(0, 10, 200, 20);
        paginationInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        paginationInfo.setForeground(new Color(107, 114, 128));
        paginationPanel.add(paginationInfo);
        
        // Botones de paginación
        prevButton = createPaginationButton("Anterior");
        prevButton.setBounds(440, 5, 80, 30);
        paginationPanel.add(prevButton);
        
        page1Button = createPaginationButton("1");
        page1Button.setBounds(530, 5, 30, 30);
        page1Button.setBackground(new Color(74, 144, 226));
        page1Button.setForeground(Color.WHITE);
        paginationPanel.add(page1Button);
        
        page2Button = createPaginationButton("2");
        page2Button.setBounds(570, 5, 30, 30);
        paginationPanel.add(page2Button);
        
        nextButton = createPaginationButton("Siguiente");
        nextButton.setBounds(610, 5, 80, 30);
        paginationPanel.add(nextButton);
        
        // Botones dentro del panel lateral
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
        btn4.setBounds(20, 220, 180, 45);
        btn4.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn4.setContentAreaFilled(false);
        btn4.setBorderPainted(false);
        btn4.setFocusPainted(false);
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebarPanel.add(btn4);
        
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
        btn1.setBounds(20, 280, 180, 45);
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
        btn2.setBounds(20, 340, 180, 45);
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
        btn3.setBounds(20, 400, 180, 45);
        btn3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn3.setContentAreaFilled(false);
        btn3.setBorderPainted(false);
        btn3.setFocusPainted(false);
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebarPanel.add(btn3);
        
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
                
                frame.dispose();
                History ventana = new History();
                ventana.iniciar();
            }
        });
        
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //(ya estamos en búsqueda)
                JOptionPane.showMessageDialog(frame, "Ya estás en la sección de búsqueda");
            }
        });
        
        // Eventos adicionales para la funcionalidad de búsqueda
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Buscar por nombre, email o teléfono...")) {
                    searchField.setText("");
                    searchField.setForeground(new Color(45, 55, 72));
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Buscar por nombre, email o teléfono...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                String selectedStatus = (String) statusCombo.getSelectedItem();
                String selectedCity = (String) cityCombo.getSelectedItem();
                
                JOptionPane.showMessageDialog(frame, 
                    "Búsqueda realizada:\n" +
                    "Término: " + searchTerm + "\n" +
                    "Estado: " + selectedStatus + "\n" +
                    "Ciudad: " + selectedCity, 
                    "Búsqueda", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, 
                    "Funcionalidad de exportación implementada", 
                    "Exportar", 
                    JOptionPane.INFORMATION_MESSAGE);
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Search ventana = new Search();
            ventana.iniciar();
        });
    }
}
