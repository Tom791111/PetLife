package util;

import java.sql.Connection;

/**
 * 可單獨執行測試 MySQL 是否連線成功。
 * Eclipse：右鍵此檔案 -> Run As -> Java Application
 */
public class ConnectionTest {
    public static void main(String[] args) {
        DatabaseSetup.initializeDatabase();
        try (Connection conn = DbUtil.getConnection()) {
            System.out.println("PetLife MySQL 連線成功：" + conn.getCatalog());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
