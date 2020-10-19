package com.rmurugaian.spring.hibernate;

import com.rmurugaian.spring.hibernate.domain.DynamicDataModel;
import com.rmurugaian.spring.hibernate.service.DynamicDataModelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DynamicModelApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DynamicModelApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final DynamicDataModelService dynamicDataModelService) {

        return args -> {
            final TransactionStatus transactionStatus =
                dynamicDataModelService.beginTransaction(TransactionDefinition.withDefaults());

            final Map<String, Object> author = new HashMap<>();
            author.put("pkId", 1L);
            author.put("name", "Vlad Miche");

            final Map<String, Object> properties = new HashMap<>();
            properties.put("pkId", 1L);
            properties.put("title", "Java Persistence");
            properties.put("author", author);

            final DynamicDataModel dynamicDataModel = new DynamicDataModel();
            dynamicDataModel.setEntityName("Book");
            dynamicDataModel.setProperties(properties);
            dynamicDataModelService.save(dynamicDataModel);

            dynamicDataModelService.commit(transactionStatus);

            final Map<String, Object> debitAccount = new HashMap<>();
            debitAccount.put("owner", "John Doe");
            debitAccount.put("balance", BigDecimal.valueOf(100));
            debitAccount.put("interestRate", BigDecimal.valueOf(1.5d));
            debitAccount.put("overdraftFee", BigDecimal.valueOf(25));

            final Map<String, Object> creditAccount = new HashMap<>();
            creditAccount.put("owner", "John Doe");
            creditAccount.put("balance", BigDecimal.valueOf(1000));
            creditAccount.put("interestRate", BigDecimal.valueOf(1.9d));
            creditAccount.put("creditLimit", BigDecimal.valueOf(5000));

            final TransactionStatus accountTransaction =
                dynamicDataModelService.beginTransaction(TransactionDefinition.withDefaults());

            final DynamicDataModel accountDebit = new DynamicDataModel();
            accountDebit.setPkId(1L);
            accountDebit.setEntityName("DebitAccount");
            accountDebit.setProperties(debitAccount);
            dynamicDataModelService.save(accountDebit);

            final DynamicDataModel accountCredit = new DynamicDataModel();
            accountCredit.setPkId(2L);
            accountCredit.setEntityName("CreditAccount");
            accountCredit.setProperties(creditAccount);
            dynamicDataModelService.save(accountCredit);

            dynamicDataModelService.commit(accountTransaction);

            final List<DynamicDataModel> dynamicDataModels = dynamicDataModelService.queryList(
                "SELECT p FROM Account p",
                Collections.emptyList());
            dynamicDataModels.forEach(System.out::println);

            dynamicDataModelService.queryList("select p from Book p ", Collections.emptyList())
                .forEach(System.out::println);
        };
    }

}
