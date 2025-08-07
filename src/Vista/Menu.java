
package Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu {
    JFrame frame;
    JLabel label1, profileLabel;
    JButton btn1, btn2, btn3,btn4;
    JPanel backgroundPanel, profilePanel, sidebarPanel;
    
    public void iniciar(){
        // Marco principal
        frame = new JFrame("Menú Principal");
        frame.setSize(600, 450);
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
        sidebarPanel.setBounds(0, 0, 220, 450);
        sidebarPanel.setLayout(null);
        backgroundPanel.add(sidebarPanel);
        
        // Título de bienvenida (ahora en fondo blanco)
        label1 = new JLabel("Bienvenido al Sistema");
        label1.setBounds(220, 40, 380, 40);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        label1.setForeground(new Color(45, 55, 72));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(label1);
        
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
        profilePanel.setBounds(350, 120, 100, 100);
        profilePanel.setOpaque(false);
        backgroundPanel.add(profilePanel);
        
        // Etiqueta para la foto de perfil
        profileLabel = new JLabel("👤");
        profileLabel.setBounds(375, 145, 50, 50);
        profileLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(profileLabel);
        
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
        btn1.setBounds(20, 150, 180, 45);
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
        btn2.setBounds(20, 210, 180, 45);
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
        btn3.setBounds(20, 270, 180, 45);
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
        btn4.setBounds(20, 90, 180, 45);
        btn4.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn4.setContentAreaFilled(false);
        btn4.setBorderPainted(false);
        btn4.setFocusPainted(false);
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebarPanel.add(btn4);
        
        frame.add(backgroundPanel);
        frame.setVisible(true);
        
        // Acciones de los botones (mantiene tu estructura)
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
                //Storage ventana = new Storage();
                //ventana.iniciar();
            }
        });
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                frame.dispose();
                //Search ventana = new Search();
                //ventana.iniciar();
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
    }
}
