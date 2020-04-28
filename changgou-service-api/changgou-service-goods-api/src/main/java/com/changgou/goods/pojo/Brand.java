package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="tb_brand")
public class Brand implements Serializable {
    @ApiModelProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; //品牌id
    @ApiModelProperty(value = "品牌名称",required = false)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(value = "品牌图片地址",required = false)
    @Column(name = "image")
    private String image;
    @ApiModelProperty(value = "品牌的首字母",required = false)
    @Column(name = "letter")
    private String letter;

    @ApiModelProperty(value = "排序",required = false)
    @Column(name = "seq")
    private Integer seq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
