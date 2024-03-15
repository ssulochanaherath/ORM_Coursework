package lk.ijse.dto;

import lk.ijse.entity.Book;
import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BranchDto {
    String id;
    String name;
    String address;
    String contact;
    String email;
    private List<User> users;
    private List<Book> books;
}
