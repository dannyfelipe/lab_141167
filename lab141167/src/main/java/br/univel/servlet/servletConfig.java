package br.univel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univel.classes.Venda;


@JMSDestinationDefinitions(
		value = {
				@JMSDestinationDefinition(
						name = "java:/queue/QUEUEPedido",
						interfaceName = "javax.jms.Queue",
						destinationName = "QueuePedido"
				),
				@JMSDestinationDefinition(
						name = "java:/topic/TOPICVenda",
						interfaceName = "javax.jms.Topic",
						destinationName = "TopicVenda"
				)
		})

@WebServlet("/Config")
public class servletConfig extends HttpServlet implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3796370063389225717L;

	private static final int MSG_COUNT = 5;

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/queue/QUEUEPedido")
	private Queue queue;

	@Resource(lookup = "java:/topic/TOPICVenda")
	private Topic topic;

	public servletConfig() {

	}

	protected void getEntrega(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, JMSException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		try {
			boolean useTopic = req.getParameterMap().keySet().contains("topic");
			final Destination destination = useTopic ? topic : queue;

			for (int i = 0; i < MSG_COUNT; i++) {
				String text = "Esta Mensagem" + (i + 1);

				Venda venda = new Venda();
				venda.setProdutos(new ArrayList<String>());
				venda.setQuantidade(10);

				ObjectMessage omsg = context.createObjectMessage();
				omsg.setObject(venda);
				out.write("Mensagem (" + i + "): " + text + "</br>");
			}

		} finally {
			if (out != null) {
				out.close();
			}
		}

}

}
