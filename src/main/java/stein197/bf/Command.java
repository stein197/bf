package stein197.bf;

/**
 * This enum represents all available commands in brainfuck language.
 */
enum Command {
	
	NEXT('>', (byte) 0),
	PREV('<', (byte) 1),
	INC('+', (byte) 2),
	DEC('-', (byte) 3),
	OUT('.', (byte) 4),
	IN(',', (byte) 5),
	OPEN('[', (byte) 6),
	CLOSE(']', (byte) 7);

	public final char character;
	public final byte code;

	Command(final char character, final byte code) {
		this.character = character;
		this.code = code;
	}

	/**
	 * Retrieves enum value from char that represents command.
	 * @param character Character from which return the command.
	 * @return Command or null if there is no corresponding character.
	 */
	public static Command from(char character) {
		for (var cmd : Command.values())
			if (cmd.character == character)
				return cmd;
		return null;
	}
}
