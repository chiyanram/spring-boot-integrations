package com.rmurugaian.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.IntStream;

/**
 * @author rmurugaian 2018-07-13
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class HibernateLockingApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    DataSource dataSource;

    public static void main(final String[] args) {
        SpringApplication.run(HibernateLockingApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {

        Student student = new Student();
        student.setStatus("NEW");
        Student save = studentRepository.save(student);

        Runnable r1 = () -> {
            System.out.println("Opening connection...");
            try {
                Connection con = dataSource.getConnection();
                con.setAutoCommit(false);
                PreparedStatement st = con.prepareStatement("update student set status=? where id=?");
                st.setString(1, "US");
                st.setLong(2, save.getId());
                System.out.println("locking table for 5 seconds...");
                st.executeUpdate();
                Thread.sleep(5000);
                st.close();
                con.commit();
                con.close();
                System.out.println("done");
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException("Error");
            }
        };

        Runnable r2 = () -> IntStream.range(1, 5).forEach(its ->
                studentRepository.findAll().forEach(it ->
                        System.out.println(" {} " + System.currentTimeMillis() + " {} " + it.getId() + "{} " + it.getStatus())));


        Thread t1 = new Thread(r1);
        Thread.sleep(100);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }
}
