package stein197.bf;

import java.text.ParseException;

public class Application {

	private final String commands;
	private final String input;

	public static void main(String ...args) {
		if (args.length == 0)
			System.err.println("No source code supplied");
		String
			input = args.length > 1 ? args[1] : null,
			commands = args[0];
		try {
			new Application(commands, input).run();
		} catch (ParseException ex) {
			System.out.println("Brainfuck source code parsing exception at " + ex.getErrorOffset() + ": " + ex.getMessage());
		}
	}

	public Application(String commands, String input) {
		this.commands = commands;
		this.input = input;
	}

	public void run() throws ParseException {
		SourceInfo info = Parser.parse(this.commands);
		var vm = new VM(info.minCapacity());
		System.out.println(vm.execute(info.commands(), this.input == null ? null : this.input.toCharArray()));
	}
}
