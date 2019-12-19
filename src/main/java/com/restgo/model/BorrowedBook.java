package com.restgo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBook {
    private int userId;
    private String username;
    private String title;
    private Date takenDate;
    private Date returnDate;
}
