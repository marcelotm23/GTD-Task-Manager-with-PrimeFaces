package com.sdi.presentation.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdi.model.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, 
urlPatterns = { "/restrictedAdmin/*" },
initParams = { @WebInitParam(name = "LoginParam", value = "/restricted/listadoTareas.xhtml") })
public class AdminFilter implements Filter {

	// Necesitamos acceder a los par�metros de inicializaci�n en
	// el m�todo doFilter por lo que necesitamos la variable
	// config que se inicializar� en init()
	FilterConfig config = null;

	/**
	 * Default constructor.
	 */
	public AdminFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Si no es petici�n HTTP nada que hacer
		if (!(request instanceof HttpServletRequest)) {

			chain.doFilter(request, response);

			return;
		}
		// En el resto de casos se verifica que se haya hecho login previamente
		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session.getAttribute("LOGGEDIN_USER") == null) {
			
			String loginForm = config.getInitParameter("LoginParam");

			// Si no hay login, redirecci�n al formulario de login

			res.sendRedirect(req.getContextPath() + loginForm);

			return;
		}
		User u = (User) session.getAttribute("LOGGEDIN_USER");
		if(!u.getIsAdmin()) {
			String loginForm = config.getInitParameter("LoginParam");

			// Si no hay login, redirecci�n al formulario de login

			res.sendRedirect(req.getContextPath() + loginForm);

			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Iniciamos la variable de instancia config
		config = fConfig;
	}

}