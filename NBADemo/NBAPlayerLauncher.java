package NBADemo;
// test the overAll ability of a NBA PLAYER
// APPLY class NBAPlayer from the same package
public class NBAPlayerLauncher {
	public static void main(String[] args) {
		NBAPlayer p1 = new NBAPlayer("Kyrie Irving");		
		NBAPlayer p2 = new NBAPlayer("JR SMITH");
		NBAPlayer p3 = new NBAPlayer("Lebron James");		
		NBAPlayer p4 = new NBAPlayer("KEVIN LOVE");
		NBAPlayer p5 = new NBAPlayer("TRISTAN THOMPSON");
		NBAPlayer p11 = new NBAPlayer("stephen curry");		
		NBAPlayer p22 = new NBAPlayer("klay thompson");
		NBAPlayer p33 = new NBAPlayer("harrison barnes");		
		NBAPlayer p44 = new NBAPlayer("andre iguodala");
		NBAPlayer p55 = new NBAPlayer("draymond green");
		NBAPlayer p111 = new NBAPlayer("russel westbrook");		
		NBAPlayer p222 = new NBAPlayer("dion waiters");
		NBAPlayer p333 = new NBAPlayer("kevin durant");		
		NBAPlayer p444 = new NBAPlayer("serge ibaka");
		NBAPlayer p555 = new NBAPlayer("enes kanter");	
		NBAPlayer p01 = new NBAPlayer("tony parker");		
		NBAPlayer p02 = new NBAPlayer("danny green");
		NBAPlayer p03 = new NBAPlayer("kawhi leonard");		
		NBAPlayer p04 = new NBAPlayer("tim duncan");
		NBAPlayer p05 = new NBAPlayer("lamarcus aldridge");			
		System.out.println("Cavaliers:");		
		System.out.println("Kyrie is overall: "+p1.getOverAll());
		System.out.println("JR is overall: "+p2.getOverAll());		
		System.out.println("LBJ is overall: "+p3.getOverAll());			
		System.out.println("LOVE is overall: "+p4.getOverAll());
		System.out.println("TT is overall: "+p5.getOverAll());
		System.out.println("Warriors:");
		System.out.println("CURRY is overall: "+p11.getOverAll());
		System.out.println("Klay is overall: "+p22.getOverAll());		
		System.out.println("Barnes is overall: "+p33.getOverAll());			
		System.out.println("iguodala is overall: "+p44.getOverAll());
		System.out.println("draymond is overall: "+p55.getOverAll());
		System.out.println("Spurs:");
		System.out.println("parker is overall: "+p01.getOverAll());
		System.out.println("danny green is overall: "+p02.getOverAll());		
		System.out.println("kawhi is overall: "+p03.getOverAll());			
		System.out.println("tim duncan is overall: "+p04.getOverAll());
		System.out.println("aldridge is overall: "+p05.getOverAll());				
		System.out.println("Thunders:");
		System.out.println("westbrook is overall: "+p111.getOverAll());
		System.out.println("waiters is overall: "+p222.getOverAll());		
		System.out.println("KD is overall: "+p333.getOverAll());			
		System.out.println("ibaka is overall: "+p444.getOverAll());
		System.out.println("kanter is overall: "+p555.getOverAll());					
	}


}