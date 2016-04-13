package servlets;


import hibernate.DaoAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 06.04.2016.
 */
@Configurable
public class ShowResultsServlet extends HttpServlet {

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

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAllDataOnPage(req);
        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("results.jsp").forward(req, resp);
    }

    private void getAllDataOnPage(HttpServletRequest req) throws ServletException, IOException {
        req.setAttribute("users", daoAccessor.getUserDao().getAll());
        req.setAttribute("admins", daoAccessor.getAdminDao().getAll());
        req.setAttribute("news", daoAccessor.getNewsDao().getAll());
        req.setAttribute("repositories", daoAccessor.getRepositoryDao().getAll());
    }

}
