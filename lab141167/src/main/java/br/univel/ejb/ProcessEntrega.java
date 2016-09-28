package br.univel.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import br.univel.classes.Entrega;

@Stateless
public class ProcessEntrega {

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/queue/QUEUEPedido")
	private Queue queue;

	public void processarEntrega(Entrega entrega) {
		System.out.println("Recebendo solicitação!!");
		final Destination destination = queue;

		try {
			ObjectMessage om = context.createObjectMessage();
			om.setObject(entrega);

			System.out.println("Mensagem enviada para " + destination);
			context.createProducer().send(destination, om);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

}
