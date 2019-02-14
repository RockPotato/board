package kr.or.ddit.util;

public class PartUtil {

	public static String getFileNameFromPart(String contentDisposition) {
		// "form-data; name=\"uploadFile\"; filename=\"cony.png\"";
		String[] splits = contentDisposition.split("; ");
		// splits[0] = form-data;
		// splits[1] = name=\"uploadFile\";
		// splits[2] =filename=\"cony.png\";
		
		String fileName = null;
		for(String split : splits){
			if(split.startsWith("filename=")){
				fileName = split.substring(split.indexOf("\"")+1,split.lastIndexOf("\""));
			}
		}
		return fileName;
	}

}
