package poo;

import barcode.CodigoDeBarra;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Emerson Ribeiro de Mello - http://docente.ifsc.edu.br/mello
 */
public class Principal {

    /**
     * Gera um imagem PNG com um código de barras ou com um QRCode
     *
     * @param tipo          - deve ser fornecido barcode ou qrcode
     * @param valor         - string que será codificada no barcode ou qrcode
     * @param nomeDoArquivo - nome do arquivo que irá conter a imagem gerada
     */
    public boolean gerar(String tipo, String valor, String nomeDoArquivo) {
        if ((nomeDoArquivo == null)||(tipo == null)||(valor == null)){
           return false;
        }
        if(nomeDoArquivo.length() == 0){
            return false;
        }
        try {
            switch (tipo) {
                case "barcode":
                    // valor para o código de barras deve obrigatoriamente ser um inteiro
                    int valorInt = Integer.parseInt(valor);
                    CodigoDeBarra.gerarCodigoDeBarra(valorInt, nomeDoArquivo);
                    return true;
                case "qrcode":
                    Path filePath = FileSystems.getDefault().getPath(nomeDoArquivo);
                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
                    BitMatrix bitMatrix = qrCodeWriter.encode(valor, BarcodeFormat.QR_CODE, 300, 300);
                    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);
                    return true;
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro na conversão para inteiro. Garanta que o 2o argumento seja um número inteiro");
        } catch (WriterException | IOException e) {
            System.err.println("Não foi possível gerar arquivo com o argumento fornecido. Erro: " + e.toString());
        } catch (Exception e) {
            System.err.println("Algo deu errado. " + e.toString());
        }

        return false;
    }


    public void sintaxeErrada() {
        System.err.println("Sintaxe errada! É necessário fornecer os seguintes parâmetros\n");
        System.err.println("(barcode | qrcode) (numero | 'string') nome-do-arquivo.png\n");
        System.err.println("Exemplo para gerar um código de barra: barcode 123456 codigo.png\n");
        System.err.println("Exemplo para gerar um QRCode: qrcode 'http://ifsc.edu.br' imagem.png\n");
        System.exit(1);
    }

    public static void main(String[] args) {
        Principal p = new Principal();

        if (args.length != 3) {
            p.sintaxeErrada();
        }

        if (!p.gerar(args[0], args[1], args[2])) {
            p.sintaxeErrada();
        }
    }
}
