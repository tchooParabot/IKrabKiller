import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Skill;

public class Eat implements Strategy {
        final int Food = 386;
        int hp = Skill.HITPOINTS.getLevel();
        int halfLife = hp/2;
        
		public boolean activate() {
			return Inventory.getCount(Food) != 0 && Players.getMyPlayer().getHealth() < halfLife;					
		}

		public void execute() {
			for (int i = 0; i < Inventory.getItems(Food).length
					&& Players.getMyPlayer().getHealth() < 50; i++) {				
				Menu.sendAction(74, 385,
						Inventory.getItems(Food)[i].getSlot(), 3214);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Players.getMyPlayer().getHealth() > halfLife;
					}
				}, 2000);
			}
		}
	}