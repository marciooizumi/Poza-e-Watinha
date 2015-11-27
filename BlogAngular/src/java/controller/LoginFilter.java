package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");
        System.out.println("usuario ");
        HttpServletRequest rec = (HttpServletRequest) request;
        if (usuario == null
                && !(rec.getRequestURI().equals("/BlogAngular/view/LoginController.js"))
                && !(rec.getRequestURI().equals("/BlogAngular/view/cadastro.html"))
                && !(rec.getRequestURI().equals("/BlogAngular/view/LoginController.js"))
                && !(rec.getRequestURI().equals("/BlogAngular/view/angular.min.js"))
                && !(rec.getRequestURI().equals("/BlogAngular/view/angular-animate.min.js"))
                && !(rec.getRequestURI().equals("/BlogAngular/view/UsuarioController.js"))
           ) {

            System.out.println("aqui esta " + ((HttpServletRequest) request).getRequestURI());

            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/");
        }

        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
