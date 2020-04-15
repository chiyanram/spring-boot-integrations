package com.rmurugaian.spring.service;

import com.rmurugaian.spring.domain.Student;
import com.rmurugaian.spring.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.IntStream;

@Service
@Slf4j
public class InitService implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final DataSource dataSource;

    public InitService(final StudentRepository studentRepository, final DataSource dataSource) {
        this.studentRepository = studentRepository;
        this.dataSource = dataSource;
    }

    @Override
    public void run(final String... args) throws Exception {

        final Student student = new Student();
        student.setStatus("NEW");
        final Student save = studentRepository.save(student);

        final Runnable r1 = () -> {
            log.debug("Opening connection...");
            try (final Connection con = dataSource.getConnection()) {
                con.setAutoCommit(false);
                @SuppressWarnings("JDBCResourceOpenedButNotSafelyClosed") final PreparedStatement st = con.prepareStatement("update student set status=? where id=?");
                st.setString(1, "US");
                st.setLong(2, save.getId());
                log.debug("locking table for 5 seconds...");
                st.executeUpdate();
                Thread.sleep(5000);
                st.close();
                con.commit();
                con.close();
                log.debug("done");
            } catch (final SQLException | InterruptedException e) {
                throw new RuntimeException("Error");
            }
        };

        final Runnable r2 =
            () -> IntStream.range(1, 5)
                .forEach(its ->
                    studentRepository.findAll()
                        .forEach(it ->
                            log.debug(" {} "
                                + System.currentTimeMillis()
                                + " {} "
                                + it.getId()
                                + "{} "
                                + it.getStatus())));

        final Thread t1 = new Thread(r1);
        Thread.sleep(100);
        final Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }
}
