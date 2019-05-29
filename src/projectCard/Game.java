package projectCard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Game {
	private List<Player> players = new ArrayList<>();
	private boolean tag = true;
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		if(players.size()<2) {
			System.out.println("玩家人数不能少于2人");
			System.out.println("是否自动创建玩家？y/n");
			Scanner sc = new Scanner(System.in);
			String choose = sc.next();
			if("y".equalsIgnoreCase(choose)){
				this.players = players;
				creatPlayer(1);
				System.out.println("创建玩家成功");
			}else {
				System.out.println("请重新组建玩家");
				this.tag = false;
			}
		}else if(players.size()>10) {
			System.out.println("玩家人数不能多于10人");
			this.tag = false;
		}else {
			this.players = players;
		}		
	}
	

//	创建玩家
	public void creatPlayer(int count ){
	
		for (int i = 0; i < count; i++) {
			this.players.add(new Player("玩家"+(i+1)));
		}
		
	}
	

//	玩家抽牌
	public void playerSample(Cards cards) {
//		如果玩家人数合法，则抽牌
		if(this.tag) {
			for (Player player : players) {
				player.sample(cards);
			}
		}
	}

//	比较结果
	public void compare() {
//		如果玩家人数合法，则进行比赛
		if(tag) {				
			for (Player player : this.players) {
				player.getCardSign();
				System.out.println(player.getName()+","+player.getRestr());
			}
			
	 		players.sort((p1,p2)->{			
				int v=0;
				for(int i=0;i<p1.getResult().length;i++){
					v=p2.getResult()[i]-p1.getResult()[i];
					if(v!=0)
						break;
				}
				return v;			
			});
			System.out.println("赢家："+players.get(0).getName());
			
			
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Player> list = new ArrayList<Player>();
		boolean tag = true;
		int count = 0;
		while(tag) {
			if(count<10) {
				System.out.println("请输入玩家姓名：");
				list.add(new Player(sc.next()));
				count++;
				System.out.println("是否继续添加？y/n");
				if("n".equals(sc.next()))
					tag = false;			
			}else {
				tag = false;
				System.out.println("玩家人数已满");
			}
			
		}
		
		boolean flag = true;
		while(flag) {
			Cards cards = new Cards();
			Game game = new Game();
			game.setPlayers(list);
		
			game.playerSample(cards);
			game.compare();
			System.out.println("是否继续？y/n");
			if("n".equals(sc.next())) {
				flag = false;
				System.out.println("游戏结束");
			}
			
			for (Player player : list) {
				player.setSamplelist(new ArrayList<Card>());
				player.setResult(null);
				player.setRestr("");
			}
					
		}
	
	}
		
}
