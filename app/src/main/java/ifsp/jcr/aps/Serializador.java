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

  public static <T extends Serializable> T desserializar(String encodedObject)
      throws IOException, ClassNotFoundException {
    byte[] serializedObject = Base64.getDecoder().decode(encodedObject);
    ObjectInputStream objectInStream = new ObjectInputStream(
        new ByteArrayInputStream(serializedObject)
    );
    T object = (T) objectInStream.readObject(); // unchecked conversion
    objectInStream.close();
    return object;
  }

  public static <V extends Serializable> HashMap<Integer, V>
  desserializarVarios(String encodedCollection)
      throws IOException, ClassNotFoundException {
    Object decodedCollection = Serializador.desserializar(encodedCollection);
    if (decodedCollection instanceof HashMap) {
      return (HashMap<Integer, V>) decodedCollection; // unchecked conversion
    }
    throw new IllegalArgumentException("Not a HashMap");
  }
}