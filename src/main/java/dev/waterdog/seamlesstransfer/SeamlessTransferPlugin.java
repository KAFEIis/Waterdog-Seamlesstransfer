package dev.waterdog.seamlesstransfer;

import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.seamlesstransfer.commands.WTransferCommand;
import dev.waterdog.seamlesstransfer.events.PlayerTransferListener;

public class SeamlessTransferPlugin extends Plugin {

    private TransferManager transferManager;

    @Override
    public void onEnable() {
        this.transferManager = new TransferManager(this);
        this.getProxy().getEventManager().subscribe(PlayerTransferListener.class, 
            new PlayerTransferListener(this));
        this.getProxy().getCommandMap().registerCommand(new WTransferCommand(this));
        this.getLogger().info("SeamlessTransferPlugin enabled successfully!");
    }

    public TransferManager getTransferManager() {
        return this.transferManager;
    }
}
