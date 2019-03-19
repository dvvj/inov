package org.inov.assignment;

import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.inov.assignment.gnl.GlobalCfg;
import org.inov.assignment.hbn.HbnDbOpsImpl;
import org.inov.assignment.hbn.IDbOps;
import org.inov.assignment.gnl.GlobalCfg;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class SvcContextListener implements ServletContextListener {

  private final static Logger logger = Logger.getLogger(SvcContextListener.class.getName());

  private static GlobalCfg _cfg = null;
  private static IDbOps _hbnOps = null;
  private static Object _lock = new Object();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    if (_cfg == null) {
      synchronized (_lock) {
        if (_cfg == null) {
          try {
            String path = sce.getServletContext().getRealPath("/WEB-INF/svc_cfg.json");
            String cfgJson = IOUtils.toString(
              new FileInputStream(path),
              StandardCharsets.UTF_8
            );
            _cfg = GlobalCfg.fromJson(cfgJson);
            logger.info("Configuration initialized: " + _cfg);

            String hbnCfgPath = sce.getServletContext().getRealPath("/WEB-INF/hibernate.cfg.xml");
            _hbnOps = HbnDbOpsImpl.hbnOps(hbnCfgPath);
            logger.info("Hibernate session factory created!");
          } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error loading config", ex);
          }
        }
      }
    }
  }

  static GlobalCfg getCfg() {
    return _cfg;
  }

  static IDbOps getDbOps() {
    return _hbnOps;
  }

//  @Override
//  public void contextDestroyed(ServletContextEvent sce) {
//    System.out.println(
//      "destroying context: " +
//        sce.getServletContext()
//    );
//  }
}
