import java.io.*;

public class User{
	private String username;
	public String getUsername(){
		return username;
	}
	
	public User(String in_user) {
		username = in_user;
	}
	
	public boolean checksUsername(){
		try{
			BufferedReader usernames = new BufferedReader(new FileReader("Users.txt"));
			String name = usernames.readLine();
			while(name != null){
				if(name.equals(username))return true;
				else name = usernames.readLine();
			}		
			
			usernames.close();
			return false;
		}
		
		catch(FileNotFoundException fnfe){
			System.out.println("File not Found!");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addUsername(){
		try{
			PrintWriter adduser = new PrintWriter(new BufferedWriter(new FileWriter("Users.txt", true)));
			adduser.println("\n"+username);
			adduser.close();
		}	
		catch(FileNotFoundException fnfe){
			System.out.println("File Not Found!");
		}	
		catch(IOException e){
			e.printStackTrace();
		}
	}	

}