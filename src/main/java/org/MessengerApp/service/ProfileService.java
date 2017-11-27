package org.MessengerApp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.MessengerApp.model.Profile;

public class ProfileService {

	DBConnection db = new DBConnection();

	public List<Profile> getAllProfiles() throws SQLException, ClassNotFoundException {
		Connection con = db.Connect();
		String sql = "select * from profile_table";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Profile> listProf = new ArrayList<Profile>();
		while (rs.next()) {
			Profile prof = new Profile();
			prof.setPid(rs.getInt(1));
			prof.setProfileName(rs.getString(2));
			prof.setFirstName(rs.getString(3));
			prof.setLastName(rs.getString(4));
			prof.setCreated(rs.getDate(5));
			listProf.add(prof);
			System.out.println(prof + " added ");
		}
		con.close();
		return listProf;

	}

	public Profile getProfile(String pname) throws ClassNotFoundException, SQLException {
		Connection con = db.Connect();
		String sql = "select * from profile_table where profileName=?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, pname);
		ResultSet rs = ps.executeQuery();
		rs.next();

		Profile prof = new Profile();
		prof.setPid(rs.getInt(1));
		prof.setProfileName(rs.getString(2));
		prof.setFirstName(rs.getString(3));
		prof.setLastName(rs.getString(4));
		prof.setCreated(rs.getDate(5));

		con.close();
		return prof;
	}

	public String addProfile(Profile profile) throws ClassNotFoundException, SQLException {
		Connection con = db.Connect();
		String sql = "insert into profile_table value(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, profile.getPid());
		ps.setString(2, profile.getProfileName());
		ps.setString(3, profile.getFirstName());
		ps.setString(4, profile.getLastName());
		Date utilDate = profile.getCreated();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		ps.setDate(5, sqlDate);

		int i = ps.executeUpdate();
		con.close();
		return i + " Record added successfully...";

	}

	public String updateProfile(String profName, Profile profWithOutProfNameProp)
			throws ClassNotFoundException, SQLException {
		Connection con = db.Connect();
		String sql = "update profile_table set pid=?,firstName=?,lastName=?,created=? where profileName=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, profWithOutProfNameProp.getPid());
		ps.setString(2, profWithOutProfNameProp.getFirstName());
		ps.setString(3, profWithOutProfNameProp.getLastName());
		Date utilDate = profWithOutProfNameProp.getCreated();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		ps.setDate(4, sqlDate);

		ps.setString(5, profName);
		int i = ps.executeUpdate();
		con.close();
		return i + " record update sucessfully..";
	}

	public String removeProfile(String pname) throws ClassNotFoundException, SQLException {
		Connection con = db.Connect();
		String sql = "delete from profile_table where profileName=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, pname);
		int i = ps.executeUpdate();
		con.close();
		return i + " records are deleted...";

	}
}