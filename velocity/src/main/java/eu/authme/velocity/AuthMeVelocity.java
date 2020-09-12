package eu.authme.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import java.nio.file.Path;
import org.slf4j.Logger;

public final class AuthMeVelocity {

  public static ChannelIdentifier MESSAGING = MinecraftChannelIdentifier.create("authme", "main");

  private final Logger logger;
  private final ProxyServer proxy;
  private final Path dataDirectory;

  @Inject
  public AuthMeVelocity(Logger logger, ProxyServer proxy, @DataDirectory Path dataDirectory) {
    this.logger = logger;
    this.proxy = proxy;
    this.dataDirectory = dataDirectory;
    logger.info("Initialized");
  }

  @Subscribe
  public void onInitialize(ProxyInitializeEvent event) {
    proxy.getChannelRegistrar().register(MESSAGING);
    logger.info("Enabled");
  }

  @Subscribe
  public void onShutdown(ProxyShutdownEvent event) {
    logger.info("Disabled");
  }

  public ProxyServer getProxy() {
    return proxy;
  }

  public Path getDataDirectory() {
    return dataDirectory;
  }
}
