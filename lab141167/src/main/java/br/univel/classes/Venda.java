package br.univel.classes;

import java.io.Serializable;
import java.util.List;

public class Venda implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1159513916260910518L;

	private List<String> produtos;
	private int quantidade;

	public List<String> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<String> produtos) {
		this.produtos = produtos;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Produtos: " + getProdutos() + "Quantidade: " + getQuantidade();
	}

}
