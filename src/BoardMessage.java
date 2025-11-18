import java.util.Date;

public class BoardMessage extends Message {
//	fields
	private PriorityType priorityType;

//	gets and sets
	public PriorityType getPriorityType() {
		return priorityType;
	}

	public void setPriority(PriorityType priorityType) {
		if (priorityType == null) {
			throw new IllegalArgumentException("Priority cannot be null");
		}
		this.priorityType = priorityType;
	}

//	constructors
	public BoardMessage(String sender, String content, String subject, Date sendDate, PriorityType priorityType) {
		super(sender, content, subject, sendDate);
		setPriority(priorityType);
	}

	public BoardMessage(String sender, String content, String subject) {
		super(sender, content, subject);
		this.sendDate = new Date();
		this.priorityType = priorityType.REGULAR;
	}

//	methods
	@Override
	public String toString() {
		return super.toString() + ", Priority: " + priorityType;
	}

	public boolean isUrgent() {
		return this.priorityType == PriorityType.URGENT;
	}

	public String generatePreview() {

		String shortContent = "";
		if (content.length() > 15) {
			int counter = 0;
			for (char c : content.toCharArray()) {
				shortContent = shortContent + c;
				counter++;
				if (counter == 15)
					break;
			}
		} else
			shortContent = content;

		return "[Board]" + this.sender + ": " + shortContent;
	}
}
