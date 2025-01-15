package com.org.library;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.org.library.data.repository")
public class LibraryConfig {
}
