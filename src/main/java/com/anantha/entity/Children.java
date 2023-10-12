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
public class Children {
    private Integer length;
    private List<Child> list;
}
