package kurs.project.izielectro;

public class ListOrder {
    int idProduct;int quantity;
    String title,photo;

    public ListOrder(int idProduct, int quantity, String title, String photo) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.title = title;
        this.photo = photo;
    }
}
