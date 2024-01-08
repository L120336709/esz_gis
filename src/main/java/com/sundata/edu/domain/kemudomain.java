package com.sundata.edu.domain;

import java.io.Serializable;
import java.util.HashMap;

public class kemudomain implements Serializable {

    private String tableName;

    private HashMap<Object,Object> colsValue=new HashMap<>();


    public HashMap<Object,Object> GetColsValue(){
        return this.colsValue;
    }
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColsValue(Object keyId,Object keyValue){
        this.colsValue.put(keyId,keyValue);
    }

    public Object getColsValue(Object keyId){
        if(colsValue.containsKey(keyId))
            return colsValue.get(keyId);
        return null;
    }
}

