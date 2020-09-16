package spring.jdbc.sharding;

import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ShardingDemo {

    @Resource(name = "shardingDataSource")
    DataSource dataSource;

    public void getAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from a");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int a = resultSet.getInt("a");
            int b = resultSet.getInt("b");
            String c = resultSet.getString("c");
            System.out.println(String.format("a: %d, b: %d, c: %s", a, b, c));
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @ShardingTransactionType(TransactionType.LOCAL)
    public void insert() {
        jdbcTemplate.execute("insert into a values (3, 4, 4)");

        jdbcTemplate.execute("insert into a values (1, 4, 4)");

    }

    public static void main(String[] args) throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring" +
                "-sharding.xml");
        ShardingDemo shardingDemo = context.getBean(ShardingDemo.class);

        shardingDemo.insert();
    }
}
