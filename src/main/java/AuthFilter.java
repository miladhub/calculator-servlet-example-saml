import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String remote_user = req.getHeader("remote_user");
        System.out.println("remote_user: " + remote_user);
        System.out.println("shib-assertion-01: " + req.getHeader("shib-assertion-01"));

        String token = req.getParameter("my_csrf_token");
        if (token == null) {
            if (remote_user != null) { // only if using SSO
    //        resp.addHeader("Token", "42");
                req.setAttribute("my_csrf_token", remote_user);
                resp.setHeader("Set-Cookie", "CalcSession=" + remote_user + "; HttpOnly; SameSite=strict");
                chain.doFilter(request, response);
            } else {
                resp.sendError(401);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
