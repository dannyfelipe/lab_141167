package br.univel.webservices;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.univel.classes.Venda;
import br.univel.ejb.ProcessVenda;

@WebService
public class VendaWebService {

	@EJB
	ProcessVenda ProcessVendaEJB;

	@WebMethod(operationName = "vender")
	@WebResult(name = "status_Venda")
	public String doGet(@WebParam(name = "num_Nota") int num) {
		Venda venda = new Venda();
		venda.setQuantidade(2);

		ArrayList<String> produtos = new ArrayList<>();
		produtos.add("1");
		produtos.add("2");

		venda.setProdutos((java.util.List<String>) produtos);

		ProcessVendaEJB.processarVenda(venda);

		return "Venda Concluída";
	}

}
