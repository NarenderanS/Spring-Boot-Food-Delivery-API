package com.restapi.response;

import com.restapi.request.CategoryRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CategoryResponse {
    private long id;
    private String title;
    @Column(name = "photo")
    private String photo;

}
