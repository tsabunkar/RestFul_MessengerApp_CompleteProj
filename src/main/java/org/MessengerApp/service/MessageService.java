package org.MessengerApp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.MessengerApp.model.Message;

public class MessageService {

	DBConnection db = new DBConnection();

	public List<Message> getAllMessage() throws SQLException, ClassNotFoundException {
		Connection con = db.Connect();
		String sql = "select * from messenger_table";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Message> listMess = new ArrayList<Message>();
		while (rs.next()) {
			Message mess = new Message();
			mess.setId(rs.getInt(1));
			mess.setMessage_detail(rs.getString(2));
			mess.setCreated(rs.getDate(3));
			mess.setAuthor(rs.getString(4));
			listMess.add(mess);
			System.out.println(mess + " added ");
		}
		con.close();
		return listMess;

	}

	public Message getMessage(int id) throws ClassNotFoundException, SQLException {
		Connection con = db.Connect();
		String sql = "select * from messenger_table where id=?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
			/*rs.next();
			Message mess = new Message();
			mess.setId(rs.getInt(1));
			mess.setMessage_detail(rs.getString(2));
			mess.setCreated(rs.getDate(3));
			mess.setAuthor(rs.getString(4));
			return mess;*/
			
		if(rs.next()) {
		Message mess = new Message();
		mess.setId(rs.getInt(1));
		mess.setMessage_detail(rs.getString(2));
		mess.setCreated(rs.getDate(3));
		mess.setAuthor(rs.getString(4));
		return mess;
		}
		else {
			return null;
		}
		
		
	}

	public String addMessage(Message message) throws ClassNotFoundException, SQLException {
		Connection con = db.Connect();
		String sql = "insert into messenger_table value(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, message.getId());
		ps.setString(2, message.getMessage_detail());
		Date utilDate = message.getCreated();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		ps.setDate(3, sqlDate);
		ps.setString(4, message.getAuthor());
		int i = ps.executeUpdate();
		con.close();
		return i + " Record added successfully...";

	}

	public String updateMessage(int id, Message messWithOutIdProp) throws ClassNotFoundException, SQLException {
		Connection con = db.Connect();
		String sql = "update messenger_table set message_detail=?,created=?,author=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, messWithOutIdProp.getMessage_detail());
		Date utilDate = messWithOutIdProp.getCreated();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		ps.setDate(2, sqlDate);
		ps.setString(3, messWithOutIdProp.getAuthor());
		ps.setInt(4, id);
		int i = ps.executeUpdate();
		con.close();
		return i + " record update sucessfully..";
	}

	public String removeMessage(int id) throws ClassNotFoundException, SQLException {
		Connection con = db.Connect();
		String sql = "delete from messenger_table where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int i = ps.executeUpdate();
		con.close();
		return i + " records are deleted...";

	}
	
	public List<Message> getAllMessageForYear(int year) throws SQLException, ClassNotFoundException{
		List<Message> messageForYear = new ArrayList<Message>();
		Calendar calend = Calendar.getInstance(); //calendar obj is created
		List<Message> listMess = getAllMessage();
		
		for (Message message : listMess) {
			calend.setTime(message.getCreated());//setting the message dateCreated to calendar instances
			if(calend.get(Calendar.YEAR) == year)//gets the year of that calendar for which message date created
				messageForYear.add(message);
		}
		return messageForYear;
		
	}
	
	
	public List<Message> getAllMessagesPaginated(int start, int size) throws ClassNotFoundException, SQLException{
		List<Message> listMess = getAllMessage();
		if(start + size > listMess.size())
			return new ArrayList<Message>();
		return listMess.subList(start, size);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
