package org.inov.assignment;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends ResourceConfig {

  public App() {

    register(
      new LoggingFeature(
        Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
        Level.INFO,
        LoggingFeature.Verbosity.PAYLOAD_TEXT,
        10000
      )
    );

  }
}
