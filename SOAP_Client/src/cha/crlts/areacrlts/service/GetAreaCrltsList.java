/**
 * GetAreaCrltsList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.areacrlts.service;

public class GetAreaCrltsList  implements java.io.Serializable {
    private cha.crlts.areacrlts.service.AreaCrltsListRequest arg0;

    public GetAreaCrltsList() {
    }

    public GetAreaCrltsList(
           cha.crlts.areacrlts.service.AreaCrltsListRequest arg0) {
           this.arg0 = arg0;
    }


    /**
     * Gets the arg0 value for this GetAreaCrltsList.
     * 
     * @return arg0
     */
    public cha.crlts.areacrlts.service.AreaCrltsListRequest getArg0() {
        return arg0;
    }


    /**
     * Sets the arg0 value for this GetAreaCrltsList.
     * 
     * @param arg0
     */
    public void setArg0(cha.crlts.areacrlts.service.AreaCrltsListRequest arg0) {
        this.arg0 = arg0;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAreaCrltsList)) return false;
        GetAreaCrltsList other = (GetAreaCrltsList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arg0==null && other.getArg0()==null) || 
             (this.arg0!=null &&
              this.arg0.equals(other.getArg0())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getArg0() != null) {
            _hashCode += getArg0().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAreaCrltsList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arg0");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arg0"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsListRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}