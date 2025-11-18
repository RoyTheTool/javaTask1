import java.util.Date;

public class TelegramMessage extends Message implements IDigital {

//	fields
	private MessageType messageType;

//	gets and sets
	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) throws InvalidMessageTypeException {
		if (messageType == null) {
			throw new InvalidMessageTypeException("telegram message type cant be empty");
		}
		this.messageType = messageType;
	}

//	constructors
	public TelegramMessage(String sender, String content, String subject, Date sendDate) {
		super(sender, content, subject, sendDate);
	}

	public TelegramMessage(String sender, String content, String subject) {
		super(sender, content, subject);
		messageType = MessageType.Text;
	}

//	interfaces
	@Override
	public String printCommunicationMethod() {
		return "sent via telegram";
	}

//	methods
	@Override
	public String toString() {
		return super.toString() + "Message type: " + messageType;
	}

	public String generatePreview() {
		return "[Telegram] Subject: " + subject + " | From: " + sender;
	}
}
