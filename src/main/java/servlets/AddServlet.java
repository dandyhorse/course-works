package servlets;

import hibernate.DaoAccessor;
import hibernate.entity.Admin;
import hibernate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 04.04.2016.
 */
@Configurable
public class AddServlet extends HttpServlet {

    @Autowired
    private DaoAccessor daoAccessor;

    public void setDaoAccessor(DaoAccessor daoAccessor) {
        this.daoAccessor = daoAccessor;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String option = req.getParameter("option");
        String login = req.getParameter("login");

        if (isFillInputs(option, login, password)) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            if (addByOption(option, login, hashedPassword)) {
                answer(req, resp, HttpServletResponse.SC_OK, "ACCEPTED!");
            } else {
                answer(req, resp, HttpServletResponse.SC_BAD_REQUEST, "ERROR IN INPUT");
            }
        } else {
            answer(req, resp, HttpServletResponse.SC_BAD_REQUEST, "ERROR IN INPUT");
        }

        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }

    private void answer(HttpServletRequest req, HttpServletResponse resp, int scBadRequest, String o) {
        req.setAttribute("message", o);
        resp.setStatus(scBadRequest);
    }

    private boolean isFillInputs(String option, String login, String password) {
        boolean bool = (option != null && !option.isEmpty());
        bool = bool && (login != null && !login.isEmpty());
        bool = bool && (password != null && !password.isEmpty());
        return bool;
    }

    private boolean addByOption(String option, String login, String hashedPassword) {
        try {
            if (option.equals("admin")) {
                Admin admin = new Admin(login, hashedPassword);
                daoAccessor.getAdminDao().add(admin);
            }
            if (option.equals("user")) {
                User user = new User(login, hashedPassword);
                daoAccessor.getUserDao().add(user);
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
        return true;
    }

}
