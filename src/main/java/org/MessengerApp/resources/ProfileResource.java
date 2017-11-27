package org.MessengerApp.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.MessengerApp.model.Profile;
import org.MessengerApp.service.ProfileService;

@Path("/myprofile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProfileResource {

	@GET
	public List<Profile> getProfile() throws ClassNotFoundException, SQLException {

		ProfileService profSer = new ProfileService();
		return profSer.getAllProfiles();
	}

	@GET
	@Path("/{profileName}")
	public Profile getParticularProfile(@PathParam("profileName") String pName)
			throws NumberFormatException, ClassNotFoundException, SQLException {
		ProfileService profSer = new ProfileService();
		return profSer.getProfile(pName);

	}

	@POST
	public String addNewProfile(Profile prof) throws ClassNotFoundException, SQLException {
		ProfileService profSer = new ProfileService();
		return profSer.addProfile(prof);
	}

	@PUT
	@Path("/{profileName}")
	public String updateExistingProfilee(@PathParam("profileName") String pName, Profile profileWithOutProfNameProp)
			throws ClassNotFoundException, SQLException {
		ProfileService profSer = new ProfileService();
		return profSer.updateProfile(pName, profileWithOutProfNameProp);
	}

	@DELETE
	@Path("/{profileName}")
	public String deleteMyProfile(@PathParam("profileName") String pName)
			throws NumberFormatException, ClassNotFoundException, SQLException {
		ProfileService profSer = new ProfileService();
		return profSer.removeProfile(pName);

	}
}
