package se.yrgo.libraryapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.yrgo.libraryapp.entities.Role;
import se.yrgo.libraryapp.entities.UserId;

public class RoleDao {
    private static Logger logger = LoggerFactory.getLogger(RoleDao.class);
    private DataSource ds;

    @Inject
    RoleDao(DataSource ds) {
        this.ds = ds;
    }

    public List<Role> get(UserId userId) {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT r.role FROM user_role AS ur JOIN role AS r ON ur.role_id = r.id WHERE ur.user_id = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roles.add(Role.fromString(rs.getString("role")));
                }
            }
        } catch (SQLException ex) {
            logger.error("Unable to get roles for user ID: " + userId, ex);
        }
        return roles;
    }
}
