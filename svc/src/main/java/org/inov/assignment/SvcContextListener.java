package org.inov.assignment;

import org.inov.assignment.hbn.HbnDbOpsImpl;
import org.inov.assignment.hbn.IDbOps;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

public class SvcContextListener implements ServletContextListener {

  private final static Logger logger = Logger.getLogger(SvcContextListener.class.getName());

  private static IDbOps _hbnOps = null;
  private static Object _lock = new Object();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    if (_hbnOps == null) {
      synchronized (_lock) {
        if (_hbnOps == null) {
          try {
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


  static IDbOps getDbOps() {
    return _hbnOps;
  }

}
