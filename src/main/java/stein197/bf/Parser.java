package stein197.bf;

import java.text.ParseException;
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
	public static SourceInfo parse(String data) throws ParseException {
		char[] charArray = data.toCharArray();
		List<Command> result = new ArrayList<Command>(data.length());
		int minCapacity = 0;
		List<Integer> openCmdPositionList = new ArrayList<Integer>();
		for (int i = 0; i < charArray.length; i++) {
			var character = charArray[i];
			var cmd = Command.from(character);
			if (cmd == null)
				continue;
			switch (cmd) {
				case NEXT: {
					minCapacity++;
					break;
				}
				case PREV: {
					minCapacity--;
					break;
				}
				case OPEN: {
					openCmdPositionList.add(i);
					break;
				}
				case CLOSE: {
					if (openCmdPositionList.size() == 0)
						throw new ParseException("Close command \"]\" has no corresponding open command", i);
					openCmdPositionList.remove(openCmdPositionList.size() - 1);
					break;
				}
			}
			if (minCapacity < 0)
				throw new ParseException("Source code defines negative pointer offset", i);
			result.add(cmd);
		}
		if (openCmdPositionList.size() > 0)
			throw new ParseException("Open command \"[\" has no corresponding close command", openCmdPositionList.get(openCmdPositionList.size() - 1));
		return new SourceInfo(result.toArray(new Command[result.size()]), minCapacity + 1);
	}
}
