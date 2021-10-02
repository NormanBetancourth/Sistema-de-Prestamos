package modelo;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileOutputStream;

public class JAXBParser {
    public void marshall(Object o, String filename){
        try{
            JAXBContext jContent = JAXBContext.newInstance(o.getClass());

            Marshaller marshallObj = jContent.createMarshaller();

            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileOutputStream f = new FileOutputStream(filename);
            marshallObj.marshal(o,f);





        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  Object unmarshall(Object ref, String filename){
        Object o = null;
        try{
            //obtenemos el archivo fuente
            File file = new File(filename);
            //creamos el contexto
            JAXBContext jConntext = JAXBContext.newInstance(ref.getClass());

            //creamos el objeto unmarshall
            Unmarshaller unmarshallerObj = jConntext.createUnmarshaller();

            //obtenemos el estudiante
            o = unmarshallerObj.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return o;
    }
}
