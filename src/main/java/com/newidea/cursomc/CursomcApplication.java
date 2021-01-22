package com.newidea.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("    .o88o.                               o8o                . ");
		System.out.println("    888 `'                               `''              .o8 ");
		System.out.println("   o888oo   .oooo.o  .ooooo.   .ooooo.  oooo   .ooooo.  .o888oo oooo    ooo ");
		System.out.println("    888    d88(  '8 d88' `88b d88' `'Y8 `888  d88' `88b   888    `88.  .8' ");
		System.out.println("    888    `'Y88b.  888   888 888        888  888ooo888   888     `88..8' ");
		System.out.println("    888    o.  )88b 888   888 888   .o8  888  888    .o   888 .    `888' ");
		System.out.println("   o888o   8''888P' `Y8bod8P' `Y8bod8P' o888o `Y8bod8P'   '888'      d8' ");
		System.out.println("                                                                .o...P' ");
	}
}

