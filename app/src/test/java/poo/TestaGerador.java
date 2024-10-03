package poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestaGerador {


    @Test
    public void barCode(){
        App p = new App();
        assertTrue(p.gerar("barcode", "12345", "saida.png"));
        assertFalse(p.gerar("barcode", "valor invalido", "saida.png"));
    }

    @Test
    public void qrCode(){
        App p = new App();
        assertTrue(p.gerar("qrcode", "12345", "saida.png"));
        assertTrue(p.gerar("qrcode", "http://www.ifsc.edu.br", "saida.png"));
    }

    @Test
    public void tipoInvalido(){
        App p = new App();
        assertFalse(p.gerar("invalido", "12345", "saida.png"));
        assertFalse(p.gerar("", "12345", "saida.png"));
        assertFalse(p.gerar(null, "12345", "sainda.png"));
    }

    @Test
    public void valorVazioOuNulo(){
        App p = new App();
        assertFalse(p.gerar("barcode", "", "saida.png"));
        assertFalse(p.gerar("qrcode", "", "saida.png"));
        assertFalse(p.gerar("barcode", null, "sainda.png"));
    }

    @Test
    public void nomeDeArquivoInvalido(){
        App p = new App();
        assertFalse(p.gerar("barcode", "12345", ""));
        assertFalse(p.gerar("barcode", "12345", null));
        assertFalse(p.gerar("barcode", "12345", "/sempermissao.png"));
        assertFalse(p.gerar("qrcode", "12345", "/sempermissao.png"));
    }
}
