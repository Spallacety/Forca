package br.edu.ifpi.forca.modelo;

public class Boneco {

	private String corpo = "\n";
	private int vida = 6;
	private boolean dead = false;
	
	public void atualizarBoneco(){
		if (vida == 6){this.corpo = "\n";}
		else if (vida == 5){this.corpo = "  O";}
		else if (vida == 4){this.corpo = "  O\n /\n";}
		else if (vida == 3){this.corpo = "  O\n /|\n";}
		else if (vida == 2){this.corpo = "  O\n /|\\";}
		else if (vida == 1){this.corpo = "  O\n /|\\ \n / \n";}
	}
	
	public String mostrar(){
		return corpo;
	}
	
	public int getVida(){
		return this.vida;
	}
	
	public void setVida(int vida){
		this.vida = vida;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
}
