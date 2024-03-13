package ifsp.jcr.aps;

import java.io.*;
import java.util.Base64;
import java.util.HashSet;

class ServicoDeMensageiro {
  public static class Mensageiro<T> {
    static String decodificar(Serializable object) throws IOException {
      ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
      ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
      objectOutStream.writeObject(object);
      objectOutStream.close();
      return Base64.getEncoder().encodeToString(byteOutStream.toByteArray());
    }

    static Object decodificar(String encodedObject) throws IOException, ClassNotFoundException {
      byte[] serializedObject = Base64.getDecoder().decode(encodedObject);
      ObjectInputStream objectInStream = new ObjectInputStream(
          new ByteArrayInputStream(serializedObject)
      );
      Object object = objectInStream.readObject();
      objectInStream.close();
      return object;
    }

    public static <T> HashSet<T> decodificarVarias(String encodedCollection) throws IOException, ClassNotFoundException {
      Object decodedCollection = Mensageiro.decodificar(encodedCollection);
      if (decodedCollection instanceof HashSet) {
        if (!((HashSet<?>) decodedCollection).isEmpty()) {
          return (HashSet<T>) decodedCollection;
        }
        throw new IllegalArgumentException("HashSet is empty");
      }
      throw new IllegalArgumentException("Not a HashSet");
    }
  }
}