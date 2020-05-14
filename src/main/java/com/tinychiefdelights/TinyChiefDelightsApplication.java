package com.tinychiefdelights;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@EnableSwagger2
public class TinyChiefDelightsApplication {

    // MAIN
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        SpringApplication.run(TinyChiefDelightsApplication.class, args);

        // Экземпляр класса для запуска методов
        TinyChiefDelightsApplication tinyChiefDelightsApplication = new TinyChiefDelightsApplication();

        // Запускаем runningScripts
        tinyChiefDelightsApplication.runningScripts();
    }


    // Script для инициализации БД
    // Использована библиотека IBATIS
    //
    private void runningScripts() throws SQLException, FileNotFoundException {
        // Тут подключаемся к БД
        String mysqlUrl = "jdbc:postgresql://localhost:8083/tinychiefdelights";
        Connection con = DriverManager.getConnection(mysqlUrl, "postgres", "barca3508");
        System.out.println("Connection established......");
        // Инициализируем ScriptRunner
        ScriptRunner sr = new ScriptRunner(con);
        System.out.println("Инициализируем базу данных PostgreSQL...");
        // Создаем reader и передаем туда файл .sql
        Reader reader = new BufferedReader(new FileReader
                ("D:\\JetBrainsProjects\\TinyChiefDelights\\src\\main\\java\\com\\tinychiefdelights\\database\\initDataBase.sql"));
        // Запускаем runner
        sr.runScript(reader);
        System.out.println("База данных успешно проинициализированна!");
    }
}