import com.rmurugaian.spring.HibernateLockingApplication
import com.rmurugaian.spring.Student
import com.rmurugaian.spring.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.PlatformTransactionManager
import spock.lang.Ignore
import spock.lang.Specification

import javax.sql.DataSource
import java.sql.Connection
import java.sql.PreparedStatement

@Ignore
@SpringBootTest(classes = [HibernateLockingApplication.class])
class LockingTest extends Specification {

    @Autowired
    StudentRepository logRepository

    @Autowired
    PlatformTransactionManager transactionManager

    @Autowired
    DataSource dataSource

    def "setup"() {
        logRepository.deleteAll()
    }


    def "deadlock"() {
        def student = new Student()
        student.status = "NEW"
        def save = logRepository.save(student)

        Runnable r1 = {
            println "Opening connection..."
            Connection con = dataSource.getConnection()
            con.setAutoCommit(false)
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
}
