package dev.waterdog.seamlesstransfer;

import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.network.protocol.ProtocolVersion;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.network.session.TransferCallback;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TransferManager {

    private final Plugin plugin;
    private final Set<ProxiedPlayer> transferringPlayers = 
        Collections.newSetFromMap(new ConcurrentHashMap<>());

    public TransferManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void transferPlayer(ProxiedPlayer player, String targetServer) {
        ServerInfo serverInfo = this.plugin.getProxy().getServerInfo(targetServer);
        if (serverInfo == null) {
            player.sendMessage("§c目标服务器未找到!");
            return;
        }

        transferringPlayers.add(player);
        setBlindnessEffect(player, true);

        player.connect(serverInfo, new TransferCallback() {
            @Override
            public void onSuccess() {
                setBlindnessEffect(player, false);
                transferringPlayers.remove(player);
            }

            @Override
            public void onFailure(String reason) {
                player.sendMessage("§c传送失败: " + reason);
                setBlindnessEffect(player, false);
                transferringPlayers.remove(player);
            }
        });
    }

    private void setBlindnessEffect(ProxiedPlayer player, boolean enable) {
        if (enable) {
            player.sendTitle("§8", "§8", 0, 60, 0);
        } else {
            player.resetTitle();
        }
    }
}
