package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_product")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="productid")
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name="short_desc")
    private String short_desc;
    @Column(name = "created_date")
    private Date createdDate;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getshort_desc() {
        return short_desc;
    }

    public void setshort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
