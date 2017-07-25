package models;


public class PrefModel {


    private Class<?> classObject;
    private boolean defaultValueBoolean;
    private int defaultValueInt;
    private String prefName;


    public PrefModel(String prefName, Class<?> classObject) {
        this.classObject = classObject;
        this.prefName = prefName;
    }

    public PrefModel(String prefName, boolean defaultValueBoolean) {
        this.defaultValueBoolean = defaultValueBoolean;
        this.prefName = prefName;
    }

    public Class<?> getClassObject() {
        return classObject;
    }

    public boolean getDefaultValueBoolean() {
        return defaultValueBoolean;
    }

    public int getDefaultValueInt() {
        return defaultValueInt;
    }

    public String getPrefName() {
        return prefName;
    }

}