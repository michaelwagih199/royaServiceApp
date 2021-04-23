package com.omelnur.roya.royaServiceApp.bluePrint;

public interface BluePrintService<Object> {
    public abstract Object createObject(Object object);
    @Deprecated
    public abstract Object updateObject(Long id, Object object);
    public abstract void deleteObject(Long id);
    public abstract Iterable<Object> getAllObject();
    public abstract Object findObject(Long id);
}
