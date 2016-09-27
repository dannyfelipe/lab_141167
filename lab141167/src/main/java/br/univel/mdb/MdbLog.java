package br.univel.mdb;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.hibernate.Session;

import br.univel.classes.Log;
import br.univel.classes.Venda;
import br.univel.hibernate.HibernateConfig;

@MessageDriven(name = "MdbLog", activationConfig = {
	    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/VENDATopic"),
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
	    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
	    })
public class MdbLog implements MessageListener {

	private final static Logger LOGGER = Logger.getLogger(MdbLog.class.toString());

	@Override
	public void onMessage(Message arg0) {

		if (arg0 instanceof ObjectMessage) {
			ObjectMessage om = (ObjectMessage) arg0;
			try {
				Venda venda = (Venda) om.getObject();
				Log logg = new Log();

				logg.setData_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				logg.setNome_mdb("MdbLog");
				logg.setMsg(venda.toString());

				Session session = HibernateConfig.getSessionFactory().openSession();
				session.beginTransaction();
				session.persist(logg);
				session.getTransaction().commit();
				session.close();

			} catch (JMSException e) {

				e.printStackTrace();
			}
		}

}

}
