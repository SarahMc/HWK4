
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Book extends Readable {
	
	public static String[][] books() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Books.txt"));
			String content;
			
			String[][] part = new String[100][6];
			int i = 0;
			while ((content = br.readLine()) != null){
				String[] contents = null;
				contents = content.split(",");
				part[i][0] = contents[0];
				part[i][1] = contents[1];
				part[i][2] = contents[2];
				part[i][3] = contents[3];
				part[i][4] = contents[4];
				part[i][5] = "Book";
				i++;
			}	
			if (br != null)
				br.close();
			return part;
		}
		catch(FileNotFoundException fnfe){
			System.out.println("File not Found!");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getPrice(int sNo) {
		int price = Integer.parseInt(books()[sNo-3][3]);
		price += price*0.02;
		return price;
	}

}