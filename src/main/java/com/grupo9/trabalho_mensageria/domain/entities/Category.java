package com.grupo9.trabalho_mensageria.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity(name = "categories")
public class Category {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Category> children;

    @JsonValue
    public Map<String, Object> serialize() {
        if (this.parent != null) {
            Map<String, Object> root = new LinkedHashMap<>();
            root.put("id", this.parent.getId());
            root.put("name", this.parent.getName());

            Map<String, Object> sub = new LinkedHashMap<>();
            sub.put("id", this.id);
            sub.put("name", this.name);

            root.put("sub_category", sub);
            return root;
        }

        Map<String, Object> root = new LinkedHashMap<>();
        root.put("id", this.id);
        root.put("name", this.name);
        return root;
    }

    public void sa() {

    }
}