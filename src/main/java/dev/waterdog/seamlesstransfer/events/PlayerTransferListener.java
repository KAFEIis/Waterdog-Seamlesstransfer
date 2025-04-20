package dev.waterdog.seamlesstransfer.events;

import dev.waterdog.seamlesstransfer.SeamlessTransferPlugin;
import dev.waterdog.waterdogpe.event.EventHandler;
import dev.waterdog.waterdogpe.event.Listener;
import dev.waterdog.waterdogpe.event.player.PlayerTransferRequestEvent;

public class PlayerTransferListener implements Listener {

    private final SeamlessTransferPlugin plugin;

    public PlayerTransferListener(SeamlessTransferPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTransferRequest(PlayerTransferRequestEvent event) {
        event.setCancelled(true);
        this.plugin.getTransferManager().transferPlayer(
            event.getPlayer(), 
            event.getTargetServer().getServerName()
        );
    }
}
