package dev.waterdog.seamlesstransfer.commands;

import dev.waterdog.seamlesstransfer.SeamlessTransferPlugin;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class WTransferCommand extends Command {

    private final SeamlessTransferPlugin plugin;

    public WTransferCommand(SeamlessTransferPlugin plugin) {
        super("wtgtr", CommandSettings.builder()
                .setDescription("无缝传送到指定服务器")
                .setUsageMessage("/wtgtr <服务器名称>")
                .setPermission("seamlesstransfer.command.wtgtr")
                .build());
        this.plugin = plugin;
    }

    @Override
    public boolean onExecute(CommandSender sender, String alias, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§c只有玩家可以使用此命令!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§c用法: /wtgtr <服务器名称>");
            return true;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        this.plugin.getTransferManager().transferPlayer(player, args[0]);
        return true;
    }
}
