import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Skill;


@ScriptManifest(author = "Tchoo", category = Category.COMBAT, description = "Kills rock crabs on Ikov", 
			name = "IkrabKiller", servers = { "Ikov" }, version = 1.1)

public class IkrabKiller extends Script implements Paintable{
	
	private final ArrayList<Strategy> strategy = new ArrayList<Strategy>();
	
	public int StartLVL = 0;
	public int StartEXP = 0;
	public int CurrentLVL = 0;
	public int CurrentEXP = 0;
	public int gainedXp = 0;

	public long startTime;
	
	public boolean onExecute() {
		StartLVL = Skill.ATTACK.getRealLevel() + Skill.STRENGTH.getRealLevel()
				+ Skill.DEFENSE.getRealLevel()
				+ Skill.HITPOINTS.getRealLevel()
				+ Skill.RANGE.getRealLevel() + Skill.MAGIC.getRealLevel();
		StartEXP = Skill.ATTACK.getExperience()
				+ Skill.STRENGTH.getExperience()
				+ Skill.DEFENSE.getExperience()
				+ Skill.HITPOINTS.getExperience()
				+ Skill.RANGE.getExperience() + Skill.MAGIC.getExperience();
		startTime = System.currentTimeMillis();

		strategy.add(new Kill());
		strategy.add(new Eat());
		strategy.add(new Loot());
		provide(strategy);
		return (true);		
	}
	
	public void paint(Graphics g1) {
		Graphics2D gr = (Graphics2D) g1;
		
		CurrentLVL = Skill.ATTACK.getRealLevel()
				+ Skill.STRENGTH.getRealLevel() + Skill.DEFENSE.getRealLevel()
				+ Skill.HITPOINTS.getRealLevel()
				+ Skill.RANGE.getRealLevel() + Skill.MAGIC.getRealLevel();
		CurrentLVL -= StartLVL;
		CurrentEXP = Skill.ATTACK.getExperience()
				+ Skill.STRENGTH.getExperience()
				+ Skill.DEFENSE.getExperience()
				+ Skill.HITPOINTS.getExperience()
				+ Skill.RANGE.getExperience() + Skill.MAGIC.getExperience();
		CurrentEXP -= StartEXP;
		
		gr.setColor(Color.WHITE);
		gr.setFont(new Font("Verdana", 0, 12));
		gr.drawString("By: Tchoo", 333, 120);
		gr.drawString("IKrabKiller v1.0", 333, 35);
		gr.drawString("XP Gained: " + CurrentEXP , 333, 60);
		gr.drawString("Levels Gained: " + CurrentLVL, 333, 80);
		gr.drawString("", 333, 240);
		gr.drawString("Runtime: " + runTime(startTime),333, 100);

		gr.drawRect(330, 6, 183, 130);
		Graphics2D rect = (Graphics2D) g1;
		rect.setColor(new Color(0, 0, 0, 120));
		rect.fillRect(330, 6, 183, 130);
	}
	
	public String runTime(long i)
	{
	    DecimalFormat nf = new DecimalFormat("00");
	
	    long millis = System.currentTimeMillis() - i;
	    long hours = millis / (1000 * 60 * 60);
	    millis -= hours * (1000 * 60 * 60);
	    long minutes = millis / (1000 * 60);
	    millis -= minutes * (1000 * 60);
	    long seconds = millis / 1000;
	
	    return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
	}
}
