package stein197.bf;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for parsing string source code into a sequence of brainfuck commands.
 */
class Parser {

	/**
	 * Parser given string into a sequence of brainfuck commands. If string contains invalid characters then parser
	 * just discards them.
	 * @param data String to parse.
	 * @return Sequence of brainfuck commands.
	 */
	public static Command[] parse(String data) {
		char[] charArray = data.toCharArray();
		List<Command> result = new ArrayList<Command>(data.length());
		for (char character : charArray) {
			var cmd = Command.from(character);
			if (cmd != null)
				result.add(cmd);
		}
		return result.toArray(new Command[result.size()]);
	}
}
