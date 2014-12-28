
package net.cateam.service.verify;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.cateam.service.verify package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Verify_QNAME = new QName("http://webservice/", "verify");
    private final static QName _VerifyResponse_QNAME = new QName("http://webservice/", "verifyResponse");
    private final static QName _VerifyArg1_QNAME = new QName("", "arg1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.cateam.service.verify
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VerifyResponse }
     * 
     */
    public VerifyResponse createVerifyResponse() {
        return new VerifyResponse();
    }

    /**
     * Create an instance of {@link Verify }
     * 
     */
    public Verify createVerify() {
        return new Verify();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Verify }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "verify")
    public JAXBElement<Verify> createVerify(Verify value) {
        return new JAXBElement<Verify>(_Verify_QNAME, Verify.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "verifyResponse")
    public JAXBElement<VerifyResponse> createVerifyResponse(VerifyResponse value) {
        return new JAXBElement<VerifyResponse>(_VerifyResponse_QNAME, VerifyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg1", scope = Verify.class)
    public JAXBElement<byte[]> createVerifyArg1(byte[] value) {
        return new JAXBElement<byte[]>(_VerifyArg1_QNAME, byte[].class, Verify.class, ((byte[]) value));
    }

}
