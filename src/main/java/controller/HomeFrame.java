package controller;

import util.IconFactory;
import util.UiStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeFrame extends JFrame {
    private static final Color SIDEBAR = new Color(248, 251, 255);
    private static final Color SIDEBAR_ITEM = Color.WHITE;
    private static final Color SIDEBAR_ITEM_ACTIVE = new Color(47, 128, 237);
    private static final Color SIDEBAR_ITEM_HOVER = new Color(232, 242, 255);
    private static final Color SIDEBAR_TEXT = new Color(31, 41, 55);
    private static final Color SIDEBAR_ICON = new Color(37, 99, 235);

    public HomeFrame() { initialize(); }

    private void initialize() {
        setTitle("PetLife 智慧寵物整合平台 v9 Enterprise");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 720);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(null);
        root.setBackground(UiStyle.BG);
        setContentPane(root);

        UiStyle.RoundedPanel sidebar = new UiStyle.RoundedPanel(0, SIDEBAR);
        sidebar.setBounds(0, 0, 220, 720);
        root.add(sidebar);

        JLabel logoIcon = new JLabel(IconFactory.icon(IconFactory.Type.PET, 32, SIDEBAR_ICON));
        logoIcon.setBounds(26, 35, 34, 34);
        sidebar.add(logoIcon);

        JLabel logo = new JLabel("PetLife");
        logo.setFont(UiStyle.font(Font.BOLD, 26));
        logo.setForeground(SIDEBAR_TEXT);
        logo.setBounds(64, 30, 140, 42);
        sidebar.add(logo);

        addNav(sidebar, "首頁總覽", IconFactory.Type.HOME, 100, true, e -> {});
        addNav(sidebar, "我的寵物", IconFactory.Type.PET, 150, false, e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.PET)));
        addNav(sidebar, "健康照護", IconFactory.Type.HEALTH, 200, false, e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.HEALTH)));
        addNav(sidebar, "預約服務", IconFactory.Type.SERVICE, 250, false, e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.SERVICE)));
        addNav(sidebar, "寵物地圖", IconFactory.Type.MAP, 300, false, e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.MAP)));
        addNav(sidebar, "走失協尋", IconFactory.Type.LOST, 350, false, e -> open(new LostReportFrame()));
        addNav(sidebar, "企業中心", IconFactory.Type.MODULE, 400, false, e -> open(new EnterpriseDashboardFrame()));
        addNav(sidebar, "登出", IconFactory.Type.HOME, 450, false, e -> { dispose(); new LoginFrame().setVisible(true); });

        JLabel law = new JLabel("符合台灣飼養情境");
        law.setFont(UiStyle.font(Font.PLAIN, 13));
        law.setForeground(new Color(75, 85, 99));
        law.setBounds(28, 600, 160, 24);
        sidebar.add(law);

        JLabel version = new JLabel("v9 Enterprise：畢業專題企業版");
        version.setFont(UiStyle.font(Font.PLAIN, 12));
        version.setForeground(new Color(75, 85, 99));
        version.setBounds(28, 624, 178, 22);
        sidebar.add(version);

        UiStyle.GradientPanel hero = new UiStyle.GradientPanel(new Color(47, 128, 237), new Color(86, 204, 242), 32);
        hero.setBounds(250, 26, 820, 188);
        root.add(hero);

        JLabel title = new JLabel("智慧寵物生活整合平台");
        title.setFont(UiStyle.font(Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(34, 28, 520, 44);
        hero.add(title);

        JTextArea intro = new JTextArea("整合毛孩資料、健康照護、預約服務、寵物地圖與走失協尋。\n以卡片式入口與現代化導覽，打造更接近 Behance / Figma 的畢業專題展示型 UI。 ");
        intro.setFont(UiStyle.font(Font.PLAIN, 16));
        intro.setForeground(new Color(238, 248, 255));
        intro.setOpaque(false);
        intro.setEditable(false);
        intro.setLineWrap(true);
        intro.setWrapStyleWord(true);
        intro.setBounds(36, 78, 560, 58);
        hero.add(intro);

        JButton start = UiStyle.primaryButton("開始管理寵物");
        start.setBackground(Color.WHITE);
        start.setForeground(UiStyle.PRIMARY_DARK);
        start.setIcon(IconFactory.icon(IconFactory.Type.PET, 18, UiStyle.PRIMARY_DARK));
        start.setBounds(36, 142, 170, 38);
        start.addActionListener(e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.PET)));
        hero.add(start);

        JLabel petVisual = new JLabel(IconFactory.icon(IconFactory.Type.PET, 112, new Color(17, 52, 91)));
        petVisual.setBounds(642, 42, 130, 120);
        hero.add(petVisual);

        addBubble(hero, 612, 38, 36, UiStyle.SOFT_ORANGE);
        addBubble(hero, 750, 120, 48, new Color(224, 247, 250));
        addBubble(hero, 720, 30, 26, new Color(255, 255, 255, 160));

        addFeatureButton(root, IconFactory.Type.PET, "我的寵物", "建立毛孩身份與晶片資料", "基本資料 / 品種 / 晶片", 250, 238, e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.PET)));
        addFeatureButton(root, IconFactory.Type.HEALTH, "健康照護", "紀錄日常健康與照護狀態", "體重 / 飲食 / 用藥", 458, 238, e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.HEALTH)));
        addFeatureButton(root, IconFactory.Type.SERVICE, "預約服務", "連結地圖查找寵物服務", "醫院 / 美容 / 旅館", 666, 238, e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.SERVICE)));
        addFeatureButton(root, IconFactory.Type.MAP, "寵物地圖", "探索附近寵物友善生活圈", "公園 / 餐廳 / 醫院", 874, 238, e -> open(new FeatureMenuFrame(FeatureMenuFrame.Feature.MAP)));

        addCard(root, IconFactory.Type.PET, "寵物資料管理", "管理名字、種類、品種、年齡、體重、晶片號碼與健康備註。", "適合放入寵物身分與合法飼養資料", 250, 345, e -> open(new PetFrame()));
        addCard(root, IconFactory.Type.HEALTH, "健康照護紀錄", "紀錄體重、食慾、排泄、症狀與備註，建立長期健康追蹤。", "強化照護日誌與就醫前整理", 680, 345, e -> open(new HealthRecordFrame()));
        addCard(root, IconFactory.Type.SERVICE, "寵物服務預約", "管理美容、旅館、保姆、散步、獸醫預約與到府照護。", "解決服務資訊分散與預約管理", 250, 520, e -> open(new ServiceBookingFrame()));
        addCard(root, IconFactory.Type.LOST, "走失協尋通報", "建立走失日期、地點、電話、狀態與特徵描述。", "可延伸為地圖通報與社區協尋", 680, 520, e -> open(new LostReportFrame()));
    }

    private void addNav(JPanel parent, String text, IconFactory.Type iconType, int y, boolean active, java.awt.event.ActionListener action) {
        Color bg = active ? SIDEBAR_ITEM_ACTIVE : SIDEBAR_ITEM;
        Color fg = active ? Color.WHITE : SIDEBAR_TEXT;
        Color iconColor = active ? Color.WHITE : SIDEBAR_ICON;
        JButton nav = new JButton(text, IconFactory.icon(iconType, 20, iconColor));
        nav.setHorizontalAlignment(SwingConstants.LEFT);
        nav.setIconTextGap(12);
        nav.setOpaque(true);
        nav.setContentAreaFilled(true);
        nav.setBackground(bg);
        nav.setForeground(fg);
        nav.setFont(UiStyle.font(Font.BOLD, 15));
        nav.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(active ? SIDEBAR_ITEM_ACTIVE : new Color(219, 229, 242), 1),
                BorderFactory.createEmptyBorder(0, 18, 0, 0)));
        nav.setFocusPainted(false);
        nav.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nav.setBounds(22, y, 170, 38);
        nav.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!active) {
                    nav.setBackground(SIDEBAR_ITEM_HOVER);
                    nav.setForeground(new Color(30, 58, 138));
                    nav.setIcon(IconFactory.icon(iconType, 20, SIDEBAR_ICON));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (!active) {
                    nav.setBackground(SIDEBAR_ITEM);
                    nav.setForeground(SIDEBAR_TEXT);
                    nav.setIcon(IconFactory.icon(iconType, 20, SIDEBAR_ICON));
                }
            }
        });
        nav.addActionListener(action);
        parent.add(nav);
    }

    private void addFeatureButton(JPanel root, IconFactory.Type iconType, String title, String desc, String tag, int x, int y, java.awt.event.ActionListener action) {
        UiStyle.RoundedPanel card = new UiStyle.RoundedPanel(24, Color.WHITE);
        card.setBounds(x, y, 176, 98);
        root.add(card);
        JLabel icon = new JLabel(IconFactory.icon(iconType, 30, UiStyle.PRIMARY_DARK));
        icon.setBounds(18, 14, 34, 34);
        card.add(icon);
        JLabel t = new JLabel(title);
        t.setFont(UiStyle.font(Font.BOLD, 20));
        t.setForeground(UiStyle.PRIMARY_DARK);
        t.setBounds(58, 12, 110, 28);
        card.add(t);
        JLabel d = new JLabel(desc);
        d.setFont(UiStyle.font(Font.PLAIN, 12));
        d.setForeground(UiStyle.MUTED);
        d.setBounds(18, 47, 145, 18);
        card.add(d);
        JLabel chip = new JLabel(tag);
        chip.setFont(UiStyle.font(Font.BOLD, 11));
        chip.setForeground(UiStyle.PRIMARY_DARK);
        chip.setBounds(18, 70, 148, 18);
        card.add(chip);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { action.actionPerformed(null); } });
    }

    private void addCard(JPanel root, IconFactory.Type iconType, String title, String desc, String tag, int x, int y, java.awt.event.ActionListener action) {
        UiStyle.RoundedPanel card = new UiStyle.RoundedPanel(28, Color.WHITE);
        card.setBounds(x, y, 390, 136);
        root.add(card);

        JLabel icon = new JLabel(IconFactory.icon(iconType, 28, UiStyle.PRIMARY_DARK));
        icon.setBounds(22, 17, 32, 32);
        card.add(icon);

        JLabel t = new JLabel(title);
        t.setFont(UiStyle.font(Font.BOLD, 20));
        t.setForeground(UiStyle.TEXT);
        t.setBounds(62, 16, 260, 30);
        card.add(t);

        JTextArea d = new JTextArea(desc);
        d.setFont(UiStyle.font(Font.PLAIN, 14));
        d.setForeground(UiStyle.MUTED);
        d.setLineWrap(true);
        d.setWrapStyleWord(true);
        d.setEditable(false);
        d.setOpaque(false);
        d.setBounds(24, 50, 250, 40);
        card.add(d);

        JLabel chip = new JLabel(tag);
        chip.setOpaque(true);
        chip.setBackground(UiStyle.SOFT_BLUE);
        chip.setForeground(UiStyle.PRIMARY_DARK);
        chip.setFont(UiStyle.font(Font.BOLD, 12));
        chip.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        chip.setBounds(22, 98, 230, 24);
        card.add(chip);

        JButton btn = UiStyle.primaryButton("進入 →");
        btn.setIcon(IconFactory.icon(iconType, 16, Color.WHITE));
        btn.setBounds(286, 82, 82, 38);
        btn.addActionListener(action);
        card.add(btn);
    }

    private void addBubble(JPanel parent, int x, int y, int size, Color color) {
        JPanel bubble = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        bubble.setOpaque(false);
        bubble.setBounds(x, y, size, size);
        parent.add(bubble);
    }

    private void open(JFrame frame) {
        frame.setVisible(true);
        dispose();
    }
}
