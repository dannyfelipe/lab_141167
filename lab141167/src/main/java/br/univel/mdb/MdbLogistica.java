package br.univel.mdb;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.hibernate.Session;

import br.univel.classes.Log;
import br.univel.hibernate.HibernateConfig;

@MessageDriven(name = "MdbLogistica", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/VENDATopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

@TransactionManagement(TransactionManagementType.BEAN)
public class MdbLogistica implements MessageListener {

	private final static Logger LOGGER = Logger.getLogger(MdbLogistica.class.toString());

	@Override
	public void onMessage(Message arg0) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Log logg1 = new Log();
				logg1.setData_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				logg1.setNome_mdb("MdbLogistica");
				logg1.setMsg("Iniciando envio");

				Session session = HibernateConfig.getSessionFactory().openSession();
				session.beginTransaction();
				session.persist(logg1);

				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Log logg2 = new Log();
				logg2.setData_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				logg2.setNome_mdb("MdbLogistica");
				logg2.setMsg("Entrega enviada!");

				session.persist(logg2);
				session.getTransaction().commit();
				session.close();
			}
		}).start();
	}

}
