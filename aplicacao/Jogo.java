package br.edu.ifpi.forca.aplicacao;

import javax.swing.JOptionPane;
import br.edu.ifpi.forca.modelo.*;

public class Jogo {
	
	public static void main(String[] args) {
		
		boolean active = true;
		String nome = JOptionPane.showInputDialog("Digite seu nome:");
		Rodada rodada = new Rodada(new Jogador(nome));
		
		while(active){
			
			String menu = "Bem vindo, " + rodada.getJogador().getNome() + "!\n\n1 - Jogar partida\n2 - Incrementar banco de dados\n3 - Exibir temas existentes\n0 - Sair da aplicação\n\nEscolha uma opção: ";
			int opcao = inputInt(menu);
			switch(opcao){
			case 1:
				mostrar(rodada);
				while(rodada.getBoneco().isDead() == false){
					inputLetra(rodada);
				}
				active = false;
				break;
			case 2:
				incrementarTemas(rodada);
				break;
			case 3:
				exibir(exibirTemas(rodada));
				break;
			case 0:
				active = false;
				break;
			default:
				exibir("Opção inválida!");
				break;
			}
		}
	}

	public static void adicionarLetra(String letra, Rodada rodada){
		
		boolean acertou = false;
		
		for (int i = 0; i < rodada.getPalavra().length; i++) {
			if (letra.equals(rodada.getPalavra()[i])){
				rodada.getEscondidas()[i] = letra;
				acertou = true;
			}
		}
		
		String strPalavra = "";
		String strEscondidas = "";
		
		for (int i = 0; i < rodada.getPalavra().length; i++) {
			strPalavra += rodada.getPalavra()[i];
			strEscondidas += rodada.getEscondidas()[i];
		}
		
		if (acertou == false){
			rodada.setVida(1);
			rodada.getBoneco().setVida(rodada.getVida());
			rodada.getBoneco().atualizarBoneco();
			rodada.setLetrasErradas(letra);
			Jogo.exibir("Você errou!");
		}else{
			if (rodada.getLetrasAcertadas().contains(letra)){
				Jogo.exibir("Você já inseriu essa letra!");
			}else{
				rodada.getJogador().setPontos(10);
				rodada.setLetrasAcertadas(letra);
				Jogo.exibir("Você acertou! Ganhou 10 pontos!");
			}
		}
		
		if (strPalavra.equals(strEscondidas)){
			rodada.getBoneco().setDead(true);
			Jogo.exibir("Parabéns! Você venceu a rodada!\nJogador: " + rodada.getJogador().getNome() + "\nPontuação final: " + rodada.getJogador().getPontos() + "\nTema: " + rodada.getTema().getNome() + "\nPalavra: " + strPalavra);}
		else if (rodada.getVida() == 0){
			rodada.getBoneco().setDead(true);
			Jogo.exibir("Você perdeu! Mais sorte da próxima vez!\n\nJogador: " + rodada.getJogador().getNome() + "\nPontuação final: " + rodada.getJogador().getPontos() + "\nTema: " + rodada.getTema().getNome() + "\nPalavra: " + strPalavra);}
			}
	
	public static void inputLetra(Rodada rodada){
			String letra = Jogo.inputStr(mostrar(rodada) + "\nDigite uma letra:");
			adicionarLetra(letra.toUpperCase(), rodada);
	}
	
	public static String mostrar(Rodada rodada){
		String str = "";
		String strEscondidas = "";
		for (int i = 0; i < rodada.getEscondidas().length; i++) {
			strEscondidas += rodada.getEscondidas()[i];
		}
		str = "Jogador: " + rodada.getJogador().getNome() + "\nPontuação parcial: " + rodada.getJogador().getPontos() + "\n==\n   |\n" + rodada.getBoneco().mostrar() + "\n\nTema: " + rodada.getTema().getNome() + "\nPalavra: " + strEscondidas + "\nLetras erradas: " + rodada.getLetrasErradas();
		return str;
	}
	
	public static void incrementarTemas(Rodada rodada){
		while(true){
			
			String menu = "1 - Adicionar palavra em tema existente\n2 - Criar tema\n0 - Voltar ao menu principal\n\nInsira uma opção:";
			
			int op = inputInt(menu);
			switch (op) {
			case 1:

				String nomeTema, novaPalavra;
				int i;
				nomeTema = inputStr(exibirTemas(rodada) + "\nDigite o nome do tema: ");
				int notFind = -1;
				
				for (i = 0; i < rodada.getTemas().size(); i++) {
					if (rodada.getTemas().get(i).getNome().toUpperCase().equals(nomeTema.toUpperCase())){
						notFind++;
						break;
					}
				}
				
				if (notFind == -1){
					exibir("O tema inserido ('"+ nomeTema.toUpperCase() +"') não existe!");
					incrementarTemas(rodada);
				}
				
				novaPalavra = inputStr("Digite palavra a adicionar: ");
				rodada.getTemas().get(i).adicionarPalavra(novaPalavra);
				exibir("Palavra adicionada com sucesso!\n");
				break;
				
			case 2:
				
					String novoTema, conjuntoTema;
					int exists = -1;
					
					novoTema = inputStr("Digite nome do novo tema:");
					
					for (i = 0; i < rodada.getTemas().size(); i++) {
						if (rodada.getTemas().get(i).getNome().toUpperCase().equals(novoTema.toUpperCase())){
							exists++;
							break;
						}
					}
					
					if (exists != -1){
						exibir("Um tema com o nome '"+ novoTema.toUpperCase() +"' já existe!");
						incrementarTemas(rodada);
					}
					
					conjuntoTema = inputStr("Digite as palavras (separadas por espaço, sem acentuação): ");
					Tema newTema = new Tema(novoTema, conjuntoTema);
					rodada.getTemas().add(newTema);
					Jogo.exibir("Tema adicionado com sucesso!\n");
					break;
				
			case 0:
				break;
			default:
				exibir("Opção inválida!");
				break;
			}
			
			if (op == 0){break;}
		
		}
	}
	
	public static String exibirTemas(Rodada rodada){
		
		String str = "Temas existentes: \n";
		
		for (int i = 0; i < rodada.getTemas().size(); i++) {
			if (rodada.getTemas().get(i) != null){
				str += "'" + rodada.getTemas().get(i).getNome() + "' ("+ rodada.getTemas().get(i).getConjuntoPalavras().length +" palavras)\n"; 
			}
		}
		
		return str;
		
	}
	public static void exibir(String str) {
		JOptionPane.showMessageDialog(null, str);
	}

	public static int inputInt(String str) {
		return Integer.parseInt(JOptionPane.showInputDialog(str));
	}
	
	public static String inputStr(String str) {
		return JOptionPane.showInputDialog(str);
	}
	
	
}