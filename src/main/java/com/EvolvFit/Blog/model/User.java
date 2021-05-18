package com.evolvfit.blog.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NonNull
    @Column(name = "first_name", columnDefinition = "text", nullable = false)
    private String firstName;

    @NotBlank
    @NonNull
    @Column(name = "second_name", columnDefinition = "text", nullable = false)
    private String secondName;

//    @OneToMany(mappedBy = "postedBy")
//    private List<Blog> blogs;
//
//    @OneToMany(mappedBy = "commentedBy")
//    private List<Comment> comments;

}
