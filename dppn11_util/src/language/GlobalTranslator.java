package language;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daniel Plaza
 */
public enum GlobalTranslator {
    
    INSTANCE;
    
    private static final List<Translatable> translatableObjects= new LinkedList<>();
    private static Language language;   

    public void addComponent(Translatable translatableObject){
        translatableObjects.add(translatableObject);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language){
        GlobalTranslator.language = language;
        translate();
    }

    private void translate(){
        translatableObjects.stream().forEach((t) -> {
            t.translate();
        });
    }
    
    public static class Translatable{
        private final Object component;
        private final String changeTextMethod;
        private final String textMarkup;

        public Translatable(Object component, String changeTextMethod, String textMarkup) {
            this.component = component;
            this.changeTextMethod = changeTextMethod;
            this.textMarkup = textMarkup;
            translate();
        }
        
        private void translate(){
            java.lang.reflect.Method method;
            try {
                method = component.getClass().getMethod(changeTextMethod,String.class);
                method.invoke(component, language.getText(textMarkup));
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (NullPointerException ex){
                //Do nothing, the language hasnt been defined yet
            } 
        }
    }

}
