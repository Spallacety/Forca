package br.edu.ifpi.forca.modelo;

public class Jogador {
	
	private int pontos;
	private String nome;

	public Jogador(String nome){
		this.nome = nome;
	}
	
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos += pontos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}