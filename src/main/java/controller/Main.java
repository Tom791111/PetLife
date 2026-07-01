package controller;

import util.UiStyle;
import util.DatabaseSetup;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        UiStyle.applyGlobalStyle();

        try {
            DatabaseSetup.initializeDatabase();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "PetLife 無法連線 MySQL，程式沒有壞掉。\n\n" +
                    "請確認：\n" +
                    "1. MySQL 8.0 已啟動\n" +
                    "2. db.properties 的 username / password 正確\n" +
                    "3. 連線位置是 localhost:3306\n\n" +
                    "錯誤訊息：" + e.getMessage(),
                    "PetLife v8 資料庫連線提醒",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
