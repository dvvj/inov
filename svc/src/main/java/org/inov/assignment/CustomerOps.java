package org.inov.assignment;

import org.inov.assignment.hbn.IDbOps;
import org.inov.assignment.hbn.ent.Customer;
import org.inov.assignment.hbn.utils.HbnUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("customer")
public class CustomerOps {
  private final static Logger logger = Logger.getLogger(CustomerOps.class.getName());

  @PUT
  @Path("create")
  @Consumes(SvcUtils.MediaType_TXT_UTF8)
  public Response newCustomer(String customerJson) {
    try {
      Customer customer = HbnUtils.fromJson(customerJson);
      IDbOps dbOps = SvcUtils.getDbOps();
      dbOps.addNewCustomer(customer);

      return Response.status(Response.Status.CREATED).build();
    }
    catch (Exception ex) {
      ex.printStackTrace();
//      throw new RuntimeException("Error adding customer!", ex);
      return Response.status(Response.Status.BAD_REQUEST).entity(
        String.format("Customer (json: %s) not added, error: %s", customerJson, ex.getMessage())
      ).build();
    }
  }

  @GET
  @Path("testGetAll")
  @Produces(SvcUtils.MediaType_TXT_UTF8)
  public Response allCustomers() {
    try {
      logger.info("Running allCustomers ...");
      IDbOps dbOps = SvcUtils.getDbOps();
      List<Customer> res = dbOps.queryByName("n1");

      String json = HbnUtils.toJson(res);
      return Response.ok(json).build();
    }
    catch (Exception ex) {
      logger.warning(ex.getMessage());
      ex.printStackTrace();
      throw new RuntimeException("Error getting all customers", ex);
    }

  }
}
