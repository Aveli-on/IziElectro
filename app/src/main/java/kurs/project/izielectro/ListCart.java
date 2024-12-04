package kurs.project.izielectro;

public class ListCart {
    int idUser;
    int idProduct;
    int idDetail;
    String photo;
    String title;
    String description;
    int price;
    int quantity;

    public ListCart(int idUser, int idProduct, int idDetail, String photo, String title, String description, int price, int quantity) {
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.idDetail = idDetail;
        this.photo = photo;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
