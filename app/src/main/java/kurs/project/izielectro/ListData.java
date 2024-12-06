package kurs.project.izielectro;

public class ListData {
    String title,description,photo;
    int price,restOf,id,idCategory;

    public ListData(int id,int idCategory,String title,String description, String photo,int price,int restOf) {
        this.restOf = restOf;
        this.price = price;
        this.photo = photo;
        this.description = description;
        this.title = title;
        this.id=id;
        this.idCategory=idCategory;
    }
}
