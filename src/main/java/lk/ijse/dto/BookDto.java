package lk.ijse.dto;

import lk.ijse.entity.Branch;
import lk.ijse.entity.UserBookDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    String id;
    private Branch branch;
    private List<UserBookDetails> transactions;
    String title;
    String author;
    String genre;
    String imagePath;
    boolean isAvailable;
}
