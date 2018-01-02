package xyz.ncvt.ifs.entity;

public class EnvironmentIndex {
    private String key;
    private String name;
    private int value;
    private String status;
    private int color;

    public EnvironmentIndex(String key, String name, int value) {
        this.key = key;
        this.name = name;
        this.value = value;
        if(value >= 12 && value <= 20) {
            this.status = "正常";
            this.color  = android.R.color.holo_green_light;
        }else {
            this.status = "警告";
            this.color  = android.R.color.holo_red_light;
        }


    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getStatus() {
        return status;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "EnvironmentIndex{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", status='" + status + '\'' +
                ", color=" + color +
                '}';
    }
}
