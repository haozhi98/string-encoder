public class Encoder {
	
	public static void main(String[] args)
	{
		EncoderUtils encoder = new EncoderUtils();
		
		String fileName = "text.txt";
				
		encoder.fileReader(fileName);
		System.out.println("Input text is: " + encoder.getText());
		String encoded = encoder.encode(encoder.getText());
		System.out.println("Encoded text is: " + encoded);
		String decoded = encoder.decode(encoded);
		System.out.println("Decoded text is: " + decoded);
	}
}