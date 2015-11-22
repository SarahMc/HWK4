/*Name: Kevin Mano, Sarah McIntosh, Melissa Lam
 * MacID: manok2, mcintosc, lamma
 * Student Number: 1421518, 1414333,1404421
 * Description: Contains functions for adding items, clearing, and returning as an 
 * array the contents of a user-specific Cart_[username].txt file. Also can create
 * a user-specific file if one does not exist.
 */

// The following are required read/write classes for file manipulation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;

// The following are required exception classes
import java.io.FileNotFoundException;
import java.io.IOException;

// The following classes allow for the generation of a date in a specific format
import java.util.Date;
import java.text.SimpleDateFormat;

public class ShoppingCart extends User
{
	
	private String[][] readables;
	private String[][] audioProducts;
	private String[][] content;
	// The username determines which Cart file is being manipulated
	private String username;
	
	public ShoppingCart(String in_user) {
		super(in_user);
		Readable readable = new Readable();
		readables = readable.assignReadables();
		Audio audio = new Audio();
		audioProducts = audio.assignAudio();
		username = in_user;
	}
	
	public String[][] getContent() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Cart_["+username+"].txt"));
			String line;
			
			content = new String[100][4];
			int i = 0;
			while ((line = br.readLine()) != null){
				String[] contents = null;
				contents = line.split(",");
				content[i][0] = contents[0];
				content[i][1] = contents[1];
				content[i][2] = contents[2];
				content[i][3] = contents[3];
				i++;
			}	
			if (br != null)
				br.close();
			return content;
		}
		catch(FileNotFoundException fnfe){
			System.out.println("File not Found!");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addItem(int sNo, int quantity) {
		try{
			PrintWriter add_item = new PrintWriter(new BufferedWriter(new FileWriter("Cart_["+username+"].txt", true)));
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		    Date date = new Date();
		    String name;
		    if (sNo>2&&sNo<7) {
		    	name = readables[sNo-3][1];
		    }
		    else {
		    	name = audioProducts[sNo-7][1];
		    }
			add_item.println("\n"+sNo+", "+name+", "+df.format(date)+", "+quantity);
			add_item.close();
		}	
		catch(FileNotFoundException fnfe){
			System.out.println("File Not Found!");
		}	
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// Creates a cart for the current username if it does not exist, or clears a 
	// cart if it does.
	public void newCart() {
		try {
			PrintWriter add_cart = new PrintWriter(new BufferedWriter(new FileWriter("Cart_["+username+"].txt", false)));
			add_cart.print("");
			add_cart.close();
		}
		catch(FileNotFoundException fnfe){
			System.out.println("File Not Found!");
		}	
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
