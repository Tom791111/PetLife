# 🐾 PetLife－寵物生活管理系統

> **Java Swing × MVC × MySQL**

一套專為寵物飼主打造的桌面管理系統，整合會員管理、寵物資料、健康紀錄、服務預約及走失協尋等功能，提供完整且便利的寵物生活管理平台。

---

# 📖 專案簡介

PetLife 是以 **Java Swing** 建立圖形化介面，並採用 **MVC（Model-View-Controller）架構** 與 **MySQL** 資料庫開發的桌面應用程式。

系統整合多項寵物管理功能，協助飼主管理毛孩的基本資料、健康狀況及日常服務，提升照護效率與資料保存便利性。

---

# ✨ 系統特色

* 🔐 會員登入 / 註冊
* 🐶 寵物資料管理
* ❤️ 健康紀錄管理
* 📅 服務預約
* 📢 寵物走失協尋
* 🖥️ Java Swing 圖形介面
* 🗄️ MySQL 資料庫儲存
* 🏗️ MVC 三層式架構

---

# 📋 系統功能

## 👤 會員系統

* 使用者註冊
* 使用者登入
* 密碼驗證
* 個人資料管理

---

## 🐕 寵物管理

可建立寵物基本資料：

* 寵物名稱
* 品種
* 性別
* 年齡
* 體重
* 備註

支援：

* 新增
* 修改
* 查詢
* 刪除

---

## ❤️ 健康紀錄

建立寵物健康資訊：

* 體重
* 健康狀況
* 看診日期
* 備註

提供完整健康紀錄查詢。

---

## 📅 服務預約

可預約：

* 動物醫院
* 寵物美容
* 健康檢查
* 其他服務

管理預約日期、時間及內容。

---

## 📢 走失協尋

建立走失資訊：

* 寵物名稱
* 走失地點
* 走失日期
* 特徵描述
* 聯絡方式

方便快速建立協尋資訊。

---

# 🏗️ 系統架構

本專案採用 MVC 設計模式。

```text
PetLife
│
├── Controller
├── Service
├── DAO
├── Model
├── Util
├── Exception
└── MySQL Database
```

---

# 📂 專案目錄

```text
src
│
├── controller
├── dao
│   └── impl
├── model
├── service
│   └── impl
├── util
└── exception
```

---

# 💻 開發技術

| 技術      | 說明        |
| ------- | --------- |
| Java    | JDK 8+    |
| Swing   | GUI 使用者介面 |
| MVC     | 系統架構      |
| JDBC    | 資料庫連線     |
| MySQL   | 關聯式資料庫    |
| Eclipse | 開發工具      |
| Git     | 版本控制      |
| GitHub  | 專案管理      |

---

# 🗄️ 資料庫

系統主要包含：

* User
* Pet
* HealthRecord
* ServiceBooking
* LostReport

---

# 🚀 執行方式

## 1.專案

```bash
git clone https://github.com/你的GitHub帳號/PetLife.git
```

---

## 2. 建立 MySQL 資料庫

```sql
CREATE DATABASE petlife;
```

---

## 3. 修改資料庫連線

修改：

```text
DbUtil.java
```

設定：

```properties
db.url=jdbc:mysql://localhost:3306/petlife
db.user=root
db.password=你的密碼
```

---

## 4. 執行程式

執行：

```text
Main.java
```

即可啟動系統。

---

---

# 🔮 未來開發方向

* Google Maps 地圖整合
* 動物醫院搜尋
* 寵物美容預約
* 推播提醒
* AI 健康分析
* 雲端同步
* Android App
* iOS App

---

# 👨‍💻 作者

**甘少棠**

UI / UX Designer × Java Developer

**PetLife－寵物生活管理系統**

---

# 📄 License

MIT License

Copyright © 2026
