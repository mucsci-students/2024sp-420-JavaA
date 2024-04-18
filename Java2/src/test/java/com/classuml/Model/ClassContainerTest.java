package com.classuml.Model;

import static org.junit.Assert.*;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class ClassContainerTest {
        @Test
	public void Classes() {
		ClassBase c = new ClassBase("test");
		SortedSet<Parameter> p1 = new TreeSet<Parameter>();
		p1.add(new Parameter("int", "a"));
		Method m = new Method("int","method",p1);
		SortedSet<Method> methods = new TreeSet<Method>();

		Field f = new Field("int","f1");
		SortedSet<Field> fields = new TreeSet<Field>();
		fields.add(f);
		Relationship r = new Relationship("rel","agg");
		SortedSet<Relationship> relationships = new TreeSet<Relationship>();
		relationships.add(r);
		ClassBase c1 = new ClassBase("test1",fields,methods,relationships, 0 , 0);
		
		SortedMap<String, ClassBase> classes = new TreeMap<String, ClassBase>();
		classes.put("test", c);
		classes.put("test1", c1);
		
		MementoState model = new MementoState(classes);
		assertEquals(classes,model.getClasses());
		
		MementoState model1 = new MementoState();
		model1.setClasses(classes);
		assertEquals(classes, model1.getClasses());
		
		MementoState model2 = new MementoState (classes,classes);
		assertEquals(classes,model2.getBackup());
		
		MementoState model3 = new MementoState();
		model3.setBackup(classes);
		assertEquals(classes, model3.getBackup());
	}
}
