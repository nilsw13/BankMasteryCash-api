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



    public List<Transaction> findAll(){
        return jdbcTemplate.query("select id, reference,customer,  payment_method ,amount, timestamp from transactions", (resultSet, rowNum) -> {
           Transaction transaction = new Transaction();

           transaction.setId(UUID.fromString(resultSet.getObject("id").toString()));
           transaction.setReference(resultSet.getString("reference"));
           transaction.setCustomer(resultSet.getString("customer"));
           transaction.setAmount(resultSet.getBigDecimal("amount"));
           transaction.setPaymentMethod(resultSet.getString("payment_method"));
           transaction.setCreated_at(resultSet.getTimestamp("timestamp").toLocalDateTime());
           return transaction;
        });
    }

    public Transaction create( BigDecimal amount, String reference, String customer,  String paymentMethod){

        UUID id = UUID.randomUUID();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into transactions (id,  amount, reference, customer, payment_method) values (?,?,?,?,?)"
                            , Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,id);
            ps.setBigDecimal(2, amount);
            ps.setString(3, reference);
            ps.setString(4, customer);
            ps.setString(5, paymentMethod);
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
        return transaction;

    }

}
