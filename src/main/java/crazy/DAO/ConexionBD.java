package crazy.DAO;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;

public class ConexionBD {

        private static final String URL= "jdbc:mysql://mysql-37cfeb24-sigepcrazyb.g.aivencloud.com:25461/defaultdb?useSSL=true&requireSSL=true";
        private static final String USER = "avnadmin";
        private static final String PASS = "TU_PASSWORD_AQUI";
        
        public static Connection abrir() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASS);
        }
        
}

    