package br.univel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univel.classes.Venda;
import br.univel.ejb.ProcessVenda;

@WebServlet("/Venda")
public class servletVenda extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	ProcessVenda ProcessVendaEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Venda venda = new Venda();
		venda.setQuantidade(1);

		ArrayList<String> produtos = new ArrayList<>();
		produtos.add("1");
		produtos.add("2");

		ProcessVendaEJB.processarVenda(venda);

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write("Processo de venda");
	}

}
