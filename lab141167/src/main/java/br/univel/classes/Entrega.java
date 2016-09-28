package br.univel.classes;

import java.io.Serializable;

public class Entrega implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8064881927233055704L;

	private String comprador;
	private String local;

	public Entrega() {

	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Comprador: " + getComprador() + "Local: " + getLocal();
	}

}
