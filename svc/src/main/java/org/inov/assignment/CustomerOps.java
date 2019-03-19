package org.inov.assignment;

import org.inov.assignment.hbn.IDbOps;
import org.inov.assignment.hbn.ent.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("customer")
public class CustomerOps {
  private final static Logger logger = Logger.getLogger(CustomerOps.class.getName());

//  @PUT
//  @Path("testAll")
//  @Produces(SvcUtils.MediaType_JSON_UTF8)
//  public Response allCustomers() {
//    try {
//      IDbOps dbOps = SvcUtils.getDbOps();
//      String res = dbOps.addNewCustomer("u1", "n1");
//
//      return Response.ok(res).build();
//    }
//    catch (Exception ex) {
//      ex.printStackTrace();
//      throw new RuntimeException("Error", ex);
//    }
//
//  }

  @GET
  @Path("all")
  @Produces(SvcUtils.MediaType_TXT_UTF8)
  public Response allCustomers() {
    try {
      logger.info("Running allCustomers ...");
      IDbOps dbOps = SvcUtils.getDbOps();
      List<Customer> res = dbOps.queryByName("n1");

      return Response.ok(res.size()).build();
    }
    catch (Exception ex) {
      logger.warning(ex.getMessage());
      ex.printStackTrace();
      throw new RuntimeException("Error", ex);
    }

  }
}
