package Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Register {
     JFrame frame;
    JLabel label1, label2, titleLabel;
    JTextField text1;
    JPasswordField password;
    JButton btn1, btn2;
    JPanel backgroundPanel;
    
    public void iniciar(){
        // Marco principal
        frame = new JFrame("Iniciar Sesión");
        frame.setSize(400, 450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar en pantalla
        
        // Panel de fondo con gradiente
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Mismo gradiente que la ventana anterior
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(74, 144, 226),
                    0, getHeight(), new Color(143, 148, 251)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        
        // Título principal
        titleLabel = new JLabel("Registrarse");
        titleLabel.setBounds(0, 40, 400, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(titleLabel);
        
        // Etiquetas con estilo mejorado
        label1 = new JLabel("Usuario:");
        label1.setBounds(50, 120, 150, 25);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label1.setForeground(Color.WHITE);
        backgroundPanel.add(label1);
        
        label2 = new JLabel("Contraseña:");
        label2.setBounds(50, 190, 150, 25);
        label2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label2.setForeground(Color.WHITE);
        backgroundPanel.add(label2);
        
        // Campo de texto mejorado
        text1 = new JTextField("") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo blanco con transparencia
                g2d.setColor(new Color(255, 255, 255, 240));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                
                // Borde
                if (hasFocus()) {
                    g2d.setColor(new Color(255, 255, 255));
                    g2d.setStroke(new BasicStroke(2));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 150));
                    g2d.setStroke(new BasicStroke(1));
                }
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 8, 8);
                
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        text1.setBounds(50, 145, 300, 35);
        text1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        text1.setOpaque(false);
        text1.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        text1.setForeground(new Color(45, 55, 72));
        backgroundPanel.add(text1);
        
        // Campo de contraseña mejorado
        password = new JPasswordField("") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo blanco con transparencia
                g2d.setColor(new Color(255, 255, 255, 240));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                
                // Borde
                if (hasFocus()) {
                    g2d.setColor(new Color(255, 255, 255));
                    g2d.setStroke(new BasicStroke(2));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 150));
                    g2d.setStroke(new BasicStroke(1));
                }
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 8, 8);
                
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        password.setBounds(50, 215, 300, 35);
        password.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        password.setOpaque(false);
        password.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        password.setForeground(new Color(45, 55, 72));
        backgroundPanel.add(password);
        
        // Botón Ingresar (usando tu estructura pero con estilo mejorado)
        btn1 = new JButton("Ingresar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Mismo color que el botón anterior
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(35, 45, 62));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(55, 65, 82));
                } else {
                    g2d.setColor(new Color(45, 55, 72));
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
        btn1.setBounds(125, 280, 140, 40);
        btn1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn1.setContentAreaFilled(false);
        btn1.setBorderPainted(false);
        btn1.setFocusPainted(false);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backgroundPanel.add(btn1);
        
        
        frame.add(backgroundPanel);
        frame.setVisible(true);
        
        // Acciones (mantiene tu estructura original)
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validación simple
                if (text1.getText().trim().isEmpty() || password.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(frame, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                frame.dispose();
                Login ventana = new Login();
                ventana.iniciar();
            }
        });
        
       
        
        // Funcionalidad extra: Enter para login
        KeyListener enterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btn1.doClick();
                }
            }
        };
        text1.addKeyListener(enterListener);
        password.addKeyListener(enterListener);
    }
}
