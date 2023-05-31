package org.sid.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebankingbackend.enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // L'héritage gérére les tables pour la classe mère et les classes filles
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // l'héritage générera une table pour chaque entité dans ce cas DiscriminatorColumn n'a plus de sens dans toutes les classes de l'héritage.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // l'héritage générera une seule table
@DiscriminatorColumn(name = "TYPE", length = 4) //La colonne type contient l'identité des classes filles dans la table générer par l'héritage
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY) // Le Mode LAZY permet de charger le compte sans les operations alors que le EAGER charge le compte et les operations le EAGER n'est pas pratique
    private List<AccountOperation> accountOperations;
}
