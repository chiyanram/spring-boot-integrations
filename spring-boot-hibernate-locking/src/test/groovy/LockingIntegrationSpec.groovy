import com.rmurugaian.spring.HibernateLockingApplication
import com.rmurugaian.spring.domain.Student
import com.rmurugaian.spring.repository.StudentRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MSSQLServerContainer
import spock.lang.Specification

import javax.sql.DataSource
import java.sql.Connection
import java.sql.PreparedStatement

@ContextConfiguration(initializers = Initializer.class)
@SpringBootTest(classes = [HibernateLockingApplication.class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class LockingIntegrationSpec extends Specification {

    @Autowired
    private StudentRepository logRepository

    @Autowired
    private DataSource dataSource

    private static MSSQLServerContainer SQL_SERVER = null

    def setupSpec() {
        startTestContainer()
    }

    static def startTestContainer() {
        if (!SQL_SERVER) {
            SQL_SERVER = new MSSQLServerContainer()
            SQL_SERVER.start()
        }
    }

    def "setup"() {
        logRepository.deleteAll()
    }


    def "deadlock"() {
        def student = new Student()
        student.status = "NEW"
        def save = logRepository.save(student)

        Runnable r1 = {
            println "Opening connection..."
            Connection con = dataSource.connection
            con.autoCommit = false
            PreparedStatement st = con.prepareStatement("update student set status=? where id=?")
            st.setString(1, 'US')
            st.setLong(2, save.id)
            println "locking table for 5 seconds..."
            st.executeUpdate()
            Thread.sleep(5000)
            st.close()
            con.commit()
            con.close()
            println "done"
        }

        Runnable r2 = {
            (0..5).each {
                logRepository.findAll().each { println "${System.currentTimeMillis()} ${it.id} : ${it.status}" }
            }

        }


        Thread t1 = new Thread(r1)
        Thread.sleep(100)
        Thread t2 = new Thread(r2)

        when:
        t1.start()
        t2.start()

        then:
        for (int i = 0; i < 6; i++) {
            Thread.sleep(1000)
        }
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            log.info("Using database container {}:{}@{}", SQL_SERVER.username, SQL_SERVER.password, SQL_SERVER.jdbcUrl)
            TestPropertyValues.of(
                "spring.datasource.url=" + SQL_SERVER.jdbcUrl,
                "spring.datasource.username=" + SQL_SERVER.username,
                "spring.datasource.password=" + SQL_SERVER.password,
                "spring.flyway.url=" + SQL_SERVER.jdbcUrl,
                "spring.flyway.user=" + SQL_SERVER.username,
                "spring.flyway.password=" + SQL_SERVER.password)
                .applyTo(configurableApplicationContext.environment)

        }
    }
}
