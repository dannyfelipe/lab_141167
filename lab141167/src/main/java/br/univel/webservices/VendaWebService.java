package br.univel.webservices;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univel.classes.Venda;
import br.univel.ejb.ProcessVenda;

@WebService
public class VendaWebService {

	@EJB(name = "processVenda")
	private ProcessVenda processVenda;

	@WebMethod(operationName = "venda")
	@WebResult(name = "resultVenda")

	public String venda(

			@WebParam(name = "quantidade") Integer quantidade,
			@WebParam(name = "produtos") List<String> produtos) {

		try {

			Venda venda = new Venda();
			venda.setQuantidade(quantidade);
			venda.setProdutos(produtos);

			processVenda.processarVenda(venda);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "Concluído a operação";
}

}
