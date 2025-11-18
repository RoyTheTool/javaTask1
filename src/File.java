
public class File {
	// fields
	private String fileName;
	private String fileType;

	// gets and sets
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if (fileName.isEmpty() || fileName == null)
			throw new IllegalArgumentException("file name cannot be empty");
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		if (fileType.isEmpty() || fileType == null)
			throw new IllegalArgumentException("file type cannot be empty");
		this.fileType = fileType;
	}

	// constructors
	public File(String fileName, String fileType) {
		setFileName(fileName);
		setFileType(fileType);
	}

//	methods
	@Override
	public String toString() {
		return "file name: " + fileName + "file type: " + fileType;
	}
}
