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

        String uid = req.getHeader("uid");
        System.out.println("uid: " + uid);
        String assertionUrl = req.getHeader("shib-assertion-01");
        System.out.println("shib-assertion-01: " + assertionUrl);

        String token = req.getParameter("my_csrf_token");
        if (token == null) {
            if (uid != null) { // only if using SSO
    //        resp.addHeader("Token", "42");
                req.setAttribute("my_csrf_token", uid);
                resp.setHeader("Set-Cookie", "CalcSession=" + uid + "; HttpOnly; SameSite=strict");
                chain.doFilter(request, response);
            } else {
                resp.sendError(401);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
