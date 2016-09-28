package br.univel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univel.classes.Entrega;
import br.univel.ejb.ProcessEntrega;

@WebServlet("/Entrega")
public class servletEntrega extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ProcessEntrega ProcessEntregaEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Entrega entrega = new Entrega();
		entrega.setComprador("Danny Felipe de Mello");
		entrega.setLocal("Rua Presidente Kennedy");

		ProcessEntregaEJB.processarEntrega(entrega);

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write("Processo de entrega");
	}

}
