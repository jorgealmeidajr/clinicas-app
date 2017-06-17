package clinicas.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clinicas.dao.MedicoDAO;
import clinicas.model.Medico;

@WebServlet(urlPatterns = "/images/medicos/*")
public class MedicoImageServlet extends HttpServlet {

	private static final long serialVersionUID = 4545498712859601694L;
	
	@Inject
	private MedicoDAO dao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String medicoId = req.getParameter("id");
        if(medicoId != null) {
        	Medico medico = dao.listar()
        		.stream()
        		.filter(m -> m.getId().equals(Integer.parseInt(medicoId)))
        		.findFirst()
        		.orElse(new Medico());
        	
			if (medico.getFoto() != null) {
				//res.reset();
				//res.setContentType("image/png");
				res.setHeader("Content-Length", String.valueOf(medico.getFoto().length));
				res.getOutputStream().write(medico.getFoto());
			}
        }
	}
	
}
