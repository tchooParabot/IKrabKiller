import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.GroundItem;


public class Loot implements Strategy{
	private final int KEY1 = 985, Key1 = 986, KEY2 = 987, Key2 = 988, KEY3 = 989, Key3 = 990, 
					Charm1 = 12158, Charm2 = 12159, Charm3 = 12160, Charm4 = 12163;
					
	
	private final int[] Loot = {KEY1, Key1, KEY2, Key2, KEY3, Key3, Charm1, Charm2, Charm3, Charm4}; 

	@Override
	public boolean activate() {
		GroundItem[] loots = GroundItems.getNearest(Loot);
		return loots.length > 0 && loots[0] != null && loots[0].distanceTo() < 6;
	}
	@Override
	public void execute() {
		final GroundItem[] loots = GroundItems.getNearest(Loot);
		
		System.out.println("Looting items");
		if (loots.length > 0 && !Inventory.isFull() && loots[0]!= null) {
				loots[0].interact(2);
				Time.sleep(new SleepCondition() {

					@Override
					public boolean isValid() {
						return loots[0] == null;
					}

				}, 1500);
		}
	}
}
