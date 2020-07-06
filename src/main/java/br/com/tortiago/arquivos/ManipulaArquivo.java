package br.com.tortiago.arquivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ManipulaArquivo {

	private static final int BUFFER = 2048;
	
	public static void deletaArquivo(String arquivo) {
		try {
			File file = new File(arquivo); 
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deZipArquivo(String arquivo, String pastaDestino) {
		try {

			File file = new File(pastaDestino);

			// Se nao existir a pasta destino
			// sera criada por nosso programa
			if (file.exists() == false) {
				file.mkdirs();
			}

			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(arquivo);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extraindo o arquivo: " + entry);
				int count;
				byte data[] = new byte[BUFFER];
				// Cria os arquivos no disco
				FileOutputStream fos = new FileOutputStream(pastaDestino + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
