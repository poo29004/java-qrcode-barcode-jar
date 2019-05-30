package poo;

import org.junit.Assert;
import org.junit.Test;

public class TestaGerador {


    @Test
    public void barCode(){
        Principal p = new Principal();
        Assert.assertTrue(p.gerar("barcode", "12345", "saida.png"));
        Assert.assertFalse(p.gerar("barcode", "valor invalido", "saida.png"));
    }

    @Test
    public void qrCode(){
        Principal p = new Principal();
        Assert.assertTrue(p.gerar("qrcode", "12345", "saida.png"));
        Assert.assertTrue(p.gerar("qrcode", "http://www.ifsc.edu.br", "saida.png"));
    }

    @Test
    public void tipoInvalido(){
        Principal p = new Principal();
        Assert.assertFalse(p.gerar("invalido", "12345", "saida.png"));
        Assert.assertFalse(p.gerar("", "12345", "saida.png"));
        Assert.assertFalse(p.gerar(null, "12345", "sainda.png"));
    }

    @Test
    public void valorVazioOuNulo(){
        Principal p = new Principal();
        Assert.assertFalse(p.gerar("barcode", "", "saida.png"));
        Assert.assertFalse(p.gerar("qrcode", "", "saida.png"));
        Assert.assertFalse(p.gerar("barcode", null, "sainda.png"));
    }

    @Test
    public void nomeDeArquivoInvalido(){
        Principal p = new Principal();
        Assert.assertFalse(p.gerar("barcode", "12345", ""));
        Assert.assertFalse(p.gerar("barcode", "12345", null));
        Assert.assertFalse(p.gerar("barcode", "12345", "/sempermissao.png"));
        Assert.assertFalse(p.gerar("qrcode", "12345", "/sempermissao.png"));
    }
}
