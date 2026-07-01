package util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class IconFactory {
    public enum Type { HOME, PET, HEALTH, SERVICE, LOST, MAP, COMMUNITY, SHOP, USER, CARE, DB, MVC, CRUD, MODULE, BACK, ADD, EDIT, DELETE, REFRESH }

    public static Icon icon(Type type, int size, Color color) {
        return new LineIcon(type, size, color);
    }

    private static class LineIcon implements Icon {
        private final Type type;
        private final int size;
        private final Color color;

        LineIcon(Type type, int size, Color color) {
            this.type = type;
            this.size = size;
            this.color = color;
        }

        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.translate(x, y);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(Math.max(2f, size / 12f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            switch (type) {
                case HOME: drawHome(g2); break;
                case PET: drawPet(g2); break;
                case HEALTH: drawHealth(g2); break;
                case SERVICE: drawService(g2); break;
                case LOST: drawLost(g2); break;
                case MAP: drawMap(g2); break;
                case COMMUNITY: drawCommunity(g2); break;
                case SHOP: drawShop(g2); break;
                case USER: drawUser(g2); break;
                case CARE: drawCare(g2); break;
                case DB: drawDb(g2); break;
                case MVC: drawMvc(g2); break;
                case CRUD: drawCrud(g2); break;
                case MODULE: drawModule(g2); break;
                case BACK: drawBack(g2); break;
                case ADD: drawPlus(g2); break;
                case EDIT: drawEdit(g2); break;
                case DELETE: drawTrash(g2); break;
                case REFRESH: drawRefresh(g2); break;
            }
            g2.dispose();
        }

        private double s(double v) { return v * size / 24.0; }

        private void drawHome(Graphics2D g) {
            Path2D p = new Path2D.Double();
            p.moveTo(s(3), s(11)); p.lineTo(s(12), s(4)); p.lineTo(s(21), s(11));
            g.draw(p); g.draw(new RoundRectangle2D.Double(s(6), s(10), s(12), s(10), s(2), s(2)));
            g.draw(new Line2D.Double(s(10), s(20), s(10), s(15))); g.draw(new Line2D.Double(s(14), s(20), s(14), s(15)));
        }

        private void drawPet(Graphics2D g) {
            g.draw(new Ellipse2D.Double(s(7), s(8), s(10), s(10)));
            g.fill(new Ellipse2D.Double(s(4), s(5), s(4), s(6)));
            g.fill(new Ellipse2D.Double(s(16), s(5), s(4), s(6)));
            g.fill(new Ellipse2D.Double(s(9), s(11), s(1.8), s(1.8)));
            g.fill(new Ellipse2D.Double(s(13), s(11), s(1.8), s(1.8)));
            g.fill(new Ellipse2D.Double(s(11.2), s(14), s(1.6), s(1.3)));
            g.draw(new Arc2D.Double(s(9), s(13), s(6), s(5), 200, 140, Arc2D.OPEN));
        }

        private void drawHealth(Graphics2D g) {
            Path2D p = new Path2D.Double();
            p.moveTo(s(12), s(20));
            p.curveTo(s(3), s(13), s(4), s(6), s(9), s(6));
            p.curveTo(s(11), s(6), s(12), s(8), s(12), s(8));
            p.curveTo(s(12), s(8), s(13), s(6), s(15), s(6));
            p.curveTo(s(20), s(6), s(21), s(13), s(12), s(20));
            g.draw(p);
            g.draw(new Line2D.Double(s(12), s(10), s(12), s(15)));
            g.draw(new Line2D.Double(s(9.5), s(12.5), s(14.5), s(12.5)));
        }

        private void drawService(Graphics2D g) {
            g.draw(new RoundRectangle2D.Double(s(5), s(7), s(14), s(13), s(2), s(2)));
            g.draw(new Line2D.Double(s(8), s(7), s(8), s(4))); g.draw(new Line2D.Double(s(16), s(7), s(16), s(4)));
            g.draw(new Line2D.Double(s(5), s(11), s(19), s(11)));
            g.fill(new Ellipse2D.Double(s(8), s(14), s(2), s(2))); g.fill(new Ellipse2D.Double(s(14), s(14), s(2), s(2)));
        }

        private void drawLost(Graphics2D g) {
            Path2D p = new Path2D.Double();
            p.moveTo(s(12), s(21));
            p.curveTo(s(6), s(14), s(6), s(5), s(12), s(5));
            p.curveTo(s(18), s(5), s(18), s(14), s(12), s(21));
            g.draw(p); g.draw(new Ellipse2D.Double(s(9), s(8), s(6), s(6)));
        }

        private void drawMap(Graphics2D g) {
            g.draw(new RoundRectangle2D.Double(s(4), s(5), s(16), s(14), s(2), s(2)));
            g.draw(new Line2D.Double(s(9), s(6), s(9), s(18)));
            g.draw(new Line2D.Double(s(15), s(6), s(15), s(18)));
            g.fill(new Ellipse2D.Double(s(10), s(8), s(4), s(4)));
            g.draw(new Line2D.Double(s(12), s(12), s(12), s(15)));
        }

        private void drawCommunity(Graphics2D g) {
            g.draw(new Ellipse2D.Double(s(5), s(6), s(5), s(5)));
            g.draw(new Ellipse2D.Double(s(14), s(6), s(5), s(5)));
            g.draw(new Arc2D.Double(s(3), s(12), s(9), s(8), 0, 180, Arc2D.OPEN));
            g.draw(new Arc2D.Double(s(12), s(12), s(9), s(8), 0, 180, Arc2D.OPEN));
        }

        private void drawShop(Graphics2D g) {
            g.draw(new RoundRectangle2D.Double(s(5), s(9), s(14), s(10), s(2), s(2)));
            g.draw(new Line2D.Double(s(7), s(9), s(9), s(5)));
            g.draw(new Line2D.Double(s(17), s(9), s(15), s(5)));
            g.draw(new Line2D.Double(s(9), s(5), s(15), s(5)));
            g.draw(new Line2D.Double(s(8), s(13), s(16), s(13)));
        }

        private void drawUser(Graphics2D g) {
            g.draw(new Ellipse2D.Double(s(8), s(5), s(8), s(8)));
            g.draw(new Arc2D.Double(s(5), s(13), s(14), s(8), 0, 180, Arc2D.OPEN));
        }

        private void drawCare(Graphics2D g) {
            drawHealth(g);
            g.draw(new Ellipse2D.Double(s(7), s(15), s(3), s(3)));
            g.draw(new Ellipse2D.Double(s(14), s(15), s(3), s(3)));
        }

        private void drawDb(Graphics2D g) {
            g.draw(new Ellipse2D.Double(s(5), s(4), s(14), s(5)));
            g.draw(new Line2D.Double(s(5), s(6.5), s(5), s(17)));
            g.draw(new Line2D.Double(s(19), s(6.5), s(19), s(17)));
            g.draw(new Ellipse2D.Double(s(5), s(14.5), s(14), s(5)));
            g.draw(new Arc2D.Double(s(5), s(9), s(14), s(5), 180, 180, Arc2D.OPEN));
        }

        private void drawMvc(Graphics2D g) {
            g.draw(new RoundRectangle2D.Double(s(4), s(5), s(5), s(5), s(1), s(1)));
            g.draw(new RoundRectangle2D.Double(s(15), s(5), s(5), s(5), s(1), s(1)));
            g.draw(new RoundRectangle2D.Double(s(9.5), s(15), s(5), s(5), s(1), s(1)));
            g.draw(new Line2D.Double(s(9), s(8), s(15), s(8)));
            g.draw(new Line2D.Double(s(7), s(10), s(10), s(15)));
            g.draw(new Line2D.Double(s(17), s(10), s(14), s(15)));
        }

        private void drawCrud(Graphics2D g) {
            g.draw(new RoundRectangle2D.Double(s(5), s(4), s(14), s(16), s(2), s(2)));
            g.draw(new Line2D.Double(s(8), s(9), s(16), s(9)));
            g.draw(new Line2D.Double(s(8), s(13), s(16), s(13)));
            g.draw(new Line2D.Double(s(8), s(17), s(13), s(17)));
        }

        private void drawModule(Graphics2D g) {
            for (int r=0; r<2; r++) for (int col=0; col<2; col++)
                g.draw(new RoundRectangle2D.Double(s(5 + col*8), s(5 + r*8), s(6), s(6), s(1.5), s(1.5)));
        }

        private void drawBack(Graphics2D g) { g.draw(new Line2D.Double(s(15), s(6), s(8), s(12))); g.draw(new Line2D.Double(s(8), s(12), s(15), s(18))); }
        private void drawPlus(Graphics2D g) { g.draw(new Line2D.Double(s(12), s(5), s(12), s(19))); g.draw(new Line2D.Double(s(5), s(12), s(19), s(12))); }
        private void drawEdit(Graphics2D g) { g.draw(new Line2D.Double(s(7), s(17), s(17), s(7))); g.draw(new Line2D.Double(s(15), s(5), s(19), s(9))); g.draw(new Line2D.Double(s(6), s(18), s(10), s(17))); }
        private void drawTrash(Graphics2D g) { g.draw(new Rectangle2D.Double(s(7), s(8), s(10), s(12))); g.draw(new Line2D.Double(s(5), s(8), s(19), s(8))); g.draw(new Line2D.Double(s(10), s(5), s(14), s(5))); }
        private void drawRefresh(Graphics2D g) { g.draw(new Arc2D.Double(s(5), s(5), s(14), s(14), 40, 270, Arc2D.OPEN)); g.draw(new Line2D.Double(s(17), s(6), s(19), s(11))); g.draw(new Line2D.Double(s(17), s(6), s(12), s(6))); }
    }
}
