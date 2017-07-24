package constants;


public enum PrefKeys {

    // define constants for shared preferences
    LOGIN_STATE(false);
//    TEST_OBJECT(News.class),
//    TEST_LIST(News[].class);
//
//


    // constructors and getters
    private Class<?> classObject;
    private boolean defaultValue;

    private PrefKeys(Class<?> classObject) {
        this.classObject = classObject;
    }

    private PrefKeys( boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Class<?> getClassObject() {
        return classObject;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }

}