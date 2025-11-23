import java.util.ArrayList;
import java.util.Date;

public abstract class Message {

//	fields
	protected String sender;
	protected String content;
	protected Date sendDate;
	protected String subject;

//	gets and sets
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		if (sender == null || sender.isEmpty())
			throw new IllegalArgumentException("sender cannot be empty");
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content == null || content.isEmpty())
			throw new IllegalArgumentException("content cannot be empty");
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		if (subject == null || subject.isEmpty())
			throw new IllegalArgumentException("subject cannot be empty");
		this.subject = subject;
	}

//	constructors
	public Message(String sender, String content, String subject, Date sendDate) {
		setSender(sender);
		setContent(content);
		setSubject(subject);
		this.sendDate = sendDate;
	}

	public Message(String sender, String content, String subject) {
		setSender(sender);
		setContent(content);
		setSubject(subject);
		this.sendDate = new Date();
	}

//	methods
	@Override
	public String toString() {
		return "Sender: " + sender + "\nSubject: " + subject + "\nContent: " + content + "\nDate: " + sendDate;
	}

	public boolean find(ArrayList<String> words) {
		for (String word : words) {
			if (content.contains(word))
				return true;
		}
		return false;
	}

	public boolean isBigContent() {
		if (this.content.length() > 20)
			return true;
		else
			return false;
	}

	public abstract String generatePreview();
}