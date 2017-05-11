package clinicas.utils;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clinicas.controller.LoginController;

@WebFilter("/app/*")
public class LoginFilter implements Filter {

	@Inject
	private LoginController loginController;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// System.out.println(">>login filter chamado");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if (!loginController.isUsuarioLogado()) {
			String contextPath = httpRequest.getContextPath();
			httpResponse.sendRedirect(contextPath + "/");
		} else {
			// Caso ele esteja logado, apenas deixamos
			// que o fluxo continue
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig f) throws ServletException {
	}

}
