package br.jp.migratable;

import java.io.Serializable;

public abstract class MigratableProcess implements Runnable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6279293770431873582L;

	public MigratableProcess(String[] args){
		//abstract
	}
	
	@Override
	public final void run(){
		this.execute();
		System.out.println("Process \"" + this.toString() + "\" was terminated");
	}
	
	public abstract void execute();
	
	public abstract void suspend();
	
	@Override
	public abstract String toString();
}
