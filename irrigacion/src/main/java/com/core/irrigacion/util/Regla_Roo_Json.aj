// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.util;

import com.core.irrigacion.util.Regla;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Regla_Roo_Json {
    
    public String Regla.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Regla.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Regla Regla.fromJsonToRegla(String json) {
        return new JSONDeserializer<Regla>()
        .use(null, Regla.class).deserialize(json);
    }
    
    public static String Regla.toJsonArray(Collection<Regla> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Regla.toJsonArray(Collection<Regla> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Regla> Regla.fromJsonArrayToReglas(String json) {
        return new JSONDeserializer<List<Regla>>()
        .use("values", Regla.class).deserialize(json);
    }
    
}