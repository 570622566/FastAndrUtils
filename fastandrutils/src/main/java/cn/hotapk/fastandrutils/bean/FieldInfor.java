package cn.hotapk.fastandrutils.bean;

/**
 * @author laijian
 * @version 2017/11/12
 * @Copyright (C)上午2:35 , www.hotapk.cn
 */
public class FieldInfor {

    private String title = "";
    private String type = "";
    private boolean isPrimary;

    public FieldInfor() {
    }

    public FieldInfor(String title, String type, boolean isPrimary) {
        this.title = title;
        this.type = type;
        this.isPrimary = isPrimary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
