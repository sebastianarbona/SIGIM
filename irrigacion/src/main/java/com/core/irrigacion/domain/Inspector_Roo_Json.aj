// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.domain;

import com.core.irrigacion.domain.Inspector;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Inspector_Roo_Json {
    
    public String Inspector.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Inspector.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Inspector Inspector.fromJsonToInspector(String json) {
        return new JSONDeserializer<Inspector>()
        .use(null, Inspector.class).deserialize(json);
    }
    
    public static String Inspector.toJsonArray(Collection<Inspector> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Inspector.toJsonArray(Collection<Inspector> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Inspector> Inspector.fromJsonArrayToInspectors(String json) {
        return new JSONDeserializer<List<Inspector>>()
        .use("values", Inspector.class).deserialize(json);
    }
    
}
