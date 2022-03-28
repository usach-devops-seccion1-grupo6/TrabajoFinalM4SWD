package com.devops.dxc.devops.model;

import java.io.Serializable;

public class Dxc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2988002029080131424L;
	
	private long dxc;
	private long saldo;
	private long impuesto;
	private long sueldo;
	private long ahorro;

	public Dxc() {
	}

	public long getDxc() {
		return dxc;
	}

	public void setDxc(long dxc) {
		this.dxc = dxc;
	}

	public long getSaldo() {
		return saldo;
	}
	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
	public long getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(long impuesto) {
		this.impuesto = impuesto;
	}

	public long getSueldo() {
		return sueldo;
	}

	public void setSueldo(long sueldo) {
		this.sueldo = sueldo;
	}

	public long getAhorro() {
		return ahorro;
	}

	public void setAhorro(long ahorro) {
		this.ahorro = ahorro;
	}
}
