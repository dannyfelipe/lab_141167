package br.univel.classes;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column
	private Timestamp data_time;

	@Column
	private String nome_mdb;

	@Column
	private String msg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getData_time() {
		return data_time;
	}

	public void setData_time(Timestamp data_time) {
		this.data_time = data_time;
	}

	public String getNome_mdb() {
		return nome_mdb;
	}

	public void setNome_mdb(String nome_mdb) {
		this.nome_mdb = nome_mdb;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
}

}
