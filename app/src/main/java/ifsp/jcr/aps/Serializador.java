package ifsp.jcr.aps;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;

public class Serializador <T> {
  public static String serializar(Serializable object) throws IOException {
    ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
    objectOutStream.writeObject(object);
    objectOutStream.close();
    return Base64.getEncoder().encodeToString(byteOutStream.toByteArray());
  }

  public static Object desserializar(String encodedObject) throws IOException, ClassNotFoundException {
    byte[] serializedObject = Base64.getDecoder().decode(encodedObject);
    ObjectInputStream objectInStream = new ObjectInputStream(
        new ByteArrayInputStream(serializedObject)
    );
    Object object = objectInStream.readObject();
    objectInStream.close();
    return object;
  }

  public static <T> HashMap<Integer, T> desserializarVarios(String encodedCollection) throws IOException, ClassNotFoundException {
    Object decodedCollection = Serializador.desserializar(encodedCollection);
    if (decodedCollection instanceof HashMap) {
      if (!((HashMap<Integer, T>) decodedCollection).isEmpty()) {
        return (HashMap<Integer, T>) decodedCollection;
      }
      throw new IllegalArgumentException("HashMap is empty");
    }
    throw new IllegalArgumentException("Not a HashMap");
  }
}