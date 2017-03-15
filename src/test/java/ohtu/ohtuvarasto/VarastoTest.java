package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2LuoSaldonJaTilavuudenOikein() {
        varasto = new Varasto(2,2);
        assertEquals(2,varasto.getSaldo(),vertailuTarkkuus);
        assertEquals(2,varasto.getTilavuus(),vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2LuoNegatiivisellaSaldollaNollaSaldon() {
        varasto = new Varasto(2,-2);
        assertEquals(0,varasto.getSaldo(),vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2LuoNegatiivisellaTilavuudellaNollaTilavuuden() {
        varasto = new Varasto(-2,2);
        assertEquals(0,varasto.getTilavuus(),vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisellaVarastonTilavuudellaVarastoJonkaTilavuusNolla() {
        varasto = new Varasto(-2);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoilisataTilavuudenYli() {
        varasto.lisaaVarastoon(1000);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttamienPalauttaaNollan() {
        varasto.lisaaVarastoon(5);
        assertEquals(0,varasto.otaVarastosta(-1),vertailuTarkkuus);
    }
    
    @Test
    public void saldonYliOttaminenPalauttaaSaldonVerran() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(6),vertailuTarkkuus);
    }
    
    @Test
    public void saldonYliOttaminenNollaaSaldon() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(6);
        assertEquals(0, varasto.getSaldo(),vertailuTarkkuus);
    }

}