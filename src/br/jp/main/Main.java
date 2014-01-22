package br.jp.main;

import java.io.IOException;

import br.jp.processManager.ProcessManager;
import br.jp.userInterface.CommandLineInterface;

public class Main {

	public static void main(String[] args) throws IOException {
		ProcessManager pm = new ProcessManager();
		CommandLineInterface cl = new CommandLineInterface(System.in, pm);
		cl.runPrompt();
	}
}
