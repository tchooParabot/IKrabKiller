import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Player;


public class Kill implements Strategy {
	private final int  CRABS = 1265, Food = 386;
	
	//get closest crab
	public Npc getNextNPC() {
		Npc[] npc = Npcs.getNearest(new Filter<Npc>() {

			public boolean accept(Npc npc) {
				return npc.getDef().getId() == CRABS;
			}
		});
		return npc.length > 0 ? npc[0] : null;

	}
	//kills crab
	public boolean activate() {
		Player me = Players.getMyPlayer();
		return !me.isInCombat() && Inventory.getCount(Food) != 0;
	}

	public void execute() {
		final Player me = Players.getMyPlayer();
		final Npc npc = getNextNPC();
		if (npc != null && !npc.isInCombat() && !me.isInCombat()) {
			npc.interact(1);				
			Time.sleep(new SleepCondition() {

				@Override
				public boolean isValid() {
					return npc.isInCombat() && me.isInCombat()
							&& npc.distanceTo() >= 10;
				}

			}, 1500);
		}
	}
	
	
}


