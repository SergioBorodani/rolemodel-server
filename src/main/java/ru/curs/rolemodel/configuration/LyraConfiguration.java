package ru.curs.rolemodel.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ru.curs.lyra.controller", "ru.curs.lyra.service", "ru.curs.rolemodel.lyra"})
public class LyraConfiguration {
}
