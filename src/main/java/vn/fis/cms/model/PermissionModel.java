package vn.fis.cms.model;

public class PermissionModel {
    private boolean  islock;
    private int id;
    private String code, description;

    public PermissionModel(int id, String code, String description, boolean islock) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.islock = islock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isIslock() {
        return islock;
    }

    public void setIslock(boolean islock) {
        this.islock = islock;
    }
}
