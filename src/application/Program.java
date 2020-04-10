package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Produtos;

public class Program {

	public static void main(String[] args) throws ParseException{
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Produtos> produtos = new ArrayList<>();
				
		System.out.println("Digite o endereco do arquivo:" );
		String strCaminho = sc.nextLine();
		
		File caminho = new File(strCaminho);
		File caminhoPasta = new File(caminho.getParent());
		
		boolean sucesso = new File (caminhoPasta + "\\out").mkdir();
		
		String caminhoPastaNova = caminhoPasta + "\\out\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(strCaminho))) {
			
			String itensLista = br.readLine();
			while(itensLista != null) {
				
				String[] fields = itensLista.split(",");
				produtos.add(new Produtos(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2])));
				itensLista = br.readLine();
				
				}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoPastaNova))) {

				for (Produtos item : produtos) {
					bw.write(item.getNome() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}

				System.out.println(caminhoPastaNova + " CRIADO!");
				
			} 
			catch (IOException e) {
				System.out.println("Erro escrevendo arquivos: " + e.getMessage());
			}
			
			} 
		
		catch(IOException e) {
			System.out.println("Erro: "+ e.getMessage());
		}
		
					
		
		sc.close();
	}

}
