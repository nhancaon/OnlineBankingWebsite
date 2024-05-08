package CSP;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CSPPoliciesApplier implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Content-Security-Policy",
                "base-uri 'self'; font-src 'self' https://fonts.gstatic.com https://cdnjs.cloudflare.com;"
                        + " img-src 'self'; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com https://cdnjs.cloudflare.com;"
                        + " script-src 'nonce-2726c7f26c' http://localhost:*;"
                        + " default-src 'self'; frame-ancestors 'self'; form-action 'self'");

        chain.doFilter(request, response);
    }
}
