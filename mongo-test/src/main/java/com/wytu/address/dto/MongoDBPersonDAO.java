package com.wytu.address.dto;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.wytu.address.converter.PersonConverter;
import com.wytu.address.entity.Student;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoDBPersonDAO {

	private MongoCollection<Document> col;
	//private MongoCollection<Document> col;

	public MongoDBPersonDAO( ) {
		MongoClient mongoClient=new MongoClient();
		//MongoClient mongoClient=(MongoClient) MongoClients.create("mongodb://localhost:27017");
		//this.col= mongoClient.getDatabase("person").getCollection("students");
//	    MongoDatabase database = mongoClient.getDatabase("student");
//	    MongoCollection<Document> collection=database.getCollection("std");
		this.col =   mongoClient.getDatabase("person").getCollection("students");
	}

	public Student createPerson(Student p) {
		Document doc = PersonConverter.toDBObject(p);
		this.col.insertOne(doc);
		//this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		p.setId(id.toString());
		return p;
	}
	
/*	public void updatePerson(Person p) {
		BasicDBObject obj=new BasicDBObject();
//		obj.put("name", p.getName());
//		obj.put("country", p.getCountry());
//		obj.put("_id", new ObjectId(p.getId()));
//		obj.put("name", p.getName());
//		obj.put("country", p.getCountry());
		obj.put("_id", p.getId());
		
//		 collection.updateOne(new Document("name", "Win Yathaw Htet"),
//	                new Document("$set", new Document("email", "yathaw@gmail.com")));
//		DBObject query=BasicDBObjectBuilder.start().append("_id", new ObjectId(p.getId())).get();
//		DBObject query = BasicDBObjectBuilder.start()
//				.append("_id", new ObjectId(p.getId())).get();
		this.col.updateOne(obj, PersonConverter.toDBObject(p));
//		this.col.updateOne((Bson) query, PersonConverter.toDBObject(p));
	}*/
	public void updatePerson(Student p) {
		this.col.updateOne(new Document("_id", new ObjectId(p.getId())),
                new Document("$set", PersonConverter.toDBObject(p)));
	}

	public List<Student> readAllPerson() {
		List<Student> data = new ArrayList<Student>();
		MongoCursor<Document> cursor = col.find().iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			Student p = PersonConverter.toPerson(doc);
			data.add(p);
		}
		return data;
	}
	
	public void deletePerson(Student p) {
		BasicDBObject obj=new BasicDBObject();
		obj.put("_id", new ObjectId(p.getId()));
//		DBObject query = BasicDBObjectBuilder.start()
//				.append("_id", new ObjectId(p.getId())).get();
		this.col.deleteOne(obj);
//		this.col.remove(query);
	}

	public Student readPerson(Student p) {
		BasicDBObject obj=new BasicDBObject();
		obj.put("_id", new ObjectId(p.getId()));
		
		MongoCursor<Document> cursor = col.find(obj).iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			Student person = PersonConverter.toPerson(doc);
			return person;
		}
		
		return null;
//		DBObject data = this.col.findOne(query);
//		return PersonConverter.toPerson(data);
	}

	public void saveAll(List<String> stList) {
		// TODO Auto-generated method stub
		for(String line:stList) {
			
			Student p=getCourseFromLine(line);
			createPerson(p);
		}
		System.out.println("Total time for saving all data "+System.nanoTime());
		
	}

	private Student getCourseFromLine(String line) {
		// TODO Auto-generated method stub
		Student c=new Student();
		String[] stLine=line.split(",,",-2);
		for (String s : stLine) {
			System.out.println(s);
		}
		c.setName(stLine[1]);
		c.setRollNumber(stLine[3]);
		c.setCountry(stLine[2]);
		c.setEmail(stLine[0]);
		
		
		return c;
	}

}
