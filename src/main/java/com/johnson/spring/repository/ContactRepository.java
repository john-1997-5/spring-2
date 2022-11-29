package com.johnson.spring.repository;

import com.johnson.spring.model.AppConstants;
import com.johnson.spring.model.Contact;
import com.johnson.spring.model.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.johnson.spring.model.AppConstants.OPEN;

@Repository
public class ContactRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int saveContact(Contact contact) {
        String query = "insert into contact_msg (name, mobile_num, email, subject, message, status, created_at, created_by)" +
                " values(?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(query,
                contact.getName(),
                contact.getMobileNum(),
                contact.getEmail(),
                contact.getSubject(),
                contact.getMessage(),
                contact.getStatus(),
                contact.getCreatedAt(),
                contact.getCreatedBy());
    }

    public List<Contact> findMessagesWithOpenStatus(String status) {
        String query = "select * from contact_msg where status = ?";
        return jdbcTemplate.query(query, ps -> ps.setString(1, status), new ContactRowMapper());
    }

    public int updateMsgStatus(int contactId, String name, String status) {
        String query = "update contact_msg set status = ?, updated_by = ?, updated_at = ? where contact_id = ?";
        return jdbcTemplate.update(query, ps -> {
                                ps.setString(1, status);
                                ps.setString(2, name);
                                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                                ps.setInt(4, contactId);
                                }
        );
    }
}
