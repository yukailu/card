package projectCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player{
	private String name;
//	samplelist存放抽出的5张牌
	private List<Card> samplelist = new ArrayList<Card>();
//	结果字符串
	private String restr = "";
//	将结果存入数组中
	Integer[] result;
	
//	抽取5张牌
	public List<Card> sample(Cards cards){
		Random r = new Random();
		List<Card> cardlist = cards.getCardlist();
		for(int i=0;i<5;i++) {
			int index = r.nextInt(cardlist.size());
			this.samplelist.add(cardlist.get(index));
			cardlist.remove(index);			
		}
		samplelist.sort((c1,c2)->c1.getRealvalue()-c2.getRealvalue());
		return samplelist;
	}	
	
//	获取结果
	/*
	 牌型，从大到小：
	 同花顺：五张相连且花色相同                      最大值，最大值花色
	 四条：4张同点值加一张其他牌                    4张同点值的牌值
	 满堂红：3张同点值加另外一对                    3张同点值的牌值
	 同花：5张花色相同，不构成顺子                花色，最大值
	 顺子：5张相连，不构成同花                       最大值，最大值花色
	 三条：三张牌点值相同，另外两张各异      3张同点值的牌值
	 两对：两对加上一个杂牌                             最大的对牌值，另一对牌值，杂牌牌值，杂牌花色
	 一对：一对加上3张杂牌                              一对的牌值，最大的杂牌牌值，次大的杂牌牌值，最小的杂牌牌值，最大的杂牌花色
	 单张                                                              最大牌牌值，次大牌牌值，... ，最大牌花色
	 
	A最大，2最小
	相同牌型比点值，4种花色不分大小

	 */
	public Integer[] getCardSign() {

		
//		将抽取的5张牌以type、realvalue依次取出
		Card c1 = this.samplelist.get(0);
		String t1 = c1.getType();
		int v1 = c1.getRealvalue();
		
		Card c2 = this.samplelist.get(1);
		String t2 = c2.getType();
		int v2 = c2.getRealvalue();
		
		Card c3 = this.samplelist.get(2);
		String t3 = c3.getType();
		int v3 = c3.getRealvalue();
		
		Card c4 = this.samplelist.get(3);
		String t4 = c4.getType();
		int v4 = c4.getRealvalue();
		
		Card c5 = this.samplelist.get(4);
		String t5 = c5.getType();
		int v5 = c5.getRealvalue();
		
	//单张	一对	两对	三张	顺子	同花	满堂红  四张   同花顺
    //0 	 1	 2	 3	 4	 5	 6	   7	8		
//		同花顺
		if(t1.equals(t2) && t2.equals(t3) & t3.equals(t4) && t4.equals(t5) &&
				v1+1==v2 && v2+1==v3 && v3+1==v4 &&v4+1==v5) {
			this.result = new Integer[] {8,v5,Integer.parseInt(t5.substring(0,1)),0,0,0,0};
			this.restr += "同花顺";
		}
//		四张
		else if((v1==v2 && v2==v3 & v3==v4) || (v2==v3 && v3==v4 &&v4==v5)) {
			this.result = new Integer[] {7,v3,0,0,0,0,0};
			this.restr += "四张";
		}
//		满堂红
		else if((v1==v2 && v3==v4 && v4==v5) || (v1==v2 && v2==v3 &&v4==v5)) {
			this.result = new Integer[] {6,v3,0,0,0,0,0};
			this.restr += "满堂红";
		}
//		同花
		else if(t1.equals(t2) && t2.equals(t3) & t3.equals(t4) && t4.equals(t5)) {
			this.result = new Integer[] {5,v5,v4,v3,v2,v1,Integer.parseInt(t5.substring(0,1))};
			this.restr += "同花";
		}
//		顺子
		else if(v1+1==v2 && v2+1==v3 && v3+1==v4 &&v4+1==v5) {
			this.result = new Integer[] {4,v5,Integer.parseInt(t5.substring(0,1))};
			this.restr += "顺子";
		}
//		三张
		else if((v1==v2 && v2==v3 ) || (v3==v4 &&v4==v5) || (v2==v3 && v3==v4)) {
			this.result = new Integer[] {3,v3,0,0,0,0,0};
			this.restr += "三张";
		}
//		两对
		else if(v4==v5 && v2==v3) {
			this.result = new Integer[] {2,v5,v3,v1,Integer.parseInt(t5.substring(0,1)),0,0};
			this.restr += "两对";
		}
		else if(v4==v5 && v2==v1) {
			this.result = new Integer[] {2,v5,v2,v3 ,Integer.parseInt(t5.substring(0,1)),0,0};
			this.restr += "两对";
		}
		else if(v4==v3 && v2==v1) {
			this.result = new Integer[] {2,v4,v2,v5,Integer.parseInt(t4.substring(0,1)),0,0};	
			this.restr += "两对";	
		}
//		一对
		else if(v1==v2) {
			this.result = new Integer[] {1,v2,v5,v4,v3,Integer.parseInt(t5.substring(0,1)),0};
			this.restr += "一对";	
		}
		else if(v2==v3) {
			this.result = new Integer[] {1,v2,v5,v4,v1,Integer.parseInt(t5.substring(0,1)),0};
			this.restr += "一对";		
		}
		else if(v3==v4) {
			this.result = new Integer[] {1,v3,v5,v2,v1,Integer.parseInt(t5.substring(0,1)),0};
			this.restr += "一对";		
		}
		else if(v4==v5) {
			this.result = new Integer[] {1,v4,v3,v2,v1,Integer.parseInt(t3.substring(0,1)),0};
			this.restr += "一对";		
		}
//		单张
		else {
			this.result = new Integer[] {0,v5,v4,v3,v2,v1,Integer.parseInt(t5.substring(0,1))};
			this.restr += "单张";	
		}
//		将抽取的牌也放到结果字符串中
		for (Card card : this.samplelist) {
			this.restr+=card.toString();
		}
		
		return this.result;		
	}

	public Player(String name) {
		super();
		this.name = name;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getSamplelist() {
		return samplelist;
	}

	public void setSamplelist(List<Card> samplelist) {
		this.samplelist = samplelist;
	}

	public String getRestr() {
		return restr;
	}

	public void setRestr(String restr) {
		this.restr = restr;
	}

	public Integer[] getResult() {
		return result;
	}

	public void setResult(Integer[] result) {
		this.result = result;
	}

	
}
