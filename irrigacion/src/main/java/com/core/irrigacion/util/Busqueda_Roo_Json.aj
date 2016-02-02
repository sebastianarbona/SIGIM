// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.core.irrigacion.util;

import com.core.irrigacion.util.Busqueda;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Busqueda_Roo_Json {
    
    public String Busqueda.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Busqueda.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Busqueda Busqueda.fromJsonToBusqueda(String json) {
        return new JSONDeserializer<Busqueda>()
        .use(null, Busqueda.class).deserialize(json);
    }
    
    public static String Busqueda.toJsonArray(Collection<Busqueda> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Busqueda.toJsonArray(Collection<Busqueda> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Busqueda> Busqueda.fromJsonArrayToBusquedas(String json) {
        return new JSONDeserializer<List<Busqueda>>()
        .use("values", Busqueda.class).deserialize(json);
    }
    
}