package br.jp.userInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

import br.jp.processManager.ProcessManager;

public class CommandLineInterface {
	private InputStream is;
	private ProcessManager procManager;
	
	public CommandLineInterface(InputStream is, ProcessManager procManager){
		this.is = is;
		this.procManager = procManager;
	}
	
	public void runPrompt() throws IOException{
		BufferedReader buff = new BufferedReader(new InputStreamReader(this.is));
		while(true){
			System.out.print("==> ");
			String[] commandLine = buff.readLine().split(" ");
			if(commandLine.length == 0 || commandLine[0].equals("")) continue;
			if(this.checkCommandExists(commandLine[0])){
				try{
					Object returned = null;
					if(commandLine.length > 1){
						String command = commandLine[0];
						String[] args = Arrays.copyOfRange(commandLine, 1, commandLine.length);
						returned = this.procManager.getClass().getMethod(command, String[].class).invoke(this.procManager, (Object[])args);
					}
					else{
						returned = this.procManager.getClass().getMethod(commandLine[0]).invoke(this.procManager);
					}
					
					if(returned instanceof Iterable<?>){
						for(Object obj : (Iterable<?>)returned){
							System.out.println(obj);
						}
					}
					else if(returned instanceof Object){
						System.out.println(returned);
					}
				}catch(Exception e){
					System.out.println("There is an error with the command syntax");
					continue;
				}
			}
			else{
				System.out.println("This command is not allowed");
				continue;
			}
			
		}
	}
	
	public boolean checkCommandExists(String command){
		for(Method method : this.procManager.getClass().getMethods()){
			if(method.getName().equals(command)) return true;
		}
		
		return false;
	}
}
