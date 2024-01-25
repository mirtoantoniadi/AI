public class State implements Comparable<State>
{
	private int f, h, g;
	private State father;
	private int totalTime;
	private List<Integer> RightList; 
	private List<Integer> LeftList; 
  private boolean lampOnRight; 
	List<Integer> initialTimes;
}
	
