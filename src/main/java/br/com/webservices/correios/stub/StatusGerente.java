//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.0 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2022.03.16 às 04:20:35 PM BRT 
//


package br.com.webservices.correios.stub;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de statusGerente.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="statusGerente"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Ativo"/&gt;
 *     &lt;enumeration value="Inativo"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "statusGerente")
@XmlEnum
public enum StatusGerente {

    @XmlEnumValue("Ativo")
    ATIVO("Ativo"),
    @XmlEnumValue("Inativo")
    INATIVO("Inativo");
    private final String value;

    StatusGerente(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusGerente fromValue(String v) {
        for (StatusGerente c: StatusGerente.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
