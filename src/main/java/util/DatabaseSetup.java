package util;

import exception.AppException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 啟動專案時自動確認 MySQL 連線，並建立 petlife_db 與必要資料表。
 * 這樣老師或同學匯入 Eclipse 後，只要 MySQL 帳密正確，就不用手動一張一張建表。
 */
public class DatabaseSetup {
    private static final Properties PROPS = new Properties();

    static {
        File external = new File("db.properties");
        try (InputStream input = external.exists()
                ? new FileInputStream(external)
                : DatabaseSetup.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new AppException("找不到 db.properties，請確認 src/main/resources/db.properties 或 JAR 同層 db.properties 是否存在");
            }
            PROPS.load(input);
            Class.forName(PROPS.getProperty("driver"));
        } catch (Exception e) {
            throw new AppException("資料庫初始化設定載入失敗", e);
        }
    }

    public static void initializeDatabase() {
        String dbName = PROPS.getProperty("database", "petlife_db");
        try (Connection conn = DriverManager.getConnection(
                PROPS.getProperty("serverUrl"),
                PROPS.getProperty("username"),
                PROPS.getProperty("password"));
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName + " DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
            stmt.executeUpdate("USE " + dbName);

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "email VARCHAR(100) NOT NULL UNIQUE," +
                    "phone VARCHAR(30)," +
                    "password_hash VARCHAR(128) NOT NULL," +
                    "role VARCHAR(30) DEFAULT '飼主'," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")");

            // 預設展示帳號：demo@petlife.com / 123456
            stmt.executeUpdate("INSERT INTO users (name, email, phone, password_hash, role) " +
                    "SELECT 'Demo 飼主', 'demo@petlife.com', '0912345678', " +
                    "'aba567400316600dc2a3ae5d968b5e6b0a3e26cbb3f0b30b24870c8e95a99e18', '飼主' " +
                    "WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'demo@petlife.com')");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pets (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "species VARCHAR(20) NOT NULL," +
                    "breed VARCHAR(60)," +
                    "gender VARCHAR(10)," +
                    "age INT DEFAULT 0," +
                    "weight DOUBLE DEFAULT 0," +
                    "chip_number VARCHAR(30)," +
                    "health_note TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS health_records (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pet_id INT NOT NULL," +
                    "record_date VARCHAR(20)," +
                    "weight DOUBLE DEFAULT 0," +
                    "appetite VARCHAR(20)," +
                    "stool_status VARCHAR(20)," +
                    "symptom VARCHAR(100)," +
                    "note TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS service_bookings (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pet_id INT NOT NULL," +
                    "service_type VARCHAR(30)," +
                    "provider_name VARCHAR(80)," +
                    "booking_date VARCHAR(20)," +
                    "status VARCHAR(20)," +
                    "note TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS lost_reports (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pet_id INT NOT NULL," +
                    "lost_date VARCHAR(20)," +
                    "lost_location VARCHAR(120)," +
                    "contact_phone VARCHAR(30)," +
                    "status VARCHAR(20)," +
                    "description TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pet_places (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "place_type VARCHAR(30) NOT NULL," +
                    "name VARCHAR(100) NOT NULL," +
                    "address VARCHAR(160)," +
                    "phone VARCHAR(30)," +
                    "note TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS care_reminders (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pet_id INT NOT NULL," +
                    "reminder_type VARCHAR(30)," +
                    "reminder_date VARCHAR(20)," +
                    "title VARCHAR(100)," +
                    "status VARCHAR(20) DEFAULT '未完成'," +
                    "note TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE" +
                    ")");



            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS vaccination_records (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pet_id INT NOT NULL," +
                    "vaccine_name VARCHAR(80) NOT NULL," +
                    "hospital VARCHAR(100)," +
                    "vaccination_date VARCHAR(20)," +
                    "next_date VARCHAR(20)," +
                    "status VARCHAR(20) DEFAULT '已施打'," +
                    "note TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS medical_records (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pet_id INT NOT NULL," +
                    "hospital VARCHAR(100)," +
                    "doctor VARCHAR(60)," +
                    "visit_date VARCHAR(20)," +
                    "symptom TEXT," +
                    "diagnosis TEXT," +
                    "medicine TEXT," +
                    "cost DOUBLE DEFAULT 0," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pet_photos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pet_id INT NOT NULL," +
                    "photo_path VARCHAR(255)," +
                    "caption VARCHAR(120)," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS notifications (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT," +
                    "title VARCHAR(120) NOT NULL," +
                    "content TEXT," +
                    "is_read TINYINT DEFAULT 0," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS favorites (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT NOT NULL," +
                    "place_id INT NOT NULL," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (place_id) REFERENCES pet_places(id) ON DELETE CASCADE" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS reviews (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT," +
                    "place_id INT," +
                    "booking_id INT," +
                    "rating INT DEFAULT 5," +
                    "comment TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL," +
                    "FOREIGN KEY (place_id) REFERENCES pet_places(id) ON DELETE SET NULL," +
                    "FOREIGN KEY (booking_id) REFERENCES service_bookings(id) ON DELETE SET NULL" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS community_posts (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT," +
                    "title VARCHAR(120) NOT NULL," +
                    "content TEXT," +
                    "category VARCHAR(30)," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL" +
                    ")");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS adoptions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pet_name VARCHAR(60) NOT NULL," +
                    "species VARCHAR(20)," +
                    "breed VARCHAR(60)," +
                    "shelter_name VARCHAR(100)," +
                    "contact_phone VARCHAR(30)," +
                    "status VARCHAR(20) DEFAULT '待領養'," +
                    "note TEXT," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")");

            // 第一次啟動時建立示範資料；若已有資料就不重複新增。
            stmt.executeUpdate("INSERT INTO pets (name, species, breed, gender, age, weight, chip_number, health_note) " +
                    "SELECT 'Cody', '狗', 'Jack Russell Terrier', '男', 5, 7.5, 'A123456789', '活潑，需注意活動量' " +
                    "WHERE NOT EXISTS (SELECT 1 FROM pets LIMIT 1)");

        } catch (Exception e) {
            throw new AppException("MySQL 初始化失敗，請確認 MySQL 服務已啟動、帳號密碼正確，並已安裝 MySQL 8.0", e);
        }
    }
}
