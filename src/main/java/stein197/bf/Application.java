package stein197.bf;

public class Application {

	private final String commands;
	private final int vmCapacity;
	private final String input;

	public static void main(String ...args) {
		if (args.length == 0)
			System.err.println("No source code supplied");
		String
			input = args.length > 1 ? args[1] : null,
			commands = args[0];
		int capacity = args.length > 2 ? Integer.parseInt(args[2]) : commands.length();
		new Application(commands, capacity, input).run();
	}

	public Application(String commands, int capacity, String input) {
		this.commands = commands;
		this.vmCapacity = capacity;
		this.input = input;
	}

	public void run() {
		Command[] commandArray = Parser.parse(this.commands);
		var vm = new VM(this.vmCapacity);
		System.out.println(vm.execute(commandArray, this.input == null ? null : this.input.toCharArray()));
	}
}
