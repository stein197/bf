package stein197.bf;

/**
 * This class does all the work about executing brainfuck code.
 */
class VM {

	private static final short MIN_VALUE = 0;
	private static final short MAX_VALUE = 0xFF;

	private final short[] data;
	private int position;

	/**
	 * Creates instance of virtual machine with specified amount of capacity.
	 * @param capacity Amount of VM memory.
	 */
	public VM(int capacity) {
		this.data = new short[capacity];
	}

	/**
	 * Executes given commands.
	 * @param commandArray Commands to execute.
	 * @param input Optional array of input characters.
	 * @return String written by "." command.
	 */
	public String execute(Command[] commandArray, char[] input) {
		var out = new StringBuilder();
		int inputPosition = 0;
		for (int i = 0; i < commandArray.length; i++) {
			Command cmd = commandArray[i];
			switch (cmd) {
				case NEXT: {
					this.position++;
					break;
				}
				case PREV: {
					this.position--;
					break;
				}
				case INC: {
					short value = this.getCurrentValue();
					if (value == VM.MAX_VALUE) {
						value = VM.MIN_VALUE;
					} else {
						value += 1;
					}
					this.setCurrentValue(value);
					break;
				}
				case DEC: {
					short value = this.getCurrentValue();
					if (value == VM.MIN_VALUE) {
						value = VM.MAX_VALUE;
					} else {
						value -= 1;
					}
					this.setCurrentValue(value);
					break;
				}
				case OUT: {
					out.append((char) this.getCurrentValue());
					break;
				}
				case IN: {
					if (input != null && inputPosition < input.length)
						this.data[this.position] = (short) input[inputPosition++];
					break;
				}
				case OPEN: {
					if (this.getCurrentValue() > 0) {
						continue;
					} else {
						int depth = 0;
						do {
							if (cmd == Command.OPEN)
								depth++;
							else if (cmd == Command.CLOSE)
								depth--;
							cmd = commandArray[++i];
						} while (depth > 0);
					}
					break;
				}
				case CLOSE: {
					if (this.getCurrentValue() > 0) {
						int depth = 0;
						do {
							if (cmd == Command.OPEN)
								depth--;
							else if (cmd == Command.CLOSE)
								depth++;
							cmd = commandArray[--i];
						} while (depth > 0);
					} else {
						continue;
					}
					break;
				}
			}
		}
		this.reset();
		return out.toString();
	}

	private void reset() {
		for (int i = 0; i < this.data.length; i++)
			this.data[i] = 0;
		this.position = 0;
	}

	private short getCurrentValue() {
		return this.data[this.position];
	}

	private void setCurrentValue(short value) {
		this.data[this.position] = value;
	}
}
