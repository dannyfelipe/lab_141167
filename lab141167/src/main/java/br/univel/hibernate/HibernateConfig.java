package br.univel.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {

	private static SessionFactory buildSessionFactory() {
		registry = (new StandardServiceRegistryBuilder()).configure().build();
		SessionFactory sessionFactory = null;

		try {
			sessionFactory = (new MetadataSources(registry)).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void encerrarSession() {
		StandardServiceRegistryBuilder.destroy(registry);
	}

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static StandardServiceRegistry registry;

}
