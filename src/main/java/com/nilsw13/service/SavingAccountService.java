package com.nilsw13.service;

import com.nilsw13.model.SavingAccount;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SavingAccountService {

    private final JdbcTemplate jdbcTemplate;

    public SavingAccountService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    String sqlFind = "select id, account_name, total_money, rate,  created_at from savingaccounts";
    String sqlInsert = "insert into savingaccounts (id, account_name, total_money, rate,  created_at) values (?,?,?,?,?)";

    public List<SavingAccount> findAll(){
        return jdbcTemplate.query(sqlFind, (result, rowNum) -> {
            SavingAccount savingAccount = new SavingAccount();
            savingAccount.setId(UUID.fromString(result.getObject("id").toString()));
            savingAccount.setName(result.getString("account_name"));
            savingAccount.setAmount(result.getBigDecimal("total_money"));
            savingAccount.setRate(result.getBigDecimal("rate"));

            savingAccount.setCreated_At(result.getTimestamp("created_at").toLocalDateTime());
            return  savingAccount;
        });
    }

    public SavingAccount create(String accountName, BigDecimal totalMoney, BigDecimal rate){

            UUID id = UUID.randomUUID();
            LocalDateTime created_at = LocalDateTime.now();

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(con -> {
                PreparedStatement ps = con
                        .prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, id);
                ps.setString(2, accountName);
                ps.setBigDecimal(3, totalMoney);
                ps.setBigDecimal(4, rate);

                ps.setTimestamp(5, Timestamp.valueOf(created_at));
                return ps;
            }, keyHolder);

                String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
                : null;


            SavingAccount savingAccount = new SavingAccount();
            savingAccount.setId(id);
            savingAccount.setName(accountName);
            savingAccount.setAmount(totalMoney);
            savingAccount.setRate(rate);
            savingAccount.setCreated_At(created_at);
            return savingAccount;
    }





}
