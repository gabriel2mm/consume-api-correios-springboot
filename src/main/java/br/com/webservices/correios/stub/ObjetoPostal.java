//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.0 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2022.03.17 às 04:34:21 PM BRT 
//


package br.com.webservices.correios.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java de objetoPostal complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="objetoPostal"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codigoEtiqueta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataAtualizacaoCliente" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataBloqueioObjeto" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataCancelamentoEtiqueta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInclusao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="objetoPostalPK" type="{http://cliente.bean.master.sigep.bsb.correios.com.br/}objetoPostalPK" minOccurs="0"/&gt;
 *         &lt;element name="plpNu" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="preListaPostagem" type="{http://cliente.bean.master.sigep.bsb.correios.com.br/}preListaPostagem" minOccurs="0"/&gt;
 *         &lt;element name="restricaoAerea" type="{http://cliente.bean.master.sigep.bsb.correios.com.br/}simNao" minOccurs="0"/&gt;
 *         &lt;element name="statusBloqueio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statusEtiqueta" type="{http://cliente.bean.master.sigep.bsb.correios.com.br/}statusObjetoPostal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "objetoPostal", propOrder = {
    "codigoEtiqueta",
    "dataAtualizacaoCliente",
    "dataBloqueioObjeto",
    "dataCancelamentoEtiqueta",
    "dataInclusao",
    "objetoPostalPK",
    "plpNu",
    "preListaPostagem",
    "restricaoAerea",
    "statusBloqueio",
    "statusEtiqueta"
})
public class ObjetoPostal {

    protected String codigoEtiqueta;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAtualizacaoCliente;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataBloqueioObjeto;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCancelamentoEtiqueta;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInclusao;
    protected ObjetoPostalPK objetoPostalPK;
    protected Long plpNu;
    protected PreListaPostagem preListaPostagem;
    @XmlSchemaType(name = "string")
    protected SimNao restricaoAerea;
    protected String statusBloqueio;
    @XmlSchemaType(name = "string")
    protected StatusObjetoPostal statusEtiqueta;

    /**
     * Obtém o valor da propriedade codigoEtiqueta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    /**
     * Define o valor da propriedade codigoEtiqueta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEtiqueta(String value) {
        this.codigoEtiqueta = value;
    }

    /**
     * Obtém o valor da propriedade dataAtualizacaoCliente.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAtualizacaoCliente() {
        return dataAtualizacaoCliente;
    }

    /**
     * Define o valor da propriedade dataAtualizacaoCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAtualizacaoCliente(XMLGregorianCalendar value) {
        this.dataAtualizacaoCliente = value;
    }

    /**
     * Obtém o valor da propriedade dataBloqueioObjeto.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataBloqueioObjeto() {
        return dataBloqueioObjeto;
    }

    /**
     * Define o valor da propriedade dataBloqueioObjeto.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataBloqueioObjeto(XMLGregorianCalendar value) {
        this.dataBloqueioObjeto = value;
    }

    /**
     * Obtém o valor da propriedade dataCancelamentoEtiqueta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCancelamentoEtiqueta() {
        return dataCancelamentoEtiqueta;
    }

    /**
     * Define o valor da propriedade dataCancelamentoEtiqueta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCancelamentoEtiqueta(XMLGregorianCalendar value) {
        this.dataCancelamentoEtiqueta = value;
    }

    /**
     * Obtém o valor da propriedade dataInclusao.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInclusao() {
        return dataInclusao;
    }

    /**
     * Define o valor da propriedade dataInclusao.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInclusao(XMLGregorianCalendar value) {
        this.dataInclusao = value;
    }

    /**
     * Obtém o valor da propriedade objetoPostalPK.
     * 
     * @return
     *     possible object is
     *     {@link ObjetoPostalPK }
     *     
     */
    public ObjetoPostalPK getObjetoPostalPK() {
        return objetoPostalPK;
    }

    /**
     * Define o valor da propriedade objetoPostalPK.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjetoPostalPK }
     *     
     */
    public void setObjetoPostalPK(ObjetoPostalPK value) {
        this.objetoPostalPK = value;
    }

    /**
     * Obtém o valor da propriedade plpNu.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlpNu() {
        return plpNu;
    }

    /**
     * Define o valor da propriedade plpNu.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlpNu(Long value) {
        this.plpNu = value;
    }

    /**
     * Obtém o valor da propriedade preListaPostagem.
     * 
     * @return
     *     possible object is
     *     {@link PreListaPostagem }
     *     
     */
    public PreListaPostagem getPreListaPostagem() {
        return preListaPostagem;
    }

    /**
     * Define o valor da propriedade preListaPostagem.
     * 
     * @param value
     *     allowed object is
     *     {@link PreListaPostagem }
     *     
     */
    public void setPreListaPostagem(PreListaPostagem value) {
        this.preListaPostagem = value;
    }

    /**
     * Obtém o valor da propriedade restricaoAerea.
     * 
     * @return
     *     possible object is
     *     {@link SimNao }
     *     
     */
    public SimNao getRestricaoAerea() {
        return restricaoAerea;
    }

    /**
     * Define o valor da propriedade restricaoAerea.
     * 
     * @param value
     *     allowed object is
     *     {@link SimNao }
     *     
     */
    public void setRestricaoAerea(SimNao value) {
        this.restricaoAerea = value;
    }

    /**
     * Obtém o valor da propriedade statusBloqueio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusBloqueio() {
        return statusBloqueio;
    }

    /**
     * Define o valor da propriedade statusBloqueio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusBloqueio(String value) {
        this.statusBloqueio = value;
    }

    /**
     * Obtém o valor da propriedade statusEtiqueta.
     * 
     * @return
     *     possible object is
     *     {@link StatusObjetoPostal }
     *     
     */
    public StatusObjetoPostal getStatusEtiqueta() {
        return statusEtiqueta;
    }

    /**
     * Define o valor da propriedade statusEtiqueta.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusObjetoPostal }
     *     
     */
    public void setStatusEtiqueta(StatusObjetoPostal value) {
        this.statusEtiqueta = value;
    }

}
