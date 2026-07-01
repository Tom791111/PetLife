package util;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class UiStyle {
    public static final Color BG = new Color(245, 248, 252);
    public static final Color CARD = Color.WHITE;
    public static final Color PRIMARY = new Color(47, 128, 237);
    public static final Color PRIMARY_DARK = new Color(34, 91, 184);
    public static final Color ACCENT = new Color(242, 153, 74);
    public static final Color MINT = new Color(39, 174, 96);
    public static final Color TEXT = new Color(34, 44, 60);
    public static final Color MUTED = new Color(112, 126, 148);
    public static final Color LINE = new Color(224, 232, 242);
    public static final Color SOFT_BLUE = new Color(226, 239, 255);
    public static final Color SOFT_ORANGE = new Color(255, 237, 220);

    public static Font font(int style, int size) {
        return new Font("Microsoft JhengHei", style, size);
    }

    public static void applyGlobalStyle() {
        UIManager.put("Button.font", font(Font.BOLD, 14));
        UIManager.put("Label.font", font(Font.PLAIN, 14));
        UIManager.put("TextField.font", font(Font.PLAIN, 14));
        UIManager.put("TextArea.font", font(Font.PLAIN, 14));
        UIManager.put("ComboBox.font", font(Font.PLAIN, 14));
        UIManager.put("Table.font", font(Font.PLAIN, 13));
        UIManager.put("Table.rowHeight", 34);
        UIManager.put("Panel.background", BG);
        UIManager.put("OptionPane.messageFont", font(Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", font(Font.BOLD, 13));
    }

    public static JLabel title(String text) {
        JLabel label = new JLabel(text);
        label.setFont(font(Font.BOLD, 28));
        label.setForeground(TEXT);
        return label;
    }

    public static JLabel subtitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(font(Font.PLAIN, 15));
        label.setForeground(MUTED);
        return label;
    }

    public static JButton primaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(font(Font.BOLD, 14));
        button.setBackground(PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static JButton secondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(font(Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(PRIMARY_DARK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), new EmptyBorder(8, 16, 8, 16)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void styleField(JTextField field) {
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), new EmptyBorder(6, 10, 6, 10)));
        field.setBackground(Color.WHITE);
        field.setForeground(TEXT);
    }

    public static void styleTable(JTable table) {
        table.setRowHeight(34);
        table.setGridColor(new Color(236, 241, 248));
        table.setSelectionBackground(SOFT_BLUE);
        table.setSelectionForeground(TEXT);
        table.setShowVerticalLines(false);
        JTableHeader header = table.getTableHeader();
        header.setFont(font(Font.BOLD, 13));
        header.setBackground(new Color(238, 244, 252));
        header.setForeground(TEXT);
    }

    public static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color fill;
        public RoundedPanel(int radius, Color fill) {
            super(null);
            this.radius = radius;
            this.fill = fill;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static class GradientPanel extends JPanel {
        private final Color start;
        private final Color end;
        private final int radius;
        public GradientPanel(Color start, Color end, int radius) {
            super(null);
            this.start = start;
            this.end = end;
            this.radius = radius;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0, start, getWidth(), getHeight(), end);
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
