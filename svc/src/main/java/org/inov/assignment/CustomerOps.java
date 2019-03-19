package org.inov.assignment;

import org.inov.assignment.hbn.IDbOps;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.logging.Logger;

@Path("customer")
public class CustomerOps {
  private final static Logger logger = Logger.getLogger(CustomerOps.class.getName());

  @GET
  @Path("testAll")
  @RolesAllowed("user")
  @Produces(SvcUtils.MediaType_JSON_UTF8)
  public Response allCustomers(@Context SecurityContext sc) {
    logger.warning("(To be removed) " + sc.getUserPrincipal());
    try {
      IDbOps dbOps = SvcUtils.getDbOps();
      String res = dbOps.addNewCustomer("u1", "n1");

      return Response.ok(res).build();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException("Error", ex);
    }

  }
}
