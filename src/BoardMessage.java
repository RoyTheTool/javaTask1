import java.util.ArrayList;
import java.util.Date;

public class BoardMessage extends Message {
//	fields
	private PriorityType priorityType;

	private ArrayList<String> recieversList = new ArrayList<>();

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

	public ArrayList<String> getRecieversList() {
		return recieversList;
	}

//	constructors
	public BoardMessage(String sender, String content, String subject, Date sendDate, PriorityType priorityType,
			ArrayList<String> recieversList) {
		super(sender, content, subject, sendDate);
		setPriority(priorityType);
		this.recieversList = recieversList;
	}

	public BoardMessage(String sender, String content, String subject) {
		super(sender, content, subject);
		sendDate = new Date();
		priorityType = PriorityType.REGULAR;
		recieversList.add("Shalev");
	}

//	methods
	@Override
	public String toString() {
		String recieverListString = "";
		for (int i = 0; i < recieversList.size(); i++) {
			recieverListString = recieverListString.concat(recieversList.get(i) + ", \n");
		}
		return super.toString() + "\nPriority: " + priorityType + "\n" + "Recivier List: \n" + recieverListString;
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
