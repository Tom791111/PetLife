package controller;

import util.IconFactory;
import util.UiStyle;
import util.MapUtil;

import javax.swing.*;
import java.awt.*;

public class FeatureMenuFrame extends JFrame {
    public enum Feature { PET, HEALTH, SERVICE, MAP }

    private final Feature feature;

    public FeatureMenuFrame(Feature feature) {
        this.feature = feature;
        initialize();
    }

    private void initialize() {
        setTitle("PetLife v7 Ultimate - 功能選項");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(null);
        root.setBackground(UiStyle.BG);
        setContentPane(root);

        JButton back = UiStyle.secondaryButton("返回首頁");
        back.setIcon(IconFactory.icon(IconFactory.Type.BACK, 16, UiStyle.PRIMARY_DARK));
        back.setBounds(900, 28, 125, 36);
        back.addActionListener(e -> { new HomeFrame().setVisible(true); dispose(); });
        root.add(back);

        IconFactory.Type iconType = iconType();
        JLabel icon = new JLabel(IconFactory.icon(iconType, 40, UiStyle.PRIMARY_DARK));
        icon.setBounds(42, 32, 46, 46);
        root.add(icon);

        JLabel title = UiStyle.title(titleText());
        title.setBounds(96, 30, 520, 42);
        root.add(title);

        JLabel subtitle = new JLabel(subtitleText());
        subtitle.setFont(UiStyle.font(Font.PLAIN, 16));
        subtitle.setForeground(UiStyle.MUTED);
        subtitle.setBounds(98, 72, 760, 28);
        root.add(subtitle);

        UiStyle.GradientPanel banner = new UiStyle.GradientPanel(new Color(47, 128, 237), new Color(86, 204, 242), 32);
        banner.setBounds(36, 118, 990, 135);
        root.add(banner);

        JLabel bTitle = new JLabel(bannerTitle());
        bTitle.setFont(UiStyle.font(Font.BOLD, 26));
        bTitle.setForeground(Color.WHITE);
        bTitle.setBounds(32, 24, 600, 34);
        banner.add(bTitle);

        JTextArea bText = new JTextArea(bannerText());
        bText.setOpaque(false);
        bText.setEditable(false);
        bText.setLineWrap(true);
        bText.setWrapStyleWord(true);
        bText.setFont(UiStyle.font(Font.PLAIN, 15));
        bText.setForeground(new Color(242, 250, 255));
        bText.setBounds(34, 64, 690, 52);
        banner.add(bText);

        JLabel bIcon = new JLabel(IconFactory.icon(iconType, 82, new Color(19, 64, 122)));
        bIcon.setBounds(840, 26, 92, 92);
        banner.add(bIcon);

        String[][] options = options();
        for (int i = 0; i < options.length; i++) {
            int x = 55 + (i % 3) * 320;
            int y = 292 + (i / 3) * 150;
            addOption(root, options[i][0], options[i][1], optionIcon(i), x, y, i);
        }
    }

    private void addOption(JPanel root, String title, String desc, IconFactory.Type type, int x, int y, int index) {
        UiStyle.RoundedPanel card = new UiStyle.RoundedPanel(30, Color.WHITE);
        card.setBounds(x, y, 280, 118);
        root.add(card);

        JLabel icon = new JLabel(IconFactory.icon(type, 30, UiStyle.PRIMARY_DARK));
        icon.setBounds(22, 20, 36, 36);
        card.add(icon);

        JLabel t = new JLabel(title);
        t.setFont(UiStyle.font(Font.BOLD, 18));
        t.setForeground(UiStyle.TEXT);
        t.setBounds(66, 17, 190, 30);
        card.add(t);

        JTextArea d = new JTextArea(desc);
        d.setOpaque(false);
        d.setEditable(false);
        d.setLineWrap(true);
        d.setWrapStyleWord(true);
        d.setFont(UiStyle.font(Font.PLAIN, 13));
        d.setForeground(UiStyle.MUTED);
        d.setBounds(24, 55, 226, 36);
        card.add(d);

        JButton btn = UiStyle.primaryButton((feature == Feature.MAP || feature == Feature.SERVICE) && index <= 4 ? "查看附近" : "進入");
        btn.setBounds(170, 78, 86, 32);
        btn.addActionListener(e -> openTarget(index));
        card.add(btn);
    }

    private void openTarget(int index) {
        if (feature == Feature.PET) {
            if (index == 0 || index == 1 || index == 2) open(new PetFrame()); else showComingSoon("相簿 / 成長日記 / 家人共享");
        } else if (feature == Feature.HEALTH) {
            if (index <= 4) open(new HealthRecordFrame()); else showComingSoon("AI 健康分析：依健康紀錄做趨勢提醒，不提供醫療診斷");
        } else if (feature == Feature.SERVICE) {
            if (index == 0) {
                MapUtil.openGoogleMapsSearch(this, "附近動物醫院");
            } else if (index == 1) {
                MapUtil.openGoogleMapsSearch(this, "附近寵物美容");
            } else if (index == 2) {
                MapUtil.openGoogleMapsSearch(this, "附近寵物旅館");
            } else if (index == 3) {
                MapUtil.openGoogleMapsSearch(this, "寵物保母");
            } else if (index == 4) {
                MapUtil.openGoogleMapsSearch(this, "寵物散步服務");
            } else {
                open(new ServiceBookingFrame());
            }
        } else {
            if (index == 0) {
                MapUtil.openGoogleMapsSearch(this, "附近動物醫院");
            } else if (index == 1) {
                MapUtil.openGoogleMapsSearch(this, "24小時動物醫院");
            } else if (index == 2) {
                MapUtil.openGoogleMapsSearch(this, "附近寵物公園");
            } else if (index == 3) {
                MapUtil.openGoogleMapsSearch(this, "附近寵物友善餐廳");
            } else if (index == 4) {
                MapUtil.openGoogleMapsSearch(this, "附近寵物用品店");
            } else {
                open(new LostReportFrame());
            }
        }
    }

    private void open(JFrame frame) { frame.setVisible(true); dispose(); }
    private void showComingSoon(String name) { JOptionPane.showMessageDialog(this, name + " 可作為下一階段延伸功能。", "PetLife v7 Ultimate", JOptionPane.INFORMATION_MESSAGE); }

    private IconFactory.Type iconType() {
        switch (feature) {
            case PET: return IconFactory.Type.PET;
            case HEALTH: return IconFactory.Type.HEALTH;
            case SERVICE: return IconFactory.Type.SERVICE;
            default: return IconFactory.Type.MAP;
        }
    }
    private IconFactory.Type optionIcon(int i) {
        IconFactory.Type[] all = { IconFactory.Type.PET, IconFactory.Type.HEALTH, IconFactory.Type.SERVICE, IconFactory.Type.MAP, IconFactory.Type.LOST, IconFactory.Type.COMMUNITY };
        return all[i % all.length];
    }
    private String titleText() {
        switch (feature) {
            case PET: return "我的寵物";
            case HEALTH: return "健康照護";
            case SERVICE: return "預約服務";
            default: return "寵物地圖";
        }
    }
    private String subtitleText() {
        switch (feature) {
            case PET: return "管理寵物身份、晶片、品種、健康備註與成長資料。";
            case HEALTH: return "建立每日照護紀錄，協助飼主整理就醫前資訊。";
            case SERVICE: return "連結 Google Map 快速查找寵物旅館、動物醫院、寵物美容與寵物保母。";
            default: return "連結 Google Map，快速查找動物醫院、24小時動物醫院、寵物公園與友善餐廳。";
        }
    }
    private String bannerTitle() { return titleText() + "功能中心"; }
    private String bannerText() {
        switch (feature) {
            case PET: return "以寵物為核心建立完整資料，讓飼主能快速查看晶片、體重、年齡、品種與照護備註。";
            case HEALTH: return "將體重、食慾、排泄、症狀與用藥資料集中管理，讓健康變化更容易被發現。";
            case SERVICE: return "將寵物服務與 Google Map 串接，快速查找附近動物醫院、寵物美容、寵物旅館、寵物保母與散步服務。";
            default: return "點選卡片可直接開啟 Google Map 搜尋結果，協助飼主快速找到附近動物醫院、24小時動物醫院、寵物公園、友善餐廳與用品店。";
        }
    }
    private String[][] options() {
        switch (feature) {
            case PET: return new String[][]{
                    {"新增寵物", "建立毛孩基本資料與晶片資訊"}, {"寵物列表", "查看與修改所有寵物資料"}, {"健康備註", "整理過敏、疾病與照護習慣"},
                    {"成長日記", "記錄照片、影片與生活片段"}, {"寵物相簿", "作品集可延伸的視覺功能"}, {"家人共享", "讓家人共同管理毛孩資料"}
            };
            case HEALTH: return new String[][]{
                    {"今日健康", "快速紀錄體重、食慾與精神"}, {"飲食紀錄", "追蹤每日食量與飲水狀態"}, {"排泄紀錄", "記錄便便狀態與異常提醒"},
                    {"用藥提醒", "協助飼主避免忘記餵藥"}, {"疫苗管理", "建立疫苗與回診提醒"}, {"AI健康分析", "依趨勢提示風險，不做診斷"}
            };
            case SERVICE: return new String[][]{
                    {"動物醫院", "開啟 Google Map 搜尋附近動物醫院"}, {"寵物美容", "開啟 Google Map 搜尋附近寵物美容"}, {"寵物旅館", "開啟 Google Map 搜尋附近寵物旅館"},
                    {"寵物保母", "開啟 Google Map 搜尋寵物保母服務"}, {"散步服務", "開啟 Google Map 搜尋寵物散步服務"}, {"預約紀錄", "回到系統內建立服務預約 CRUD"}
            };
            default: return new String[][]{
                    {"動物醫院", "開啟 Google Map 搜尋附近動物醫院"}, {"24小時急診", "開啟 Google Map 搜尋24小時動物醫院"}, {"寵物公園", "開啟 Google Map 搜尋附近寵物公園"},
                    {"友善餐廳", "開啟 Google Map 搜尋寵物友善餐廳"}, {"用品店", "開啟 Google Map 搜尋附近寵物用品店"}, {"走失協尋", "建立走失資料並協尋"}
            };
        }
    }
}
