package application.viewmodel.common;

public class ProductVM {
    private  int id;
    private String name;
    private  String image;
    private  String short_desc;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getshort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }
}
