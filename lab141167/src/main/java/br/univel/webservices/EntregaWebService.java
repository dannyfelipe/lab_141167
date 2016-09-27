package br.univel.webservices;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.univel.classes.Entrega;
import br.univel.ejb.ProcessEntregaWBS;

@WebService
public class EntregaWebService {

	@EJB(name = "processEntregaWBS")

	private ProcessEntregaWBS processEntregaWBS;

	@WebMethod(operationName = "entrega")

	@WebResult(name = "resultEntrega")

	public String entrega(

			@WebParam(name = "comprador") String comprador,
			@WebParam(name = "local") String local) {
		try {
			Entrega entrega = new Entrega();
			entrega.setComprador(comprador);
			entrega.setLocal(local);

			processEntregaWBS.processarEntrega(entrega);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "Concluído a operação";
}

}
