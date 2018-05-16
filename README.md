# FEAnserInput
IPAの基本情報技術者試験の過去問題の解答入力機能、ユーザ毎の受験履歴機能と全ユーザのランキング表示機能があります

# Dependency
～使用言語～

サーブレット

JSP

CSS

～必要なライブラリ～

activation.jar

mail.jar

mysql-connector-java-5.1.45-bin.jar

～開発環境～

Tomcat 8.0

MySQL

# Setup
MYSQLでCreate_FE_Table.sqlとInsert_FE_Table.sqlの順に取り込む

srcディレクトリ内のservletディレクトリ内のMailServlet.java内の

93行目～96行目までの

/*下の3つの値を適切なものに書き換えてください。*/

String mail_address = "送信元のメールアドレス";

String account_password = "送信元のアカウントのパスワード";

String account_name = "送信元のアカウント名";

をご自身で用意したメールアカウントの値に書き換えてください

# Usage
srcディレクトリ内のservletディレクトリ内のTopServlet.javaを

サーバーで起動させてシステムスタートです

詳しい使い方は https://yuoishi.github.io/FEAnswerInput/ をご覧ください

# Licence
MIT
# References
JavaMailを使ってGmailのSMTPでメールを送信するとき - Qiita
https://qiita.com/yoh-nak/items/bff51637fa4f558b37ac
