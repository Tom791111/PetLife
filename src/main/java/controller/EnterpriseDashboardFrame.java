package controller;

import util.IconFactory;
import util.MapUtil;
import util.UiStyle;

import javax.swing.*;
import java.awt.*;

/**
 * PetLife v9 Enterprise：企業版功能總覽。
 * 這一頁用來展示畢業專題的完整系統規模，並提供資料表維護入口。
 */
public class EnterpriseDashboardFrame extends JFrame {
    public EnterpriseDashboardFrame() { initialize(); }

    private void initialize() {
        setTitle("PetLife v9 Enterprise 企業版功能中心");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1120, 720);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(null);
        root.setBackground(UiStyle.BG);
        setContentPane(root);

        JLabel icon = new JLabel(IconFactory.icon(IconFactory.Type.MODULE, 36, UiStyle.PRIMARY_DARK));
        icon.setBounds(45, 34, 40, 40);
        root.add(icon);

        JLabel title = new JLabel("PetLife v9 Enterprise 企業版");
        title.setFont(UiStyle.font(Font.BOLD, 30));
        title.setForeground(UiStyle.TEXT);
        title.setBounds(92, 30, 520, 42);
        root.add(title);

        JLabel subtitle = new JLabel("整合會員、寵物、醫療、疫苗、提醒、服務、地點、評價、收藏、通知、照片與社群資料管理。");
        subtitle.setFont(UiStyle.font(Font.PLAIN, 15));
        subtitle.setForeground(UiStyle.MUTED);
        subtitle.setBounds(92, 76, 760, 24);
        root.add(subtitle);

        JButton back = UiStyle.secondaryButton("返回首頁");
        back.setIcon(IconFactory.icon(IconFactory.Type.BACK, 16, UiStyle.PRIMARY_DARK));
        back.setBounds(900, 30, 130, 38);
        back.addActionListener(e -> { dispose(); new HomeFrame().setVisible(true); });
        root.add(back);

        UiStyle.GradientPanel hero = new UiStyle.GradientPanel(new Color(47,128,237), new Color(86,204,242), 30);
        hero.setBounds(45, 116, 985, 118);
        root.add(hero);
        JLabel h = new JLabel("畢業專題展示重點：資料庫完整度 + MVC/DAO 架構 + 商業化功能模組");
        h.setFont(UiStyle.font(Font.BOLD, 22));
        h.setForeground(Color.WHITE);
        h.setBounds(30, 22, 780, 32);
        hero.add(h);
        JLabel p = new JLabel("v9 新增 15+ 資料表、角色權限欄位、Dashboard 管理入口、Google Maps 服務連結與企業版 README。 ");
        p.setFont(UiStyle.font(Font.PLAIN, 15));
        p.setForeground(new Color(238,248,255));
        p.setBounds(30, 62, 860, 28);
        hero.add(p);

        addStat(root, "15+", "資料表", 45, 260);
        addStat(root, "3", "角色權限", 270, 260);
        addStat(root, "MVC", "專案架構", 495, 260);
        addStat(root, "MySQL 8", "資料庫", 720, 260);

        int y1 = 370;
        addModule(root, "疫苗紀錄", "vaccination_records", "疫苗名稱、施打醫院、下次提醒、狀態", 45, y1);
        addModule(root, "醫療紀錄", "medical_records", "症狀、診斷、藥物、費用、就診醫院", 370, y1);
        addModule(root, "通知中心", "notifications", "使用者提醒、已讀未讀、系統訊息", 695, y1);

        int y2 = 505;
        addModule(root, "寵物照片", "pet_photos", "寵物相簿、成長紀錄、圖片路徑", 45, y2);
        addModule(root, "收藏地點", "favorites", "收藏醫院、美容、餐廳、公園", 370, y2);
        addModule(root, "服務評價", "reviews", "星等、評論、服務體驗回饋", 695, y2);

        JButton maps = UiStyle.primaryButton("開啟 Google Maps 寵物服務搜尋");
        maps.setBounds(45, 638, 250, 38);
        maps.addActionListener(e -> MapUtil.openGoogleMapsSearch(this, "附近動物醫院 寵物美容 寵物旅館"));
        root.add(maps);
    }

    private void addStat(JPanel root, String number, String label, int x, int y) {
        UiStyle.RoundedPanel card = new UiStyle.RoundedPanel(24, Color.WHITE);
        card.setBounds(x, y, 195, 78);
        root.add(card);
        JLabel n = new JLabel(number);
        n.setFont(UiStyle.font(Font.BOLD, 28));
        n.setForeground(UiStyle.PRIMARY_DARK);
        n.setBounds(20, 10, 150, 34);
        card.add(n);
        JLabel l = new JLabel(label);
        l.setFont(UiStyle.font(Font.PLAIN, 14));
        l.setForeground(UiStyle.MUTED);
        l.setBounds(22, 45, 150, 22);
        card.add(l);
    }

    private void addModule(JPanel root, String title, String table, String desc, int x, int y) {
        UiStyle.RoundedPanel card = new UiStyle.RoundedPanel(28, Color.WHITE);
        card.setBounds(x, y, 295, 105);
        root.add(card);
        JLabel t = new JLabel(title);
        t.setFont(UiStyle.font(Font.BOLD, 20));
        t.setForeground(UiStyle.TEXT);
        t.setBounds(22, 12, 180, 28);
        card.add(t);
        JLabel tb = new JLabel(table);
        tb.setFont(UiStyle.font(Font.BOLD, 12));
        tb.setForeground(UiStyle.PRIMARY_DARK);
        tb.setOpaque(true);
        tb.setBackground(UiStyle.SOFT_BLUE);
        tb.setBounds(22, 44, 150, 24);
        tb.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        card.add(tb);
        JLabel d = new JLabel(desc);
        d.setFont(UiStyle.font(Font.PLAIN, 13));
        d.setForeground(UiStyle.MUTED);
        d.setBounds(22, 72, 250, 22);
        card.add(d);
    }
}
