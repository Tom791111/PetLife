package util;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MapUtil {
    private MapUtil() {}

    public static void openGoogleMapsSearch(Component parent, String keyword) {
        try {
            String encoded = URLEncoder.encode(keyword, StandardCharsets.UTF_8.toString());
            URI uri = new URI("https://www.google.com/maps/search/?api=1&query=" + encoded);
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            } else {
                JOptionPane.showMessageDialog(parent,
                        "此電腦目前不支援自動開啟瀏覽器，請手動搜尋：" + keyword,
                        "Google Map", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent,
                    "無法開啟 Google Map：" + ex.getMessage(),
                    "Google Map 錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }
}
