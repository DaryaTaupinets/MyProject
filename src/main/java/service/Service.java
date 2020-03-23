package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Service {


    void  listUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

    void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException;

    void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException;

    void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

    void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    void insertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException;

}
