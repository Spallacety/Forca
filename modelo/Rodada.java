package br.edu.ifpi.forca.modelo;

import java.util.ArrayList;
import java.util.List;

public class Rodada {
	
	private static Boneco boneco = new Boneco();
	private static int vida = 6;
	private static Tema tema;
	private static List<Tema> temas = new ArrayList<>();
	private static Jogador jogador;
	private static String[] palavra;
	private static String[] escondidas;
	private static String letrasErradas = "";
	private static String letrasAcertadas = "";

	// temas pré existentes
	private static Tema animais = new Tema("Cidade", "Teresina Fortaleza Natal Aracaju Belem");
	private static Tema frutas  = new Tema("Frutas", "Graviola Tangerina Kiwi");
	private static Tema veiculos  = new Tema("Veiculos", "Palio Corsa Hilux RangeRover");
	
	public Rodada(Jogador jogador){
		
		Rodada.jogador = jogador;
		
		temas.add(animais);
		temas.add(frutas);
		temas.add(veiculos);
			
		int n = (int)(Math.random() * ((temas.size())));
			
		tema = temas.get(n);

		n = (int)(Math.random() * ((tema.getConjuntoPalavras().length)));
			
		palavra = tema.getConjuntoPalavras()[n].split("");
		escondidas = new String[palavra.length];
			
		for (int i = 0; i < palavra.length; i++) {
			escondidas[i] = "_ ";
		}
	}

	public Boneco getBoneco() {
		return boneco;
	}

	public void setBoneco(Boneco boneco) {
		Rodada.boneco = boneco;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		Rodada.vida -= vida;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		Rodada.tema = tema;
	}

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		Rodada.temas = temas;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		Rodada.jogador = jogador;
	}

	public String[] getPalavra() {
		return palavra;
	}

	public void setPalavra(String[] palavra) {
		Rodada.palavra = palavra;
	}

	public String[] getEscondidas() {
		return escondidas;
	}

	public void setEscondidas(String[] escondidas) {
		Rodada.escondidas = escondidas;
	}

	public String getLetrasErradas() {
		return letrasErradas;
	}

	public void setLetrasErradas(String letrasErradas) {
		Rodada.letrasErradas += " " + letrasErradas;
	}

	public String getLetrasAcertadas() {
		return letrasAcertadas;
	}

	public void setLetrasAcertadas(String letrasAcertadas) {
		Rodada.letrasAcertadas += " " +  letrasAcertadas;
	}

}