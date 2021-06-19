package com.omelnur.roya.royaServiceApp.bluePrint;

import org.springframework.http.ResponseEntity;

public interface ControllerBluePrint<Object> {

    public abstract ResponseEntity getObject();
    public abstract Object getObjectById( Long id);
    public abstract Object addObject( Object object);
    public abstract Object updateObject(Long id, Object object);
    public abstract void deleteObject( Long id);
}
