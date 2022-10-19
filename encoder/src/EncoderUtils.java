import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class EncoderUtils {
	private String text;
	private int offset;
	
	public String getText() { return text; }
	public int getOffset() { return offset; }
	
	public void setText(String newString) { this.text = newString; }
	public void setOffset(int newInt) { this.offset = newInt; }
	
	public Boolean fileReader(String fileName)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/resources/"+ fileName));
			StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    setText(sb.toString());
		    br.close();
		    return true;
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	private int asciiToHash(int ascii) {
		if (65 <= ascii && ascii <= 90) {
			ascii -= 65;
		}
		else if (40 <= ascii && ascii <= 47) {
			ascii -= 4;
		}
		else if (48 <= ascii && ascii <= 57) {
			ascii -= 22;
		}
		return ascii;
	}
	
	private int hashToAscii(int hash) {
		if (0 <= hash && hash <= 25) {
			hash += 65;
		}
		else if (26 <= hash && hash <= 35) {
			hash += 22;
		}
		else if (36 <= hash && hash <= 43) {
			hash += 4;
		}
		return hash;
	}
	
	private Boolean readOffset() {
		Random rand = new Random();		
		setOffset(asciiToHash(rand.nextInt(44)));
		return true;
	}
	
	public String encode(String plainText)
	{
		String encoded = "";
		if (!readOffset()) { 
			System.out.println("Offset char not recognised");
			return encoded; 
		}
		int offset = getOffset();
		
		StringBuilder sb = new StringBuilder();
		sb.append((char) hashToAscii(offset));
		
		for (int i=0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			int ascii = (int) c;
			int hash = asciiToHash(ascii);
			
			if (hash != ascii && hash >= 0 && hash <= 43) {
				hash = (hash - offset + 44) % 44;
				c = (char) hashToAscii(hash);
			}
			sb.append(c);
		}			
		encoded = sb.toString();
		return encoded;
	}
	
	public String decode(String encodedText) {
		StringBuilder sb = new StringBuilder();
		
		if (encodedText.length() >= 1) {
			int offset = asciiToHash((int) encodedText.charAt(0));
			
			
			for (int i=1; i < encodedText.length(); i++) {
				char c = encodedText.charAt(i);
				int ascii = (int) c;
				int hash = asciiToHash(ascii);
				
				if (hash != ascii && hash >= 0 && hash <= 43) {
					hash = (hash + offset) % 44;
					c = (char) hashToAscii(hash);
				}
				sb.append(c);
			}
		}
		String decodedText = sb.toString();
		return decodedText;
	}
}