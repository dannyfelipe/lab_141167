package br.univel.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

import br.univel.classes.Venda;

@Stateless
public class ProcessVenda {

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/topic/TOPICVenda")
	private Topic topic;

	public void processarVenda(Venda venda) {
		System.out.println("Recebendo solicitação!!");
		final Destination destination = topic;

		try {
			ObjectMessage om = context.createObjectMessage();
			om.setObject(venda);

			System.out.println("Mensagem enviada para: " + destination);
			context.createProducer().send(destination, om);
		} catch (JMSException e) {
			e.printStackTrace();
		}
}

}
