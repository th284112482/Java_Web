# Java_Web
初始环境v1.0
项目使用方法步骤
1.导入项目
2.将项目中的数据库信息修改为自己的信息，具体路径是com.example.util包下的DBUtil，修改其中的：
    private static final String URL = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";【你的实际用户名】
    private static final String PASSWORD = "root";【你的实际用户密码】
