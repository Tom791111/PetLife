package controller;

import exception.AppException;
import service.UserService;
import service.impl.UserServiceImpl;
import util.IconFactory;
import util.UiStyle;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private final UserService userService = new UserServiceImpl();
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirm;
    private JComboBox<String> comboRole;

    public RegisterFrame() {
        initialize();
    }

    private void initialize() {
        setTitle("PetLife 會員註冊");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 680);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel(null);
        root.setBackground(UiStyle.BG);
        setContentPane(root);

        UiStyle.GradientPanel banner = new UiStyle.GradientPanel(new Color(47, 128, 237), new Color(86, 204, 242), 28);
        banner.setBounds(48, 36, 884, 132);
        root.add(banner);

        JLabel title = new JLabel("建立 PetLife 帳號");
        title.setFont(UiStyle.font(Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(36, 26, 360, 42);
        banner.add(title);

        JLabel sub = new JLabel("註冊會員後可使用 MySQL 儲存登入資料，並開始管理你的毛孩生活紀錄。");
        sub.setFont(UiStyle.font(Font.PLAIN, 16));
        sub.setForeground(new Color(238, 248, 255));
        sub.setBounds(38, 72, 680, 28);
        banner.add(sub);

        JLabel icon = new JLabel(IconFactory.icon(IconFactory.Type.PET, 82, new Color(17, 52, 91)));
        icon.setBounds(740, 24, 100, 90);
        banner.add(icon);

        UiStyle.RoundedPanel card = new UiStyle.RoundedPanel(30, Color.WHITE);
        card.setBounds(260, 198, 460, 390);
        root.add(card);

        txtName = addField(card, "姓名", 42, 34, false);
        txtEmail = addField(card, "Email", 42, 104, false);
        txtPhone = addField(card, "手機", 42, 174, false);
        txtPassword = (JPasswordField) addField(card, "密碼（至少 6 碼）", 250, 34, true);
        txtConfirm = (JPasswordField) addField(card, "確認密碼", 250, 104, true);

        JLabel roleLabel = new JLabel("身分類型");
        roleLabel.setFont(UiStyle.font(Font.BOLD, 14));
        roleLabel.setForeground(UiStyle.TEXT);
        roleLabel.setBounds(250, 174, 120, 24);
        card.add(roleLabel);

        comboRole = new JComboBox<>(new String[]{"飼主", "寵物保母", "寵物店家", "獸醫/照護人員"});
        comboRole.setBounds(250, 202, 168, 38);
        card.add(comboRole);

        JButton registerBtn = UiStyle.primaryButton("完成註冊");
        registerBtn.setBounds(42, 282, 178, 42);
        registerBtn.addActionListener(e -> doRegister());
        card.add(registerBtn);

        JButton backBtn = UiStyle.secondaryButton("返回登入");
        backBtn.setBounds(240, 282, 178, 42);
        backBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        card.add(backBtn);

        JLabel hint = new JLabel("註冊成功後會自動回到登入畫面");
        hint.setFont(UiStyle.font(Font.PLAIN, 12));
        hint.setForeground(UiStyle.MUTED);
        hint.setBounds(42, 340, 300, 22);
        card.add(hint);
    }

    private JTextField addField(JPanel parent, String label, int x, int y, boolean password) {
        JLabel l = new JLabel(label);
        l.setFont(UiStyle.font(Font.BOLD, 14));
        l.setForeground(UiStyle.TEXT);
        l.setBounds(x, y, 180, 24);
        parent.add(l);
        JTextField field = password ? new JPasswordField() : new JTextField();
        field.setBounds(x, y + 28, 168, 38);
        UiStyle.styleField(field);
        parent.add(field);
        return field;
    }

    private void doRegister() {
        try {
            userService.register(
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    new String(txtPassword.getPassword()),
                    new String(txtConfirm.getPassword()),
                    String.valueOf(comboRole.getSelectedItem())
            );
            JOptionPane.showMessageDialog(this, "註冊成功，請登入系統");
            dispose();
            new LoginFrame().setVisible(true);
        } catch (AppException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "註冊失敗", JOptionPane.ERROR_MESSAGE);
        }
    }
}
