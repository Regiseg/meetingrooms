package meetingrooms;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariadbMeetingRoomsRepository implements MeetingRoomsRepository {

    public JdbcTemplate jdbcTemplate;

    public MariadbMeetingRoomsRepository() {
        try {
            MariaDbDataSource dataSource;
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();

            jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (SQLException sqlException) {
            throw new IllegalStateException("Connection failed", sqlException);
        }
    }

    @Override
    public void save(String name, int width, int length) {
        jdbcTemplate.update("INSERT INTO meetingrooms(mr_name, mr_width, mr_length) VALUES (?,?,?)", name, width, length);
    }

    @Override
    public List<String> getOrderedNames() {
        return jdbcTemplate.query("SELECT mr_name FROM meetingrooms ORDER BY mr_name;",
                (rs, i) -> rs.getString("mr_name"));
    }

    @Override
    public List<String> getReversedNames() {
        return jdbcTemplate.query("SELECT mr_name FROM meetingrooms ORDER BY mr_name DESC;",
                (rs, i) -> rs.getString("mr_name"));
    }

    @Override
    public List<String> getEvenOrderedNames() {
        return jdbcTemplate.query("SELECT mr_name FROM (SELECT mr_name, row_number() over (ORDER BY mr_name) as `rn` FROM `meetingrooms`) as `w_rownum` WHERE w_rownum.rn % 2 = 0;",
                (rs, i) -> rs.getString("mr_name"));
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsOrderedByAreaDesc() {
        return jdbcTemplate.query("SELECT * FROM meetingrooms ORDER BY mr_width*mr_length DESC;",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("mr_name"), rs.getInt("mr_width"), rs.getInt("mr_length")));
    }

    @Override
    public void printMeetingRoomsWithName(String name) {

    }

    @Override
    public void printMeetingRoomsContains(String part) {

    }

    @Override
    public void printAreasLargerThan(int area) {

    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from meetingrooms");
    }
}
