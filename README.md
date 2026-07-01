# PetLife

PetLife 是以寵物整合平台為主題的 Java Swing 專題系統，使用 **JDK 11 + MySQL 8.0 + Maven + Eclipse + WindowBuilder** 製作，架構遵守 **MVC + DAO Pattern**。

## v9 Enterprise 重點

- 會員登入與註冊
- 角色欄位：飼主 / 管理員 / 服務提供者
- 寵物資料 CRUD
- 健康照護紀錄 CRUD
- 服務預約 CRUD
- 走失協尋 CRUD
- Google Maps 搜尋連結
- 企業版 Dashboard 功能中心
- 自動建立 MySQL 資料庫與資料表
- Maven Fat JAR 設定，可將 MySQL Driver 一起打包

## 開發環境

- JDK 11
- MySQL 8.0
- Eclipse IDE
- Java SE
- WindowBuilder
- Maven Project

## 專案架構

```text
src/main/java
├─ controller
├─ model
├─ dao
├─ dao.impl
├─ service
├─ service.impl
├─ util
└─ exception
```

## MySQL 資料表

v9 Enterprise 會自動建立以下資料表：

```text
users
pets
health_records
service_bookings
lost_reports
pet_places
care_reminders
vaccination_records
medical_records
pet_photos
notifications
favorites
reviews
community_posts
adoptions
```

## 資料庫設定

請修改專案根目錄或 `src/main/resources/db.properties`：

```properties
driver=com.mysql.cj.jdbc.Driver
serverUrl=jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=Asia/Taipei&allowPublicKeyRetrieval=true
database=petlife_db
url=jdbc:mysql://localhost:3306/petlife_db?useSSL=false&serverTimezone=Asia/Taipei&allowPublicKeyRetrieval=true
username=root
password=1234
```

## 執行方式

### Eclipse

1. File → Import → Existing Maven Projects
2. 選擇 PetLife 專案資料夾
3. 修改 `db.properties` 的 MySQL 帳號密碼
4. 啟動 MySQL
5. 執行 `controller.Main`

### 打包 Fat JAR

```bat
build_v9_fat_jar.bat
```

### 執行 JAR

```bat
run_v9_jar.bat
```

## 預設帳號

```text
Email：demo@petlife.com
Password：123456
```

## 注意事項

- 若 JAR 雙擊無反應，請使用 `run_v9_jar.bat` 查看錯誤訊息。
- 第一次啟動會自動建立 `petlife_db` 與資料表。
- 若資料表沒有出現，請確認 MySQL 服務已啟動，帳號密碼正確。
