package com.anantha.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author anantha
 */
@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Parents {
    private Integer length;
    private List<Parent> list;
}
