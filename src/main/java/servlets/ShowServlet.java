package servlets;

import hibernate.DaoAccessor;
import hibernate.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
public class ShowServlet extends HttpServlet {

    private DaoAccessor daoAccessor;

    @Override
    public void init() throws ServletException {
        super.init();
        daoAccessor = DaoAccessor.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAllDataOnPage(req, resp);
    }

    private void getAllDataOnPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = daoAccessor.getUserDao().getAll();
        req.setAttribute("users", userList);
        req.setAttribute("admins", daoAccessor.getAdminDao().getAll());
        req.setAttribute("news", daoAccessor.getNewsDao().getAll());
        req.setAttribute("repositories", daoAccessor.getRepositoryDAO().getAll());
        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("results.jsp").forward(req, resp);
    }

}
