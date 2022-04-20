package poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestaGerador {


    @Test
    public void barCode(){
        Principal p = new Principal();
        assertTrue(p.gerar("barcode", "12345", "saida.png"));
        assertFalse(p.gerar("barcode", "valor invalido", "saida.png"));
    }

    @Test
    public void qrCode(){
        Principal p = new Principal();
        assertTrue(p.gerar("qrcode", "12345", "saida.png"));
        assertTrue(p.gerar("qrcode", "http://www.ifsc.edu.br", "saida.png"));
    }

    @Test
    public void tipoInvalido(){
        Principal p = new Principal();
        assertFalse(p.gerar("invalido", "12345", "saida.png"));
        assertFalse(p.gerar("", "12345", "saida.png"));
        assertFalse(p.gerar(null, "12345", "sainda.png"));
    }

    @Test
    public void valorVazioOuNulo(){
        Principal p = new Principal();
        assertFalse(p.gerar("barcode", "", "saida.png"));
        assertFalse(p.gerar("qrcode", "", "saida.png"));
        assertFalse(p.gerar("barcode", null, "sainda.png"));
    }

    @Test
    public void nomeDeArquivoInvalido(){
        Principal p = new Principal();
        assertFalse(p.gerar("barcode", "12345", ""));
        assertFalse(p.gerar("barcode", "12345", null));
        assertFalse(p.gerar("barcode", "12345", "/sempermissao.png"));
        assertFalse(p.gerar("qrcode", "12345", "/sempermissao.png"));
    }
}
