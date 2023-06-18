package eStoreProduct.filters;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import eStoreProduct.DAO.adminDAO;
import eStoreProduct.model.adminModel;
import eStoreProduct.model.passwordHashing;


@WebFilter("/admin")
public class adminCheckFilter implements Filter {
	
	private final adminDAO ad;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if any
    }
    public adminCheckFilter(adminDAO admindao) {
    	ad=admindao;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        boolean isAllowed = performDatabaseChecks(email,password);

        if (isAllowed) {
            // User is allowed to access the web page, continue processing
            chain.doFilter(request, response);
        } else {
            // User is not allowed to access the web page
            // You can redirect to an error page or show an access denied message
            request.getRequestDispatcher("/admin").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if any
    }

    private boolean performDatabaseChecks(String email,String password) {
    	password=passwordHashing.hashString(password);

        adminModel am=ad.getAdmin(email, password);
        

        if (am != null ) {
            return true;
        }

        return false;
    }
}
