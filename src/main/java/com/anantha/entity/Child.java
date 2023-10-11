package com.anantha.entity;

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
public class Child {
    private Integer id;
    private Integer parentId;
    private Integer paidAmount;
}