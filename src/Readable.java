
public class Readable extends Item{
	
	protected String authorName;
	
	@Override
	public String getInfo(int sNo){
		String[][] reads = assignReadables();
		String info = "";
		int index = sNo - 3;
		for (int i = 0; i < reads[0].length; i++) {
			if (index < 4) info += reads[index][i]+", ";
			else info += reads[index][i];
		}
		return info;
	}
	
	public String[][] assignReadables(){
		String[][] book = Book.books();
		String[][] ebook = eBook.ebooks();
		String[][] reads = new String[book.length+ebook.length][6];
		int count = 0;
		
		for (int i=0; i<book.length;i++){
			if (book[i][0] != null){
				for (int j=0; j<6;j++){
					reads[i][j] = book[i][j];
				}
				count++;
			}
			else
			{
				
			}
		}

		for (int i=0; i<ebook.length;i++){
			if (ebook[i][0] != null){
				for (int j=0; j<6;j++){
					reads[i+(count)][j] = ebook[i][j];
				}
			}
			else
			{
				break;
			}
		}
		
		return reads;
	}

	@Override
	public int getPrice(int sNo) {
		int price = 0;
		if (sNo==3||sNo==4) price = Book.getPrice(sNo);
		else if (sNo==5||sNo==6) price = eBook.getPrice(sNo);
		return price;
	}
}
