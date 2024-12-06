package kurs.project.izielectro;

public class ListOrder {
    int idProduct;int quantity;
    String title,photo,fio;

    public ListOrder(int idProduct, int quantity, String title, String photo,String fio) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.title = title;
        this.photo = photo;
        this.fio=fio;
    }
}
