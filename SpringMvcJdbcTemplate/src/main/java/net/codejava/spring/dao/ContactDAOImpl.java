package net.codejava.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

import net.codejava.spring.model.Contact;

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;

	private MongoTemplate mongoTemplate;

	// public ContactDAOImpl(DataSource dataSource) {
	// jdbcTemplate = new JdbcTemplate(dataSource);
	// }
	public ContactDAOImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void saveOrUpdate(Contact contact) {

		mongoTemplate.save(contact);

	}

	@Override
	public void delete(String contactEmail) {
		// String sql = "DELETE FROM contact WHERE contact_id=?";
		// jdbcTemplate.update(sql, contactId);
		Query q = new Query();
		q.addCriteria(Criteria.where("email").is(contactEmail));
		mongoTemplate.remove(q, Contact.class);
	}

	@Override
	public List<Contact> list() {

		return mongoTemplate.findAll(Contact.class);
	}

	@Override
	public Contact get(String contactEmail) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(contactEmail));
		return mongoTemplate.findOne(query, Contact.class);
	}

	@Override
	public void update(Contact contact) {
		Query query=new Query();
		query.addCriteria(Criteria.where("email").is(contact.getEmail()));
		Update update=new Update();
		update.set("name", contact.getName());
		update.set("email",contact.getEmail());
		update.set("address",contact.getAddress());
		update.set("telephone", contact.getTelephone());
		mongoTemplate.updateFirst(query, update, Contact.class);
	}
}
// // Update the existing user to the mongo database.
//// Contact y= coll.update(existing, edit);
// return null;
// Query query=new Query();
// query.addCriteria(Criteria.where("email").is(contactEamil));
//
// Update u=new Update();
//
// u.set("email", contactEamil);
// mongoTemplate.updateMulti(query, u, Contact.class);

// @Override
// public Contact get(String contactEmail) {
// Contact contact=new Contact();
// BasicDBObject edit = new BasicDBObject();
// edit.put("Name", contact.getName());
//// edit.put("Email", contact.getEmail());
// edit.put("Address", contact.getAddress());
// edit.put("Telephone", contact.getTelephone());
// Query query=new Query();
// query.addCriteria(Criteria.where("email").is(contact.getEmail()));
//
// Update u=new Update();
//
// u.set("email", contactEmail);
//
//// return mongoTemplate.updateMulti(query, u, Contact.class);
// return null;
// }
