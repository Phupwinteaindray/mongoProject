package com.wytu.address.converter;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.wytu.address.entity.Student;

public class PersonConverter {

	public static Document toDBObject(Student p) {

		BasicDBObject obj=new BasicDBObject();
		obj.put("name", p.getName());
		obj.put("country", p.getCountry());
		obj.put("email", p.getEmail());
		obj.put("rollNumber", p.getRollNumber());
//		BasicDBObject obj=BasicDBObject.parse("name",p.getName());
//		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
//				.append("name", p.getName()).append("country", p.getCountry());
		if (p.getId() != null) {
			obj.put("_id", new ObjectId(p.getId()));
//			builder = builder.append("_id", new ObjectId(p.getId()));
		}
			
			
		return new Document(obj.toMap());
	}
	
	public static Student toPerson(Document doc) {
		Student p = new Student();
		p.setName((String) doc.get("name"));
		p.setCountry((String) doc.get("country"));
		p.setEmail((String)doc.get("email"));
		p.setRollNumber((String)doc.get("rollNumber"));
		ObjectId id = (ObjectId) doc.get("_id");
		p.setId(id.toString());
		return p;

	}
}
