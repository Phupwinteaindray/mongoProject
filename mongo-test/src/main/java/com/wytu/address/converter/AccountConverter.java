package com.wytu.address.converter;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.wytu.address.entity.Account;

public class AccountConverter {
	public static Document toDBObject(Account p) {

		BasicDBObject obj=new BasicDBObject();
		obj.put("loginId", p.getLoginId());
		obj.put("password", p.getPassword());
		obj.put("role", p.getRole());
//		BasicDBObject obj=BasicDBObject.parse("name",p.getName());
//		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
//				.append("name", p.getName()).append("country", p.getCountry());
		if (p.getId() != null) {
			obj.put("_id", new ObjectId(p.getId()));
//			builder = builder.append("_id", new ObjectId(p.getId()));
		}
			
			
		return new Document(obj.toMap());
	}
	
	public static Account toAccount(Document doc) {
		Account account=new Account();
		account.setLoginId((String)doc.get("loginId"));
		account.setPassword((String)doc.get("password"));
		account.setRole((String)doc.get("role"));
//		Person p = new Person();
//		p.setName((String) doc.get("name"));
//		p.setCountry((String) doc.get("country"));
		ObjectId id = (ObjectId) doc.get("_id");
		account.setId(id.toString());
		return account;

	}
}
