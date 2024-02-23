package hello.servlet.web.frontcontoller;

import java.util.HashMap;
import java.util.Map;

/**
 * Make Study. 31강. Model 추가 (v3)
 * */
public class ModelView {

    private String viewname;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewname) {
        this.viewname = viewname;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
