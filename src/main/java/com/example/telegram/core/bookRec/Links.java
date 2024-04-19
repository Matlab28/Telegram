package com.example.telegram.core.bookRec;

import lombok.Getter;

@Getter
public enum Links {
    ROMANCE_1("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1700522826i/41865.jpg"),
    ROMANCE_2("https://target.scene7.com/is/image/Target/GUEST_a882a301-e17a-460c-92bf-0d655bcaa1dc?wid=488&hei=488&fmt=pjpeg"),
    ROMANCE_3("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1666362713i/62967897.jpg"),
    ROMANCE_4("https://m.media-amazon.com/images/I/91kG2nNdqXL._AC_UF1000,1000_QL80_.jpg"),
    ROMANCE_5("https://m.media-amazon.com/images/I/61I24wOsn8L._AC_UF1000,1000_QL80_.jpg"),
    ROMANCE_6("https://upload.wikimedia.org/wikipedia/en/a/a2/Catching_Fire_%28Suzanne_Collins_novel_-_cover_art%29.jpg"),
    SCIENCE_FICTION_1("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1560816565l/48855.jpg"),
    SCIENCE_FICTION_2("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1555447414i/44767458.jpg"),
    SCIENCE_FICTION_3("https://m.media-amazon.com/images/I/71rpa1-kyvL._AC_UF1000,1000_QL80_.jpg"),
    SCIENCE_FICTION_4("https://m.media-amazon.com/images/I/717e5mrJz+L._AC_UF1000,1000_QL80_.jpg"),
    SCIENCE_FICTION_5("https://m.media-amazon.com/images/I/71f32nD8PCL._AC_UF1000,1000_QL80_.jpg"),
    SCIENCE_FICTION_6("https://m.media-amazon.com/images/I/91YWt2jiowL._AC_UF1000,1000_QL80_.jpg"),
    NON_FICTION_1("https://m.media-amazon.com/images/I/61V8g4GgqdL._AC_UF1000,1000_QL80_.jpg"),
    NON_FICTION_2("https://m.media-amazon.com/images/I/71wXZB-VtBL._AC_UF1000,1000_QL80_.jpg"),
    NON_FICTION_3("https://m.media-amazon.com/images/I/71O3PTUA3vL._AC_UF1000,1000_QL80_.jpg"),
    NON_FICTION_4("https://m.media-amazon.com/images/I/41r85eg1ZpL.jpg"),
    NON_FICTION_5("https://m.media-amazon.com/images/I/810qv+xLezL._AC_UF1000,1000_QL80_.jpg"),
    NON_FICTION_6("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1398768721i/16070018.jpg");

    private String coverImageUrl;

    Links(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
