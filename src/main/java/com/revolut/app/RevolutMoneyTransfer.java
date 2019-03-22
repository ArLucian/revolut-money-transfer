package com.revolut.app;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = { "com.revolut.app" })
@EnableJpaRepositories("com.revolut.app.repository")
@EnableTransactionManagement
public class RevolutMoneyTransfer {
    public static void main(String[] args) {
        SpringApplication.run(RevolutMoneyTransfer.class, args);
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * PlatformTransactionManager Bean definition.
     *
     * @return {@link PlatformTransactionManager}
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
