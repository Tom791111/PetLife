package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import exception.AppException;

public class DbUtil {
    private static final Properties PROPS = new Properties();

    static {
        loadProperties(PROPS);
        try {
            Class.forName(PROPS.getProperty("driver"));
        } catch (Exception e) {
            throw new AppException("MySQL Driver 載入失敗，請確認 Maven 已打包 mysql-connector-j", e);
        }
    }

    private static void loadProperties(Properties props) {
        File external = new File("db.properties");
        try (InputStream input = external.exists()
                ? new FileInputStream(external)
                : DbUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new AppException("找不到 db.properties，請確認 src/main/resources/db.properties 或 JAR 同層 db.properties 是否存在");
            }
            props.load(input);
        } catch (Exception e) {
            throw new AppException("資料庫設定載入失敗", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    PROPS.getProperty("url"),
                    PROPS.getProperty("username"),
                    PROPS.getProperty("password")
            );
        } catch (Exception e) {
            throw new AppException("MySQL 連線失敗，請確認資料庫、帳號密碼與 MySQL 服務是否正常", e);
        }
    }

    public static String getDbInfo() {
        return PROPS.getProperty("url") + " / user=" + PROPS.getProperty("username");
    }
}
