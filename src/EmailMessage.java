import java.util.ArrayList;
import java.util.Date;

public class EmailMessage extends Message implements IDigital {
//	fields
	private String subSubject;
	private ArrayList<File> files = new ArrayList<>();

//	gets and sets
	public String getSubSubject() {
		return subSubject;
	}

	public void setSubSubject(String subSubject) {
		if (subSubject == null || subSubject.isEmpty())
			throw new IllegalArgumentException("sub subject cannot be empty");
		this.subSubject = subSubject;
	}

//	constructors 
	public EmailMessage(String sender, String content, String subject, Date sendDate, String subSubject,
			ArrayList<File> files) {
		super(sender, content, subject, sendDate);
		setSubSubject(subSubject);
		this.files = files;
	}

	public EmailMessage(String sender, String content, String subject, String subSubject) {
		super(sender, content, subject);
		setSubSubject(subSubject);
	}

// interfaces
	@Override
	public String printCommunicationMethod() {
		return "sent via email";
	}

//	methods
	@Override
	public String toString() {
		String emailMessageDetails = super.toString() + "\nsub subject: " + subSubject + "\nFiles:\n";
		for (File file : files) {
			emailMessageDetails += "file name: " + file.getFileName() + "file type: " + file.getFileType() + "\n";
		}
		return emailMessageDetails;
	}

	public String generatePreview() {
		return "[Email] Subject: " + subject + " | From: " + sender;
	}

	public void addAttachment(File file) {
		files.add(file);
	}

	public void removeAttachment(File fileToDelete) throws AttachmentException {
		int deleteCount = 0;
		// delete from the back to not hurt the indexes of the files in the files array
		for (int i = files.size() - 1; i >= 0; i--) {
			if (files.get(i).getFileName().equals(fileToDelete.getFileName())
					&& files.get(i).getFileType().equals(fileToDelete.getFileType())) {
				files.remove(i);
				deleteCount++;
			}
		}
		if (deleteCount == 0)
			throw new AttachmentException("your file is not on the list of attachemnts");
	}
}
