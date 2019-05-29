package projectCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Cards {
	private List<Card> cardlist=new ArrayList<Card>();
	
	
	public Cards(){
		//拼凑52张牌
		//花色：黑桃：B, 红桃：R, 棉花：M, 方块：F
		String [] types=new String[]{"4B","3R","2M","1F"};
		String [] values=new String[13];
		
//		value值
//		 2 3 ....0   J   Q   K   A
//		 2 3 ...10  11   12  13  14	
		for (int i=0;i<9;i++){
			values[i]=String.valueOf(i+2);
		}
	
		values[9]="J";
		values[10]="Q";
		values[11]="K";
		values[12]="A"; 
//设置type
		for(String t:types){
			for(int i=0;i<values.length;i++){
				this.cardlist.add(new Card(t,values[i]));
			}
		}
	}
	public List<Card>  getCardlist() {
		return cardlist;
	}
	public void setCardlist(List<Card> cardlist) {
		this.cardlist = cardlist;
	}
	
	
}
