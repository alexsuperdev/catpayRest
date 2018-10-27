package domiu.dto;

public class  PaymentTemplate {

    public static String TEMPLATE ="<DirectDebit>\n" +
            "\t\t<LocalInstrument>CORE</LocalInstrument>\n" +
            "\t\t<InitPtyCreditor>\n" +
            "\t\t\t<Name>##Name##</Name>\n" +
            "\t\t\t<IBAN>##IBANABSENDER##</IBAN>\n" +
            "\t\t\t<Id>GFEDCBA</Id>\n" +
            "\t\t\t<CreditorId>DE23ABCDEFG</CreditorId>\n" +
            "\t\t</InitPtyCreditor>\n" +
            "\t\t<Transaction>\n" +
            "\t\t\t<Amount Ccy=\"EUR\">##Amount##</Amount>\n" +
            "\t\t\t<Purpose>##Verwendungszweck##</Purpose>\n" +
            "\t\t\t<ExecutionDate>2018-10-10</ExecutionDate>\n" +
            "\t\t\t<EndToEndId>Bob EndToEndId 1</EndToEndId>\n" +
            "\t\t\t<OthrPtyDebitor>\n" +
            "\t\t\t\t<Name>Alice</Name>\n" +
            "\t\t\t\t<IBAN>##IBANENPOFAENGER##</IBAN>\n" +
            "\t\t\t\t<MandateData>\n" +
            "\t\t\t\t\t<MandateId>MNDID123456</MandateId>\n" +
            "\t\t\t\t\t<MandateDate>2018-10-11</MandateDate>\n" +
            "\t\t\t\t\t<Sequence>RCUR</Sequence>\n" +
            "\t\t\t\t</MandateData>\n" +
            "\t\t\t</OthrPtyDebitor>\n" +
            "\t\t</Transaction>\n" +
            "\t</DirectDebit>";
}
