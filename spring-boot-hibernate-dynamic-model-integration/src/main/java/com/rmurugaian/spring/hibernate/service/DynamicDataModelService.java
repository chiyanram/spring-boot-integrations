package com.rmurugaian.spring.hibernate.service;

import com.rmurugaian.spring.hibernate.domain.DynamicDataModel;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

public interface DynamicDataModelService {

    TransactionStatus beginTransaction(TransactionDefinition transactionDefinition);

    void commit(TransactionStatus transactionStatus);

    void rollback(TransactionStatus transactionStatus);

    DynamicDataModel save(DynamicDataModel dynamicDataModel);

    DynamicDataModel getById(String entityName, Long pkId);

    DynamicDataModel deleteById(String entityName, Long pkId);

    DynamicDataModel delete(DynamicDataModel dynamicDataModel);

    DynamicDataModel queryOne(String hql, List<Object> params);

    List<DynamicDataModel> queryList(String hql, List<Object> params);

}
