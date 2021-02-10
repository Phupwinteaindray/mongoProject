package com.wytu.address.dto;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.wytu.address.converter.AccountConverter;
import com.wytu.address.entity.Account;

public class MongoDBAccountDAO {

	private MongoCollection<Document> col;
	//private MongoCollection<Document> col;

	public MongoDBAccountDAO( ) {
		MongoClient mongoClient=new MongoClient();
		//MongoClient mongoClient=(MongoClient) MongoClients.create("mongodb://localhost:27017");
		//this.col= mongoClient.getDatabase("person").getCollection("students");
//	    MongoDatabase database = mongoClient.getDatabase("student");
//	    MongoCollection<Document> collection=database.getCollection("std");
		this.col =   mongoClient.getDatabase("person").getCollection("account");
	}

	public Account createPerson(Account p) {
		Document doc = AccountConverter.toDBObject(p);
		this.col.insertOne(doc);
		//this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		p.setId(id.toString());
		return p;
	}
	
	public void updatePerson(Account p) {
		BasicDBObject obj=new BasicDBObject();
//		obj.put("name", p.getName());
//		obj.put("country", p.getCountry());
		obj.put("_id", new ObjectId(p.getId()));
		
//		 collection.updateOne(new Document("name", "Win Yathaw Htet"),
//	                new Document("$set", new Document("email", "yathaw@gmail.com")));
		
//		DBObject query = BasicDBObjectBuilder.start()
//				.append("_id", new ObjectId(p.getId())).get();
		this.col.updateOne(obj, AccountConverter.toDBObject(p));
//		this.col.update(query, PersonConverter.toDBObject(p));
	}

	public List<Account> readAllPerson() {
		List<Account> data = new ArrayList<Account>();
		MongoCursor<Document> cursor = col.find().iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			Account p = AccountConverter.toAccount(doc);
			data.add(p);
		}
		return data;
	}
	
	public void deletePerson(Account p) {
		BasicDBObject obj=new BasicDBObject();
		obj.put("_id", new ObjectId(p.getId()));
//		DBObject query = BasicDBObjectBuilder.start()
//				.append("_id", new ObjectId(p.getId())).get();
		this.col.deleteOne(obj);
//		this.col.remove(query);
	}

	public Account readPerson(Account p) {
//		BasicDBObject obj=new BasicDBObject();
//		obj.put("loginId", p.getLoginId());
//		obj.put("password", p.getPassword());
		
		List<BasicDBObject> list = new ArrayList<BasicDBObject>();
        list.add(new BasicDBObject("loginId", p.getLoginId()));
        list.add(new BasicDBObject("password", p.getPassword()));
 
        // Form a where query
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("$and", list);
		
		MongoCursor<Document> cursor = col.find(whereQuery).iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			Account person = AccountConverter.toAccount(doc);
			return person;
		}
		
		return null;
//		DBObject data = this.col.findOne(query);
//		return PersonConverter.toPerson(data);
	}

}
