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

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        SpringApplication.run(TinyChiefDelightsApplication.class, args);
        TinyChiefDelightsApplication tinyChiefDelightsApplication = new TinyChiefDelightsApplication();
        tinyChiefDelightsApplication.runningScripts();
    }

    public void runningScripts() throws SQLException, FileNotFoundException {
        //Registering the Driver
        //Getting the connection
        String mysqlUrl = "jdbc:postgresql://localhost:8083/tinychiefdelights";
        Connection con = DriverManager.getConnection(mysqlUrl, "postgres", "");
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader("D:\\JetBrainsProjects\\TinyChiefDelights/src/main/com/tinychiefdelights/database/databaseDatas.sql"));
        //Running the script
        sr.runScript(reader);
    }
}