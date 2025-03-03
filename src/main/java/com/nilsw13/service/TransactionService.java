package com.nilsw13.service;


import com.nilsw13.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final JdbcTemplate jdbcTemplate;

    public TransactionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    String sqlFind = "select id, reference,customer,  payment_method ,amount, created_at, type from transactions";
    String sqlInsert = "insert into transactions (id,  amount, reference, customer, payment_method,type) values (?,?,?,?,?, ?)";


    public List<Transaction> findAll(){
        return jdbcTemplate.query(sqlFind, (resultSet, rowNum) -> {
           Transaction transaction = new Transaction();

           transaction.setId(UUID.fromString(resultSet.getObject("id").toString()));
           transaction.setReference(resultSet.getString("reference"));
           transaction.setCustomer(resultSet.getString("customer"));
           transaction.setAmount(resultSet.getBigDecimal("amount"));
           transaction.setPaymentMethod(resultSet.getString("payment_method"));
           transaction.setCreated_at(resultSet.getTimestamp("created_at").toLocalDateTime());
           transaction.setType(resultSet.getString("type"));
           return transaction;
        });
    }

    public Transaction create( BigDecimal amount, String reference, String customer,  String paymentMethod, String type){

        UUID id = UUID.randomUUID();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sqlInsert
                            , Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,id);
            ps.setBigDecimal(2, amount);
            ps.setString(3, reference);
            ps.setString(4, customer);
            ps.setString(5, paymentMethod);
            ps.setString(6,type);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
                : null;

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(amount);
        transaction.setReference(reference);
        transaction.setCustomer(customer);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setCreated_at(LocalDateTime.now());
        transaction.setType(type);
        return transaction;

    }

}
