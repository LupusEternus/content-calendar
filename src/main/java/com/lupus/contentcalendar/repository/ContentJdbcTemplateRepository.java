package com.lupus.contentcalendar.repository;


import com.lupus.contentcalendar.model.Content;
import com.lupus.contentcalendar.model.Status;
import com.lupus.contentcalendar.model.Type;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository implements RowMapper<Content> {

    private final JdbcTemplate jdbcTemplate;


    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("desc"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content-type")),
                LocalDateTime.parse(rs.getString("date_created")),
                LocalDateTime.parse(rs.getString("date_updated")),
                rs.getString("url")
        );
    }

    public List<Content> getAllContent() {
        String sql = "SELECT * FROM Content";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public void createContent(String title, String desc, Status status, Type type, String url) {
        String sql = "INSERT INTO Content(title,desc,status,content_type,date_created,date_updated,url)" +
                " VALUES (?,?,?,?,NOW(),?)";
        jdbcTemplate.update(sql, title, desc, status, type, url);
    }

    public void updateContent(String title, String desc, Status status, Type type, String url) {
        String sql = "UPDATE Content SET title=?,desc=?,status=?,content-type = ?, date_created = NOW(), url = ?";
        jdbcTemplate.update(sql, title, desc, status, type, url);
    }

    public void deleteContent(Integer id) {
        String sql = "DELETE FROM Content WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Content getContent(Integer id) {
        String sql = "SELECT FROM Content WHERE id = ?";
        Content content = jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRow);
        return content;
    }

}
