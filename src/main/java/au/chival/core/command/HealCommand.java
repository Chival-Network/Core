package au.chival.core.command;

import au.chival.core.Core;
import au.chival.core.util.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;


public class HealCommand extends CommandBase {
    private static final int COOLDOWN = Core.PLUGIN.getConfig().getInt("heal-cooldown", 60);
    private final HashMap<UUID, Long> COOLDOWN_MAP = new HashMap<>();


    public HealCommand() {
        super("heal", true, "chival.heal");
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player target = Utils.getPlayerFromArgs(sender, args, "chival.heal.others");
        if (target == null) return;

        // Cooldown
        if (sender instanceof Player && COOLDOWN_MAP.containsKey(((Player) sender).getUniqueId())) {
            long cooldown = COOLDOWN_MAP.get(((Player) sender).getUniqueId());
            if (cooldown > System.currentTimeMillis()) {
                sender.sendMessage(tl("heal.cooldown", (int) (cooldown - System.currentTimeMillis()) / 1000));
                return;
            }
        }

        target.setHealth(20);
        target.setFoodLevel(20);
        target.setSaturation(5.0F);
        target.setFireTicks(0);
        target.getActivePotionEffects().forEach(effect -> target.removePotionEffect(effect.getType()));

        target.playSound(target.getLocation(), Sound.LEVEL_UP, 1, 1);

        target.sendMessage(tl("heal.success"));
        if (target != sender) sender.sendMessage(tl("heal.success.other", target.getName()));

        if (!sender.hasPermission("chival.heal.exempt") && sender instanceof Player)
            COOLDOWN_MAP.put(((Player) sender).getUniqueId(), System.currentTimeMillis() + (COOLDOWN * 1000L)); // Healed self, start cooldown
    }
}
