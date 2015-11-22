
import java.io.IOException;

public class Audio extends Item {
	protected String authorName;
	private int price;
	
	@Override
	public String getInfo(int sNo){
		String[][] auds = assignAudio();
		String info = "";
		int index = sNo - 7;
		for (int i = 0; i < auds[0].length; i++) {
			if (index < 4) info += auds[index][i]+", ";
			else info += auds[index][i];
		}
		return info;
	}
	
	@Override
	public int getPrice(int sNo){
		if (sNo==7||sNo==8) price = CD.getPrice(sNo);
		else if (sNo==9||sNo==10) price = MP3.getPrice(sNo);
		return price;
	}
	
	
	public String[][] assignAudio(){
		String[][] cds = CD.cds();
		String[][] mp3 = MP3.mp3();
		String[][] audios = new String[cds.length+mp3.length][6];
		int count = 0;
		
		for (int i=0; i<cds.length;i++){
			if (cds[i][0] != null){
				for (int j=0; j<6;j++){
					audios[i][j] = cds[i][j];
				}
				count++;
			}
			else
			{
				
			}
		}

		for (int i=0; i<mp3.length;i++){
			if (mp3[i][0] != null){
				for (int j=0; j<6;j++){
					audios[i+(count)][j] = mp3[i][j];
				}
			}
			else
			{
				break;
			}
		}
		
		return audios;
	}
}
