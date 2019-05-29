package projectCard;

import java.util.Arrays;
import java.util.List;

//4、请模拟实现德州扑克的认牌和比牌大小
//
//牌面数字使用：1 2 3 4 5 6 7 8 9 T J Q K
//花色：黑桃：B, 红桃：R, 棉花：M, 方块：F
//
//一手牌就是：
//B2M2R3RJRK
//（一张黑桃2，一张棉花2，一张红桃3，一张红桃J，一张红桃K）
//
//比较规则：首先约定，这些牌面首先使用这些数字符号表示
////	单张	一对	两对	三张	顺子	同花	满堂红	四张	同花顺
////	0  	1	2	3	4	5	6	7	8
//
//实现两个功能：
//1、假如一手牌，就是这样的包含10个字符的一个字符串，那么请实现一个方法，能判定当前这手牌是什么牌
//public int getCardSign(String cards);
//传入参数：一手牌，也就是5张牌
//返回值：该牌的数字符号
//
//2、现在给定两手牌，请帮我比较大小
//public boolean compareCards(String cardsFirst, String cardsSecond);
//传入参数：两手牌
//返回值：布尔值，返回true表示cardsFirst大于cardsSecond，返回false，表示cardsSecond大于cardsFirst
//比较规则：
//0 < 1 < 2 < 3 < 4 < 5 < 6 < 7 < 8
//然后，出现：两手牌的数字符号一样，请按照花色等自定义规则在继续比较。一定要比出个大小。
//
//备注：详细德州扑克的规则，请自行查询。

public class Card {
	private String type;
	private String value;
	private Integer realvalue;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getRealvalue() {
		return realvalue;
	}
	public void setRealvalue(Integer realvalue) {
		this.realvalue = realvalue;
	}
	public Card() {
		super();
	}
	
	public Card(String type, String value) {
		super();
		this.type = type;
		this.value = value;
		String[] arr = {"2","3","4","5","6","7","8","9","10"};
		List<String> list = Arrays.asList(arr );
		if(list.contains(value))
			this.realvalue = Integer.parseInt(value);
		else if(value.equals("J"))
			this.realvalue = 11;
		else if(value.equals("Q"))
			this.realvalue = 12;
		else if(value.equals("K"))
			this.realvalue = 13;
		else if(value.equals("A"))
			this.realvalue = 14;
		
	}
	@Override
	public String toString() {
		return "【" + type.substring(1)  + value + "】";
	}
	

}
