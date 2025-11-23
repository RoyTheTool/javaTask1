import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<Message> messages = new ArrayList<>();
		BoardMessage boardMessage1 = new BoardMessage("Roy", "whats up man i here and free to do something",
				"something to do");
		EmailMessage emailMessage1 = new EmailMessage("Shalev", "what a sunny day", "about the day", "day");

		messages.add(boardMessage1);
		messages.add(emailMessage1);

		Scanner sc = new Scanner(System.in);
		boolean systemUp = true;

		while (systemUp) {
			printMenu();
			int userChoice = sc.nextInt();
			switch (userChoice) {

			case 1:
				addMessage(sc, messages);
				break;

			case 2:
				deleteMessage(sc, messages);
				break;
			case 3:
				displayMessages(messages);
				break;
			case 4:
				displayMessagesOnlyWithContent(messages);
				break;

			case 5:
				displayDigitalMessages(messages);
				break;

			case 6:
				displayAllMails(messages);
				break;

			case 7:
				System.out.println("Exiting application...");
				systemUp = false;
			}

		}
	}

	public static void printMenu() {
		System.out.println("========== Message Application ==========");
		System.out.println("1. הוספת הודעה");
		System.out.println("2. מחיקת הודעה");
		System.out.println("3. הדפסת כל מאגר ההודעות");
		System.out.println("4. הצגת מספר ההודעות המכילות מילים בתוכן");
		System.out.println("5. הדפסת ההודעות הדיגיטליות בלבד");
		System.out.println("6. הדפסת כל המיילים");
		System.out.println("7. יציאה");
		System.out.println("בחר אופציה (1-7): ");
	}

//	1
	public static void addMessage(Scanner sc, ArrayList<Message> messages) {

		System.out.println("Choose message type:");
		System.out.println("1. BoardMessage");
		System.out.println("2. EmailMessage");
		System.out.println("3. TelegramMessage");
		System.out.print("Your choice: ");

		int messageKind = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter sender: ");
		String sender = sc.nextLine();

		System.out.print("Enter content: ");
		String content = sc.nextLine();

		System.out.print("Enter subject: ");
		String subject = sc.nextLine();

		Message msg = null;

		if (messageKind == 1) {
			msg = new BoardMessage(sender, content, subject);
		} else if (messageKind == 2) {
			System.out.print("Enter sub subject: ");
			String subSubject = sc.nextLine();
			System.out.print("Enter the amount of files you want to add");
			int filesAmount = sc.nextInt();
			sc.nextLine();

			ArrayList<File> files = new ArrayList<>();
			for (int i = 0; i < filesAmount; i++) {
				System.out.print("Enter file name " + (i + 1) + " :");
				String fileName = sc.nextLine();
				System.out.print("Enter file type " + (i + 1) + " :");
				String fileType = sc.nextLine();
				File newFile = new File(fileName, fileType);
				files.add(newFile);
			}
			if (filesAmount > 0) {
				msg = new EmailMessage(sender, content, subject, new Date(), subSubject, files);
			}

			if (filesAmount == 0)
				msg = new EmailMessage(sender, content, subject, subSubject);

		} else if (messageKind == 3) {
			System.out.print("Enter the number of your desired Message type:\n");
			int index = 1;
			for (MessageType Mtype : MessageType.values()) {
				System.out.print(index + ". " + Mtype.name() + "\n");
				index++;
			}

			int messageType = sc.nextInt();
			sc.nextLine();
			msg = new TelegramMessage(sender, content, subject, MessageType.values()[messageType - 1]);

		} else {
			System.out.println("Invalid type. Message not added.");
			return;
		}

		messages.add(msg);
		System.out.println("Message added successfully!");
	}

//	2
	public static void deleteMessage(Scanner sc, ArrayList<Message> messages) {

		if (messages.isEmpty()) {
			System.out.print("the are no messages yet...");
			return;
		}

		System.out.print("choose a message number to delte: \n");
		for (int i = 0; i < messages.size(); i++) {
			System.out.print((i + 1) + ". " + messages.get(i).toString());
		}

		int indexMessageToDelete = sc.nextInt();
		sc.nextLine();

		if (indexMessageToDelete < 1 || indexMessageToDelete > messages.size()) {
			System.out.println("Invalid message number.");
			return;
		}

		// we minus 1 from the indexMessageToDelete because we showed the user a list
		// that starts from 1
		messages.remove(indexMessageToDelete - 1);
		System.out.print("your message has been removed. \n");
	}

//	3
	public static void displayMessages(ArrayList<Message> messages) {
		if (messages.isEmpty()) {
			System.out.print("the are no messages yet...");
			return;
		}

		int counter = 1;
		for (Message message : messages) {
			System.out.print(counter + ". " + message.toString() + "\n");
			counter++;
		}
	}

//	4
	public static void displayMessagesOnlyWithContent(ArrayList<Message> messages) {
		if (messages.isEmpty()) {
			System.out.print("the are no messages yet...");
			return;
		}

		int counter = 0;
		for (Message message : messages) {
			if (message.content.isEmpty() || message.content.isBlank())
				continue;
			else
				counter++;
		}
		System.out.print("you have " + counter + " messages with content. \n");
	}

// 5
	public static void displayDigitalMessages(ArrayList<Message> messages) {
		for (Message message : messages) {
			if (message instanceof IDigital)
				System.out.print(message.toString());
		}
	}

//	6
	public static void displayAllMails(ArrayList<Message> messages) {
		for (Message message : messages) {
			if (message instanceof EmailMessage) {
				System.out.print(message.toString());
			}
		}
	}
}
