import java.io.File;

public class c_Files {
	
	private String fileName;
	private File inputSource;
	
	public String getFile() { return fileName; }
	public String getFileSource() { return inputSource.toString(); }
	
	public void setproject(String file) { fileName = file; }
	public void setprojectnumber(File fileLocation) { inputSource = fileLocation; }
	
	
	public c_Files(String file, File fileLocation)
	{
		fileName = file;
		inputSource = fileLocation;		
		
	}	
		
	public c_Files(String file)
	{
		fileName = file;			
		
	}
	
	
	@Override
	public String toString(){
			
			if(this.getFile() != null)
			{
			String fullFile = new String(this.getFile());
			return fullFile;
			}
			
			else 
			{
				return this.getFile();
			}
			
			
		}

}
