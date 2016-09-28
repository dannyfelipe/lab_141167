package br.univel.webservices;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.univel.classes.Entrega;
import br.univel.ejb.ProcessEntrega;

@WebService
public class EntregaWebService {

	@EJB
	ProcessEntrega ProcessEntregaEJB;

	@WebMethod(operationName = "entregar")
	@WebResult(name = "status_Entrega")
	public String doGet(@WebParam(name = "endereco_Entrega") String endereco) {

		Entrega entrega = new Entrega();
		entrega.setComprador("comprador");
		entrega.setLocal("local");

		ProcessEntregaEJB.processarEntrega(entrega);
		return "Entrega enviada";
}

}
