package controller;

import exception.AppException;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import util.IconFactory;
import util.UiStyle;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final UserService userService = new UserServiceImpl();
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public LoginFrame() {
        initialize();
    }

    private void initialize() {
        setTitle("PetLife 會員登入");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 620);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel(null);
        root.setBackground(UiStyle.BG);
        setContentPane(root);

        UiStyle.GradientPanel left = new UiStyle.GradientPanel(new Color(47, 128, 237), new Color(86, 204, 242), 0);
        left.setBounds(0, 0, 430, 620);
        root.add(left);

        JLabel petIcon = new JLabel(IconFactory.icon(IconFactory.Type.PET, 118, new Color(17, 52, 91)));
        petIcon.setBounds(145, 110, 140, 130);
        left.add(petIcon);

        JLabel brand = new JLabel("PetLife");
        brand.setFont(UiStyle.font(Font.BOLD, 38));
        brand.setForeground(Color.WHITE);
        brand.setBounds(64, 260, 280, 48);
        left.add(brand);

        JTextArea slogan = new JTextArea("智慧寵物生活整合平台\n登入後即可管理寵物資料、健康照護、預約服務與走失協尋。");
        slogan.setOpaque(false);
        slogan.setEditable(false);
        slogan.setLineWrap(true);
        slogan.setWrapStyleWord(true);
        slogan.setForeground(new Color(240, 248, 255));
        slogan.setFont(UiStyle.font(Font.PLAIN, 17));
        slogan.setBounds(64, 318, 310, 90);
        left.add(slogan);

        JLabel law = new JLabel("符合台灣飼養情境｜畢業專題展示版");
        law.setFont(UiStyle.font(Font.PLAIN, 13));
        law.setForeground(new Color(245, 250, 255));
        law.setBounds(64, 510, 320, 24);
        left.add(law);

        UiStyle.RoundedPanel card = new UiStyle.RoundedPanel(30, Color.WHITE);
        card.setBounds(500, 70, 380, 460);
        root.add(card);

        JLabel title = new JLabel("會員登入");
        title.setFont(UiStyle.font(Font.BOLD, 30));
        title.setForeground(UiStyle.TEXT);
        title.setBounds(42, 40, 220, 42);
        card.add(title);

        JLabel sub = new JLabel("請使用 Email 與密碼登入 PetLife");
        sub.setFont(UiStyle.font(Font.PLAIN, 14));
        sub.setForeground(UiStyle.MUTED);
        sub.setBounds(44, 82, 280, 28);
        card.add(sub);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(UiStyle.font(Font.BOLD, 14));
        emailLabel.setForeground(UiStyle.TEXT);
        emailLabel.setBounds(44, 134, 100, 24);
        card.add(emailLabel);

        txtEmail = new JTextField("demo@petlife.com");
        txtEmail.setBounds(44, 162, 292, 40);
        UiStyle.styleField(txtEmail);
        card.add(txtEmail);

        JLabel passLabel = new JLabel("密碼");
        passLabel.setFont(UiStyle.font(Font.BOLD, 14));
        passLabel.setForeground(UiStyle.TEXT);
        passLabel.setBounds(44, 218, 100, 24);
        card.add(passLabel);

        txtPassword = new JPasswordField("123456");
        txtPassword.setBounds(44, 246, 292, 40);
        UiStyle.styleField(txtPassword);
        card.add(txtPassword);

        JButton loginBtn = UiStyle.primaryButton("登入系統");
        loginBtn.setBounds(44, 318, 292, 42);
        loginBtn.setIcon(IconFactory.icon(IconFactory.Type.HOME, 18, Color.WHITE));
        loginBtn.addActionListener(e -> doLogin());
        card.add(loginBtn);

        JButton registerBtn = UiStyle.secondaryButton("尚未有帳號？前往註冊");
        registerBtn.setBounds(44, 376, 292, 42);
        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });
        card.add(registerBtn);

        JLabel demo = new JLabel("測試帳號：demo@petlife.com / 123456");
        demo.setFont(UiStyle.font(Font.PLAIN, 12));
        demo.setForeground(UiStyle.MUTED);
        demo.setBounds(56, 424, 280, 24);
        card.add(demo);
    }

    private void doLogin() {
        try {
            User user = userService.login(txtEmail.getText(), new String(txtPassword.getPassword()));
            JOptionPane.showMessageDialog(this, "歡迎回來，" + user.getName() + "！");
            dispose();
            new HomeFrame().setVisible(true);
        } catch (AppException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "登入失敗", JOptionPane.ERROR_MESSAGE);
        }
    }
}
