package br.jp.processManager;

import java.util.ArrayList;
import java.util.List;

import br.jp.migratable.MigratableProcess;

public class ProcessManager {
	private final static String MIGRATABLE_PACKAGE_NAME = "br.jp.migratable.";
	private List<MigratableProcess> migProcs;
	
	public ProcessManager(){
		this.migProcs = new ArrayList<>();
	}
	
	public void launchProcess(String processName, String[] args){
		MigratableProcess migProc = null;
		
		try{
			migProc = (MigratableProcess)Class.forName(MIGRATABLE_PACKAGE_NAME + processName).getConstructor(String[].class).newInstance((Object[])args);
		}catch(Exception e){
			return;
		}
		
		this.migProcs.add(migProc);
		Thread migProcThread = new Thread(migProc);
		migProcThread.start();
	}
	
	public List<String> ps(){
		List<String> migProcStrings = new ArrayList<>();
		
		if(this.migProcs.isEmpty()){
			migProcStrings.add("no running process");
		}
		else{
			for(MigratableProcess migProc : this.migProcs){
				migProcStrings.add(migProc.toString());
			}
		}
		return migProcStrings;
	}
	
	public void quit(){
		System.exit(0);
	}

	public List<MigratableProcess> getMigProcs() {
		return migProcs;
	}

	public void setMigProcs(List<MigratableProcess> migProcs) {
		this.migProcs = migProcs;
	}
	
	
}
